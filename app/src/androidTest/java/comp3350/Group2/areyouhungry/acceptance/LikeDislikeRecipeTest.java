package comp3350.Group2.areyouhungry.acceptance;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LikeDislikeRecipeTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void likeDislikeRecipeTest(){
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.specifiedFoodButton), withText("Find your food!"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radio_button4), withText("Ahhh A nice fresh box!"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatRadioButton2 = onView(
                allOf(withId(R.id.radio_button4), withText("A horse bred so large for the purpose to stop this dumb statement"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatRadioButton3 = onView(
                allOf(withId(R.id.radio_button4), withText("Play a game of Monopoly"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatRadioButton4 = onView(
                allOf(withId(R.id.radio_button4), withText("The thing the Rat in Ratatouille made"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatRadioButton5 = onView(
                allOf(withId(R.id.radio_button4), withText("Breathing the Air here is like smoking a pack of cigs a day"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.like),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.snackbar_text), withText("You liked it! Thank You For The Feedback!"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("You liked it! Thank You For The Feedback!")));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.returnHomeButton), withText("Return Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton7.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.specifiedFoodButton), withText("Find your food!"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton8.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatRadioButton6 = onView(
                allOf(withId(R.id.radio_button4), withText("Ahhh A nice fresh box!"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton6.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatRadioButton7 = onView(
                allOf(withId(R.id.radio_button4), withText("A horse bred so large for the purpose to stop this dumb statement"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton7.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatRadioButton8 = onView(
                allOf(withId(R.id.radio_button4), withText("Play a game of Monopoly"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton8.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatRadioButton9 = onView(
                allOf(withId(R.id.radio_button4), withText("The thing the Rat in Ratatouille made"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton9.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatRadioButton10 = onView(
                allOf(withId(R.id.radio_button4), withText("Breathing the Air here is like smoking a pack of cigs a day"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatRadioButton10.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.preferredSearch), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton13.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.dislike),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                12),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.like),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                11),
                        isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.snackbar_text), withText("You Disliked it :( Thank You For The Feedback!"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("You Disliked it :( Thank You For The Feedback!")));

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.returnHomeButton), withText("Return Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton14.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position){

        return new TypeSafeMatcher<View>(){
            @Override
            public void describeTo(Description description){
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view){
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
