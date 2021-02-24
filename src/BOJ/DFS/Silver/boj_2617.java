package BOJ.DFS.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_2617 {
    private static HashMap<Integer, Integer> ascHash = new HashMap<>();
    private static HashMap<Integer, Integer> desHash = new HashMap<>();
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        arr  = new int[N + 1];

        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int high = Integer.parseInt(stk.nextToken());
            int low = Integer.parseInt(stk.nextToken());
            ascHash.put(low, high);
            desHash.put(high, low);
        }

        for(Integer node : desHash.keySet()) {
            searchDesc(1, node);
        }
        for(Integer node : ascHash.keySet()) {
            searchAsc(1, node);
        }

        System.out.println(Arrays.toString(arr));
    }
    private static void searchDesc(int depth, int startNode) {
        if(desHash.get(startNode) == null) {
            arr[startNode] += depth;
            return;
        }

        for(Integer node : desHash.keySet()) {
            if(node == startNode) {
                searchDesc(depth + 1, desHash.get(node));
            }
        }
    }
    private static void searchAsc(int depth, int startNode) {
        if(ascHash.get(startNode) == null) {
            arr[startNode] += depth;
            return;
        }

        for(Integer node : ascHash.keySet()) {
            if(node == startNode) {
                searchDesc(depth + 1, ascHash.get(node));
            }
        }
    }
}
