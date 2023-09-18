package com.youth.moim.presentation.user;

import com.youth.moim.application.user.UserService;
import com.youth.moim.domain.user.LoginUser;
import com.youth.moim.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{idx}")
    public UserResponse.RetrieveUser retrieveUser(@PathVariable(value = "idx") Long idx,
                                                  @AuthenticationPrincipal(expression = "loginUser") LoginUser user) {

        UserInfo.Main result = userService.retrieveUser(idx);
        return UserResponse.RetrieveUser.builder().user(result).build();
    }
}
