package tech.jubin.introtextview

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.*
import android.text.Annotation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomLayoutDemoFragment : androidx.fragment.app.Fragment() {

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

    }
}