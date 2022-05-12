package com.example.sopt_main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_main.databinding.FollowerListBinding

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): FollowerViewHolder{
        val binding =
                FollowerListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(
            private val binding:FollowerListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: FollowerData){
            binding.followerName.text = data.name
            Glide.with(binding.root)
                .load(R.drawable.selfie_true)
                .circleCrop()
                .into(binding.followerProfile)

            }

    }



}