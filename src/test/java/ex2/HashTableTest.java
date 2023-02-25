package ex2;

import org.junit.jupiter.api.Assertions;

class HashTableTest {

    @org.junit.jupiter.api.Test
    void count() {
    }


    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void put_no_colision_buit() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void put_no_colision_nobuit() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("Test1","Tost1");
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost]\n" +
                " bucket[15] = [Test1, Tost1]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }

    @org.junit.jupiter.api.Test
    void put_con_colision_1() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("2","Tost 2");
        //2, 02, 13

        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost] -> [2, Tost 2]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void put_con_colision_2() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("2","Tost 2");
        ht.put("02","Tost 3");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost] -> [2, Tost 2] -> [02, Tost 3]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void update_no_colision_buit() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("Test","Tust");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tust]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void update_colision_nobuit1() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("02","Tast");
        ht.put("Test","Tust");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tust] -> [02, Tast]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void update_colision_nobuit2() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("2","Tost 2");
        ht.put("2","Tust");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost] -> [2, Tust]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }
    @org.junit.jupiter.api.Test
    void update_no_colision_nobuit3() {
        HashTable ht = new HashTable();
        ht.put("Test","Tost");
        ht.put("2","Tost 2");
        ht.put("02","Tost 3");
        ht.put("02","Tost 3.1");
        //2, 02, 13
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tost] -> [2, Tost 2] -> [02, Tost 3.1]",ht.toString());
        Assertions.assertEquals(16,ht.size());
    }

    @org.junit.jupiter.api.Test
    void get_no_collision_buit() {
        HashTable ht = new HashTable();
        //2, 02, 13
        ht.put("Test","Tost");
        Assertions.assertEquals("Tost",ht.get("Test"));
    }
    @org.junit.jupiter.api.Test
    void get_si_collision_nobuit1() {
        HashTable ht = new HashTable();
        //2, 02, 13
        ht.put("Test","Tost");
        ht.put("2","Tost and Tast");
        Assertions.assertEquals("Tost",ht.get("Test"));
    }
    @org.junit.jupiter.api.Test
    void get_si_collision_nobuit2() {
        HashTable ht = new HashTable();
        //2, 02, 13
        ht.put("Test","Tost");
        ht.put("2","Tost and Tast");
        Assertions.assertEquals("Tost and Tast",ht.get("2"));
    }
    @org.junit.jupiter.api.Test
    void get_si_collision_nobuit3() {
        HashTable ht = new HashTable();
        //2, 02, 13
        ht.put("Test","Tost");
        ht.put("2","Tost and Tast");
        ht.put("02","Tasting");
        Assertions.assertEquals("Tasting",ht.get("02"));
    }
    @org.junit.jupiter.api.Test
    void get_empty() {
        HashTable ht = new HashTable();
        //2, 02, 13
        ht.get("tast");
        Assertions.assertEquals(null,ht.get("tast"));
    }
    @org.junit.jupiter.api.Test
    void get_no_exists_noColision() {
        HashTable ht = new HashTable();
        ht.put("Test","Tist");
        //2, 02, 13
        ht.get("tast");
        Assertions.assertEquals(null,ht.get("tast"));
    }
    @org.junit.jupiter.api.Test
    void get_no_exists_Colision123() {
        HashTable ht = new HashTable();
        ht.put("Test","Tist");
        ht.put("2","Tast");
        ht.put("02","Tust");
        //2, 02, 13
        Assertions.assertEquals(null,ht.get("13"));
    }
    @org.junit.jupiter.api.Test
    void drop_buit_noColision() {
        HashTable ht = new HashTable();
        ht.put("Test","Tisting");
        ht.drop("Test");
        Assertions.assertEquals("",ht.toString());
    }
    @org.junit.jupiter.api.Test
    void drop_nobuit_Colision1() {
        HashTable ht = new HashTable();
        ht.put("Test","Tisting");
        ht.put("2","Tisting tusting");
        ht.drop("Test");
        Assertions.assertEquals("\n" +
                " bucket[2] = [2, Tisting tusting]",ht.toString());
    }
    @org.junit.jupiter.api.Test
    void drop_nobuit_Colision2() {
        HashTable ht = new HashTable();
        ht.put("Test","Tisting");
        ht.put("2","Tisting tusting");
        ht.put("02","Tosting");
        ht.drop("2");
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tisting] -> [02, Tosting]",ht.toString());
    }
    @org.junit.jupiter.api.Test
    void drop_nobuit_Colision3() {
        HashTable ht = new HashTable();
        ht.put("Test","Tisting");
        ht.put("2","Tisting tusting");
        ht.put("02","Tosting");
        ht.drop("02");
        Assertions.assertEquals("\n" +
                " bucket[2] = [Test, Tisting] -> [2, Tisting tusting]",ht.toString());
    }
    @org.junit.jupiter.api.Test
    void drop_noelement() {
        //2, 02, 13
        HashTable ht = new HashTable();
        ht.drop("patat");
        Assertions.assertEquals("",ht.toString());
    }

}