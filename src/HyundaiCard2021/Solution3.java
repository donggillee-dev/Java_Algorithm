package HyundaiCard2021;

import java.util.*;

public class Solution3 {
    private static int[] solve(int l1, int l2) {
        Set<Integer> storage = new HashSet<>();
        int small = Math.min(l1, l2);
        int big = Math.max(l1, l2);

        int mul = small;
        while(mul <= big) {
            storage.add(mul);
            mul += small;
        }
        int div = big;
        while(div > 0) {
            storage.add(div);
            div -= small;
        }

        int[] answer = new int[storage.size()];
        int index = 0;
        for(int i: storage) {
            answer[index++] = i;
        }

        return answer;
    }
}
