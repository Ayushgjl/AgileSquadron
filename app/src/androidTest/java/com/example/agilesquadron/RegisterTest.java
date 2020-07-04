package com.example.agilesquadron;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterTest {

    @Rule
    public ActivityTestRule<RegisterActivity> testRule = new ActivityTestRule<> (RegisterActivity.class);
    String expected ="true";

    @Test
    public void RegisterTest(){

        onView(withId(R.id.imgProfile))
                .perform(click());

        onView(withId(R.id.etfirstname))
                .perform(typeText("arju"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etlastname))
                .perform(typeText("dutta"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etPhoneNumber))
                .perform(typeText("987654321"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etUsername))
                .perform(typeText("arjudutta1"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etpassword))
                .perform(typeText("arjudutta11"))
                .perform(ViewActions.closeSoftKeyboard());





        onView(withId(R.id.btnregister)) .perform(click());



    }
}
