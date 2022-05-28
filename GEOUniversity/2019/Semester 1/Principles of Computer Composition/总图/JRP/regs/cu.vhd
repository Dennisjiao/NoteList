--ʵ��12 ΢���������ʵ�� 
LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL,IEEE.NUMERIC_STD.ALL;
--USE WORK.CPU_DEFS.ALL;

ENTITY CU IS
PORT( clock      : IN   STD_LOGIC;--ʱ�ӣ���Ҫ���룩
      reset      : IN   STD_LOGIC;--��λ�źţ���Ҫ���룩
      op         : in   STD_LOGIC_VECTOR(2 DOWNTO 0); --����ָ�������  
      z_flag      : IN   STD_LOGIC;   	--״̬�ź�
	  data_r_out : buffer  STD_LOGIC_VECTOR(19 DOWNTO 0);--΢ָ������ź�(����)
	  op_out     : OUT  STD_LOGIC_VECTOR(2 DOWNTO 0);--����ָ�������
	  add_r_out  : OUT  UNSIGNED(4 DOWNTO 0);--΢��ַ���µ�ַ  
	--�������������ź�
	  ALU_flag  :out  STD_LOGIC;
      R_NW	    :out  STD_LOGIC;
      cs		:out  STD_LOGIC;
      INC_PC    :out std_logic;
      load_MDR  :out std_logic;
      MDR_bus	:out STD_LOGIC;
      load_MAR  :out std_logic;
      load_IR   :out std_logic;
      PC_bus    :out STD_LOGIC;
      load_PC   :out std_logic;
      ALU_op    :out  STD_LOGIC_VECTOR(6 DOWNTO 5);
      Addr_bus  :out  STD_LOGIC;
      load_ACC  :out  STD_LOGIC;
      ACC_bus   :out  STD_LOGIC
	    
	);	         
END ENTITY;

ARCHITECTURE rtl OF CU IS	
	
	TYPE microcode_array IS ARRAY (0 TO 14) OF STD_LOGIC_VECTOR(19 DOWNTO 0);--���Ͷ����װ��΢���������
	CONSTANT code      : microcode_array:=(
				0=> "00010100000000000001",
				1=> "10000001010110000010",
				2=> "00001010000000000011",
				3=> "00000100001000001111",	
				4=> "00100010000000000000",
				5=> "00000000000100000000",
				6=> "00000010100001000000",
				7=> "00000010100000100000",
				8=> "00000001000110000100",
				9=> "01000001000000000101",
			    10=> "00000010000110000110",	
			    11=> "00000010000110000111",
			    12=> "00000010000110010000",
       		    13=> "10000010000000000000",
		        14=> "00000000000000000000");	
	--��0-14�����������,constant���ڿ���ROM��code�ǳ��������֣�����������
	
BEGIN	
		
	PROCESS(reset,clock)			
		VARIABLE microcode : microcode_array;
		VARIABLE add_r     : UNSIGNED(4 DOWNTO 0);	
    	VARIABLE data_r    : STD_LOGIC_VECTOR(19 DOWNTO 0);
		VARIABLE temp      : STD_LOGIC_VECTOR(4 DOWNTO 0);
	BEGIN		
	--���̱���
		IF reset='0' THEN
			add_r:=(OTHERS =>'0');	--othersָ����λ��Ϊ0	addr��΢��ַ	         

			
		ELSIF RISING_EDGE(clock) THEN			
			--microprogram controller	
			data_r  := code(TO_INTEGER(add_r));--ȡ΢ָ��
			IF data_r(4 DOWNTO 0)="01111" THEN --�ж��µ�ַ�����ݻ���ָ��ת���γ���ڵ�ַ������if��caseʵ��
			    temp:="01" & op(2 DOWNTO 0);
				add_r := UNSIGNED(temp);
			ELSIF data_r(4 DOWNTO 0)="10000"  THEN
				IF z_flag='1' THEN
					add_r:="11110";--14
				ELSE
					add_r:="11101";--13
				END IF;
			ELSE
				add_r   := UNSIGNED(data_r(4 DOWNTO 0));--�����µ�ֱַ��ת��
			END IF;			
			data_r_out <=data_r;--���΢ָ��
			add_r_out <= add_r;--�����ַ
			
			
		END IF;	--���µ�ַ
		
		
	END PROCESS;
	ALU_OP <=data_r_out(6 DOWNTO 5);
		R_NW<=data_r_out(7);	
		cs<=data_r_out(8);
		Addr_bus<=data_r_out(9);
        INC_PC<=data_r_out(10);
        ALU_flag <=data_r_out(11);
        load_MDR<=data_r_out(12) and (not clock);
        MDR_bus<=data_r_out(13);
        load_MAR<=data_r_out(14)and (not clock);
        load_IR<=data_r_out(15)and (not clock);
        PC_bus<=data_r_out(16);
        load_ACC<=data_r_out(17)and (not clock);
        ACC_bus<=data_r_out(18); 
        load_PC<=data_r_out(19)and (not clock);
        op_out<=op;
									
END ARCHITECTURE;			
--�����źŵľ��岿��
--���߸ߵ�ƽ�������ź���������ڵĸߵ�ƽ			