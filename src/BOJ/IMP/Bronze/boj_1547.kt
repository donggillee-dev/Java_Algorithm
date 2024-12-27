package BOJ.IMP.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    var cnt : Int = readLine().toInt()
    var ballIndex: Int = 1

    while(cnt-->0) {
        val cupIndexes = readLine().split(' ')
            .map { it.toInt() }

        when (ballIndex) {
            cupIndexes[0] -> ballIndex = cupIndexes[1]
            cupIndexes[1] -> ballIndex = cupIndexes[0]
        }
    }

    println(ballIndex)
}