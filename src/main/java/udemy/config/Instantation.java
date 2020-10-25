package udemy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import udemy.domain.Post;
import udemy.domain.User;
import udemy.dto.AuthorDTO;
import udemy.dto.CommentDTO;
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

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/10/2020"), new AuthorDTO(alexa));
        CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/10/2020"), new AuthorDTO(bruna));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/10/2020"), new AuthorDTO(alexa));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
