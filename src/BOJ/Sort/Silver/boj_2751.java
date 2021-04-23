package BOJ.Sort.Silver;

import java.io.*;

public class boj_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Sort2 Sort2 = new Sort2();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Sort2.MergeSort(arr, 0, N -1);

        for(int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
        return;
    }
}

class Sort2 {
    static int[] sorted = new int[10000001]; //Merge 할때마다 정렬하는 것에 대해 쓰이는 임시 배열

    void MergeSort(int[] arr, int start, int end) {//MergeSort 시행
        if(end > start) {
            int middle = (end + start) / 2;// 중간값

            MergeSort(arr, start, middle); //왼쪽부분 분기
            MergeSort(arr, middle + 1, end); //오른쪽 부분 분기
            Merge(arr, start, middle, end); //합치기
        }

        return;
    }

    void Merge(int[] arr, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int k = start;

        while(i <= middle && j <= end) { //배열의 각 좌우부분(중간 기준으로)에 대해서 정렬 및 병합시작
            if(arr[i] < arr[j]) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }

        if(i > middle) {//왼쪽 부분의 정렬이 종료됐을 경우 오른쪽 부분은 그대로 배열에 병합
            for(int t = j; t <= end; t++, k++) {
                sorted[k] = arr[t];
            }
        } else {//오른쪽 부분의 정렬이 종료됐을 경우
            for(int t = i; t <= middle; t++, k++) {
                sorted[k] = arr[t];
            }
        }

        for(int t = start; t <= end; t++) {//마지막으로 정렬된 값들을 원래 배열에 다시 대입
            arr[t] = sorted[t];
        }

        return;
    }
}
