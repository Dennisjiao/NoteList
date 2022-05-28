LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_arith.ALL;
ENTITY jsq IS
   PORT(clk,clr_l,ld_l,enp,ent:in std_logic;
        d:in unsigned(2 downto 0);
        q:out unsigned(2 downto 0);
        rco:out std_logic);
END jsq;
architecture jsq161 of jsq is
signal iq:unsigned(2 downto 0);
begin
process(clk,ent,enp,clr_l,ld_l,iq)
 begin
  if clr_l = '0' then
 iq<=(others=>'0');
elsif clk'event and clk='0' then
if ld_l ='0' then iq<=d;
elsif(ent and enp)='1' then iq<=iq+1;
end if;
end if;
if(iq=7)and(ent='1')then rco<='1';
else rco<='0';
 end if;
q<=iq;
end process;
end jsq161;