package sources


val engLower = 97..122
val engUpper = 65..90

val spCharsOld = arrayListOf(
    46, 44, 33, 63, 47, 40, 41, 45, 43, 61, 64, 35,
    37, 94, 38, 58, 59, 34, 39, 92, 96, 126, 42, 95
)

val rusLower = arrayListOf(
    'а'.toInt(),
    'б'.toInt(),
    'в'.toInt(),
    'г'.toInt(),
    'д'.toInt(),
    'е'.toInt(),
    'ё'.toInt(),
    'ж'.toInt(),
    'з'.toInt(),
    'и'.toInt(),
    'й'.toInt(),
    'к'.toInt(),
    'л'.toInt(),
    'м'.toInt(),
    'н'.toInt(),
    'о'.toInt(),
    'п'.toInt(),
    'р'.toInt(),
    'с'.toInt(),
    'т'.toInt(),
    'у'.toInt(),
    'ф'.toInt(),
    'х'.toInt(),
    'ц'.toInt(),
    'ч'.toInt(),
    'ш'.toInt(),
    'щ'.toInt(),
    'ъ'.toInt(),
    'ы'.toInt(),
    'ь'.toInt(),
    'э'.toInt(),
    'ю'.toInt(),
    'я'.toInt(),
    ' '.toInt()
)
val rusUpper = arrayListOf(
    'А'.toInt(),
    'Б'.toInt(),
    'В'.toInt(),
    'Г'.toInt(),
    'Д'.toInt(),
    'Е'.toInt(),
    'Ё'.toInt(),
    'Ж'.toInt(),
    'З'.toInt(),
    'И'.toInt(),
    'Й'.toInt(),
    'К'.toInt(),
    'Л'.toInt(),
    'М'.toInt(),
    'Н'.toInt(),
    'О'.toInt(),
    'П'.toInt(),
    'Р'.toInt(),
    'С'.toInt(),
    'Т'.toInt(),
    'У'.toInt(),
    'Ф'.toInt(),
    'Х'.toInt(),
    'Ц'.toInt(),
    'Ч'.toInt(),
    'Ш'.toInt(),
    'Щ'.toInt(),
    'Ъ'.toInt(),
    'Ы'.toInt(),
    'Ь'.toInt(),
    'Э'.toInt(),
    'Ю'.toInt(),
    'Я'.toInt()
)
val numbers = arrayListOf(
    '1'.toInt(),
    '2'.toInt(),
    '3'.toInt(),
    '4'.toInt(),
    '5'.toInt(),
    '6'.toInt(),
    '7'.toInt(),
    '8'.toInt(),
    '9'.toInt(),
    '0'.toInt()
)
val spChars = arrayListOf(
    ' '.toInt(),
    '.'.toInt(),
    ','.toInt(),
    '-'.toInt(),
    '!'.toInt(),
    '?'.toInt(),
    ':'.toInt(),
    ';'.toInt(),
    '\''.toInt(),
    '\\'.toInt(),
    '/'.toInt(),
    '"'.toInt(),
    '('.toInt(),
    ')'.toInt(),
    '='.toInt(),
    '`'.toInt(),
    '~'.toInt(),
    '^'.toInt(),
    '%'.toInt(),
    '@'.toInt(),
    '>'.toInt(),
    '<'.toInt()
)

val alphabetsMapNames = hashMapOf(
    1 to "rusLower",
    2 to "rusUpper",
    3 to "numbers",
    4 to "spChars"
)

val alphabetsMap = hashMapOf(
    "rusLower" to rusLower,
    "rusUpper" to rusUpper,
    "numbers" to numbers,
    "spChars" to spChars
)