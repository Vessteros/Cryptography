package blocks.blockA

import blocks.AlgorithmInterface
import helpers.Printer.errorChar
import managers.MainMenu
import sources.Statics

class Algorithm3 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    private var parsedAlphabets: ArrayList<HashMap<Char, Pair<Int, Int>>> = arrayListOf()

    override fun encode() {
        mapAlphabets()
        parsedData.forEach { char: Int ->
            result += run {
                var value = Pair(0, 0)

                parsedAlphabets.forEach { map ->
                    map[char.toChar()].let { pair ->
                        if (pair == null) {
                            throw Exception(errorChar)
                        }

                        value = pair
                    }
                }

                "${value.first}${value.second} "
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    private fun mapAlphabets() {
        Statics.connectedAlphabets.forEach { alphabetName ->
            mapAlphabet(alphabetName)
        }
    }

    private fun mapAlphabet(alphabet: ArrayList<Int>) {
        val meh = when (true) {
            alphabet.count() <= 36 -> 6
            else -> 7
        }

        val parsedAlphabet = hashMapOf<Char, Pair<Int, Int>>()

        alphabet.forEach { char ->
            val pair = Pair(
                alphabet.getCharPosition(char).div(meh),
                alphabet.getCharPosition(char).rem(meh)
            )

            parsedAlphabet[char.toChar()] = pair
        }

        parsedAlphabets.add(parsedAlphabet)
    }
}