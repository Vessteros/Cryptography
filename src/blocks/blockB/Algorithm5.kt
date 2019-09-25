package blocks.blockB

import blocks.AlgorithmInterface
import helpers.*
import sources.Statics

class Algorithm5 : AlgorithmInterface {
    override lateinit var data: String

    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    var keyWord: String = ""

    lateinit var parsedKey: ArrayList<Int>

    var table: ArrayList<ArrayList<Int>> = arrayListOf()

    private val alphabet: ArrayList<Int>
        get() = concatenateArrayLists(*Statics.connectedAlphabets.toTypedArray())

    override fun scanFromTerminal() {
        Printer.delimiterLine()
        Printer.cryptString()

        readLine().nullOverChecker({
            data = it!!
        }, {
            Printer.emptyStringType()
            scanFromTerminal()

            return@nullOverChecker
        }, {
            it == ""
        })

        parseData()
        scanKeyWord()
    }

    private fun scanKeyWord() {
        Printer.delimiterLine()
        Printer.printKeyWord()

        readLine().nullOverChecker({
            keyWord = it!!
        }, {
            Printer.emptyStringType()
            scanFromTerminal()

            return@nullOverChecker
        }, {
            it == ""
        })
    }

    override fun encode() {
        val keyString = setKeyWordString()

        setTable()
        print("1 - $keyString\n")
        table.forEach { row ->
            print("2 - ${row}\n")
        }
    }

    private fun setKeyWordString(): ArrayList<Int> {
        val keyString = arrayListOf<Int>()
        val countData = parsedData.count()

        parsedKey = keyWord.convertToIntArray()

        val countKey = parsedKey.count()

        if (countKey > countData) {
            parsedKey.forEachIndexed { index, keyChar ->
                keyString.add(keyChar)

                if (index == countData) return@forEachIndexed
            }
        } else {
            val div = countData.div(countKey)
            val mod = countData.rem(countKey)
            var i = 0

            while (i < div) {
                keyString.addAll(parsedKey)
                i++
            }

            i = 0
            while (i < mod) {
                keyString.add(parsedKey[i])
            }
        }

        return keyString
    }

    private fun setTable() {
        table.add(alphabet)

        var i = 1
        parsedKey.forEach { char: Int ->
            val row = arrayListOf(char)
            row.addAll(setAlphabetWithStep(i))

            table.add(row)
            i++
        }
    }

    private fun setAlphabetWithStep(step: Int): ArrayList<Int> {
        val meh = arrayListOf<Int>()

        var start = -step // после того, как цикл подойдет к шндексу превышающему количество элементов алфавита,
        // надо начинать с 0
        alphabet.forEach { char ->
            var index = alphabet.indexOf(char) + step

            if (index < alphabet.count()) {
                meh.add(alphabet[index])
            } else {
                index = start + step
                meh.add(alphabet[index])
                start++
            }
        }

        return meh
    }
}