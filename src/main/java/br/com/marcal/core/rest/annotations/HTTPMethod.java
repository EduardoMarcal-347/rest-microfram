package br.com.marcal.core.rest.annotations;

import br.com.marcal.core.exceptions.InvalidHttpMethod;

import java.util.Optional;

public enum HTTPMethod {

    GET,
    POST,
    PATCH,
    PUT,
    DELETE;

    public static HTTPMethod getHTTPMethod( String method ) {
        return Optional.of( HTTPMethod.valueOf( method.toUpperCase( ) ) )
                .orElseThrow( () -> new InvalidHttpMethod( "HTTP method not supported: " + method ) );
    }

}
