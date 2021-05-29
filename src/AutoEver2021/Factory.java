package AutoEver2021;

public class Factory {
    private static class Info {
        int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int answer = Integer.MIN_VALUE;
    private static Info[] pos;
    private static int n, A, B;

    public static void main(String[] args) {
//        answer = Integer.MIN_VALUE;
        System.out.println(solution(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 2, 3, 4}, 2, 2));
//        answer = Integer.MIN_VALUE;
//        System.out.println(solution(new int[]{0, 1, 2, 3, 4}, new int[]{0, 0, 0, 0, 0}, 3, 2));
    }

    private static void getDist(int[] selA, int[] selB) {
        int distA = Integer.MAX_VALUE;
        int distB = Integer.MAX_VALUE;

        for (int i = 0; i < A - 1; i++) {
            for (int j = i + 1; j < A; j++) {
                Info node1 = pos[selA[i]];
                Info node2 = pos[selA[j]];
                int x = Math.abs(node1.x - node2.x);
                int y = Math.abs(node1.y - node2.y);

                distA = Math.min(distA, (x * x + y * y));
            }
        }

        for (int i = 0; i < B - 1; i++) {
            for (int j = i + 1; j < B; j++) {
                Info node1 = pos[selB[i]];
                Info node2 = pos[selB[j]];
                int x = Math.abs(node1.x - node2.x);
                int y = Math.abs(node1.y - node2.y);

                distB = Math.min(distB, (x * x + y * y));
            }
        }

        int min = Math.min(distA, distB);
        answer = Math.max(answer, min);
    }

    private static void comb(int cntA, int cntB, int startA, int startB, int[] selA, int[] selB, boolean[] visited) {
        if (cntA == A && cntB == B) {
            getDist(selA, selB);
        } else if (cntA == A && cntB != B) {
            for (int i = startB; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    selB[cntB] = i;
                    comb(cntA, cntB + 1, startA, i + 1, selA, selB, visited);
                    visited[i] = false;
                }
            }
        } else {
            for (int i = startA; i < n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    selA[cntA] = i;
                    comb(cntA + 1, cntB, i + 1, startB, selA, selB, visited);
                    visited[i] = false;
                }
            }
        }
    }

    private static int solution(int[] x, int[] y, int a, int b) {
        n = x.length;
        A = a;
        B = b;

        int[] selA = new int[A];
        int[] selB = new int[B];
        boolean[] visited = new boolean[n];

        pos = new Info[n];

        for (int i = 0; i < n; i++) {
            pos[i] = new Info(x[i], y[i]);
        }

        comb(0, 0, 0, 0, selA, selB, visited);
        return answer;
    }
}
