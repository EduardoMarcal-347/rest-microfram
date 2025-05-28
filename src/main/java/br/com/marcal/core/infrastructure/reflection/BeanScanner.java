package br.com.marcal.core.infrastructure.reflection;

import br.com.marcal.core.annotations.bean.Component;
import br.com.marcal.core.annotations.bean.Controller;
import br.com.marcal.core.infrastructure.bean.SimpleBeanRegistry;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.util.Set;
import java.util.stream.Collectors;

public class BeanScanner {

    private SimpleBeanRegistry beanRegistry;

    public void registerApplicationBeans( ) {
        Set<Class<?>> beanAnnotations = Set.of( Component.class, Controller.class );

        try ( ScanResult scanResult = new ClassGraph( ).enableAnnotationInfo( ).scan( ) ) {
            beanAnnotations.forEach( annotation -> {
                scanResult.getClassesWithAnnotation( annotation.getName( ) )
                        .loadClasses( )
                        .forEach( clazz -> {
                            beanRegistry.registerBean( clazz, true );
                        } );
            } );
        }
    }

    public Set<Object> getRegisteredBeansByAnnotation( Class<?> clazz ) {
        return beanRegistry.getLoadedBeans( )
                .stream( )
                .filter( obj -> obj.getClass( ).equals( clazz ) )
                .collect( Collectors.toSet( ) );
    }

}
