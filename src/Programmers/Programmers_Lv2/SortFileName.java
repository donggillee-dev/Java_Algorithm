package Programmers.Programmers_Lv2;

import java.util.*;

public class SortFileName {
    private static class Info implements Comparable<Info>{
        String head, number, tail;
        public Info(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        @Override
        public int compareTo(Info o) {
            int headComp = this.head.toLowerCase().compareTo(o.head.toLowerCase());

            if(headComp == 0) {
                return Integer.parseInt(this.number) - Integer.parseInt(o.number);
            } else {
                return headComp;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(this.head + this.number + this.tail);
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        String[] files = {"F-5", "B-50", "A-10", "F-14"};
        SortFileName.solution(files);
    }
    private static String[] solution(String[] files) {
        String[] answer = new String[files.length];
        StringBuilder sb = new StringBuilder();
        ArrayList<Info> list = new ArrayList<>();

        for(String str : files) {
            int idx = 0, length = str.length();
            String head = "", number = "", tail = "";

            for(; idx < length; idx++) {
                char ch = str.charAt(idx);
                if(Character.isDigit(ch)) {
                    break;
                } else {
                    sb.append(ch);
                }
            }
            head = sb.toString();
            sb.setLength(0);

            for(; idx < length; idx++) {
                char ch = str.charAt(idx);
                if(!Character.isDigit(ch)) {
                    break;
                } else {
                    sb.append(ch);
                }
            }
            number = sb.toString();
            sb.setLength(0);

            for(; idx < length; idx++) {
                sb.append(str.charAt(idx));
            }
            tail = sb.toString();
            sb.setLength(0);

            list.add(new Info(head, number, tail));
        }

        Collections.sort(list);

        int idx = 0;
        for(Info inf : list) {
            answer[idx] = inf.toString();
            idx++;
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
