package BOJ.Greedy.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Logic
//알파벳들의 나타나는 자릿수를 모두 합해서 가장 큰 값을 갖는 알파벳에게 가장 큰 값을 할당해주는 것이 해설
//계속 틀려서 고민 더 하다가 힌트 보고 풀었다 질문 게시판

//풀이 시간 :50분

public class boj_1339 {
    private static int n;

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }

    private static void initArr(int max, StringBuilder[] arr) { //문자열 자릿수 공통되게 맞춰주는 함수
        for (int i = 0; i < n; i++) {
            int length = arr[i].length();
            if (length < max) {
                while (length != max) {
                    arr[i].insert(0, '0');
                    length++;
                }
            }
        }
    }

    private static void initHash(int max, StringBuilder[] arr, HashMap<Character, Integer> hash) { //자릿수를 계산, 그리고 그것을 정렬하고 가장 큰 숫자부터 각 알파벳에 할당하는 함수
        List<Character> listKeySet = null;
        int num = 9;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < max; j++) {
                char ch = arr[i].charAt(j);

                if (ch == '0') continue;

                hash.put(ch, hash.getOrDefault(ch, 0) + (int) Math.pow(10, max - j - 1));
            }
        }

        listKeySet = new ArrayList<>(hash.keySet());

        Collections.sort(listKeySet, (v1, v2) -> (
                hash.get(v2).compareTo(hash.get(v1))
        ));

        for (char ch : listKeySet) {
            hash.put(ch, num--);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < max; j++) {
                char ch = arr[i].charAt(j);

                if (ch != '0') {
                    arr[i].deleteCharAt(j);
                    arr[i].insert(j, hash.get(ch));
                }
            }
        }
    }

    private static int getAns(StringBuilder[] arr) { //정답 도출 함수
        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans += stoi(arr[i].toString());
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = stoi(br.readLine());

        int max = 0;
        StringBuilder[] arr = new StringBuilder[n];
        HashMap<Character, Integer> hash = new HashMap<>();

        for (int i = 0; i < n; i++) {
            arr[i] = new StringBuilder();
            arr[i].append(br.readLine());
            max = Math.max(max, arr[i].length());
        }

        initArr(max, arr);
        initHash(max, arr, hash);

        System.out.println(getAns(arr));
    }
}
