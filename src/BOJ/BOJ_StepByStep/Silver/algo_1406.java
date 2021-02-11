package BOJ.BOJ_StepByStep.Silver;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

//Logic
//단순한 시뮬레이션 문제
//다만 주로 이뤄지는 연산이 remove와 insert이기 때문에 list중에 그 두개의 연산이 빠른 LinkesList 사용

//시간 초과가 나는 문제... 도저히 어디서 나는지 찾을수가 없어서 LinkedList의 시간복잡도와 관련된 문서를 다 뒤졌다...
//


public class algo_1406 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> editorList = new LinkedList<>();
        String input = br.readLine();
        int cmdLine = Integer.parseInt(br.readLine());
        char ch;

        for(int i = 0; i < input.length(); i++) {
            editorList.add(input.charAt(i));
        }
        ListIterator<Character> editorIt = editorList.listIterator();
        while(editorIt.hasNext()){
            editorIt.next();
        }

        for(int i = 0; i < cmdLine; i++) {
            sb.append(br.readLine());

            switch(sb.charAt(0)) {
                case 'P':
                    ch = sb.charAt(2);
                    editorIt.add(ch);
                    break;
                case 'L':
                    if(editorIt.hasPrevious())
                        editorIt.previous();
                    break;
                case 'D':
                    if(editorIt.hasNext())
                        editorIt.next();
                    break;
                case 'B':
                    if(editorIt.hasPrevious()) {
                        editorIt.previous();
                        editorIt.remove();
                    }
                    break;
            }

            sb.setLength(0);
        }

        for(Character c : editorList) {
            bw.write(c);
        }
        bw.close();
        br.close();
    }
}
