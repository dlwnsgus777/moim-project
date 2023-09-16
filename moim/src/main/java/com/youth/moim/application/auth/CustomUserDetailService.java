package com.youth.moim.application.auth;

import com.youth.moim.domain.auth.LoginUser;
import com.youth.moim.domain.auth.LoginUserAdapter;
import com.youth.moim.domain.user.User;
import com.youth.moim.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
  private final UserReader userReader;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    User user = userReader.getByLoginId(id);
    LoginUser loginUser = LoginUser.builder()
            .idx(user.getIdx())
            .id(user.getId())
            .build();
    return new LoginUserAdapter(loginUser, user.getPassword());
  }
}
