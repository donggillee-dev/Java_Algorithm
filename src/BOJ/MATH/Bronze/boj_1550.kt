package BOJ.MATH.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val line = readLine()
    var answer = 0

    line.forEachIndexed { index, value ->
        val powCount = 16.0.pow(line.length - index - 1)

        answer += when (value) {
            'A', 'B', 'C', 'D', 'E', 'F' -> {
                powCount.toInt() * (10 + (value - 'A'))
            }
            else -> {
                powCount.toInt() * value.toString().toInt()
            }
        }
    }

    print(answer)
}