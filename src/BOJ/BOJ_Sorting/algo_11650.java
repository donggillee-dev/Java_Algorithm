package BOJ.BOJ_Sorting;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class algo_11650 {
    public static void main(String[] args) throws IOException {
        MyComparator myComparator = new MyComparator();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Point> pointList = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            pointList.add(new Point(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }

        Collections.sort(pointList, myComparator);

        for(int i = 0; i < N; i++) {
            sb.append(pointList.get(i).x).append(" ").append(pointList.get(i).y).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class MyComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        if(p1.x > p2.x) {
            return 1;
        }
        else if(p1.x == p2.x) {
            if(p1.y > p2.y) {
                return 1;
            }
        }
        return -1;
    }
}

