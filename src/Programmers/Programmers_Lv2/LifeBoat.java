package Programmers.Programmers_Lv2;

import java.util.Arrays;

//Logic
//보트에는 한번에 최대 2명밖에 못태우므로 왼쪽 오른쪽 투포인터 + 정렬을 이용하여 해결 가능
//왼쪽(최소)과 오른쪽(최대) 둘을 한번에 보트에 넣을 수 있으면 넣어주고 불가능하면 오른쪽만 태움

public class LifeBoat {
    public static void main(String[] args) {
        int ans = solution(new int[]{50, 70, 50, 80}, 100);
        System.out.println(ans);
    }
    private static int solution(int[] people, int limit) {
        int answer = 0, length = people.length;

        Arrays.sort(people);

        for(int max = length - 1, min = 0; max >= min; max--) {
            if(people[max] + people[min] <= limit) min++;
            answer++;
        }

        return answer;
    }
}
