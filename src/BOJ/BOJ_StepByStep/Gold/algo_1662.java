package BOJ.BOJ_StepByStep.Gold;

//나의 Logic
//
//들어오는 문자를 검증한다
//1. 숫자인 경우
//압축을 몇번했는지의 정보를 알려주는 문자열의 맨 마지막 숫자는 num으로 계속 갱신
//그 앞의 숫자들은 길이정보로 계속 갱신만 해준다
//
//여기서 내가 틀렸던 이유가 많다...
//1 - 1. 숫자만 들어오게 되는 경우 ⇒ StackEmpty 예외를 뿜어내며 죽어버린다
//이걸 처리해줘야 했음... 스택이 비어있는데 문자열의 마지막에 도달한거면
//처리중인 부분 문자열의 길이를 스택에 넣어줘버림
//
//1 - 2. 모든거 다 처리해주고 마지막에 그냥 숫자들만 존재하는 부분문자열이 있는 경우
//이 경우에는 마지막 숫자로만 존재하는 부분문자열을 더해주지 않는 값이 결과로
//출력되기 때문에 더해주게끔 로직 처리함
//
//2. ( 인 경우
//이전까지 들어온 숫자가 존재할 경우에는 위에 숫자인 경우에서 갱신된 반복 횟수와
//그 문자열의 길이를 스택에 넣어준다
//
//여기서 중요한건 length가 1보다 작은 경우에는 숫자가 하나만 들어온거니까 num만 넣어줌
//
//3. ) 인 경우
//tmp변수를 선언해서 스택에서 하나를 꺼내온다
//꺼내온 값이 '(' ⇒ -1 이면은 압축 해제를 시행한다 , 여는 괄호 앞에는 반복 횟수 즉 num값
//이 들어가 있으므로 해당 값을 꺼내와서 현재 괄호 안에 있는 문자열의 길이에 곱해준다
//
//괄호가 아닌 경우에는 연속된 압축 해제된 문자열 길이가 존재한다는 의미이므로 해당 값을
//현재 길이에 더해준다
//
//괄호 안에 있는 문자열에 대한 압축 해제가 종료되면은 길이 결과를 스택에 다시 넣어준다
//
//
//맨 마지막에 결과값을 출력할때는 스택에 부분 문자열들이 떨어져서 존재할 수도 있으므로 다 더해줘서 출력해준다....

import java.io.*;
import java.util.Stack;

public class algo_1662 {
    private static final long leftBracket = -1;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Long> S = new Stack<>(); //
        String input = br.readLine();

        long length = 0;
        long num = 0, tmp = 0, cnt = 0;
        for(int idx = 0; idx < input.length(); idx++) {
            char ch = input.charAt(idx);

            if(ch == '(') {
                if(length > 1) {
                    length--;
                    S.push(length);
                    S.push(num);
                } else {
                    S.push(num);
                }
                length = 0;
                S.push(leftBracket);
            } else if(ch == ')') {
                tmp = 0;
                while(!S.isEmpty()) {
                    cnt = 0;
                    tmp = S.pop();
                    if(tmp == leftBracket) {
                        cnt = S.pop();
                        length *= cnt;
                        if(!S.isEmpty() && S.peek() > 0) {
                            length += S.pop();
                        }
                        S.push(length);
                        length = 0;
                        break;
                    } else {
                        length += tmp;
                    }
                }
            } else {
                num = ch - '0';
                length++;
                if(ch != ')' && idx == input.length() - 1) {
                    if(S.isEmpty()) {
                        S.push(length);
                    } else {
                        length += S.pop();
                        S.push(length);
                    }
                }
            }
        }

        length = 0;
        while(!S.isEmpty()) {
            length += S.pop();
        }
        bw.write(String.valueOf(length));
        bw.close();
        br.close();
    }
}
