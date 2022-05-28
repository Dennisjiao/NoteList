LIBRARY ieee;
use ieee.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
entity ALU is
	port (
			a,b: in std_logic_vector(7 downto 0);
			c:buffer std_logic_vector(7 downto 0);
			cin:buffer std_logic;
			flag: buffer std_logic;
			choice :in std_logic_vector(1 downto 0)
		 );
end ALU;

architecture adderdo of ALU is 
signal a1,b1,c1: std_logic_vector(8 downto 0);
begin
process(a,b,flag,choice)
begin	
		a1<='0'&a;
		b1<='0'&b;	
		if (choice="00") then 
		c1<=a1 + b1;
		c<=c1(7 downto 0);
		cin<=c1(8);
		elsif(choice="01") then
		c1<=a1-b1;
		c<=c1(7 downto 0);
		cin<=c1(8);
		elsif(choice="10") then
		c<=a AND b;
		cin<='0';
		elsif(choice="11") then
		c<=b;
		cin<='0';
		end if;
	if(c="00000000" and cin='0')then 
		flag<='1';
	else flag<='0';
	end if;
		end process;
		end adderdo;