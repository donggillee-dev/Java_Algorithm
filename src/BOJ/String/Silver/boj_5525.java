package BOJ.String.Silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
//                int size = S.size();
//                if(size >= P) {
//                    while(size >= P) {
//                        answer++;
//                        size -= 2;
//                    }
//                }
//                S.clear();
//                i = idx - 1;
//            }
//        }
//        System.out.println(answer);
//    }
//}
