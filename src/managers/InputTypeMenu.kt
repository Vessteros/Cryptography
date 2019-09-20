package managers

import helpers.Printer
import sources.Statics

object InputTypeMenu: MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()
        print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Выбор способа ввода данных;\n")

        if (Statics.isFileInput) {
            print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Выбор текстового файла;\n")
        }

        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться в главное меню;\n\n")
        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            when(command.toInt()) {
                1 -> InputTypesChooserMenu.printMenuCommandList()
                2 -> {
                    if (!Statics.isFileInput) {
                        commandTypeError()
                    }
                }
                0 -> MainMenu.printMenuCommandList()
                else -> commandTypeError()
            }
        }
    }
}