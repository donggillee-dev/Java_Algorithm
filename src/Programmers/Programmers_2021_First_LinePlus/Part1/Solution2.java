package Programmers.Programmers_2021_First_LinePlus.Part1;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Solution.solution("UUUUU");
//        Pattern[] patternArr = new Pattern[4];
//        patternArr[0] = Pattern.compile("[a-z]");
//        patternArr[1] = Pattern.compile("[A-Z]");
//        patternArr[2] = Pattern.compile("[0-9]");
//        patternArr[3] = Pattern.compile("[~!@#$%^&*]");
//        String val = "a2Z@";
//        String pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*])[A-Za-z\\d~!@#$%^&*]{0,}$";
//        System.out.println(val.matches(pwPattern));
////        int cnt = 0;
////        for(int i = 0; i < 4; i++) {
////            Matcher matcher = patternArr[i].matcher(val);
////            if(matcher.find()) {
////                cnt++;
////            }
////        }
////        System.out.println(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static class Solution {
        private static int[] solution(String inp_str) {
            int[] answer;
            HashMap<Character, Integer> hash = new HashMap<>();
            Pattern[] patternArr = new Pattern[4];
            patternArr[0] = Pattern.compile("[a-z]");
            patternArr[1] = Pattern.compile("[A-Z]");
            patternArr[2] = Pattern.compile("[0-9]");
            patternArr[3] = Pattern.compile("[~!@#$%^&*]");
            Matcher matcher;

            ArrayList<Integer> errList = new ArrayList<>();
            int length = inp_str.length();
            //1번 규칙
            if(length < 8 || length > 15) errList.add(1);

            String pwPattern = "^[A-Za-z\\d~!@#$%^&*]{0,}$";
            if(!inp_str.matches(pwPattern)) errList.add(2);

            //3번 규칙
            int cnt = 0;
            for(int i = 0; i < 4; i++) {
                matcher = patternArr[i].matcher(inp_str);
                if(matcher.find()) {
                    cnt++;
                }
            }

            if(cnt < 3) errList.add(3);

            boolean flag1 = true, flag2 = true;
            cnt = 0;
            char[] arr = inp_str.toCharArray();
            for(int i = 0; i < length - 1; i++) {
                char ch = arr[i];
                if(flag1) {
                    if(arr[i] == arr[i + 1]) {
                        cnt++;
                        if(cnt == 3) {
                            errList.add(4);
                            flag1 = false;
                        }
                    } else {
                        cnt = 0;
                    }
                }
                if(flag2) {
                    if(hash.get(ch) == null) {
                        hash.put(ch, 1);
                    } else {
                        int num = hash.get(ch);
                        if(num == 4) {
                            errList.add(5);
                            flag2 = false;
                        } else {
                            hash.put(ch, num + 1);
                        }
                    }
                }
            }
            if(hash.get(arr[length - 1]) != null && !errList.contains(5) && hash.get(arr[length - 1]) == 4) {
                errList.add(5);
            }

            Collections.sort(errList);

            if(errList.size() != 0) {
                answer = new int[errList.size()];
                int idx = 0;

                for(int elem : errList) {
                    answer[idx] = elem;
                    idx++;
                }
            } else answer = new int[1];

            return answer;
        }
    }
}
