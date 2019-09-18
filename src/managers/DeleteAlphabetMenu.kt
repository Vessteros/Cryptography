package managers

import helpers.Printer
import sources.Statics

object DeleteAlphabetMenu : MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        print("Для удаления введите название алфавита в системе из списка:\n\n")

        printAlphabetList()

        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n")

        print("\nКоманда {${Printer.ANSI_CYAN}back${Printer.ANSI_RESET}} так же вернет вас в предыдущее меню.\n")
        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            val copy = command.toInt()
            if (copy == 0 ) {
                AlphabetMenu.printMenuCommandList()
            } else {
                commandTypeError()
            }
        } else {
            when (command) {
                "back" -> AlphabetMenu.printMenuCommandList()
                else -> {
                    Statics.deleteAlphabet(command)
                    manageCommand()
                }
            }
        }


    }

    fun printAlphabetList() = Statics.alphabets.forEach { alphabetName ->
        print("\t${Printer.ANSI_CYAN}> ${Printer.ANSI_GREEN}$alphabetName${Printer.ANSI_RESET}\n")
    }
}