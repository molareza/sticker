package com.vanniktech.emoji.emoji;

import org.junit.Test;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

public class NatureTest {
    @Test
    public void constructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Nature.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
