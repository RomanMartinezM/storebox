package myproject.storebox.users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }

    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserException();
        }

        var user = userMapper.toEntity(request);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setRole(Role.USER);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long userId, UpdateUserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userMapper.update(request, user);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public void deleteUser(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

}
