package BOJ.BinarySearch.Silver;

import java.io.*;
import java.util.*;
//Logic
//숫자카드들을 입력받아서 오름차순으로 정렬하고 Collections.binarySearch 함수를 이용해서
//이분 탐색을 진행해준다
//위 방벅으로 하는 것이 정공법이라고 생각했지만 단순히 boolean배열 20,000,000짜리를 선언해서
//들어오는 숫자들에 대해서 음수는 10,000,000을 더해서 각 숫자의 존재여부를 체크해주는 방식이 있다
//내가 한 방법보다 대략 2배정도 빠르기에 그 방식으로 하는 것이 더 나을 것 같다는 생각이 든다

//풀이 시간 : 15분

public class boj_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        ArrayList<Long> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        while(N --> 0) {
            list.add(Long.parseLong(stk.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        Collections.sort(list);

        while(M --> 0) {
            if(Collections.binarySearch(list, Long.parseLong(stk.nextToken())) >= 0) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}