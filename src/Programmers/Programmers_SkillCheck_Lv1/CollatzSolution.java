package Programmers.Programmers_SkillCheck_Lv1;

public class CollatzSolution {
    public static void main(String[] args) {
        long n = 626331;
        int ret = solution(n);
        System.out.println(ret);
    }
    public static int solution(long num) {
        int cnt = 0;

        while(num != 1) {
            if(cnt > 500) {
                cnt = -1;
                break;
            }

            if(num % 2 == 0)
                num /= 2;
            else
                num = num * 3 + 1;
            cnt++;
        }
        return cnt;
    }
}
