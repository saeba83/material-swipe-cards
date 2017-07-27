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


/*
 * material-swipe-cards library for Android
 * Copyright (c) 2017 Marco Caridi (https://github.com/saeba83).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class StateCardView extends CardView {

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

    public StateCardView(Context context) {
        super(context, null, R.style.CustomCardStyle);
        init();
    }

    public StateCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithAttrs(attrs);
    }

    public StateCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, null, R.style.CustomCardStyle);
        initWithAttrs(attrs);
    }

    /**
     * Initialize inflated layout component and add new adapter instance with stylable attributes
     * @param attrs
     */

    private void initWithAttrs(AttributeSet attrs){
        init();
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.StateCardView, 0, 0);
        isProgress = attributes.getBoolean(R.styleable.StateCardView_progress, false);
        textColor = attributes.getColor(R.styleable.StateCardView_textColor, ContextCompat.getColor(getContext(),android.R.color.darker_gray));
        buttonTextColor = attributes.getColor(R.styleable.StateCardView_buttonTextColor,ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        progressColor = attributes.getColor(R.styleable.StateCardView_progressColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        attributes.recycle();
    }

    /**
     * Initialize inflated layout component and add new adapter instance
     */

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


    /**
     * Set card in progress state
     * @param value if true set progress state
     */
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

    /**
     * Set card in error state
     * @param message error message you want show in card
     * @param buttonLabel button label you want show in card
     * @param clickListener defines the action you want perform on button click
     */
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

    /**
     * Override android view addView method to add views only in a defined viewGroup container
     * @param child view you want add
     */
    @Override
    public void addView(View child) {
        if (mDataContainerLayout != null){
            mDataContainerLayout.addView(child);
        }else {
            super.addView(child);
        }

    }

    /**
     * Override android view addView method to add views only in a defined viewGroup container
     * @param child view you want add
     * @param params layoutParams to add child view
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (mDataContainerLayout != null){
            mDataContainerLayout.addView(child, params);
        }else{
            super.addView(child, params);
        }
    }

    /**
     *
     * @return the current card state
     */
    public int getState(){
        return state;
    }

    /**
     *
     * @return the current progressBar color
     */
    public int getProgressColor(){
        return this.progressColor;
    }

    /**
     * set progressBar color
      * @param color selected color
     */
    public void setProgressColor(int color){
        this.progressColor = color;
    }

    /**
     * Set text color for default text messages
     * @param color selected color
     */
    public void setTextColor(int color){
        this.textColor = color;
    }

    /**
     * @return text color for default text messages
     */

    public int getTextColor(){
        return this.textColor;
    }

    /**
     * set default buttons text color
     * @param color selected color
     */
    public void setButtonTextColor(int color){
        this.buttonTextColor = color;
    }

    /**
     *
     * @return dafault buttons text color
     */
    public int getButtonTextColor(){
        return this.buttonTextColor;
    }
}
