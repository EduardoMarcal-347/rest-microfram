package br.com.marcal.core.infrastructure.reflection;

import br.com.marcal.core.annotations.bean.Controller;
import br.com.marcal.core.annotations.rest.http.*;
import br.com.marcal.core.infrastructure.http.HTTPMethod;
import br.com.marcal.core.infrastructure.http.HttpMethodDefinition;
import br.com.marcal.core.infrastructure.http.HttpMethodRegistry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class HttpMethodDiscovery {

    private final BeanDiscovery beanDiscovery;

    private final HttpMethodRegistry httpMethodRegistry;

    public HttpMethodDiscovery( BeanDiscovery beanDiscovery, HttpMethodRegistry httpMethodRegistry ) {
        this.beanDiscovery = beanDiscovery;
        this.httpMethodRegistry = httpMethodRegistry;
    }

    public void discoverHttpMethods( ) {
        Set<Class<?>> httpAnnotations = Set.of( GET.class, POST.class, PUT.class, DELETE.class, PATCH.class );
        Set<Object> beansDef = beanDiscovery.getRegisteredBeansByAnnotation( Controller.class );

        for ( Object bean : beansDef ) {
            Class<?> beanClass = bean.getClass( );
            for ( Method methodHandler : beanClass.getDeclaredMethods( ) ) {
                getHttpAnnotation( methodHandler, httpAnnotations )
                        .ifPresent( annotation -> {
                            String routeValue = getRoutingValue( annotation );
                            HTTPMethod httpMethod = HTTPMethod.getHTTPMethod( annotation );
                            httpMethodRegistry.registerMethod( routeValue, httpMethod, methodHandler );
                        } );
            }
        }
    }

    public HttpMethodDefinition getHttpMethodDefinition( String path, HTTPMethod httpMethod ) {
        return httpMethodRegistry.getHandler( path, httpMethod );
    }

    private Optional<Annotation> getHttpAnnotation( Method method, Set<Class<?>> httpAnnotations ) {
        return Arrays.stream( method.getAnnotations( ) )
                .filter( a -> httpAnnotations.contains( a.annotationType( ) ) )
                .findFirst( );
    }

    private String getRoutingValue( Annotation annotation ) {
        try {
            Method mappingMethod = annotation.annotationType( ).getMethod( "route" );
            return ( String ) mappingMethod.invoke( annotation );
        } catch ( Exception e ) {
            throw new RuntimeException( );
        }
    }
}
