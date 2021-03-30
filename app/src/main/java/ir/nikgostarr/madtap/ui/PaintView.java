package ir.nikgostarr.madtap.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintView extends View {
    public PaintView(Context context) {
        super(context);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private final int SIZE = dpToPx(16);

    private Context context;

    private Paint paint1;
    private Paint paint2;
    //    private RectF rect1;
//    private RectF rect2;
    private AnimatableRectF rect1;
    private AnimatableRectF rect2;


    public void refresh() {
//        rect1 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight() / 2);
//        rect2 = new RectF(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight());
        rect1 = new AnimatableRectF(0, 0, getMeasuredWidth(), getMeasuredHeight() / 2);
        rect2 = new AnimatableRectF(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight());
        invalidate();
    }

    @SuppressLint({"ClickableViewAccessibility", "ObjectAnimatorBinding"})
    public void init(Context context) {
        this.context = context;

        paint1 = new Paint();
        paint1.setColor(Color.parseColor("#ff33b5e5"));
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#ffff4444"));
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);

        this.post(new Runnable() {
            @Override
            public void run() {
//                rect1 = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight() / 2);
//                rect2 = new RectF(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight());
                rect1 = new AnimatableRectF(0, 0, getMeasuredWidth(), getMeasuredHeight() / 2);
                rect2 = new AnimatableRectF(0, getMeasuredHeight() / 2, getMeasuredWidth(), getMeasuredHeight());
                invalidate();
            }
        });


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();

                    if (y < rect1.bottom) {
                        //rect 1 y increase
//                        rect1 = new RectF(0,0,rect1.width(), rect1.height() + SIZE);
//                        rect2 = new RectF(0,rect1.height(),rect2.width(), rect2.bottom);
                        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(rect1, "left", 0, 0);
                        ObjectAnimator animateRight = ObjectAnimator.ofFloat(rect1, "right", rect1.width(), rect1.width());
                        ObjectAnimator animateTop = ObjectAnimator.ofFloat(rect1, "top", 0, 0);
                        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(rect1, "bottom", rect1.bottom, rect1.height() + SIZE);
                        animateBottom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                postInvalidate();
                            }
                        });
                        AnimatorSet rectAnimation = new AnimatorSet();
                        rectAnimation.playTogether(animateLeft, animateRight, animateTop, animateBottom);
                        rectAnimation.setDuration(100).start();

                        ObjectAnimator animateLeft2 = ObjectAnimator.ofFloat(rect2, "left", 0, 0);
                        ObjectAnimator animateRight2 = ObjectAnimator.ofFloat(rect2, "right", rect2.width(), rect2.width());
                        ObjectAnimator animateTop2 = ObjectAnimator.ofFloat(rect2, "top", rect2.top, rect1.height() + SIZE);
                        ObjectAnimator animateBottom2 = ObjectAnimator.ofFloat(rect2, "bottom", rect2.bottom, rect2.bottom);
                        animateBottom2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                postInvalidate();
                            }
                        });
                        AnimatorSet rectAnimation2 = new AnimatorSet();
                        rectAnimation2.playTogether(animateLeft2, animateRight2, animateTop2, animateBottom2);
                        rectAnimation2.setDuration(100).start();

//                        rect1 = new RectF(0,0,rect1.width(), rect1.height() + SIZE);
//                        rect2 = new RectF(0,rect1.height(),rect2.width(), rect2.bottom);
//                        rect1 = new AnimatableRectF(0, 0, rect1.width(), rect1.height() + SIZE);
//                        rect2 = new AnimatableRectF(0, rect1.height(), rect2.width(), rect2.bottom);
                    } else {

                        ObjectAnimator animateLeft = ObjectAnimator.ofFloat(rect1, "left", 0, 0);
                        ObjectAnimator animateRight = ObjectAnimator.ofFloat(rect1, "right", rect1.width(), rect1.width());
                        ObjectAnimator animateTop = ObjectAnimator.ofFloat(rect1, "top", 0, 0);
                        ObjectAnimator animateBottom = ObjectAnimator.ofFloat(rect1, "bottom", rect1.bottom, rect1.height() - SIZE);
                        animateBottom.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                postInvalidate();
                            }
                        });
                        AnimatorSet rectAnimation = new AnimatorSet();
                        rectAnimation.playTogether(animateLeft, animateRight, animateTop, animateBottom);
                        rectAnimation.setDuration(100).start();

                        ObjectAnimator animateLeft2 = ObjectAnimator.ofFloat(rect2, "left", 0, 0);
                        ObjectAnimator animateRight2 = ObjectAnimator.ofFloat(rect2, "right", rect2.width(), rect2.width());
                        ObjectAnimator animateTop2 = ObjectAnimator.ofFloat(rect2, "top", rect2.top, rect1.height() - SIZE);
                        ObjectAnimator animateBottom2 = ObjectAnimator.ofFloat(rect2, "bottom", rect2.bottom, rect2.bottom);
                        animateBottom2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                postInvalidate();
                            }
                        });
                        AnimatorSet rectAnimation2 = new AnimatorSet();
                        rectAnimation2.playTogether(animateLeft2, animateRight2, animateTop2, animateBottom2);
                        rectAnimation2.setDuration(100).start();
//                        rect1 = new RectF(0,0,rect1.width(), rect1.height() - SIZE);
//                        rect2 = new RectF(0,rect1.height(),rect2.width(), rect2.bottom);
                    }
                    invalidate();

                    if (rect1.height() < dpToPx(48)) {
                        // 2 wins
                        listener.onWin(2);
                    }
                    if (rect2.height() < dpToPx(48)) {
                        // 1 wins
                        listener.onWin(1);
                    }
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (rect1 != null && rect2 != null && paint1 != null) {
            canvas.drawRect(rect1, paint1);
            canvas.drawRect(rect2, paint2);
        }


    }

    public OnWinListener listener = null;

    public interface OnWinListener {
        void onWin(int winner);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
