package com.neeson.example.util;


import java.util.HashMap;
import java.util.Map;

public class NumberToWuxing {
    private static Map<Integer,String> sMaps;
    static {
        sMaps = new HashMap<>();
        sMaps.put(1,"木");
        sMaps.put(2,"木");
        sMaps.put(3,"火");
        sMaps.put(4,"火");
        sMaps.put(5,"土");
        sMaps.put(6,"土");
        sMaps.put(7,"金");
        sMaps.put(8,"金");
        sMaps.put(9,"水");
        sMaps.put(0,"水");
    }

    public static String getWxByNumber(int number){
        int key = number%10;
        return sMaps.get(key);
    }
}
