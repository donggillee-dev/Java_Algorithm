package BOJ.BinarySearch.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
// 시간 문자열을 level 별로 저장 -> 이때 쿼리에서 특정 level 이상을 원하니까
// 해당 level 이하의 배열에 모두 저장해준다
// 쿼리문의 lowerbound, upperbound를 이용해서 일치하는 로그들 매핑해줌

//다시 풀어야함

public class boj_21774 {
    private static int getLowerBound(ArrayList<String> list, String startTime) {
        int low = 0, high = list.size();

        while(low < high) {
            int mid = (low + high) / 2;
            String midTime = list.get(mid);

            if(startTime.compareTo(midTime) <= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    private static int getUpperBound(ArrayList<String> list, String endTime) {
        int low = 0, high = list.size();

        while(low < high) {
            int mid = (low + high) / 2;
            String midTime = list.get(mid);

            if(endTime.compareTo(midTime) < 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return high;
    }

    private static String solution(ArrayList<String>[] logList, Queue<String> queryQ) {
        StringBuilder sb = new StringBuilder();

        while(!queryQ.isEmpty()) {
            String[] query = queryQ.poll().split("#");
            String startTime = query[0];
            String endTime = query[1];
            int level = Integer.parseInt(query[2]);

            int diff = getUpperBound(logList[level], endTime) - getLowerBound(logList[level], startTime);

            sb.append(diff).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String>[] logList = new ArrayList[7];
        Queue<String> queryQ = new LinkedList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken()), q = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= 6; i++) {
            logList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("#");
            int lev = Integer.parseInt(str[1]);

            for (int curLev = 1; curLev <= lev; curLev++) {
                logList[curLev].add(str[0]);
            }
        }

        for (int i = 1; i <= 6; i++) {
            Collections.sort(logList[i]);
        }

        for (int i = 0; i < q; i++) {
            queryQ.add(br.readLine());
        }

        System.out.print(solution(logList, queryQ));
    }
}
