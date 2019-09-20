package managers

import helpers.Printer
import sources.Statics

object BlockChooserMenu : MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        if (Statics.algorithmPull.isNullOrEmpty()) {
            print("${Printer.ANSI_RED}В пуле алгоритмов шифрования пока нет ни одного подключенного блока алгоритмов.${Printer.ANSI_RESET}\n")
            Printer.delimiterLine()
            MainMenu.printMenuCommandList()

            return
        }

        Statics.algorithmPull.forEach { (blockName, _) ->
            print("\t${Printer.ANSI_CYAN}{$blockName}${Printer.ANSI_RESET} - перейти в Блок $blockName;\n")
        }

        print("\n\t${Printer.ANSI_CYAN}{0}${Printer.ANSI_RESET} - Вернуться назад;\n\n")

        Printer.delimiterLine()

        manageCommand()
    }

    override fun manageCommand() {
        Printer.chooseCommand()

        val command = readLine()!!

        if (validateCommand(command)) {
            val copy = command.toInt()
            if (copy == 0) {
                MainMenu.printMenuCommandList()
            } else {
                commandTypeError()
            }
        } else {
            when (command) {
                "A" -> {
                    AlgorithmChooserMenu.algorithmPull = Statics.algorithmPull.getValue(command)
                    AlgorithmChooserMenu.printMenuCommandList()
                }
                else -> commandTypeError()
            }
        }
    }
}