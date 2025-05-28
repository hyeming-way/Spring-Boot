package net.skhu.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//모델 역할을 하는 클래스 : 입력폼에서 입력한 정보를 받아서 저장 및 유효성 검사하는 용도

@Data
public class StudentEdit {

	int id;

	@NotEmpty @NotBlank
	@Size(min=8, max=12)
	String studentNo;

	@NotEmpty @NotBlank
	@Size(min=2, max=20)
	String name;

	@Min(value = 1, message = "학과를 선택하세요.")
	int departmentId;

	@NotEmpty @NotBlank
	// 010-으로 시작하는지? 두번째 번호가 0~9사이의 3자~4자 사이인지? 세번째 번호가 0~9사이의 4자리인지?
	@Pattern(regexp = "010-[0-9]{3,4}-[0-9]{4}", message = "휴대폰 번호를 입력하세요. 예: 010-000-0000")
	String phone;

	@NotEmpty
	@Pattern(regexp = "남|여")
	String sex;

	@NotEmpty @Email
	String email;

}
