package br.com.marcal.core.infrastructure.http;

import br.com.marcal.core.infrastructure.reflection.HttpMethodDiscovery;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class HttpServlet implements HttpHandler {

    private final HttpMethodDiscovery httpMethodDiscovery;

    public HttpServlet( HttpMethodDiscovery httpMethodDiscovery ) {
        this.httpMethodDiscovery = httpMethodDiscovery;
    }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        HttpServletRequest req = getHttpServletRequest( exchange );
        if ( req == null ) { return; }

    }

    public HttpServletRequest getHttpServletRequest( HttpExchange exchange ) {
        HTTPMethod method = HTTPMethod.getHTTPMethod( exchange.getRequestMethod( ) );
        String path = exchange.getRequestURI( ).getPath( );
        HttpMethodDefinition def = httpMethodDiscovery.getHttpMethodDefinition( path, method );
        if ( def == null ) { return null; }
        return new HttpServletRequest( exchange, def );
    }

}
