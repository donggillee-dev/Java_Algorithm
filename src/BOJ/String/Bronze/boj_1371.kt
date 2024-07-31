package BOJ.String.Bronze

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val charArray = IntArray(26) {0}
    var maxCount: Int = 0

    while (true) {
        val line = readLine() ?: break

        for(ch in line) {
            if(ch == ' ') {
                continue
            }

            if(maxCount < ++charArray[ch - 'a']) {
                maxCount = charArray[ch - 'a']
            }
        }
    }
    charArray.forEachIndexed { index, value ->
        if(maxCount == value) {
            print('a' + index)
        }
    }
}