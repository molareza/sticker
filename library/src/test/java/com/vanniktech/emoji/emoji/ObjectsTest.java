package com.vanniktech.emoji.emoji;

import org.junit.Test;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

public class ObjectsTest {
    @Test
    public void constructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(Objects.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}
