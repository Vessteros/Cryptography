package managers

import helpers.Printer
import sources.Statics

interface MenuInterface {
    val alphabetsListNotEmpty
        get() = Statics.connectedAlphabets.isNotEmpty()

    fun printMenuCommandList()

    fun manageCommand()

    fun commandTypeError() {
        Printer.commandTypeError()
        Printer.delimiterLine()
        manageCommand()
    }

    fun validateCommand(command: String): Boolean = try {
        command.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}