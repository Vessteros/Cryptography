package blocks.blockB

import blocks.AlgorithmInterface
import helpers.shl
import helpers.shr
import kotlin.experimental.and
import kotlin.experimental.xor

class Algorithm7 : AlgorithmInterface {
    override lateinit var data: String
    override lateinit var parsedData: ArrayList<Int>
    override var result: String = ""

    companion object {
        const val BLOCK_SIZE = 16

        // таблица прямого нелинейного преобразования
        val Pi = byteArrayOf(
            0xFC.toByte(),
            0xEE.toByte(),
            0xDD.toByte(),
            0x11,
            0xCF.toByte(),
            0x6E,
            0x31,
            0x16,
            0xFB.toByte(),
            0xC4.toByte(),
            0xFA.toByte(),
            0xDA.toByte(),
            0x23,
            0xC5.toByte(),
            0x04,
            0x4D,
            0xE9.toByte(),
            0x77,
            0xF0.toByte(),
            0xDB.toByte(),
            0x93.toByte(),
            0x2E,
            0x99.toByte(),
            0xBA.toByte(),
            0x17,
            0x36,
            0xF1.toByte(),
            0xBB.toByte(),
            0x14,
            0xCD.toByte(),
            0x5F,
            0xC1.toByte(),
            0xF9.toByte(),
            0x18,
            0x65,
            0x5A,
            0xE2.toByte(),
            0x5C,
            0xEF.toByte(),
            0x21,
            0x81.toByte(),
            0x1C,
            0x3C,
            0x42,
            0x8B.toByte(),
            0x01,
            0x8E.toByte(),
            0x4F,
            0x05,
            0x84.toByte(),
            0x02,
            0xAE.toByte(),
            0xE3.toByte(),
            0x6A,
            0x8F.toByte(),
            0xA0.toByte(),
            0x06,
            0x0B,
            0xED.toByte(),
            0x98.toByte(),
            0x7F,
            0xD4.toByte(),
            0xD3.toByte(),
            0x1F,
            0xEB.toByte(),
            0x34,
            0x2C,
            0x51,
            0xEA.toByte(),
            0xC8.toByte(),
            0x48,
            0xAB.toByte(),
            0xF2.toByte(),
            0x2A,
            0x68,
            0xA2.toByte(),
            0xFD.toByte(),
            0x3A,
            0xCE.toByte(),
            0xCC.toByte(),
            0xB5.toByte(),
            0x70,
            0x0E,
            0x56,
            0x08,
            0x0C,
            0x76,
            0x12,
            0xBF.toByte(),
            0x72,
            0x13,
            0x47,
            0x9C.toByte(),
            0xB7.toByte(),
            0x5D,
            0x87.toByte(),
            0x15,
            0xA1.toByte(),
            0x96.toByte(),
            0x29,
            0x10,
            0x7B,
            0x9A.toByte(),
            0xC7.toByte(),
            0xF3.toByte(),
            0x91.toByte(),
            0x78,
            0x6F,
            0x9D.toByte(),
            0x9E.toByte(),
            0xB2.toByte(),
            0xB1.toByte(),
            0x32,
            0x75,
            0x19,
            0x3D,
            0xFF.toByte(),
            0x35,
            0x8A.toByte(),
            0x7E,
            0x6D,
            0x54,
            0xC6.toByte(),
            0x80.toByte(),
            0xC3.toByte(),
            0xBD.toByte(),
            0x0D,
            0x57,
            0xDF.toByte(),
            0xF5.toByte(),
            0x24,
            0xA9.toByte(),
            0x3E,
            0xA8.toByte(),
            0x43.toByte(),
            0xC9.toByte(),
            0xD7.toByte(),
            0x79,
            0xD6.toByte(),
            0xF6.toByte(),
            0x7C,
            0x22,
            0xB9.toByte(),
            0x03,
            0xE0.toByte(),
            0x0F,
            0xEC.toByte(),
            0xDE.toByte(),
            0x7A,
            0x94.toByte(),
            0xB0.toByte(),
            0xBC.toByte(),
            0xDC.toByte(),
            0xE8.toByte(),
            0x28,
            0x50,
            0x4E,
            0x33,
            0x0A,
            0x4A,
            0xA7.toByte(),
            0x97.toByte(),
            0x60,
            0x73,
            0x1E,
            0x00,
            0x62,
            0x44,
            0x1A,
            0xB8.toByte(),
            0x38,
            0x82.toByte(),
            0x64,
            0x9F.toByte(),
            0x26,
            0x41,
            0xAD.toByte(),
            0x45,
            0x46,
            0x92.toByte(),
            0x27,
            0x5E,
            0x55,
            0x2F,
            0x8C.toByte(),
            0xA3.toByte(),
            0xA5.toByte(),
            0x7D,
            0x69,
            0xD5.toByte(),
            0x95.toByte(),
            0x3B,
            0x07,
            0x58,
            0xB3.toByte(),
            0x40,
            0x86.toByte(),
            0xAC.toByte(),
            0x1D,
            0xF7.toByte(),
            0x30,
            0x37,
            0x6B,
            0xE4.toByte(),
            0x88.toByte(),
            0xD9.toByte(),
            0xE7.toByte(),
            0x89.toByte(),
            0xE1.toByte(),
            0x1B,
            0x83.toByte(),
            0x49,
            0x4C,
            0x3F,
            0xF8.toByte(),
            0xFE.toByte(),
            0x8D.toByte(),
            0x53,
            0xAA.toByte(),
            0x90.toByte(),
            0xCA.toByte(),
            0xD8.toByte(),
            0x85.toByte(),
            0x61,
            0x20,
            0x71,
            0x67,
            0xA4.toByte(),
            0x2D,
            0x2B,
            0x09,
            0x5B,
            0xCB.toByte(),
            0x9B.toByte(),
            0x25,
            0xD0.toByte(),
            0xBE.toByte(),
            0xE5.toByte(),
            0x6C,
            0x52,
            0x59,
            0xA6.toByte(),
            0x74,
            0xD2.toByte(),
            0xE6.toByte(),
            0xF4.toByte(),
            0xB4.toByte(),
            0xC0.toByte(),
            0xD1.toByte(),
            0x66,
            0xAF.toByte(),
            0xC2.toByte(),
            0x39,
            0x4B,
            0x63,
            0xB6.toByte()
        )

        // вектор линейного преобразования
        val l_vec = byteArrayOf(
            1,
            148.toByte(),
            32,
            133.toByte(),
            16,
            194.toByte(),
            192.toByte(),
            1,
            251.toByte(),
            1,
            192.toByte(),
            194.toByte(),
            16,
            133.toByte(),
            32,
            148.toByte()
        )

        // массив для хранения констант
        var iter_C = Array(32) { ByteArray(16) }
        // массив для хранения ключей
        var iter_key = Array(10) { ByteArray(64) }

        var key_1 = byteArrayOf(
            0x77,
            0x66,
            0x55,
            0x44,
            0x33,
            0x22,
            0x11,
            0x00,
            0xff.toByte(),
            0xee.toByte(),
            0xdd.toByte(),
            0xcc.toByte(),
            0xbb.toByte(),
            0xaa.toByte(),
            0x99.toByte(),
            0x88.toByte()
        )
        var key_2 = byteArrayOf(
            0xef.toByte(),
            0xcd.toByte(),
            0xab.toByte(),
            0x89.toByte(),
            0x67,
            0x45,
            0x23,
            0x01,
            0x10,
            0x32,
            0x54,
            0x76,
            0x98.toByte(),
            0xba.toByte(),
            0xdc.toByte(),
            0xfe.toByte()
        )
//        var blk = DatatypeConverter.parseHexBinary("8899aabbccddeeff0077665544332211")
    }

