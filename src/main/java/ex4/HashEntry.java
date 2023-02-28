package ex4;

class HashEntry {
    //    String key;
//    String value;
    Object key;
    Object value;

    // Linked list of same hash entries.
    HashEntry next;
    HashEntry prev;

    //    public HashEntry(String key, String value) {
//        this.key = key;
//        this.value = value;
//        this.next = null;
//        this.prev = null;
//    }
    //New code for Object storage
    public HashEntry(String key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "[" + key + ", " + value + "]";
    }
}
