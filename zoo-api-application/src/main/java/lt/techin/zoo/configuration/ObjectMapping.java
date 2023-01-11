package lt.techin.zoo.configuration;

//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapping {

    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        return new MappingJackson2XmlHttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .defaultUseWrapper(false)
                        .createXmlMapper(true)
                        .build()
        );
    }

//    @Bean
//    @Primary
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper()
//                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
//    }

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer customBuilder() {
//        return builder -> {
//            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//        }
//    }

}
