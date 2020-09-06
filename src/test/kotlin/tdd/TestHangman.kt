package tdd

import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertTrue

class TestHangman {

    @Test
    fun test_lengthOfFetchedWord() {
        val hangman = getHangmanFromResource()
        val word = hangman.fetchWord()
        assertTrue { word.length == 5 }
    }

    @Test
    fun test_lengthOfFetchedWordRandom() {
        val random = Random.Default
        val requestedLength = random.nextInt(5, 10)
        val hangman = getHangmanFromResource()
        val word = hangman.fetchWord(requestedLength)
        assertTrue { word.length == requestedLength }
    }

    @Test
    fun test_uniquenessOfFetchedWord() {
        val random = Random.Default
        val usedWords = HashSet<String>()
        val hangman = getHangmanFromResource()

        for (round in 1..100) {
            val requestedLength = random.nextInt(5, 10)
            val word = hangman.fetchWord(requestedLength)
            assertTrue { usedWords.add(word) }
        }
    }

    fun getHangmanFromResource() = run {
        val wordsStream = TestHangman::class.java.classLoader.getResourceAsStream("WordSource.txt")
        Hangman(wordsStream)
    }
}