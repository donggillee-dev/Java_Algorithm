package Kakao_Internship_CT;

import java.io.*;
import java.util.*;

public class Problem2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String tmp = "100-200*300-500+20*12312312+1231+22";
        solution(tmp);
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void solution(String expression) {
        String[] minus = expression.split("\\-");//마이너스가 우선순위 3
        String tmp = "";
        for(int i = 0; i < minus.length; i++) {
            tmp += "(";
            String[] minus_plus = minus[i].split("\\+");
            if(minus_plus.length == 1) {
                System.out.println(minus[0]);
            } else {
//                for(int j = 0; j < minus_plus.length; j++) {
//                    tmp += "(";
//                    if(minus_plus.length == 1) {
//                        tmp += minus_plus[0];
//                        tmp += "+";
//                    } else {
//                        String[] minus_plus_mul = minus_plus[j].split("\\*");
//                        for(int k = 0; k < minus_plus_mul.length; k++) {
//                            if(minus_plus_mul.length != 1) {
//                                tmp += minus_plus_mul[k];
//                                tmp += "*";
//                            }
//                        }
//                        tmp = tmp.substring(0, tmp.length() - 1);
//                        tmp += ")";
//                    }
//                }
            }

        }
        System.out.println(tmp);

    }
}
