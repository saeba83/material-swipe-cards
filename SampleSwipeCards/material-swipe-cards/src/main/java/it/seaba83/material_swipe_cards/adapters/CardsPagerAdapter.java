package it.seaba83.material_swipe_cards.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import it.seaba83.material_swipe_cards.R;
import it.seaba83.material_swipe_cards.custom.CustomCardViewContainer;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.material_swipe_cards.model.BaseCard;
import it.seaba83.material_swipe_cards.model.BaseCardError;

/**
 * Created by Marco on 25/07/2017.
 */

public abstract class CardsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<AbstractCardModel> mCards;
    private CustomCardViewContainer mCardsContainer;


    public CardsPagerAdapter(Context context, CustomCardViewContainer pager) {
        this.mContext = context;
        this.mCardsContainer = pager;
        reset();
        mCardsContainer.setAdapter(this);
    }

    protected Context getContext(){
        return mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LinearLayout currentFrame = newFrame();
        currentFrame.addView(getView(position), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        collection.addView(currentFrame);
        return currentFrame;
    }

    public abstract View getView(int position);

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return this.mCards.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public ArrayList<AbstractCardModel> getList(){
        return this.mCards;
    }

    public void setProgress(int position, boolean value){
        BaseCard card = new BaseCard();
        card.setProgress(value);
        put(position, card);
        notifyDataSetChanged();
    }

    public void setError(int position, String message, View.OnClickListener clickListener){
        BaseCard card = new BaseCard();
        BaseCardError error = new BaseCardError();
        error.setMessage(message);
        error.setButtonClickListener(clickListener);
        card.setError(error);
        put(position, card);
        notifyDataSetChanged();
    }

    public void put(int position, AbstractCardModel card){
        if (position < mCards.size()){
            mCards.set(position, card);
        }else{
            mCards.add(position, card);
        }
        notifyDataSetChanged();
        this.mCardsContainer.onPageAdded();
    }

    public void reset(){
        mCards = new ArrayList<AbstractCardModel>();
        addDefaultCard();
        notifyDataSetChanged();
    }

    private void addDefaultCard(){
        AbstractCardModel cardModel = new BaseCard();
        cardModel.setProgress(true);
        put(0, cardModel);
    }

    private LinearLayout newFrame(){
        LinearLayout frame = new LinearLayout(getContext());
        frame.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        frame.setPadding((int)getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) mContext.getResources().getDimension(R.dimen.pager_internal_padding));
        return frame;
    }

}