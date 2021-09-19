package Programmers.Programmers_Lv2;

import java.util.HashMap;
import java.util.HashSet;

//Logic
//들어오는 사람에 대해서 기존에 방에 존재하는 사람들은 만난 사람 횟수 + 1
//새로 들어오는 사람은 기존에 존재하는 방 인원의 갯수로 Hash값 지정
//나가려는 사람이 방에 존재하면 그 사람 빼줌

public class InputOutput {
    public static void main(String[] args) {
        solution(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    }
    private static HashMap<Integer, Integer> initHash(int n) {
        HashMap<Integer, Integer> retHash = new HashMap<>();

        for(int i = 1; i <= n; i++) {
            retHash.put(i, 0);
        }

        return retHash;
    }
    private static int[] solution(int[] enter, int[] leave) {
        int n = enter.length, inIdx = 0, outIdx = 0;
        int[] answer = new int[n];
        HashMap<Integer, Integer> ansHash = initHash(n);
        HashSet<Integer> room = new HashSet<>();

        for(; inIdx < n; inIdx++) {
            int in = enter[inIdx];

            if(room.size() != 0) {
                room.forEach(entry -> {
                    ansHash.put(entry, ansHash.get(entry) + 1);
                });
            }

            ansHash.put(in, room.size());
            room.add(in);

            for(; outIdx < n && room.contains(leave[outIdx]); outIdx++) {
                room.remove(leave[outIdx]);
            }
        }

        for(int i = 0; i < n; i++) {
            answer[i] = ansHash.get(i + 1);
        }

        return answer;
    }
}
