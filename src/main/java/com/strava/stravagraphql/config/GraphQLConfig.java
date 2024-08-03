package com.strava.stravagraphql.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.DateTime).scalar(instantScalar());
    }

    @Bean
    public GraphQLScalarType instantScalar() {
        return GraphQLScalarType.newScalar()
                .name("Instant")
                .description("Java Instant as Scalar")
                .coercing(new Coercing<Instant, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                        if (dataFetcherResult instanceof Instant) {
                            return ((Instant) dataFetcherResult).toString();
                        } else {
                            throw new CoercingSerializeException("Expected a Instant but got " + dataFetcherResult.getClass());
                        }
                    }

                    @Override
                    public Instant parseValue(Object input) throws CoercingParseValueException {
                        try{
                            if (input instanceof String){
                                return Instant.parse((String)input);
                            } else {
                                throw new CoercingParseValueException("Expected a String but got " + input.getClass());
                            }
                        } catch (DateTimeParseException e){
                            throw new CoercingParseValueException(String.format("Not a valid Instant: '%s'.", input), e);
                        }
                    }

                    @Override
                    public Instant parseLiteral(Object input) throws CoercingParseLiteralException {
                        if(!(input instanceof String)){
                            throw new CoercingParseLiteralException("Expected a String but got " + input.getClass());
                        }
                        try {
                            return Instant.parse((String)input);
                        } catch (DateTimeParseException e){
                            throw new CoercingParseLiteralException(String.format("Not a valid Instant: '%s'.", input), e);
                        }
                    }
                }).build();

    }
}
