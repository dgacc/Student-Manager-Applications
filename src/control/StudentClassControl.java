package control;

import java.sql.*;
import Connect.ConnectDatabase;
import display.Student;
import object.*;

public class StudentClassControl {
	ConnectDatabase connectDatabase;
	
	public StudentClassControl() {
		// TODO Auto-generated constructor stub
		connectDatabase = new ConnectDatabase();
	}
	
	public void addClass(StudentClassObject stClass) {
		String insertClass = "insert into studentclass(idClass, educate, idSubject, nameClass, nameTeacher, day, address, time, number, note) value ("
							+ stClass.getIdClass() + ",'" + stClass.getEducate() + "'," +stClass.getIdSubject() + ",'" + stClass.getNameClass() + "','" 
							+ stClass.getNameTeacher() + "','" + stClass.getDay() + "','" + stClass.getAddress() + "','" + stClass.getTime() + "'," + stClass.getNumber() + ",'" + stClass.getNote() + "')";
		if(!checkClass(stClass)) {
			connectDatabase.updateData(insertClass);
		}
	}
	
	public void deleteClass(StudentClassObject stClass){
		if(checkClass(stClass)) {
			String deleteClass = "delete from studentclass where idClass = " + stClass.getIdClass();
			connectDatabase.updateData(deleteClass);
		}
	}
	
	public void deleteClass(int idClass){
		if(checkClass(idClass)) {
			String deleteClass = "delete from studentclass where idClass = " + idClass;
			connectDatabase.updateData(deleteClass);
		}
	}
	
	public StudentClassObject loadClass(int id) {
		StudentClassObject stClass = new StudentClassObject();
		String loadClass = "select * from studentclass where idClass = " + id;
		ResultSet resultSet = connectDatabase.returnData(loadClass);
		
		try {
			while(resultSet.next()) {
				stClass.setIdClass(resultSet.getInt(1));
				stClass.setEducate(resultSet.getString(2));
				stClass.setIdSubject(resultSet.getInt(3));
				stClass.setNameClass(resultSet.getString(4));
				stClass.setNameTeacher(resultSet.getString(5));
				stClass.setDay(resultSet.getString(6));
				stClass.setAddress(resultSet.getString(7));
				stClass.setTime(resultSet.getString(8));
				stClass.setNumber(resultSet.getInt(9));
				stClass.setNote(resultSet.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stClass;
	}
	
	public boolean checkClass(StudentClassObject stClass) {
		String s = "select idClass from studentclass";
		ResultSet resultSet = connectDatabase.returnData(s);
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				if(stClass.getIdClass() == id) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkClass(int id) {
		String s = "select idClass from studentclass";
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				if(rs.getInt(1) == id) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkClass(int id, String TC) {
		String s = "select idClass from studentclass where educate = '" + TC + "'";
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				if(rs.getInt(1) == id) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkStudentFromClass(StudentClassObject stClass, StudentObject student) {
		String s = "select idStudent from studentlist where idClass = " + stClass.getIdClass();
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				if(rs.getInt(1) == student.getIdStudent()) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkStudentFromClass(int idClass, int id) {
		String s = "select idStudent from studentlist where idClass = " + idClass;
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				if(rs.getInt(1) == id) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void insertStudent(StudentClassObject stClass, StudentObject student) {
		String insertStudent = "insert into studentlist (idClass, idStudent, name, diemgiuaki, diemcuoiki, diemtong) value (" + stClass.getIdClass() + ","
								+ student.getIdStudent() + ",'" + student.getName() + "', null, null, null)";
		if(!checkStudentFromClass(stClass, student)) {
			connectDatabase.updateData(insertStudent);
		}
	}
	
	public void delteteStudentFromClass(StudentClassObject stClass, StudentObject student) {
		String deleteStudent = "delete from studentlist where idStudent = " + student.getIdStudent() + " and idClass = " + stClass.getIdClass();
		if(checkStudentFromClass(stClass, student)) {
			connectDatabase.updateData(deleteStudent);
		}
	}
	
	public void delteteStudentFromClass(int idClass, int id) {
		String deleteStudent = "delete from studentlist where idStudent = " + id + " and idClass = " + idClass;
		if(checkStudentFromClass(idClass, id)) {
			connectDatabase.updateData(deleteStudent);
		}
	}
	
	public void infClass(StudentClassObject stClass) {
		System.out.println("********************************");
		System.out.println("IdCLass:" + stClass.getIdClass());
		System.out.println("Educate:" + stClass.getEducate());
		System.out.println("IdSubject:" + stClass.getIdSubject());
		System.out.println("NameClass:" + stClass.getNameClass());
		System.out.println("NameTeacher:" + stClass.getNameTeacher());
		System.out.println("day:" + stClass.getDay());
		System.out.println("Address:" + stClass.getAddress());
		System.out.println("Time:" + stClass.getTime());
		System.out.println("Number:" + stClass.getNumber());
		System.out.println("Note:" + stClass.getNote());
	}
}
