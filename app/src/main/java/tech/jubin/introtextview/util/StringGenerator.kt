package tech.jubin.introtextview.util

import android.os.SystemClock
import java.lang.StringBuilder
import kotlin.random.Random

object TextGenerator {
    private const val ALPHABETS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private const val BLANK_SPACE = " "
    private const val DOT = "."
    private const val NEW_LINE = "\n"
    private const val WORD_MIN_LENGTH = 2
    private const val WORD_MAX_LENGTH = 10
    private const val SENTENCE_MIN_LENGTH = 3
    private const val SENTENCE_MAX_LENGTH = 20
    private const val PARAGRAPH_MIN_LENGTH = 1
    private const val PARAGRAPH_MAX_LENGTH = 10
    private val random = Random(SystemClock.uptimeMillis())


    private fun getWord(): CharSequence {
        val wordLength = random.nextInt(WORD_MIN_LENGTH, WORD_MAX_LENGTH + 1)
        val wordBuilder = StringBuilder()
        for (i in 0..wordLength) {
            val characterPosition = random.nextInt(0, ALPHABETS.length)
            val nextChar = ALPHABETS[characterPosition]
            wordBuilder.append(nextChar)
        }

        return wordBuilder.toString()
    }

    private fun getSentence(): CharSequence {
        val sentenceLength = random.nextInt(SENTENCE_MIN_LENGTH, SENTENCE_MAX_LENGTH + 1)
        val sentenceBuilder = StringBuilder()
        var isFirstWord = true
        for (i in 0..sentenceLength) {
            val nextWord = if (isFirstWord) {
                isFirstWord = false
                getWord()
            } else {
                BLANK_SPACE + getWord()
            }
            sentenceBuilder.append(nextWord)
        }
        sentenceBuilder.append(DOT)
        return sentenceBuilder.toString()
    }

    fun getParagraphText(): CharSequence {
        val paragraphLength = random.nextInt(PARAGRAPH_MIN_LENGTH, PARAGRAPH_MAX_LENGTH + 1)
        val paragraphBuilder = StringBuilder()
        var isFirstSentence = true
        for (i in 0..paragraphLength) {
            val nextSentence = if (isFirstSentence) {
                isFirstSentence = false
                getSentence()
            } else {
                BLANK_SPACE + getSentence()
            }
            paragraphBuilder.append(nextSentence)
        }
        paragraphBuilder.append(NEW_LINE)
        return paragraphBuilder.toString()
    }
}