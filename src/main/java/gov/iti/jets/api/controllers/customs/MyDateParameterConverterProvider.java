package gov.iti.jets.api.controllers.customs;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Provider
public class MyDateParameterConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter( Class<T> rawType, Type type, Annotation[] annotations ) {
        if ( rawType.getName().equals( MyDateType.class.getName() ) ) {
            return new ParamConverter<T>() {
                @Override
                public T fromString( String value ) {
                    LocalDate requestedDate = LocalDate.now();
                    if ( value.equals( "tomorrow" ) ) requestedDate = requestedDate.plusDays( 1 );
                    else if ( value.equals( "yesterday" ) ) requestedDate = requestedDate.minusDays( 1 );
                    MyDateType myDate = new MyDateType( requestedDate );
                    return rawType.cast( myDate );
                }

                @Override
                public String toString( T value ) {
                    if ( value == null ) return null;
                    return value.toString();
                }
            };
        }
        return null;
    }

}
