package ver1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class student {

	private static final Logger LOGGER = Logger.getLogger(student.class.getName());

	public static void main(String[] args) {

		try {

			Connection conn = DBConnectionManager.getConnection();

			Scanner scanner = new Scanner(System.in);

			while (true) {

				System.out.println("1. 학생 정보 조회");
				System.out.println("2. 학생 정보 추가");
				System.out.println("3. 학생 정보 수정");
				System.out.println("4. 학생 정보 삭제");
				System.out.println("5. 종료");
				System.out.println("-----------------");
				int choice = scanner.nextInt();

				if (choice == 1) {
					System.out.println("학생 정보 조회 기능을 선택했습니다.");
					// 조회 기능
					studentSelect(conn);
					
				} else if (choice == 2) {
					System.out.println("학생 정보 추가 기능을 선택했습니다.");
					// 정보 추가
					studentAdd(conn, scanner);
					
				} else if (choice == 3) {
					System.out.println("학생 정보 수정 기능을 선택했습니다.");
					// 정보 수정
					studentUpdate(conn, scanner);
					
				} else if (choice == 4) {
					System.out.println("학생 정보 삭제 기능을 선택했습니다.");
					// 정보 삭제
					studentDelete(conn, scanner);
					
				} else if (choice == 5) {
					System.out.println("프로그램이 종료되었습니다.");
					break;
					
				} else {
					System.out.println("잘못 입력되었습니다.");
				}

			} // end of while

		} catch (Exception e) {
			// TODO: handle exception
		} // try- catch 

	} // end of main

	private static void studentSelect(Connection conn) {

		String sql = " select * from students ";

		try (
				PreparedStatement ptsmt = conn.prepareStatement(sql); ResultSet resultSet = ptsmt.executeQuery();
				) {

			while (resultSet.next()) {
				System.out.println("문제 ID : " + resultSet.getInt("id"));
				System.out.println("이름 : " + resultSet.getString("name"));
				System.out.println("나이 : " + resultSet.getInt("age"));
				System.out.println("이메일 : " + resultSet.getString("email"));

				if (!resultSet.isLast()) {
					System.out.println("-----------------------------------");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	} // end of studentSelect();

	private static void studentUpdate(Connection conn, Scanner scanner) {

		System.out.println("이름을 입력해 주세요.");
		scanner.nextLine();
		String name = scanner.nextLine();

		System.out.println("나이를 입력해 주세요.");
		String age = scanner.nextLine();

		System.out.println("수정할 이름를 입력해 주세요.");
		String name1 = scanner.nextLine();

		System.out.println("수정할 이름를 입력해 주세요.");
		String age1 = scanner.nextLine();

		String sql = "Update students set name = ?, age = ? where name = ? && age = ? ";

		try (
				PreparedStatement ptsmt = conn.prepareStatement(sql);
				) {
			ptsmt.setString(1, name1);
			ptsmt.setString(2, age1);
			ptsmt.setString(3, name);
			ptsmt.setString(4, age);
			ptsmt.executeUpdate();

			System.out.println("학생 정보 삭제 성공");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of studentUpdate()

	private static void studentAdd(Connection conn, Scanner scanner) {

		System.out.println("이름을 입력해 주세요.");
		scanner.nextLine();
		String name = scanner.nextLine();

		System.out.println("나이를 입력해 주세요.");
		String age = scanner.nextLine();

		System.out.println("주소를 입력해 주세요.");
		String address = scanner.nextLine();

		String sql = " Insert into students(name, age, email) values(?, ?, ?); ";

		try (PreparedStatement ptsmt = conn.prepareStatement(sql);) {
			ptsmt.setString(1, name);
			ptsmt.setString(2, age);
			ptsmt.setString(3, address);
			ptsmt.executeUpdate();

			System.out.println("학생 정보 추가 성공");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of studentAdd()

	private static void studentDelete(Connection conn, Scanner scanner) {

		System.out.println("이름을 입력하세요.");
		scanner.nextLine();
		String name = scanner.nextLine();

		System.out.println("나이를 입력해 주세요.");
		String age = scanner.nextLine();

		String sql_check = " select * from students where name = ? and age = ? ";

		try {
			PreparedStatement ptsmt = conn.prepareStatement(sql_check);

			ptsmt.setString(1, name);
			ptsmt.setString(2, age);
			ptsmt.executeQuery();

		} catch (Exception e) {
		}

		String sql = " delete from students where name = ? and age = ? ";

		try (PreparedStatement ptsmt = conn.prepareStatement(sql);) {
			ptsmt.setString(1, name);
			ptsmt.setString(2, age);
			ptsmt.executeUpdate();

			System.out.println("학생 정보 삭제 성공");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of student Delete

} // end of class
