package com.palazares.hackerrank.solutions

fun roadsAndLibraries(n: Int, c_lib: Int, c_road: Int, cities: Array<Array<Int>>): Long {
    if (c_lib <= c_road || cities.isEmpty()) return n * c_lib.toLong()

    val visited = Array(n) { false }
    val matrix = Array<MutableList<Int>?>(n) { null }

    for (road in cities) {
        if (matrix[road[0] - 1] == null) matrix[road[0] - 1] = mutableListOf(road[1] - 1) else matrix[road[0] - 1]!!.add(road[1] - 1)
        if (matrix[road[1] - 1] == null) matrix[road[1] - 1] = mutableListOf(road[0] - 1) else matrix[road[1] - 1]!!.add(road[0] - 1)
    }

    var clustersCount = 0
    for (i in 0 until n) {
        if (!visited[i]) {
            clustersCount++
            search(i, matrix, visited)
        }
    }

    return (n - clustersCount) * c_road.toLong() + clustersCount * c_lib.toLong();
}

fun search(i: Int, matrix: Array<MutableList<Int>?>, visited: Array<Boolean>) {
    visited[i] = true
    if (matrix[i] == null) return
    for (x in matrix[i]!!) {
        if (!visited[x]) {
            search(x, matrix, visited)
        }
    }
}

fun main() {
    println(roadsAndLibraries(3, 2, 1, arrayOf(
            arrayOf(1, 2), arrayOf(3, 1), arrayOf(2, 3))))

    println(roadsAndLibraries(6, 2, 5, arrayOf(
            arrayOf(1, 3), arrayOf(3, 4), arrayOf(2, 4),
            arrayOf(1, 2), arrayOf(2, 3), arrayOf(5, 6))))
}