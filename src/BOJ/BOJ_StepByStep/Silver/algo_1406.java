package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

//Logic

//Simulation 문제
//이 문제의 주요 핵심은
//1. LinkedList를 꼭 이용해야하는데... 시간 복잡도를 어떻게 처리할지?? 이다
//  1-1. LinkedList에서 원소를 제거하고 추가하는데 맨 앞 맨 뒤가 아닌 이상 O(N)의 시간복잡도를 가짐
//      이런 문제를 해결하기 위해 ListIterator라는게 존재한다.
//      이터레이터를 통해서 원하는 원소를 포인팅하는...? 느낌이라고 보면 될 것 같다

//그 외에는 구현만 잘 하면 될 것 같다



public class algo_1406 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> editorList = new LinkedList<>();
        String input = br.readLine();
        int N = input.length();
        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            editorList.add(input.charAt(i));
        }
        ListIterator<Character> listIt = editorList.listIterator(N);

        while(M --> 0) {
            input = br.readLine();
            char cmd = input.charAt(0);

            switch(cmd) {
                case 'L':
                    if(listIt.hasPrevious())
                        listIt.previous();
                    break;
                case 'D':
                    if(listIt.hasNext())
                        listIt.next();
                    break;
                case 'B':
                    if(listIt.hasPrevious()) {
                        listIt.previous();
                        listIt.remove();
                    }
                    break;
                case 'P':
                    char ch = input.charAt(2);
                    listIt.add(ch);
                    break;
            }
        }
        for(Character ch : editorList) {
            bw.write(ch);
        }

        bw.close();
        br.close();
    }
}
