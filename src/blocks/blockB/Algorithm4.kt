package blocks.blockB

import blocks.AlgorithmInterface
import managers.MainMenu
import sources.alphabetsMap

class Algorithm4 : AlgorithmInterface {
    override lateinit var data: String

    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    override fun encode() {
        parsedData.forEach { char: Int ->
            result += when (true) {
                char.toChar() == ' ' -> char.toChar()
                else -> {
                    val alphabet = alphabetsMap.getValue(getCharAlphabet(char))
                    val alphabetCount = alphabet.count()

                    val meh = (
                            getCharPosition(char, alphabet) + 2*getCharTextPosition(char, parsedData) + 15
                            ).rem(alphabetCount) + (alphabet.first() - 1)

                    meh.toChar()
                }
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }


}