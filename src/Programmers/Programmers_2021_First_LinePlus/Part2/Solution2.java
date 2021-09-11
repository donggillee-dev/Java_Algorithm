package Programmers.Programmers_2021_First_LinePlus.Part2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("trip", new String[]{"-days NUMBERS", "-dest STRING"},
                new String[]{"trip -days 15 10 -dest Seoul Paris", "trip -days 10 -dest Seoul"})));
    }

    private static boolean[] solution(String program, String[] flag_rules, String[] commands) {
        boolean[] answer = new boolean[commands.length]; //리턴해줄 정답 배열
        HashMap<String, String> flagHash = new HashMap<>(); //flag에 대한 정보를 담을 hashMap
        HashMap<String, Integer> flagCntHash; //flag가 나타나는 횟수를 나타낼 hashMap
        StringTokenizer stk; //문자열 토크나이징을 위한 토크나이저 변수

        //플래그 사용을 위한 해시맵을 만들어줌
        //Key : 플래그명, Value : 해당 플래그의 argument type
        for (String str : flag_rules) {
            stk = new StringTokenizer(str);
            flagHash.put(stk.nextToken(), stk.nextToken());
        }

        //각 command에 대해 조건 만족 여부 판단
        int answer_idx = 0; //answer배열의 idx
        for (String command : commands) {
            flagCntHash = new HashMap<>(); //현재 명령어에서 각 플래그가 몇회 사용되는지 확인하기 위한 HashMap 초기화
            String[] commandArr = command.split(" ");

            boolean isPossible = true; //모든 검증 거친 이후에 현재 명령어에 대한 가능, 불가능 여부 변수

            //프로그램명을 가져와 주어진 program명과 동일한지 체크, 그렇지 않으면 다른 검증 할 필요없이 answer배열 원소 false로 내버려둠
            String progName = commandArr[0];
            if (progName.compareTo(program) == 0) { //프로그램명 일치 , 플래그에 대한 검증 시작
                int length = commandArr.length; //반복문 돌 때 지속적인 length함수 호출 방지하기 위해 명령어 토큰 길이의 변수화

                loop:
                for (int i = 1; i < length; i++) {//0번째 원소는 프로그램명이 들어있으므로 1번쨰 토큰부터 검증 시작
                    //필수적으로 플래그부터 시작되어야함, argument가 올 수 없음
                    String flag = commandArr[i];

                    //명령어의 규칙은 flag , argument 이런 형식으로 나타나야한다
                    //그렇기에 flag가 flagHash에 저장되어있지 않은 값이면 제대로된 command가 아님
                    if (flagHash.get(flag) == null) {
                        isPossible = false;
                        break;
                    } else {
                        //해당 플래그가 몇번 나타나는 건지 검증, 무조건 0번 아니면 1번 나타나야함

                        if(flagCntHash.get(flag) == null) { //한번도 존재한적 없었던 플래그라면 1로 세팅
                            flagCntHash.put(flag, 1);
                        } else {
                            if(flagCntHash.get(flag) == 1) { //이미 1번 존재했던 플래그라면 오류이므로 검증 종료
                                isPossible = false;
                                break;
                            }
                        }

                        //해당 플래그가 존재하는 플래그라면
                        //해당 플래그의 argument_type이 어떤 타입인지 알아낸다
                        String argType = flagHash.get(flag);

                        //맨 마지막 플래그가 아닌 경우에는 arg를 가져온다
                        if (i != length - 1) {
                            int j = i + 1;
                            String arg = commandArr[j];
                            char[] argArr = arg.toCharArray();

                            //String의 경우에는 argument문자열을 하나씩 돌면서 isAlphabetic으로 문자열 체크를 해준다
                            if (argType.compareTo("STRING") == 0) {
                                for (char ch : argArr) {
                                    if (!Character.isAlphabetic(ch)) {
                                        isPossible = false;
                                        break loop;
                                    }
                                }
                            } else if (argType.compareTo("NUMBER") == 0) { //NUMBER의 경우에는 isDigit으로 숫자 체크를 해준다
                                for (char ch : argArr) {
                                    if (!Character.isDigit(ch)) {
                                        isPossible = false;
                                        break loop;
                                    }
                                }
                            } else if (argType.compareTo("STRINGS") == 0) {//다중 인자 STRINGS에 대한 처리
                                for (j = i + 1; j < length; j++) {
                                    if (flagHash.get(commandArr[j]) != null) {//다중 인자 처리중에 다음 플래그를 만났으므로 검증 중지
                                        i = j - 2;//i 인덱스값 조정
                                        break;
                                    }
                                    argArr = commandArr[j].toCharArray(); //현재 j인덱스에 해당하는 인자를 가져와 char배열로 만들어준다
                                    for (char ch : argArr) { //위의 STRING과 동일한 방식으로 해당 인자에 대한 유효성 검증
                                        if (!Character.isAlphabetic(ch)) {
                                            isPossible = false;
                                            break loop;
                                        }
                                    }
                                }
                            } else if (argType.compareTo("NUMBERS") == 0) {//다중 인자 NUMBERS에 대한 처리
                                for (j = i + 1; j < length; j++) {
                                    if (flagHash.get(commandArr[j]) != null) {//다중 인자 처리중에 다음 플래그를 만났으므로 검증 중지
                                        i = j - 2;//i 인덱스값 조정
                                        break;
                                    }
                                    argArr = commandArr[j].toCharArray(); //현재 j인덱스에 해당하는 인자를 가져와 char배열로 만들어준다
                                    for (char ch : argArr) {//위의 NUMBER와 동일한 방식으로 해당 인자에 대한 유효성 검증
                                        if (!Character.isDigit(ch)) {
                                            isPossible = false;
                                            break loop;
                                        }
                                    }
                                }
                            } else { //NULL의 경우에는 다음인자 즉 arg가 flagHash에 존재하는 플래그인지 체크
                                if (flagHash.get(arg) == null) { //존재하지 않는다면 NULL임에도 불구하고 임의의 argument가 온 것이므로 검증 종료
                                    isPossible = false;
                                    break;
                                }
                                i--;
                            }
                            //현재 플래그에 해당하는 인자에 대한 검증이 끝났으므로 다음 플래그에 대한 검증을 하기 위해 i값 증가
                            i++;
                        } else { //맨 마지막 토큰이 플래그인 경우
                            //해당 플래그의 인자 타입이 NULL이 아닌 경우에는 유효하지 않은 command
                            if (argType.compareTo("NULL") != 0) {
                                isPossible = false;
                                break;
                            }
                        }
                    }
                }
                answer[answer_idx] = isPossible; //위의 모든 검증에 대한 결과를 정갑 배열에 넣어준다
            }
            answer_idx++; //다음 명령어에 대한 검증을 위해 answer_idx를 증가시켜준다
        }
        return answer;
    }
}