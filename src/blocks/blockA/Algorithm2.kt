package blocks.blockA

import blocks.AlgorithmInterface
import managers.MainMenu
import sources.alphabetsMap

class Algorithm2 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    override fun encode() {
        parsedData.forEach { char: Int ->
            result += when(true) {
                char.toChar() == ' ' -> char.toChar()
                else -> {
                    val alphabet = alphabetsMap.getValue(getCharAlphabet(char))

                    val meh = (getCharPosition(char, alphabet) + 3).rem(alphabet.count()) + (alphabet.first() - 1)

                    print("$meh => ${meh.toChar()}\n")

                    meh.toChar()
                }
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }
}