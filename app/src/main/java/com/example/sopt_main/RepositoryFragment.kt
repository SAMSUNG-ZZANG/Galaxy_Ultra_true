package com.example.sopt_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.databinding.FragmentRepositoryBinding


class RepositoryFragment : Fragment() {

    private lateinit var repoAdaptor: RepositoryAdaptor
    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentRepositoryBinding.inflate(layoutInflater,container,false)

        initFollowerAdaptor()

        binding.rvFollower.addItemDecoration(VerticalItemDecorator(20))
        binding.rvFollower.addItemDecoration(HorizontalItemDecorator(20))

        return binding.root
    }

    private fun initFollowerAdaptor(){
        repoAdaptor = RepositoryAdaptor()
        _binding?.rvFollower?.adapter = repoAdaptor

        repoAdaptor.repoList.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리이구요,,안드로이드 과제 레포지토리 입니다아아아아아", "안드로이드 파트 과제 중 입니다~!~!~! 과제 중중 과제 하는 중중"),
                RepositoryData("ios 과제 레포지토리", "ios 파트 과제"),
                RepositoryData("서버 과제 레포지토리", "서버 파트 과제"),
                RepositoryData("기획 과제 레포지토리", "기획 파트 과제")

            )
        )
        repoAdaptor.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}