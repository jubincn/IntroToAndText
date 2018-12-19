package tech.jubin.introtextview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Spanned
import android.text.style.TabStopSpan
import android.text.SpannableString
import kotlinx.android.synthetic.main.fragment_paragraph_style.*
import android.graphics.BitmapFactory
import android.text.style.IconMarginSpan
import android.text.Layout
import android.text.style.AlignmentSpan
import android.text.style.QuoteSpan
import android.graphics.Color
import android.text.style.BulletSpan
import android.support.v4.content.res.ResourcesCompat
import android.text.style.DrawableMarginSpan






class ParagraphStyleDemoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paragraph_style, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabStopSpanStr = SpannableString("\tParagraph text beginning with tab.")
        tabStopSpanStr.setSpan(TabStopSpan.Standard(100), 0, tabStopSpanStr.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_tab_stop_standard.text = tabStopSpanStr

        val iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_drawable)
        val iconMarginSpanStr = SpannableString("Text with icon and padding")
        iconMarginSpanStr.setSpan(IconMarginSpan(iconBitmap, 30), 0, iconMarginSpanStr.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_icon_margin_span.text = iconMarginSpanStr

        val alignmentSpanStr = SpannableString("Text with opposite alignment")
        alignmentSpanStr.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0,
                alignmentSpanStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_alignment_standard.text = alignmentSpanStr

        val quoteSpanStr = SpannableString("Text with quote span on a long line")
        quoteSpanStr.setSpan(QuoteSpan(), 0, quoteSpanStr.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_quote_span_1.text = quoteSpanStr
        quoteSpanStr.setSpan(QuoteSpan(Color.GREEN, 20, 40), 0, quoteSpanStr.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_quote_span_2.text = quoteSpanStr

        val bulletSpanStr = SpannableString("Text with\nBullet point")
        bulletSpanStr.setSpan(BulletSpan(), 10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_bullet_span_1.text = bulletSpanStr
        bulletSpanStr.setSpan(BulletSpan(40, Color.GREEN, 20), 10, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_bullet_span_2.text = bulletSpanStr

        val drawableMarginSpanStr = SpannableString("Text with a drawable.")
        val drawable = ResourcesCompat.getDrawable(resources, R.mipmap.ic_launcher_round, null)!!
        drawableMarginSpanStr.setSpan(DrawableMarginSpan(drawable, 20), 0, drawableMarginSpanStr.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_drawable_margin_span.text = drawableMarginSpanStr
    }

    companion object {
        @JvmStatic
        fun newInstance(): ParagraphStyleDemoFragment {
            return ParagraphStyleDemoFragment()
        }
    }
}