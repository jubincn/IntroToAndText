package tech.jubin.introtextview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.view_rv_item_text.view.*
import tech.jubin.introtextview.data.DataGenerationWorker
import tech.jubin.introtextview.data.DataGenerationWorker.Companion.OUTPUT_KEY_TEXTS

class NormalTextRVFragment : Fragment() {

    private val adapter = NormalTextRVAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_prefetch_recycler_view, container, false)

        val recyclerView = rootView as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataGenWork = OneTimeWorkRequestBuilder<DataGenerationWorker>().build()
        WorkManager.getInstance().enqueue(dataGenWork)

        WorkManager.getInstance().getWorkInfoByIdLiveData(dataGenWork.id)
            .observe(this, Observer { info ->
                if (info != null && info.state.isFinished) {
                    val data = info.outputData.getStringArray(OUTPUT_KEY_TEXTS)
                    if (data != null) {
                        view.alpha
                        adapter.updateData(data.toList())
                        adapter.notifyDataSetChanged()
                    }
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance(): NormalTextRVFragment {
            return NormalTextRVFragment()
        }
    }
}

class PrecomputedTextRVFragment : Fragment() {
    private val adapter = PrecomputedTextRVAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_prefetch_recycler_view, container, false)

        val recyclerView = rootView as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataGenWork = OneTimeWorkRequestBuilder<DataGenerationWorker>().build()
        WorkManager.getInstance().enqueue(dataGenWork)

        WorkManager.getInstance().getWorkInfoByIdLiveData(dataGenWork.id)
            .observe(this, Observer { info ->
                if (info != null && info.state.isFinished) {
                    val data = info.outputData.getStringArray(OUTPUT_KEY_TEXTS)
                    if (data != null) {
                        view.alpha
                        adapter.updateData(data.toList())
                        adapter.notifyDataSetChanged()
                    }
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance(): NormalTextRVFragment {
            return NormalTextRVFragment()
        }
    }
}

class NormalTextRVAdapter : RecyclerView.Adapter<DemoViewHolder>() {
    private val data = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_rv_item_text, parent, false)
        return DemoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val text = data[position]
        holder.tvContent.text = text
    }

    fun updateData(newData: List<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}

class PrecomputedTextRVAdapter : RecyclerView.Adapter<DemoViewHolder>() {
    private val data = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_rv_item_text, parent, false)
        return DemoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        val demoText = data[position]
        val params :PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(holder.tvContent)
        val precomputedText = PrecomputedTextCompat.create(demoText, params)
        TextViewCompat.setPrecomputedText(holder.tvContent, precomputedText)

        val textView = holder.tvContent
        textView.setTextFuture(PrecomputedTextCompat.getTextFuture(
            demoText,
            TextViewCompat.getTextMetricsParams(textView),
            /*optional custom executor*/ null))
    }

    fun updateData(newData: List<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}

class DemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvContent = itemView.tv_item as AppCompatTextView
}
