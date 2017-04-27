package com.khavronsky.appbartest;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//com.khavronsky.appbartest.CustomLayotBehavior
public class CustomLayotBehavior extends CoordinatorLayout.Behavior<CustomCollapsedLayout> {

    private final static float MIN_AVATAR_PERCENTAGE_SIZE = 0.3f;

    private final static int EXTRA_FINAL_AVATAR_PADDING = 80;

    private final static String TAG = "behavior";

    private Context mContext;

    private float mCustomFinalYPosition;

    private float mCustomStartXPosition;

    private float mCustomStartToolbarPosition;

    private float mCustomStartHeight;

    private float mCustomFinalHeight;

    private float mAvatarMaxSize;

    private float mFinalLeftAvatarPadding;

    private float mStartPosition;

    private int mStartXPosition;

    private float mStartToolbarPosition;

    private int mStartYPosition;

    private int mFinalYPosition;

    private int mStartHeight;

    private int mFinalXPosition;

    private float mChangeBehaviorPoint;

    float maxForAlpha, maxForText, alpha;

    private float mStartTitle;

    private float mStartSubTitle;

    private float mStartValue;

    private float mStartUnit;

    private float mStartExtraDescription;

    private boolean first = true;

    private float mStartLayoutY;
    private float mStartLayoutX;

    private float mStartTitleLayoutY;

    private float mStartTitleLayoutX;

    public CustomLayotBehavior() {
    }

    public CustomLayotBehavior(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(final CoordinatorLayout parent, final CustomCollapsedLayout child,
            final View dependency) {
        Log.d("123", "layoutDependsOn: ");
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout parent, final CustomCollapsedLayout child,
            final View dependency) {
        logHell(parent, child, dependency);
        if (first){
            startPosition(child);
        }


//        maybeInitProperties(child, dependency);
        float asd = 56 * mContext.getResources().getDisplayMetrics().density;
        maxForAlpha = dependency.getHeight() - 56 * mContext.getResources()
                .getDisplayMetrics().density;
//        maxForText = dependency.getHeight() - 56 * mContext.getResources().getDisplayMetrics().density * 2f;
        // рассчитываем прозрачность
        alpha = 1 - Math.abs(dependency.getY()) / maxForAlpha;

        child.setY((float) (asd));

//        child.setScaleX(alpha);
//        child.setScaleY(alpha);
        child.setAlpha((float) (alpha *1.1));
        child.getImageView().setScaleY(alpha);
        child.getImageView().setScaleX(alpha);

//        child.getTitle().setTextScaleX(alpha);
//        child.getTitle().setScaleY(alpha);
//        child.getTitle().setY((float) (mStartTitle + dependency.getY()*0.2));
//        child.getSubTitle().setTextScaleX(alpha);
//        child.getSubTitle().setScaleY(alpha);
//        child.getSubTitle().setY((float) (mStartSubTitle  + dependency.getY()*0.3));
//        child.getValue().setTextScaleX(alpha);
//        child.getValue().setScaleY(alpha);
//        child.getValue().setY((float) (mStartValue  + dependency.getY()*1.5));
//        child.getUnit().setTextScaleX(alpha);
//        child.getUnit().setScaleY(alpha);
//        child.getUnit().setY((float) (mStartUnit  + dependency.getY()*1.5));
//        child.getExtraDescription().setTextScaleX(alpha);
//        child.getExtraDescription().setScaleY(alpha);
//        child.getExtraDescription().setY((float) (mStartExtraDescription  + dependency.getY()*1.5));

        child.getTitleLayout().setScaleX(alpha);
        child.getTitleLayout().setScaleY(alpha);
        child.getTitleLayout().setY((float) (mStartTitleLayoutY + dependency.getY()*0.4));
        child.getTitleLayout().setX((float) (mStartTitleLayoutX - correction(child.getTitleLayout().getWidth())));


        child.getLayout().setScaleX(alpha);
        child.getLayout().setScaleY(alpha);
        child.getLayout().setY((float) (mStartLayoutY + dependency.getY()*0.8));
        child.getLayout().setX((float) (mStartLayoutX - correction(child.getLayout().getWidth())));

        return true;
    }

    private float correction (int width){
        return (width - width *alpha)/2;
    }

    private void startPosition(final CustomCollapsedLayout child) {
        mStartTitle = child.getTitle().getY();
        mStartSubTitle = child.getSubTitle().getY();
        mStartValue = child.getValue().getY();
        mStartUnit = child.getUnit().getY();
        mStartExtraDescription = child.getExtraDescription().getY();
        mStartLayoutY = child.getLayout().getY();
        mStartLayoutX = child.getLayout().getX();
        mStartTitleLayoutY = child.getTitleLayout().getY();
        mStartTitleLayoutX = child.getTitleLayout().getX();
        first = false;
    }

    private void logHell(final CoordinatorLayout parent, final CustomCollapsedLayout child,
            final View dependency){
//        Log.d("logHell", "child.getHeight() - " + child.getHeight());
//        Log.d("logHell", "child.getY() - " + child.getY());
//        Log.d("logHell", "child.getX() - " + child.getX());
//        Log.d("logHell", "dependency.getHeight() - " + dependency.getHeight());
//        Log.d("logHell", "dependency.getX() - " + dependency.getX());
        Log.d("logHell", "dependency.getY() - " + dependency.getY());
        Log.d("logHell", "mStartTitle - " + mStartTitle );
        Log.d("logHell", "mStartSubTitle - " + mStartSubTitle);
        Log.d("logHell", "mStartValue - " + mStartValue );
        Log.d("logHell", "mStartUnit - " + mStartUnit );
        Log.d("logHell", "mStartExtraDescription - " + mStartExtraDescription);
        Log.d("logHell", "mStartLayoutY - " + mStartLayoutY );
        Log.d("logHell", "mStartLayoutX - " + mStartLayoutX );
//        Log.d("logHell", "dependency.getTranslationY() - " + dependency.getTranslationY());
//        Log.d("logHell", "dependency.getAlpha() - " + dependency.getAlpha());
//        Log.d("logHell", "dependency.getScaleY() - " + dependency.getScaleY());
        Log.d("logHell", "alpha " + alpha);
    }
}

