package com.khavronsky.appbartest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.69f;

    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheSubTitleVisible = false;

    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    Toolbar toolbar;
    private TextView mTitle;
    private TextView mSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout collapsingLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        CustomCollapsedLayout customCollapsedLayout = (CustomCollapsedLayout) findViewById(R.id.customLayout);
        ((CustomLayotBehavior) ((CoordinatorLayout.LayoutParams) customCollapsedLayout.getLayoutParams())
                .getBehavior()).setICCListener(new CustomLayotBehavior.ICCListener() {
            @Override
            public void setTitleVisibility(final int visibility) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, visibility);
            }

            @Override
            public void setSubTitleVisibility(final int visibility) {
                startAlphaAnimation(mSubTitle, ALPHA_ANIMATIONS_DURATION, visibility);
            }
        });

        mTitleContainer = (LinearLayout) findViewById(R.id.title_container);
        mTitle = (TextView) findViewById(R.id.toolbar_title);
        mSubTitle = (TextView) findViewById(R.id.toolbar_sub_title);
        mTitle.setText("Пляски святого Витта");
        mSubTitle.setText("Супер Кардио");
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(this);

//        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(final View v) {
        onBackPressed();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
//        int maxScroll = appBarLayout.getTotalScrollRange();
//        float percentage = (float) Math.abs(offset) / (float) maxScroll;
//        Log.d("onOffsetChanged", "offset: " + offset);
//        Log.d("onOffsetChanged", "maxScroll: " + maxScroll);
//        Log.d("onOffsetChanged", "toolbar.getHeight: " + toolbar.getHeight());
//        handleAlphaOnTitle(percentage);
//        handleToolbarTitleVisibility(percentage, mTitle);
//        handleToolbarTitleVisibility(percentage, mSubTitle);
//        if(percentage == 100){
//            toolbar.setBackgroundColor(getResources().getColor(R.color.nav_bar));
//        } else {
//            toolbar.setBackgroundColor(getResources().getColor(R.color.nav_bar_white));
//        }
    }

    private void handleToolbarTitleVisibility(float percentage, View v) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            startAlphaAnimation(v, ALPHA_ANIMATIONS_DURATION, v.getVisibility());
//            if (!mIsTheTitleVisible) {
//                mIsTheTitleVisible = true;
//
//            }
//        } else {
//
//            if (mIsTheTitleVisible) {
//                toolbar.setBackgroundColor(getResources().getColor(R.color.nav_bar_white));
//                startAlphaAnimation(v, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
//                mIsTheTitleVisible = false;
//            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = !(visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
