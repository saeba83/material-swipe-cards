package it.seaba83.material_swipe_cards.model;

import android.view.View;

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

public class CardError {

    private String message;
    private String buttonLabel;
    private View.OnClickListener buttonClickListener;

    /**
     *
     * @return error card message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set error card message
     * @param message error card message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return error card button click listener
     */
    public View.OnClickListener getButtonClickListener() {
        return buttonClickListener;
    }

    /**
     * Set error card model button click listener
     * @param buttonClickListener
     */
    public void setButtonClickListener(View.OnClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    /**
     * @return error card model button label
     */
    public String getButtonLabel() {
        return buttonLabel;
    }

    /**
     * Set error card model button label
     * @param buttonLabel error card model button label
     */
    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }
}
