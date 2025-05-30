package br.com.marcal.core.infrastructure.http;

import br.com.marcal.core.exceptions.InvalidHttpMethod;

import java.lang.annotation.Annotation;
import java.util.Optional;

public enum HTTPMethod {

    GET,
    POST,
    PATCH,
    PUT,
    DELETE;

    public static HTTPMethod getHTTPMethod( String method ) {
        try {
            return HTTPMethod.valueOf( method );
        } catch ( IllegalArgumentException e ) {
            throw new InvalidHttpMethod( "HTTP method not supported: " + method );
        }
    }

    public static HTTPMethod getHTTPMethod( Annotation annotation ) {
        String method = annotation.getClass().getSimpleName( ).toUpperCase( );
        return getHTTPMethod( method );
    }
}
