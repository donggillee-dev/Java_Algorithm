package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Logic

//mod를 이용하여 타겟 목표를 제대로 인덱싱 해주는 문제이다
//이 문제의 주요 핵심은
//1. ArrayList vs LinkedList
//  1-1. ArrayList가 remove를 하는데 O(N)의 시간 복잡도를 가지고 LinkedList가 O(1)을 가지기에
//      더 빨라보일 수 있지만 그렇지 않음...
//      LinkedList가 remove를 하는데 O(1)의 시간복잡도를 가지는 경우는 맨 앞과 맨 끝인 경우
//      결과적으로 동일하지만 get하는데 더 빠른 시간복잡도를 가지는 ArrayList를 선택하는게 맞다

//2. Mod를 이요한 음수, 양수 처리
//  2-1. 현재 위치의 풍선을 터트리면 그 위치의 오른쪽에 있는 풍선들이 당겨짐.
//      즉 오른쪽으로 이동하게 될 경우 하나씩 당겨진 상태니까 1 빼줘야함
//  2-2. 왼쪽으로 이동하는 경우에는 빼줄 필요없이 list의 사이즈로 moveIdx를 mod해준 값을 현재 idx에 더해주면 된다

//3. 처리하고 난 값이 제대로 된 값인지 한번 더 검증
//  3-1. 2에서 처리하고 난 현재 idx의 값이 리스트의 사이즈보다 큰 경우에는 다시한번 mod해줌
//  3-2. 0보다 작은 음수의 경우에는 리스트의 맨 끝에서 해당 값(음수)를 더해

public class algo_2346 {
    private static class BallonInfo {
        int ballonIdx;
        int moveIdx;
        public BallonInfo(int ballonIdx, int moveIdx) {
            this.ballonIdx = ballonIdx;
            this.moveIdx = moveIdx;
        }
    }
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int cur_idx = 0;
        ArrayList<BallonInfo> list = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            list.add(new BallonInfo(i, Integer.parseInt(stk.nextToken())));
        }

        while(true) {
            BallonInfo ballon = list.get(cur_idx);
            int ballonIdx = ballon.ballonIdx;
            int moveIdx = ballon.moveIdx;

            sb.append(ballonIdx).append(" ");
            list.remove(cur_idx);
            int size = list.size();

            if(size == 0) break;

            //움직임이 왼쪽이면
            if(moveIdx < 0) {
                //타겟 idx를 움직여야하는 것에서 리스트의 사이즈를 mod해줌
                cur_idx += (moveIdx % size);
            } else {
                //오른쪽인 경우에는 현재 하나 터트려서 idx가 오른쪽으로 밀려있으니까 하나 빼주고 mod해줌
                cur_idx += (moveIdx % size) - 1;
            }

            //계산해준 결과값이 리스트 길이 초과할 수 있는 경우 다시 한번 나눠줌
            if(cur_idx >= size) {
                cur_idx %= size;
            } else if(cur_idx < 0) {
                //음수의 값이 나오면 맨끝 idx인 리스트 사이즈에서 움직임만큼 더해줌
                cur_idx += size;
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
