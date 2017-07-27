package it.seaba83.sampleswipecards.compilers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.StateCardView;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.R;
import it.seaba83.sampleswipecards.model.StarWarsLogoCard;

/**
 * Created by Marco on 26/07/2017.
 */

public class StarWarsLogoCompiler extends AbstractCardCompiler {

    public StarWarsLogoCompiler(Context context) {
        super(context);
    }

    @Override
    public StateCardView compile(AbstractCardModel cardModel) {
        StarWarsLogoCard currentItem = (StarWarsLogoCard) cardModel;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        StateCardView cardView = new StateCardView(getContext());
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.starwars_logo_card_layout, null);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.imageView);
        imageView.setImageDrawable(currentItem.getImage());
        cardView.addView(viewGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return cardView;
    }
}
