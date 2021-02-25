package BOJ.String.Silver;
import java.io.*;
import java.util.HashMap;

public class boj_2941 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, Integer> hash = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int answer = 0;
        int length = input.length();

        init(hash);

        for(int i = 0; i < length; i++) {
            sb.append(input.charAt(i));
            if(input.charAt(i) == 'd' && i < length - 1) {
                if(i < length - 2 && input.charAt(i + 1) == 'z') {
                    sb.append(input.charAt(i + 1)).append(input.charAt(i + 2));
                } else if(i < length - 1){
                    sb.append(input.charAt(i + 1));
                }
            } else {
                if(i < length - 1)
                    sb.append(input.charAt(i + 1));
            }
            if(hash.get(sb.toString()) != null) {
                i += sb.length() - 1;
            }
            answer++;
            sb.setLength(0);
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
    }
    private static void init(HashMap<String, Integer> hash) {
        hash.put("c=", 0);
        hash.put("c-", 0);
        hash.put("dz=", 0);
        hash.put("d-", 0);
        hash.put("lj", 0);
        hash.put("nj", 0);
        hash.put("s=", 0);
        hash.put("z=", 0);
    }
}