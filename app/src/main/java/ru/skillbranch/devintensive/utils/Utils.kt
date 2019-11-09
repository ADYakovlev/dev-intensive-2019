package ru.skillbranch.devintensive.utils

object Utils {
    //TODO FIX ME!!!
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val result = StringBuilder()
        for (i in 0..payload.length - 1) {
            result.append(Utils.translitChar(payload.get(i).toString(), divider))
        }
        return result.toString()
    }

    fun translitChar(c: String, divider: String): String {
        return when (c) {
            "А" -> "A"
            "Б" -> "B"
            "В" -> "V"
            "Г" -> "G"
            "Д" -> "D"
            "Е" -> "E"
            "Ё" -> "Je"
            "Ж" -> "Zh"
            "З" -> "Z"
            "И" -> "I"
            "Й" -> "Y"
            "К" -> "K"
            "Л" -> "L"
            "М" -> "M"
            "Н" -> "N"
            "О" -> "O"
            "П" -> "P"
            "Р" -> "R"
            "С" -> "S"
            "Т" -> "T"
            "У" -> "U"
            "Ф" -> "F"
            "Х" -> "Kh"
            "Ц" -> "C"
            "Ч" -> "Ch"
            "Ш" -> "Sh"
            "Щ" -> "Sh"
            "Ъ" -> ""
            "Ы" -> "Ih"
            "Ь" -> ""
            "Э" -> "Eh"
            "Ю" -> "U"
            "Я" -> "Ya"
            "а" -> "a"
            "б" -> "b"
            "в" -> "v"
            "г" -> "g"
            "д" -> "d"
            "е" -> "e"
            "ё" -> "je"
            "ж" -> "zh"
            "з" -> "z"
            "и" -> "i"
            "й" -> "y"
            "к" -> "k"
            "л" -> "l"
            "м" -> "m"
            "н" -> "n"
            "о" -> "o"
            "п" -> "p"
            "р" -> "r"
            "с" -> "s"
            "т" -> "n"
            "у" -> "u"
            "ф" -> "f"
            "х" -> "kh"
            "ц" -> "c"
            "ч" -> "ch"
            "ш" -> "sh"
            "щ" -> "jsh"
            "ъ" -> ""
            "ы" -> "i"
            "ь" -> ""
            "э" -> "e"
            "ю" -> "u"
            "я" -> "ya"
            " " -> divider
            else -> c
        }
    }

    fun toInitials(firstName: String?, middleName: String?): String? {
        val initials = StringBuilder();
        initials.append(firstName?.get(0)?.toUpperCase())
        initials.append(". ")
        initials.append(middleName?.get(0)?.toUpperCase())
        initials.append(".")
        return initials.toString()
    }
}