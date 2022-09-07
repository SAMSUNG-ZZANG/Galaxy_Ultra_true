package com.example.sopt_main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_main.MyItemTouchHelperCallBack
import com.example.sopt_main.databinding.FollowerListBinding
import com.example.sopt_main.server.response.ResponseFollowerInfo

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(),
    MyItemTouchHelperCallBack.ItemTouchHelperListener {

    var followerList = mutableListOf<ResponseFollowerInfo>()
    private var itemClickListener: OnItemClickListener? = null


    interface OnItemClickListener {
        fun onClick(data: ResponseFollowerInfo)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            FollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.onClick(followerList[position])
        }

    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(
        private val binding: FollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseFollowerInfo) {
            binding.followerName.text = data.login
            Glide.with(binding.root)
                .load(data.avatar_url)
                .circleCrop()
                .into(binding.followerProfile)
        }
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int): Boolean {
        val follower = followerList[fromPosition]
        // 리스트 갱신
        followerList.removeAt(fromPosition)
        followerList.add(toPosition, follower)

        // fromPosition에서 toPosition으로 아이템 이동 공지
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwiped( position: Int) {

    }



}