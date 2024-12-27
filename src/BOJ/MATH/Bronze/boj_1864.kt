package BOJ.MATH.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sb: StringBuilder = StringBuilder()

    while (true) {
        val str: String = readLine()!!
        val length: Int = str.length

        if (str[0] == '#') break

        var answer = 0
        var pow = 1

        for (i in length - 1 downTo 0) {
            when (str[i]) {
                '\\' -> answer += (pow * 1)
                '(' -> answer += (pow * 2)
                '@' -> answer += (pow * 3)
                '?' -> answer += (pow * 4)
                '>' -> answer += (pow * 5)
                '&' -> answer += (pow * 6)
                '%' -> answer += (pow * 7)
                '/' -> answer += (pow * -1)
            }
            pow *= 8
        }
        sb.appendLine(answer)
    }
    print(sb.toString())
}