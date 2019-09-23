package blocks.blockA

import blocks.AlgorithmInterface
import managers.MainMenu
import sources.Statics
import sources.alphabetsMap

class Algorithm3 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    private lateinit var parsedAlphabets: HashMap<String, HashMap<Char, Pair<Int, Int>>>

    override fun encode() {
        mapAlphabets()
        parsedData.forEach { char: Int ->
            result += when(true) {
                char.toChar() == ' ' -> char.toChar()
                else -> {
                    var value = Pair(0,0)

                    parsedAlphabets.forEach { (_, map) ->
                        if (map.containsKey(char.toChar()) && map[char.toChar()] != null) {
                           value = map[char.toChar()]!!
                        }
                    }

                    "${value.first}${value.second} "
                }
            }
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    private fun mapAlphabets() {
        parsedAlphabets = hashMapOf()

        Statics.alphabets.forEach { alphabetName ->
            mapAlphabet(alphabetName)
        }
    }

    private fun mapAlphabet(alphabetName: String) {
        val alphabet = alphabetsMap.getValue(alphabetName)

        parsedAlphabets[alphabetName] = hashMapOf()

        val meh = when(true) {
            alphabet.count() <= 36 -> 6
            else -> 7
        }

        alphabet.forEach { char ->
            val pair = Pair(
                getCharPosition(char, alphabet).div(meh) + 1,
                getCharPosition(char, alphabet).rem(meh)
            )

            parsedAlphabets[alphabetName]!![char.toChar()] = pair
        }
    }
}