    override fun encode() {

    }

    // функция X
    private fun GOST_Kuz_X(a: ByteArray?, b: ByteArray?): ByteArray {
        var i = 0
        val c = ByteArray(BLOCK_SIZE)
        while (i < BLOCK_SIZE) {
            c[i] = (a!![i] xor b!![i])
            i++
        }
        return c
    }

    // Функция S
    private fun GOST_Kuz_S(in_data: ByteArray): ByteArray {
        var i = 0
        val out_data = ByteArray(in_data.size)
        while (i < BLOCK_SIZE) {
            var data = in_data[i].toInt()
            if (data < 0) {
                data += 256
            }
            out_data[i] = Pi[data]
            i++
        }
        return out_data
    }

    // умножение в поле Галуа
    private fun GOST_Kuz_GF_mul(a1: Byte, b1: Byte): Byte {
        var a = a1
        var b = b1
        var c: Byte = 0
        var hiBit: Byte
        var i = 0
        while (i < 8) {
            if (b and 1 == 1.toByte())
                c = c xor a
            hiBit = (a and 0x80.toByte())
            a = a shl 1
            if (hiBit < 0) {
                a = a xor 0xc3.toByte() //полином  x^8+x^7+x^6+x+1
            }
            b = b shr 1
            i++
        }
        return c
    }

