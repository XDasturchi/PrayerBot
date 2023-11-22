package user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository {

    private static final List<User> users = new ArrayList<>();

    private static final UserRepository userRepository = new UserRepository();


    public void add(User user) {
        Optional<User> user1 = findById(user.getUserId());

        if (user1.isEmpty()) {
            users.add(user);
        }
    }

    public Optional<User> findById(long user_id) {
        return users.stream()
                .filter(user -> user.getUserId() == user_id)
                .findFirst();
    }


    public List<User> getAll() {
        return users;
    }


    public static UserRepository getInstance() {
        return userRepository;
    }
}
