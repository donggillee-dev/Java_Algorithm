package ETC;
import java.io.*;
import java.util.*;

public class algo_1076 {
    static HashMap<String, Info> Resis = new HashMap<>();
    static String[] Color = new String[10];
    static String ans = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        InitHash();

        for(int i = 0; i < 3; i++) {
            String input = br.readLine();
            Info hash = Resis.get(input);
            if(i < 2) {
                if(hash.val.equals("0")) {
                    if(!ans.equals("")) ans += hash.val;
                } else {
                    ans += hash.val;
                }
            } else {
                String tmp = hash.mul;
                if(ans.equals("")) {
                    ans = "0";
                } else {
                    for(int j = 1; j < tmp.length(); j++) {
                        ans += tmp.charAt(j);
                    }
                }
            }
        }

        sb.append(ans).append("\n");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }

    private static void InitHash() {
        int mul = 1;
        InitColor();

        for(int i = 0; i < 10; i++) {
            Resis.put(Color[i], new Info(i, mul));
            mul *= 10;
        }
    }
    private static void InitColor() {
        Color[0] = "black";
        Color[1] = "brown";
        Color[2] = "red";
        Color[3] = "orange";
        Color[4] = "yellow";
        Color[5] = "green";
        Color[6] = "blue";
        Color[7] = "violet";
        Color[8] = "grey";
        Color[9] = "white";
        return;
    }
}
class Info {
    String val;
    String mul;
    public Info(int v, int m) {
        this.val = Integer.toString(v);
        this.mul = Integer.toString(m);
    }
}
