package com.example.cakes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakes.R
import com.example.cakes.databinding.ItemCakeBinding
import com.example.cakes.interfaces.ItemClickListener
import com.example.cakes.model.CakeListItem


class CakeAdapter(
    context: Context,
    cakeList: List<CakeListItem>,
    clickListener: ItemClickListener
) : RecyclerView.Adapter<CakeAdapter.MyViewHolder>() {
    private var cakeList: List<CakeListItem>
    private lateinit var binding: ItemCakeBinding
    private var context: Context
    private val clickListener: ItemClickListener

    fun setCakeList(cakeList: List<CakeListItem>) {
        this.cakeList = cakeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemCakeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        cakeList[position].apply {
            binding.tvTitle.text = title
            binding.tvDescription.text = desc
            Glide.with(context).load(image).placeholder(R.drawable.ic_cake).error(R.drawable.ic_cake).into(binding.imgCake)
            binding.itemCake.setOnClickListener {
                clickListener.onClick(title)
            }
        }


    }

    override fun getItemCount(): Int {
        return cakeList.size
    }

    inner class MyViewHolder(itemBinding: ItemCakeBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    init {
        this.cakeList = cakeList
        this.context = context
        this.clickListener=clickListener
    }
}
