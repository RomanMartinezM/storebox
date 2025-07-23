package myproject.storebox.users;
//import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public Iterable<User> getAllUsers() {
            return userRepository.findAll();
    }
    /*{
        return userService.getAllUsers(sortBy);
    }*/

}
