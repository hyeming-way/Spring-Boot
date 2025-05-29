package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Student;
import com.example.demo.model.Pagination;

@Mapper
public interface StudentMapper {

	
    @Select("""
        SELECT s.*, d.name departmentName
        FROM student s JOIN department d ON s.departmentId = d.id
        ORDER BY id
        LIMIT #{firstRecordIndex}, #{sz} """)
    List<Student> findAll(Pagination pagination);
/*findAll 메소드 :
  pagination 객체의 pg 속성값과 sz 속성값을 사용해서,
  pg 페이지의 레코드만 조회해서 리턴한다.
  --------------------------------------------------
  pagination 객체의 pg 속성값과 sz 속성값을 사용해서,
     pg 페이지의 레코드만 조회해서 리턴한다.

     #{firstRecordIndex} 인덱스 번째 레코드 부터, #{sz} 개의 레코드만 조회한다.
     #{firstRecordIndex} 부분에 채워지는 값은, pagination.getFirstRecordIndex() 메소드의 리턴값이고,
     #{sz} 부분에 채워지는 값은, pagination.getSz() 메소드의 리턴값이다.

     pagination.getFirstRecordIndex() 메소드는 pagination.pg 번째 페이지의 첫 레코드의 인덱스를
     계산해서 리턴한다.
 */
    
    
    @Select("SELECT count(*) FROM student")
    int getCount();
/*getCount 메소드 : student 테이블의 전체 레코드 수를 리턴한다.*/
    
    
    
    @Select("SELECT * FROM student WHERE id=#{id}")
    Student findById(int id);

 
    
    
    @Update("""
       UPDATE student
       SET
         studentNo = #{studentNo},
         name = #{name},
         departmentId = #{departmentId},
         sex = #{sex},
         phone = #{phone},
         email = #{email}
       WHERE id = #{id} """)
    void update(Student student);

    @Insert("""
       INSERT student (studentNo, name, departmentId, phone, sex, email)
       VALUES (#{studentNo}, #{name}, #{departmentId}, #{phone}, #{sex}, #{email}) """)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Student student);

    @Delete("DELETE FROM student WHERE id=#{id}")
    void deleteById(int id);
}