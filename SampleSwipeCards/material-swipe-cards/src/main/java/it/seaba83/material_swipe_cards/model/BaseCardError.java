package it.seaba83.material_swipe_cards.model;

import android.view.View;


/**
 * Created by Marco on 25/07/2017.
 */

public class BaseCardError {

    private String message;
    private String buttonLabel;

    private View.OnClickListener buttonClickListener;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public View.OnClickListener getButtonClickListener() {
        return buttonClickListener;
    }

    public void setButtonClickListener(View.OnClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }
}
