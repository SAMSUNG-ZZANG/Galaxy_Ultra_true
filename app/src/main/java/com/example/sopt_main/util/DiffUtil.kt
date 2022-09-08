package com.example.sopt_main.util

import androidx.recyclerview.widget.DiffUtil

class DiffUtil(private val oldList: List<Any>, private val newList: List<Any>) :
    DiffUtil.Callback() {

    //현재 리스트에 있는 리스트 사이즈
    override fun getOldListSize(): Int = oldList.size

    //새로운 리스트(추가될)에 있는 리스트 사이즈
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.hashCode()== newItem.hashCode()
    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}