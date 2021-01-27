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
        //문자열을 잘라서 담을 문자열 배열
        //공간 복잡도와 시간 복잡도를 단순화하기 위해 StringBuilder객체 배열을 선언함
        private static StringBuilder[] arr;

        //HashMap 사용해서 문자열 그룹화 & 검색 , 수정 속도 향상 => O(1)
        private static HashMap<String, ArrayList<Integer>> hash = new HashMap<>();
        public static int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            //info 문자열 Tokenizing 과정
            for(String str : info) {
                String[] strArr = str.split(" ");
                arr = new StringBuilder[strArr.length];

                for(int i = 0; i < strArr.length; i++) {
                    arr[i] = new StringBuilder();
                    arr[i].append(strArr[i]);
                }
                //하나의 info가 알맞게 토큰화 되었으면 해당 info를 가지고 만들 수 있는 그룹들을 만들어준다
                //내 생각엔 이 방법보다 더 좋은 그룹화 방법이 존재할 것 같다... DFS이기에 재귀 + N^2의 시간복잡도를 가지기에 통과는 하더라도 시간이 좀 오래 걸리긴 한다...
                DFS(0);
            }

            //HashMap에 담긴 각 그룹들의 점수 정보들 오름차순으로 정렬 => 이분탐색을 위함
            for(String str : hash.keySet()) {
                Collections.sort(hash.get(str));
            }

            //query 문자열들 가져와서 hash에 key로 들어간 문자열 규격처럼 맞춤
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

                //Hash에서 해당 Value를 가져오는건 hash함수를 통해 바로 가져오기 때문에 O(1)의 시간복잡도를 가진다
                ArrayList<Integer> valueList = hash.get(String.valueOf(sb));

                //내가 간과했던 이슈!!
                //query를 날리더라도 해당 쿼리에 대해 아에 그룹이 생성조차 안되어 있을수도 있음 => null을 리턴할수도 있기때문에 null Check 필수!!!
                if(valueList == null) {
                    answer[idx] = 0;
                } else {
                    //ArrayList가 존재하면 내가 검색하려는 점수가 몇번째인지 확인 => 배열의 길이 - idx = 찾는 사람들 인원 수
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

        //DFS를 통해서 한 문자열에 대해 16개의 그룹을 찾아낸다
        //java backend junior pizza 100 => - backend junior pizza 100 , java - junior pizza 100 , java backend - pizza 100 , ... , - - - - 100
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

            //재귀를 돌면서 모든 경우의 수를 탐색해줌
            for(int i = idx; i < arr.length - 1; i++) {
                String tmp = String.valueOf(arr[i]);
                arr[i].delete(0, arr[i].length());
                arr[i].append("-");
                DFS(i + 1);
                arr[i].delete(0, arr[i].length());
                arr[i].append(tmp);
            }
        }

        //점수 배열에 대해서 이분탐색하는 부분
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