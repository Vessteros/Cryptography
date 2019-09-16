package managers

import blocks.blockA.*
import helpers.*

object MainMenu : MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        if (alphabetsListNotEmpty) {
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Управыление подключаемыми алфавитами;\n")
            print("\t${Printer.ANSI_CYAN}{2}${Printer.ANSI_RESET} - Управление способом ввода данных;\n")
            print("\t${Printer.ANSI_CYAN}{3}${Printer.ANSI_RESET} - Выбор способа шифрования;\n")
            print("\t${Printer.ANSI_CYAN}{4}${Printer.ANSI_RESET} - Выполнить шифрование данных;\n")
        } else {
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Управыление подключенными алфавитами;\n")
        }

        print("\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Завершить выполнение программы;\n")

        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            when (command.toInt()) {
                1 -> AlphabetMenu.printMenuCommandList()
                2 -> {
                    if (!alphabetsListNotEmpty) {
                        this.commandTypeError()
                    } else {
                        InputTypeMenu.printMenuCommandList()
                    }
                }
                3 -> {
                    if (!alphabetsListNotEmpty) {
                        this.commandTypeError()
                    }
                }

                4 -> {
                    Algorithm3().startAlgorithmLogic()
                }

                0 -> {
                    Printer.delimiterLine()
                    print("Завершение работы.\n")
                    Printer.delimiterLine()
                }

                else -> {
                    this.commandTypeError()
                }
            }
        } else {
            commandTypeError()
        }
    }
}