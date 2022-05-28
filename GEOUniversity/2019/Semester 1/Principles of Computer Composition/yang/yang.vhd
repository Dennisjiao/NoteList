library ieee;
use ieee.std_logic_1164.all;

entity yang is
port(
		reset,load_ACC:in std_logic;		
		a2:in std_logic_vector(7 downto 0);
		ACC:buffer std_logic_vector(7 downto 0)
	);
end yang;

architecture leijia of yang is
begin
process(reset,load_ACC)
begin
	if(reset='0') then
	ACC<="00000000";
	elsif(load_ACC'EVENT AND load_ACC='1') then
	ACC<=a2;
	end if;
	end process;
	end leijia;