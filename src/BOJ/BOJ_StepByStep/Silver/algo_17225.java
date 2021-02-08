package BOJ.BOJ_StepByStep.Silver;
import java.io.*;
import java.util.*;

public class algo_17225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Queue<Info> sangminQueue = new LinkedList<>();
        Queue<Info> jiminQueue = new LinkedList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sangminSb = new StringBuilder();
        StringBuilder jiminSb = new StringBuilder();
        int sangminCnt = 0;
        int jiminCnt = 0;
        int sangminTime = Integer.parseInt(stk.nextToken());
        int jiminTime = Integer.parseInt(stk.nextToken());
        int customNum = Integer.parseInt(stk.nextToken());
        int timeLine = 0;
        int prdNum = 1;
        for(int i = 0; i < customNum; i++) {
            stk = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(stk.nextToken());
            char ch = stk.nextToken().charAt(0);
            int remain = Integer.parseInt(stk.nextToken());

            if(ch == 'B') {
                sangminQueue.offer(new Info(input, remain));
            } else {
                jiminQueue.offer(new Info(input, remain));
            }
        }

        while(!sangminQueue.isEmpty() && !jiminQueue.isEmpty()) {
            int t1 = sangminQueue.peek().inputTime;
            int t2 = jiminQueue.peek().inputTime;

            if(t1 <= t2) {
                sangminCnt++;
                timeLine = sangminQueue.peek().inputTime + sangminTime;
                if(--sangminQueue.peek().remain == 0) sangminQueue.poll();
                if(!sangminQueue.isEmpty()) sangminQueue.peek().inputTime = Math.max(sangminQueue.peek().inputTime, timeLine);
                sangminSb.append(prdNum).append(" ");
            } else {
                jiminCnt++;
                timeLine = jiminQueue.peek().inputTime + jiminTime;
                if(--jiminQueue.peek().remain == 0) jiminQueue.poll();
                if(!jiminQueue.isEmpty()) jiminQueue.peek().inputTime = Math.max(jiminQueue.peek().inputTime, timeLine);
                jiminSb.append(prdNum).append(" ");
            }
            prdNum++;
        }


        if(sangminQueue.isEmpty()) {//상민이 작업이 먼저 끝났을때
            while(!jiminQueue.isEmpty()) {
                jiminCnt++;
                timeLine = jiminQueue.peek().inputTime + jiminTime;
                if(--jiminQueue.peek().remain == 0) jiminQueue.poll();
                if(!jiminQueue.isEmpty()) jiminQueue.peek().inputTime = Math.max(jiminQueue.peek().inputTime, timeLine);
                jiminSb.append(prdNum).append(" ");
                prdNum++;
            }
        } else {//지민이 작업미 먼저 끝났을때
            while(!sangminQueue.isEmpty()) {
                sangminCnt++;
                timeLine = sangminQueue.peek().inputTime + sangminTime;
                if(--sangminQueue.peek().remain == 0) sangminQueue.poll();
                if(!sangminQueue.isEmpty()) sangminQueue.peek().inputTime = Math.max(sangminQueue.peek().inputTime, timeLine);
                sangminSb.append(prdNum).append(" ");
                prdNum++;
            }
        }

        sb.append(sangminCnt).append("\n").append(sangminSb).append("\n").append(jiminCnt).append("\n").append(jiminSb);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    private static class Info {
        int inputTime;
        int remain;
        public Info(int input, int remain) {
            this.inputTime = input;
            this.remain = remain;
        }
    }
}

//Short Version

//import java.io.*;
//import java.util.*;
//public class Main {
//    public static class PresentInfo {
//        int inputTime;
//        int remain;
//        public PresentInfo(int input, int remain) {
//            this.inputTime = input;
//            this.remain = remain;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringBuilder sb = new StringBuilder();
//        Queue<PresentInfo> sangminQueue = new LinkedList<>();
//        Queue<PresentInfo> jiminQueue = new LinkedList<>();
//        StringTokenizer stk = new StringTokenizer(br.readLine());
//        StringBuilder sangminSb = new StringBuilder();
//        StringBuilder jiminSb = new StringBuilder();
//        int sangminCnt = 0;
//        int jiminCnt = 0;
//        int sangminTime = Integer.parseInt(stk.nextToken());
//        int jiminTime = Integer.parseInt(stk.nextToken());
//        int customNum = Integer.parseInt(stk.nextToken());
//        int prdNum = 1;
//        for(int i = 0; i < customNum; i++) {
//            stk = new StringTokenizer(br.readLine());
//            int input = Integer.parseInt(stk.nextToken());
//            char ch = stk.nextToken().charAt(0);
//            int remain = Integer.parseInt(stk.nextToken());
//
//            if(ch == 'B') {
//                sangminQueue.offer(new PresentInfo(input, remain));
//            } else {
//                jiminQueue.offer(new PresentInfo(input, remain));
//            }
//        }
//
//        while(!sangminQueue.isEmpty() || !jiminQueue.isEmpty()) {
//            if(!sangminQueue.isEmpty() && (jiminQueue.isEmpty() || sangminQueue.peek().inputTime <= jiminQueue.peek().inputTime)) {
//                logic(sangminQueue, sangminSb, sangminTime, prdNum++);
//                sangminCnt++;
//            } else {
//                logic(jiminQueue, jiminSb, jiminTime, prdNum++);
//                jiminCnt++;
//            }
//        }
//
//        sangminSb.delete(sangminSb.length() - 1, sangminSb.length());
//        jiminSb.delete(jiminSb.length() - 1, jiminSb.length());
//        sb.append(sangminCnt).append("\n").append(sangminSb).append("\n").append(jiminCnt).append("\n").append(jiminSb);
//        bw.write(sb.toString());
//        bw.close();
//        br.close();
//    }
//
//    public static void logic(Queue<PresentInfo> Q, StringBuilder sb, int time, int num) {
//        sb.append(num).append(" ");
//        int t = Q.peek().inputTime + time;
//        if(--Q.peek().remain == 0) Q.poll();
//        if(!Q.isEmpty()) Q.peek().inputTime = Math.max(Q.peek().inputTime, t);
//    }
//}