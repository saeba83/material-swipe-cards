package it.seaba83.sampleswipecards;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer;
import it.seaba83.sampleswipecards.compilers.ColoredCardCompiler;
import it.seaba83.sampleswipecards.compilers.StarWarsLogoCompiler;
import it.seaba83.sampleswipecards.compilers.VaderCardCompiler;
import it.seaba83.sampleswipecards.compilers.HelloWorldCardCompiler;
import it.seaba83.sampleswipecards.compilers.YodaCardCompiler;
import it.seaba83.sampleswipecards.model.ColoredCard;
import it.seaba83.sampleswipecards.model.StarWarsLogoCard;
import it.seaba83.sampleswipecards.model.CharacterCard;
import it.seaba83.sampleswipecards.model.HelloWorldCard;

public class MainActivity extends AppCompatActivity {

    SwipeCardViewContainer mExampleCardsContainer;
    SwipeCardViewContainer mStarWarsCardsContainer;
    SwipeCardViewContainer mColoredCardsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleCardsContainer();
        createStarWarsCardsContainer();
        createColoredCardsContainer();
    }

    private void createExampleCardsContainer(){
        mExampleCardsContainer = (SwipeCardViewContainer) findViewById(R.id.example_cards_container);
        final HelloWorldCardCompiler helloWorldCardCompiler = new HelloWorldCardCompiler(this);

        mExampleCardsContainer.putCardView(0, new HelloWorldCard(getString(R.string.sample_card_one_title), getString(R.string.sample_card_one_message), helloWorldCardCompiler));
        mExampleCardsContainer.putCardView(1, new HelloWorldCard(getString(R.string.sample_card_two_title), getString(R.string.sample_card_two_message), helloWorldCardCompiler));
        mExampleCardsContainer.putCardView(2, new HelloWorldCard(getString(R.string.sample_card_three_title), getString(R.string.sample_card_three_message), helloWorldCardCompiler));

        mExampleCardsContainer.setProgress(1, true);

        mExampleCardsContainer.setError(2, getString(R.string.error_test_message), getString(R.string.error_retry_label), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExampleCardsContainer.putCardView(2, new HelloWorldCard(getString(R.string.problem_solved_title), getString(R.string.problem_solved_message), helloWorldCardCompiler));
            }
        });
    }

    private void createStarWarsCardsContainer(){
        mStarWarsCardsContainer = (SwipeCardViewContainer) findViewById(R.id.starwars_cards_container);

        CharacterCard vaderCard = new CharacterCard();
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

        CharacterCard yodaCard = new CharacterCard();
        yodaCard.setCompiler(new YodaCardCompiler(this));
        yodaCard.setImage(ContextCompat.getDrawable(MainActivity.this, R.drawable.yoda));
        yodaCard.setMessage(getString(R.string.yoda_message));
        yodaCard.setButtonLabel(getString(R.string.yoda_label_button));
        yodaCard.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, getString(R.string.yoda_action_example), Toast.LENGTH_SHORT).show();
            }
        });

        StarWarsLogoCompiler starWarsLogoCompiler = new StarWarsLogoCompiler(MainActivity.this);
        mStarWarsCardsContainer.putCardView(0, new StarWarsLogoCard(ContextCompat.getDrawable(MainActivity.this, R.drawable.starwars_logo) ,starWarsLogoCompiler));
        mStarWarsCardsContainer.putCardView(1, yodaCard);
        mStarWarsCardsContainer.putCardView(2, vaderCard);
    }

    private void createColoredCardsContainer(){
        mColoredCardsContainer = (SwipeCardViewContainer) findViewById(R.id.colored_cards_container);

        ColoredCardCompiler coloredCardCompiler = new ColoredCardCompiler(MainActivity.this);

        mColoredCardsContainer.putCardView(0, new ColoredCard(ContextCompat.getColor(MainActivity.this, R.color.green_indicator), coloredCardCompiler));
        mColoredCardsContainer.putCardView(1, new ColoredCard(ContextCompat.getColor(MainActivity.this, R.color.blue_indicator), coloredCardCompiler));
        mColoredCardsContainer.putCardView(2, new ColoredCard(ContextCompat.getColor(MainActivity.this, R.color.red_indicator), coloredCardCompiler));
        mColoredCardsContainer.putCardView(3, new ColoredCard(ContextCompat.getColor(MainActivity.this, R.color.colorAccent), coloredCardCompiler));
    }
}
