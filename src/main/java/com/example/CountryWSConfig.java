package com.example;

import com.example.wsdl.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryWSConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.wsdl");
        return marshaller;
    }

    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient countryClient = new CountryClient();
        countryClient.setDefaultUri("http://localhost:8080/ws");
        countryClient.setMarshaller(marshaller);
        countryClient.setUnmarshaller(marshaller);
        return countryClient;
    }

    @Bean
    public CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";
            if(args.length > 0) {
                country = args[0];
            }

            GetCountryResponse response = countryClient.getCountry(country);
            System.out.println(response.getCountry().getCapital());
        };

    }
}
