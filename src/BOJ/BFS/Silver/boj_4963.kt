package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data object Direction {
    val dh = arrayOf(-1, 0, 1, 0, 1, -1, -1, 1)
    val dw = arrayOf(0, -1, 0, 1, 1, -1, 1, -1)
    const val SIZE = 8
}

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val stringBuilder = StringBuilder()

    while (true) {
        val (width, height) = readLine()!!.split(' ').map { it.toInt() }

        if (width == 0 || height == 0) break

        val map: Array<Array<Int>> = Array(height) { Array(width) { 0 } }

        for (i in 0 until height) {
            map[i] = readLine()!!.split(' ').map { it.toInt() }.toTypedArray()
        }

        stringBuilder.appendLine(bfs(width, height, map))
    }

    print(stringBuilder.toString())
}

fun bfs(width: Int, height: Int, map: Array<Array<Int>>): Int {
    var size = 0
    val visited: Array<BooleanArray> = Array(height) { BooleanArray(width) }

    for (h in 0 until height) {
        for (w in 0 until width) {
            if (!visited[h][w] && map[h][w] == 1) {
                size++

                val queue: Queue<Pair<Int, Int>> = LinkedList()

                queue.offer(Pair(h, w))
                visited[h][w] = true

                while (queue.isNotEmpty()) {
                    val pair = queue.poll()

                    for (i in 0 until Direction.SIZE) {
                        val dh = pair.first + Direction.dh[i]
                        val dw = pair.second + Direction.dw[i]

                        if (validatePosition(dh, dw, height, width) && !visited[dh][dw] && map[dh][dw] == 1) {
                            visited[dh][dw] = true
                            queue.offer(Pair(dh, dw))
                        }
                    }
                }
            }
        }
    }
    return size
}

fun validatePosition(dh: Int, dw: Int, height: Int, width: Int): Boolean{
    if(dh >= 0 && dw >= 0 && dh < height && dw < width) {
        return true
    }

    return false
}