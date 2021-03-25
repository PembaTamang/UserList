package com.example.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userlist.databinding.ItemRecyclerBinding
import com.example.userlist.models.Data

class RecyclerAdapter(private val arrayList: ArrayList<Data>, private val listner : (Int) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.RVH>() {

    inner class RVH(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(
       binding.root
    ) {
        fun bind(data: Data) {
            binding.apply {
                name.text = data.name
                email.text = data.email
                status.text = data.status
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVH {
      return RVH(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RVH, position: Int) {
       holder.bind(arrayList[position])
    }

    override fun getItemCount() =  arrayList.size
}