package CodingTest.NAVER_HACKDAY_1;
import java.io.*;
import java.util.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Solution2 sol = new Solution2();

        String[] tmp = new String[6];
        for(int i = 0; i < 6; i++) {
            tmp[i] = br.readLine();
        }
        int k = Integer.parseInt(br.readLine());
        sol.solution(tmp, k);
    }
}
class Solution2 {
    public int solution(String[] id_list, int k) {
        int answer = 0;
        Map < String, Integer > Coup = new HashMap< String, Integer >();

        for(int i = 0; i < id_list.length; i++) {
            StringTokenizer stk = new StringTokenizer(id_list[i]);
            String Cli = new String();

            while(stk.hasMoreTokens()) {
                String tmp = stk.nextToken();
                if(!Cli.contains(String.valueOf(tmp))) {
                    Cli += String.valueOf(tmp);
                    Cli += " ";
                }
            }
            Cli.trim();
            stk = new StringTokenizer(Cli);
            while(stk.hasMoreTokens()) {
                String cli = stk.nextToken();
                if(Coup.containsKey(cli)) {
                    if(Coup.get(cli) < k) {
                        int cnt = Coup.get(cli);
                        cnt++;
                        Coup.put(cli, cnt);
                    }
                } else {
                    Coup.put(cli, 1);
                }
            }
        }

        for( Map.Entry< String, Integer > elem : Coup.entrySet() ) { //출처 https://stove99.tistory.com/96
            System.out.println(elem.getKey() + " : " + elem.getValue());
            answer += elem.getValue();
        }
        return answer;
    }
}