package tech.jubin.introtextview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.res.ResourcesCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import tech.jubin.introtextview.R

class FontMetricsView : View {

    private var mText = "My text line"
    private var mTextSize = DEFAULT_FONT_SIZE_PX
    private val mAscentPaint = Paint()
    private val mTopPaint = Paint()
    private val mBaselinePaint = Paint()
    private val mDescentPaint = Paint()
    private val mBottomPaint = Paint()
    private val mMeasuredWidthPaint = Paint()
    private val mTextBoundsPaint = Paint()
    private val mTextPaint = TextPaint()
    private val mLinePaint = Paint()
    private val mRectPaint = Paint()
    private val mBounds = Rect()
    private var mIsTopVisible: Boolean = false
    private var mIsAscentVisible: Boolean = false
    private var mIsBaselineVisible: Boolean = false
    private var mIsDescentVisible: Boolean = false
    private var mIsBottomVisible: Boolean = false
    private var mIsBoundsVisible: Boolean = false
    private var mIsWidthVisible: Boolean = false

    // getters
    val fontMetrics: Paint.FontMetrics
        get() = mTextPaint.fontMetrics

    val textBounds: Rect?
        get() {
            mTextPaint.getTextBounds(mText, 0, mText.length, mBounds)
            return mBounds
        }

    val measuredTextWidth: Float
        get() = mTextPaint.measureText(mText)


    constructor(context: Context) : super(context) {
        init()
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        mTextPaint.isAntiAlias = true
        mTextPaint.textSize = mTextSize.toFloat()
        mTextPaint.color = Color.BLACK

        mLinePaint.color = Color.RED
        mLinePaint.strokeWidth = STROKE_WIDTH

        mAscentPaint.color = ResourcesCompat.getColor(resources, R.color.ascent, null)
        mAscentPaint.strokeWidth = STROKE_WIDTH

        mTopPaint.color = resources.getColor(R.color.top)
        mTopPaint.strokeWidth = STROKE_WIDTH

        mBaselinePaint.color = resources.getColor(R.color.baseline)
        mBaselinePaint.strokeWidth = STROKE_WIDTH

        mBottomPaint.color = resources.getColor(R.color.bottom)
        mBottomPaint.strokeWidth = STROKE_WIDTH

        mDescentPaint.color = resources.getColor(R.color.descent)
        mDescentPaint.strokeWidth = STROKE_WIDTH

        mMeasuredWidthPaint.color = resources.getColor(R.color.measured_width)
        mMeasuredWidthPaint.strokeWidth = STROKE_WIDTH

        mTextBoundsPaint.color = resources.getColor(R.color.text_bounds)
        mTextBoundsPaint.strokeWidth = STROKE_WIDTH
        mTextBoundsPaint.style = Paint.Style.STROKE

        mRectPaint.color = Color.BLACK
        mRectPaint.strokeWidth = STROKE_WIDTH
        mRectPaint.style = Paint.Style.STROKE

        mIsTopVisible = true
        mIsAscentVisible = true
        mIsBaselineVisible = true
        mIsDescentVisible = true
        mIsBottomVisible = true
        mIsBoundsVisible = true
        mIsWidthVisible = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // center the text baseline vertically
        val verticalAdjustment = this.height / 2f
        canvas.translate(0f, verticalAdjustment)

        var startX = paddingLeft.toFloat()
        var startY = 0f
        var stopX = measuredWidth.toFloat()
        var stopY = 0f

        // draw text
        canvas.drawText(mText, startX, startY, mTextPaint) // x=0, y=0

        // draw lines
        startX = 0f

        if (mIsTopVisible) {
            startY = mTextPaint.fontMetrics.top
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mTopPaint)
        }

        if (mIsAscentVisible) {
            startY = mTextPaint.fontMetrics.ascent
            stopY = startY
            //mLinePaint.setColor(Color.GREEN);
            canvas.drawLine(startX, startY, stopX, stopY, mAscentPaint)
        }

        if (mIsBaselineVisible) {
            startY = 0f
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mBaselinePaint)
        }

        if (mIsDescentVisible) {
            startY = mTextPaint.fontMetrics.descent
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mDescentPaint)
        }

        if (mIsBottomVisible) {
            startY = mTextPaint.fontMetrics.bottom
            stopY = startY
            mLinePaint.setColor(Color.RED)
            canvas.drawLine(startX, startY, stopX, stopY, mBaselinePaint)
        }

        if (mIsBoundsVisible) {
            mTextPaint.getTextBounds(mText, 0, mText.length, mBounds)
            val dx = paddingLeft.toFloat()
            canvas.drawRect(mBounds.left.toFloat() + dx, mBounds.top.toFloat(),
                mBounds.right.toFloat() + dx, mBounds.bottom.toFloat(), mTextBoundsPaint)
        }

        if (mIsWidthVisible) {
            // get measured width
            val width = mTextPaint.measureText(mText)

            // get bounding width so that we can compare them
            mTextPaint.getTextBounds(mText, 0, mText.length, mBounds)

            // draw vertical line just before the left bounds
            startX = paddingLeft + mBounds.left - (width - mBounds.width()) / 2
            stopX = startX
            startY = -verticalAdjustment
            stopY = startY + height
            canvas.drawLine(startX, startY, stopX, stopY, mMeasuredWidthPaint)

            // draw vertical line just after the right bounds
            startX += width
            stopX = startX
            canvas.drawLine(startX, startY, stopX, stopY, mMeasuredWidthPaint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var width = 200
        var height = 200

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthRequirement = MeasureSpec.getSize(widthMeasureSpec)
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement
        } else if (widthMode == MeasureSpec.AT_MOST && width > widthRequirement) {
            width = widthRequirement
        }

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightRequirement = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement
        } else if (heightMode == MeasureSpec.AT_MOST && width > heightRequirement) {
            height = heightRequirement
        }

        setMeasuredDimension(width, height)
    }

    // setters
    fun setText(text: String) {
        mText = text
        invalidate()
        requestLayout()
    }

    fun setTextSizeInPixels(pixels: Int) {
        mTextSize = pixels
        mTextPaint!!.textSize = mTextSize.toFloat()
        invalidate()
        requestLayout()
    }

    fun setTopVisible(isVisible: Boolean) {
        mIsTopVisible = isVisible
        invalidate()
    }

    fun setAscentVisible(isVisible: Boolean) {
        mIsAscentVisible = isVisible
        invalidate()
    }

    fun setBaselineVisible(isVisible: Boolean) {
        mIsBaselineVisible = isVisible
        invalidate()
    }

    fun setDescentVisible(isVisible: Boolean) {
        mIsDescentVisible = isVisible
        invalidate()
    }

    fun setBottomVisible(isVisible: Boolean) {
        mIsBottomVisible = isVisible
        invalidate()
    }

    fun setBoundsVisible(isVisible: Boolean) {
        mIsBoundsVisible = isVisible
        invalidate()
    }

    fun setWidthVisible(isVisible: Boolean) {
        mIsWidthVisible = isVisible
        invalidate()
    }

    companion object {

        const val DEFAULT_FONT_SIZE_PX = 180
        //private static final int PURPLE = Color.parseColor("#9315db");
        //private static final int ORANGE = Color.parseColor("#ff8a00");
        private const val STROKE_WIDTH = 5.0f
    }

}