package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class PositionInfo(val row: Int, val col: Int, val value: Int)

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val lineCount: Int = readLine()!!.toInt()
    val map: Array<Array<Int>> = Array(lineCount) { Array(lineCount) { 0 } }
    val visited: Array<BooleanArray> = Array(lineCount) { BooleanArray(lineCount) }
    val queue: Queue<PositionInfo> = LinkedList()

    for (i in 0 until lineCount) {
        val tokenizer: StringTokenizer = StringTokenizer(readLine())
        for (j in 0 until lineCount) {
            map[i][j] = tokenizer.nextToken().toInt()
        }
    }

    queue.offer(PositionInfo(row = 0, col = 0, value = map[0][0]))

    while(queue.isNotEmpty()) {
        val info = queue.poll()
        val movedRow = info.row + info.value
        val movedCol = info.col + info.value

        visited[info.row][info.col] = true

        if(movedCol < lineCount && !visited[info.row][movedCol]) {
            queue.offer(PositionInfo(row = info.row, col = movedCol, value = map[info.row][movedCol]))
        }

        if(movedRow < lineCount && !visited[movedRow][info.col]) {
            queue.offer(PositionInfo(row = movedRow, col = info.col, value = map[movedRow][info.col]))
        }
    }

    if(visited[lineCount - 1][lineCount - 1]) {
        println("HaruHaru")
    } else {
        println("Hing")
    }
}