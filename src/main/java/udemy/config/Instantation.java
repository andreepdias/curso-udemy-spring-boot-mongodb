package udemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import udemy.domain.User;
import udemy.repository.UserRepository;

import java.util.Arrays;

@Configuration
public class Instantation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alexa = new User(null, "Alexa Green", "alexa@gmail.com");
        User bruna = new User(null, "Bruna Grey", "bruna@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alexa, bruna));
    }
}
