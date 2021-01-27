package Programmers_Lv2;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class RankSearch {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(Solution.solution(info, query)));
    }
    private static class Solution {
        private static StringBuilder[] arr;
        private static HashMap<String, ArrayList<Integer>> hash = new HashMap<>();
        public static int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            for(String str : info) {
                String[] strArr = str.split(" ");
                arr = new StringBuilder[strArr.length];

                for(int i = 0; i < strArr.length; i++) {
                    arr[i] = new StringBuilder();
                    arr[i].append(strArr[i]);
                }
                DFS(0);
            }

            for(String str : hash.keySet()) {
                Collections.sort(hash.get(str));
            }

            StringBuilder sb = new StringBuilder();
            int idx = 0;
            for(String str : query) {
                String[] arr1 = str.split(" and ");
                String[] arr2 = arr1[arr1.length - 1].split(" ");
                int score = Integer.parseInt(arr2[arr2.length - 1]);

                for(int i = 0; i < arr1.length - 1; i++) {
                    sb.append(arr1[i]).append(" and ");
                }
                sb.append(arr2[0]);
                ArrayList<Integer> valueList = hash.get(String.valueOf(sb));

                if(valueList == null) {
                    answer[idx] = 0;
                } else {
                    int pos = BinarySearch(valueList, score);

                    if(pos >= 0 && pos <= (valueList.size() - 1)) {
                        answer[idx] = valueList.size() - pos;
                    } else {
                        answer[idx] = 0;
                    }
                }

                sb.delete(0, sb.length());

                idx++;
            }
            return answer;
        }

        private static void DFS(int idx) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < arr.length - 1; i++) {
                sb.append(arr[i]);

                if(i == arr.length - 2) continue;

                sb.append(" and ");
            }
            ArrayList<Integer> list = hash.get(String.valueOf(sb));
            if(list == null) {
                list = new ArrayList<Integer>();
                list.add(Integer.parseInt(String.valueOf(arr[arr.length - 1])));
                hash.put(String.valueOf(sb), list);
            } else {
                list.add(Integer.parseInt(String.valueOf(arr[arr.length - 1])));
            }

            for(int i = idx; i < arr.length - 1; i++) {
                String tmp = String.valueOf(arr[i]);
                arr[i].delete(0, arr[i].length());
                arr[i].append("-");
                DFS(i + 1);
                arr[i].delete(0, arr[i].length());
                arr[i].append(tmp);
            }
        }

        private static int BinarySearch(ArrayList<Integer> list, int score) {
            int start = 0;
            int end = list.size() - 1;
            int mid = 0;

            while(end >= start) {
                mid = (start + end) / 2;
                if(list.get(mid) < score) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            return start;
        }
    }
}