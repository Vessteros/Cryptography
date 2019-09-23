package blocks.blockA

import blocks.AlgorithmInterface
import managers.MainMenu

class Algorithm1 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    override fun encode() {
        parsedData.forEach { char: Int ->
            result += run {
                val alphabet = getCharAlphabet(char)

                val meh = (alphabet.getAlphabetCountable() - alphabet.getCharPosition(char) + 1) // формула алгоритма

                alphabet[meh].toChar()

            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }
}