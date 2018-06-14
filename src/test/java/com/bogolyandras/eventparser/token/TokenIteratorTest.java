package com.bogolyandras.eventparser.token;

import org.junit.Test;

import java.util.Arrays;

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

    private enum EnumForToken { A }

}
