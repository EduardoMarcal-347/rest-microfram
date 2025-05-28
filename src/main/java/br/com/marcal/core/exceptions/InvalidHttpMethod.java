package br.com.marcal.core.exceptions;

public class InvalidHttpMethod extends RuntimeException {
    public InvalidHttpMethod( String message ) {
        super( message );
    }
}
