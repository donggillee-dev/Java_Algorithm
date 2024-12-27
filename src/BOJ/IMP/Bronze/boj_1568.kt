package BOJ.IMP.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    var birdNumber: Int = readLine()!!.toInt()
    var singNumber = 0
    var singTime = 0

    while (birdNumber > 0) {
        singTime++
        singNumber++

        if (birdNumber < singNumber) {
            singNumber = 1
        } else if (birdNumber == singNumber) {
            break
        }

        birdNumber -= singNumber
    }

    println(singTime)
}