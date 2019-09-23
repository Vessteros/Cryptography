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

val united = arrayListOf(
    'а'.toInt(),'б'.toInt(),'в'.toInt(),'г'.toInt(),'д'.toInt(),'е'.toInt(),'ё'.toInt(),'ж'.toInt(),'з'.toInt(),'и'.toInt(),'й'.toInt(),'к'.toInt(),'л'.toInt(),'м'.toInt(),'н'.toInt(),'о'.toInt(),'п'.toInt(),'р'.toInt(),'с'.toInt(),'т'.toInt(),'у'.toInt(),'ф'.toInt(),'х'.toInt(),'ц'.toInt(),'ч'.toInt(),'ш'.toInt(),'щ'.toInt(),'ъ'.toInt(),'ы'.toInt(),'ь'.toInt(),'э'.toInt(),'ю'.toInt(),'я'.toInt(),
    'А'.toInt(),'Б'.toInt(),'В'.toInt(),'Г'.toInt(),'Д'.toInt(),'Е'.toInt(),'Ё'.toInt(),'Ж'.toInt(),'З'.toInt(),'И'.toInt(),'Й'.toInt(),'К'.toInt(),'Л'.toInt(),'М'.toInt(),'Н'.toInt(),'О'.toInt(),'П'.toInt(),'Р'.toInt(),'М'.toInt(),'Т'.toInt(),'У'.toInt(),'Ф'.toInt(),'Х'.toInt(),'Ц'.toInt(),'Ч'.toInt(),'Ш'.toInt(),'Щ'.toInt(),'Ъ'.toInt(),'Ы'.toInt(),'Ь'.toInt(),'Э'.toInt(),'Ю'.toInt(),'Я'.toInt(),
    '1'.toInt(),'2'.toInt(),'3'.toInt(),'4'.toInt(),'5'.toInt(),'6'.toInt(),'7'.toInt(),'8'.toInt(),'9'.toInt(),'0'.toInt(),
    '.'.toInt(),','.toInt(),'-'.toInt(),' '.toInt(),'!'.toInt(),'?'.toInt()
)

val alphabetsMapNames = mapOf(
    1 to "rusLower",
    2 to "rusUpper",
    3 to "engLower",
    4 to "engUpper",
    5 to "numbers",
    6 to "spChars",
    7 to "united"
)

val alphabetsMap = mapOf(
    "rusLower" to rusLower,
    "rusUpper" to rusUpper,
    "engLower" to engLower,
    "engUpper" to engUpper,
    "numbers"  to numbers,
    "spChars"  to spChars,
    "united"   to united
)