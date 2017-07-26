package it.seaba83.material_swipe_cards.model;


import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.CustomCardView;

/**
 * Created by Marco on 25/07/2017.
 */

public abstract class AbstractCardModel {

    private int type;
    private BaseCardError error;
    private boolean progress;
    private AbstractCardCompiler mCompiler;
    private int mMainColor;

    public AbstractCardModel(){
        setType(CardTypes.CARD_TYPE_COMMON);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isError() {
        return (error != null);
    }

    public BaseCardError getError(){
        return this.error;
    }

    public void setError(BaseCardError error) {
        this.error = error;
    }

    public boolean isProgress() {
        return progress;
    }

    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    public void setCompiler(AbstractCardCompiler compiler){
        this.mCompiler = compiler;
    }

    public AbstractCardCompiler getCompiler(){
        return this.mCompiler;
    }

    public CustomCardView compile(){
        return getCompiler().compile(this);
    }

    public int getMainColor(){
        return this.mMainColor;
    }

    public void setMainColor(int color){
        this.mMainColor = color;
    }
}
