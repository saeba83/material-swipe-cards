package it.seaba83.sampleswipecards;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import it.seaba83.material_swipe_cards.custom.CustomCardViewContainer;
import it.seaba83.material_swipe_cards.model.BaseCard;
import it.seaba83.sampleswipecards.compilers.VaderCardCompiler;
import it.seaba83.sampleswipecards.compilers.HelloWorldCardCompiler;
import it.seaba83.sampleswipecards.model.VaderCard;
import it.seaba83.sampleswipecards.model.HelloWorldCard;

public class MainActivity extends AppCompatActivity {

    CustomCardViewContainer mCardsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCardsContainer = (CustomCardViewContainer) findViewById(R.id.cards_container);

        HelloWorldCardCompiler helloWorldCardCompiler = new HelloWorldCardCompiler(this);

        mCardsContainer.put(0, new HelloWorldCard(getString(R.string.sample_card_one_title), getString(R.string.sample_card_one_message), helloWorldCardCompiler));
        mCardsContainer.put(1, new HelloWorldCard(getString(R.string.sample_card_two_title), getString(R.string.sample_card_two_message), helloWorldCardCompiler));
        mCardsContainer.put(2, new HelloWorldCard(getString(R.string.sample_card_three_title), getString(R.string.sample_card_three_message), helloWorldCardCompiler));

        VaderCard vaderCard = new VaderCard();
        vaderCard.setCompiler(new VaderCardCompiler(this));
        vaderCard.setImage(ContextCompat.getDrawable(MainActivity.this, R.drawable.vader));
        vaderCard.setMessage(getString(R.string.vader_message));
        vaderCard.setButtonLabel(getString(R.string.vader_label_button));
        vaderCard.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, getString(R.string.vader_action_example), Toast.LENGTH_SHORT).show();
            }
        });
        mCardsContainer.put(3, vaderCard);
    }
}
