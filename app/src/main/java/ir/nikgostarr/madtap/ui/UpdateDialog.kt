package ir.nikgostarr.madtap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ir.nikgostarr.madtap.databinding.UpdateDialogBinding
import java.lang.Exception

class UpdateDialog : DialogFragment() {

    private var _binding : UpdateDialogBinding? = null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = UpdateDialogBinding.inflate(inflater)

        setupViews(view)
        return binding.root
    }

    private fun setupViews(view: View?) {

        binding.cardUpdateDialogUpdate.setOnClickListener {
            try {
                val url = "bazaar://details?id=$PACKAGE_NAME"
                val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)).setPackage("com.farsitel.bazaar")
                startActivity(intent)
            }catch (e:Exception){

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}