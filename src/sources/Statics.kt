package sources

import blocks.AlgorithmInterface
import blocks.blockA.*
import helpers.Printer
import managers.AlphabetMenu
import managers.DeleteAlphabetMenu

object Statics {
    val connectedAlphabets = arrayListOf<ArrayList<Int>>()

    var isFileInput = false

    val fileMap = hashMapOf(
        "test.txt" to "../files/"
    )

    var chosenFile = "test.txt"

    val algorithmPull: HashMap<String, HashMap<Int, String>> = hashMapOf(
        "A" to hashMapOf(
            1 to "Алгоритм 1",
            2 to "Алгоритм 2",
            3 to "Алгоритм 3"
        ),
        "B" to hashMapOf(
            1 to "Алгоритм 4",
            2 to "Алгоритм 5"
        )
    )

    val algorithmMap = hashMapOf(
        "Алгоритм 1" to Algorithm1(),
        "Алгоритм 2" to Algorithm2(),
        "Алгоритм 3" to Algorithm3(),
        "Алгоритм 4" to blocks.blockB.Algorithm4(),
        "Алгоритм 5" to blocks.blockB.Algorithm5()
    )

    var algorithm: AlgorithmInterface? = null

    fun addAlphabet(alphabetNum: Int) {
        val alphabetName = alphabetsMapNames.getValue(alphabetNum)
        if (alphabetsMap.getValue(alphabetName) !in connectedAlphabets) {
            connectedAlphabets.add(alphabetsMap.getValue(alphabetName))
            print("Алфавит {${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET}} добавлен в пул.\n")
        } else {
            print("${Printer.ANSI_RED}Выбранный алфавит уже находится в пуле.${Printer.ANSI_RESET}\n")
        }

        Printer.delimiterLine()
    }

    fun deleteAlphabet(alphabetName: String) {
        if (alphabetsMap.getValue(alphabetName) in connectedAlphabets) {
            connectedAlphabets.remove(alphabetsMap.getValue(alphabetName))
            print("Алфавит {${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET}} был удален из пула.\n")

            if (connectedAlphabets.isNotEmpty()) {
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