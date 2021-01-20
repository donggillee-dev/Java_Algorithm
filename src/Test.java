import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int num = 129392;
        while(num > 1) {
            sb.insert(0, num % 2);
            num /= 2;
        }
        if(num == 1) sb.insert(0, 1);

        System.out.println(String.valueOf(sb));
    }
}