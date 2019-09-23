package blocks

import helpers.Printer
import managers.MainMenu
import sources.Statics

const val errorChar = "В веденной последовательности присутствует символ из неподключенного алфавита.\n" +
        "Подключите дополнительные алфавиты, либо проверьте введенную последовательность."

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

    fun getCharAlphabet(char: Int): ArrayList<Int> {
        var result = arrayListOf<Int>()
        // костыль
        Statics.connectedAlphabets.forEach { alphabet ->
            if (char in alphabet) {
                result = alphabet
                return@forEach
            }
        }

        if (result.isNullOrEmpty()) {
            throw Exception(errorChar)
        }

        return result
    }

    fun <T> ArrayList<T>.getCharPosition(char: T) = this.indexOf(char)

    fun <R, T : ArrayList<R>> T.getAlphabetCountable() = this.count() - 1

    fun printResult() {
        print("${Printer.ANSI_BLUE}Результат шифрования: ${Printer.ANSI_RESET}\n${Printer.ANSI_PURPLE}$result${Printer.ANSI_RESET}\n")
        result = ""
    }
}