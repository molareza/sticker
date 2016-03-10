package com.vanniktech.emoji.emoji;

import org.junit.Test;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

public class PlacesTest {
    @Test
    public void constructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Places.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
