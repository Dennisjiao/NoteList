public class UsingPairs
{
    public static final int NUMBER_OF_LETTERS = 26;
    public static void main(String[] args)
    {
        Pair<Character,Integer>[] pairs = new Pair[NUMBER_OF_LETTERS];
        // the above statement gives compiler warnings
        // Pair<Character,Integer>[] pairs = new
        //        Pair<Character,Integer>[NUMBER_OF_LETTERS]; the above
        // statement produces compilation error: generic array creation
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++)
        {
            pairs[i] = new Pair<Character,Integer>(c,ordinalNumber(c));
            i++;
        }
        for (Pair<Character,Integer> p: pairs)
            System.out.println("Ordinal number of " + p.getFirst() + " is "
                    + p.getSecond());
    }
    private static int ordinalNumber(char ch){ return (int) ch; }
}
