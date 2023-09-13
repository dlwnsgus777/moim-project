package com.youth.moim.presentation.user;

import groovy.transform.builder.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest {
  private UserController userController;
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository = new UserRepository();
    UserStore userStore = new UserStore(userRepository);
    UserReader userReader = new UserReader(userRepository);
    UserService userService = new UserService(userStore, userReader);
    userController = new UserController(userService);
  }

  @Test
  @DisplayName("유저 저장 테스트")
  void test20230912213527() {
    // given
    UserRequest.SignInOrganizer request = new UserRequest.SignInOrganizer(
            "이름",
            "19930927",
            Gender.MALE,
            "dlwnsgus",
            "password",
            "dlwnsgus777@test.com",
            "company"
    );

    // when
    userController.signIn(request);

    // then
    User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(request.email());

  }

  public static class UserRequest {
    public record SignInOrganizer(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            String company
    ) {

      public User toEntity() {
        return new User(
                null,
                name,
                birth,
                gender,
                id,
                password,
                email,
                company
        );
      }
    }
  }

  public enum Gender {
    FEMALE, MALE
  }

  public static class UserService {
    UserStore userStore;
    UserReader userReader;

    public UserService(UserStore userStore, UserReader userReader) {
      this.userStore = userStore;
      this.userReader = userReader;
    }

    public void signIn(UserRequest.SignInOrganizer request) {
      Optional<User> user = userReader.findByEmail(request.email());
      if (user.isEmpty()) {
        userStore.registerUser(request.toEntity());
      }
    }
  }

  public static class UserController {
    UserService userService;

    public UserController(UserService userService) {
      this.userService = userService;
    }

    public void signIn(UserRequest.SignInOrganizer request) {
      userService.signIn(request);
    }
  }

  public static class UserStore {
    private final UserRepository userRepository;

    public UserStore(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    public void registerUser(User entity) {
      userRepository.save(entity);
    }
  }

  public static class UserReader {
    private final UserRepository userRepository;

    public UserReader(UserRepository userRepository) {
      this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
    }
  }

  public static class UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long nextId = 1L;

    public void save(final User user) {
      user.assignId(nextId);
      nextId++;
      users.put(user.getIdx(), user);
    }

    public List<User> findAll() {
      return new ArrayList<>(users.values());
    }

    public Optional<User> findByEmail(String email) {
      return users.values().stream()
              .filter(user -> user.getEmail().equals(email))
              .findAny();
    }
  }

  public static class User {
    private Long idx;
    private String name;
    private String birth;
    private Gender gender;
    private String id;
    private String password;
    private String email;
    private String company;

    @Builder
    public User(Long idx, String name, String birth, Gender gender, String id, String password, String email, String company) {
      this.idx = idx;
      this.name = name;
      this.birth = birth;
      this.gender = gender;
      this.id = id;
      this.password = password;
      this.email = email;
      this.company = company;
    }

    public Long getIdx() {
      return idx;
    }

    public void assignId(Long idx) {
      this.idx = idx;
    }

    public String getEmail() {
      return email;
    }
  }
}
