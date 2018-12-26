package tech.jubin.introtextview.data

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import tech.jubin.introtextview.util.TextGenerator

class DataGenerationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        val result = Array(100) { TextGenerator.getParagraphText().toString() }
        val output = Data.Builder()
            .putStringArray(OUTPUT_KEY_TEXTS, result)
            .build()

        return Result.success(output)
    }

    companion object {
        const val OUTPUT_KEY_TEXTS = "output_key_texts"
    }
}