package Programmers_Lv2;
import java.util.*;

//알고리즘 적인 문제라기 보다는 단순 탐색 구현 문제
    //탐색인데 왜 알고리즘이 아니야? => 문자열이기에 HashMap을 생각해봤지만 단순히 하나의 키로 모든 문자열을 탐색할 수는 없음
    //왜냐하면 문자열에서 타고타고 들어가야하는 조건들이 있기때문
//그래도 연산 횟수를 어느정도 줄일 수 있는 방법은? => 주어지는 문자열을 정렬하여 탐색이 훨씬 빠르게!!
//=> 이 방법은 정말 최악 ? => 결과값을 출력할떄 주어진 쿼리 순서대로 출력해줘야 하기 때문...
//주어진 info에 대한 class를 만들어서 LinkedList를 만들자!!

public class RankSearch {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(Arrays.toString(Solution.solution(info, query)));
    }
    private static class Solution {
        private static StringBuilder[] arr;
        private static boolean[] visited;
        private static HashMap<String, ArrayList<Integer>> hash = new HashMap<>();
        public static int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            for(String str : info) {
                String[] strArr = str.split(" ");
                visited = new boolean[strArr.length];
                arr = new StringBuilder[strArr.length];

                for(int i = 0; i < strArr.length; i++) {
                    arr[i] = new StringBuilder();
                    arr[i].append(strArr[i]);
                }
                DFS(0, 0);
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

        private static void DFS(int depth, int idx) {
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
                if(!visited[i]) {
                    String tmp = String.valueOf(arr[i]);
                    arr[i].delete(0, arr[i].length());
                    arr[i].append("-");
                    visited[i] = true;
                    DFS(depth + 1, i + 1);
                    arr[i].delete(0, arr[i].length());
                    arr[i].append(tmp);
                    visited[i] = false;
                }
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