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
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //this.window!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        binding.cardStartGame.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }

//        val client = OkHttpClient.Builder()
//            .addInterceptor(
//                BasicAuthInterceptor(
//                    "gigfa_28246037",
//                    "123@Pass"
//                )
//            )
//            .build()
//
//
//        val retrofit = Retrofit.Builder()
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("http://hnikanm74.gigfa.com/")
//            .build()
        //val queue = Volley.newRequestQueue(this)
        //val url = URL("http://hnikanm74.gigfa.com/click_fast/version.php")

        binding.cardShare.setOnClickListener {
            try {
                val url = "bazaar://details?id=$PACKAGE_NAME"
                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
                    .setPackage("com.farsitel.bazaar")
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("LOG1", "onCreate: bazaar not installed")
            }
        }

//        val url = "http://hnikanm74.gigfa.com/click_fast/version.php"
//        val queue = Volley.newRequestQueue(this)
//        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
//            Log.i("TAG3", "onCreate: ${response.toString()}")
//        },
//            { error ->
//                Log.i("TAG3", "onCreate: ${error.toString()}")
//            }
//        )
//        queue.add(jsonObjectRequest)
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

// Access the RequestQueue through your singleton class.
        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

//        val model =URL("http://hnikanm74.gigfa.com/click_fast/").readText()
//        Log.i("LOG3", "onCreate: "+model)
//        CoroutineScope(Dispatchers.IO).launch {
//            val model =URL("http://hnikanm74.gigfa.com/click_fast/").readText()
//            Log.i("LOG3", "onCreate: "+model)
//        }
//        val queue = Volley.newRequestQueue(this)
//        val url = "http://hnikanm74.gigfa.com/click_fast/version.php"
//
//// Request a string response from the provided URL.
//        val stringRequest = StringRequest(Request.Method.GET, url,
//            { response ->
//                // Display the first 500 characters of the response string.
//                Log.i("LOG3", "Response is: ${response}")
//            },
//            {
//                Log.i("LOG3", "Response is: ${it.toString()}")
//            })
//        queue.add(stringRequest)
//        queue.start()

//        val url = "http://hnikanm74.gigfa.com/click_fast/version.php"
//        val stringRequest = StringRequest(url,
//            { response ->
//                Log.i("LOG5", "onCreate: $response")
//            }) {
//            Log.i("LOG5", "onCreate: $it")
//            // Anything you want
//        }
//        val requestQueue = Volley.newRequestQueue(applicationContext)
//        requestQueue.add(stringRequest)
//        requestQueue.start()

//        //val url = URL("http://www.android.com/")
//        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
//        try {
//            val `in`: InputStream = BufferedInputStream(urlConnection.getInputStream())
//            Log.i(
//                "LOG1", "onCreate: ${`in`.read().toString()}"
//            )
//        } finally {
//            urlConnection.disconnect()
//        }

        RetrofitBuilder.getClient().create(ApiClass::class.java).version().enqueue(object :
            Callback<List<VersionModel>> {

            override fun onFailure(call: Call<List<VersionModel>>, t: Throwable) {
                Log.e("LOG1", "onFailure: " + t.toString())
            }

            override fun onResponse(
                call: Call<List<VersionModel>>,
                response: Response<List<VersionModel>>
            ) {
                val verCode = BuildConfig.VERSION_CODE
                if (verCode < response.body()!![0].version.toInt()) {
                    val dialog = UpdateDialog()
                    dialog.show(supportFragmentManager, null)
                }
            }
        })
        //apiHit()

    }
//
//    private fun apiHit() {
//        val url = "http://hnikanm74.gigfa.com/click_fast/version.php"
//        val queue : RequestQueue = Volley.newRequestQueue(this)
//        val request =  JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject? ->
//
//            Log.i("LOG", "apiHit: ${response.toString()}" )
//        }, { error: VolleyError? ->
//            Log.i("LOG", "apiHit: ${error.toString()}" )
//        })
//        queue.add(request)
//    }


}