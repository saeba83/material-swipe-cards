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
import it.seaba83.material_swipe_cards.adapters.SwipeCardsAdapter;
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

    private static final int INDICATOR_ITEM_MAX_SIZE = 5;
    private static final int ACTION_GO_TO_RIGHT = 0;
    private static final int ACTION_GO_TO_LEFT = 1;

    private LinearLayout mIndicatorLayout;
    private ViewPager mPager;

    private int mItemsNumber;
    private int mMainColor;
    boolean haveExtraItems = false;

    int mLeftLimitPosition;
    int mRightLimitPosition;
    int mCurrentAction;
    int mCurrentIndicatorPosition;

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
        setAdapter(new SwipeCardsAdapter(getContext(), this));
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
    public SwipeCardsAdapter getAdapter(){
        return (SwipeCardsAdapter)mPager.getAdapter();
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
     * @param cardsNumber number of cards into container
     */
    private void createCardsIndicator(int cardsNumber){
        mIndicatorLayout.removeAllViews();
        mPager.clearOnPageChangeListeners();
        mIndicatorLayout.invalidate();

        int itemsNumber = cardsNumber;
        setCurrentIndicatorPosition(-1);

        if (itemsNumber > INDICATOR_ITEM_MAX_SIZE){
            itemsNumber = INDICATOR_ITEM_MAX_SIZE-1;
            haveExtraItems = true;
        }

        if (itemsNumber > 1){
            for (int i=0; i<itemsNumber; i++) {
               createIndicatorItem((int)getContext().getResources().getDimension(R.dimen.indicator_radius_size));
            }
            if (haveExtraItems) {
                createIndicatorItem((int) getContext().getResources().getDimension(R.dimen.indicator_extra_radius_size));
                mLeftLimitPosition = 1;
                mRightLimitPosition = INDICATOR_ITEM_MAX_SIZE -2;
            }else{
                mLeftLimitPosition = 0;
                mRightLimitPosition = 0;
            }

            mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                int currentPosition = 0;

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    Utils.log(TAG, "onPageSelected");

                    if (position > currentPosition){
                        setCurrentAction(ACTION_GO_TO_RIGHT);
                    }else{
                        setCurrentAction(ACTION_GO_TO_LEFT);
                    }
                    currentPosition = position;
                    selectCurrentIndex(position);
                }
            });
            selectCurrentIndex(mPager.getCurrentItem());
            mIndicatorLayout.setVisibility(View.VISIBLE);
        }else{
            mIndicatorLayout.setVisibility(View.GONE);
        }
    }

    /**
     * Create single indicator item, and add this to indicator layout
     * @param size of indicator item
     */
    private void createIndicatorItem(int size){
        createIndicatorItem(size, mIndicatorLayout.getChildCount());
    }

    /**
     * Create single indicator item, and add this to indicator layout
     * @param size of indicator item
     * @param position position where you wante insert indicator item
     */
    private void createIndicatorItem(int size, int position){
        View indicator = new View(getContext());
        LayoutParams layoutParams = new LayoutParams(size, size);
        layoutParams.setMargins((int)getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_top), (int) getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_bottom));
        indicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
        indicator.setLayoutParams(layoutParams);
        mIndicatorLayout.addView(indicator, position, layoutParams);
        Utils.log(TAG, "Added view");
    }

    /**
     * Select current position on cards indicator
     * @param position current item position
     */
    private void selectCurrentIndex(int position){
        Utils.log(TAG, "selectCurrentIndex " + position);

        setCurrentIndicatorPosition(getFixedIndicatorPosition(position, getCurrentIndicatorPosition()));
        animateExtraItems();

        for (int i = 0; i < mIndicatorLayout.getChildCount(); i++) {
            View selectedIndicator = mIndicatorLayout.getChildAt(i);
            if (selectedIndicator != null) {
                if (i == getCurrentIndicatorPosition()) {
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_selected);
                    ((GradientDrawable) selectedIndicator.getBackground()).setColor(getMainColor());
                    resizeCurrentIndex(true, i, getCurrentIndicatorPosition());
                } else {
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
                    resizeCurrentIndex(false, i, getCurrentIndicatorPosition());
                }
            }
        }
    }

    /**
     *
     * @param position cards perger position
     * @param oldIndicatorPosition indicator current position
     * @return index for control swipe indicator
     */
    private int getFixedIndicatorPosition(int position, int oldIndicatorPosition){
        int result = position;
        if (haveExtraItems){
            result = oldIndicatorPosition;
            int endIndicatorPosition = INDICATOR_ITEM_MAX_SIZE -1;
            int endCardsPosition = mItemsNumber -1;

            if (getCurrentAction() == ACTION_GO_TO_RIGHT){
                result = oldIndicatorPosition +1;
                if (result > mRightLimitPosition && position < endCardsPosition) {
                    result = mRightLimitPosition;
                } else if (position == endCardsPosition) {
                    result = endIndicatorPosition;
                }
            } else if (getCurrentAction() == ACTION_GO_TO_LEFT){
                result = oldIndicatorPosition - 1;

                if (result <= 0 && position > 0){
                    result = mLeftLimitPosition;
                }

                if (position == 0){
                    result = 0;
                }
            }
        }
        Utils.log(TAG, "Position: " + position + " Current action: " + getCurrentAction() + " Indicator:" + result);
        return result;
    }


    /**
     * Animate indicator with extra items
     */
    private void animateExtraItems(){
        if (haveExtraItems){
            if (mIndicatorLayout.getChildCount() > 0) {
                if (getCurrentAction() == ACTION_GO_TO_RIGHT) {
                    mIndicatorLayout.removeViewAt(0);
                    createIndicatorItem((int) getResources().getDimension(R.dimen.indicator_radius_size), mIndicatorLayout.getChildCount());
                } else {
                    mIndicatorLayout.removeViewAt(mIndicatorLayout.getChildCount() - 1);
                    createIndicatorItem((int) getResources().getDimension(R.dimen.indicator_radius_size), 0);
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
            createCardsIndicator(getItemsNumber());
        }
    }

    /**
     * Resize indicator items view, the selected item is larger then the others
     * @param selected true if the selected item
     */
    private void resizeCurrentIndex(boolean selected, int currentIndex, int selectedIndex){
        LayoutParams layoutParams = selected ? new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size)) : new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_radius_size));
        if (haveExtraItems) {
            if (((currentIndex == 0 && mPager.getCurrentItem() >= mRightLimitPosition) ||
                    (currentIndex == mIndicatorLayout.getChildCount() - 1) && (mPager.getCurrentItem() < mItemsNumber-1 && getCurrentAction() == ACTION_GO_TO_RIGHT || (selectedIndex <= mLeftLimitPosition && getCurrentAction() == ACTION_GO_TO_LEFT)))
                    && !selected) {
                layoutParams = new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_extra_radius_size), (int) getContext().getResources().getDimension(R.dimen.indicator_extra_radius_size));
            }
        }
        layoutParams.setMargins((int)getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_top), (int) getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_bottom));
        mIndicatorLayout.getChildAt(currentIndex).setLayoutParams(layoutParams);
    }

    private void setCurrentAction(int action){
        this.mCurrentAction = action;
    }

    private int getCurrentAction(){
        return this.mCurrentAction;
    }

    private void setCurrentIndicatorPosition(int position){
        this.mCurrentIndicatorPosition = position;
    }

    private int getCurrentIndicatorPosition(){
        return this.mCurrentIndicatorPosition;
    }


}
