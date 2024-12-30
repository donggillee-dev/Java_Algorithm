package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (startNumber, targetNumber) = readLine().split(" ").map { it.toInt() }
    val visited: Array<Int> = Array(targetNumber + 1) { Integer.MAX_VALUE }

    visited[startNumber] = 0

    for(i in startNumber..targetNumber) {
        if(i * 2 <= targetNumber && visited[i * 2] > visited[i] + 1) {
            visited[i * 2] = visited[i] + 1
        }

        if(i + 1 <= targetNumber && visited[i + 1] > visited[i] + 1) {
            visited[i + 1] = visited[i] + 1
        }
    }

    println(visited[targetNumber])
}