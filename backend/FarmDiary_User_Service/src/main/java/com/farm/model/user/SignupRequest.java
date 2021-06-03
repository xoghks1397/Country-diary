package com.farm.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Valid
@ToString
public class SignupRequest {

	@ApiModelProperty(required = true)
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어 숫자만 가능합니다.")
	String id;

	@ApiModelProperty(required = true)
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,20}$", message = "공백없이 영어와숫자의 조합으로 8~20자 이내로 작성해주세요.")
	String password;

	@ApiModelProperty(required = true)
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	@Pattern(regexp = "^[가-힣]*$", message = "2~4자이내 한글로만 작성하세요.")
	String name;

	@ApiModelProperty(required = true)
	@NotBlank(message = "닉네임은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 아닙니다.")
	String email;
}
