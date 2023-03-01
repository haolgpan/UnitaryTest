package ex2;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

import java.util.ArrayList;

/**
 * Implementació d'una taula de hash sense col·lisions.
 * Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
 */
public class HashTable {
    private int SIZE = 16;
    private int ITEMS = 0;
    private HashEntry[] entries = new HashEntry[SIZE];

    public int count() {
        //Correction Code
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            if (entries[i] != null) {
                HashEntry entry = entries[i];
                while (entry != null) {
                    count++;
                    entry = entry.next;
                }
            }
        }
        this.ITEMS = count;
        return this.ITEMS;
    }

    public int size() {
        return this.SIZE;
    }

    /**
     * Permet afegir un nou element a la taula.
     *
     * @param key   La clau de l'element a afegir.
     * @param value El propi element que es vol afegir.
     */
    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if (entries[hash] == null) {
            entries[hash] = hashEntry;
        } else {
            //Original Code

//            HashEntry temp = entries[hash];
//            while(temp.next != null)
//                temp = temp.next;
//
//            temp.next = hashEntry;
//            hashEntry.prev = temp;


            //Correction code
            // The bucket is not empty, so look for the key in the linked list of entries
            HashEntry temp = entries[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    // The key already exists in the hash table, so update the value
                    temp.value = value;
                    return;
                }
                temp = temp.next;
            }
            // The key does not exist in the hash table, so add the new entry to the end of the linked list
            temp = entries[hash];
            while (temp.next != null)
                temp = temp.next;
            temp.next = hashEntry;
            hashEntry.prev = temp;
        }
        /*
        En aquesta versió actualitzada del mètode put(), primer comprovem si el "bucket"
        corresponent al codi hash de la clau està buit. Si està buit, simplement afegim
        la nova entrada al "bucket".
        Si el "bucket" no està buit, busquem la llista enllaçada d'entrades emmagatzemades
        en el "bucket" per a la clau. Si la clau ja existeix a la taula de hash, actualitzem
        el valor associat amb la clau i retornem del mètode.
        Si la clau no existeix a la taula de hash, afegim la nova entrada al final de
        la llista enllaçada d'entrades emmagatzemades en el "bucket".
         */
    }

    /**
     * Permet recuperar un element dins la taula.
     *
     * @param key La clau de l'element a trobar.
     * @return El propi element que es busca (null si no s'ha trobat).
     */
    public String get(String key) {
        int hash = getHash(key);
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];
            //Original Code

//            while( !temp.key.equals(key))
//                temp = temp.next;
//
//            return temp.value;

            //Modified code
            while (temp != null && !temp.key.equals(key))
                temp = temp.next;

            if (temp != null) {
                return temp.value;
            }
            //
//            En aquest codi actualitzat, la condició del bucle
//            while es modifica per comprovar també si "temp" no
//            és nul abans d'accedir al seu camp "key". A més,
//            s'afegeix una comprovació de nul·litat addicional
//            després del bucle per garantir que "temp" no sigui nul
//            abans de retornar el seu camp "value".
        }

        return null;
    }

    /**
     * Permet esborrar un element dins de la taula.
     *
     * @param key La clau de l'element a trobar.
     */
    public void drop(String key) {
        //Original Code

//        int hash = getHash(key);
//        if(entries[hash] != null) {
//
//            HashEntry temp = entries[hash];
//            while( !temp.key.equals(key))
//                temp = temp.next;
//
//            if(temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
//            else{
//                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
//                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
//            }
//        }


        //Correction Code
        int hash = getHash(key);
        if (entries[hash] != null) {

            HashEntry temp = entries[hash];
            while (temp != null && !temp.key.equals(key))
                temp = temp.next;

            if (temp != null) {
                if (temp.prev != null) temp.prev.next = temp.next;   // remove temp from the linked list
                else entries[hash] = temp.next;                     // update entries[hash] if temp is the head node
                if (temp.next != null) temp.next.prev = temp.prev;   // update the previous node of temp's next node
            }
        }
        //
        /*
        En el codi modificat, si l'element a esborrar no es troba
        a la llista enllaçada, el bucle terminarà naturalment.
        Després de trobar l'element, el codi el elimina de la llista
        enllaçada actualitzant els nodes anterior i següent adequadament.
        El node inicial s'actualitza només quan l'element a esborrar és
        ell mateix el node inicial.
         */
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % SIZE;
    }

    private class HashEntry {
        String key;
        String value;

//        Object key;
//        Object value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        public HashEntry(String key, String value) {
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

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if (entry == null) {
                bucket++;
                continue;
            }

            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while (temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     *
     * @param key La clau que es farà servir per calcular col·lisions.
     * @return Una clau que, de fer-se servir, provoca col·lisió amb la que s'ha donat.
     */
    public String getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1).get(0);
    }

    /**
     * Permet calcular quants elements col·lisionen (produeixen la mateixa posició dins la taula de hash) per a la clau donada.
     *
     * @param key      La clau que es farà servir per calcular col·lisions.
     * @param quantity La quantitat de col·lisions a calcular.
     * @return Un llistat de claus que, de fer-se servir, provoquen col·lisió.
     */
    public ArrayList<String> getCollisionsForKey(String key, int quantity) {
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() - 1;

        while (foundKeys.size() < quantity) {
            //building current key
            String currentKey = "";
            for (int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if (!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current) + 1);

            //overflow over the alphabet on current!
            if (newKey.get(current) == alphabet.length) {
                int previous = current;
                do {
                    //increasing the previous to current alphabet key
                    previous--;
                    if (previous >= 0) newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for (int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if (previous < 0) newKey.add(0);

                current = newKey.size() - 1;
            }
        }

        return foundKeys;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
        log("\nValue for key(20) : " + hashTable.get("20"));
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}