library ieee;
use ieee.std_logic_1164.all;
entity dsbj is
    port(a,b,c: in std_logic;
            f: out std_logic);
end dsbj;
architecture A of dsbj is
begin
f<=(a and b) or (b and c) or (a and c);
end A;
