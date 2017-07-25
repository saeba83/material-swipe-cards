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

    public SwipeCardsAdapter(Context context, CustomCardViewContainer pager) {
        super(context, pager);
        this.mInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position) {
        HelloWorldCard currentItem = (HelloWorldCard) this.getList().get(position);
        CustomCardView cardView = new CustomCardView(getContext());
        ViewGroup viewGroup = (ViewGroup) mInflater.inflate(R.layout.genric_card_view_layout, null);
        TextView titleTxt = (TextView) viewGroup.findViewById(R.id.title_txt);
        TextView messageTxt = (TextView) viewGroup.findViewById(R.id.message_txt);
        titleTxt.setText(currentItem.getTitle());
        messageTxt.setText(currentItem.getMessage());

        cardView.addView(viewGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return cardView;
    }
}
