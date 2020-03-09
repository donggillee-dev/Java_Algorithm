package Sorting;

import java.io.*;

public class algo_2750 {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        Sort Sort = new Sort();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {//배열에 정렬해야하는 값들 대입
            arr[i] = Integer.parseInt(br.readLine());
        }

        Sort.MergeSort(arr, 0, N - 1);

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

class Sort {
    static int[] sorted = new int[1001];
    void MergeSort(int[] arr, int start, int end) {//분할
        if(start < end) {
            int middle = (start + end) / 2;
            MergeSort(arr, start, middle);
            MergeSort(arr, middle + 1, end);
            Merge(arr, start, middle, end);
        }
    }
    void Merge(int[] arr, int start, int middle, int end) {//병합
        int i = start;
        int j = middle + 1;
        int k = start;

        while(i <= middle && j <= end) {//분할된 왼쪽과 오른쪽에 대해 각각의 값을 비교후 sorted배열에 차례로 삽입
            if(arr[i] < arr[j]) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }
        if(i > middle) {//각 좌우에 대해 비교부분이 중간 분기점을 지난거면 남은 부분들을 그냥 sorted배열에 비교없이 삽입
            for(int t = j; t <= end; t++, k++) {
                sorted[k] = arr[t];
            }
        } else {
            for(int t = i; t <= middle; t++, k++) {
                sorted[k] = arr[t];
            }
        }

        for(int t = start; t <= end; t++) {//정렬이 끝난 분할된 부분에 대해서 원래 배열에 업데이
            arr[t] = sorted[t];
        }
    }
}

