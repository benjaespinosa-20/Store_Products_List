package com.example.practicemvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicemvvm.data.model.ListProducts
import com.example.practicemvvm.data.model.Products
import com.example.practicemvvm.databinding.ListItemBinding

class ProductsAdapter (
    private val productList: List<Products>,
    private val itemClickListener: OnProductClickListener
    ) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnProductClickListener{
        fun onProductClick(product: Products)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ProductsViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onProductClick(productList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ProductsViewHolder -> holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int =productList.size

    private inner class ProductsViewHolder(val binding: ListItemBinding, val context: Context): BaseViewHolder<Products>(binding.root){
        override fun bind(item: Products) {
            binding.listTitle.text = item.title
            binding.listDescription.text = item.description
            binding.listPrice.text = item.price.toString()
        }
    }
}

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}