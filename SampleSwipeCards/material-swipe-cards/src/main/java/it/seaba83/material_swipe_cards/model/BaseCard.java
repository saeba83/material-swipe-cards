package it.seaba83.material_swipe_cards.model;

import android.content.Context;

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

public class BaseCard extends AbstractCardModel {

    /**
     * Creates AbstractCardModel instance in progress or error state and always without data
     * @param context
     */
    public BaseCard(Context context){
        setType(CardTypes.CARD_TYPE_BASE);
        if (!isError()) {
            setProgress(true);
        }
        setCompiler(new AbstractCardCompiler(context) {
            @Override
            public StateCardView compile(AbstractCardModel cardModel) {
                StateCardView cardView = new StateCardView(getContext());
                cardView.setProgressColor(getMainColor());
                cardView.setButtonTextColor(getMainColor());
                cardView.setProgress(BaseCard.this.isProgress());
                if (isError()) {
                    cardView.setError(getError().getMessage(), getError().getButtonLabel(), getError().getButtonClickListener());
                }
                return cardView;
            }
        });
    }
}
