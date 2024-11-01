package aldovalzani.prog_sett_be_m2_w2.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {
    @Bean
    public Cloudinary getImageUploader(@Value("${cloudinary.name}") String cloudName,
                                       @Value("${clodinary.key}") String apiKey,
                                       @Value("${cloudinary.key}") String apiSecret) {
        Map<String, String> confing = new HashMap<>();
        confing.put("cloud_name", cloudName);
        confing.put("api_keu", apiKey);
        confing.put("api_secret", apiSecret);
        return new Cloudinary(confing);
    }
}
