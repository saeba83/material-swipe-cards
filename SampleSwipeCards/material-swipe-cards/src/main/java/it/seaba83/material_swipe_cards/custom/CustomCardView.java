package it.seaba83.material_swipe_cards.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import it.seaba83.material_swipe_cards.R;


/**
 * Created by Marco on 25/07/2017.
 */

public class CustomCardView extends CardView {

    public final static int CARD_STATE_DATA = 0;
    public final static int CARD_STATE_PROGRESS = 1;
    public final static int CARD_STATE_ERROR = 2;

    private boolean isProgress = false;
    private int textColor;
    private int buttonTextColor;
    private int progressColor;

    private int state = 0;

    private ProgressBar mProgressView;
    private LinearLayout mDataContainerLayout;
    private LinearLayout mErrorContainerLayout;
    private TextView mErrorTxt;
    private Button mErrorRetryButton;

    public CustomCardView(Context context) {
        super(context, null, R.style.CustomCardStyle);
        init();
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithAttrs(attrs);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, null, R.style.CustomCardStyle);
        initWithAttrs(attrs);
    }

    private void initWithAttrs(AttributeSet attrs){
        init();
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CustomCardView, 0, 0);
        isProgress = attributes.getBoolean(R.styleable.CustomCardView_progress, false);
        textColor = attributes.getColor(R.styleable.CustomCardView_textColor, ContextCompat.getColor(getContext(),android.R.color.darker_gray));
        buttonTextColor = attributes.getColor(R.styleable.CustomCardView_buttonTextColor,ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        progressColor = attributes.getColor(R.styleable.CustomCardView_progressColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        attributes.recycle();
    }

    private void init(){

        setTextColor(ContextCompat.getColor(getContext(),android.R.color.darker_gray));
        setButtonTextColor(ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        setProgressColor(ContextCompat.getColor(getContext(), R.color.color_default_foreground));

        setUseCompatPadding(true);
        setCardElevation(getContext().getResources().getDimension(R.dimen.card_elevation_size));

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.custom_cardview_layout, this, true);
        mProgressView = (ProgressBar) rootView.findViewById(R.id.progressBar);
        mDataContainerLayout = (LinearLayout) rootView.findViewById(R.id.container);

        mErrorContainerLayout = (LinearLayout) rootView.findViewById(R.id.errorContainer);
        mErrorTxt = (TextView) rootView.findViewById(R.id.errorMessageTxt);
        mErrorRetryButton = (Button) rootView.findViewById(R.id.errorButton);
        setProgress(isProgress);
    }

    public void setProgress(boolean value){

        if (value){
            setVisibility(VISIBLE);
        }

        if (mProgressView != null) {
            state = CARD_STATE_PROGRESS;
            mProgressView.getIndeterminateDrawable().setColorFilter(getProgressColor(), PorterDuff.Mode.SRC_IN);
            mProgressView.setVisibility(value ? VISIBLE : GONE);
        }
        if (mDataContainerLayout != null){
            state = CARD_STATE_DATA;
            mDataContainerLayout.setVisibility(!value ? VISIBLE : GONE);
        }

        if (mErrorContainerLayout != null){
            mErrorContainerLayout.setVisibility(GONE);
        }
    }

    public void setError(String message, String buttonLabel, OnClickListener clickListener){
        setVisibility(VISIBLE);
        if (mProgressView != null) {
            mProgressView.setVisibility(GONE);
        }
        if (mDataContainerLayout != null){
            mDataContainerLayout.setVisibility(GONE);
        }

        if (mErrorContainerLayout != null){
            state = CARD_STATE_ERROR;
            mErrorContainerLayout.setVisibility(VISIBLE);
            mErrorTxt.setText(message);
            mErrorTxt.setTextColor(getTextColor());
            if (clickListener != null){
                mErrorRetryButton.setText(buttonLabel);
                mErrorRetryButton.setTextColor(getButtonTextColor());
                mErrorRetryButton.setVisibility(View.VISIBLE);
                mErrorRetryButton.setOnClickListener(clickListener);
            }else{
                mErrorRetryButton.setVisibility(GONE);
            }
        }
    }

    @Override
    public void addView(View child) {
        if (mDataContainerLayout != null){
            mDataContainerLayout.addView(child);
        }else {
            super.addView(child);
        }

    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (mDataContainerLayout != null){
            mDataContainerLayout.addView(child, params);
        }else{
            super.addView(child, params);
        }
    }

    public int getState(){
        return state;
    }

    public int getProgressColor(){
        return this.progressColor;
    }

    public void setProgressColor(int color){
        this.progressColor = color;
    }

    public void setTextColor(int color){
        this.textColor = color;
    }

    public int getTextColor(){
        return this.textColor;
    }

    public void setButtonTextColor(int color){
        this.buttonTextColor = color;
    }

    public int getButtonTextColor(){
        return this.buttonTextColor;
    }
}
