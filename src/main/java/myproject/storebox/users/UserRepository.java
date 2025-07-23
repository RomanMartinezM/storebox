package myproject.storebox.users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
/**
 * Retrieves an Optional containing a User entity based on the given email.
 *
 * @param email the email of the user to search for
 * @return an Optional containing the User if found, or an empty Optional if not found
 */

/* <<<<<<<<<<  c475d003-6d19-4832-b9af-d2b7cc2ede28  >>>>>>>>>>> */
    Optional<User> findByEmail(String email);
}
