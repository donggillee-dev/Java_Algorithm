package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_5373 {
    static char[][]
            Upper = {{'w', 'w', 'w'},
                    {'w', 'w', 'w'},
                    {'w', 'w', 'w'}},
            Front = {{'r', 'r', 'r'},
                    {'r', 'r', 'r'},
                    {'r', 'r', 'r'}},
            Left = {{'g', 'g', 'g'},
                    {'g', 'g', 'g'},
                    {'g', 'g', 'g'}},
            Right = {{'b', 'b', 'b'},
                    {'b', 'b', 'b'},
                    {'b', 'b', 'b'}},
            Back = {{'o', 'o', 'o'},
                    {'o', 'o', 'o'},
                    {'o', 'o', 'o'}},
            Down = {{'y', 'y', 'y'},
                    {'y', 'y', 'y'},
                    {'y', 'y', 'y'}};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int cnt;
        StringTokenizer stk;
        String info;
        for(int i = 0; i < T; i++) {
            cnt = Integer.parseInt(br.readLine());
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < cnt; j++) {
                info = stk.nextToken();
                Rotate(info.charAt(0), info.charAt(1));
            }
            Append();
            Init();
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Plate_Rotate(char Side, char Dir) {
        char[][] tmp = new char[3][3];
        switch(Side) {
            case 'U':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Upper[2-col][row];
                        }
                    }

                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Upper[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Upper[i], 0, 3);
                }
                break;

            case 'L':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Left[2-col][row];
                        }
                    }
                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Left[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Left[i], 0, 3);
                }
                break;

            case 'R':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Right[2-col][row];
                        }
                    }
                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Right[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Right[i], 0, 3);
                }
                break;

            case 'B':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Back[2-col][row];
                        }
                    }
                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Back[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Back[i], 0, 3);
                }
                break;

            case 'F':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Front[2-col][row];
                        }
                    }
                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Front[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Front[i], 0, 3);
                }
                break;

            case 'D':
                if(Dir == '+') {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Down[2-col][row];
                        }
                    }
                } else {
                    for(int row=0;row<=2;row++){
                        for(int col=0;col<=2;col++){
                            tmp[row][col]=Down[col][2-row];
                        }
                    }
                }
                for(int i = 0; i < 3; i++) {
                    System.arraycopy(tmp[i], 0, Down[i], 0, 3);
                }
                break;
            default:
                break;
        }
    }
    private static void Rotate(char Side, char Dir) {
        char[] tmp_row = new char[3];
        char[] tmp_column = new char[12];
        switch(Side) {
            case 'U':
                System.arraycopy(Front[0], 0, tmp_row, 0, 3);
                if(Dir == '+') {
                    System.arraycopy(Right[0], 0, Front[0], 0, 3);
                    System.arraycopy(Back[0], 0, Right[0], 0, 3);
                    System.arraycopy(Left[0], 0, Back[0], 0, 3);
                    System.arraycopy(tmp_row, 0, Left[0], 0, 3);
                } else {
                    System.arraycopy(Left[0], 0, Front[0], 0, 3);
                    System.arraycopy(Back[0], 0, Left[0], 0, 3);
                    System.arraycopy(Right[0], 0, Back[0], 0, 3);
                    System.arraycopy(tmp_row, 0, Right[0], 0, 3);
                }
                break;

            case 'L':
                if(Dir == '+') {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[i % 3][0];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Back[i % 3][2];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[i % 3][0];
                        } else {
                            tmp_column[i] = Front[i % 3][0];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[Math.abs((i % 3) - 2)][0] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Back[Math.abs((i % 3) - 2)][2] = tmp_column[i];
                        } else {
                            Down[i % 3][0] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Front[i][0] = tmp_row[i];
                    }
                } else {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[i % 3][0];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Front[i % 3][0];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[i % 3][0];
                        } else {
                            tmp_column[i] = Back[i % 3][2];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[i % 3][0] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Front[i % 3][0] = tmp_column[i];
                        } else {
                            Down[Math.abs((i % 3) - 2)][0] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Back[Math.abs(i - 2)][2] = tmp_row[i];
                    }
                }
                break;

            case 'R':
                if(Dir == '+') {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[i % 3][2];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Front[i % 3][2];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[i % 3][2];
                        } else {
                            tmp_column[i] = Back[i % 3][0];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[i % 3][2] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Front[i % 3][2] = tmp_column[i];
                        } else {
                            Down[Math.abs((i % 3) - 2)][2] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Back[Math.abs(i - 2)][0] = tmp_row[i];
                    }
                } else {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[i % 3][2];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Back[i % 3][0];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[i % 3][2];
                        } else {
                            tmp_column[i] = Front[i % 3][2];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[Math.abs((i % 3) - 2)][2] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Back[Math.abs((i % 3) - 2)][0] = tmp_column[i];
                        } else {
                            Down[i % 3][2] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Front[i][2] = tmp_row[i];
                    }
                }
                break;

            case 'B':
                if(Dir == '+') {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[0][i % 3];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Right[i % 3][2];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[2][i % 3];
                        } else {
                            tmp_column[i] = Left[i % 3][0];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[0][i % 3] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Right[Math.abs((i % 3) - 2)][2] = tmp_column[i];
                        } else {
                            Down[2][i % 3] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Left[Math.abs(i - 2)][0] = tmp_row[i];
                    }
                } else {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[0][i % 3];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Left[i % 3][0];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[2][i % 3];
                        } else {
                            tmp_column[i] = Right[i % 3][2];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[0][Math.abs((i % 3) - 2)] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Left[i % 3][0] = tmp_column[i];
                        } else {
                            Down[2][Math.abs((i % 3) - 2)] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Right[i][2] = tmp_row[i];
                    }
                }
                break;

            case 'F':
                if(Dir == '+') {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[2][i % 3];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Left[i % 3][2];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[0][i % 3];
                        } else {
                            tmp_column[i] = Right[i % 3][0];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[2][Math.abs((i % 3) - 2)] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Left[i % 3][2] = tmp_column[i];
                        } else {
                            Down[0][Math.abs((i % 3) - 2)] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Right[i][0] = tmp_row[i];
                    }
                } else {
                    for(int i = 0; i < 12; i++) {
                        if(i / 3 == 0) {
                            tmp_column[i] = Upper[2][i % 3];
                        } else if(i / 3 == 1){
                            tmp_column[i] = Right[i % 3][0];
                        } else if(i / 3 == 2) {
                            tmp_column[i] = Down[0][i % 3];
                        } else {
                            tmp_column[i] = Left[i % 3][2];
                        }
                    }
                    System.arraycopy(tmp_column, 0, tmp_row, 0, 3); //Upper 임의로 저장
                    for(int i = 3; i < 12; i++) {
                        if(i / 3 == 1) {
                            Upper[2][i % 3] = tmp_column[i];
                        } else if(i / 3 == 2) {
                            Right[Math.abs((i % 3) - 2)][0] = tmp_column[i];
                        } else {
                            Down[0][i % 3] = tmp_column[i];
                        }
                    }
                    for(int i = 0; i < 3; i++) {
                        Left[Math.abs(i - 2)][2] = tmp_row[i];
                    }
                }
                break;

            case 'D':
                System.arraycopy(Front[2], 0, tmp_row, 0, 3);
                if(Dir == '+') {
                    System.arraycopy(Left[2], 0, Front[2], 0, 3);
                    System.arraycopy(Back[2], 0, Left[2], 0, 3);
                    System.arraycopy(Right[2], 0, Back[2], 0, 3);
                    System.arraycopy(tmp_row, 0, Right[2], 0, 3);
                } else {
                    System.arraycopy(Right[2], 0, Front[2], 0, 3);
                    System.arraycopy(Back[2], 0, Right[2], 0, 3);
                    System.arraycopy(Left[2], 0, Back[2], 0, 3);
                    System.arraycopy(tmp_row, 0, Left[2], 0, 3);
                }
                break;

            default:
                break;
        }
        Plate_Rotate(Side, Dir);
    }
    private static void Append() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                sb.append(Upper[i][j]);
            }
            sb.append("\n");
        }
    }
    private static void Init() {
        for(int i = 0; i < 3; i++) {
            Arrays.fill(Upper[i], 'w');
            Arrays.fill(Front[i], 'r');
            Arrays.fill(Left[i], 'g');
            Arrays.fill(Right[i], 'b');
            Arrays.fill(Back[i], 'o');
            Arrays.fill(Down[i], 'y');
        }
    }
}
