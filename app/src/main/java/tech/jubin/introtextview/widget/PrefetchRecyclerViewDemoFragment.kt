package tech.jubin.introtextview.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import tech.jubin.introtextview.data.DataGenerationWorker
import tech.jubin.introtextview.data.DataGenerationWorker.Companion.OUTPUT_KEY_TEXTS

class PrefetchRecyclerViewDemoFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataGenWork = OneTimeWorkRequestBuilder<DataGenerationWorker>().build()
        WorkManager.getInstance().enqueue(dataGenWork)

        WorkManager.getInstance().getWorkInfoByIdLiveData(dataGenWork.id)
            .observe(this, Observer { info ->
                if (info != null && info.state.isFinished) {
                    val data = info.outputData.getStringArray(OUTPUT_KEY_TEXTS)

                }
            })
    }

}

class DemoRVAdapter: RecyclerView.Adapter<DemoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {

    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class DemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}
