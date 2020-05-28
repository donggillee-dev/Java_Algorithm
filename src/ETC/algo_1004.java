package ETC;
import java.io.*;
import java.util.*;

public class algo_1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int TestCase = 0; TestCase < T; TestCase++) {
            int FromX, FromY, ToX, ToY;
            int PlanetCnt = 0;
            int ans = 0;
            StringTokenizer stk = new StringTokenizer(br.readLine());
            FromX = Integer.parseInt(stk.nextToken());
            FromY = Integer.parseInt(stk.nextToken());
            ToX = Integer.parseInt(stk.nextToken());
            ToY = Integer.parseInt(stk.nextToken());

            PlanetCnt = Integer.parseInt(br.readLine());
            for(int nthPlanet = 0; nthPlanet < PlanetCnt; nthPlanet++) {
                int PlanetX, PlanetY, PlanetR;
                stk = new StringTokenizer(br.readLine());
                PlanetX = Integer.parseInt(stk.nextToken());
                PlanetY = Integer.parseInt(stk.nextToken());
                PlanetR = Integer.parseInt(stk.nextToken());
                double x1 = Math.pow((double)FromX - (double)PlanetX, 2);
                double y1 = Math.pow((double)FromY - (double)PlanetY, 2);
                double x2 = Math.pow((double)ToX - (double)PlanetX, 2);
                double y2 = Math.pow((double)ToY - (double)PlanetY, 2);
                double dis1 = Math.sqrt(x1 + y1), dis2 = Math.sqrt(x2 + y2);

                if(dis1 < PlanetR && dis2 > PlanetR) ans++;
                else if(dis2 < PlanetR && dis1 > PlanetR) ans++;
            }
            sb.append(ans).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
