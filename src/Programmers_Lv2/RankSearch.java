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
        public static int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];
            LinkedList<Data> infoList = new LinkedList<>();
            LinkedList<QueryData> queryList = new LinkedList<>();


            // infoList 생성
            for(String str : info) {
                StringTokenizer stk = new StringTokenizer(str);
                String lang = stk.nextToken();
                String part = stk.nextToken();
                String career = stk.nextToken();
                String food = stk.nextToken();
                String rem = stk.nextToken();

                int score = 0;

                if(rem.equals("-")) {
                    score = -1;
                } else {
                    score = Integer.parseInt(rem);
                }

                infoList.add(new Data(lang, part, career, food, score));
            }
            Collections.sort(infoList, new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    return o2.score - o1.score;
                }
            });

            // queryList 생성
            int qIdx = 0;
            for(String str : query) {
                String[] strArr = str.split(" and ");
                String lang = strArr[0];
                String part = strArr[1];
                String career = strArr[2];
                String food = strArr[3].split(" ")[0];
                String rem = strArr[3].split(" ")[1];

                int score = 0;

                if(rem.equals("-")) {
                    score = -1;
                } else {
                    score = Integer.parseInt(rem);
                }

                queryList.add(new QueryData(lang, part, career, food, score, qIdx));
                qIdx++;
            }

            Collections.sort(queryList, new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    return o2.score - o1.score;
                }
            });


            return answer;
        }
    }
    private static class Data {
        String lang;
        String part;
        String career;
        String food;
        int score;

        public Data(String lang, String part, String career, String food, int score) {
            this.lang = lang;
            this.part = part;
            this.career = career;
            this.food = food;
            this.score = score;
        }
    }

    private static class QueryData extends Data {
        int idx;

        public QueryData(String lang, String part, String career, String food, int score, int idx) {
            super(lang, part, career, food, score);
            this.idx = idx;
        }
    }
}