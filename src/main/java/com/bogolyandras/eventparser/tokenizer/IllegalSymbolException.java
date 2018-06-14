package com.bogolyandras.eventparser.tokenizer;

/**
 * Exception type indication failed tokenization of string.
 */
public class IllegalSymbolException extends RuntimeException {

    public IllegalSymbolException(String message) {
        super(message);
    }

}
