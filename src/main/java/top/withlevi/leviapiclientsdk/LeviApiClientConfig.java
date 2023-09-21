package top.withlevi.leviapiclientsdk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.withlevi.leviapiclientsdk.client.LeviApiClient;

/**
 * Created on 9/21/2023 5:52 PM
 *
 * @author Levi
 */

@Configuration
@ConfigurationProperties("leviapi.client")
@ComponentScan
@Data
public class LeviApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public LeviApiClient leviApiClient() {
        return new LeviApiClient(accessKey, secretKey);
    }


}
