package blocks.blockC

import blocks.AlgorithmInterface
import helpers.*
import managers.MainMenu
import sources.Statics

class Algorithm8 : AlgorithmInterface {
    // строка с данными
    override lateinit var data: String
    // Массив номеров букв в общем алфавите
    override var parsedData: ArrayList<Int> = arrayListOf()
    // Строка номеров букв, разбитая на вектора
    private val structuredData: ArrayList<ArrayList<Int>> = arrayListOf()
    override var result: String = ""

    private lateinit var matrixValues: Pair<Int, Int>
    private val keyMatrix: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun parseData() {
        val alphabet = hashMapOf<Int, Char>()
        concatenateArrayLists(
            *Statics.connectedAlphabets.toTypedArray()
        ).forEachIndexed { key, value ->
            alphabet[key] = value.toChar()
        }
        println(alphabet)
        data.toCharArray().forEach {
            parsedData.add(alphabet.getKeyByValue(it))
        }
    }

    override fun encode() {
        getKeyMatrix()
        structData()
        structuredData.forEach {
            it.vectorMultiply()
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    private fun <T : ArrayList<Int>> T.vectorMultiply() {
        keyMatrix.forEach { matrixRow ->
            var multiply = 0
            result += ":"
            matrixRow.forEachIndexed { i, value ->
                multiply += value * this[i]
            }
            result += "$multiply"
        }
    }

    private fun structData() {
        structuredData.add(arrayListOf())
        parsedData.forEachIndexed { _, char ->
            if (structuredData.last().count() == matrixValues.second) {
                structuredData.add(arrayListOf())
            }

            structuredData.last().add(char)
        }

        val diff = matrixValues.second - structuredData.last().count()

        if (diff > 0) {
            var i = 0
            while (i < diff) {
                structuredData.last().add(Statics.connectedAlphabets.first().first())
                i++
            }
        }
    }

    private fun getKeyMatrix() {
        println("Введите размер матрицы-ключа (${Printer.ANSI_GREEN}например, 3:4${Printer.ANSI_RESET}):")

        readLine().nullOverChecker({
            val values = it!!.split(':')

            if (values.count() != 2) {
                Printer.delimiterLine()
                Printer.wrongStringType()
                Printer.delimiterLine()
                getKeyMatrix()

                return@nullOverChecker
            }

            matrixValues = Pair(
                values[0].toInt(),
                values[1].toInt()
            )
        }, {
            Printer.emptyStringType()
            getKeyMatrix()

            return@nullOverChecker
        }, {
            it == ""
        })

        var i = 0
        while (i < matrixValues.first) {
            getMatrixLine()
            i++
        }
    }

    private fun getMatrixLine() {
        println("Введите через запятую числа строки матрицы ключа: ")
        readLine().nullOverChecker({ string ->
            val values = string!!.split(',')

            if (values.count() != matrixValues.second) {
                Printer.delimiterLine()
                Printer.wrongStringType()
                Printer.delimiterLine()
                getMatrixLine()

                return@nullOverChecker
            }

            keyMatrix.add(ArrayList(values.map { it.toInt() }))
        }, {
            Printer.delimiterLine()
            Printer.emptyStringType()
            Printer.delimiterLine()
            getMatrixLine()

            return@nullOverChecker
        }, {
            it == ""
        })
    }
}