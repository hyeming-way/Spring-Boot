package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Student;

/*
StudentMapper
   student 테이블에 대한 SQL 명령들을 구현한 mybatis mapper 이다.
   클래스가 아니고 interface 임에 주의하자.  (java interface 문법을 공부하라)

@Mapper 어노테이션
   mybatis mapper에는 반드시 이 어노테이션을 붙여야 한다.

spring mybatis 라이브러리가 자동으로 해주는 것
   StudentMapper interface의 자식 구현 익명클래스를 자동으로 구현해준다.
   그 자식 익명구현 클래스를 스프링 빈(Bean) 으로 컨테이너에 등록해서, 우리가 바로 쓸 수 있게 해줌
   그 자식 클래스가 StudentMapper interface로 부터 상속 받은 메소드를 (findAll 메소드)
   자동으로 구현해 준다.
*/
@Mapper
public interface StudentMapper {

	//실습1. 학생목록을 조회 하는 메소드
	//@Select("SELECT * FROM student")
	// List<Student>  findAll();
/*
	 위 코드 설명
	@Select("SELECT * FROM student")
	List<Student> findAll();
	   spring mybatis 라이브러리가 이 메소드를 자동으로 구현해준다.
	   자동으로 구현된 이 메소드는
      SELECT * FROM student SQL 명령을 실행하고
      이 SQL 명령의 조회 결과 데이터를
      List<Student> 타입의 객체에 채워서 리턴한다.
      즉 위 SQL 명령의 조회 결과 레코드 각각을 Student DTO 객체에 채우고
      그렇게 데이터가 채워진 Student 객체들을 List<Student> 타입의 목록 객체에 채워서 리턴한다.

	위 SQL 명령의 조회 결과 데이터가 Student DTO 객체에 채워질 때,
	조회 결과 컬럼명과 Student DTO 객체의 속성명이 일치해야 한다. (setter 메소드 이름이 일치해야 한다)
	일치하지 않은 속성은 값이 채워지지 않고 무시된다.


	조회 결과 컬럼명      자바  Student DTO객체의 set 메소드 이름
	id 				  setId
	studentNo         setStudentNo
	name			  setName
	departmentId      setDepartmentId
	phone			  setPhone
	sex				  setSex
	email			  setEmail

	Student DTO 클래스의 setter 메소드들은 lombok에 의해 자동 구현된다.

*/

	//실습2. student테이블의 학생 정보와  department소속학과테이블의 학과명을 같이 연결하여 조회
	/*
	학생 목록을 출력할 때, 소속 학과명도 출력해야 한다.
	그런데 student 테이블에 학과명 필드가 없으니,
	다음과 같이 department 테이블과 조인하여 학과명 필드도 조회해야 한다.

	student 테이블과 department 테이블을 연결(join)해서 조회한다.
	이때 student.departmentId 필드 값과 department.id 필드 값을 비교해서 연결한다.
	즉 각 학생의 소속 학과 id 값을 비교해서
	student 테이블의 레코드와 department 테이블의 레코드를 연결해서 조회한다.
	student 테이블의 모든 필드와 department 테이블의 name 필드값을 조회한다.

	참고.  SELECT s.*, d.name departmentName  줄에서
	      departmentName 부분에 대한 설명

			   조회 결과 컬럼명과 Student DTO 클래스의 속성명(setter 메소드 이름)이 일치해야 한다.
			   setter 메소드는 lombok에 의해서 자동으로 구현된다.
	*/
	@Select("""
	        SELECT s.*, d.name departmentName
	        FROM student s JOIN department d ON s.departmentId = d.id """)
	List<Student> findAll();

	   //참고.Java 여러줄 문자열 문법
	   //""" 여러줄 문자열 """
	   //SQL 명령이 긴 편이기 때문에 여러줄 문자열 문법을 사용하여 구현하였다.
	   //이 문법은 Java 15 이상 버전에서 지원된다.


	@Select("""
			SELECT s.*, d.name departmentName
			FROM student s JOIN department d ON s.departmentId = d.id
			WHERE s.name LIKE #{name}
			""")
	List<Student> findByName(String name);


