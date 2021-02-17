package BOJ.Hash.Silver;

import java.io.*;
import java.util.HashSet;
//[ BOJ_11478 ] 서로 다른 문자열의 개수
//
//        모든 경우의 수대로 반복을 돌아서 중복 부분 문자열을 처리해주는 문제
//
//        substring을 이용해서 처리할 수 있는 코드를 보고 시간을 좀 개선했다
//
//        풀이 시간 : 15분
public class boj_11478 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hash = new HashSet<>();
        String input = br.readLine();
        int length = input.length();

        for(int i = 0; i < length; i++) {
            for(int j = i; j < length; j++) {
                hash.add(input.substring(i, j + 1));
            }
        }

        System.out.println(hash.size());
    }
}
