package com.bogolyandras.eventparser.parser;

/**
 * Exception class indication failed parsing from valid tokens into a parse tree.
 */
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }

}
