package comp3350.Group2.areyouhungry.acceptance;


import android.util.Log;
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

import static android.content.ContentValues.TAG;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OnHandComplexTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onHandComplexTest(){
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.specifiedFoodButton2), withText("Search By Criteria"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(4, click()));

        onView(withText("Meat")).check(matches(isDisplayed()));
        onView(withText("Vegetable")).check(matches(isDisplayed()));
        onView(withText("Grain")).check(matches(isDisplayed()));
        onView(withText("Dairy")).check(matches(isDisplayed()));
        onView(withText("Fruit")).check(matches(isDisplayed()));


        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(5, click()));

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.snackbar_text), withText("1 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("1 food match")));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(7, click()));

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.snackbar_text), withText("4 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("4 food match")));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView5.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.snackbar_text), withText("0 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("0 food match")));

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView6.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView7.perform(actionOnItemAtPosition(11, click()));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView8.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView9.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction recyclerView10 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView10.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction recyclerView11 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView11.perform(actionOnItemAtPosition(7, click()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.snackbar_text), withText("0 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView10.check(matches(withText("0 food match")));

        ViewInteraction recyclerView12 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView12.perform(actionOnItemAtPosition(8, click()));

        ViewInteraction recyclerView13 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView13.perform(actionOnItemAtPosition(9, click()));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.snackbar_text), withText("1 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("1 food match")));

        ViewInteraction recyclerView14 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView14.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("random"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction recyclerView15 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView15.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(android.R.id.edit), withText("random"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText2.perform(scrollTo(), replaceText("onion"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(android.R.id.edit), withText("onion"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction recyclerView16 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView16.perform(actionOnItemAtPosition(11, click()));

        ViewInteraction recyclerView17 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView17.perform(actionOnItemAtPosition(11, click()));

        ViewInteraction recyclerView18 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView18.perform(actionOnItemAtPosition(11, click()));

        onView(withText("60+ mins")).check(doesNotExist());

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.snackbar_text), withText("1 food match"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView12.check(matches(withText("1 food match")));

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.search_fba), withText("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        extendedFloatingActionButton.perform(click());

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.content), withText("Greek Salad"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.searchresult_list),
                                        0),
                                1),
                        isDisplayed()));
        textView13.check(matches(withText("Greek Salad")));
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
