package com.swipeapp.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.hasImeAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.uiautomator.UiDevice
import com.swipeapp.R
import org.junit.Rule
import org.junit.Test
import java.util.EnumSet.allOf


class ProductActivityTest{

    private lateinit var device: UiDevice


    @get:Rule
    val mSuggestionActivityTestRule = activityScenarioRule<ProductActivity>()

   @Test
    fun setup(){
        //onView(withId(R.id.fb_button).perform(ViewActions.click()))
    }

    @Test
    fun add(){
        Intents.init()
       // val expected = allOf(hasImeAction(Intent(AddProductActivity::javaClass)))


    }
}