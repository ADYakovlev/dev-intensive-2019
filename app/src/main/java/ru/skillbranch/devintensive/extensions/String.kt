package ru.skillbranch.devintensive.extensions

import java.util.*

fun String.truncate(count: Int = 16): String {
    if (this.isNullOrEmpty())
        return ""

    if (this.trim().length <= count)
        return this.trim()

    return this.substring(0, count).trim() + "..."
}


fun String.stripHtml(): String {

    var isHtml = Stack<Any>()

    if (this.isNullOrEmpty())
        return ""

    var result = ""
    var string = this.trim()

    for (i in string.indices) {

        when (string[i]) {
            '<' -> isHtml.push("html")
            '>' -> isHtml.pop()

            ' ' -> if (!(result.takeLast(1) == " ") && isHtml.empty())
                result += " "

            else -> if (isHtml.empty())
                result += string[i]

        }
    }



    return result
}