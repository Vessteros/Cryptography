package blocks.blockA

import blocks.AlgorithmInterface
import helpers.concatenateArrayLists
import managers.MainMenu
import sources.Statics

class Algorithm3 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    private val alphabetList = arrayListOf<Int>()

    private val resAlphabet: HashMap<Char, Pair<Int, Int>> = hashMapOf()

    override fun encode() {
        mapAlphabet()

        parsedData.forEach { char: Int ->
            result += run {
                var value = Pair(0, 0)

                resAlphabet[char.toChar()]?.let { pair ->
                    value = pair
                }

                "${value.first} ${value.second}  "
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    private fun mapAlphabet() {
        alphabetList.addAll(concatenateArrayLists(*Statics.connectedAlphabets.toTypedArray()))

        val meh = when (true) {
            alphabetList.count() <= 36 -> 6
            else -> 7
        }

        alphabetList.forEach { char ->
            val pair = Pair(
                alphabetList.getCharPosition(char).div(meh),
                alphabetList.getCharPosition(char).rem(meh)
            )

            resAlphabet[char.toChar()] = pair
        }
    }
}