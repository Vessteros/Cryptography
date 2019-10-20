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
            result += run {
                val alphabet = getCharAlphabet(char)
                val alphabetCount = alphabet.count()

                val meh = (alphabet.getCharPosition(char) + parsedData.getCharPosition(char) + 1).rem(alphabetCount)

                alphabet[meh].toChar()
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }


}