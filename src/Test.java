import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
        int ans = 1;
        int input = 17;
        while(input >= 1) {
            ans *= input;
            System.out.println(ans);
            input--;
        }

    }
}