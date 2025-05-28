package br.com.marcal.core.infrastructure.http;

import br.com.marcal.core.annotations.bean.Controller;
import br.com.marcal.core.annotations.rest.http.*;
import br.com.marcal.core.infrastructure.reflection.BeanDiscovery;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public class HttpServer implements HttpHandler {

    private final BeanDiscovery beanDiscovery;

    private Set<HttpMethodDefinition> httpMethods;

    public HttpServer( BeanDiscovery beanDiscovery ) { this.beanDiscovery = beanDiscovery; }

    @Override
    public void handle( HttpExchange exchange ) throws IOException {
        HTTPMethod method = HTTPMethod.getHTTPMethod( exchange.getRequestMethod( ) );
        String path = exchange.getRequestURI( ).getPath( );
        getHttpMethod( path ).ifPresent( httpMethod -> {
            //todo: validate params and invoke method
        });
    }

    public void discoverHttpMethods( ) {
        Set<Class<?>> httpAnnotations = Set.of( GET.class, POST.class, PUT.class, DELETE.class, PATCH.class );
        Set<Object> beansDef = beanDiscovery.getRegisteredBeansByAnnotation( Controller.class );

        for ( Object bean : beansDef ) {
            Class<?> beanClass = bean.getClass( );
            for ( Method method : beanClass.getDeclaredMethods( ) ) {
                getHttpAnnotation( method, httpAnnotations )
                        .ifPresent( annotation -> {
                            String mappingValue = getAnnotationMappingValue( annotation );
                            HttpMethodDefinition def = new HttpMethodDefinition( method, method.getParameters( ), mappingValue );
                            httpMethods.add( def );
                        } );
            }
        }
    }

    public Optional<HttpMethodDefinition> getHttpMethod( String path ) {
        return httpMethods.stream()
                .filter( method -> method.getMappingPath().equals( path ) )
                .findFirst();
    }

    private Optional<Annotation> getHttpAnnotation( Method method, Set<Class<?>> httpAnnotations ) {
        return Arrays.stream( method.getAnnotations( ) )
                .filter( a -> httpAnnotations.contains( a.annotationType( ) ) )
                .findFirst( );
    }

    private String getAnnotationMappingValue( Annotation annotation ) {
        try {
            Method mappingMethod = annotation.annotationType( ).getMethod( "mapping" );
            return ( String ) mappingMethod.invoke( annotation );
        } catch ( Exception e ) {
            throw new RuntimeException( );
        }
    }
}
