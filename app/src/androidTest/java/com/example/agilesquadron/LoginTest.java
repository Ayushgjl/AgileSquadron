package com.example.agilesquadron;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.agilesquadron.ui.LoginActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> testRule = new ActivityTestRule<> (LoginActivity.class);
    String expected ="true";

    @Test
    public void LoginTest(){
        onView(withId(R.id.etUsername))
                .perform(typeText("arjudutta1"))
                .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.etpassword)) .perform(typeText("arjudutta11")) .perform(ViewActions.closeSoftKeyboard());

        onView(withId(R.id.btnlogin)) .perform(click());

    }
}
