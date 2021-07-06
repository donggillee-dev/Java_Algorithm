package BOJ.Stack.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//Logic
//6개 줄에 대해 스택을 만들어서 풀면 되는 문제 (Case-Work)
//풀이 시간 : 25분

public class boj_2841 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Stack<Integer>[] guitar = new Stack[7];
        int n = Integer.parseInt(stk.nextToken());
        int p = Integer.parseInt(stk.nextToken());
        int answer = 0;

        for(int i = 1; i <= 6; i++) {
            guitar[i] = new Stack<>();
        }

        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());

            int melody = Integer.parseInt(stk.nextToken());
            int pret = Integer.parseInt(stk.nextToken());

            if(guitar[melody].isEmpty()) { //해당 줄의 멜로디가 비어있다면
                guitar[melody].add(pret);
                answer++;
            } else { //멜로디가 쌓여있다면
                if(guitar[melody].peek() > pret) { //눌러야하는 프렛보다 큰게 맨 위에 존재하면 작거나 같은애 나올때까지 다 뺴줌
                    while(!guitar[melody].isEmpty() && guitar[melody].peek() > pret) {
                        guitar[melody].pop();
                        answer++;
                    }

                    if(!guitar[melody].isEmpty() && guitar[melody].peek() == pret) continue; //같은 애가 맨 위에 있다면 걍 skip

                    guitar[melody].push(pret); //그 외의 경우에는 해당 pret 눌러주고 ++
                    answer++;
                } else { //바로 프렛 눌러도 되는 경우
                    if(guitar[melody].peek() == pret) continue; //같은 프랫이 맨 위에 존재하면 skip

                    guitar[melody].push(pret); //그 외의 경우는 해당 프렛 눌러주고 ++
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
