package BOJ.MATH.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val number: Long = readLine()!!.toLong()
    var answer: Long = 0

    for(i in 1 until number) {
        answer += number * i + i
    }

    println(answer)
}