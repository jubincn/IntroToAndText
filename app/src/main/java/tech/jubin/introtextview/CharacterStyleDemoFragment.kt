package tech.jubin.introtextview

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.text.Spanned
import android.text.style.*
import kotlinx.android.synthetic.main.fragment_character_style.*
import android.text.style.ScaleXSpan
import android.text.style.TypefaceSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.graphics.Color
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.MaskFilterSpan
import android.graphics.BlurMaskFilter
import android.support.v4.content.res.ResourcesCompat
import android.text.style.URLSpan


class CharacterStyleDemoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ---------------------------------------- MetricAffectingSpan ----------------------------------------
        val styleSpanStr = SpannableString("Bold and italic text");
        styleSpanStr.setSpan(StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        styleSpanStr.setSpan(StyleSpan(Typeface.ITALIC), 9, 15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_style_span.text = styleSpanStr

        val superscriptSpanStr = SpannableString("1st example")
        superscriptSpanStr.setSpan(SuperscriptSpan(), 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_superscript_span.text = superscriptSpanStr

        val subscriptSpanStr = SpannableString("☕- C8H10N4O2\n")
        subscriptSpanStr.setSpan(SubscriptSpan(), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        subscriptSpanStr.setSpan(SubscriptSpan(), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        subscriptSpanStr.setSpan(SubscriptSpan(), 9, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        subscriptSpanStr.setSpan(SubscriptSpan(), 11, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_subscript_span.text = subscriptSpanStr

        val relativeSpanStr = SpannableString("Text with relative size span")
        relativeSpanStr.setSpan(RelativeSizeSpan(1.5f), 10, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_relative_size_span.text = relativeSpanStr

        val absoluteSpanStr = SpannableString("Text with absolute size span")
        absoluteSpanStr.setSpan(AbsoluteSizeSpan(55, true), 10, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_absolute_size_span.text = absoluteSpanStr

        val imageSpanStr = SpannableString("Bottom: span.\nBaseline: span.")
        // using the default alignment: ALIGN_BOTTOM
        imageSpanStr.setSpan(ImageSpan(context!!, R.mipmap.ic_launcher), 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        imageSpanStr.setSpan(ImageSpan(context!!, R.mipmap.ic_launcher_round, DynamicDrawableSpan.ALIGN_BASELINE),
                22, 23, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_image_span.text = imageSpanStr

        val textAppearanceSpanStr = SpannableString("Text with TextAppearance_AppCompat_Small appearance ")
        val textAppearanceSpan = TextAppearanceSpan(context!!, R.style.TextAppearance_AppCompat_Small)
        textAppearanceSpanStr.setSpan(textAppearanceSpan, 0, textAppearanceSpanStr.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_text_appearance_span.text = textAppearanceSpanStr

        val scaleXSpanStr = SpannableString("Text with ScaleX span")
        scaleXSpanStr.setSpan(ScaleXSpan(2f), 10, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_scale_x_span.text = scaleXSpanStr

        val acmeTypeface = ResourcesCompat.getFont(context!!, R.font.acme_regular)!!
        val typefaceSpanStr = SpannableString("Text with typeface span.")
        typefaceSpanStr.setSpan(TypefaceSpan(acmeTypeface), 10, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        typefaceSpanStr.setSpan(TypefaceSpan("monospace"), 19, 22, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_typeface_span.text = typefaceSpanStr


        // ---------------------------------------- Other CharacterStyle Spans ----------------------------------------
        val strikeThroughSpanStr = SpannableString("Text with strikethrough span")
        strikeThroughSpanStr.setSpan(StrikethroughSpan(), 10, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_strike_through_span.text = strikeThroughSpanStr

        val underlineSpanStr = SpannableString("Text with underline span")
        underlineSpanStr.setSpan(UnderlineSpan(), 10, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_underline_span.text = underlineSpanStr

        val backgroundSpanStr = SpannableString("Text with a background color span")
        backgroundSpanStr.setSpan(BackgroundColorSpan(Color.RED), 12, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_background_color_span.text = backgroundSpanStr

        val foregroundSpanStr = SpannableString("Text with a foreground color span")
        foregroundSpanStr.setSpan(ForegroundColorSpan(Color.GREEN), 12, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_foreground_color_span.text = foregroundSpanStr

        val blurMask = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
        val maskFilterSpanStr = SpannableString("Text with blur mask")
        maskFilterSpanStr.setSpan(MaskFilterSpan(blurMask), 10, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_musk_filter_span.text = maskFilterSpanStr

        val urlSpanStr = SpannableString("Text with a url span")
        urlSpanStr.setSpan(URLSpan("http://www.developer.android.com"), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_url_span.text = urlSpanStr
    }


    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(): CharacterStyleDemoFragment {
            return CharacterStyleDemoFragment()
        }
    }
}