package SSAFY_ALGO_HW;
/*nPr ==> 3P2 = 3 * 2 = 6
-----순열-----
[1, 2]
[1, 3]
[2, 1]
[2, 3]
[3, 1]
[3, 2]
-----조합-----
[1, 2]
[1, 3]
[2, 3]
*/

import java.util.Arrays;

public class PerCombiTest {
    static int[] num = {1, 2, 3};   // num = {1,2,3,4}
    static int N = 2;             // N=2, N=3

    public static void main(String[] args) {
//		1. num에서 N개를 뽑아서 만들 수 있는 순열을 모두 출력하시오.
        System.out.println("-----순열-----");
		makePermutation(0, new int[N], new boolean[num.length]);


//		2. num에서 N개를 뽑아서 만들 수 있는 조합을 모두 출력하시오.
        System.out.println("-----조합-----");
		makeCombination(0, new int[N], 0);
    }
    private static void makePermutation(int depth, int[] arr, boolean[] visited) {
        if(depth >= arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for(int i = 0; i < num.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = num[i];
                makePermutation(depth + 1, arr, visited);
                visited[i] = false;
            }
        }
    }

    private static void makeCombination(int depth, int[] arr, int start_idx) {
        if(depth >= arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for(int i = start_idx; i < num.length; i++) {
            arr[depth] = num[i];
            makeCombination(depth + 1, arr, i + 1);
        }
    }
}