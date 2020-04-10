import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int tmp = N / 2; //숫자를 크게할 수 있는 방법은 최소 비용으로 자릿수를 늘리는법 -> 1로 도배
                        //2로 나눴을때 몫은 1로 채울수있는 자릿수

        if(tmp % 2 != 0) { //나눴는데 나머지가 하나 남으면 그 다음 최소 비용으로 제일 큰 수인 7을 먼저 앞자리에 넣어줌 7은 획을 3개 쓰므로 몫에서 1을 빼준다
            sb.append(7);
            tmp -= 1;
        }

        for(int i = 0; i < tmp; i++) {//7을 써준개수에서 뺀만큼 1을 채워
            sb.append(1);
        }
        sb.append("\n");//********하지만 틀린점이 하나 있다 입력이 2로 들어왔을경우 1을 출력해야하는데 7이 나옴

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}
