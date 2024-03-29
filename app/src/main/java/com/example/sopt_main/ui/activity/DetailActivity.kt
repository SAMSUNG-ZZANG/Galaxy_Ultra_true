package com.example.sopt_main.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.sopt_main.R
import com.example.sopt_main.base.BaseActivity
import com.example.sopt_main.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>
    (R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }


    private fun getData() {
        binding.apply {
            with(intent) {
                Glide.with(this@DetailActivity).load(getStringExtra("image")).into(detailImage)
                detailName.text = getStringExtra("login")
            }
        }
    }
}