library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

ENTITY MDR IS
	PORT
	(
		reset	: IN		STD_LOGIC;
		load_MDR	    : IN		STD_LOGIC;
		R_NW    : IN		STD_LOGIC;
		d1		: IN		STD_LOGIC_VECTOR(7 DOWNTO 0);
		d2		: IN		STD_LOGIC_VECTOR(7 DOWNTO 0);
		q		: BUFFER	STD_LOGIC_VECTOR(7 DOWNTO 0)
	);
END MDR;

ARCHITECTURE one OF MDR IS

BEGIN
	PROCESS (load_MDR,reset,R_NW)
	BEGIN
		IF reset = '0' THEN
			q <= "00000000";
		ELSIF (load_MDR'EVENT AND load_MDR='1') THEN
		    IF R_NW = '0' THEN
				q<=d1;
			ELSE
			    q<=d2;
			END IF;
		END IF;
	END PROCESS;
END;