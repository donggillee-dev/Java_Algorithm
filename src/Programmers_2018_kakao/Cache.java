package Programmers_2018_kakao;

import java.io.*;
import java.util.*;

public class Cache {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


//        String[] arr = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] arr = {"Jeju", "Pangyo", "NewYork", "newyork"};
//        String[] arr = {"Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Jeju", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "NewYork", "newyork"};
        int res = solution(0, arr);

        sb.append(res).append("\n");
        bw.write(String.valueOf(sb));

        bw.flush();
        bw.close();
        br.close();
        return;
    }

    static int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return cities.length * 5;

        int answer = 0;
        Map<String, Integer> CacheMap = new HashMap<>();
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            if(CacheMap.containsKey(city)) {
                answer += 1;
                CacheMap.put(city, i);
            } else {
                answer += 5;
                if(CacheMap.size() < cacheSize) {
                    CacheMap.put(city, i);
                } else {
                    Map<String, Integer> result = sortMapByValue(CacheMap);
                    CacheMap = result;
                    for( String s : CacheMap.keySet()) {
                        CacheMap.remove(s);
                        break;
                    }
                    CacheMap.put(city, i);
                }
            }
        }
        return answer;
    }
    public static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
        Collections.sort(entries, (o2, o1) -> o2.getValue().compareTo(o1.getValue()));

        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}

