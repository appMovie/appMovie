package mindera.porto.AppMovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.porto.AppMovie.TestcontainersConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvcM;

   @Autowired
   protected ObjectMapper objectMapper;

}
