class List {
    int data;
    List next;

    void add_to_1st_pos(int n) {
        List p;
        p = this.next;
        this.next = new List();
        this.next.data = n;
        this.next.next = p;
    }

    void print() {
        List p;
        for (p = this.next; p != null; p = p.next) {
            System.out.print(p.data);
            System.out.print(" ");
        }
        System.out.println("");
    }

    List search(int n) {
        List p;
        for (p = this.next; p != null; p = p.next) {
            if (p.data == n) {
                return p;
            }
        }
        return null;
    }

    void delete(List dlt) {
        List p;
        for (p = this; p != null; p = p.next)
        {
            if (p.next == dlt)
            {
                p.next = p.next.next;
                break;
            }
        }
    }

    public static void main(String[] args) {
        int key;
        List find;

        List root = new List();
        root.add_to_1st_pos(2);
        root.add_to_1st_pos(3);
        root.add_to_1st_pos(5);
        root.add_to_1st_pos(7);
        root.add_to_1st_pos(11);
        root.add_to_1st_pos(13);
        root.add_to_1st_pos(17);
        root.add_to_1st_pos(19);

        root.print();

        key=13;
        find = root.search(key);
        root.delete(find);

        root.print();
    }
}
