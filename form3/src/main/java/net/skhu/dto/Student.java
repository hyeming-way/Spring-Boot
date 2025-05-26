package net.skhu.dto;

import lombok.Data;

@Data
public class Student {

    String studentNo;
    String name;
    int departmentId;
    String gender;
    boolean absense;
    int year;

    /*
     * @Data 어노테이션에 의해 자동으로 생성되는 코드 (예시):
     *
     * // Getters
     * public String getStudentNo() { return this.studentNo; }
     * public String getName() { return this.name; }
     * public int getDepartmentId() { return this.departmentId; }
     * public String getGender() { return this.gender; }
     * public boolean isAbsense() { return this.absense; } // 또는 getAbsense()
     * public int getYear() { return this.year; }
     *
     * // Setters
     * public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
     * public void setName(String name) { this.name = name; }
     * public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
     * public void setGender(String gender) { this.gender = gender; }
     * public void setAbsense(boolean absense) { this.absense = absense; }
     * public void setYear(int year) { this.year = year; }
     *
     * // toString()
     * @Override
     * public String toString() {
     * return "Student(studentNo=" + this.getStudentNo() + ", name=" + this.getName() +
     * ", departmentId=" + this.getDepartmentId() + ", gender=" + this.getGender() +
     * ", absense=" + this.isAbsense() + ", year=" + this.getYear() + ")";
     * }
     *
     * // equals() and hashCode()
     * // (필드를 기반으로 생성됨)
     *
     * // 기본 생성자 (명시적으로 다른 생성자가 없을 경우)
     * // public Student() {}
     *
     * // @RequiredArgsConstructor (final 또는 @NonNull 필드가 있을 경우 해당 필드만 받는 생성자)
     * // 이 클래스에는 해당 필드가 없으므로, 기본 생성자와 유사하게 동작합니다.
     */


}
