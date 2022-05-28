library ieee;
use ieee.std_logic_1164.all;
entity adder is
     port(a,b,c:in std_logic;
          ci,si:out std_logic);
end adder;

architecture be_adder of adder is
begin
process(a,b,c)
    variable x:std_logic_vector(2 downto 0);
    begin
    x:=a&b&c;
    case x is
        when "000" => si<='0';ci<='0';
        when "001" => si<='1';ci<='0';
        when "010" => si<='1';ci<='0';
        when "011" => si<='0';ci<='1';
        when "100" => si<='1';ci<='0';
        when "101" => si<='0';ci<='1';
        when "110" => si<='0';ci<='1';
        when "111" => si<='1';ci<='1';
    end case;
    end process;
end be_adder;