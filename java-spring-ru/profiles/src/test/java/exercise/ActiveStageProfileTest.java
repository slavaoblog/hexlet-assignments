
package exercise;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles(value = "stage")
public class ActiveStageProfileTest {

    private static final Logger logger = LoggerFactory.getLogger(ActiveStageProfileTest.class);

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Test
    void testProperties() {
        logger.info("dataSourceUrl: {}", dataSourceUrl);
        logger.info("dataSourceUsername: {}", dataSourceUsername);
        logger.info("dataSourcePassword: {}", dataSourcePassword);

        assertThat(dataSourceUrl).isEqualTo("jdbc:h2:file:./test");
        assertThat(dataSourceUsername).isEqualTo("admin");
        assertThat(dataSourcePassword).isEqualTo("admin");
    }
}
