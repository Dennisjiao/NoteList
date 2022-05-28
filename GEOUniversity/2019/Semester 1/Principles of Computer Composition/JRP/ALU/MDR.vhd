LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_unsigned.all;
ENTITY MDR IS

	PORT
	(
		RESET,LOAD_MDR,R_NW			: IN	STD_LOGIC;
		D1,D2		: IN	STD_LOGIC_VECTOR(7 DOWNTO 0);
		P		: BUFFER	STD_LOGIC_VECTOR(7 DOWNTO 0)
	);
	
END MDR;

ARCHITECTURE ARCH1 OF MDR IS
BEGIN

	PROCESS (RESET,LOAD_MDR)
	BEGIN
	
		IF RESET = '0' THEN
		
			P <= "00000000";
			
		ELSIF RISING_EDGE (LOAD_MDR) THEN
			
				IF R_NW = '0' THEN
		
				P <= D1;
		
				ELSE P <= D2;

			END IF;
		
		END IF;				
	
	END PROCESS;
END ARCH1;