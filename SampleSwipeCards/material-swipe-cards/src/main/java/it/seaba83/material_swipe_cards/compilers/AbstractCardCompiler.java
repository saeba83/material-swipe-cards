package it.seaba83.material_swipe_cards.compilers;

import android.content.Context;

import it.seaba83.material_swipe_cards.custom.CustomCardView;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;

/**
 * Created by Marco on 26/07/2017.
 */

public abstract class AbstractCardCompiler {

    private Context mContext;

    public AbstractCardCompiler(Context context){
        setContext(context);
    }

    protected Context getContext(){
        return mContext;
    }

    protected void setContext(Context context){
        this.mContext = context;
    }

    public abstract CustomCardView compile(AbstractCardModel cardModel);
}
