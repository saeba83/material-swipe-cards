package it.seaba83.sampleswipecards.model;

import android.graphics.Color;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;

/**
 * Created by Marco on 26/07/2017.
 */

public class ColoredCard extends AbstractCardModel{

    private int color;

    public ColoredCard(int color, AbstractCardCompiler compiler){
        setColor(color);
        setCompiler(compiler);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
