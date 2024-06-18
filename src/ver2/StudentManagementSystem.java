package ver2;

import java.sql.SQLException;
import java.util.List;

import ver2.model.StudentDTO;

public class StudentManagementSystem {
	
	private static final StudentDAO studentDAO = new StudentDAO();
	
	public static void main(String[] args) {
		
		// 사용자에게 보여주는 부분 꾸며 줘도 된다.
		
		try {
			List<StudentDTO> list =  studentDAO.getAllStudents();
			System.out.println(list.size());
			System.out.println(list.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	} // end of main

} // end of class
