package br.com.marcal.core.infrastructure.http;

import com.sun.net.httpserver.HttpExchange;

public class HttpServletRequest {

    private String path;

    private HttpMethodDefinition httpMethod;

    private Object body;

    public HttpServletRequest( HttpExchange exchange, HttpMethodDefinition httpMethod ) {
        this.path = exchange.getRequestURI().getPath();
        this.httpMethod = httpMethod;

    }
}
