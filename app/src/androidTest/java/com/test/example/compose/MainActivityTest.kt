package com.test.example.compose

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    // val composeTestRule = createComposeRule() Does not start the applocation
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    lateinit var baseTest: BaseTest

    @Before
    fun setUp() {
        baseTest = BaseTest(composeTestRule)
    }

    @Test
    fun mainActivityTest() {
        composeTestRule.onNodeWithTag("myField").assertExists()
    }

    @Test
    fun mainActivityTestFail() {
        composeTestRule.onNodeWithTag("myField2").assertDoesNotExist()
        baseTest.waitForNode("myField", 1)
    }
}