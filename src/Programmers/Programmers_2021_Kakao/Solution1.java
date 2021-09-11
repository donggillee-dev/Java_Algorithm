package Programmers.Programmers_2021_Kakao;

import java.util.*;

public class Solution1 {
    private static HashMap<String, Integer> makeTable(String[] idList) {
        HashMap<String, Integer> hashTable = new HashMap<>();

        for(String str : idList) {
            hashTable.put(str, hashTable.getOrDefault(str, 0));
        }

        return hashTable;
    }
    private static HashMap<String, HashSet<String>> calcReport(HashMap<String, Integer> hashTable, String[] report) {
        HashMap<String, HashSet<String>> reportHash = new HashMap<>();

        for(String str : report) {
            String[] splitArr = str.split(" ");

            if(reportHash.get(splitArr[0]) == null) {
                reportHash.put(splitArr[0], new HashSet<>());
            }

            reportHash.get(splitArr[0]).add(splitArr[1]);
        }

        for(String key : reportHash.keySet()) {
            for(String target : reportHash.get(key)) {
                hashTable.put(target, hashTable.get(target) + 1);
            }
        }

        return reportHash;
    }
    private static int[] solution(String[] id_list, String[] report, int k) {
        int length = id_list.length;
        int[] answer = new int[length];
        HashMap<String, Integer> hashTable = makeTable(id_list);
        HashMap<String, HashSet<String>> reportHash = calcReport(hashTable, report);

        for (int i = 0; i < length; i++) {
            String name = id_list[i];
            if (reportHash.get(name) == null) continue;
            for (String target : reportHash.get(name)) {
                if (hashTable.get(target) >= k) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2);
    }
}
