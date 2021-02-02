package Programmers.Programmers_SkillCheck_Lv1;

import java.io.*;
import java.util.*;

public class SortStringArray {
    public static void main(String[] args) throws IOException {
        String[] strings = {"abce", "abcd", "cdx"};
        int idx = 2;
        String[] result = solution(strings, idx);

        System.out.println(Arrays.toString(result));
        return;
    }

    public static String[] solution(String[] strings, int index) {
        Arrays.sort(strings);
        char[] chars = new char[strings.length];

        for(int i = 0; i < strings.length; i++) {
            chars[i] = strings[i].charAt(index);
        }

        for(int i = 0; i < strings.length; i++) {
            for(int j = 0; j < strings.length - 1; j++) {
                if(chars[j] > chars[j + 1]) {
                    char tmp_char = chars[j];
                    String tmp_string = strings[j];

                    chars[j] = chars[j + 1];
                    chars[j + 1] = tmp_char;
                    strings[j] = strings[j + 1];
                    strings[j + 1] = tmp_string;
                }
            }
        }
        return strings;
    }
}
