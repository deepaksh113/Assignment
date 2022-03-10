package com.ezetap.assignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ezetap.assignment.databinding.ActivityMain2Binding
import com.ezetap.network.UIModel
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        if (uiData != null) {
            val uiModel = uiData
            if (uiModel?.heading_text != null) {
                binding.textViewHeading.visibility = View.VISIBLE
                binding.textViewHeading.text = uiModel.heading_text
            }
            if (uiModel?.logo_url != null) {
                binding.imageViewLogo.visibility = View.VISIBLE
                Picasso.with(this).load(uiModel.logo_url).into(binding.imageViewLogo)
            }
            val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT
            params.width = LinearLayout.LayoutParams.MATCH_PARENT
            params.setMargins(20, 40, 20, 40)

            uiModel?.uidata?.forEach {uidata ->
                when (uidata.uitype) {
                    "label" -> {
                        val textView: TextView = TextView(this).apply {
                            text = uidata.value
                            textSize = 18.0F
                            layoutParams = params
                        }
                        binding.linearLayout.addView(textView)
                    }
                    "edittext" -> {
                        val editText = EditText(this).apply {
                            hint = uidata.hint
                            textSize = 18.0F
                            layoutParams = params
                        }
                        binding.linearLayout.addView(editText)
                    }
                    "button" -> {
                        val button = Button(this).apply {
                            width = LinearLayout.LayoutParams.MATCH_PARENT
                            height = LinearLayout.LayoutParams.WRAP_CONTENT
                            text = uidata.value
                            layoutParams = params
                        }
                        binding.linearLayout.addView(button)
                    }
                }
            }
        }
    }

    companion object {
        var uiData: UIModel? = null
    }
}