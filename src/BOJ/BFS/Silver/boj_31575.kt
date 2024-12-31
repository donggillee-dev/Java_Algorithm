package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val dirRow = arrayOf(1, 0)
    val dirCol = arrayOf(0, 1)

    val (colSize, rowSize) = readLine()!!.split(' ').map { it.toInt() }
    val map: Array<Array<Int>> = Array(rowSize) { Array(colSize) { 0 } }
    val visited: Array<BooleanArray> = Array(rowSize) { BooleanArray(colSize) { false } }
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    for (i in 0 until rowSize) {
        map[i] = readLine()!!.split(' ').map { it.toInt() }.toTypedArray()
    }

    queue.add(Pair(0, 0))
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val elem = queue.poll()

        for (i in 0 until 2) {
            val dr = elem.first + dirRow[i]
            val dc = elem.second + dirCol[i]

            if (dr < rowSize && dc < colSize && !visited[dr][dc] && map[dr][dc] == 1) {
                queue.add(Pair(dr, dc))
                visited[dr][dc] = true
            }
        }
    }

    if (visited[rowSize - 1][colSize - 1]) {
        println("Yes")
    } else {
        println("No")
    }
}