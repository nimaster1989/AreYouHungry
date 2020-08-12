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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReceiveRecipeSuggestionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void receiveRecipeSuggestion(){
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.specified_Food_Button), withText("Find your food!"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radio_button2), withText("Ants are like little chips... right?"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
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
                allOf(withId(R.id.radio_button3), withText("Wow thats a big horse"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
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
                allOf(withId(R.id.radio_button1), withText("Defuse a bomb with 10 seconds left"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
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
                allOf(withId(R.id.radio_button2), withText("Cant have a high amount of covid cases if we dont test land"),
                        childAtPosition(
                                allOf(withId(R.id.radio_group),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
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
        try {
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.viewRecipeButton), withText("View Recipe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton7.perform(click());
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
