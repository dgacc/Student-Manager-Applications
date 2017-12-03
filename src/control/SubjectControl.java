package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import Connect.ConnectDatabase;
import object.*;

public class SubjectControl {
	ConnectDatabase connectDatabase;
	
	public SubjectControl() {
		connectDatabase = new ConnectDatabase();
	}
	public void addSubject(SubjectObject subject) {
		String insertSubject = "Insert into subject (idSubject, name, specialized, count, factor, note) values ("
				+ subject.getIdSubject() + ",'" + subject.getName() + "','" + subject.getSpecialized() + "'," +subject.getCount() + ","
				+ subject.getFactor() + ",'" + subject.getNote() + "')";
		if(!checkIdSubject(subject.getIdSubject())) {
			connectDatabase.updateData(insertSubject);
		}
	}
	
	public void deleteSubject(SubjectObject subject) {
		String deleteSubject = "delete from subject where idSubject = " +subject.getIdSubject();
		
		if(checkIdSubject(subject.getIdSubject())) {
			connectDatabase.updateData(deleteSubject);
		}
	}
	
	public void deleteSubject(int id) {
		String deleteSubject = "delete from subject where idSubject = " + id;
		
		if(checkIdSubject(id)) {
			connectDatabase.updateData(deleteSubject);
		}
	}
	public boolean checkIdSubject(int id) {
		String s = "select idSubject from subject";
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
	
	public SubjectObject loadSubject(int id) {
		SubjectObject subject = new SubjectObject();
		String s = "select * from subject where idSubject = " + id;
		ResultSet resultSet = connectDatabase.returnData(s);
		
		try {
			while(resultSet.next()) {
				subject.setIdSubject(resultSet.getInt(1));
				subject.setName(resultSet.getString(2));
				subject.setSpecialized(resultSet.getString(3));
				subject.setCount(resultSet.getInt(4));
				subject.setFactor(resultSet.getString(5));
				subject.setNote(resultSet.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subject;
	}
	
	
}
