package it.seaba83.material_swipe_cards.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import it.seaba83.material_swipe_cards.R;
import it.seaba83.material_swipe_cards.general.Utils;


/**
 * Created by Marco on 25/07/2017.
 */

public class CustomCardViewContainer extends LinearLayout {

    private static final String TAG = "CustomIndicatorPager";

    private LinearLayout mIndicatorLayout;
    private ViewPager mPager;

    private int mItemsNumber;
    private float mPagerHeight;

    public CustomCardViewContainer(Context context) {
        super(context);
        init();
    }

    public CustomCardViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewContainer, 0, 0);
        try {
            mPagerHeight = ta.getDimension(R.styleable.CustomCardViewContainer_pagerHeight, 100.0f);
        } finally {
            ta.recycle();
        }

        init();
    }

    public CustomCardViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomCardViewContainer, 0, 0);
        try {
            mPagerHeight = ta.getDimension(R.styleable.CustomCardViewContainer_pagerHeight, 100.0f);
        } finally {
            ta.recycle();
        }

        init();
    }

    private void init(){
        inflate(getContext(), R.layout.custom_cardview_container_layout, this);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        mPager = (ViewPager) findViewById(R.id.pager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        //mPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) mPagerHeight));
    }

    public void setAdapter(PagerAdapter adapter){
        Utils.log(TAG, "setAdapter");
        mPager.setAdapter(adapter);
        onPageAdded();
    }

    public PagerAdapter getAdapter(){
        return mPager.getAdapter();
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
                }else{
                    selectedIndicator.setBackgroundResource(R.drawable.custom_pager_indicator_circle_unselected);
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

}
