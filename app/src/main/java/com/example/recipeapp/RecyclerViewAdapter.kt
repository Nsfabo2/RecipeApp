package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recpie_item_row.view.*

class RecyclerViewAdapter (private val recipes: List<DataItem>): RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {
    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recpie_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val recipe = recipes[position]

        holder.itemView.apply {
            title.text = recipe.title
            author.text = recipe.author
            ingredients.text = recipe.ingredients
            instructions.text = recipe.instructions

        }
    }

    override fun getItemCount(): Int = recipes.size
}