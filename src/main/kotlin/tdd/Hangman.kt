package tdd

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStream
import java.net.URL
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.streams.asSequence

class Hangman {
    private val wordsSet: MutableSet<String>

    constructor(wordsStream: InputStream) {
        this.wordsSet = wordsStream.bufferedReader().use {
            it.lines().asSequence().toHashSet()
        }
    }

    fun fetchWord() = fetchWord(5)

    fun fetchWord(requestedLength: Int): String = run {
        val selectedWord = wordsSet.first { it.length == requestedLength }
        wordsSet.remove(selectedWord)
        selectedWord
    }
}
