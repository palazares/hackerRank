package com.palazares.hackerrank.solutions

import java.util.*
import kotlin.math.min

fun riddle(arr: Array<Long>): Array<Long> {
    var stack = Stack<Long>()
    var list = mutableListOf(arr.max()!!)
    arr.forEach { stack.push(it) }

    while (stack.size > 1){
        var curMax = 0L;
        val tmpStack = Stack<Long>()
        while (stack.size > 1){
            tmpStack.push(min(stack.pop(), stack.peek()))
            if (curMax < tmpStack.peek()) curMax = tmpStack.peek()
        }
        tmpStack.reverse()
        stack = tmpStack
        list.add(curMax)
    }
    return list.toLongArray().toTypedArray()
}

fun main() {
    println(riddle(arrayOf(3,5,4,7,6,2)).joinToString())
}