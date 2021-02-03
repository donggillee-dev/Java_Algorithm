package Programmers.Programmers_Lv2;

import java.util.*;


//나가고 들어가는 것만 결과값에 출력해주면된다
//결과의 순서는 입력의 순서에서 Change를 제외하고 순서가 보장되어야함
//이걸 보장하기 위해서 각 uid와 Name으로 HashMap을 만들고
//출입 이력에 대해서 uid, Queue hash를 만들어 주었다
public class OpenChat {
    public static void main(String[] args) {
//        String[] record = {"Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi","Enter uid1234 Muzi", "Leave uid1234 Muzi"};
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan", "Enter uid9999 Test", "Enter uid1111 Test", "Enter uid1 ejej"};
        System.out.println(Arrays.toString(Solution.solution(record)));
    }
    private static class Solution {
        public static String[] solution(String[] record) {
            StringTokenizer stk;
            String[] answer;
            StringBuilder sb = new StringBuilder();
            //Queue에 1은 들어왔다 0은 나갔다
            HashMap<Integer, Queue<Integer>> inOutHash = new HashMap<Integer, Queue<Integer>>();
            HashMap<Integer, String> nameHash = new HashMap<>();
            Queue<Integer> uidQueue = new LinkedList<>();

            for(int i = 0; i < record.length; i++) {
                stk = new StringTokenizer(record[i]);
                String first = stk.nextToken();

                sb.append(stk.nextToken());
                sb.delete(0,3);
                int uid = Integer.parseInt(sb.toString());


                if(!first.equals("Change")) uidQueue.offer(uid);

                //들어왔을때 경우 두가지
                //닉네임이 변경되어 들어와진 경우 => inOutHash에 정보 존재
                //최초에 들어온 경우
                if(first.equals("Enter")) {
                    if(!inOutHash.containsKey(uid)) {
                        inOutHash.put(uid, new LinkedList<>());
                    }

                    inOutHash.get(uid).offer(1);

                    sb.delete(0, sb.length());
                    sb.append(stk.nextToken());

                    nameHash.put(uid, String.valueOf(sb));

                } else if(first.equals("Leave")) {
                    inOutHash.get(uid).offer(0);
                } else { //방 안에서 닉네임을 변경한 경우
                    sb.delete(0, sb.length());
                    sb.append(stk.nextToken());

                    nameHash.put(uid, String.valueOf(sb));
                }

                sb.delete(0, sb.length());
            }

            ArrayList<String> answerList = new ArrayList<>();

            while(!uidQueue.isEmpty()) {
                int uid = uidQueue.poll();
                sb.append(nameHash.get(uid) + "님이 ");

                int inout = inOutHash.get(uid).poll();

                if(inout == 0) {
                    sb.append("나갔습니다.");
                } else {
                    sb.append("들어왔습니다.");
                }

                answerList.add(String.valueOf(sb));
                sb.delete(0, sb.length());
            }

            answer = new String[answerList.size()];
            answer = answerList.toArray(answer);

            return answer;
        }
    }
}
