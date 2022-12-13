package com.test.example.compose

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry

class BaseTest(
    protected val composeTestRule: ComposeTestRule
) {

    fun getString(@StringRes id: Int): String {
        return InstrumentationRegistry.getInstrumentation().targetContext.getString(id)
    }

    fun click(@IdRes id: Int) {
        onView(withId(id)).perform(ViewActions.click())
    }

    fun isDisplayed(@IdRes id: Int) {
        onView(withId(id))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    fun scrollTo(@IdRes id: Int) {
        onView(withId(id)).perform(ScrollToAction())
    }

    fun waitForNode(testTag: String, size: Int = 1) {
        composeTestRule.waitUntil(2) {
            composeTestRule
                .onAllNodesWithTag(
                    testTag,
                    useUnmergedTree = true
                )
                .fetchSemanticsNodes().size == size
        }
    }

    fun waitForText(stringId: Int, size: Int = 1) {
        composeTestRule.waitUntil(2) {
            composeTestRule
                .onAllNodesWithText(
                    getString(stringId),
                    useUnmergedTree = true
                )
                .fetchSemanticsNodes().size == size
        }
    }
}