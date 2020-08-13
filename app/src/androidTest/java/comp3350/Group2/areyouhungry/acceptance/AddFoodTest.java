package comp3350.Group2.areyouhungry.acceptance;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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

import static androidx.test.espresso.Espresso.onData;
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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddFoodTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addFoodTest(){
        ViewInteraction appCompatButton = onView(
                allOf(ViewMatchers.withId(R.id.add_food_button), withText("Add Food"),
                        childAtPosition(
                                allOf(withId(R.id.container),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
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
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatEditText = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText.perform(scrollTo(), replaceText("white cut chicken"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        onView(withText("white cut chicken")).check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText2.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        onView(withText("5")).check(matches(isDisplayed()));

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView3.perform(actionOnItemAtPosition(3, click()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText3.perform(scrollTo(), replaceText("40"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());

        onView(withText("40")).check(matches(isDisplayed()));

        ViewInteraction recyclerView4 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView4.perform(actionOnItemAtPosition(4, click()));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText4.perform(scrollTo(), replaceText("Sweet"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        onView(withText("Sweet")).check(matches(isDisplayed()));

        ViewInteraction recyclerView5 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView5.perform(actionOnItemAtPosition(5, click()));

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(3);
        appCompatCheckedTextView.perform(click());

        ViewInteraction recyclerView6 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView6.perform(actionOnItemAtPosition(6, click()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(android.R.id.edit),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatEditText5.perform(scrollTo(), replaceText("Chinese"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

        onView(withText("Chinese")).check(matches(isDisplayed()));

        ViewInteraction recyclerView7 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView7.perform(actionOnItemAtPosition(8, click()));

        ViewInteraction recyclerView8 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView8.perform(actionOnItemAtPosition(10, click()));

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.addfood_fba), withText("Next Step"),
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

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.ingredientName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingredientLayout),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("chicken"), closeSoftKeyboard());

        onView(withId(R.id.ingredientName)).check(matches(withText("chicken")));

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.ingredientQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("1kg"), closeSoftKeyboard());

        onView(withId(R.id.ingredientQuantity)).check(matches(withText("1kg")));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.addIngredientButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                4)));
        materialButton.perform(scrollTo(), click());

        onView(withText("chicken - 1kg")).check(matches(isDisplayed()));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.ingredientName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingredientLayout),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("salt"), closeSoftKeyboard());

        onView(withId(R.id.ingredientName)).check(matches(withText("salt")));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.ingredientQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("10g"), closeSoftKeyboard());

        onView(withId(R.id.ingredientQuantity)).check(matches(withText("10g")));

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.addIngredientButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                4)));
        materialButton2.perform(scrollTo(), click());

        onView(withText("salt - 10g")).check(matches(isDisplayed()));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.remove), withText("Remove"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingredientContainer),
                                        1),
                                0)));
        materialButton3.perform(scrollTo(), click());

        onView(withText("salt - 10g")).check(doesNotExist());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.ingredientName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.ingredientLayout),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), replaceText("sea salt"), closeSoftKeyboard());

        onView(withId(R.id.ingredientName)).check(matches(withText("sea salt")));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.ingredientQuantity),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0)));
        textInputEditText6.perform(scrollTo(), replaceText("20g"), closeSoftKeyboard());

        onView(withId(R.id.ingredientQuantity)).check(matches(withText("20g")));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.addIngredientButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                4)));
        materialButton4.perform(scrollTo(), click());

        onView(withText("sea salt - 20g")).check(matches(isDisplayed()));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.instructionName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.instructionLayout),
                                        0),
                                0)));
        textInputEditText7.perform(scrollTo(), replaceText("cook the chicken"), closeSoftKeyboard());

        onView(withId(R.id.instructionName)).check(matches(withText("cook the chicken")));

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.addInstructionButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                8)));
        materialButton5.perform(scrollTo(), click());

        onView(withText("cook the chicken")).check(matches(isDisplayed()));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.instructionName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.instructionLayout),
                                        0),
                                0)));
        textInputEditText8.perform(scrollTo(), replaceText("paste with salt"), closeSoftKeyboard());

        onView(withId(R.id.instructionName)).check(matches(withText("paste with salt")));

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.addInstructionButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                8)));
        materialButton6.perform(scrollTo(), click());

        onView(withText("cook the chicken")).check(matches(isDisplayed()));

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.instructionName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.instructionLayout),
                                        0),
                                0)));
        textInputEditText9.perform(scrollTo(), replaceText("roast the chicken"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.addInstructionButton), withText("ADD"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.scrollView),
                                        0),
                                8)));
        materialButton7.perform(scrollTo(), click());

        onView(withId(R.id.submit)).perform(scrollTo(), click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button3), withText("OK"),
                        childAtPosition(
                                allOf(withClassName(is("com.android.internal.widget.ButtonBarLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        appCompatButton8.perform(click());

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_favorites), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        try{
            Thread.sleep(700);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        ViewInteraction recyclerView9 = onView(
                allOf(withId(R.id.favouritefood_list),
                        childAtPosition(
                                withId(R.id.frameLayout),
                                0)));

        recyclerView9.perform(actionOnItemAtPosition(3, click()));


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
