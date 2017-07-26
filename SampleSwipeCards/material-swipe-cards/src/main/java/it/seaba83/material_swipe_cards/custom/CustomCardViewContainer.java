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


/**
 * Created by Marco on 25/07/2017.
 */

public class CustomCardViewContainer extends LinearLayout {

    private static final String TAG = "CustomIndicatorPager";

    private LinearLayout mIndicatorLayout;
    private ViewPager mPager;

    private int mItemsNumber;
    private int mMainColor;

    public CustomCardViewContainer(Context context) {
        super(context);
        init();
    }

    public CustomCardViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewContainer, 0, 0);
        try {
            mMainColor = ta.getColor(R.styleable.CustomCardViewContainer_mainColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        } finally {
            ta.recycle();
        }

        init();
    }

    public CustomCardViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewContainer, 0, 0);
        try {
            mMainColor = ta.getColor(R.styleable.CustomCardViewContainer_mainColor, ContextCompat.getColor(getContext(), R.color.color_default_foreground));
        } finally {
            ta.recycle();
        }

        init();
    }

    private void init(){
        inflate(getContext(), R.layout.custom_cardview_container_layout, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        mPager = (ViewPager) findViewById(R.id.pager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        setAdapter(new CardsPagerAdapter(getContext(), this));
    }

    public void setAdapter(PagerAdapter adapter){
        Utils.log(TAG, "setAdapter");
        mPager.setAdapter(adapter);
        onPageAdded();
    }

    public CardsPagerAdapter getAdapter(){
        return (CardsPagerAdapter)mPager.getAdapter();
    }

    public void put(int position, AbstractCardModel cardModel){
        if (getAdapter() != null){
            getAdapter().put(position, cardModel);
        }
    }


    private void createPageIndicator(int itemsNumber){
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
                    selectCurrentIndicator(position);
                    requestLayout();
                }
            });
            selectCurrentIndicator(mPager.getCurrentItem());
            mIndicatorLayout.setVisibility(View.VISIBLE);
        }else{
            mIndicatorLayout.setVisibility(View.GONE);
        }
    }

    private void selectCurrentIndicator(int position){
        Utils.log(TAG, "selectCurrentIndicator " + position);
        for (int i=0; i<mIndicatorLayout.getChildCount(); i++){
            View selectedIndicator = mIndicatorLayout.getChildAt(i);
            if (selectedIndicator != null){
                if (i== position){
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_selected);
                    ((GradientDrawable) selectedIndicator.getBackground()).setColor(mMainColor);
                    resizeIndicator(selectedIndicator, true);
                }else{
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
                    resizeIndicator(selectedIndicator, false);
                }
            }
        }
    }

    public void onPageAdded(){
        if (getAdapter() != null){
            mItemsNumber = getAdapter().getCount();
            createPageIndicator(mItemsNumber);
        }
    }

    private void resizeIndicator(View currentItem, boolean selected){
        LayoutParams layoutParams = selected ? new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_selected_radius_size))
                : new LayoutParams((int) getContext().getResources().getDimension(R.dimen.indicator_radius_size),(int) getContext().getResources().getDimension(R.dimen.indicator_radius_size));
        layoutParams.setMargins((int)getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_top), (int) getContext().getResources().getDimension(R.dimen.indicator_margins_horizontal), (int)getContext().getResources().getDimension(R.dimen.indicator_margins_bottom));
        currentItem.setLayoutParams(layoutParams);
    }

}
