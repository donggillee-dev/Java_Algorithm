import java.io.*;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[][] arr = new char[3][3];
        arr[0][0] = 'W';
        arr[0][1] = 'W';
        arr[0][2] = 'W';
        sb.append(Arrays.toString(arr[0]));
        System.out.println(sb);
    }
}