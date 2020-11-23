package nl.danielmast.goldfinch.user

data class User(val id: Long, var name: String, var gender: Gender, var orientation: Orientation, var text: String)

enum class Gender {
    MALE, FEMALE
}

enum class Orientation {
    STRAIGHT, GAY, BI
}