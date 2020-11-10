package com.smascaro.listdetail

import android.animation.ObjectAnimator
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smascaro.listdetail.list.model.Breed
import com.smascaro.listdetail.list.view.BreedsAdapter

@BindingAdapter("binding:data")
fun setData(view: RecyclerView, data: List<Breed>?) {
    view.adapter = BreedsAdapter().apply {
        items = data ?: listOf()
        notifyDataSetChanged()
    }
}

@BindingAdapter("binding:expandOnClick")
fun expandOnClick(textView: TextView, expand: Boolean?) {
    if (expand != null && expand) {
        textView.setOnClickListener {
            if (textView.maxLines == 1) {
                //expand
                ObjectAnimator.ofInt(textView, "maxLines", textView.lineCount).apply {
                    duration = 200
                }.start()
            } else {
                //collapse
                ObjectAnimator.ofInt(textView, "maxLines", 1).apply {
                    duration = 200
                }.start()
            }
        }
    }
}