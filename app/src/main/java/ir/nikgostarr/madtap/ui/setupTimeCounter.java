package ir.nikgostarr.madtap.ui;

import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

public class setupTimeCounter {

    private void setupTimeCounter(TextView timer,int counter) {

        //hide animation
        AnimationSet hideSet = new AnimationSet(true);
        hideSet.setDuration(250);
        AlphaAnimation hideAlpha = new AlphaAnimation(1f, 0f);
        ScaleAnimation hideScale = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        hideSet.addAnimation(hideAlpha);
        hideSet.addAnimation(hideScale);

        ///show animation
        AnimationSet showSet = new AnimationSet(true);
        showSet.setDuration(250);
        AlphaAnimation showAlpha = new AlphaAnimation(0f, 1f);
        ScaleAnimation showScale = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        showSet.addAnimation(showAlpha);
        showSet.addAnimation(showScale);

        //starting animation
        AnimationSet startingAnim = new AnimationSet(true);
        startingAnim.addAnimation(hideAlpha);
        startingAnim.addAnimation(hideScale);
        startingAnim.setDuration(0);

        hideSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                timer.setVisibility(View.GONE);
                if (counter == 3) timer.setText("2");
                if (counter == 2) timer.setText("1");
                if (counter == 1) timer.setText("START");
                if (counter == 0){
                    //do your work here
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timer.startAnimation(showSet);
                    }
                }, 250);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        showSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                timer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //counter--;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timer.startAnimation(hideSet);
                    }
                }, 250);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        startingAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                timer.startAnimation(showSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        timer.startAnimation(startingAnim);

    }
}
