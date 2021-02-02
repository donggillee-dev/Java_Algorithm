package CodingTest.SW_Maestro;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Second_Test2 {
    static int N, M;
    static int[] Visit = new int[300001];
    static ArrayList<Integer>[] T = new ArrayList[300001];
    static Info[] Arr = new Info[300001];
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        for(int i = 1; i <= N; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            Arr[i].MAX_X = x;
            Arr[i].MIN_X = x;
            Arr[i].MAX_Y = y;
            Arr[i].MIN_Y = y;
        }

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(stk.nextToken());
            int k = Integer.parseInt(stk.nextToken());
            T[j].add(k);
            T[k].add(j);
        }

        for(int i = 1; i <= N; i++) {
            int s = T[i].size();
            int v;
            Visit[i] = 1;
            for(int j = 0; j < s; j++ ){
                if(Visit[T[i].get(j)] == 0 ) {
                    if(Arr[i].MAX_X < Arr[T[i].get(j)].MAX_X) {
                        Arr[i].MAX_X = Arr[T[i].get(j)].MAX_X;
                    }
                } else {
                    Arr[T[i].get(j)].MAX_X = Arr[i].MAX_X;
                }

            }
        }


        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
class Info {
    int MIN_X, MIN_Y, MAX_X, MAX_Y;
    public Info(int min_x, int min_y, int max_x, int max_y) {
        this.MIN_X = min_x;
        this.MIN_Y = min_y;
        this.MAX_X = max_x;
        this.MAX_Y = max_y;
    }
}
