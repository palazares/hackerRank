package com.palazares.hackerrank.solutions

import java.util.*

fun largestRectangle(h: Array<Int>): Long {
    val heights = Stack<Long>()
    val positions = Stack<Int>()
    var max = 0L;

    for ((i, b) in h.withIndex()){
        var lastPosition = i
        while (!heights.empty() && heights.peek() > b) {
            lastPosition = positions.pop()
            val mult = heights.pop() * (i - lastPosition)
            if(max < mult) max = mult
        }
        if(heights.empty() || heights.peek() < b) {
            heights.push(b.toLong())
            positions.push(lastPosition)
        }
    }

    while(!heights.empty()) {
        val mult = heights.pop() * (h.size - positions.pop())
        if (max < mult) max = mult
    }

    return max;
}

fun main() {
    //println(largestRectangle(arrayOf(1,2,3,4,5,6,7,8,9,10)))
    println(largestRectangle(arrayOf(11,11,10,10,10)))
}