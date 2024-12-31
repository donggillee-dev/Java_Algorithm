package BOJ.BFS.Silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

data class Info(val nodeNumber: Int, val depth: Int)

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val totalPeople = readLine()!!.toInt()
    val (start, end) = readLine()!!.split(" ").map { it.toInt() }
    val relationCount = readLine()!!.toInt()
    val vertexList: Array<ArrayList<Int>> = Array(totalPeople + 1) { ArrayList<Int>() }
    val visited = BooleanArray(totalPeople + 1)

    repeat(relationCount) {
        val (node1, node2) = readLine()!!.split(" ").map { it.toInt() }

        vertexList[node1].add(node2)
        vertexList[node2].add(node1)
    }

    bfs(start, end, visited, vertexList)
}

fun bfs(start: Int, end: Int, visited: BooleanArray, vertexList: Array<ArrayList<Int>>) {
    val queue = LinkedList<Info>()

    queue.offer(Info(start, 0))
    visited[start] = true

    while(queue.isNotEmpty()) {
        val queueElem = queue.poll()

        if(queueElem.nodeNumber == end) {
            println(queueElem.depth)
            return
        }

        for(nextNode in vertexList[queueElem.nodeNumber]) {
            if(!visited[nextNode]) {
                visited[nextNode] = true
                queue.offer(Info(nextNode, queueElem.depth + 1))
            }
        }
    }

    println(-1)
}