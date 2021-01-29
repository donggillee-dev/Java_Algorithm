package Programmers_Lv2;

import java.util.Arrays;

//제약 사항
//1. 코스는 메뉴 두개 이상의 조합으로 이루어져야됨
//2. 코스 조합을 주문시킨 고객이 한명 뿐이면 해당 코스는 만들 수 없음
//3. course 배열에서 원하는 코스길이에 합당한 코스 조합이 없으면 넣지 않는다
import java.util.*;
public class MenuRenewal {
    static int max = Integer.MIN_VALUE;
    static int min = 2;
    static HashMap<String, Integer> courseHash = new HashMap<>();

    private static class Course {
        String str;
        int cnt;

        public Course(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) {
        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};

        System.out.println(Arrays.toString(Solution.solution(orders, course)));
    }
    private static class Solution {
        public static String[] solution(String[] orders, int[] course) {
            String[] answer;

            //원하는 코스 길이를 hash로부터 가져오기는 로직에서 사용된 Contains를 위해 생성한 자료구조
            HashMap<Integer, Integer> countHash = new HashMap<>();
            ArrayList<Course> courseList = new ArrayList<Course>();
            List<String> semiAnswer = new ArrayList<>();

            //course 길이중에 최대값을 가져와서 백트래킹에 활용
            for(int i = 0; i < course.length; i++) {
                countHash.put(course[i], -1);
                if (max < course[i]) {
                    max = course[i];
                }
            }

            //고객별 주문한 메뉴 문자열 정렬 부분
            for(int i = 0; i < orders.length; i++) {
                char[] chArr = orders[i].toCharArray();

                Arrays.sort(chArr);
                orders[i] = new String(chArr);
                DFS(orders[i], new StringBuilder(), 0);
            }

            //조합들 중 원하는 길이의 조합들을 List에 담는 과정
            for(String str : courseHash.keySet()) {
                int cnt = str.length();

                //해당 코스 조합의 선호도가 1이 아닌 경우와 도출해내려는 코스 길이에 적합한 코스 조합인지 체크
                if(courseHash.get(str) != 1 && countHash.keySet().contains(cnt)) {
                    courseList.add(new Course(str, courseHash.get(str)));
                }
            }

            //원하는 코스 길이에 맞는 코스 조합 중에 가장 선호도가 높은 것 부터 뽑아오기 위해 선호도 내림차순 순서대로 정렬함
            Collections.sort(courseList, new Comparator<Course>() {
                @Override
                public int compare(Course o1, Course o2) {
                    return o2.cnt - o1.cnt;
                }
            });

            //정렬된 선호도를 토대로 코스조합에 대한 선호도가 가장 높은 것보다 미만일 경우에는 코스 출시에 포함시키지 않음
            for(Course c : courseList) {
                int key = c.str.length();

                if(countHash.get(key) <=  c.cnt) {
                    countHash.replace(key, c.cnt);
                    semiAnswer.add(c.str);
                }
            }

            //정답은 문자열 순서대로 오름차순이 되어야하니까 semiAnswer를 정렬함
            Collections.sort(semiAnswer, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });

            answer = new String[semiAnswer.size()];
            semiAnswer.toArray(answer);
            return answer;
        }

        //DFS를 통해 문자열 조합을 만들어냄 => 코스 생성기
        public static void DFS(String orders, StringBuilder sb, int startIdx) {
            int length = sb.length();
            if(length <= max && length >= min) {
                if(length >= min) {
                    //HashMap에 sb => 코스 로 만들어진 Key가 존재하는지 확인
                    String str = String.valueOf(sb);
                    if(courseHash.containsKey(str)) {
                        int update = courseHash.get(str) + 1;
                        courseHash.replace(str, update);
                    } else {
                        courseHash.put(str, 1);
                    }
                }
            }

            //백트레킹 조건에 충족되지 않으면 return;
            if(length > max) return;
            for(int i = startIdx; i < orders.length(); i++) {
                sb.append(orders.charAt(i));
                DFS(orders, sb, i + 1);
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }
}
