library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
ENTITY ALU IS
	PORT
	(
		
		OP			: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		B		: IN STD_LOGIC_VECTOR(7 downto 0);
		A			: IN STD_LOGIC_VECTOR(7 DOWNTO 0);
		Flags		: BUFFER STD_LOGIC;
		result			: BUFFER STD_LOGIC_VECTOR(7 DOWNTO 0)
	);
END ENTITY ALU;
architecture ONE of ALU is

begin
process(OP)
begin
		
			case OP is
				when "00"=>result<=A+B;
				when "01"=>result<=A+(not B)+1;
				when "10"=>result<=A and B;
				when "11"=>result<=B;
			end case;
			if result="00000000" then
				Flags<='1';
			else
				Flags<='0';
			end if;
		
end process;
 
end;