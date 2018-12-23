package tech.jubin.introtextview.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_font_metrics.*
import tech.jubin.introtextview.R


class FontMetricsFragment : androidx.fragment.app.Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_font_metrics, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etTextString.setText("My text line")
        etFontSize.setText("150")

        updateButton.setOnClickListener(this)
        cbTop.setOnClickListener(this)
        cbAscent.setOnClickListener(this)
        cbBaseline.setOnClickListener(this)
        cbDescent.setOnClickListener(this)
        cbBottom.setOnClickListener(this)
        cbTextBounds.setOnClickListener(this)
        cbWidth.setOnClickListener(this)

        updateTextViews()
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.updateButton -> {
                viewWindow.setText(etTextString.text.toString())
                val fontSize: Int = try {
                    Integer.valueOf(etFontSize.text.toString())
                } catch (e: NumberFormatException) {
                    FontMetricsView.DEFAULT_FONT_SIZE_PX
                }

                viewWindow.setTextSizeInPixels(fontSize)
                updateTextViews()
                hideKeyboard(activity?.currentFocus)
            }
            R.id.cbTop -> viewWindow.setTopVisible(cbTop.isChecked)
            R.id.cbAscent -> viewWindow.setAscentVisible(cbAscent.isChecked)
            R.id.cbBaseline -> viewWindow.setBaselineVisible(cbBaseline.isChecked)
            R.id.cbDescent -> viewWindow.setDescentVisible(cbDescent.isChecked)
            R.id.cbBottom -> viewWindow.setBottomVisible(cbBottom.isChecked)
            R.id.cbTextBounds -> viewWindow.setBoundsVisible(cbTextBounds.isChecked)
            R.id.cbWidth -> viewWindow.setWidthVisible(cbWidth.isChecked)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTextViews() {
        tvTop.text = viewWindow.fontMetrics.top.toString()
        tvAscent.text = viewWindow.fontMetrics.ascent.toString()
        tvBaseline.text = 0f.toString()
        tvDescent.text = viewWindow.fontMetrics.descent.toString()
        tvBottom.text = viewWindow.fontMetrics.bottom.toString()
        tvTextBounds.text = "w = " + (viewWindow.textBounds!!.width().toString() +
                "     h = " + viewWindow.textBounds!!.height().toString())
        tvWidth.text = viewWindow.measuredTextWidth.toString()
        tvLeadingValue.text = viewWindow.fontMetrics.leading.toString()
    }

    private fun hideKeyboard(view: View?) {
        if (view != null) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(): FontMetricsFragment {
            return FontMetricsFragment()
        }
    }
}