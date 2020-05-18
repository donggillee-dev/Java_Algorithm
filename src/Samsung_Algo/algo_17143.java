package Samsung_Algo;
import java.io.*;
import java.util.*;

public class algo_17143 {
    private static class Shark {
        int Row;
        int Column;
        int Speed;
        int Direction;
        int Weight;
        public Shark(int r, int c, int s, int d, int z) {
            this.Row = r;
            this.Column = c;
            this.Speed = s;
            this.Direction = d;
            this.Weight = z;
        }
    }
    static int R, C, M, ans = 0;
    static int[] dir_x = {0, -1, 1, 0, 0};
    static int[] dir_y = {0, 0, 0, 1, -1};
    static LinkedList<Shark> Sharks = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        if(M == 0) {
            sb.append(M).append("\n");
        } else {
            Shark cur;
            for(int i = 0; i < M; i++) {
                stk = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                int s = Integer.parseInt(stk.nextToken());
                int d = Integer.parseInt(stk.nextToken());
                int z = Integer.parseInt(stk.nextToken());
                Sharks.add(new Shark(r, c, s, d, z));
            }
            Sort();
            for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {
                cur = shark.next();
                if(cur.Column == 1) {
                    ans += cur.Weight;
                    shark.remove();
                    break;
                }
            }
//            for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {
//                cur = shark.next();
//                System.out.println(cur.Row + " " + cur.Column + " " + cur.Weight);
//            }
//            System.out.println("========== 0 ============");
            Solve();
            sb.append(ans).append("\n");
        }
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
    private static void Solve() {
        Shark cur;

        int Time = 1;
        while(Time <= C) {
            int x = -1;
            int y = -1;
            int weight = 0;
            if(Time >= 2) {
                for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {
                    cur = shark.next();
                    if(cur.Column == Time) {
                        ans += cur.Weight;
                        shark.remove();
                        break;
                    }
                }
            }
            for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {//상어들 이동
                cur = shark.next();
                int Direction = cur.Direction;
                if(Direction == 3 || Direction == 4) {
                    int s = 0;
                    while(s < cur.Speed) {
                        cur.Column += dir_y[Direction];
                        if(cur.Column > C || cur.Column < 1) {
                            if(Direction == 3) {
                                cur.Column = C - 1;
                                Direction = 4;
                            } else {
                                cur.Column = 2;
                                Direction = 3;
                            }
                            cur.Direction = Direction;
                        }
                        s++;
                    }
                } else {
                    int s = 0;
                    while(s < cur.Speed) {
                        cur.Row += dir_x[Direction];
                        if(cur.Row < 1 || cur.Row > R) {
                            if(Direction == 1) {
                                cur.Row = 2;
                                Direction = 2;
                            } else {
                                cur.Row = R - 1;
                                Direction = 1;
                            }
                            cur.Direction = Direction;
                        }
                        s++;
                    }
                }

            }

            Sort();//정렬
//            for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {
//                cur = shark.next();
//                System.out.println(cur.Row + " " + cur.Column + " " + cur.Weight);
//            }
//            System.out.println("=========="+ Time +"============");
            for(Iterator<Shark> shark = Sharks.iterator(); shark.hasNext();) {//상어끼리 잡아먹힘 &&
                cur = shark.next();
                if(cur.Row == x && cur.Column == y) {
                    if(weight > cur.Weight) {
                        shark.remove();
                    }
                } else {
                    x = cur.Row;
                    y = cur.Column;
                    weight = cur.Weight;
                }
            }

            Time++;
        }

    }
    private static void Sort() {
        Collections.sort(Sharks, new Comparator<Shark>() {
            @Override
            public int compare(Shark o1, Shark o2) {
                if(o1.Row > o2.Row) {
                    return +1;
                } else if(o1.Row == o2.Row) {
                    if(o1.Column > o2.Column) {
                        return +1;
                    } else if(o1.Column == o2.Column){
                        if(o1.Weight < o2.Weight) {
                            return +1;
                        } else return -1;
                    } else return -1;
                } else return -1;
            }
        });
    }
}
