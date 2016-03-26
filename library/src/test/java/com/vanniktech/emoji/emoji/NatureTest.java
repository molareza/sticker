package com.vanniktech.emoji.emoji;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

import org.junit.Test;

public class NatureTest {
    @Test
    public void constructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Nature.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
