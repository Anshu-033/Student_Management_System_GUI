package studentManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	
	//INSERT STUDENT DETAILS --> ROLL NUMBER  ||   NAME   ||   MARKS
	public void addStudent(Student s) {
	
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
			);
			
			String query = "insert into studentdetails values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,s.roll_no);
			ps.setString(2,s.name);
			ps.setInt(3,s.marks);
			
			int rows = ps.executeUpdate();
			if(rows > 0) {
				System.out.println("Details of the student inserted");
			}
			else {
				System.out.println("Could not insert");
			}
			
			ps.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//VIEW THE DETAILS OF THE STUDENTS --> ROLL NUMBER  ||   NAME   ||   MARKS
	public void getAllStudents() {
		try {
			
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
			);
			String query = "select * from studentdetails";
			Statement s1 = con.createStatement();
			ResultSet rs = s1.executeQuery(query);
			
			while(rs.next()) {
				System.out.println(
						"Roll No :- " + rs.getInt(1) +
						", Name :- " + rs.getString(2) +
						", Marks :- " + rs.getInt(3)
						);
				System.out.println();
			}
			
			rs.close();
			s1.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public List<Student> getStudents() {

	    List<Student> list = new ArrayList<>();
	    
	    try {
	    	
	    		Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
			);
	        String query = "SELECT * FROM studentdetails";
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);

	        while(rs.next()) {
	            Student s = new Student();
	            s.roll_no = rs.getInt("roll_no");
	            s.name = rs.getString("name");
	            s.marks = rs.getInt("marks");

	            list.add(s);
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    return list;   
	}
	
	
	//GET STUDENT BY THEIR ROLL NUMBER
	public Student getStudent(int roll_no) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
			);
			String query = "select * from studentdetails where roll_no = ? ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,roll_no);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Student st = new Student();
				st.name = rs.getString(2);
				st.roll_no = rs.getInt(1);
				st.marks = rs.getInt(3);
				
				rs.close();
				ps.close();
				con.close();
				
				return st;
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//DELETE STUDENT BY THEIR ROLL NUMBER 
	public void deleteStudent(int roll_no) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
				);
			
			String query = "delete from studentdetails where roll_no = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,roll_no);
			 
			int rows = ps.executeUpdate();
			if(rows > 0) {
				System.out.println("Details of the student deleted.");
			}
			else {
				System.out.println("No student found with the given roll number");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//UPDATE THE MARKS OF A STUDENT BY THEIR ROLL NUMBER 
	public void updateStudent(int roll_no,int marks) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/student",
					"root",
					"anshu"
				);
			
			String query = "update studentdetails set marks = ? where roll_no = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,marks);
			ps.setInt(2, roll_no);
			 
			int rows = ps.executeUpdate();
			if(rows > 0) {
				System.out.println("Student updated successfully");
			}
			else {
				System.out.println("No student found with the given roll number");
			}
			
			ps.close();
	        con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}











