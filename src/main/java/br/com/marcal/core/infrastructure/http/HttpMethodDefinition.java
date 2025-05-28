package br.com.marcal.core.infrastructure.http;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class HttpMethodDefinition {

    private Method httpMethod;

    private Parameter[] parameters;

    private String mappingPath;

    public HttpMethodDefinition( Method httpMethod, Parameter[] parameters, String mappingPath ) {
        this.httpMethod = httpMethod;
        this.parameters = parameters;
        this.mappingPath = mappingPath;
    }

    public Method getHttpMethod( ) {
        return httpMethod;
    }

    public void setHttpMethod( Method httpMethod ) {
        this.httpMethod = httpMethod;
    }

    public Parameter[] getParameters( ) {
        return parameters;
    }

    public void setParameters( Parameter[] parameters ) {
        this.parameters = parameters;
    }

    public String getMappingPath( ) {
        return mappingPath;
    }

    public void setMappingPath( String mappingPath ) {
        this.mappingPath = mappingPath;
    }
}
