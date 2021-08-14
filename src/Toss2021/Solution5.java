package Toss2021;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution5 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] fruitWeights, int k) {
        int length = fruitWeights.length, size = 0;
        int max = -1, cnt = 0;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < k; i++) {
            if(max < fruitWeights[i]) {
                max = fruitWeights[i];
                cnt = 1;
            } else if(max == fruitWeights[i]) {
                cnt++;
            }
        }

        set.add(max);

        for(int start = 0, end = k; end < length; start++, end++) {
//            if(max == fruitWeights[])
        }

        return null;
    }
}
