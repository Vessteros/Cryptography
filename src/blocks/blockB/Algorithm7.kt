package blocks.blockB

import blocks.AlgorithmInterface
import helpers.convertToIntArray
import helpers.shl
import helpers.shr
import managers.MainMenu
import java.util.*
import kotlin.collections.ArrayList
import kotlin.experimental.and
import kotlin.experimental.xor
import kotlin.reflect.typeOf

class Algorithm7 : AlgorithmInterface {
    override lateinit var data: String
    override var parsedData: ArrayList<Int> = arrayListOf()
    override var result: String = ""

    val dataAsBitSet = arrayListOf(
        arrayListOf(
            ""
        )
    )

    val sBlocks: ArrayList<ArrayList<Int>> = arrayListOf()

    override fun encode() {
        println("Список 32-битных блоков по 4 бита")
        dataAsBitSet.forEach {
            println(it)
        }

        setSBlocks()

        println("Список S-блоков")
        sBlocks.forEach {
            println(it)
        }

        dataAsBitSet.forEach { row32 ->
            row32.forEachIndexed { i, string ->
                result += sBlocks[i][string.toInt(2)]
            }

            result += " "
        }

        printResult()

        MainMenu.printMenuCommandList()
    }

    override fun parseData() {
        val meh = data.convertToIntArray().joinToString("") { Integer.toBinaryString(it) }

        var nyan = 0
        var menyan = 0

        meh.forEachIndexed { i, char ->
             if (i.div(32) != nyan) {
                 nyan = i.div(32)
                 dataAsBitSet.add(nyan, arrayListOf())
             }

            if ((i.div(4)).rem(8) != menyan) {
                menyan = (i.div(4)).rem(8)
                dataAsBitSet[nyan].add(menyan, "")
            }

            dataAsBitSet[nyan][menyan] += char.toString()
        }
    }

    private fun setSBlocks() = (0 until 8).forEach { _ ->
        val sBlock = arrayListOf<Int>()
        val existingVals = arrayListOf<Int>()

        (0 until 15).forEach { _ ->
            var newVal: Int

            do {
                newVal = (0..15).random()
            } while (newVal in existingVals)

            sBlock.add(newVal)
            existingVals.add(newVal)
        }

        sBlocks.add(sBlock)
    }
}

