package blocks.blockB

import blocks.AlgorithmInterface
import helpers.Printer
import sources.united

class Algorithm5 : AlgorithmInterface {
    override lateinit var data: String

    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    var keyWord: String = ""
    lateinit var parsedKey: ArrayList<Int>
    var table: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun scanFromTerminal() {
        Printer.delimiterLine()
        Printer.cryptString()

        data = readLine()!!

        if (data == "") {
            Printer.emptyStringType()
            scanFromTerminal()
            return
        }

        parseData()
        scanKeyWord()
    }

    private fun scanKeyWord() {
        Printer.delimiterLine()
        Printer.printKeyWord()

        keyWord = readLine()!!

        if (keyWord == "") {
            Printer.emptyStringType()
            scanFromTerminal()
            return
        }
    }

    override fun encode() {
        val keyString = setKeyWordString()
        setTable()
        table.forEach { row ->
            print("${row}\n")
        }
    }

    private fun setKeyWordString(): ArrayList<Int> {
        val keyString = arrayListOf<Int>()
        val countData = parsedData.count()

        parsedKey = ArrayList(
            keyWord
                .toCharArray()
                .toList()
                .map {
                    it.toInt()
                }
        )
        val countKey = parsedKey.count()

        if (countKey > countData) {
            var i = 0
            parsedKey.forEach { keyChar ->
                keyString.add(keyChar)
                i++

                if (i == countData) {
                    return@forEach
                }
            }
        } else {
            val mod = countData.rem(countKey)
            val div = countData.div(countKey)
            var i = 1

            while (i <= div) {
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
        table.add(ArrayList(united))
        var i = 1
        parsedKey.forEach { char: Int ->
            val row = arrayListOf(char)
            row.addAll(setAlphabetWithStep(i))

            table.add(row)
            i++
        }
    }

    private fun setAlphabetWithStep(step: Int): ArrayList<Int> {
        var meh = arrayListOf<Int>()
        val alphabet: ArrayList<Int> = united

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