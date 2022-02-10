package collectionFramework;
import java.util.HashMap;
import java.util.Iterator;

public class MapHash {
    public static void main(String[] args) {

        //no order - no indexing
        //stores values - key value
        //key can't be duplicate
        //can store n number of null values but only one null key
        //hashmap is not synchronized

        HashMap <String , String> capitalMap = new HashMap<String , String>();

        capitalMap.put("India" , "New Delhi");
        capitalMap.put("USA" , "Washington DC");
        capitalMap.put("UK" , "London");
        capitalMap.put(null , "NYC");
        capitalMap.put(null , "Africa");
        capitalMap.put("India" , null);
        System.out.println(capitalMap.get("USA"));
        System.out.println(capitalMap.get("Germany"));
        System.out.println(capitalMap.get(null));
        System.out.println(capitalMap.get("France"));


        //iteration
        Iterator<String> it = capitalMap.keySet().iterator();
        while (it.hasNext()){
            String key = it.next();
            String value = capitalMap.get(key);
            System.out.println("key "+key+" value "+value);
        }
    }
}
