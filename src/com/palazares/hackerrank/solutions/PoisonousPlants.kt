package com.palazares.hackerrank.solutions

import java.io.File
import java.util.*

fun poisonousPlants(p: Array<Int>): Int {
    val list = breakDownToLists(p)
    var i = 0

    while (list.size > 1) {
        val listIterator = list.listIterator()
        var previousList = listIterator.next()

        while (listIterator.hasNext()) {
            val innerList = listIterator.next()
            innerList.removeAt(0)
            when {
                innerList.isEmpty() -> listIterator.remove()
                previousList.last() >= innerList.first() -> {
                    previousList.addAll(innerList)
                    listIterator.remove()
                }
                else -> previousList = innerList
            }
        }
        i++
    }

    return i
}

fun breakDownToLists(arr: Array<Int>): MutableList<MutableList<Int>> {
    val result = mutableListOf<MutableList<Int>>()
    var innerList = mutableListOf<Int>()
    for (e in arr) {
        if (innerList.isEmpty())
            innerList.add(e)
        else {
            if (e <= innerList.last()) {
                innerList.add(e)
            } else {
                result.add(innerList)
                innerList = mutableListOf(e)
            }
        }
    }
    if (innerList.isNotEmpty()) result.add(innerList)

    return result
}

fun main() {
    println(poisonousPlants(arrayOf(4, 3, 7, 5, 6, 4, 2)))
}

fun main2(args: Array<String>) {
    //result should be 16
    val scan = Scanner(File("./resources/posionousPlants/input/input12.txt"))

    val n = scan.nextLine().trim().toInt()

    val p = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = poisonousPlants(p)

    println(result)
}