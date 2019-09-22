package helpers

object Printer {
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLACK = "\u001B[30m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITE = "\u001B[37m"

    fun commandTypeError() = print("\n${ANSI_RED}Введена не известная системе команда.${ANSI_RESET}\n")
    fun delimiterLine() = print("------------------------------------------------------------------\n")
    fun commandList() = print("Список исполняемых команд:\n\n")
    fun chooseCommand() = print("${ANSI_YELLOW}Выполнить команду$ANSI_RESET: ")
    fun cryptString() = print("${ANSI_BLUE}Введите строку для шифрования:$ANSI_RESET\n")
    fun printKeyWord() = print("${ANSI_BLUE}Введите ключ шифрования:$ANSI_RESET\n")
    fun emptyStringType() = print("${ANSI_RED}Была введена пустая строка.${ANSI_RESET}\n")
}