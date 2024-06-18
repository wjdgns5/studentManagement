package ver2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ver2.model.StudentDTO;

// 물론 기능 설계는 인터페이스를 먼저 작성하고 구현 클래스를 만드는 것이 좋다.
public class StudentDAO {
	
	// 학생 정보 추가 가능 만들기
	public void addStudent(StudentDTO dto) throws SQLException {
		String query = " INSERT INTO students (name, age, email) values(?, ?, ?) ";
		try(
				Connection conn = DBConnectionManager.getInstance().getConnection()
				) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getEmail());
			pstmt.executeUpdate();
		} 
	}
	
	// 학생의 아이디로 조회하는 기능 만들기 (id 또는 이름으로 많이 한다.)
	public StudentDTO getStudentbyId(int id) throws SQLException {
		
		String query = " SELECT * FROM students WHERE id = ? ";
		
		try(
				Connection conn = DBConnectionManager.getInstance().getConnection()
				) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new StudentDTO(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"));
				}
				
			}
			
		} 
		
		// todo 수정하기
		return null;
	}
	
	// 학생 전체 조회 기능
	public List<StudentDTO> getAllStudents() throws SQLException{
		// tip - 리스트라면 무조건 리스트를 생성하고 코드 작성하기
		List<StudentDTO> list = new ArrayList<>();
		String query = " SELECT * FROM students ";
		
		try(
				Connection conn = DBConnectionManager.getInstance().getConnection()
				) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 메서드 체이닝 기법
			 	StudentDTO dto = new StudentDTO().builder()
				.id(rs.getInt("id"))
				.name(rs.getString("name"))
				.age(rs.getInt("age"))
				.email(rs.getString("email"))
				.build();
			 list.add(dto);
			}
		} 
		// todo 수정하기
		return list;
	}
	
	// 학생 정보 수정하기
	public void updateStudent(String name, StudentDTO dto) throws SQLException {
		String query = " UPDATE SET name = ?, age = ?, email = ? WHERE name = ? ";
		try(
				Connection conn = DBConnectionManager.getInstance().getConnection();
				) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getAge());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, name); // 조건값 셋팅
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	// 학생 정보 삭제하기
	public void deleteStudent(int id) throws SQLException {
		String query = " DELETE FROM students WHERE id = ? ";
		try(
				Connection conn = DBConnectionManager.getInstance().getConnection();
				) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
} // end of class
