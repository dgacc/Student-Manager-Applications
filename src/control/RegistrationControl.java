package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connect.ConnectDatabase;
import object.StudentClassObject;
import object.StudentObject;

public class RegistrationControl {
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	
	public RegistrationControl() {
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
	}
	
	public void add(StudentObject student, StudentClassObject stClass) {
		String add = " insert into dangki (idStudent, idClass, nameClass, status) value (" + student.getIdStudent() + "," + stClass.getIdClass() + ",'"
						+ stClass.getNameClass() + "','dang dki')";
		if(!check(student, stClass)) {
			connectDatabase.updateData(add);
		}
	}
	
	public void delete(StudentObject student, StudentClassObject stClass) {
		if(check(student, stClass)) {
			String delete = "delete from dangki where idStudent = " + student.getIdStudent() + " and idClass = " + stClass.getIdClass();
			connectDatabase.updateData(delete);
		}
	}
	
	public void delete(int idStudent, int idClass) {
		if(check(idStudent, idClass)) {
			String delete = "delete from dangki where idStudent = " + idStudent + " and idClass = " + idClass;
			connectDatabase.updateData(delete);
		}
	}
	
	public boolean check(StudentObject student, StudentClassObject stClass) {
		String check = "select idClass from dangki where idStudent = " + student.getIdStudent();
		ResultSet resultSet = connectDatabase.returnData(check);
		try {
			while(resultSet.next()) {
				int idClass = resultSet.getInt(1);
				if(idClass == stClass.getIdClass()) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean check(int idStudent,int idClass) {
		String check = "select idClass from dangki where idStudent = " + idStudent;
		ResultSet resultSet = connectDatabase.returnData(check);
		try {
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				if(id == idClass) return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteAll(StudentObject student) {
		String delAll = "delete from dangki where idStudent = " + student.getIdStudent();
		int ans = JOptionPane.showConfirmDialog(null, "Viec nay se so het du lieu dang ki cua Sv " + student.getName() + ".Ban co chac k?", "Question", JOptionPane.YES_NO_OPTION);
		if(ans == JOptionPane.YES_OPTION) {
			connectDatabase.updateData(delAll);
		}
	}
	
	public void updateStatus(StudentObject student, StudentClassObject stClass) {
		String update = "update dangki set status = 'thanh cong' where idStudent = " + student.getIdStudent() + " and idClass = " + stClass.getIdClass();
		connectDatabase.updateData(update);
		
		studentClassControl.insertStudent(stClass, student);
	}
	
	public void updateStatus(StudentObject student) {
		String s = "select idClass from dangki where idStudent = " + student.getIdStudent();
		ResultSet resultSet = connectDatabase.returnData(s);
		
		try {
			while(resultSet.next()) {
				int idClass = resultSet.getInt(1);
				StudentClassObject stClass = studentClassControl.loadClass(idClass);
				updateStatus(student, stClass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean checkDki(StudentObject student, StudentClassObject stClass) {
		if(check(student, stClass)) {
			String status = null, returnStatus = "select status from dangki where idStudent = " + student.getIdStudent() + " and idClass = " + stClass.getIdClass();
			ResultSet resultSet = connectDatabase.returnData(returnStatus);
			
			try {
				while(resultSet.next()) {
					status = resultSet.getString(1);
					if(status.toLowerCase().equals("thanh cong")) return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		else return false;
	}
	
	public boolean checkDki(int idStudent, int idClass) {
		if(check(idStudent, idClass)) {
			String status = null, returnStatus = "select status from dangki where idStudent = " + idStudent + " and idClass = " + idClass;
			ResultSet resultSet = connectDatabase.returnData(returnStatus);
			
			try {
				while(resultSet.next()) {
					status = resultSet.getString(1);
					if(status.toLowerCase().equals("thanh cong")) return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		else return false;
	}
	
	public boolean checkInfClass(StudentClassObject stClass, StudentObject student) {
		boolean[][] TimeTable = {{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true}, 
								{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true}};
		time(TimeTable, student);
		if(!checkTime(TimeTable, stClass)) {
			return false;
		}
		return true;
	}
	
	public boolean checkTime(boolean A[][], StudentClassObject newClass) {
		int day = Integer.parseInt(newClass.getDay().substring(4, 5));
		String time = newClass.getTime();
		int hoursBegin = Integer.parseInt(time.substring(0, 2)), minuteBegin = Integer.parseInt(time.substring(3, 5));
		int hoursEnd = Integer.parseInt(time.substring(6, 8)), minuteEnd = Integer.parseInt(time.substring(9, 11));
		
		if(hoursBegin == hoursEnd && A[day-2][hoursBegin-6] == false) return false;
		else if((minuteBegin <= 15 && minuteEnd >= 15) || minuteEnd >= 45) {
			for(int i = hoursBegin; i<= hoursEnd; i++) {
				if(A[day-2][i-6] == false) return false;
			}
		}
		else {
			for(int i = hoursBegin ; i< hoursEnd ; i++) {
				if(A[day-2][i-6] == false) return false;
			}
		}
		return true;
	}
	
	public boolean[][] time(boolean Time[][], StudentObject student) {
		
		String loadData = "select idClass from dangki where idStudent = " + student.getIdStudent();
		ResultSet resultSet = connectDatabase.returnData(loadData);
		try {
			while(resultSet.next()) {
				int idClass = resultSet.getInt(1);
				StudentClassObject stClass = studentClassControl.loadClass(idClass);
				int day = Integer.parseInt(stClass.getDay().substring(4, 5));
				String time = stClass.getTime();
				int hoursBegin = Integer.parseInt(time.substring(0, 2)), minuteBegin = Integer.parseInt(time.substring(3, 5));
				int hoursEnd = Integer.parseInt(time.substring(6, 8)), minuteEnd = Integer.parseInt(time.substring(9, 11));
				if(hoursBegin == hoursEnd) {
					Time[day-2][hoursBegin-6] = false;
				}
				else if((minuteBegin <= 15 && minuteEnd >= 15) || minuteEnd >= 45) {
					for(int i = hoursBegin; i<= hoursEnd; i++) {
						Time[day-2][i-6] = false;
					}
				}
				else {
					for(int i = hoursBegin ; i< hoursEnd ; i++) {
						Time[day-2][i-6] = false;
					}
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Time;
	}
	
}
