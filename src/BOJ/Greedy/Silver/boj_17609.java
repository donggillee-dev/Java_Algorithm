package BOJ.Greedy.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Logic
//너무 더럽게 짬...
//재귀로 풀걸 괜히 거의 구현식으로 풀었다가 시간만 낭비했다
//재귀로 간단하게 짠 코드를 첨부함. 나중에 보고 다시 풀어볼 것
//풀이 시간 : 50분

public class boj_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            tmp.setLength(0);
            tmp.append(br.readLine());
            int left = 0, right = tmp.length() - 1;
            boolean flag = true;

            if (tmp.length() == 1) {
                sb.append(0).append("\n");
                continue;
            }
            while (left < right) {
                if (tmp.charAt(left) != tmp.charAt(right)) {
                    if (!flag) break;

                    if (left + 1 == right) {
                        if (flag) {
                            sb.append(1).append("\n");
                            break;
                        }
                    } else if (tmp.charAt(left + 1) == tmp.charAt(right) && tmp.charAt(left) == tmp.charAt(right - 1)) {
                        int i = 1;
                        while (true) {
                            if (tmp.charAt(left + i) == tmp.charAt(right - (i + 1)) && tmp.charAt(left + (i + 1)) == tmp.charAt(right - 1)) {
                                i++;
                            } else if (tmp.charAt(left + i) == tmp.charAt(right - (i + 1))) {
                                tmp.delete(right, right + 1);
                                right--;
                                break;
                            } else if (tmp.charAt(left + (i + 1)) == tmp.charAt(right - i)) {
                                tmp.delete(left, left + 1);
                                right--;
                                break;
                            }
                        }
                    } else if (tmp.charAt(left + 1) == tmp.charAt(right)) {
                        tmp.delete(left, left + 1);
                        right--;
                    } else if (tmp.charAt(left) == tmp.charAt(right - 1)) {
                        tmp.delete(right, right + 1);
                        right--;
                    }
                    flag = false;
                } else {
                    left++;
                    right--;
                }
            }

            if (left > right) {
                if (flag) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(1).append("\n");
                }
            } else if (left == right) {
                if (flag) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(1).append("\n");
                }
            } else {
                if (!flag)
                    sb.append(2).append("\n");
            }
        }

        System.out.print(sb);
    }
}

//public class B_17609_회문_Main {
//    static int t;
//    static String s;
//    static char[] arr;
//    public static void main(String[] args) {
//
//        Scanner sc=new Scanner(System.in);
//        t=sc.nextInt();
//        for (int tc = 1; tc <=t; tc++) {
//            s=sc.next();
//            arr=s.toCharArray();
//            int left=0;
//            int right=s.length()-1;
//            if(check(left,right)) {
//                System.out.println(0);
//                continue;
//            }
//            if(checkS(left,right)) {
//                System.out.println(1);
//            }else {
//                System.out.println(2);
//            }
//
//        }
//
//    }
//
//    private static boolean check(int left,int right) {
//        while(left<=right) {
//            if(arr[left]!=arr[right]) {//다름
//                return false;
//            }
//            left+=1;
//            right-=1;
//        }
//        return true;
//    }
//
//    private static boolean checkS(int left,int right) {
//        while(left<=right) {
//            if(arr[left]!=arr[right]) {//다름
//                boolean a=check(left+1,right);
//                boolean b=check(left,right-1);
//                if(a==false && b==false) {
//                    return false;
//                }else return true;
//            }
//            left+=1;
//            right-=1;
//        }
//        return true;
//    }
//}