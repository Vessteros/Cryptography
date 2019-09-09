package blocks

import sources.Statics
import sources.alphabetsMap

interface AlgorithmInterface {
    var data: String
    var parsedData: ArrayList<Int>

    var result: String

    fun getFromFile()

    fun startAlgorithmLogic()

    fun scanFromTerminal()

    fun parseData()

    fun encode()

    fun getCharAlphabet(char: Int): String {
        var result = ""
        // костыль
        Statics.alphabets.forEach { alphabet ->
            if (char in alphabetsMap.getValue(alphabet)) {
                result = alphabet
                return@forEach
            }
        }

        if (result == "") {
            throw Exception(
                "В веденной последовательности присутствует символ из не подключенного алфавита.\n" +
                        "Подключите дополнительные алфавиты, либо проверьте введенную последовательность."
            )
        }

        return result
    }
}