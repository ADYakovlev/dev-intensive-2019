package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun aksQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val valid = validate(answer, question)

        if (valid != "OK")
            return "$valid\n${question.question}" to status.color

        return if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n" +
                    "${question.question}" to status.color
        } else {
            status = status.nextStatus()
            when (status.name) {
                "NORMAL" -> {
                    this.question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n" +
                            "${question.question}" to status.color
                }
                else -> {
                    "Это неправильный ответ\n" +
                            "${question.question}" to status.color
                }
            }

        }

    }

    fun validate(answer: String, question: Question): String {
        if (answer.isNullOrEmpty()) return ""
        return when (question) {
            Question.NAME -> {
                if (answer[0].isLowerCase()) "Имя должно начинаться с заглавной буквы"
                else "OK"
            }
            Question.PROFESSION -> {
                if (answer[0].isUpperCase()) "Профессия должна начинаться со строчной буквы"
                else "OK"
            }
            Question.MATERIAL -> {
                if (answer.contains("[0-9]".toRegex())) "Материал не должен содержать цифр"
                else "OK"
            }
            Question.BDAY -> {
                if (answer.toLowerCase().contains("[a-z]|[а-я]".toRegex()))
                    "Год моего рождения должен содержать только цифры"
                else "OK"
            }
            Question.SERIAL -> {
                if ((answer.toLowerCase().contains("[a-z]|[а-я]".toRegex())) || (answer.length != 7))
                    "Серийный номер содержит только цифры, и их 7"
                else "OK"
            }
            Question.IDLE -> "OK"
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибольщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "wood", "iron")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question

    }

}