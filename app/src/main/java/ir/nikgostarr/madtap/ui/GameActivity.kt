package ir.nikgostarr.madtap.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import ir.nikgostarr.madtap.pack.MySharedPref
import ir.nikgostarr.madtap.R
import ir.nikgostarr.madtap.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private var startPlayCounter = 4
    private lateinit var theCounter: MutableLiveData<Int>
    private lateinit var motion1: MotionLayout
    private lateinit var motion2: MotionLayout
    private var onBackPressEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //this.getWindow()!!.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val mediaPlayer = MediaController(applicationContext).show()
        binding.paintView.listener = PaintView.OnWinListener { winner ->
            sho(winner)
            //binding.paintView.refresh()
        }

    }

    override fun onResume() {
        super.onResume()
        startPlayCounter = 4
        theCounter = MutableLiveData()

        setupViews()
    }

    private fun setupViews() {
        binding.paintView.isEnabled = false

        motion1 = binding.motionStartPlayP1
        motion2 = binding.motionLayoutP2

        theCounter.postValue(startPlayCounter)

        motionListener()

        theCounter.observe(this) {
            Log.i("LOG4", "setupViews: $it")
            when (it) {
                4 -> {
                    startPlayCounter--
                    theCounter.postValue(startPlayCounter)
                }
                3 -> {
                    changeCounterImgIcon()
                    motion1.setTransition(R.id.tran_p1_startToMid)
                    motion1.transitionToEnd()
                    motion2.setTransition(R.id.tran_p2_startToMid)
                    motion2.transitionToEnd()
                }
                2 -> {
                    Log.i("LOG3", "onTransitionCompleted LIVE DATA == 2 : $startPlayCounter")
                    changeCounterImgIcon()
                    motion1.setTransition(R.id.tran_p1_startToMid)
                    motion1.transitionToEnd()
                    motion2.setTransition(R.id.tran_p2_startToMid)
                    motion2.transitionToEnd()
                }
                1 -> {
                    changeCounterImgIcon()
                    motion1.setTransition(R.id.tran_p1_startToMid)
                    motion1.transitionToEnd()
                    motion2.setTransition(R.id.tran_p2_startToMid)
                    motion2.transitionToEnd()
                }
                0 -> {
                    changeCounterImgIcon()
                    motion1.setTransition(R.id.tran_p1_startToMid)
                    motion1.transitionToEnd()
                    motion2.setTransition(R.id.tran_p2_startToMid)
                    motion2.transitionToEnd()
                }
            }
        }

    }

    private fun motionListener() {

        motion1.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, endMotionId: Int) {
                if (endMotionId == R.id.mid_p1) {
                    motion1.setTransition(R.id.tran_p1_midToEnd)
                    motion1.transitionToEnd()
                    motion2.setTransition(R.id.tran_p2_midToEnd)
                    motion2.transitionToEnd()
                } else {
                    startPlayCounter--
                    theCounter.postValue(startPlayCounter)
                }

                if (startPlayCounter == -1) {
                    startPlay()
                }

            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })


    }


    private fun startPlay() {

        //binding.motionStartPlayP1.visibility = View.GONE
        YoYo.with(Techniques.FadeOut)
            .duration(300)
            .playOn(binding.motionStartPlayP1)
        //binding.motionLayoutP2.visibility = View.GONE
        YoYo.with(Techniques.FadeOut)
            .duration(300)
            .playOn(binding.motionLayoutP2)
        binding.paintView.isEnabled = true
        onBackPressEnable = true
    }

    private fun changeCounterImgIcon() {
        when (startPlayCounter) {
            3 -> {
                binding.imgStartPlayP1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num3_txt_white
                    )
                )
                binding.imgStartPlayP2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num3_txt_white
                    )
                )
            }
            2 -> {
                binding.imgStartPlayP1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num2_txt_white
                    )
                )
                binding.imgStartPlayP2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num2_txt_white
                    )
                )
            }
            1 -> {
                binding.imgStartPlayP1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num1_txt_white
                    )
                )
                binding.imgStartPlayP2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.num1_txt_white
                    )
                )
            }
            0 -> {
                binding.imgStartPlayP1.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.start_txt_white
                    )
                )
                binding.imgStartPlayP2.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.start_txt_white
                    )
                )
            }
        }
    }

    private fun sho(winner: Int) {

        onBackPressEnable = false
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.newver_dialog)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )

        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        lp.copyFrom(window!!.attributes)
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;

        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        dialog.window!!.setGravity(Gravity.CENTER)
        val layout = dialog.findViewById(R.id.const_winnerDialog) as LinearLayout

        if (winner == 1) {
            layout.rotation = 180F
            layout.setBackgroundResource(R.drawable.bg_dialog_blue)
        } else {
            layout.setBackgroundResource(R.drawable.bg_dialog)
        }

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(false)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                dialog.setCanceledOnTouchOutside(true)
            }
        }, 2000)
        dialog.show()

        dialog.setOnDismissListener {
            startPlayCounter = 4
            theCounter.postValue(startPlayCounter)
            //binding.motionStartPlayP1.visibility = View.VISIBLE
            YoYo.with(Techniques.FadeIn)
                .duration(300)
                .playOn(binding.motionStartPlayP1)

            //binding.motionLayoutP2.visibility = View.VISIBLE
            YoYo.with(Techniques.FadeIn)
                .duration(300)
                .playOn(binding.motionLayoutP2)

            binding.motionStartPlayP1.rotation = 180F

            binding.paintView.refresh()
            binding.paintView.isEnabled = false
        }

        if (MySharedPref().getData(applicationContext) == 0) {

        }

    }

    override fun onRestart() {
        super.onRestart()
        startPlayCounter = 4
        theCounter.postValue(startPlayCounter)
        //binding.motionStartPlayP1.visibility = View.VISIBLE
        YoYo.with(Techniques.FadeIn)
            .duration(300)
            .playOn(binding.motionStartPlayP1)

        //binding.motionLayoutP2.visibility = View.VISIBLE
        YoYo.with(Techniques.FadeIn)
            .duration(300)
            .playOn(binding.motionLayoutP2)
        binding.paintView.refresh()
        binding.paintView.isEnabled = false
    }

    override fun onBackPressed() {
        if (onBackPressEnable) super.onBackPressed()
    }


}

