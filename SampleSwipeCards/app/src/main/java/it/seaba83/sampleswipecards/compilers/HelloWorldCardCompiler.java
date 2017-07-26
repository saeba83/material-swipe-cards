package it.seaba83.sampleswipecards.compilers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.CustomCardView;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.R;
import it.seaba83.sampleswipecards.model.HelloWorldCard;

/**
 * Created by Marco on 26/07/2017.
 */

public class HelloWorldCardCompiler extends AbstractCardCompiler {

    public HelloWorldCardCompiler(Context context) {
        super(context);
    }

    @Override
    public CustomCardView compile(AbstractCardModel cardModel) {
        HelloWorldCard currentItem = (HelloWorldCard) cardModel;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        CustomCardView cardView = new CustomCardView(getContext());
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.genric_card_view_layout, null);
        TextView titleTxt = (TextView) viewGroup.findViewById(R.id.title_txt);
        TextView messageTxt = (TextView) viewGroup.findViewById(R.id.message_txt);
        titleTxt.setText(currentItem.getTitle());
        messageTxt.setText(currentItem.getMessage());
        cardView.addView(viewGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return cardView;
    }
}
