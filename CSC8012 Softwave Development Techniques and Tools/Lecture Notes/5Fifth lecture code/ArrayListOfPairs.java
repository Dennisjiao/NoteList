    import java.util.*;
    public class ArrayListOfPairs {
        public static void main(String[] args)
        {
            ArrayList<Pair<Character, Integer>> pairs = new ArrayList<Pair<Character, Integer>>();
            for (char c = 'a'; c <= 'z'; c++) {
                Pair<Character, Integer> pair = new Pair<Character, Integer>(c, ordinalNumber(c));
                pairs.add(pair);
            }
            for (Pair<Character, Integer> p : pairs)
                System.out.println("Ordinal number of " + p.getFirst() + " is " + p.getSecond());
        }
    private static int ordinalNumber(char ch) { return (int) ch; }
}
