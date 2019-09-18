package managers

import helpers.Printer
import sources.Statics

object AlgorithmChooserMenu: MenuInterface {
    lateinit var algorithmPull: HashMap<Int, String>

    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        if (algorithmPull.isNullOrEmpty()) {
            print("${Printer.ANSI_RED}В пуле алгоритмов шифрования пока нет ни одного подключенного алгоритма.${Printer.ANSI_RESET}\n")
            Printer.delimiterLine()

            BlockChooserMenu.printMenuCommandList()

            return
        }

        algorithmPull.forEach { (i, algorithmName) ->
            print("\t${Printer.ANSI_CYAN}{$i}${Printer.ANSI_RESET} - Использовать $algorithmName;\n")
        }

        print("\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n")

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            val algorithm = algorithmPull.getValue(command.toInt())
            when(true) {
                algorithm.isNotEmpty() -> {
                    Statics.algorithm = Statics.algorithmMap.getValue(algorithm)
                    print("Выбранный алгоритм установлен: $algorithm\n")

                    print("${Statics.algorithm}")

                    MainMenu.printMenuCommandList()
                }

                else -> commandTypeError()
            }
        }
    }
}