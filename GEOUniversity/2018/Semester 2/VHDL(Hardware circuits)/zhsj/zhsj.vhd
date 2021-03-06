library ieee;
use ieee.std_logic_1164.all;

entity zhsj is
   port( x , clk :in std_logic;
        start:in std_logic;
        z:out std_logic);
end zhsj;

architecture dataflow of zhsj is
    type state_type is(s,s1,s10,s101);
    Signal  state:state_type;
begin
process(start,clk)
begin
 if start='1' then
    state<=s;
elsif clk'event and clk='1' then
  case state is
  when s=>
  if x='1' then
   state<=s1;
  end if;
  
  when s1 =>
  if x='0' then
  state<=s10;
  end if;

  when s10 =>
  if x='0' then
  state<=s;
  else
  state<=s101;
  end if;

  when s101 =>
  if x='0' then
   state<=s;
  else
   state<=s1;
  end if;
end case;
end if;
end process;


process(state,x)
begin
  case state is
  when s101=> 
  if x='1' then
  z<='1';
  else 
  z<='0';
  end if;
 when others =>
 z<='0';
end case;

end process;
end dataflow;