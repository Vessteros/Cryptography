package blocks

import helpers.*
import helpers.Printer
import helpers.Printer.errorChar
import managers.MainMenu
import sources.Statics
import java.io.File

interface AlgorithmInterface {
    var data: String
    var parsedData: ArrayList<Int>

    var result: String

    fun startAlgorithmLogic() {
        when (Statics.isFileInput) {
            true -> getFromFile()
            false -> scanFromTerminal()
        }

//        try {
            encode()
//        } catch (e: Exception) {
//            print("${Printer.ANSI_RED}${e.message}${Printer.ANSI_RESET}\n")
//            MainMenu.printMenuCommandList()
//        }
    }

    fun getFromFile() {
        Printer.delimiterLine()
        Printer.readFromFile()

        data = File(Statics.chosenFile)
            .readText(Charsets.UTF_8)

        parseData()

        println(parsedData.map { it.toChar() }.joinToString(""))
        Printer.delimiterLine()
    }

    fun scanFromTerminal() {
        Printer.delimiterLine()
        Printer.cryptString()

        readLine().nullOverChecker({
            data = it!!
        }, {
            Printer.emptyStringType()
            scanFromTerminal()
            return@nullOverChecker
        }, {
            it == ""
        })

        parseData()
    }

    fun parseData() {
        parsedData = data.convertToIntArray()
    }

    fun encode()

    fun getCharAlphabet(char: Int): ArrayList<Int> {
        val result = Statics.connectedAlphabets.first {
            char in it
        }

        if (result.isNullOrEmpty()) {
            throw Exception(errorChar)
        }

        return result
    }

    fun <T> ArrayList<T>.getCharPosition(char: T) = this.indexOf(char) + 1

    fun <R, T : ArrayList<R>> T.getAlphabetCountable() = this.count() - 1

    fun printResult() {
        print("${Printer.ANSI_BLUE}Результат шифрования: ${Printer.ANSI_RESET}\n${Printer.ANSI_PURPLE}$result${Printer.ANSI_RESET}\n")
        result = ""
    }
}