import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test {

    static class Tree{
        int x, y, z;
        Tree(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static int N,M,K;
    static int[][] arr;
    static int[][] A;
    static List<Tree> tree = new LinkedList<>();
    static int[] di= {-1,-1,-1,0,0,1,1,1};
    static int[] dj= {-1,0,1,-1,1,-1,0,1};

    static void solve() {
        Tree cur;
//        int size;	// 나무 개수
        int[][] dies = new int[N+1][N+1];    // 죽은 나무
        List<Tree> newTree = new LinkedList<>();    // 새로운 나무

        while(K-->0) {
            // 봄 & 가을
//            size = tree.size();

            for(Iterator<Tree> it = tree.iterator(); it.hasNext(); ) {
                cur = it.next();
                int x = cur.x;
                int y = cur.y;
                int z = cur.z;

                // 양분 먹기
                if(arr[x][y]>= z) {
                    arr[x][y] -= z;
                    cur.z++;

                    // 번식
                    if(cur.z%5==0) {
                        int nx,ny;
                        for(int k=0; k<8; k++) {
                            nx = x+di[k];
                            ny = y+dj[k];

                            if(nx==0 || ny==0 || nx>N || ny>N) continue;

                            newTree.add(new Tree(nx,ny,1));
                        }
                    }

                } else {
                    // 제거
                    dies[x][y] += z/2;
                    it.remove();
//                    size--;
                }
            }

            tree.addAll(0, newTree);
            newTree.clear();

            // 여름 & 겨울
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++) {
                    arr[i][j]+=A[i][j];
                    arr[i][j]+=dies[i][j];
                    dies[i][j]=0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);

        N=scan.nextInt();
        M=scan.nextInt();
        K=scan.nextInt();
        arr = new int[N+1][N+1];
        A = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                arr[i][j]=5;
                A[i][j] = scan.nextInt();
            }
        }

        for(int i=1; i<=M; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int z = scan.nextInt();
            tree.add(new Tree(x,y,z));
        }

        // 크기 순 정렬
        tree.sort(new Comparator<Tree>() {
            public int compare(Tree t1, Tree t2) {
                return t1.z - t2.z;
            }
        });

        solve();

        System.out.println(tree.size());

    }

}