    // функция R сдвигает данные и реализует уравнение, представленное для расчета L-функции
    private fun GOST_Kuz_R(state: ByteArray): ByteArray {
        var i = 15
        var a15: Byte = 0
        val internal = ByteArray(16)
        while (i >= 0) {
            if (i == 0)
                internal[15] = state[i]
            else
                internal[i - 1] = state[i]
            a15 = a15 xor GOST_Kuz_GF_mul(state[i], l_vec[i])
            i--
        }
        internal[15] = a15
        return internal
    }

    private fun GOST_Kuz_L(in_data: ByteArray): ByteArray {
        var i = 0
        val outData: ByteArray
        var internal = in_data
        while (i < 16) {
            internal = GOST_Kuz_R(internal)
            i++
        }
        outData = internal
        return outData
    }

    // функция расчета констант
    private fun GOST_Kuz_Get_C() {
        var i = 0
        val iterNum = Array(32) { ByteArray(16) }
        while (i < 32) {
            for (j in 0 until BLOCK_SIZE)
                iterNum[i][j] = 0
            iterNum[i][0] = (i + 1).toByte()
            i++
        }
        i = 0
        while (i < 32) {
            iter_C[i] = GOST_Kuz_L(iterNum[i])
            i++
        }
    }

    // функция, выполняющая преобразования ячейки Фейстеля
    private fun GOST_Kuz_F(in_key_1: ByteArray?, in_key_2: ByteArray?, iter_const: ByteArray): Array<ByteArray?> {
        var internal: ByteArray = GOST_Kuz_X(in_key_1, iter_const)
        internal = GOST_Kuz_S(internal)
        internal = GOST_Kuz_L(internal)
        val outKey1 = GOST_Kuz_X(internal, in_key_2)
        val key = arrayOfNulls<ByteArray>(2)
        key[0] = outKey1
        key[1] = in_key_1
        return key
    }

    // функция расчета раундовых ключей
    fun GOST_Kuz_Expand_Key(key_1: ByteArray, key_2: ByteArray) {
        var i = 0

        var iter12 = arrayOfNulls<ByteArray>(2)
        var iter34: Array<ByteArray?>
        GOST_Kuz_Get_C()
        iter_key[0] = key_1
        iter_key[1] = key_2
        iter12[0] = key_1
        iter12[1] = key_2
        while (i < 4) {
            iter34 = GOST_Kuz_F(iter12[0], iter12[1], iter_C[0 + 8 * i])
            iter12 = GOST_Kuz_F(iter34[0], iter34[1], iter_C[1 + 8 * i])
            iter34 = GOST_Kuz_F(iter12[0], iter12[1], iter_C[2 + 8 * i])
            iter12 = GOST_Kuz_F(iter34[0], iter34[1], iter_C[3 + 8 * i])
            iter34 = GOST_Kuz_F(iter12[0], iter12[1], iter_C[4 + 8 * i])
            iter12 = GOST_Kuz_F(iter34[0], iter34[1], iter_C[5 + 8 * i])
            iter34 = GOST_Kuz_F(iter12[0], iter12[1], iter_C[6 + 8 * i])
            iter12 = GOST_Kuz_F(iter34[0], iter34[1], iter_C[7 + 8 * i])

            iter_key[2 * i + 2] = iter12[0]!!
            iter_key[2 * i + 3] = iter12[1]!!
            i++
        }
    }

    // функция шифрования блока
    fun GOST_Kuz_Encript(blk: ByteArray): ByteArray {
        var i = 0
        var outBlk: ByteArray
        outBlk = blk
        while (i < 9) {
            outBlk = GOST_Kuz_X(iter_key[i], outBlk)
            outBlk = GOST_Kuz_S(outBlk)
            outBlk = GOST_Kuz_L(outBlk)
            i++
        }
        outBlk = GOST_Kuz_X(outBlk, iter_key[9])
        return outBlk
    }
}

