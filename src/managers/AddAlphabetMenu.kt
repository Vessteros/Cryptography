package managers

import helpers.*
import sources.Statics
import sources.alphabetsMap
import sources.alphabetsMapNames

object AddAlphabetMenu : MenuInterface{

    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        alphabetsMapNames.forEach { (i, alphabetName) ->
            print("\t${Printer.ANSI_CYAN}{$i}${Printer.ANSI_RESET} - Добавить ${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET} в пул;")

            if (alphabetsMap.getValue(alphabetName) in Statics.connectedAlphabets) {
                print(" (${Printer.ANSI_GREEN}Добавлено${Printer.ANSI_RESET})")
            }

            print("\n")
        }

        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n\n")
        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (commandIsValid(command)) {
            when (val i = command.toInt()) {
                0 -> AlphabetMenu.printMenuCommandList()
                1,2,3,4 -> {
                    Statics.addAlphabet(i)
                    manageCommand()
                    return
                }
                else -> commandTypeError()
            }
        } else {
            commandTypeError()
        }

    }

}