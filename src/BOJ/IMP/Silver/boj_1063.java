package BOJ.IMP.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
//Logic
//그냥 구현문제
//다만 상하좌우와 인덱스에 대해서 약간의 헷갈림이 있었지만
//굳이 배열을 이용해서 구현할 필요가 없는 문제였음
//풀이 시간 : 40분
public class boj_1063 {
    private static void initHash(HashMap<Character, Integer> posHash, HashMap<String, int[]> dirHash) {
        char ch = 'A';
        while(ch <= 'Z') {
            posHash.put(ch, ch - 'A' + 1);
            ch++;
        }

        dirHash.put("R", new int[]{0, 1});
        dirHash.put("L", new int[]{0, -1});
        dirHash.put("B", new int[]{-1, 0});
        dirHash.put("T", new int[]{1, 0});
        dirHash.put("RT", new int[]{1, 1});
        dirHash.put("LT", new int[]{1, -1});
        dirHash.put("RB", new int[]{-1, 1});
        dirHash.put("LB", new int[]{-1, -1});
    }
    private static boolean isRange(int r, int c) {
        if(r < 1 || c < 1 || r > 8 || c > 8) return false;

        return true;
    }
    public static void main(String[] args) throws IOException {
        //알파벳 Col, 숫자 Row
        //왼쪽 아래는 A1, 그 오른쪽은 B1
        //체스판에는 돌이 존재, 킹이 그 돌과 같은 곳으로 움직이게 되면은 돌을 민다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> posHash = new HashMap<>();
        HashMap<String, int[]> dirHash = new HashMap<>();
        String[] pos = br.readLine().split(" ");

        initHash(posHash, dirHash);

        int king_row = pos[0].charAt(1) - '0';
        int king_col = posHash.get(pos[0].charAt(0));
        int stone_row = pos[1].charAt(1) - '0';
        int stone_col = posHash.get(pos[1].charAt(0));
        int n = Integer.parseInt(pos[2]);


        while(n-- > 0) {
            int[] dir = dirHash.get(br.readLine());

            int nr = king_row + dir[0];
            int nc = king_col + dir[1];

            if(!isRange(nr, nc)) continue;

            if(nr == stone_row && nc == stone_col) { //해당 위치에 돌이 존재하는 경우
                int nr2 = stone_row + dir[0];
                int nc2 = stone_col + dir[1];

                if(isRange(nr2, nc2)) {
                    stone_row = nr2;
                    stone_col = nc2;
                    king_row = nr;
                    king_col = nc;
                }
            } else { //돌 존재 안하면 걍 밀어줌
                king_row = nr;
                king_col = nc;
            }
        }
        String kingPos = "" + (char)(64 + king_col) + king_row ;
        String stonePos = "" + (char)(64 + stone_col) + stone_row;

        System.out.println(kingPos);
        System.out.println(stonePos);
    }
}
