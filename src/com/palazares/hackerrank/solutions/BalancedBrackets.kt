package com.palazares.hackerrank.solutions

import java.util.*

fun isBalanced(s: String): String {
    val stack = Stack<Char>()
    val openingChars = setOf('{', '[', '(')
    val closingChars = setOf('}', ']', ')')
    val closeToOpenMatch = mapOf( '}' to '{', ']' to '[', ')' to '(')

    for (char in s){
        when (char) {
            in openingChars -> stack.push(char)
            in closingChars -> if (stack.empty() || closeToOpenMatch[char] != stack.pop()) return "NO"
        }
    }

    return if (stack.empty()) "YES" else "NO"
}

fun main() {
    println(isBalanced("[({})]"))
    println(isBalanced("[({})]}"))
    println(isBalanced("[({)})]"))
    println(isBalanced("[[({})]"))
}