package sources

import blocks.AlgorithmInterface
import helpers.Printer
import managers.AlphabetMenu
import managers.DeleteAlphabetMenu

object Statics {
    val alphabets = arrayListOf<String>()

    var isFileInput = false

    lateinit var algoritm: AlgorithmInterface

    fun addAlphabet(alphabetNum: Int) {
        val alphabetName = alphabetsMapNames.getValue(alphabetNum)
        if (alphabetName !in alphabets) {
            alphabets.add(alphabetName)
            print("Алфавит {${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET}} добавлен в пул.\n")
        } else {
            print("${Printer.ANSI_RED}Выбранный алфавит уже находится в пуле.${Printer.ANSI_RESET}\n")
        }

        Printer.delimiterLine()
    }

    fun deleteAlphabet(alphabetName: String) {
        if (alphabetName in alphabets) {
            alphabets.remove(alphabetName)
            print("Алфавит {${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET}} был удален из пула.\n")

            if (alphabets.isNotEmpty()) {
                print("\n")
                DeleteAlphabetMenu.printAlphabetList()
                print("\n")
            } else {
                AlphabetMenu.printMenuCommandList()
            }
        } else {
            print("${Printer.ANSI_RED}Веденное значение не является названием алфавита из пула,\nлибо известной системе командой.${Printer.ANSI_RESET}\n")
        }

        Printer.delimiterLine()
    }

    fun setFileInput() {
        isFileInput = true
    }

    fun setTerminalInput() {
        isFileInput = false
    }
}