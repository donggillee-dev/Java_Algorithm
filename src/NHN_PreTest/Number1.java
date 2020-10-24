package NHN_PreTest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Number1 {
    public static void main(String[] args) throws IOException {
        Solution sol = new Solution();
        int numOfAllPlayers = 17;
        int numOfQuickPlayers = 5;
        char[] namesOfQuickPlayers = {'B', 'D', 'I', 'M', 'P'};
        int numOfGames = 11;
        int[] numOfMovesPerGame = {3, -4, 5, 6, -7, -8, 10, -12, -15, -20, 23};

        sol.solution(numOfAllPlayers, numOfQuickPlayers, namesOfQuickPlayers, numOfGames, numOfMovesPerGame);
    }
    private static class Solution {
        private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame) throws IOException {
            HashMap<Character, Integer> playerInfo = new HashMap<>();
            HashMap<Character, Integer> quickPlayerInfo = new HashMap<>();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();
            char[] playGround = new char[numOfAllPlayers - 1];
            char joker = 'A';
            int jokerPos = 0;

            for(int i = 0; i < numOfAllPlayers; i++) {
                char player = (char)(i + 'A');

                if(i == 0) playerInfo.put(player, 1);
                else {
                    playerInfo.put(player, 0);
                    playGround[i - 1] = player;
                }

            }
            for(int i = 0; i < numOfQuickPlayers; i++) {
                quickPlayerInfo.put(namesOfQuickPlayers[i], 0);
            }

            for(int i = 0; i < numOfGames; i++) {
                int move = numOfMovesPerGame[i] % playGround.length;
                char nextJoker;
                int nextJokerPos;

                if(move > 0) {
                    int idx = jokerPos + move;
                    if(idx - (playGround.length - 1) > 0) {
                        nextJokerPos = idx - playGround.length;
                    } else nextJokerPos = idx;
                } else {
                    int idx = jokerPos + move;
                    if(idx < 0) {
                        nextJokerPos = (playGround.length + idx);
                        if(nextJokerPos >= playGround.length);
                    } else nextJokerPos = idx;
                }
                nextJoker = playGround[nextJokerPos];

                if(quickPlayerInfo.containsKey(nextJoker)) {
                    int cnt = playerInfo.get(joker);
                    playerInfo.put(joker, cnt + 1);
                    jokerPos = nextJokerPos;
                } else {
                    playGround[nextJokerPos] = joker;
                    joker = nextJoker;
                    jokerPos = nextJokerPos;
                    int cnt = playerInfo.get(joker);
                    playerInfo.put(joker, cnt + 1);
                }
            }
            for(int i = 0; i < playGround.length; i++) {
                char player = playGround[i];
                int cnt = playerInfo.get(player);
                sb.append(player).append(" ").append(cnt).append("\n");
            }
            int cnt = playerInfo.get(joker);
            sb.append(joker).append(" ").append(cnt).append("\n");
            bw.write(String.valueOf(sb));
            bw.flush();
            bw.close();
        }
    }
}
