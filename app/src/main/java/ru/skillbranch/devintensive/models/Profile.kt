package ru.skillbranch.devintensive.models

data class Profile(

    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: String,
    val respect: String

) {
    val nickName: String = "John Doe" //TODO
    val rank: String = "Junior Android Developer"

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository
    )
}