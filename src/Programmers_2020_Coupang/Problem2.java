package Programmers_2020_Coupang;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Problem2 {
    public static void main(String[] args) throws ParseException {
        int n = 3;
        String[] customers = {"10/01 23:20:25 30", "10/01 23:25:50 26", "10/01 23:31:00 05", "10/01 23:33:17 24", "10/01 23:50:25 13", "10/01 23:55:45 20", "10/01 23:59:39 03", "10/02 00:10:00 10"};
        int tmp = solution(n, customers);

    }
    public static int solution(int n, String[] customers) throws ParseException{
        int answer = 0;
        long[] kiosk = new long[n + 1];
        Date[] EndDateOfCus = new Date[customers.length];

        for(int i = 0; i < customers.length; i++) {
            SimpleDateFormat f = new SimpleDateFormat("MM/DD HH:MM:SS");
            StringTokenizer stk = new StringTokenizer(customers[i]);
            String tmp = "";
            tmp += stk.nextToken();
            tmp += stk.nextToken();
            Date D = f.parse(tmp);
            System.out.println(tmp);

//            D.setMinutes(D.getMinutes() + Integer.parseInt(stk.nextToken()));
            EndDateOfCus[i] = D;
            System.out.println(EndDateOfCus[i]);
        }
        return answer;
    }

    public static long getDiff(String d1, String d2) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("MM/DD HH:MM:SS");
        Date D1 = f.parse(d1);
        Date D2 = f.parse(d2);
        long diff, sec;
        int compare = D1.compareTo( D2 );

        if(compare > 0) {
            diff = D1.getTime() - D2.getTime();
        } else if(compare < 0) {
            diff = D2.getTime() - D1.getTime();
        } else {
            diff = 0;
        }
        sec = diff / 1000;
        return sec;
    }
}
