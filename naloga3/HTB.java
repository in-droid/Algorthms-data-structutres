package naloga3;

public class HTB {
    private int p;
    private int c1;
    private int c2;
    private int collisions;
    private int[] table;

    public HTB(int p, int m, int c1, int c2) {
        this.p = p;
        this.table = new int[m];
        this.c1 = c1;
        this.c2 = c2;
        this.collisions = 0;
    }

    public void insert(int key) {
        int hash = (key * p) % table.length;
        if (table[hash] == 0) {
            table[hash] = key;
        }
        else if (table[hash] != key) {
            for (int i = 1; i <= table.length; i++) {
                collisions++;
                int collisionHash = (hash + c1 * i + c2 * i * i) % table.length;
                if (table[collisionHash] == key) {
                    return;
                }
                if (table[collisionHash] == 0) {
                    table[collisionHash] = key;
                    return;
                }

            }
            resize();
            insert(key);
        }
    }

    private void resize() {
        int newM = 2 * table.length + 1;
        int[] temp = new int[newM];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0) {
                int hash = (table[i] * p) % newM;
                if (temp[hash] == 0) {
                    temp[hash] = table[i];
                }
                else {
                    for (int j = 1; j < table.length; j++) {
                        collisions++;
                        int collisionHash = (hash + c1 * j + c2 * j * j) % newM;
                        if (temp[collisionHash] == 0) {
                            temp[collisionHash] = table[i];
                            break;
                        }

                    }
                }
            }
        }
        table = temp;
    }

    public boolean find(int key) {
        int hash = (key * p) % table.length;
        if (table[hash] == key) {
            return true;
        }
        for (int i = 1; i <table.length; i++) {
            int newHash = (hash + c1 * i + c2 * i * i) % table.length;
            if (table[newHash] == key) {
                return true;
            }
        }
        return false;
    }

    public void delete(int key) {
        int hash = (key * p) % table.length;
        if (table[hash] == key) {
            table[hash] = 0;
            return;
        }
        for (int i = 1; i <table.length; i++) {
            int newHash = (hash + c1 * i + c2 + i * i) % table.length;
            if (table[newHash] == key) {
                table[hash] = 0;
                break;
            }
        }
    }

    public void printKeys() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0) {
                System.out.println(i + ": " + table[i]);
            }
        }

    }

    public void printCollisions() {
        System.out.println(collisions);

    }

    public static void main(String[] args) {
      //
    }
}
