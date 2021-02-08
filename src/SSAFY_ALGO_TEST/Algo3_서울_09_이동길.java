package SSAFY_ALGO_TEST;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Algo3_서울_09_이동길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        //관리자가 사용할 비밀번호 1 ~ 1000
        //해커가 사용한 비밀번호 목록 1000개
        //관리자가 사용할 비밀번호의 범위를 돌면서 해커가 사용한 비밀번호 목록과 대조하여 보안성이 가장 높은 비밀번호를 출력하자
        //이분 탐색해서 관리자가 시도하려는 숫자중 해커의 숫자와 가장 가까운 숫자를 찾아내고 해당 숫자와의 보안성 체크
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringBuilder[] arr = new StringBuilder[M];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            int num = Integer.parseInt(stk.nextToken());
            arr[i] = new StringBuilder();
            //해커의 비밀번호 이진 변환
            while(num > 0) {
                if(num % 2 == 0) {
                    arr[i].insert(0, 0);
                } else {
                    arr[i].insert(0, 1);
                }
                num /= 2;
            }
        }

        StringBuilder targetBinary;
        StringBuilder numBinary;
        //정답은 가장 높은 보안성이어야 하니까 가장 작은 값으로 세팅
        int answer = Integer.MIN_VALUE;
        for(int i = 0; i <= N; i++) {
            //새로운 비밀번호를 이진변환
            int num = i;
            numBinary = new StringBuilder();
            while(num > 0) {
                if(num % 2 == 0) {
                    numBinary.insert(0, 0);
                } else {
                    numBinary.insert(0, 1);
                }
                num /= 2;
            }
            //내가 선정한 비밀번호와 해커의 비밀번호를 비교해서 가장 작은 보안성을 찾아줌
            int secu = Integer.MAX_VALUE;
            for(int j = 0; j < arr.length; j++) {
                int cnt = 0;
                targetBinary = arr[j];
                int myLength = numBinary.length() - 1;
                int targetLength = targetBinary.length() - 1;
                //각 비밀번호의 길이가 다르므로 맨 뒤부터 하나씩 비교해줌
                while(myLength >= 0 && targetLength >= 0) {
                    if(numBinary.charAt(myLength) != targetBinary.charAt(targetLength)) cnt++;
                    myLength--;
                    targetLength--;
                }
                //둘 중 먼저 0이 된 비밀번호가 더 짧은 길이이므로 길이가 아직 남은 비밓번호 탐색해서 1인 경우 cnt++
                if(myLength == 0) {
                    for(int k = 0; k <= targetLength; k++) {
                        if(targetBinary.charAt(k) == '1') cnt++;
                    }
                } else {
                    for(int k = 0; k <= myLength; k++) {
                        if(numBinary.charAt(k) == '1') cnt++;
                    }
                }
                if(secu > cnt) secu = cnt;
            }
            if(answer < secu) answer = secu;
        }
        //가장 좋은 보안성을 보이는 경우의 답을 출력해줌
        sb.append(answer).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
