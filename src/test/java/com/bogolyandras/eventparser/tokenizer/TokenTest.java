package com.bogolyandras.eventparser.tokenizer;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TokenTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstruction1() {
        new Token<>(null, ExampleEnum.A);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalConstruction2() {
        new Token<ExampleEnum>("A", null);
    }

    @Test
    public void testIfTheyEquals() {
        final Token<ExampleEnum> a1 = new Token<>("A", ExampleEnum.A);
        final Token<ExampleEnum> a2 = new Token<>("A", ExampleEnum.A);
        final Token<ExampleEnum> b = new Token<>("B", ExampleEnum.B);
        assertEquals(a1, a2);
        assertNotEquals(a1, b);
        final String s = a1.toString();
        assertTrue(s.startsWith("{"));
    }

    private enum ExampleEnum { A, B }

}
