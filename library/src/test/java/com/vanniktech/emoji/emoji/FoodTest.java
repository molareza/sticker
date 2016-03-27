package com.vanniktech.emoji.emoji;

import org.junit.Test;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

public class FoodTest {
    @Test
    public void testConstructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Food.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
