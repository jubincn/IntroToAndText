package tech.jubin.introtextview

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_emoji_icon.*
import tech.jubin.introtextview.thirdparty.qmui.EmojiconSpan

class EmojiIconDemoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_emoji_icon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textSize = tv_content.textSize.toInt()
        val emojiSpan = EmojiconSpan(context, R.drawable.smiley_21, textSize, textSize)
        val text = SpannableStringBuilder("Hello Emoji")
        text.setSpan(emojiSpan,5,6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_content.text = text
    }

    companion object {
        @JvmStatic
        fun newInstance(): EmojiIconDemoFragment {
            return EmojiIconDemoFragment()
        }
    }
}