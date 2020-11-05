package Programmers_Kit_DFS_BFS;
import java.util.*;

public class TravelRoute {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        System.out.println(Arrays.toString(sol.solution(tickets)));
    }
    private static class Solution {
        static List<String> list = new ArrayList<>();
        static String route = "";

        public String[] solution(String[][] tickets) {
            String[] answer = {};
            boolean[] used = new boolean[tickets.length];

            Arrays.fill(used, false);

            for(int i = 0; i < tickets.length; i++) {
                if(tickets[i][0].compareTo("ICN") == 0) {
                    used[i] = true;
                    route += tickets[i][0];
                    route += " ";
                    DFS(1, tickets[i][1], tickets, used);
                    route = route.substring(0, route.length() - 8);
                    used[i] = false;
                }

            }
            Collections.sort(list);
            answer = list.get(0).split(" ");
            return answer;
        }
        public void DFS(int cnt, String start, String[][] tickets, boolean[] used) {
            route += start;
            route += " ";
            if(cnt == tickets.length) list.add(route);

            for(int i = 0; i < tickets.length; i++) {
                if(!used[i] && tickets[i][0].compareTo(start) == 0) {
                    used[i] = true;
                    DFS(cnt+1, tickets[i][1], tickets, used);
                    used[i] = false;
                    route = route.substring(0, route.length() - 4);
                }
            }
            return;
        }
    }
}
