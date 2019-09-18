package managers

import helpers.Printer
import sources.Statics

object BlockChooserMenu: MenuInterface {
    override fun printMenuCommandList() {
        Printer.delimiterLine()
        Printer.commandList()

        if (Statics.algorithmPull.isNullOrEmpty()) {
            print("${Printer.ANSI_RED}В пуле алгоритмов шифрования пока нет ни одного подключенного алгоритма.${Printer.ANSI_RESET}\n")
            Printer.delimiterLine()
            MainMenu.printMenuCommandList()

            return
        }

        Statics.algorithmPull.forEach { (blockName, _) ->
            print("\t${Printer.ANSI_CYAN}{$blockName}${Printer.ANSI_RESET} - перейти в Блок $blockName;\n")
        }

        manageCommand()
    }

    override fun manageCommand() {

    }
}