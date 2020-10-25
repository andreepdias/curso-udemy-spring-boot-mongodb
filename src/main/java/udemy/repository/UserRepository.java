package udemy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import udemy.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
