package SWEA.D2;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SudokuVerification {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;
        int[][] arr = new int[9][9];
        int TestCase = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TestCase; tc++) {
            HashSet<Integer> set;
            boolean isPossible = true;
            for(int i = 0; i < 9; i++) {
                set = new HashSet<>();
                stk = new StringTokenizer(br.readLine());
                if(!isPossible) continue;
                for(int j = 0; j < 9; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                    set.add(arr[i][j]);
                }
                if(set.size() != 9) {
                    isPossible = false;
                }
            }

            if(isPossible) {
                for(int i = 0; i < 9; i++) {
                    set = new HashSet<>();
                    for(int j = 0; j < 9; j++) {
                        set.add(arr[j][i]);
                    }
                    if(set.size() != 9) {
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible) {
                    loop:for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            set = new HashSet<>();
                            for(int x = i * 3; x < (i * 3) + 3; x++) {
                                for(int y = j * 3; y < (j * 3) + 3; y++) {
                                    set.add(arr[x][y]);
                                }
                            }
                            if(set.size() != 9) {
                                isPossible = false;
                                break loop;
                            }
                        }
                    }
                }
            }
            sb.append("#"+tc+" ");
            if(isPossible) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
