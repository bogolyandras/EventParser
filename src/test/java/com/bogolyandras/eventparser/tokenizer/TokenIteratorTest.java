package com.bogolyandras.eventparser.tokenizer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TokenIteratorTest {

    @Test
    public void testTokenStream() {
        final TokenIterator<EnumForToken> twoElementStream = new TokenIterator<>(
                Arrays.asList(
                        new Token<>("A", EnumForToken.A),
                        new Token<>("A", EnumForToken.A)
                )
        );
        assertTrue(twoElementStream.hasNext());
        twoElementStream.next();
        assertTrue(twoElementStream.hasNext());
        twoElementStream.next();
        assertFalse(twoElementStream.hasNext());
    }

    @Test(expected = IllegalAccessError.class)
    public void testOverrun() {
        final TokenIterator<EnumForToken> oneElementStream = new TokenIterator<>(
                Collections.singletonList(
                        new Token<>("A", EnumForToken.A)
                )
        );
        oneElementStream.next();
        oneElementStream.next();
    }

    private enum EnumForToken { A }

}
