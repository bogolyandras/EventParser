package com.bogolyandras.eventparser.token;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TokenizerTest extends Tokenizer<TokenizerTest.TestTokenValues> {

    @Test
    public void testStringParsing() {
        final List<Token<TestTokenValues>> a_b_c = super.tokenize("A B C");
        assertEquals(3, a_b_c.size());
        assertEquals(a_b_c.get(0).kind, TestTokenValues.A);
        assertEquals(a_b_c.get(1).kind, TestTokenValues.B);
        assertEquals(a_b_c.get(2).kind, TestTokenValues.C);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullString() {
        super.tokenize(null);
    }

    @Test
    public void testEmptyTokens() {
        final List<Token<TestTokenValues>> tokens = super.tokenize("");
        assertEquals(0, tokens.size());
    }

    @Override
    protected Token<TestTokenValues> parseString(String string) {
        switch (string.toUpperCase()) {
            case "A":
                return new Token<>(string, TestTokenValues.A);
            case "B":
                return new Token<>(string, TestTokenValues.B);
            case "C":
                return new Token<>(string, TestTokenValues.C);
            default:
                throw new IllegalArgumentException("Failed to parse " + string);
        }
    }

    public enum TestTokenValues {
        A, B, C
    }

}
