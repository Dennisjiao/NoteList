library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

ENTITY reg IS
	PORT
	(
		reset	: IN		STD_LOGIC;
		cp	: IN		STD_LOGIC;
		d			: IN		STD_LOGIC_VECTOR(7 DOWNTO 0);
		q			: BUFFER	STD_LOGIC_VECTOR(7 DOWNTO 0)
	);
END reg;

ARCHITECTURE one OF reg IS

BEGIN
	PROCESS (cp,reset)
	BEGIN
		IF reset = '0' THEN
			q <= "00000000";
		ELSIF Rising_edge(cp) THEN  ---falling_edge
				q<=d;
		END IF;
	END PROCESS;
END;
