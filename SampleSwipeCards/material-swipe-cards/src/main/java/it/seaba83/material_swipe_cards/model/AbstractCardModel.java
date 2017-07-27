package it.seaba83.material_swipe_cards.model;


import it.seaba83.material_swipe_cards.compilers.AbstractCardCompiler;
import it.seaba83.material_swipe_cards.custom.StateCardView;

/*
 * material-swipe-cards library for Android
 * Copyright (c) 2017 Marco Caridi (https://github.com/saeba83).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public abstract class AbstractCardModel {

    private int type;
    private CardError error;
    private boolean progress;
    private AbstractCardCompiler mCompiler;
    private int mMainColor;

    public AbstractCardModel(){
        setType(CardTypes.CARD_TYPE_COMMON);
    }

    /**
     *
     * @return card type
     */
    public int getType() {
        return type;
    }

    /**
     * Set card type
     * @param type card type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     * @return true if card model is in error state
     */
    public boolean isError() {
        return (error != null);
    }

    /**
     *
     * @return error card model details
     */
    public CardError getError(){
        return this.error;
    }

    /**
     * Set card model in error state
     * @param error
     */
    public void setError(CardError error) {
        this.error = error;
    }

    /**
     *
     * @return true if card model is in progress state
     */
    public boolean isProgress() {
        return progress;
    }

    /**
     * Set card model in progress state
     * @param progress
     */
    public void setProgress(boolean progress) {
        this.progress = progress;
    }

    /**
     * Associates a compiler with the current model
     * @param compiler associated compiler
     */
    public void setCompiler(AbstractCardCompiler compiler){
        this.mCompiler = compiler;
    }

    /**
     * @return current compiler
     */
    public AbstractCardCompiler getCompiler(){
        return this.mCompiler;
    }

    /**
     * @return new CardView compiled with data inside current model from current compiler
     */
    public StateCardView compile(){
        return getCompiler().compile(this);
    }

    /**
     *
     * @return card main color
     */
    public int getMainColor(){
        return this.mMainColor;
    }

    /**
     * Set card model main color
     * @param color selected color
     */
    public void setMainColor(int color){
        this.mMainColor = color;
    }
}
