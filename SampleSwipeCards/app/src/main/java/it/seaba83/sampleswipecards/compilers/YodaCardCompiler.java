package it.seaba83.sampleswipecards.compilers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.StateCardView;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.R;
import it.seaba83.sampleswipecards.model.CharacterCard;

/**
 * Created by Marco on 26/07/2017.
 */

public class YodaCardCompiler extends AbstractCardCompiler {


    public YodaCardCompiler(Context context) {
        super(context);
    }

    @Override
    public StateCardView compile(AbstractCardModel cardModel) {
        CharacterCard currentItem = (CharacterCard) cardModel;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        StateCardView cardView = new StateCardView(getContext());

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.yoda_card_layout, null);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.imageView);
        TextView messageTxt = (TextView) viewGroup.findViewById(R.id.message_txt);
        Button button = (Button) viewGroup.findViewById(R.id.button);

        imageView.setImageDrawable(currentItem.getImage());
        messageTxt.setText(currentItem.getMessage());
        button.setText(currentItem.getButtonLabel());
        button.setOnClickListener(currentItem.getClickListener());

        cardView.addView(viewGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return cardView;
    }
}
