package com.ezetap.assignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ezetap.assignment.databinding.ActivityMainBinding
import com.ezetap.network.UIModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callApi()

    }

    private fun callApi() {
        ApiClass.callFetchUIApi(binding.progressBar ,object : OnComplete<UIModel?> {
            override fun onResponse(model: UIModel?) {
                if (model != null) {
                    MainActivity2.uiData = model
                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    overridePendingTransition(0,0)
                    startActivity(intent)
                    this@MainActivity.finish()
                }
            }
        })
    }
}