library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

ENTITY IR IS
	PORT
	(
		reset	: IN		STD_LOGIC;
		cp : IN          STD_LOGIC;
		d			: IN		STD_LOGIC_VECTOR(7 DOWNTO 0);
		q			: BUFFER	STD_LOGIC_VECTOR(7 DOWNTO 0);
		q2			: BUFFER	STD_LOGIC_VECTOR(2 DOWNTO 0)
	);
END IR;

ARCHITECTURE one OF IR IS

BEGIN
	PROCESS (cp,reset)
	BEGIN
		IF reset = '0' THEN
			q  <= "00000000";
			q2 <= "000";
		ELSIF Rising_edge(cp) THEN
				q  <="000"&d(4 DOWNTO 0);
				q2 <=d(7 DOWNTO 5);
		
		END IF;
	END PROCESS;
END;
