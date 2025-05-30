package br.com.marcal.core.infrastructure.http;

import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class HttpMethodRegistry {

    private final Map<String, EnumMap<HTTPMethod, HttpMethodDefinition>> routeMap = new HashMap<>();

    public void registerMethod( String routeValue, HTTPMethod httpMethod, Method methodHandler ) {
        HttpMethodDefinition def = new HttpMethodDefinition( methodHandler, methodHandler.getParameters( ), routeValue );
        routeMap.computeIfAbsent( routeValue, k -> new EnumMap<>( HTTPMethod.class ) )
                .put( httpMethod, def );
    }

    public HttpMethodDefinition getHandler( String path, HTTPMethod httpMethod ) {
        EnumMap<HTTPMethod, HttpMethodDefinition> methodMap = routeMap.get( path );
        return methodMap != null ? methodMap.get( httpMethod ) : null;
    }

}
