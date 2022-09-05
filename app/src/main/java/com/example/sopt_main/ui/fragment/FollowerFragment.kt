package com.example.sopt_main.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sopt_main.R
import com.example.sopt_main.adapter.FollowerAdapter
import com.example.sopt_main.base.BaseFragment
import com.example.sopt_main.databinding.FragmentFollowerBinding
import com.example.sopt_main.server.response.ResponseFollowerInfo
import com.example.sopt_main.ui.activity.DetailActivity
import com.example.sopt_main.ui.viewmodel.FollowerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : BaseFragment<FragmentFollowerBinding>(R.layout.fragment_follower) {
    private var followerAdapter: FollowerAdapter? = FollowerAdapter()
    private val viewModel: FollowerViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        itemClickEvent()
        viewModel.fetchFollowers()
        followerUserNetwork()
    }


    private fun followerUserNetwork() {
        viewModel.followerList.observe(viewLifecycleOwner) {
            if (it != null) {
                followerAdapter?.followerList = it.toMutableList()
            }
            followerAdapter?.notifyDataSetChanged()
        }
    }

    private fun itemClickEvent() {
            followerAdapter?.setItemClickListener(object : FollowerAdapter.OnItemClickListener {
                override fun onClick(data:ResponseFollowerInfo) {
                    // 클릭 시 이벤트 작성
                    val image = data.avatar_url
                    val login = data.login
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("image", image)
                        putExtra("login", login)
                    }
                    startActivity(intent)
                }
            })



    }
}