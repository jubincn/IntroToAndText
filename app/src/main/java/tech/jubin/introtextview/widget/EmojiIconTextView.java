package tech.jubin.introtextview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class EmojiIconTextView extends TextView {

    public EmojiIconTextView(Context context) {
        super(context);
    }

    public EmojiIconTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EmojiIconTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*
    if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            EmojiconHandler.addEmojis(getContext(), builder, mEmojiconSize, mEmojiconTextSize, mTextStart, mTextLength, mUseSystemDefault);
            text = builder;
        }
     */
}