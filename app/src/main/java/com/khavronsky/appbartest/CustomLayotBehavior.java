package com.khavronsky.appbartest;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

//com.khavronsky.appbartest.CustomLayotBehavior
public class CustomLayotBehavior extends CoordinatorLayout.Behavior<CustomCollapsedLayout> {

    private final static String TAG = "behavior";

    private Context mContext;

    private int standartY;

    float maxForAlpha, maxForText, alpha;

    private float mStartTitle;

    private float mStartSubTitle;

    private float mStartValue;

    private float mStartUnit;

    private float mStartExtraDescription;

    private float mStartLayoutY;

    private float mStartLayoutX;

    private float mStartTitleLayoutY;

    private float mStartTitleLayoutX;

    private float mStartImageViewX;

    private float mStartImageViewY;

    private boolean first = true;

    private ICCListener mListener;

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

        float asd = 56 * mContext.getResources().getDisplayMetrics().density;
        maxForAlpha = dependency.getHeight() - 56 * mContext.getResources()
                .getDisplayMetrics().density;
        alpha = (float) (1 - Math.abs(dependency.getY()) / maxForAlpha * 0.5);

        child.setY((float) (asd));
        child.setAlpha((float) (alpha));
        if (first) {
            startPosition(child);
        }
        int[] a1 = {0, 0};
        child.getLocationOnScreen(a1);
        standartY = a1[1];
        child.getImageView().setScaleY((float) (alpha));
        child.getImageView().setScaleX((float) (alpha));
        if (dependency.getY() < -450.0f) {
//            child.getImageView().setX((float) (mStartImageViewX + correction(child.getImageView().getWidth())));
            child.getImageView().setY((float) (mStartImageViewY + (dependency.getY() + 450.0f)));
        } else {
            child.getImageView().setY(mStartImageViewY);

        }

        child.getTitleLayout().setScaleX(alpha);
        child.getTitleLayout().setScaleY(alpha);
        child.getTitleLayout().setY((float) (mStartTitleLayoutY + dependency.getY() * 0.45));
        child.getTitleLayout().setX((float) (mStartTitleLayoutX - correction(child.getTitleLayout().getWidth())));
        hideView(child.getTitle(), true);

        hideView(child.getSubTitle(), true);

        child.getLayout().setScaleX(alpha);
        child.getLayout().setScaleY(alpha);
        if (dependency.getY() > -450.0f) {
            child.getLayout().setY((float) (mStartLayoutY + dependency.getY() * 0.8));
        } else {
            child.getLayout().setY((float) ((mStartLayoutY - 360.0f) + dependency.getY() + 450.0f));
        }
        child.getLayout().setX((float) (mStartLayoutX - correction(child.getLayout().getWidth())));
        hideView2(child.getImageView(),child.getLayout());

        return true;
    }

    void hideView(View v, boolean title){
        int[] a = {0, 0};
        v.getLocationOnScreen(a);
        Log.d(TAG, "hideView: " + v.getTag() + " ___ " + standartY + " <-> " + a[1]);
        int visibility;
        if (standartY > a[1] - (v.getY()*(1-alpha))) {
            visibility = View.INVISIBLE;
            v.setVisibility(visibility);
            visibility = View.VISIBLE;
        } else {
            visibility = View.VISIBLE;
            v.setVisibility(visibility);
            visibility = View.INVISIBLE;
        }

        if (title){
            mListener.setTitleVisibility(visibility);
        } else {
            mListener.setSubTitleVisibility(visibility);
        }
    }

    void hideView2(View v, View v2) {
        int[] a = {0, 0};
        v.getLocationOnScreen(a);
        Log.d(TAG, "hideView: " + v.getTag() + " ___ " + standartY + " <-> " + a[1]);

        if (standartY > a[1] - (v.getHeight()/2 - v.getHeight()/2*alpha  )) {
            v.setVisibility(View.INVISIBLE);
            v2.setVisibility(View.INVISIBLE);
        } else {
            v.setVisibility(View.VISIBLE);
            v2.setVisibility(View.VISIBLE);
        }
    }

    private float correction(int width) {
        return (width - width * alpha) / 2;
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
        mStartImageViewY = child.getImageView().getY();
        mStartImageViewX = child.getImageView().getX();
        first = false;
    }

    private void logHell(final CoordinatorLayout parent, final CustomCollapsedLayout child,
            final View dependency) {
//        Log.d("logHell", "child.getHeight() - " + child.getHeight());
//        Log.d("logHell", "child.getY() - " + child.getY());
//        Log.d("logHell", "child.getX() - " + child.getX());
//        Log.d("logHell", "dependency.getHeight() - " + dependency.getHeight());
//        Log.d("logHell", "dependency.getX() - " + dependency.getX());
        Log.d("logHell", "dependency.getY() - " + dependency.getY());
        Log.d("logHell", "mStartTitle - " + mStartTitle);
        Log.d("logHell", "mStartSubTitle - " + mStartSubTitle);
        Log.d("logHell", "mStartValue - " + mStartValue);
        Log.d("logHell", "mStartUnit - " + mStartUnit);
        Log.d("logHell", "mStartExtraDescription - " + mStartExtraDescription);
        Log.d("logHell", "mStartLayoutY - " + mStartLayoutY);
        Log.d("logHell", "mStartLayoutX - " + mStartLayoutX);
//        Log.d("logHell", "dependency.getTranslationY() - " + dependency.getTranslationY());
//        Log.d("logHell", "dependency.getAlpha() - " + dependency.getAlpha());
//        Log.d("logHell", "dependency.getScaleY() - " + dependency.getScaleY());
        Log.d("logHell", "alpha " + alpha);
    }

    void setICCListener(ICCListener listener){
        this.mListener = listener;
    }

    public interface ICCListener{
        void setTitleVisibility(int visibility);
        void setSubTitleVisibility(int visibility);
    }
}

