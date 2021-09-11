package Programmers.Programmers_2021_Kakao;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution3 {
    private static class Info {
        int time;
        boolean isOut;

        public Info(int time, boolean isOut) {
            this.time = time;
            this.isOut = isOut;
        }
    }
    private static class AnsInfo implements Comparable<AnsInfo>{
        String carNum;
        int fee;

        public AnsInfo(String carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }

        @Override
        public int compareTo(AnsInfo o) {
            return this.carNum.compareTo(o.carNum);
        }
    }
    private static int calcFee(int usedTime, int[] fees) {
        int fee = fees[1];

        if(usedTime > fees[0]) {
            int unitTime = (int)Math.ceil((double)(usedTime - fees[0]) / fees[2]);
            fee += unitTime * fees[3];
        }

        return fee;
    }
    private static int[] getAns(int[] fees, HashMap<String, Integer> resultHash) {
        PriorityQueue<AnsInfo> pq = new PriorityQueue<>();

        for(String carNum : resultHash.keySet()) {
            int usedTime = resultHash.get(carNum);
            int fee = calcFee(usedTime, fees);

            pq.add(new AnsInfo(carNum, fee));
        }

        int size = pq.size();
        int[] ans = new int[size];

        for(int i = 0; i < size; i++) {
            ans[i] = pq.poll().fee;
        }

        return ans;
    }

    private static HashMap<String, Integer> makeResult(String[] records) {
        HashMap<String, Integer> ret = new HashMap<>();
        HashMap<String, Info> statusHash = new HashMap<>();

        for(String record : records) {
            String[] infoArr = record.split(" ");
            String[] timeArr = infoArr[0].split(":");
            int hour = Integer.parseInt(timeArr[0]) * 60;
            int min = Integer.parseInt(timeArr[1]);
            int time = hour + min;
            String carNum = infoArr[1];
            boolean isOut = infoArr[2].compareTo("OUT") == 0;

            if(isOut) { //출차의 경우
                int usedTime = time - statusHash.get(carNum).time;

                ret.put(carNum, ret.getOrDefault(carNum, 0) + usedTime);
                statusHash.get(carNum).isOut = true;
            } else { //입차의 경우
                statusHash.put(carNum, new Info(time, isOut));
            }
        }

        //출차 처리되지 않은 애들 처리
        for(String carNum : statusHash.keySet()) {
            Info car = statusHash.get(carNum);
            if(!car.isOut) {
                int usedTime = (23 * 60 + 59) - car.time;

                ret.put(carNum, ret.getOrDefault(carNum, 0) + usedTime);
            }
        }

        return ret;
    }

    private static int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        HashMap<String, Integer> resultHash = makeResult(records);

        answer = getAns(fees, resultHash);

        return answer;
    }
    public static void main(String[] args) {
        solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
    }
}
