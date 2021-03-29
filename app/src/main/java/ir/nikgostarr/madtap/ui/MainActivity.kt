package ir.nikgostarr.madtap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Directory.PACKAGE_NAME
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

        binding.cardShare.setOnClickListener {
            val url = "bazaar://details?id=$PACKAGE_NAME"
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)).setPackage("com.farsitel.bazaar")
            startActivity(intent)
        }

        retrofit.create(ApiClass::class.java).ver.enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                val verCode = BuildConfig.VERSION_CODE
                if (verCode < response.body()!!.toInt()) {
                    val dialog = UpdateDialog()
                    dialog.show(supportFragmentManager, null)
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("LOG1", "onFailure: "+t.toString())
            }
        })

    }


}