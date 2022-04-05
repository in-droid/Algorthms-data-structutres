package naloga1;

import naloga1.Elt;

public class Main {

    public static void main(String[] args) {

        Seznam s = null;
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.insert(s,new Elt(3));
        s = Seznam.insert(s,new Elt(4));
        s = Seznam.insert(s,new Elt(2));
        s = Seznam.insert(s,new Elt(5));
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);
    }
}
