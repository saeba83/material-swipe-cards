package it.seaba83.sampleswipecards.model;

import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.model.AbstractCardModel;

/**
 * Created by Marco on 25/07/2017.
 */

public class HelloWorldCard extends AbstractCardModel {

    private String title;
    private String message;

    public HelloWorldCard(String title, String message, AbstractCardCompiler compiler){
        setTitle(title);
        setMessage(message);
        setCompiler(compiler);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
