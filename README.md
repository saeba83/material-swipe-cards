# material-swipe-cards

## What?
An Androd custom view component that allow to manage material-style cardViews on a swipe controller and everyone with a distinct structure and layout.

## How to ?

1.	Add it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer in your xml layout

```
 <it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer
        android:layout_marginBottom="3dp"
        app:mainColor="@color/green_indicator"
        android:id="@+id/example_cards_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

2.	Associate reference in your activity
```
        SwipeCardViewContainer mExampleCardsContainer = (SwipeCardViewContainer) findViewById(R.id.example_cards_container);
```

3. Define a card template extending the class AbstractCardModel
