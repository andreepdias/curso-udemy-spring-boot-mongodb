package udemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import udemy.domain.Post;
import udemy.domain.User;
import udemy.dto.AuthorDTO;
import udemy.repository.PostRepository;
import udemy.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alexa = new User(null, "Alexa Green", "alexa@gmail.com");
        User bruna = new User(null, "Bruna Grey", "bruna@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alexa, bruna));

        Post post1 = new Post(null, sdf.parse("21/10/2020"), "Partiu viagem!", "Vou para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/10/2020"), "Bom dia", "Acordei cedo hoje!", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
