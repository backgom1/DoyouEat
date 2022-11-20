package com.DoyouEat.DYEat.controller.signup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
public class signupForm {

    private Long id;

    @NotBlank(message = "아이디는 필수 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String emailId;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String nickname;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    private int type;

    private LocalDateTime newDate;
}
