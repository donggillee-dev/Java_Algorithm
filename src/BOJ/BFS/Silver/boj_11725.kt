package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList


fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val totalNodeCount = readLine()!!.toInt()
    val vertexList: Array<ArrayList<Int>> = Array(totalNodeCount + 1) { ArrayList() }

    repeat(totalNodeCount - 1) {
        val (node1, node2) = readLine()!!.split(" ").map { it.toInt() }

        vertexList[node1].add(node2)
        vertexList[node2].add(node1)
    }

    bfs(totalNodeCount, vertexList)
}

fun bfs(totalNodeCount: Int, vertexList: Array<ArrayList<Int>>) {
    val start = 1
    val visited = BooleanArray(totalNodeCount + 1) { false }
    val queue = LinkedList<Int>()
    val result = Array(totalNodeCount + 1) { 1 }

    visited[start] = true
    queue.add(start)

    while (queue.isNotEmpty()) {
        val currentNode = queue.poll()

        for (nextNode in vertexList[currentNode]) {
            if (!visited[nextNode]) {
                visited[nextNode] = true
                result[nextNode] = currentNode
                queue.add(nextNode)
            }
        }
    }

    for (i in 2..totalNodeCount) {
        println(result[i])
    }
}