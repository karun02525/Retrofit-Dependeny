package com.swipeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.swipeapp.R
import com.swipeapp.databinding.ItemLayoutBinding
import com.swipeapp.domain.model.ProductModel

class ProductListAdapter (var list: List<ProductModel>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(model: ProductModel) {
            with(model) {
                if (model.image == "") {
                    binding.itemImg.setImageResource(R.drawable.no_image)
                } else {
                    Picasso.get().load(model.image).into(binding.itemImg)
                }
                binding.itemTitle.text=productName
                binding.itemType.text=productType
                binding.itemPrice.text=price
                binding.itemTax.text=tax
            }
        }
    }


}