package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
//Logic
//가장 많이 들 수 있는 크레인과 가장 무거운 박스를 비교해서 안되는 경우에는 그냥 -1
//그 외의 경우에는 가능한 크레인 다 돌아보고 1분 처리

//풀이 시간 : 25분

public class boj_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        ArrayList<Integer> crane = new ArrayList<>();
        ArrayList<Integer> box = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            crane.add(Integer.parseInt(stk.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++) {
            box.add(Integer.parseInt(stk.nextToken()));
        }

        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        if(crane.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }

        int ans = 0;

        while(!box.isEmpty()) {
            int craneIdx = 0, boxIdx = 0;
            while(craneIdx < n && boxIdx < box.size()) {
                if(crane.get(craneIdx) < box.get(boxIdx)) {
                    boxIdx++;
                } else {
                    box.remove(boxIdx);
                    craneIdx++;
                }
            }
            ans++;
        }

        System.out.println(ans);
    }
}
