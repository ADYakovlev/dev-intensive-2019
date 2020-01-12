package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val currentTime = date.time
    val time = this.time

    var diff: Long = currentTime - time
    var result = ""
    var unit = ""
    var count: Long
    var lastNubmer = ""

    when (diff) {
        in 0..1 * SECOND -> {
            result = "только что"
        }
        in 1 * SECOND..45 * SECOND -> {
            result = "несколько секунд назад"
        }
        in -45 * SECOND..0 -> {
            result = "через несколько секунд"
        }
        in 45 * SECOND..75 * SECOND -> {
            result = "минуту назад"
        }
        in -75 * SECOND..-45 * SECOND -> {
            result = "через минуту"
        }
        in 75 * SECOND..45 * MINUTE -> {
            count = Math.round((diff / MINUTE).toDouble())
            result = "${TimeUnits.MINUTE.plural(count)} назад"
        }
        in -45 * MINUTE..-75 * SECOND -> {
            count = -Math.round((diff / MINUTE).toDouble())
            result = "через ${TimeUnits.MINUTE.plural(count)}"
        }
        in 45 * MINUTE..22 * HOUR -> {
            count = Math.round((diff / HOUR).toDouble())
            result = "${TimeUnits.HOUR.plural(count)} назад"
        }
        in -22 * HOUR..-45 * MINUTE -> {
            count = -Math.round((diff / HOUR).toDouble())
            result = "через ${TimeUnits.HOUR.plural(count)}"
        }
        in 22 * HOUR..26 * HOUR -> {
            result = "день назад"
        }
        in -26 * HOUR..-22 * HOUR -> {
            result = "через день"
        }
        in 26 * HOUR..360 * DAY -> { //360 * DAY
            count = Math.round((diff / DAY).toDouble())
            result = "${TimeUnits.DAY.plural(count)} назад"
        }
        in -360 * DAY..-26 * HOUR -> { //-360 * DAY
            count = -Math.round((diff / DAY).toDouble())
            result = "через ${TimeUnits.DAY.plural(count)}"
        }
        else -> {
            if (diff > 0)
                result = "более года назад"
            else
                result = "более чем через год"
        }
    }

    return result
}

fun Date.shortFormat(): String {
    val pattern = if (this.isSameDay(Date())) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.isSameDay(date: Date): Boolean {
    val day1 = this.time / DAY
    val day2 = date.time / DAY
    return day1 == day2
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(count: Long): String {
        var unit = ""
        val lastNubmer = count.toString().takeLast(1) //последняя цифра для падежа
        when (lastNubmer) {
            "0", "5", "6", "7", "8", "9" -> {
                when (this) {
                    SECOND -> unit = "секунд"
                    MINUTE -> unit = "минут"
                    HOUR -> unit = "часов"
                    DAY -> unit = "дней"

                }
            }
            "1" -> {
                when (this) {
                    SECOND -> unit = "секунда"
                    MINUTE -> unit = "минута"
                    HOUR -> unit = "час"
                    DAY -> unit = "день"

                }
            }
            "2", "3", "4" -> {
                when (this) {
                    SECOND -> unit = "секунды"
                    MINUTE -> unit = "минуты"
                    HOUR -> unit = "часа"
                    DAY -> unit = "дня"

                }
            }
        }
        return "$count $unit"

    }
}