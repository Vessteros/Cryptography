package blocks.blockG

import blocks.AlgorithmInterface
import helpers.convertToIntArray
import kotlin.math.pow

class Algorithm17 : AlgorithmInterface {
    override lateinit var data: String
    override var parsedData: ArrayList<Int> = arrayListOf()
    override var result: String = ""

    val dataAsBitSet = arrayListOf(
        // блоки по 64 бита
        arrayListOf(
            "" // строки по 32 бита
        )
    )

    /** 32-битные ключи */
    private val sBlocks: ArrayList<ArrayList<Byte>> = arrayListOf()

    private val pKeys: ArrayList<String> = arrayListOf("")

    private val iterKeys: ArrayList<String> = arrayListOf()

    override fun encode() {
        setSBlocks()
        setIterKeys()

        dataAsBitSet.forEach {
            encodeBlock(it)
            println(it)
        }

//        iterKeys.forEach {
//            println(it)
//        }
//
//        println()
//
//        dataAsBitSet.forEach {
//            println(it)
//        }
    }

    private fun encodeBlock(block: ArrayList<String>) {
        repeat(31) {
            val rightSumString = sum32(block[1], it)
            val sString = fun4BitSBlock(rightSumString)
            var meh = ""

            for (i in 0 until block[1].count()) {
                meh += sString[i].toInt().xor(block[0][i].toInt())
            }

            val right = block[1]
            block[1] = meh
            block[0] = right
        }

        run {
            val rightSumString = sum32(block[1], 31)
            val sString = fun4BitSBlock(rightSumString)
            var meh = ""

            for (i in 0 until block[1].count()) {
                meh += sString[i].toInt().xor(block[0][i].toInt())
            }

            block[0] = meh
        }
    }

    private fun fun4BitSBlock(string: String): String {
        val blocks = arrayListOf("")
        var result = ""
        var meh = 0

        string.forEachIndexed { i, char ->
            if (i.div(4) != meh) {
                meh = i.div(4)

                blocks.add(meh, "")
            }

            blocks[meh] += char.toString()
        }

        blocks.forEachIndexed { i, block ->
            result += Integer.toBinaryString(
                sBlocks[i][block.toInt(2)].toInt()
            ).padStart(4, '0')
        }
        //todo: сдвинуть на 11 разрядов влево
//        println(result)
//        result = Integer.toBinaryString(result.toInt(2) shl 11)
//        println(result)
        return result
    }

    private fun sum32(s: String, i: Int): String {
        var rightSumString = ""

        for (j in 0 until s.count()) {
            rightSumString += s[j].toInt().xor(iterKeys[i][j].toInt())
        }

        return rightSumString
    }

    override fun parseData() {
        val meh = data.convertToIntArray().joinToString("") { Integer.toBinaryString(it) }

        var nyan = 0
        var menyan = 0

        meh.forEachIndexed { i, char ->
            if (i.div(64) != nyan) {
                nyan = i.div(64)
                dataAsBitSet.add(nyan, arrayListOf())
            }

            if ((i.div(32)).rem(2) != menyan) {
                menyan = (i.div(32)).rem(2)
                dataAsBitSet[nyan].add(menyan, "")
            }

            dataAsBitSet[nyan][menyan] += char.toString()
        }

        if (dataAsBitSet.last().first().count() != 32) {
            val diff = 32 - dataAsBitSet.last().first().count()

            repeat(diff) {
                dataAsBitSet.last()[0] += "0"
            }

            dataAsBitSet.last().add(1, "0")
        }

        if (dataAsBitSet.last().last().count() != 32) {
            val diff = 32 - dataAsBitSet.last().last().count()

            repeat(diff) {
                dataAsBitSet.last()[1] += "0"
            }
        }
    }

    private fun setSBlocks() = (0 until 8).forEach { _ ->
        val sBlock = arrayListOf<Byte>()
        val existingVals = arrayListOf<Byte>()

        (0 until 16).forEach { _ ->
            var newVal: Byte

            do {
                newVal = (0..15).random().toByte()
            } while (newVal in existingVals)

            sBlock.add(newVal)
            existingVals.add(newVal)
        }

        sBlocks.add(sBlock)
    }

    private fun setIterKeys() {
        createKey()

        repeat(3) {
            iterKeys.addAll(pKeys)
        }

        (7 downTo 0).forEach { i ->
            iterKeys.add(pKeys[i])
        }
    }

    private fun createKey() {
        var key256 = ""

        repeat(256) {
            key256 += (0..1).random().toString()
        }

        var div = 0
        key256.forEachIndexed { index, char ->
            if (index.div(32) != div) {
                div = index.div(32)
                pKeys.add(div, "")
            }

            pKeys[div] += char.toString()
        }
    }
}