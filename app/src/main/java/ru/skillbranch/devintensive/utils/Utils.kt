package ru.skillbranch.devintensive.utils

import android.content.Context
import kotlin.math.roundToInt

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val fullName1 = fullName?.trim()
        if (fullName1.isNullOrEmpty())
            return Pair(null, null)

        val parts: List<String>? = fullName1?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {

        var nickName: String = ""
        for (liter in payload) {

            nickName += when (liter) {
                'А' -> "A"
                'Б' -> "B"
                'В' -> "V"
                'Г' -> "G"
                'Д' -> "D"
                'Е' -> "E"
                'Ё' -> "E"
                'Ж' -> "Zh"
                'З' -> "Z"
                'И' -> "I"
                'Й' -> "I"
                'К' -> "K"
                'Л' -> "L"
                'М' -> "M"
                'Н' -> "N"
                'О' -> "O"
                'П' -> "P"
                'Р' -> "R"
                'С' -> "S"
                'Т' -> "T"
                'У' -> "U"
                'Ф' -> "F"
                'Х' -> "H"
                'Ц' -> "C"
                'Ч' -> "C"
                'Ш' -> "Sh"
                'Щ' -> "Sh"
                'Ъ' -> ""
                'Ы' -> "I"
                'Ь' -> ""
                'Э' -> "E"
                'Ю' -> "Yu"
                'Я' -> "Ya"
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                ' ' -> divider[0]

                else -> liter
            }
        }

        return nickName

    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        var firstName1 = firstName?.trim()
        var lastName1 = lastName?.trim()

        var result = ""

        if (!firstName1.isNullOrEmpty())
            result += firstName1?.substring(0, 1)?.toUpperCase()

        if (!lastName1.isNullOrEmpty()) {
            result += lastName1?.substring(0, 1)?.toUpperCase()
        }

        if (result.length > 0)
            return result;
        else
            return null
    }


    fun convertPxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density).roundToInt()
    }

    fun convertDpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.roundToInt()
    }

}


