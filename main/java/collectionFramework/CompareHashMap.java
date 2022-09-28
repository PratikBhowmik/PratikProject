package collectionFramework;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CompareHashMap {
    public static void main(String[] args) {
        HashMap<Integer , String > map1 = new HashMap<Integer , String>();
        map1.put(1,"A");
        map1.put(2,"B");
        map1.put(3,"C");

        HashMap<Integer , String> map2 = new HashMap<Integer , String>();
        map2.put(3,"C");
        map2.put(1,"A");
        map2.put(2,"B");

        HashMap<Integer , String> map3 = new HashMap<Integer , String>();
        map3.put(3,"C");
        map3.put(1,"A");
        map3.put(2,"B");
        map3.put(3,"D");

        //1.On the basis of key-value pair use equals method
        System.out.println(map1.equals(map2));//true
        System.out.println(map1.equals(map3));//false

        //2.Compare hashmaps for the same keys : keyset():
        System.out.println(map1.keySet().equals(map2.keySet()));
        System.out.println(map1.keySet().equals(map3.keySet()));

        //3.find out extra keys
        HashMap<Integer , String> map4 = new HashMap<Integer , String>();
        map4.put(1,"A");
        map4.put(2,"B");
        map4.put(3,"C");
        map4.put(4,"D");

        //Combine/Union the keys from both maps : using hashset
        HashSet<Integer> combineKeys = new HashSet<>(map1.keySet());
        combineKeys.addAll(map4.keySet());
        combineKeys.removeAll(map1.keySet());
        System.out.println(combineKeys);

        //compare maps by values
        HashMap<Integer,String> map5 = new HashMap<Integer,String>();
        map5.put(1,"A");
        map5.put(2,"B");
        map5.put(3,"C");

        HashMap<Integer,String> map6 = new HashMap<Integer,String>();
        map6.put(4,"A");
        map6.put(5,"B");
        map6.put(6,"C");

        HashMap<Integer,String> map7 = new HashMap<Integer,String>();
        map7.put(1,"A");
        map7.put(2,"B");
        map7.put(3,"C");
        map7.put(4,"C");

        //duplicates are not allowed - using arraylist
        System.out.println(new ArrayList<>(map5.values()).equals(new ArrayList<>(map6.values())));
        System.out.println(new ArrayList<>(map5.values()).equals(new ArrayList<>(map7.values())));

        //duplicates are allowed - using Hashset
        System.out.println(new HashSet<>(map5.values()).equals(new HashSet<>(map6.values())));
        System.out.println(new HashSet<>(map5.values()).equals(new HashSet<>(map7.values())));

        //Synchronizing hashmap
        Map<Integer,String> syncmap = Collections.synchronizedMap(map7);
        System.out.println(syncmap);

        ConcurrentHashMap<String,String > concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A","Java");
        concurrentMap.put("B","Python");
        concurrentMap.put("C","C++");
        System.out.println(concurrentMap.get("A"));

    }
}
