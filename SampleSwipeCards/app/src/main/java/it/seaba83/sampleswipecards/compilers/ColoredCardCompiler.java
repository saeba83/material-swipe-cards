package it.seaba83.sampleswipecards.compilers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.CustomCardView;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.model.ColoredCard;

/**
 * Created by Marco on 26/07/2017.
 */

public class ColoredCardCompiler extends AbstractCardCompiler {

    public ColoredCardCompiler(Context context) {
        super(context);
    }

    @Override
    public CustomCardView compile(AbstractCardModel cardModel) {
        ColoredCard currentItem = (ColoredCard) cardModel;
        CustomCardView cardView = new CustomCardView(getContext());
        cardView.setBackgroundColor(currentItem.getColor());
        return cardView;
    }
}
