package tdd

class Hangman {
    fun countAlphabet(word: String, a: Char) =
        word.count { it == a }
}
