package blocks.blockB

class Algorithm6 : Algorithm5() {
    override fun setTable() {
        alphabet.forEach { char ->
            val meh = arrayListOf<Int>()
            meh.addAll(setAlphabetWithStep(char))
            table.add(meh)
        }
    }

    override fun setAlphabetWithStep(char: Int): ArrayList<Int> {
        val meh = arrayListOf<Int>()
        val charPos = alphabet.indexOf(char)

        var start = 0
        alphabet.forEachIndexed { i, _ ->
            var index = charPos + i

            if (index < alphabet.count()) {
                meh.add(alphabet[index])
            } else {
                index = start
                meh.add(alphabet[index])
                start++
            }

            if (alphabet.count() == meh.count())
                return meh
        }

        return meh
    }
}