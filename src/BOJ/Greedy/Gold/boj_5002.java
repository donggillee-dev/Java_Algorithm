package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//Deque를 이용해서 남녀 성비가 차이가 날 경우 맨 앞에꺼가 현재 큰 개수를 가진 성별이라면 그 뒤를 무조건 픽해줌
//남녀 성비 차이가 업을 경우에는 그냥 무조건 넣어줌

//풀이 시간 : 30분

public class boj_5002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        String line = br.readLine();
        int length = line.length();
        Deque<Character> q = new LinkedList<Character>();
        for (int i = 0; i < length; i++) {
            q.addLast(line.charAt(i));
        }

        int man = 0, woman = 0;

        while (!q.isEmpty() && Math.abs(woman - man) <= x) {
            if (Math.abs(woman - man) > 0) {
                if (length >= 2) {
                    if (woman > man) {
                        if (q.peekFirst() == 'W') {
                            char tmp = q.pollFirst();
                            if (q.peekFirst() == 'M') {
                                man++;
                            } else {
                                woman++;
                            }
                            q.pollFirst();
                            q.addFirst(tmp);
                        } else {
                            man++;
                            q.pollFirst();
                        }
                    } else {
                        if (q.peekFirst() == 'M') {
                            char tmp = q.pollFirst();
                            if (q.peekFirst() == 'M') {
                                man++;
                            } else {
                                woman++;
                            }
                            q.pollFirst();
                            q.addFirst(tmp);
                        } else {
                            woman++;
                            q.pollFirst();
                        }
                    }
                } else {
                    if (q.peekFirst() == 'M') {
                        man++;
                    } else {
                        woman++;
                    }
                    q.pollFirst();
                }
            } else {
                char ch = q.peekFirst();
                if (ch == 'W') woman++;
                else man++;
                q.pollFirst();
            }
            length--;
        }
        int answer = woman + man;

        if(Math.abs(woman - man) > x) System.out.println(answer - 1);
        else System.out.println(answer);
    }
}
