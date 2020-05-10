import java.io.*;
import java.util.*;

public class algo_18808 {
    static int[][] sticker;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        sticker = new int[3][4];
        test();
        sticker = new int[10][2];
        test();

        bw.flush();
        bw.close();
        br.close();
        return;
    }
    public static void rotate() {

    }

    public static void test() {
        System.out.println(sticker.length + " " + sticker[1].length);
    }
    //call sticker attach function
    //sticker attach function
    //sticker roate function
}
