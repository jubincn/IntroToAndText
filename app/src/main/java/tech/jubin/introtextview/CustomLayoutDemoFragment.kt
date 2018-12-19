package tech.jubin.introtextview

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.*
import android.text.Annotation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomLayoutDemoFragment : Fragment() {

    companion object {
        private const val KEY_BG_ROUNDED = "bgRounded"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bgRoundedStr = SpannableString("This is a paragraph.\n This is a longer paragraph.")
        bgRoundedStr.setSpan(Annotation(KEY_BG_ROUNDED, "red"), 10, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        bgRoundedStr.setSpan(Annotation(KEY_BG_ROUNDED, "green"), 39, bgRoundedStr.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        val spanStartIndex = bgRoundedStr.nextSpanTransition(0, bgRoundedStr.length, Annotation::class.java)
        val spanEndIndex = bgRoundedStr.nextSpanTransition(spanStartIndex, bgRoundedStr.length, Annotation::class.java)

        val layout: StaticLayout = StaticLayout.Builder.obtain(bgRoundedStr, 0, bgRoundedStr.length, TextPaint(), 540).build()

        // Get line numbers for the start and end of the Annotation
        val startLine = layout.getLineForOffset(spanStartIndex)
        val endLine = layout.getLineForOffset(spanEndIndex)

        if (startLine == endLine) {
            val lineTop = layout.getLineTop(startLine)
            val lineBottom = layout.getLineBottom(startLine)
            val startCoor = layout.getPrimaryHorizontal(spanStartIndex).toInt()
            val endCoor = layout.getPrimaryHorizontal(spanEndIndex).toInt()

            val drawable = BitmapDrawable()
        }
    }
}