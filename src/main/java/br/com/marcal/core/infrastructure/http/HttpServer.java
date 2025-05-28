package br.com.marcal.core.infrastructure.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HttpServer implements HttpHandler {

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        HTTPMethod method = HTTPMethod.getHTTPMethod( exchange.getRequestMethod() );
        String path = exchange.getRequestURI( ).getPath( );
        //todo: find @httpMethod anottation
    }
}
