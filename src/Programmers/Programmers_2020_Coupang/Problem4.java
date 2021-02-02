package Programmers.Programmers_2020_Coupang;

import java.util.*;

public class Problem4 {
    static HashMap<String, ArrayList<String>> Map = new HashMap<String, ArrayList<String>>();
    static List<String> Visited = new ArrayList<String>();
    static int answer = 0;
    public static void main(String[] args) {
        String depar = "SEOUL";
        String hub = "DAEGU";
        String dest = "YEOSU";
        String[][] roads = {{"SEOUL","DAEJEON"},{"ULSAN","BUSAN"},{"DAEJEON","ULSAN"},{"DAEJEON","GWANGJU"},{"SEOUL","ULSAN"},{"DAEJEON","BUSAN"},{"GWANGJU","BUSAN"}};


        solution(depar, hub, dest, roads);
        System.out.println(answer);
    }
    public static int solution(String depar, String hub, String dest, String[][] roads) {
        for(int i = 0; i < roads.length; i++) {
            ArrayList<String> list;
            if(Map.containsKey(roads[i][0])) {
                list = Map.get(roads[i][0]);
                list.add(roads[i][1]);
            } else {
                list = new ArrayList<>();
                list.add(roads[i][1]);
                Map.put(roads[i][0], list);
            }
        }
        Visited.add(depar);
        DFS(depar, hub, dest);

        return answer % 1007;
    }
    public static void DFS(String start, String hub, String goal) {
        if(start.equals(goal)) {
            if( Visited.contains(hub))
                answer += 1;
            else
                return;
        }
        else {
            ArrayList<String> list = Map.get(start);
            if(list == null) return;
            for(String point : list) {
                if(!Visited.contains(point)) {
                    Visited.add(point);
                    DFS(point, hub, goal);
                    Visited.remove(point);
                }
            }
        }
        return;
    }
}
