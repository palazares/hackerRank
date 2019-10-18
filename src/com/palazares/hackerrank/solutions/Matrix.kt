package com.palazares.hackerrank.solutions

import java.io.File
import java.util.*
import kotlin.collections.HashMap

data class Edge(val x: Int, val y: Int, val weight: Int)
data class Path(val x: Int, val y: Int)

fun minTime(roads: Array<Array<Int>>, machines: Array<Int>): Int {
    val paths: MutableMap<Int, List<Edge>?> = HashMap()
    machines.map { it to null }.toMap(paths)
    val visited = Array(roads.size) { false }
    traverse(0, listOf(), paths, roads, visited)
    val machinePaths: MutableMap<Path, List<Edge>?> = HashMap()
    val edge2Paths: MutableMap<Edge, MutableList<Path>> = HashMap()
    for (i in machines.indices) {
        for (j in i + 1 until machines.size) {
            val pathValue = getCommonPath(paths[machines[i]]!!, paths[machines[j]]!!)
            val pathKey = Path(i, j)
            machinePaths[pathKey] = pathValue
            pathValue.forEach {
                if (!edge2Paths.containsKey(it)) edge2Paths[it] = mutableListOf(pathKey)
                else edge2Paths[it]!!.add(pathKey)
            }
        }
    }
    val allMachineRelatedEdges = machinePaths.flatMap { x -> x.value!! }.distinct().sortedBy { it.weight }
    var result = 0
    var taken = 1
    val iterator = allMachineRelatedEdges.iterator()
    while (taken < machines.size) {
        val edge = iterator.next()
        if (edge2Paths[edge]!!.map { machinePaths[it] }.any { it != null && it.isNotEmpty() }) {
            taken++
            result += edge.weight
            edge2Paths[edge]!!.forEach { machinePaths[it] = null }
        }
    }

    return result
}

fun traverse(node: Int, path: List<Edge>, paths: MutableMap<Int, List<Edge>?>, roads: Array<Array<Int>>, visited: Array<Boolean>) {
    if (visited[node]) return
    visited[node] = true
    if (paths.containsKey(node)) paths[node] = path
    for ((i, r) in roads[node].withIndex().filter { it.value != 0 }) {
        traverse(i, path + Edge(node, i, r), paths, roads, visited)
    }
}

fun getCommonPath(path1: List<Edge>, path2: List<Edge>): List<Edge> {
    if (path1.isEmpty() || path2.isEmpty()) return path1 + path2
    var i = 0
    while (i < path1.size && i < path2.size && path1[i] == path2[i]) i++

    return path1.drop(i) + path2.drop(i)
}

fun main() {
    val scan = Scanner(File("./resources/matrix/input/input10.txt"))

    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val roads = Array(n) { Array(n) { 0 } }

    for (i in 0 until n - 1) {
        val road = scan.nextLine().split(" ").map { it.trim().toInt() }
        roads[road[0]][road[1]] = road[2]
        roads[road[1]][road[0]] = road[2]
    }

    val machines = Array(k) { 0 }
    for (i in 0 until k) {
        val machinesItem = scan.nextLine().trim().toInt()
        machines[i] = machinesItem
    }

    val result = minTime(roads, machines)

    println(result)
}