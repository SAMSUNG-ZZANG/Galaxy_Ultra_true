package com.example.sopt_main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.databinding.FollowerListBinding
import com.example.sopt_main.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var followerListBinding: FollowerListBinding
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater,container,false)

        initFollowerAdaptor()

        binding.rvFollower.addItemDecoration(VerticalItemDecorator(20))
        binding.rvFollower.addItemDecoration(HorizontalItemDecorator(20))

        return binding.root
    }

    private fun initFollowerAdaptor(){
        followerAdapter = FollowerAdapter()
        _binding?.rvFollower?.adapter = followerAdapter

        followerAdapter.followerList.addAll(
                listOf(
                        FollowerData("우진실", "안드로이드 파트원"),
                        FollowerData("최유리", "안드로이드 파트원"),
                        FollowerData("유지민", "안드로이드 파트원"),
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