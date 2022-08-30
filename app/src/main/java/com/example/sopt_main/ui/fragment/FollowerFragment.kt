package com.example.sopt_main.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.sopt_main.R
import com.example.sopt_main.adapter.FollowerAdapter
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentFollowerBinding
import com.example.sopt_main.ui.viewmodel.FollowerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private lateinit var followerAdapter: FollowerAdapter
    private val viewModel: FollowerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFollowers()
        followerUserNetwork()
    }


    private fun followerUserNetwork(){
        viewModel.followerList.observe(viewLifecycleOwner) {
            followerAdapter = FollowerAdapter()
            binding.rvFollower.adapter = followerAdapter
            if (it != null) {
                followerAdapter.followerList = it.toMutableList()
            }
            followerAdapter.notifyDataSetChanged()
        }
    }

}