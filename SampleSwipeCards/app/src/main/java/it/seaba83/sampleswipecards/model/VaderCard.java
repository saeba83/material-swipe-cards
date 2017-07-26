package it.seaba83.sampleswipecards.model;

import android.graphics.drawable.Drawable;
import android.view.View;

import it.seaba83.material_swipe_cards.model.AbstractCardModel;

/**
 * Created by Marco on 26/07/2017.
 */

public class VaderCard extends AbstractCardModel {

    private Drawable image;
    private String message;
    private String buttonLabel;

    private View.OnClickListener clickListener;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }
}
