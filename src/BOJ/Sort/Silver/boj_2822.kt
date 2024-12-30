package BOJ.Sort.Silver

import java.io.BufferedReader
import java.io.InputStreamReader

data class Score(val score: Int, val index: Int)

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val list: ArrayList<Score> = arrayListOf()
    var sumScore = 0
    val stringBuilder = StringBuilder()

    for (idx in 0 until 8) {
        list.add(Score(score = readLine().toInt(), index = idx + 1))
    }

    var sortedList = list.sortedByDescending { it.score }
    sortedList = sortedList.dropLast(3).sortedBy { it.index }

    for (scoreObj in sortedList) {
        sumScore += scoreObj.score
        stringBuilder.append(scoreObj.index.toString() + ' ')
    }

    println(sumScore)
    print(stringBuilder.toString().trim())
}