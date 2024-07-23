package BOJ.IMP.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    while(true) {
        val sentence = bufferedReader.readLine()
        var count = 0

        if(sentence == "#") {
            break
        }

        for(word in sentence) {
            when(word) {
                'A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u' -> count++
            }
        }
        println(count)
    }
}