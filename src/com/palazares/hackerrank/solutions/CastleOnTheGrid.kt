package com.palazares.hackerrank.solutions

import java.util.*

fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
    val cells = Array(grid.size) { Array(grid.size) { -1 } }

    for ((i, row) in grid.withIndex())
        for ((j, char) in row.withIndex()) {
            if (char == 'X') cells[i][j] = -2
        }
    cells[startX][startY] = 0
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.offer(Pair(startX, startY))
    while (!queue.isEmpty()) {
        val elem = queue.poll()
        val nextStepList = getAllReachable(elem.first, elem.second, cells)
        for (p in nextStepList) {
            if (p.first == goalX && p.second == goalY) return cells[goalX][goalY]
            queue.offer(p)
        }
    }

    return -1;
}

fun getAllReachable(x: Int, y: Int, cells: Array<Array<Int>>): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    val size = cells.size
    var i = 1;
    val currentVal = cells[x][y] + 1
    while (x - i >= 0 && cells[x - i][y] != -2) {
        if (cells[x - i][y] < 0) {
            cells[x - i][y] = currentVal
            result.add(Pair(x - i, y))
        }
        i++;
    }
    i = 1;
    while (y - i >= 0 && cells[x][y - i] != -2) {
        if (cells[x][y - i] < 0) {
            cells[x][y - i] = currentVal
            result.add(Pair(x, y - i))
        }
        i++;
    }
    i = 1;
    while (x + i <= size - 1 && cells[x + i][y] != -2) {
        if (cells[x + i][y] < 0) {
            cells[x + i][y] = currentVal
            result.add(Pair(x + i, y))
        }
        i++;
    }
    i = 1;
    while (y + i <= size - 1 && cells[x][y + i] != -2) {
        if (cells[x][y + i] < 0) {
            cells[x][y + i] = currentVal
            result.add(Pair(x, y + i))
        }
        i++;
    }

    return result
}

fun main() {
    val x = listOf(1)
    x.zipWithNext()
    println(minimumMoves(arrayOf(".X.", ".X.", "..."), 0, 0, 0, 2))
}