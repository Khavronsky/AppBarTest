package com.khavronsky.appbartest;


import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCollapsedLayout extends FrameLayout {

    private TextView mTitle;

    private TextView mSubTitle;

    private TextView mValue;

    private TextView mUnit;

    private TextView mExtraDescription;

    private ImageView mImageView;

    private View mLayout;

    private View mTitleLayout;

    public CustomCollapsedLayout(@NonNull final Context context) {
        super(context);
        init();
    }

    public CustomCollapsedLayout(@NonNull final Context context,
            @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCollapsedLayout(@NonNull final Context context, @Nullable final AttributeSet attrs,
            @AttrRes final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_collapsed_layout, this);
        mTitle = (TextView) findViewById(R.id.title);
        mSubTitle = (TextView) findViewById(R.id.sub_title);
        mValue = (TextView) findViewById(R.id.value);
        mUnit = (TextView) findViewById(R.id.unit);
        mExtraDescription = (TextView) findViewById(R.id.extra_description);
        mImageView = (ImageView) findViewById(R.id.image);
        mLayout = findViewById(R.id.lin_layout);
        mTitleLayout = findViewById(R.id.title_layout);
    }

    public View getTitleLayout() {
        return mTitleLayout;
    }

    public View getLayout() {
        return mLayout;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public CustomCollapsedLayout setTitle(final TextView title) {
        mTitle = title;
        return this;
    }

    public TextView getSubTitle() {
        return mSubTitle;
    }

    public CustomCollapsedLayout setSubTitle(final TextView subTitle) {
        mSubTitle = subTitle;
        return this;
    }

    public TextView getValue() {
        return mValue;
    }

    public CustomCollapsedLayout setValue(final TextView value) {
        mValue = value;
        return this;
    }

    public TextView getUnit() {
        return mUnit;
    }

    public CustomCollapsedLayout setUnit(final TextView unit) {
        mUnit = unit;
        return this;
    }

    public TextView getExtraDescription() {
        return mExtraDescription;
    }

    public CustomCollapsedLayout setExtraDescription(final TextView extraDescription) {
        mExtraDescription = extraDescription;
        return this;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public CustomCollapsedLayout setImageView(final ImageView imageView) {
        mImageView = imageView;
        return this;
    }
}
