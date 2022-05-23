package com.example.sopt_main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_main.data.model.RepositoryData
import com.example.sopt_main.databinding.RepositoryListBinding

class RepositoryAdaptor: RecyclerView.Adapter<RepositoryAdaptor.RepositoryViewHolder>(){
    val repoList = mutableListOf<RepositoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            RepositoryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    class RepositoryViewHolder(
        private val binding: RepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: RepositoryData){
            binding.repoListTitle.text = data.title
            binding.repoListContent.text = data.content
        }

    }
}