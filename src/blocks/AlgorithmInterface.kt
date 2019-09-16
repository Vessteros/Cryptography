package blocks

import helpers.Printer
import managers.MainMenu
import sources.Statics
import sources.alphabetsMap

interface AlgorithmInterface {
    var data: String
    var parsedData: ArrayList<Int>

    var result: String

    fun getFromFile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun startAlgorithmLogic() {
        when (Statics.isFileInput) {
            true -> getFromFile()
            false -> scanFromTerminal()
        }

        try {
            encode()
        } catch (e: Exception) {
            print("${Printer.ANSI_RED}${e.message}${Printer.ANSI_RESET}\n")
            MainMenu.printMenuCommandList()
        }
    }

    fun scanFromTerminal() {
        Printer.delimiterLine()
        Printer.cryptString()

        data = readLine()!!

        if (data == "") {
            Printer.emptyStringType()
            scanFromTerminal()
            return
        }

        parseData()
    }

    fun parseData() {
        parsedData = ArrayList(
            data
                .toCharArray()
                .toList()
                .map {
                    it.toInt()
                }
        )
    }

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
                "В веденной последовательности присутствует символ из неподключенного алфавита.\n" +
                "Подключите дополнительные алфавиты, либо проверьте введенную последовательность."
            )
        }

        return result
    }

    fun getCharPosition(char: Int, alphabet: Iterable<Int>) = char - (alphabet.first() - 1)

    fun printResult() {
        print("${Printer.ANSI_BLUE}Результат шифрования: ${Printer.ANSI_RESET}\n${Printer.ANSI_PURPLE}$result${Printer.ANSI_RESET}\n")
    }
}