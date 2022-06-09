package com.example.sopt_main.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.sopt_main.Server.GithubServiceCreator
import com.example.sopt_main.Server.response.ResponseFollowerInfo
import com.example.sopt_main.adapter.FollowerAdapter
import com.example.sopt_main.databinding.FragmentFollowerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowerFragment : Fragment() {

    private lateinit var followerAdapter: FollowerAdapter
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater,container,false)


        followerUserNetwork()
        binding.rvFollower.addItemDecoration(DividerItemDecoration(context,1)) // 구분선 설정
        return binding.root
    }


    private fun followerUserNetwork(){

        val call: Call<List<ResponseFollowerInfo>> = GithubServiceCreator.githubService.getUserInfo("jinsilWoo")

        call.enqueue(object : Callback<List<ResponseFollowerInfo>> {

                override fun onResponse(
                    call: Call<List<ResponseFollowerInfo>>,
                    response: Response<List<ResponseFollowerInfo>>
                ) {
                    val data = response.body()

                    followerAdapter = FollowerAdapter()
                    _binding?.rvFollower?.adapter = followerAdapter

                    if (data != null) {
                        followerAdapter.followerList = data.toMutableList()
                    }
                    followerAdapter.notifyDataSetChanged()


                }

                override fun onFailure(call: Call<List<ResponseFollowerInfo>>, t: Throwable) {
                    Log.e("GitHubTest", "error:$t")
                }
            }
        )
    }


//    private fun initFollowerAdaptor(){
//
//        val img ="https://www.google.com/imgres?imgurl=https%3A%2F%2Fpng.clipart.me%2Fistock%2Fpreviews%2F9349%2F93493545-people-icon.jpg&imgrefurl=https%3A%2F%2Fkr.clipart.me%2Fistock%2Fpeople-icon-678625&tbnid=LlYcmUvC7G6UYM&vet=12ahUKEwihlof27bn3AhWtQ_UHHc5pBZwQMygJegUIARDuAQ..i&docid=io4QQzH1abvDgM&w=189&h=200&q=%EC%82%AC%EB%9E%8C%20%EC%95%84%EC%9D%B4%EC%BD%98&hl=ko&ved=2ahUKEwihlof27bn3AhWtQ_UHHc5pBZwQMygJegUIARDuAQ"
//        followerAdapter = FollowerAdapter()
//        _binding?.rvFollower?.adapter = followerAdapter
//
//        followerAdapter.followerList.addAll(
//                listOf(
//                        FollowerData(img,"우진실", "안드로이드 파트원"),
//                        FollowerData(img,"최유리", "안드로이드 파트원"),
//                        FollowerData(img,"유지민", "안드로이드 파트원"),
//                        FollowerData(img,"이강민", "안드로이드 파트장")
//
//                )
//        )
//        followerAdapter.notifyDataSetChanged()
//
//    }







        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}