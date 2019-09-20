package managers

import helpers.Printer
import sources.Statics

object InputTypesChooserMenu: MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()
        print("\t${Printer.ANSI_CYAN}{1}${Printer.ANSI_RESET} - Считать данные с текстового файла;\n")
        print("\t${Printer.ANSI_CYAN}{2}${Printer.ANSI_RESET} - Считывать данные через терминал;\n")
        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n\n")
        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            when(command.toInt()) {
                1 -> {
                    Statics.setFileInput()
                    InputTypeMenu.printMenuCommandList()
                }
                2 -> {
                    Statics.setTerminalInput()
                    InputTypeMenu.printMenuCommandList()
                }
                0 -> InputTypeMenu.printMenuCommandList()
                else -> commandTypeError()
            }
        }
    }
}