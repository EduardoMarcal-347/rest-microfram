package br.com.marcal.core.utlis;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Serializer {

    static <T> T fromJson( InputStream inputStream, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue( inputStream, clazz );
    }

}
