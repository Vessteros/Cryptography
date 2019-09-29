package blocks.blockB

import blocks.AlgorithmInterface
import helpers.*
import managers.MainMenu
import sources.Statics

open class Algorithm5 : AlgorithmInterface {
    override lateinit var data: String

    override lateinit var parsedData: ArrayList<Int>

    override var result: String = ""

    protected var keyWord: String = ""

    protected lateinit var parsedKey: ArrayList<Int>

    protected var table: ArrayList<ArrayList<Int>> = arrayListOf()

    protected val alphabet: ArrayList<Int>
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

    protected fun scanKeyWord() {
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

        parsedData.forEach { char ->
            result += table.first {
                it.first() == keyString[parsedData.indexOf(char)]
            }[table.first().indexOf(char)].toChar()
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    protected fun setKeyWordString(): ArrayList<Int> {
        val keyString = arrayListOf<Int>()
        val countData = parsedData.count()

        parsedKey = keyWord.convertToIntArray()

        val countKey = parsedKey.count()

        if (countKey > countData) {
            parsedKey.forEachIndexed loop@{ index, keyChar ->
                keyString.add(keyChar)

                if ((countData - index) == 1) {
                    return keyString
                }
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
                i++
            }
        }

        return keyString
    }

    protected open fun setTable() {
        table.add(alphabet)

        parsedKey.forEach { char: Int ->
            val row = arrayListOf(char)
            row.addAll(setAlphabetWithStep(char))

            table.add(row)
        }
    }

    protected open fun setAlphabetWithStep(char: Int): ArrayList<Int> {
        val meh = arrayListOf<Int>()
        val charPos = alphabet.indexOf(char)

        var i = 1
        var start = 0
        alphabet.forEach { _ ->
            var index = charPos + i
            i++

            if (index < alphabet.count()) {
                meh.add(alphabet[index])
            } else {
                index = start
                meh.add(alphabet[index])
                start++
            }

            if (alphabet.count() - meh.count() == 1)
                return meh
        }

        return meh
    }
}