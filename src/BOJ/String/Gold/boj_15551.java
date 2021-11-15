package BOJ.String.Gold;

import java.util.Scanner;

public class boj_15551 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        String s="";
        for(int i=2;i<N;i++) s+="*";
        System.out.printf("%s>>\n%s=]\n",s,s);
        sc.close();
    }
}
