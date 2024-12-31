package BOJ.String.Silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Stack;
//
//public class boj_5525 {
//    public static void main(String[] args) {
//        Stack<Character> S = new Stack<>();
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt(), M = sc.nextInt();
//        int P = 3 + (2 * (N - 1));
//        int answer = 0;
//        String input = sc.next();
//
//        for(int i = 0; i < input.length(); i++) {
//            if(input.charAt(i) == 'I') {
//                int idx = i + 1;
//                S.push('I');
//                while(idx < input.length()) {
//                    if(S.peek() != input.charAt(idx)) {
//                        S.push(input.charAt(idx));
//                    } else {
//                        break;
//                    }
//                    idx++;
//                }
//                if(S.peek() != 'I') S.pop();
//                int SIZE = S.SIZE();
//                if(SIZE >= P) {
//                    while(SIZE >= P) {
//                        answer++;
//                        SIZE -= 2;
//                    }
//                }
//                S.clear();
//                i = idx - 1;
//            }
//        }
//        System.out.println(answer);
//    }
//}
//[BOJ_5525] IOIOI
//
//        현재 문자열이 I인 경우 IOI...OI의 패턴을 가지는 문자열을 스택을 통해서 가져온다
//
//        스택의 크기를 P의 값으로 계산해주어서 원하는 패턴이 반복되는 횟수를 구해준다.
//
//        위 방법으로는 시간이 좀 더 걸리길래 빠른 사람들의 코드를 확인해보았고
//
//        스택을 사용하지 않아도 비슷한 로직으로 풀 수 있다는 것을 알 수 있었다.
//
//        풀이 시간 : 25분
public class boj_5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
        int answer = 0, cnt = 0;
        char[] s = br.readLine().toCharArray();

        for(int i = 0; i < M - 2; i++) {
            if(s[i] == 'I' && s[i + 1] == 'O' && s[i + 2] == 'I') {
                cnt++;
                if(cnt == N) {
                    answer++;
                    cnt--;
                }
                i++;
            } else {
                cnt = 0;
            }
        }
        System.out.println(answer);
    }
}


