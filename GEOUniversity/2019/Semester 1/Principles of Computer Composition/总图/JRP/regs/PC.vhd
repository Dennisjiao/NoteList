library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

ENTITY pc IS
	PORT
	(
		reset	: IN		STD_LOGIC;
		load_PC	: IN		STD_LOGIC;
		INC_PC	: IN		STD_LOGIC;
		d			: IN		STD_LOGIC_VECTOR(7 DOWNTO 0);
		q			: BUFFER	STD_LOGIC_VECTOR(7 DOWNTO 0)
	);
END pc;

ARCHITECTURE one OF pc IS

BEGIN
	PROCESS (load_PC,reset)
	BEGIN
		IF reset = '0' THEN
			q <= "00000000";
		ELSIF (load_PC'event and load_PC='1') THEN
				IF INC_PC='1' THEN
				  q<=q+1;
				ELSE
				 q<=d;
				END IF;
		END IF;
	END PROCESS;
END;
