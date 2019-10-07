package com.palazares.hackerrank.solutions

import java.util.*
import kotlin.math.max

fun riddle(arr: Array<Long>): Array<Long> {
    val valuesStack = Stack<Long>()
    val positionStack = Stack<Int>()
    val pos = Array(arr.size) { 0 }

    positionStack.push(-1)
    for (i in arr.indices) {
        while (!valuesStack.empty() && valuesStack.peek() >= arr[i]) {
            valuesStack.pop()
            positionStack.pop()
        }

        pos[i] = i - positionStack.peek() - 1
        valuesStack.push(arr[i])
        positionStack.push(i)
    }

    valuesStack.clear()
    positionStack.clear()
    positionStack.push(arr.size)
    for (i in arr.size - 1 downTo 0) {
        while (!valuesStack.empty() && valuesStack.peek() >= arr[i]) {
            valuesStack.pop()
            positionStack.pop()
        }

        pos[i] += positionStack.peek() - i - 1
        valuesStack.push(arr[i])
        positionStack.push(i)
    }

    val result = Array<Long>(arr.size) { 0 }

    for (i in arr.indices) {
        result[pos[i]] = max(result[pos[i]], arr[i])
    }

    for (i in arr.size - 2 downTo 0) {
        result[i] = max(result[i], result[i + 1])
    }

    return result
}

fun main() {
    // 7,6,4,4,3,2
    println(riddle(arrayOf(3,5,4,7,6,2)).joinToString())
}