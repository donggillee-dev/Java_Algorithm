package Programmers.Programmers_2021_Second_LinePlus;

public class Solution1 {
    private static int[] initArr(int length, int[] student) {
        int[] arr = new int[length];

        arr[1] = student[0];

        for (int i = 2; i < length; i++) {
            arr[i] = arr[i - 1] + student[i - 1];
        }

        return arr;
    }

    private static int solution(int[] student, int k) {
        int answer = 0, length = student.length + 1;
        int[] arr = initArr(length, student);

        for (int i = length - 1; i >= 1; i--) {
            if (arr[i] < k) continue;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] - arr[j] == k) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, 1));
        System.out.println(solution(new int[]{0, 1, 0, 0, 1, 1, 0}, 2));
        System.out.println(solution(new int[]{0, 1, 0}, 2));
    }
}
