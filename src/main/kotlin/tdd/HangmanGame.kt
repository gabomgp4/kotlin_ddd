package tdd

import java.util.*

class HangmanGame(val testedWord: String, val maxFails: Int) {
    val mask = BitSet(testedWord.length)

    fun guess(c: Char) = run {
        if (status != HangamanGameStatus.Playing) {
            throw HangmanGameException("El juego ha sido cerrado, no se aceptan mas intentos")
        }

        var found = false
        var index = testedWord.indexOf(c)
        while (index != -1) {
            found = true
            mask.set(index, true)
            index = testedWord.indexOf(c, index + 1)
        }

        maskedWord = testedWord
            .mapIndexed { index, c -> if (mask.get(index)) c else '_' }
            .map { it.toString() }
            .reduce { acc, e -> acc + e }

        if (!found) {
            addFail()
        } else if (mask.cardinality() == testedWord.length) {
            status = HangamanGameStatus.Gained
        }

        found
    }

    private fun addFail() {
        fails++
        if (fails == maxFails) {
            status = HangamanGameStatus.Lossed
        }
    }

    var maskedWord = "_".repeat(testedWord.length)
        private set

    var status = HangamanGameStatus.Playing
        private set

    var fails = 0
        private set
}

class HangmanGameException(text: String) : Exception(text)

enum class HangamanGameStatus {
    Playing,
    Lossed,
    Gained
}