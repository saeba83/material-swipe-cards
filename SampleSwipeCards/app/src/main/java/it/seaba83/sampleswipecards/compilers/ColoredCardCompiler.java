package it.seaba83.sampleswipecards.compilers;

import android.content.Context;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.StateCardView;
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
    public StateCardView compile(AbstractCardModel cardModel) {
        ColoredCard currentItem = (ColoredCard) cardModel;
        StateCardView cardView = new StateCardView(getContext());
        cardView.setBackgroundColor(currentItem.getColor());
        return cardView;
    }
}
