package com.ezetap.assignment

import android.view.View
import com.ezetap.network.Api
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.reflect.Modifier
import java.util.concurrent.TimeUnit

class ApiClass {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL: String = "https://demo.ezetap.com/"

        private fun init(): Retrofit {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(180, TimeUnit.SECONDS)
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
                val gson = GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                    .serializeNulls()
                    .setLenient()
                    .create()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit as Retrofit
        }

        fun callGetImageApi(): String? {
            val apis: Api = init().create(Api::class.java)

            val call: Call<String> = apis.fetchImage()

            var data: String? = null
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        try {
                            val gson = GsonBuilder().create()

                            val data1: UIModel = gson.fromJson(response.body(),
                                object : TypeToken<UIModel>() {}.type
                            )
                            data = data1.logo_url
                        } catch (e: Exception) {
                            e.printStackTrace()

                        }
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    data = null
                }
            })
            return data
        }

        fun callFetchUIApi(view: View, onComplete: OnComplete<UIModel?>) {
            val apis: Api = init().create(Api::class.java)

            val call: Call<String> = apis.fetchCustomUI()
            view.visibility = View.VISIBLE
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    view.visibility = View.GONE
                    if (response.isSuccessful) {
                        try {
                            val gson = GsonBuilder().create()

                            val data1: UIModel = gson.fromJson(response.body(),
                                object : TypeToken<UIModel>() {}.type
                            )
                            onComplete.onResponse(data1)
                        } catch (e: Exception) {
                            e.printStackTrace()

                        }
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    view.visibility = View.GONE
                    onComplete.onResponse(null)
                }
            })
        }
    }
}