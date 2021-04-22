package BOJ.IMP.Bronze;

import java.io.*;
import java.math.*;
import java.util.*;

//Logic
//에라토스테네스의 체를 사용해서 입력받은 10^100 수에 대해서 char로 변환해 일일해 숫자를 만들어가면서 mod연산을 해도됨
//하지만 더 간편하게 풀겸 BigInteger에 대한 개념을 잡고 가고싶어서 BigInteger로 품
//풀이 시간 : 10분

public class boj_2061 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        BigInteger k = new BigInteger(stk.nextToken());
        int l = Integer.parseInt(stk.nextToken());
        int ans = 0;

        for(int i = 2; i < l; i++) {
            if(k.remainder(BigInteger.valueOf(i)) == BigInteger.ZERO) {
                ans = i;
                break;
            }
        }

        if(ans != 0) System.out.println("BAD " + ans);
        else System.out.println("GOOD");
    }
}
