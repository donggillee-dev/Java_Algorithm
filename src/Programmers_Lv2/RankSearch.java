//package Programmers_Lv2;
//import java.util.*;
//
////알고리즘 적인 문제라기 보다는 단순 탐색 구현 문제
//    //탐색인데 왜 알고리즘이 아니야? => 문자열이기에 HashMap을 생각해봤지만 단순히 하나의 키로 모든 문자열을 탐색할 수는 없음
//    //왜냐하면 문자열에서 타고타고 들어가야하는 조건들이 있기때문
////그래도 연산 횟수를 어느정도 줄일 수 있는 방법은? => 주어지는 문자열을 정렬하여 탐색이 훨씬 빠르게!!
////=> 이 방법은 정말 최악 ? => 결과값을 출력할떄 주어진 쿼리 순서대로 출력해줘야 하기 때문...
////주어진 info에 대한 class를 만들어서 LinkedList를 만들자!!
//
//public class RankSearch {
//    public static void main(String[] args) {
//        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
//        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
//        System.out.println(Arrays.toString(Solution.solution(info, query)));
//    }
//    private static class Solution {
//        public static int[] solution(String[] info, String[] query) {
//            int[] answer;
//            LinkedList<Data> infoList = new LinkedList<>();
//            LinkedList<Data> queryList = new LinkedList<>();
//            HashMap<String, String> hm = new HashMap<>();
//
//            for(int i = 0; i < query.length; i++) {
//                hm.put(query[i], String.valueOf(i));
//            }
//
//            Arrays.sort(info, Collections.reverseOrder());
//            Arrays.sort(query, Collections.reverseOrder());
//
//
//            // infoList 생성
//            for(String str : info) {
//                StringTokenizer stk = new StringTokenizer(str);
//                String lang = stk.nextToken();
//                String part = stk.nextToken();
//                String career = stk.nextToken();
//                String food = stk.nextToken();
//                String rem = stk.nextToken();
//
//                int score = 0;
//
//                if(rem.equals("-")) {
//                    score = -1;
//                } else {
//                    score = Integer.parseInt(rem);
//                }
//
//                infoList.add(new Data(lang, part, career, food, score));
//            }
//
//            // queryList 생성
//
//            for(String str : query) {
//                String[] strArr = str.split(" and ");
//                String lang = strArr[0];
//                String part = strArr[1];
//                String career = strArr[2];
//                String food = strArr[3].split(" ")[0];
//                String rem = strArr[3].split(" ")[1];
//
//                int score = 0;
//
//                if(rem.equals("-")) {
//                    score = -1;
//                } else {
//                    score = Integer.parseInt(rem);
//                }
//
//                queryList.add(new Data(lang, part, career, food, score));
//            }
//
//            answer = new int[queryList.size()];
//            Collections.sort(infoList, new Comparator<Data>() {
//                @Override
//                public int compare(Data o1, Data o2) {
//                    if(o1.lang.equals(o2.lang)) {
//
//                    }
//                }
//            });
//            StringBuilder sb = new StringBuilder();
//            for(Data qData : queryList) {
//                int maxCnt = 5;
//                int anyCnt = 0;
//
//                sb.append(qData.lang).append(" and ").append(qData.part).append(" and ")
//                        .append(qData.career).append(" and ").append(qData.food).append(" ").append(qData.score);
//                int oriIdx = Integer.parseInt(hm.get(String.valueOf(sb)));
//
//                if(qData.lang.equals("-")) {
//                    anyCnt++;
//                }
//
//                if(qData.part.equals("-")) {
//                    anyCnt++;
//                }
//
//                if(qData.career.equals("-")) {
//                    anyCnt++;
//                }
//
//                if(qData.food.equals("-")) {
//                    anyCnt++;
//                }
//
//                if(qData.score == -1) {
//                    anyCnt++;
//                }
//
//                for(Data iData : infoList) {
//                    int eqCnt = 0;
//
//                    if(iData.lang.equals(qData.lang)) {
//                        eqCnt++;
//                    }
//
//                    if(iData.part.equals(qData.part)) {
//                        eqCnt++;
//                    }
//
//                    if(iData.career.equals(qData.career)) {
//                        eqCnt++;
//                    }
//
//                    if(iData.food.equals(qData.food)) {
//                        eqCnt++;
//                    }
//                    if((eqCnt + anyCnt) == (maxCnt - 1)) {
//
//                    }
//                    if(qData.score != -1 && iData.score >= qData.score) {
//                        eqCnt++;
//                    }
//
//                    if( maxCnt == (eqCnt + anyCnt) ) {
//                        answer[oriIdx]++;
//                    }
//                }
//                sb.delete(0, sb.length());
//            }
//            return answer;
//        }
//    }
//    private static class Data {
//        String lang;
//        String part;
//        String career;
//        String food;
//        int score;
//
//        public Data(String lang, String part, String career, String food, int score) {
//            this.lang = lang;
//            this.part = part;
//            this.career = career;
//            this.food = food;
//            this.score = score;
//        }
//    }
//}