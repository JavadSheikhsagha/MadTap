package ir.nikgostarr.madtap.pack

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class MySharedPref {

    fun saveData(context : Context){
        val sharedPref = context.getSharedPreferences("newShare",MODE_PRIVATE)
        val editor = sharedPref.edit {
            this.putInt("shared",1)
            this.commit()
            this.apply()
        }
    }

    fun getData(context: Context):Int{
        val sharedPref = context.getSharedPreferences("newShare", MODE_PRIVATE)
        return sharedPref.getInt("shared",0)
    }
}