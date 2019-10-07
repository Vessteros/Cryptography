package blocks.blockC

import blocks.AlgorithmInterface
import helpers.Printer
import helpers.nullOverChecker

class Algorithm8 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>
    override var result: String = "Ошибка шифрования"

    private lateinit var matrixValues: Pair<Int, Int>
    private val keyMatrix: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun encode() {
        getKeyMatrix()
        print(keyMatrix)
    }

    private fun getKeyMatrix() {
        println("Введите размер матрицы-ключа (${Printer.ANSI_GREEN}например, 3:4${Printer.ANSI_RESET}):")

        readLine().nullOverChecker({
            val values = it!!.split(':')

            if (values.count() != 2) {
                Printer.wrongStringType()
                Printer.delimiterLine()
                getKeyMatrix()

                return@nullOverChecker
            }

            matrixValues = Pair(
                values[0].toInt(),
                values[1].toInt()
            )
        },{
            Printer.emptyStringType()
            getKeyMatrix()

            return@nullOverChecker
        }, {
            it == ""
        })

        var i = 0
        while (i < matrixValues.first) {
            println("Введите через запятую числа строки матрицы ключа: ")
            getMatrixLine()
            i++
        }
    }

    private fun getMatrixLine() {
        readLine().nullOverChecker({ string ->
            val values = string!!.split(',')

            if (values.count() != matrixValues.second) {
                Printer.wrongStringType()
                Printer.delimiterLine()
                getMatrixLine()

                return@nullOverChecker
            }

            keyMatrix.add(ArrayList(values.map { it.toInt() }))
        },{
            Printer.emptyStringType()
            Printer.delimiterLine()
            getKeyMatrix()

            return@nullOverChecker
        }, {
            it == ""
        })
    }
}