package br.com.marcal.core.infrastructure.bean;

import br.com.marcal.core.exceptions.NotRegisteredBeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleBeanRegistry {

    private final Map<Class<?>, BeanDefinition> registry = new HashMap<>( );

    public void registerBean( Class<?> clazz, boolean isSingleton ) {
        BeanDefinition bean = new BeanDefinition( clazz, isSingleton );
        registry.put( clazz, bean );
    }

    public Object getBean( Class<?> clazz ) {
        BeanDefinition def = Optional.of( contaisBean( clazz ) )
                .orElseThrow( ( ) -> new NotRegisteredBeanDefinition( "Not registered Bean " + clazz.getSimpleName( ) ) );

        if ( def.isSingleton( ) ) {
            if ( def.getInstance( ) == null ) {
                try {
                    Object instance = def.getBeanClass( ).getDeclaredConstructor( ).newInstance( );
                    def.setInstance( instance );
                } catch ( Exception e ) {
                    throw new RuntimeException( e );
                }
            }
            return def.getInstance( );
        }

        try {
            return def.getBeanClass( ).getDeclaredConstructor( ).newInstance( );
        } catch ( Exception e ) {
            throw new RuntimeException( e );
        }
    }

    public Set<Object> getLoadedBeans( ) {
        return registry.keySet( )
                .stream( )
                .map( this::getBean )
                .collect( Collectors.toSet( ) );
    }

    public BeanDefinition contaisBean( Class<?> clazz ) {
        return registry.get( clazz );
    }

}
