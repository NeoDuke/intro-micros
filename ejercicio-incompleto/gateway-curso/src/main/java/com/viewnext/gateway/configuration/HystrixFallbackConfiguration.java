package com.viewnext.gateway.configuration;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class HystrixFallbackConfiguration{

    @Bean
    public FallbackProvider zuulFallbackProvider(){
        return new FallbackProvider(){

            @Override
            public String getRoute(){
                // Might be confusing: it's the serviceId property and not the route
                return "suma";
            }

            @Override
            public ClientHttpResponse fallbackResponse(final String route, final Throwable cause){
                return new ClientHttpResponse(){
                    @Override
                    public HttpStatus getStatusCode() throws IOException{
                        return HttpStatus.OK;
                    }

                    @Override
                    public int getRawStatusCode() throws IOException{
                        return HttpStatus.OK.value();
                    }

                    @Override
                    public String getStatusText() throws IOException{
                        return HttpStatus.OK.toString();
                    }

                    @Override
                    public void close(){
                    }

                    @Override
                    public InputStream getBody() throws IOException{
                        return new ByteArrayInputStream(
                                "{\"factorA\":\"Sorry, Service is Down!\",\"factorB\":\"?\",\"id\":null}".getBytes());
                    }

                    @Override
                    public HttpHeaders getHeaders(){
                        final HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccessControlAllowCredentials(true);
                        headers.setAccessControlAllowOrigin("*");
                        return headers;
                    }
                };
            }
        };
    }
}
