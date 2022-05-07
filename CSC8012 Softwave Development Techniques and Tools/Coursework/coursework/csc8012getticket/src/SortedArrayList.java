import java.util.ArrayList;
import java.lang.*;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    //Compare insert sort
    public void adda(E en) {
        if (this.size() > 0) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).compareTo(en) > 0) {
                    this.add(i, en);
                    return;
                }
            }
        }
        this.add(en);
    }
}