package blocks.blockA

import blocks.AlgorithmInterface
import managers.MainMenu
import sources.*

class Algorithm1 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    override fun encode() {
        parsedData.forEach { char: Int ->
            result += when(true) {
                char.toChar() == ' ' -> char.toChar()
                else -> {
                    val alphabet = alphabetsMap.getValue(getCharAlphabet(char))

                    val meh = (
                            alphabet.count() - getCharPosition(char, alphabet) + 1 // формула алгоритма
                            ) + (alphabet.first() - 1)

                    meh.toChar()
                }
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }
}