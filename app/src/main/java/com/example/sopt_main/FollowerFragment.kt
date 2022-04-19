package com.example.sopt_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater,container,false)

        initFollowerAdaptor()
        return binding.root
    }

    private fun initFollowerAdaptor(){
        followerAdapter = FollowerAdapter()
        _binding?.rvFollower?.adapter = followerAdapter

        followerAdapter.followerList.addAll(
                listOf(
                        FollowerData("이강민", "안드로이드 파트장"),
                        FollowerData("이강민", "안드로이드 파트장"),
                        FollowerData("이강민", "안드로이드 파트장"),
                        FollowerData("이강민", "안드로이드 파트장")

                )
        )
        followerAdapter.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}