package it.seaba83.sampleswipecards.model;

import android.graphics.drawable.Drawable;

import it.seaba83.material_swipe_cards.model.AbstractCardModel;
import it.seaba83.sampleswipecards.compilers.StarWarsLogoCompiler;

/**
 * Created by Marco on 26/07/2017.
 */

public class StarWarsLogoCard extends AbstractCardModel {

    private Drawable image;

    public StarWarsLogoCard(Drawable img, StarWarsLogoCompiler compiler){
        setImage(img);
        setCompiler(compiler);
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
