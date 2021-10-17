package com.example.recipeapp

import androidx.recyclerview.widget.DiffUtil

class DiffUtilClass(private val oldList: MutableList<DataItem>, private val newList: MutableList<DataItem>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].author != newList[newItemPosition].author -> false
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].ingredients != newList[newItemPosition].ingredients -> false
            oldList[oldItemPosition].instructions != newList[newItemPosition].instructions -> false
            else -> true
        }
    }

}