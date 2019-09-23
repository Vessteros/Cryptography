package managers

import helpers.*
import sources.Statics
import sources.alphabetsMap

object AlphabetMenu : MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        if (alphabetsListNotEmpty) {
            print("Подключенные алфавиты:\n\t{ ")
            var i = 1
            Statics.connectedAlphabets.forEach { alphabetName ->
                print("${Printer.ANSI_GREEN}$alphabetName ${Printer.ANSI_RESET}")
                if (i % 3 == 0 && i < alphabetsMap.count()) {
                    print("}\n\t{ ")
                }
                i++
            }
            print("}\n\n")

            Printer.commandList()
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Добавление алфавита в пул;\n")
            print("\t${Printer.ANSI_CYAN}{2}${Printer.ANSI_RESET} - Удалить алфавит из пула;\n")
            print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться в главное меню;\n\n")
        } else {
            Printer.commandList()
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Добавление алфавита в пул;\n")
            print("\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться в главное меню;\n\n")
        }

        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (MainMenu.validateCommand(command)) {
            when (command.toInt()) {
                1 -> AddAlphabetMenu.printMenuCommandList()
                2 -> {
                    if (!alphabetsListNotEmpty) {
                        commandTypeError()
                    } else {
                        DeleteAlphabetMenu.printMenuCommandList()
                    }
                }
                0 -> MainMenu.printMenuCommandList()
                else -> commandTypeError()
            }
        } else {
            commandTypeError()
        }
    }
}