package tdd

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

class TestHangmanGame {
    val testedWord = "triangle"

    @Test
    fun test_beforeAGuess() {
        val hangmanGame = HangmanGame(testedWord, 3)
        assertEquals(hangmanGame.status, HangamanGameStatus.Playing)
        assertEquals(hangmanGame.maskedWord, "________")
    }

    @Test
    fun test_correctGuess() {
        val hangmanGame = HangmanGame(testedWord, 3)
        hangmanGame.guess('t')
        assertEquals(hangmanGame.status, HangamanGameStatus.Playing)
        assertEquals(hangmanGame.maskedWord, "t_______")
    }

    @Test
    fun test_incorrectGuess() {
        val hangmanGame = HangmanGame(testedWord, 3)
        hangmanGame.guess('k')
        assertEquals(hangmanGame.status, HangamanGameStatus.Playing)
        assertEquals(hangmanGame.maskedWord, "________")
    }

    @Test
    fun test_failedGame() {
        val hangmanGame = HangmanGame(testedWord, 3)
        "???".forEach { hangmanGame.guess(it) }
        assertEquals(hangmanGame.status, HangamanGameStatus.Lossed)
        assertEquals(hangmanGame.maskedWord, "________")
        assertFailsWith<HangmanGameException> { hangmanGame.guess('?') }
    }

    @Test
    fun test_gainedGame() {
        val hangmanGame = HangmanGame(testedWord, 3)
        testedWord.forEach { hangmanGame.guess(it) }
        assertEquals(hangmanGame.status, HangamanGameStatus.Gained)
        assertEquals(hangmanGame.maskedWord, testedWord)
        assertFailsWith<HangmanGameException> { hangmanGame.guess('?') }
    }

}