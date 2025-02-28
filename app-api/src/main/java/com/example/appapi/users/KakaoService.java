package com.example.appapi.users;

import com.example.appapi.users.model.Users;
import com.example.appapi.users.model.UsersDto;
import com.example.appapi.utils.HttpClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.appapi.users.model.UserType.CUSTOMER;

@RequiredArgsConstructor
@Service
public class KakaoService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public String kakaoLogin(String code) {
        String userInfo = HttpClientUtil.getUserInfo(code);
        String userKakaoId = userInfo.split("\"id\":")[1].split(",")[0];

        if(userKakaoId != null) {
            if (usersRepository.existsByUserId("kakao"+userKakaoId)) {
                return userKakaoId;
            } else {
                UsersDto.SignupRequest dto = new UsersDto.SignupRequest();
                dto.setUserId("kakao"+userKakaoId);
                dto.setUserType(CUSTOMER);
                dto.setName(userInfo.split("\"nickname\":\"")[1].split("\"")[0]);
                Users user = usersRepository.save(dto.toEntity(passwordEncoder.encode("kakao@"+userKakaoId)));
                UsersDto.SignupResponse.from(user);

                String kakaoId = UsersDto.SignupResponse.from(user).getUserId();
                if(usersRepository.existsByUserId(kakaoId)) {
                    return userKakaoId;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }

    }
}
