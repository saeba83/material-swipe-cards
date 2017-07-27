package it.seaba83.material_swipe_cards.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import it.seaba83.material_swipe_cards.R;
import it.seaba83.material_swipe_cards.adapters.CardsPagerAdapter;
import it.seaba83.material_swipe_cards.general.Utils;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;

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

public class SwipeCardViewContainer extends LinearLayout {

    private static final String TAG = "CustomIndicatorPager";

    private LinearLayout mIndicatorLayout;
    private ViewPager mPager;

    private int mItemsNumber;
    private int mMainColor;

    public SwipeCardViewContainer(Context context) {
        super(context);
        init();
    }

    public SwipeCardViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwipeCardViewContainer, 0, 0);
        try {
            setMainColor(ta.getColor(R.styleable.SwipeCardViewContainer_mainColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground)));
        } finally {
            ta.recycle();
        }

        init();
    }

    public SwipeCardViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwipeCardViewContainer, 0, 0);
        try {
            setMainColor(ta.getColor(R.styleable.SwipeCardViewContainer_mainColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground)));
        } finally {
            ta.recycle();
        }

        init();
    }

    /**
     * Initialize inflated layout component and add new adapter instance
     */
    private void init(){
        inflate(getContext(), R.layout.custom_cardview_container_layout, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mPager = (ViewPager) findViewById(R.id.pager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        setAdapter(new CardsPagerAdapter(getContext(), this));
    }

    /**
     * Set an adapter to internal ViewPager and call onCardAdded callback
     * @param adapter PagerAdapter that you want set to embedded ViewPager
     */
    public void setAdapter(PagerAdapter adapter){
        Utils.log(TAG, "setAdapter");
        mPager.setAdapter(adapter);
        onCardAdded();
    }

    /**
     *
     * @return current embedded ViewPager adapter
     */
    public CardsPagerAdapter getAdapter(){
        return (CardsPagerAdapter)mPager.getAdapter();
    }

    /**
     *
     * @return current mainColor
     */

    public int getMainColor(){
        return this.mMainColor;
    }

    /**
     *
     * @param mainColor set current mainColor
     */
    public void setMainColor(int mainColor){
        this.mMainColor = mainColor;
    }

    /**
     *
     * @return the current number of items, equivalent of number of cards
     */

    public int getItemsNumber(){
        return this.mItemsNumber;
    }

    /**
     *
     * @param number set the number of items
     */
    public void setItemsNumber(int number){
        this.mItemsNumber = number;
    }

    /**
     * Add or replace new card inside the component
     * @param position where you want add the card
     * @param cardModel contains data to create the card
     */
    public void putCardView(int position, AbstractCardModel cardModel){
        if (getAdapter() != null){
            getAdapter().put(position, cardModel);
        }
    }

    /**
     * Set progress state to card in a defined position
     * @param position where the card to set in progress state is
     * @param value true if you want the card in progress state false else
     */
    public void setProgress(int position, boolean value){
        if (getAdapter() != null){
            getAdapter().setProgress(position, value);
        }
    }

    /**
     * Set error state to card in a defined position
     * @param position where the card to set in error state is
     * @param message error message you want show in card
     * @param buttonLabel button label you want show on error card button
     * @param clickListener define the action you want execute on error button click
     */
    public void setError(int position, String message, String buttonLabel, OnClickListener clickListener){
        if (getAdapter() != null){
            getAdapter().setError(position, message, buttonLabel, clickListener);
        }
    }


    /**
     * Crate cards number indicator end set current selected index
     * @param itemsNumber number of items
     */
    private void createCardIndicator(int itemsNumber){
        mIndicatorLayout.removeAllViews();
        mIndicatorLayout.invalidate();

        if (itemsNumber > 1){
            for (int i=0; i<itemsNumber; i++) {
                View indicator = new View(getContext());
                LayoutParams layoutParams = new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_radius_size));
                layoutParams.setMargins((int)getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_top), (int) getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_bottom));
                indicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
                indicator.setLayoutParams(layoutParams);
                mIndicatorLayout.addView(indicator, layoutParams);
                Utils.log(TAG, "Added view");
            }
            mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                private float mLastPositionOffset = 0f;

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if(positionOffset < mLastPositionOffset && positionOffset < 0.9) {
                        mPager.setCurrentItem(position);
                    } else if(positionOffset > mLastPositionOffset && positionOffset > 0.1) {
                        mPager.setCurrentItem(position+1);
                    }
                    mLastPositionOffset = positionOffset;
                }

                @Override
                public void onPageSelected(int position) {
                    selectCurrentIndex(position);
                    requestLayout();
                }
            });
            selectCurrentIndex(mPager.getCurrentItem());
            mIndicatorLayout.setVisibility(View.VISIBLE);
        }else{
            mIndicatorLayout.setVisibility(View.GONE);
        }
    }

    private void selectCurrentIndex(int position){
        Utils.log(TAG, "selectCurrentIndex " + position);
        for (int i=0; i<mIndicatorLayout.getChildCount(); i++){
            View selectedIndicator = mIndicatorLayout.getChildAt(i);
            if (selectedIndicator != null){
                if (i== position){
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_selected);
                    ((GradientDrawable) selectedIndicator.getBackground()).setColor(getMainColor());
                    resizeCurrentIndex(selectedIndicator, true);
                }else{
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
                    resizeCurrentIndex(selectedIndicator, false);
                }
            }
        }
    }

    /**
     * Callback called when a new card is added, update items number and indicator
     */
    public void onCardAdded(){
        if (getAdapter() != null){
            setItemsNumber(getAdapter().getCount());
            createCardIndicator(getItemsNumber());
        }
    }

    /**
     * Resize indicator items view, the selected item is larger then the others
     * @param currentItem item view to resize*
     * @param selected true if the selected item
     */
    private void resizeCurrentIndex(View currentItem, boolean selected){
        LayoutParams layoutParams = selected ? new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size))
                : new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_radius_size));
        layoutParams.setMargins((int)getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_top), (int) getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_bottom));
        currentItem.setLayoutParams(layoutParams);
    }

}
