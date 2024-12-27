package BOJ.IMP.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val cnt = readLine()!!.toInt()
    val arr: Array<Int> = Array(cnt) { 0 }

    for (i in 0 until cnt) {
        arr[i] = readLine()!!.toInt()
    }

    if(arr[1] % arr[0] == 0) {
        println(arr[cnt - 1] * (arr[1] / arr[0]))
    } else {
        println(arr[cnt - 1] + (arr[1] - arr[0]))
    }
}