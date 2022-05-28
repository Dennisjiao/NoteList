library ieee;
use ieee.std_logic_1164.all;
entity cfq is
      port(cp,d:in std_logic;
           reset_l:in std_logic;
           q:buffer std_logic;
           nq :out std_logic);
end cfq;
architecture arch of cfq is
begin
    process(cp,reset_l)
    begin
       if(reset_l='0')then q<='0';
        elsif rising_edge(cp) then q<=d;
    end if;
end process;
nq<=NOT q;
end arch;