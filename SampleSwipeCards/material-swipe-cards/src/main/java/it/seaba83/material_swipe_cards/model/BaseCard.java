package it.seaba83.material_swipe_cards.model;

/**
 * Created by Marco on 25/07/2017.
 */

public class BaseCard extends AbstractCardModel {

    public BaseCard(){
        setType(CardTypes.CARD_TYPE_BASE);
        setProgress(true);
    }
}
