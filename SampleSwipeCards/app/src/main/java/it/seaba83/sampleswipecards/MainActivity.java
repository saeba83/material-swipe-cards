package it.seaba83.sampleswipecards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import it.seaba83.material_swipe_cards.custom.CustomCardViewContainer;
import it.seaba83.sampleswipecards.compilers.HelloWorldCardCompiler;
import it.seaba83.sampleswipecards.model.HelloWorldCard;

public class MainActivity extends AppCompatActivity {

    CustomCardViewContainer mCardsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardsContainer = (CustomCardViewContainer) findViewById(R.id.cards_container);

        HelloWorldCardCompiler cardCompiler = new HelloWorldCardCompiler(this);
        mCardsContainer.put(0, new HelloWorldCard(getString(R.string.sample_card_one_title), getString(R.string.sample_card_one_message), cardCompiler));
        mCardsContainer.put(1, new HelloWorldCard(getString(R.string.sample_card_two_title), getString(R.string.sample_card_two_message), cardCompiler));
        mCardsContainer.put(2, new HelloWorldCard(getString(R.string.sample_card_three_title), getString(R.string.sample_card_three_message), cardCompiler));
    }
}
