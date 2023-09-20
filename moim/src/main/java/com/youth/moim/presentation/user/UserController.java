package com.youth.moim.presentation.user;

import com.youth.moim.application.user.UserService;
import com.youth.moim.domain.user.LoginUser;
import com.youth.moim.domain.user.UserInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (!idx.equals(user.getIdx())) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        UserInfo.Main result = userService.retrieveUser(idx);
        return UserResponse.RetrieveUser.builder().user(result).build();
    }

    @PatchMapping("/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modifyUser(@PathVariable(value = "idx") Long idx,
                             @RequestBody @Valid UserRequest.ModifyUser request,
                             @AuthenticationPrincipal(expression = "loginUser") LoginUser user) {
        if (!idx.equals(user.getIdx())) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        userService.modifyUser(idx, request);
    }

    @PostMapping("/{idx}/change-role")
    public void changeUserRole(@PathVariable(value = "idx") Long idx,
                               @AuthenticationPrincipal(expression = "loginUser") LoginUser user) {
        if (!idx.equals(user.getIdx())) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        userService.changeUserRole(idx);
    }
}
