public class TestPairOperation
{
    public static void main(String[] args)
    {
        Integer i = 5;
        Double d = -3.5;
        Byte b = 0x07;
        String s = "test";
        Pair<Character,String> pair =
                new Pair<Character,String>('a',"aa");
        pair.operation(i);
        pair.operation(d);
        pair.operation(b);
        //pair.operation(s); // compile-time error
    }
}
