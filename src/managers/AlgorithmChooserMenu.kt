package managers

import helpers.Printer
import sources.Statics

object AlgorithmChooserMenu : MenuInterface {
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

        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться в главное меню;\n\n")

        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (commandIsValid(command)) when (command.toInt()) {
            0 -> MainMenu.printMenuCommandList()
            else -> {
                if (command.toInt() !in algorithmPull) {
                    commandTypeError()
                    manageCommand()
                    return
                }
                val algorithm = algorithmPull.getValue(command.toInt())

                if (algorithm !in Statics.algorithmMap) {
                    commandTypeError()
                    manageCommand()
                    return
                }

                Statics.algorithm = Statics.algorithmMap.getValue(algorithm)
                print("Выбранный алгоритм установлен: ${Printer.ANSI_GREEN}$algorithm${Printer.ANSI_RESET}\n")

                MainMenu.printMenuCommandList()
            }
        } else {
            commandTypeError()
        }
    }
}