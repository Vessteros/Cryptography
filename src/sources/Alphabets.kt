package sources

val rusLower = 1072..1103
val rusUpper = 1040..1071

val engLower = 97..122
val engUpper = 65..90

val numbers = 48..57

val spChars = arrayListOf(
    46, 44, 33, 63, 47, 40, 41, 45, 43, 61, 64, 35,
    37, 94, 38, 58, 59, 34, 39, 92, 96, 126, 42, 95
)

val alphabetsMapNames = mapOf(
    1 to "rusLower",
    2 to "rusUpper",
    3 to "engLower",
    4 to "engUpper",
    5 to "numbers",
    6 to "spChars"
)

val alphabetsMap = mapOf(
    "rusLower" to rusLower,
    "rusUpper" to rusUpper,
    "engLower" to engLower,
    "engUpper" to engUpper,
    "numbers"  to numbers,
    "spChars"  to spChars
)