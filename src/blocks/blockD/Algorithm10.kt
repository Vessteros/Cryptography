package blocks.blockD

import blocks.AlgorithmInterface
import helpers.Printer
import helpers.nullOverChecker
import managers.MainMenu

class Algorithm10 : AlgorithmInterface {
    // строка с данными
    override lateinit var data: String
    // Массив номеров букв в общем алфавите
    override var parsedData: ArrayList<Int> = arrayListOf()
    override var result: String = ""

    private val evalData = arrayListOf<Char>()

    private val matrix = arrayListOf<ArrayList<Char>>()
    private var matrixValues = Pair(0,0)

    override fun encode() {
        eval()
        createMatrix()
        matrix.forEach {
            println(it)
        }
        readEncode()

        printResult().also {
            clear()
        }

        MainMenu.printMenuCommandList()
    }

    override fun clear() {
        super.clear()
        matrix.removeAll(matrix)
        matrixValues = Pair(0,0)
        evalData.removeAll(evalData)
    }

    private fun readEncode() {
        for (i in matrixValues.second - 1 downTo 0 step 1) {
            val reversal = when ((matrixValues.second - 1).rem(2)) {
                0 -> i.rem(2) == 1
                else -> i.rem(2) == 0
            }

            readColumn(i, reversal)
        }
    }

    private fun readColumn(column: Int, reversal: Boolean) {
        if (reversal) {
            for (i in matrixValues.first - 1 downTo 0 step 1) {
                result += matrix[i][column]
            }
        } else {
            for (i in 0 until matrixValues.first step 1) {
                result += matrix[i][column]
            }
        }
    }

    private fun eval() = data.toCharArray().forEach {
        if (it == ' ') {
            return@forEach
        }

        evalData.add(it)
    }

    private fun createMatrix() {
        println("Введите размер матрицы (${Printer.ANSI_GREEN}например, 3:4${Printer.ANSI_RESET}):")
        getMatrixValues()

        for (i in 0 until matrixValues.first step 1) {
            val start = i*matrixValues.second
            val reversal = i.rem(2) == 1
            setMatrixRow(
                start,
                start + matrixValues.second - 1,
                reversal
            )
        }
    }

    private fun setMatrixRow(start: Int, end: Int, reversal: Boolean = false) {
        val row = arrayListOf<Char>()

        if (reversal) {
            for (i in end downTo start step 1) {
                row.add(evalData.getOrNull(i) ?: 'а')
            }
        } else {
            for (i in start .. end step 1) {
                row.add(evalData.getOrNull(i) ?: 'а')
            }
        }

        matrix.add(row)
    }

    private fun getMatrixValues(): Unit = readLine().nullOverChecker({
        val values = it!!.split(':')

        if (values.count() != 2) {
            Printer.delimiterLine()
            Printer.wrongStringType()
            Printer.delimiterLine()
            getMatrixValues()

            return@nullOverChecker
        }

        matrixValues = Pair(
            values[0].toInt(),
            values[1].toInt()
        )
    }, {
        Printer.emptyStringType()
        getMatrixValues()

        return@nullOverChecker
    }, {
        it == ""
    })
}