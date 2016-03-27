package com.vanniktech.emoji.emoji;

import org.junit.Test;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

public class ElectronicsTest {
    @Test
    public void testConstructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Electronics.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
