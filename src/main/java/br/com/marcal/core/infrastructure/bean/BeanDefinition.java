package br.com.marcal.core.infrastructure.bean;

public class BeanDefinition {

    private final Class<?> beanClass;

    private final boolean singleton;

    private Object instance;

    public BeanDefinition( Class<?> beanClass, boolean singleton ) {
        this.beanClass = beanClass;
        this.singleton = singleton;
    }

    public Class<?> getBeanClass( ) {
        return beanClass;
    }

    public boolean isSingleton( ) {
        return singleton;
    }

    public Object getInstance( ) {
        return instance;
    }

    public void setInstance( Object instance ) {
        this.instance = instance;
    }

}
