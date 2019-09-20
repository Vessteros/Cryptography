package managers

import helpers.*
import sources.Statics

object MainMenu : MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        if (alphabetsListNotEmpty) {
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Управление подключаемыми алфавитами;\n")
            print("\t${Printer.ANSI_CYAN}{2}${Printer.ANSI_RESET} - Управление способом ввода данных;\n")
            print("\t${Printer.ANSI_CYAN}{3}${Printer.ANSI_RESET} - Выбор способа шифрования;\n")
            print("\t${Printer.ANSI_CYAN}{4}${Printer.ANSI_RESET} - Выполнить шифрование данных;\n")
            print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Завершить выполнение программы;\n\n")
        } else {
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Управление подключенными алфавитами;\n")
            print("\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Завершить выполнение программы;\n\n")
        }

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
                    } else {
                        BlockChooserMenu.printMenuCommandList()
                    }
                }

                4 -> {
                    Statics.algorithm.startAlgorithmLogic()
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