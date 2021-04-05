package ir.nikgostarr.madtap.ui

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ir.nikgostarr.madtap.api.*
import ir.nikgostarr.madtap.databinding.ActivityMain2Binding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //this.window!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding.cardStartGame.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }


        Log.e("LOG1", "onCreate: bazaar not ${applicationContext.packageName}")
        binding.cardShare.setOnClickListener {
            try {
                val url = "bazaar://details?id=${applicationContext.packageName}"
                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                    .setPackage("com.farsitel.bazaar")
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("LOG1", "onCreate: bazaar not installed")
            }
        }


        RetrofitBuilder.getClient().create(ApiClass::class.java).version("madtap").enqueue(object :
            Callback<List<VersionModel>> {

            override fun onFailure(call: Call<List<VersionModel>>, t: Throwable) {
                Log.e("LOG1", "onFailure: " + t.toString())
            }

            override fun onResponse(
                call: Call<List<VersionModel>>,
                response: Response<List<VersionModel>>
            ) {
                if (response.body() != null) {
                    val pInfo = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0)
                    Log.i("LOG3", "onResponse:${response.body()!![0].version.toString()},,,${pInfo.versionCode} ")
                    if (pInfo.versionCode < response.body()!![0].version.toInt()) {
                        val dialog = UpdateDialog()
                        dialog.show(supportFragmentManager, null)
                    }
                }
            }
        })
    }


}