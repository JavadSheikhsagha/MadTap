package ir.nikgostarr.madtap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ir.nikgostarr.madtap.BuildConfig
import ir.nikgostarr.madtap.api.ApiClass
import ir.nikgostarr.madtap.api.BasicAuthInterceptor
import ir.nikgostarr.madtap.api.VerModel1
import ir.nikgostarr.madtap.api.VersionModel
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
            .baseUrl("http://hnikanm74.gigfa.com/")
            .build()

        binding.cardShare.setOnClickListener {
            val url = "bazaar://details?id=$PACKAGE_NAME"
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)).setPackage("com.farsitel.bazaar")
            startActivity(intent)
        }

        retrofit.create(ApiClass::class.java).getVer().enqueue(object : Callback<VersionModel> {
            override fun onResponse(call: Call<VersionModel>, response: Response<VersionModel>) {
                val verCode = BuildConfig.VERSION_CODE
                if (verCode < response.body()!!.version.toInt()) {
                    val dialog = UpdateDialog()
                    dialog.show(supportFragmentManager, null)
                }
            }

            override fun onFailure(call: Call<VersionModel>, t: Throwable) {
                Log.e("LOG1", "onFailure: " + t.toString())
            }
        })

    }


}