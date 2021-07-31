package HyundaiCard2021;

public class Solution2 {
    private static int answer = 0;
    public static void main(String[] args) {
        System.out.println(solution(5, 1));
    }
    private static void dfs(boolean[] visited, int num, int depth, int k, int m) {
        if(depth == k) {
            if(num % m == 0) answer++;

            return;
        }

        for(int i = 1; i <= k; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(visited, num * 10 + i, depth + 1, k, m);
                visited[i] = false;
            }
        }
    }
    private static int solution(int k, int m) {
        boolean[] visited = new boolean[k + 1];

        dfs(visited, 0, 0, k, m);

        return answer;
    }
}
