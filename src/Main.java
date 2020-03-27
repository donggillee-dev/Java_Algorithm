import java.io.*;
import java.util.*;

public class Main {
    static int[] pos_arr;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        pos_arr = new int[N];

        N_Queen(N);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void N_Queen(int N) {
        Stack<QueenInfo> S = new Stack<>();
        for(int i = 0; i < N; i++) {
            S.push(new QueenInfo(0, i));
            pos_arr[0] = i;

            while(!S.empty()) {
                int cnt = 0;
                QueenInfo info = S.pop();
                pos_arr[info.depth] = info.pos;
                if(info.depth == N - 1) {//만약에 큐에서 꺼낸 branch가 N - 1과 동일하다면 ans++해주고 continue;
                    ans++;
                    continue;
                }

                //앞으로의 branch들 유망한지 isPossible 함수로 테스트
                //if true => Stack에 넣어줌
                //if false => 아무것도 하지 않는다
                for(int j = 0; j < N; j++) {
                    QueenInfo branch = new QueenInfo(info.depth + 1, j);
                    if(isPossible(branch)) {
                        S.push(branch);
                    }
                }
            }
            S.clear();
        }
    }
    private static boolean isPossible(QueenInfo info) {
        //이때까지 거쳐온 branch들에 대해서 앞으로의 branch(input값)가 유망한지 확인
        int depth = info.depth;
        int pos = info.pos;
        boolean flag = true;

        for(int i = 0; i < depth; i++) {
            //직선거리 대각선 확인
            //대각선의 경우 높이와 밑변의 거리가 같으면 동일 대각선상에 있다고 본다
            if(pos_arr[i] == pos || Math.abs(i - depth) == Math.abs(pos_arr[i] - pos)) {
                flag = false;
                break;
            }
        }

        if(flag) {
            pos_arr[depth] = pos;
            return true;
        } else return false;
        //유망하면
        //pos[depth]에 해당 pos값 넣어주고 true 리턴

        //유망하지 않으면
        //false 리턴
    }
}

class QueenInfo {
    int depth, pos;

    QueenInfo(int d, int p) {
        this.depth = d;
        this.pos = p;
    }
}