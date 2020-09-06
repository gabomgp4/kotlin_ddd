package tdd

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestHangman {
    @Test
    fun test_alphabetCountInAWord() {
        val word = "pizza"
        val alphabet = 'a'

        val hangman= Hangman()
        val count = hangman.countAlphabet(word, alphabet)

        assertEquals(1, count)
    }
}