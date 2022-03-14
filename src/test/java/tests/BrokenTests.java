package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrokenTests {

    @Test
    @Disabled
    void test02() {
        assertTrue(false);
    }

    @Test
    @Disabled("With some reason")
    void test03() {
        assertTrue(false);
    }
}
