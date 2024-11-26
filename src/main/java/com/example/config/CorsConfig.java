import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Tüm endpointlere izin verir
                        .allowedOrigins("http://localhost:8081") // React uygulamasının çalıştığı port
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metodları
                        .allowedHeaders("*") // İzin verilen header'lar
                        .allowCredentials(true); // Credential'lara izin ver
            }
        };
    }
}
