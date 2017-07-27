# material-swipe-cards

<img src="https://github.com/saeba83/material-swipe-cards/blob/master/graphics/20170727152705-726defe922.%5Bgif-2-mp4.com%5D.gif" width="30%" />

## What?
An Androd custom view component that allow to manage material-style cardViews on a swipe controller and everyone with a distinct structure and layout.

## How to ?

1.	Add it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer in your xml layout

```xml
 <it.seaba83.material_swipe_cards.custom.SwipeCardViewContainer
        android:layout_marginBottom="3dp"
        app:mainColor="@color/green_indicator"
        android:id="@+id/example_cards_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

2.	Associate reference in your activity
```java
        SwipeCardViewContainer mExampleCardsContainer = (SwipeCardViewContainer) findViewById(R.id.example_cards_container);
```

3. Define a card template extending the class AbstractCardModel
```java
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
```
4. Define a card internal layout

5. Add a compiler to your card model, exending AbstractCardCompiler. Override method compile() and here define the rules to match graphic layout and the model defined at point 3
```java
public class HelloWorldCardCompiler extends AbstractCardCompiler {

    public HelloWorldCardCompiler(Context context) {
        super(context);
    }

    @Override
    public StateCardView compile(AbstractCardModel cardModel) {
        HelloWorldCard currentItem = (HelloWorldCard) cardModel;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        StateCardView cardView = new StateCardView(getContext());
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.genric_card_view_layout, null);
        TextView titleTxt = (TextView) viewGroup.findViewById(R.id.title_txt);
        TextView messageTxt = (TextView) viewGroup.findViewById(R.id.message_txt);
        titleTxt.setText(currentItem.getTitle());
        messageTxt.setText(currentItem.getMessage());
        cardView.addView(viewGroup, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return cardView;
    }
}
```

6. Add your cardModel, with the associated compiler, to SwipeCardsContainer
```java
HelloWorldCardCompiler helloWorldCardCompiler = new HelloWorldCardCompiler(this);
mExampleCardsContainer.putCardView(0, new HelloWorldCard(getString(R.string.sample_card_one_title), getString(R.string.sample_card_one_message), helloWorldCardCompiler));
```

7. And that's all. Remember that you cans set every card in progress or error state
```java
mExampleCardsContainer.setProgress(0, true);
```

```java
mExampleCardsContainer.setError(0, getString(R.string.error_test_message), getString(R.string.error_retry_label), errorClickListener);
```

## License

material-swipe-cards library for Android
Copyright (c) 2017 Marco Caridi (https://github.com/saeba83).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
 * limitations under the License.
