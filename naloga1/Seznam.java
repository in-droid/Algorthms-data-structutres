package naloga1;

public class Seznam {
    private Elt elt;
    private Seznam rep;
    private int comparisons;
    static int comps = 0;

    Seznam(Elt elt, Seznam rep) {
        this.elt = elt;
        this.rep = rep;
        this.comparisons = 0;

    }


    static Seznam insert(Seznam s, Elt e) {
       Elt found = find(s, e.key);
        if (found == null) {
            return new Seznam(e, s);
        }

        found.data = e.data;
        return s;
    }

    static Elt find(Seznam s, int key) {
        if (s == null) {
            return null;
        }

        s.comparisons++;
        comps++;
        if (s.elt.key == key) {
            return s.elt;
        }
        else {
            return find(s.rep, key);
        }
    }

    static Seznam delete(Seznam s, int key) {
        if (s == null) {
            return null;
        }

        comps++;
        if (s.elt.key == key) {
            return s.rep;
        }
        else {
            return new Seznam(s.elt, delete(s.rep, key));
        }
    }

    static void printElementKeys(Seznam s) {
        if (s == null) {
            return;
        }
        System.out.println(s.elt.key);
        printElementKeys(s.rep);

    }

    static void printElementKeyComparisons(Seznam s) {
        if (s == null) {
            return;
        }
        System.out.println(comps);
    }

     private static int viewComparisons(Seznam s) {
        if (s == null) {
            return 0;
        }
        if (s.rep == null) {
            return s.comparisons;
        }
        return s.comparisons + s.rep.comparisons;

     }


}
