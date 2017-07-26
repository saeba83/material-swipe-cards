package it.seaba83.sampleswipecards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.seaba83.material_swipe_cards.adapters.CardsPagerAdapter;
import it.seaba83.material_swipe_cards.custom.CustomCardView;
import it.seaba83.material_swipe_cards.custom.CustomCardViewContainer;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.model.HelloWorldCard;

/**
 * Created by Marco on 25/07/2017.
 */

public class SwipeCardsAdapter extends CardsPagerAdapter {

    private LayoutInflater mInflater;

    public SwipeCardsAdapter(Context context, CustomCardViewContainer cardsContainer) {
        super(context, cardsContainer);
    }

    @Override
    public View getView(int position) {
        HelloWorldCard currentItem = (HelloWorldCard) this.getList().get(position);
        return currentItem.compile();
    }
}
