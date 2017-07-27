package it.seaba83.material_swipe_cards.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import it.seaba83.material_swipe_cards.R;
import it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.material_swipe_cards.model.BaseCard;
import it.seaba83.material_swipe_cards.model.BaseCardError;

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

public class SwipeCardsAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<AbstractCardModel> mItems;
    private SwipeCardViewContainer mSwipeCardsContainer;


    public SwipeCardsAdapter(Context context, SwipeCardViewContainer pager) {
        this.mContext = context;
        this.mSwipeCardsContainer = pager;
        reset();
        mSwipeCardsContainer.setAdapter(this);
    }

    protected Context getContext(){
        return mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LinearLayout currentFrame = newFrame();
        currentFrame.addView(getView(position, getItems().get(position)), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        collection.addView(currentFrame);
        return currentFrame;
    }

    /**
     * Creates current cardView, it call cardModel compiler
     * @param position current item position
     * @param currentItem current cardModel
     * @return created view
     */
    public View getView(int position, AbstractCardModel currentItem){
        currentItem.setMainColor(mSwipeCardsContainer.getMainColor());
        return currentItem.compile();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    /**
     *
     * @return items list size
     */
    @Override
    public int getCount() {
        return this.mItems.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     *
     * @return items list
     */
    public ArrayList<AbstractCardModel> getItems(){
        return this.mItems;
    }

    /**
     * Set progress state to card in a defined position
     * @param position where the card to set in progress state is
     * @param value true if you want the card in progress state false else
     */
    public void setProgress(int position, boolean value){
        BaseCard card = new BaseCard(getContext());
        card.setProgress(value);
        put(position, card);
        notifyDataSetChanged();
    }


    /**
     * Set error state to card in a defined position
     * @param position where the card to set in error state is
     * @param message error message you want show in card
     * @param buttonLabel button label you want show on error card button
     * @param clickListener define the action you want execute on error button click
     */
    public void setError(int position, String message, String buttonLabel, View.OnClickListener clickListener){
        BaseCard card = new BaseCard(getContext());
        BaseCardError error = new BaseCardError();
        error.setMessage(message);
        error.setButtonLabel(buttonLabel);
        error.setButtonClickListener(clickListener);
        card.setError(error);
        put(position, card);
        notifyDataSetChanged();
    }

    /**
     * Add or replace new card inside the component
     * @param position where you want add the card
     * @param card contains data to create the card
     */
    public void put(int position, AbstractCardModel card){
        if (position < mItems.size()){
            mItems.set(position, card);
        }else{
            mItems.add(position, card);
        }
        notifyDataSetChanged();
        this.mSwipeCardsContainer.onCardAdded();
    }

    /**
     * Initialize items list with a new empty list and add a dafault base cardView into SwipeCardViewContainer
     */
    public void reset(){
        mItems = new ArrayList<AbstractCardModel>();
        addDefaultCard();
        notifyDataSetChanged();
    }

    /**
     * Add a dafault base cardView into SwipeCardViewContainer
     */
    private void addDefaultCard(){
        AbstractCardModel cardModel = new BaseCard(getContext());
        cardModel.setProgress(true);
        put(0, cardModel);
    }

    /**
     *
     * @return a commons LinearLayout with padding used to insert all items
     */
    private LinearLayout newFrame(){
        LinearLayout frame = new LinearLayout(getContext());
        frame.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        frame.setPadding((int)getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) getContext().getResources().getDimension(R.dimen.pager_internal_padding), (int) mContext.getResources().getDimension(R.dimen.pager_internal_padding));
        return frame;
    }

}