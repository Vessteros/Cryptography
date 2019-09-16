package blocks.blockA

import blocks.AlgorithmInterface
import sources.Statics
import sources.alphabetsMap

class Algorithm3 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    var parsedAlphabets: Map<String, Map<Char, Pair<Int, Int>>> = mapOf(
             "asd" to mapOf(
                'd' to Pair(1,2)
            )
    )

    override fun encode() {
        print("${Statics.alphabets}")
        mapAlphabets()
//        parsedData.forEach { char: Int ->
//            result += when(true) {
//                char.toChar() == ' ' -> char.toChar()
//                else -> {
//                    val alphabet = alphabetsMap.getValue(getCharAlphabet(char))
//                }
//            }
//        }
    }

    fun mapAlphabets() {
        Statics.alphabets.forEach { alphabetName ->
            parseAlphabet(alphabetName)
        }
    }

    fun parseAlphabet(alphabetName: String) {
        val alphabet = alphabetsMap.getValue(alphabetName)

//        parsedAlphabets[alphabetName] =
    }
}