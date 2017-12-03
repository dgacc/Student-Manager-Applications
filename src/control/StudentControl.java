package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import Connect.ConnectDatabase;
import display.StudentManage;
import object.*;

public class StudentControl {
	ConnectDatabase connectDatabase;
	public StudentControl() {
		connectDatabase = new ConnectDatabase();
	}
	
	public void addStudent(StudentObject student) {
		String insertStudent = "Insert into student (idStudent, name, course, specialized, class, educate, sex, birthday, address, email, phone) values ("
				+ student.getIdStudent() + ",'" + student.getName() + "','" + student.getCourse() +"','" + student.getSpecialized() + "','" +student.getClassSt() + "',"
				+ student.getEducate() + ",'" + student.getGioitinh() + "','" + student.getBirthday() + "','" + student.getAddress() + "','" 
				+ student.getEmail() + "','" + student.getPhone() + "')";
		if(!checkIdStudent(student.getIdStudent())) {
			connectDatabase.updateData(insertStudent);
		}
	}
	
	public void changeInfStudent(StudentObject student) {
		deleteStudent(student.getIdStudent());
		addStudent(student);
		System.out.println("update thanh cong");
	}
	
	public void deleteStudent(StudentObject student) {
		if(checkIdStudent(student.getIdStudent())) {
			String deleteStudent = "delete from student where idStudent =" + student.getIdStudent();
			connectDatabase.updateData(deleteStudent);
		}
	}
	
	public void deleteStudent(int id) {
		if(checkIdStudent(id)) {
			String deleteStudent = "delete from student where idStudent =" + id;
			connectDatabase.updateData(deleteStudent);
		}
	}
	
	public StudentObject loadStudent(int idStudent) {
		StudentObject student  = new StudentObject();
		String s = "select * from student where idStudent = " +idStudent;
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				student.setIdStudent(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setCourse(rs.getString(3));
				student.setSpecialized(rs.getString(4));
				student.setClassSt(rs.getString(5));
				student.setEducate(rs.getInt(6));
				student.setGioitinh(rs.getString(7));
				student.setBirthday(rs.getString(8));
				student.setAddress(rs.getString(9));
				student.setEmail(rs.getString(10));
				student.setPhone(rs.getString(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
	public boolean checkIdStudent(int id) {
		String s = "select idStudent from student";
		ResultSet resultSet = connectDatabase.returnData(s);
		try {
			while(resultSet.next()) {
				if(resultSet.getInt(1) == id) return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void inTTin(StudentObject student) {
		System.out.println("Thong tin student:");
		System.out.println("ID:" + student.getIdStudent());
		System.out.println("Name:" + student.getName());
		System.out.println("Class:" + student.getClassSt());
		System.out.println("Date:" + student.getBirthday());
		System.out.println("Address:" + student.getAddress());
		System.out.println("Email:" + student.getEmail());
		System.out.println("Phone:" + student.getPhone());
	}
	
}
