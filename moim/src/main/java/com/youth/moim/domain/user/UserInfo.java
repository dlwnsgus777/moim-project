package com.youth.moim.domain.user;

import lombok.Builder;

import java.time.LocalDate;

public class UserInfo {
    public record Main(
        Long idx,
        String name,
        LocalDate birth,
        Gender gender,
        String id,
        String email,
        String company,
        String ignoreFoods,
        String description
    ) {
        @Builder
        public Main {
        }

        public static Main of(User user) {
            if (user.getRole().equals(MoimRole.ORGANIZER)) {
                return Main.builder()
                    .idx(user.getIdx())
                    .name(user.getName())
                    .birth(user.getBirth())
                    .gender(user.getGender())
                    .email(user.getEmail())
                    .company(user.getCompany())
                    .build();
            } else {
                return Main.builder()
                    .idx(user.getIdx())
                    .name(user.getName())
                    .birth(user.getBirth())
                    .gender(user.getGender())
                    .email(user.getEmail())
                    .ignoreFoods(user.getIgnoreFoods())
                    .description(user.getDescription())
                    .build();
            }
        }
    }
}
