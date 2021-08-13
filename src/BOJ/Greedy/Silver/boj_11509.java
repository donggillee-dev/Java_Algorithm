package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Logic
//h높이의 풍선을 터트리면 화살은 h-1이 된다
//arrow의 배열의 idx를 h로 생각하고 해당위치에 존재하는 화살의 개수 => 즉 현재 높이의 풍선을 터트리기 위한 화살이 존재하나? => 이걸로 해결 가능
//존재하지 않으면 화살 하나 늘림
//존재한다면 터트리고 화살 한칸 낮춤

//풀이 시간 : 50분

public class boj_11509 {
    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), maxHeight = 0, ans = 0;
        int[] arr = new int[n], arrow;
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = stoi(stk.nextToken());
            maxHeight = Math.max(maxHeight, arr[i]);
        }

        arrow = new int[maxHeight + 1];

        for(int i = 0; i < n; i++) {
            if(arrow[arr[i]] == 0) {
                arrow[arr[i] - 1]++;
                ans++;
            } else {
                arrow[arr[i]]--;
                arrow[arr[i] - 1]++;
            }
        }

        System.out.println(ans);
    }
}
