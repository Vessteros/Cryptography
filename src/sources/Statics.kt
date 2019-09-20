package sources

import blocks.AlgorithmInterface
import blocks.blockA.*
import helpers.Printer
import managers.AlphabetMenu
import managers.DeleteAlphabetMenu

object Statics {
    val alphabets = arrayListOf<String>()

    var isFileInput = false

    val algorithmPull: HashMap<String, HashMap<Int, String>> = hashMapOf(
        "A" to hashMapOf(
            1 to "Алгоритм 1",
            2 to "Алгоритм 2",
            3 to "Алгоритм 3"
        )
    )

    val algorithmMap = hashMapOf(
        "Алгоритм 1" to Algorithm1(),
        "Алгоритм 2" to Algorithm2(),
        "Алгоритм 3" to Algorithm3()
    )

    lateinit var algorithm: AlgorithmInterface

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