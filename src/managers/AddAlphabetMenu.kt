package managers

import helpers.*
import sources.Statics
import sources.alphabetsMapNames

object AddAlphabetMenu : MenuInterface{

    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        alphabetsMapNames.forEach { (i, alphabetName) ->
            print("\t${Printer.ANSI_CYAN}{$i}${Printer.ANSI_RESET} - Добавить $alphabetName в пул;")

            if (alphabetName in Statics.alphabets) {
                print(" (${Printer.ANSI_GREEN}Добавлено${Printer.ANSI_RESET})")
            }

            print("\n")
        }

        print("\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n\n")
        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            when (val i = command.toInt()) {
                0 -> AlphabetMenu.printMenuCommandList()
                1,2,3,4,5,6 -> {
                    Statics.addAlphabet(i)
                    manageCommand()
                }
                else -> commandTypeError()
            }
        } else {
            commandTypeError()
        }

    }

}