    /*
    @Select: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    - 이 어노테이션은 데이터베이스에서 데이터를 가져올 때 사용됩니다.
    - "SELECT * FROM student WHERE id=#{id}":
      - SQL 쿼리입니다.
      - student 테이블에서 id가 특정 값과 일치하는 학생 정보를 가져오겠다는 의미입니다.
      - #{id}: 메서드의 파라미터로 전달된 id 값을 참조합니다.
    */
	@Select("SELECT * FROM student WHERE id = #{id}")
	Student findById(int id);
    /*
    Student findById(int id): 메서드 선언부입니다.
    - findById: 메서드 이름입니다. id 값을 이용해 학생 정보를 찾는 기능을 합니다.
    - int id: 학생의 고유 번호를 의미하는 정수형 변수입니다.
    - Student: 이 메서드는 Student 객체를 반환합니다. 즉, 특정 id 값을 가진 학생의 정보를 반환합니다.
    */


    /*
    @Update: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    - 이 어노테이션은 데이터베이스의 기존 정보를 업데이트(수정)할 때 사용됩니다.
    - UPDATE student: student 테이블의 데이터를 수정한다는 의미입니다.
    - SET: 변경할 컬럼(열)과 새로운 값을 지정하는 부분입니다.
      - studentNo = #{studentNo}: studentNo 컬럼의 값을 새롭게 설정할 studentNo 값으로 변경합니다.
      - name = #{name}: name 컬럼의 값을 새롭게 설정할 name 값으로 변경합니다.
      - departmentId = #{departmentId}: departmentId 컬럼의 값을 새롭게 설정할 departmentId 값으로 변경합니다.
      - sex = #{sex}: sex 컬럼의 값을 새롭게 설정할 sex 값으로 변경합니다.
      - phone = #{phone}: phone 컬럼의 값을 새롭게 설정할 phone 값으로 변경합니다.
      - email = #{email}: email 컬럼의 값을 새롭게 설정할 email 값으로 변경합니다.
    - WHERE id = #{id}: id가 특정 값과 일치하는 행을 수정한다는 의미입니다.
      - #{id}는 메서드에서 제공된 student 객체의 id 값을 참조합니다.
    */
	@Update("""
			UPDATE student
			SET
				studentNo = #{studentNo},
				name = #{name},
				departmentId = #{departmentId},
				phone = #{phone},
				sex = #{sex},
				email = #{email}
			WHERE id = #{id}
			""")
	void update(Student student);
    /*
    void update(Student student): 메서드 선언부입니다.
    - update: 메서드 이름입니다. 학생 정보를 데이터베이스에서 수정하는 기능을 합니다.
    - Student student: 학생 정보를 담고 있는 객체입니다.
      - 이 객체에는 studentNo, name, departmentId, phone, sex, email 등의 정보가 포함됩니다.
    */


    /*
    @Insert: MyBatis라는 라이브러리에서 사용되는 어노테이션입니다.
    - 이 어노테이션은 데이터베이스에 새로운 정보를 추가할 때 사용됩니다.
    - INSERT student (...): student 테이블에 데이터를 추가하겠다는 의미입니다.
    - studentNo, name 등은 테이블의 열(컬럼) 이름입니다.
    - VALUES (#{...}): 각각의 열에 들어갈 값을 지정합니다.
      - #{studentNo} 등은 메서드의 파라미터 값을 참조합니다.
      - 예를 들어, #{studentNo}는 메서드에서 제공된 student 객체의 studentNo 값을 의미합니다.
    */
	@Insert("""
			INSERT student(studentNo, name, departmentId, phone, sex, email)
			VALUES(#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
    /*
    @Options: 추가 옵션을 설정하는 어노테이션입니다.
    - useGeneratedKeys=true: 데이터베이스에서 자동 생성된 키 값을 사용하겠다는 의미입니다.
      - 예를 들어, 데이터베이스가 새로운 행을 추가할 때 자동으로 생성하는 ID 값을 가져옵니다.
    - keyProperty="id": 자동 생성된 ID 값을 Student 객체의 id 필드에 저장합니다.
    */
	void insert(Student student);
	 /*
    void insert(Student student): 메서드 선언부입니다.
    - insert: 메서드 이름입니다. 학생 정보를 데이터베이스에 추가하는 기능을 합니다.
    - Student student: 등록시 입력한 학생 정보를 담고 있는 객체입니다.
      - 이 객체에는 studentNo, name, departmentId, phone, sex, email 등의 정보가 포함됩니다.
   */


}












