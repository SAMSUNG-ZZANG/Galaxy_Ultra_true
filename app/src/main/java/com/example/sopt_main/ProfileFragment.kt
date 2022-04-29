package com.example.sopt_main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_main.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var testViewPagerAdaptor: TestViewPagerAdaptor



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)


        transactionFragment()


        return binding.root
    }


    private fun transactionFragment(){

        val fragment1 =FollowerFragment()
        val fragment2 =RepositoryFragment()

        parentFragmentManager.beginTransaction().add(R.id.profile_fragment,fragment1).commit()

        binding.homeFollowerBtn.setOnClickListener{
            val transaction = childFragmentManager.beginTransaction()

            transaction.replace(R.id.profile_fragment,fragment1)
            transaction.commit()
        }

        binding.homeRepoBtn.setOnClickListener{
            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.profile_fragment,fragment2)
            transaction.commit()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}