public class UsingPairsOfObjects {
    public static final int NUMBER_OF_LETTERS = 26;
    public static void main(String[] args)
    {
        PairOfObjects[] pairs = new PairOfObjects[NUMBER_OF_LETTERS];
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++)
        {
            pairs[i] = new PairOfObjects(c, ordinalNumber(c));
            i++;
        }
        for (PairOfObjects p : pairs)
            System.out.println("Ordinal number of " + p.getFirst()
                    + " is " + p.getSecond());
    }
    private static int ordinalNumber(char ch) { return (int) ch; }
}
