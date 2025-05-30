package br.com.marcal.core.infrastructure.http;

import br.com.marcal.core.infrastructure.reflection.HttpMethodDiscovery;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class HttpServer implements HttpHandler {

    private final HttpMethodDiscovery httpMethodDiscovery;

    public HttpServer( HttpMethodDiscovery httpMethodDiscovery ) {
        this.httpMethodDiscovery = httpMethodDiscovery;
    }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        HTTPMethod method = HTTPMethod.getHTTPMethod( exchange.getRequestMethod( ) );
        String path = exchange.getRequestURI( ).getPath( );
        HttpMethodDefinition def = httpMethodDiscovery.getHttpMethodDefinition( path, method );
        if ( def == null ) { return; }
    }

}
