LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY ram_256 IS
PORT (
		reset	: IN  STD_LOGIC;
		cs		: IN  STD_LOGIC;
		R_NW	: IN  STD_LOGIC;
		MAR		: IN STD_LOGIC_VECTOR(7 DOWNTO 0);
		MDR_IN	: IN  STD_LOGIC_VECTOR(7 DOWNTO 0);
		MDR_OUT	: BUFFER  STD_LOGIC_VECTOR(7 DOWNTO 0)
);
END ram_256;

ARCHITECTURE ONE OF ram_256 IS
SUBTYPE ram_word IS STD_LOGIC_VECTOR(0 TO 7 );
TYPE ram_type IS ARRAY (0 TO (31)) OF ram_word;
SIGNAL ram : ram_type;
CONSTANT ram_start: ram_type :=(
0=> "11"&"1000"&"01",--mov r1,mem
1=> "00000001",
2=> "00"&"0000"&"00",--add r0,r1
3=> "11"&"1010"&"00",--mov [mem],r0
4=> "00000111",
5=> "11"&"1000"&"10",--mov PC,mem
6=> "00000010",
7=> "00000000",
OTHERS => (OTHERS =>'0'));
begin
	PROCESS(cs,R_NW,reset)
	begin
		if reset = '0' then
			ram <= ram_start;
			MDR_out <= "00000000";
		elsif rising_edge(cs) then
				if R_NW='1' THEN
					MDR_OUT <= ram(conv_integer(MAR));
                    
				else
				 	ram(conv_integer(MAR)) <= MDR_IN;
					MDR_OUT <= MDR_IN;
				end if;
		END IF;
	end process;
end;