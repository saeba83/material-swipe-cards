package it.seaba83.material_swipe_cards.model;

import android.content.Context;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.StateCardView;

/**
 * Created by Marco on 25/07/2017.
 */

public class BaseCard extends AbstractCardModel {

    public BaseCard(Context context){
        setType(CardTypes.CARD_TYPE_BASE);
        if (!isError()) {
            setProgress(true);
        }
        setCompiler(new AbstractCardCompiler(context) {
            @Override
            public StateCardView compile(AbstractCardModel cardModel) {
                StateCardView cardView = new StateCardView(getContext());
                cardView.setProgressColor(getMainColor());
                cardView.setButtonTextColor(getMainColor());
                cardView.setProgress(BaseCard.this.isProgress());
                if (isError()) {
                    cardView.setError(getError().getMessage(), getError().getButtonLabel(), getError().getButtonClickListener());
                }
                return cardView;
            }
        });
    }
}
