package br.com.marcal.core.exceptions;

public class NotRegisteredBeanDefinition extends RuntimeException {
    public NotRegisteredBeanDefinition( String message ) {
        super( message );
    }
}
