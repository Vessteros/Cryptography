package blocks.blockA

import blocks.AlgorithmInterface
import helpers.Printer
import managers.MainMenu
import sources.Statics
import sources.*
import java.lang.Exception

class Algorithm1 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    override fun startAlgorithmLogic() {
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

    override fun getFromFile() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scanFromTerminal() {
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

    override fun parseData() {
        parsedData = ArrayList(
            data
                .toCharArray()
                .toList()
                .map {
                    it.toInt()
                }
        )

    }

    override fun encode() {
        parsedData.forEach { char ->
            when(true) {
                char.toString() == " " -> {
                    result += char
                }
                else -> {
                    val alphabet = alphabetsMap.getValue(getCharAlphabet(char))

                    val meh = (alphabet.count() - (char - alphabet.first()) + 1 + alphabet.first())
                    print("${meh.toChar()} -> $meh")
//                    result += (alphabet.count() - (char - alphabet.first() + 1) + 1).toChar()
                }
            }
        }

//        print(result)
    }
}