package com.example.fastfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.fastfood.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        binding.lifecycleOwner=this

        binding.tvAppName.animate().alpha(1f).setDuration(1000).withEndAction {
            binding.tvDescribe.animate().alpha(1f).setDuration(1000).withEndAction {
                Handler().postDelayed({
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                },1500)
            }
        }

    }
}