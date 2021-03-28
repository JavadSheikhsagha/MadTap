package ir.nikgostarr.madtap.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ir.nikgostarr.madtap.api.ApiClass
import ir.nikgostarr.madtap.api.BasicAuthInterceptor
import ir.nikgostarr.madtap.BuildConfig
import ir.nikgostarr.madtap.databinding.ActivityMain2Binding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //this.window!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding.cardStartGame.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(
                BasicAuthInterceptor(
                    "gigfa_28246037",
                    "123@Pass"
                )
            )
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://hnikanm74.gigfa.com/click_fast/")
            .build()

        retrofit.create(ApiClass::class.java).ver.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val verCode = BuildConfig.VERSION_CODE.toString()
                if (verCode != response.body()) {
                    val dialog = UpdateDialog()
                    dialog.show(supportFragmentManager, null)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("LOG1", "onFailure: "+t.toString())
            }
        })

    }


}