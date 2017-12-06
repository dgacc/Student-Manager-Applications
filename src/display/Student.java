package display;

import javax.swing.*;
import java.awt.*;
import object.*;
import control.*;

public class Student extends JPanel{
	private JTabbedPane jtpMain;
	Information information;
	Registration registration;
	TimeTable timeTable;
	Transcript transcript;
	
	AccountObject account;
	StudentObject student;
	StudentControl studentControl;

	public Student() {
		jtpMain = new JTabbedPane();
		information = new Information();
		registration = new Registration();
		timeTable = new TimeTable();
		transcript = new Transcript();
		
		account = new AccountObject();
		studentControl = new StudentControl();
	}

	public void runStudent(int x, int y, MainFrame mainFrame) {
		setSize(x, y);
		setLayout(null);
		
		add(jtpMain);
 		jtpMain.setBounds(0, 0, x, y-30);
		jtpMain.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
		
		jtpMain.addTab("information", information);
		information.runInformation(jtpMain.getWidth(), jtpMain.getHeight());
		
		jtpMain.addTab("transcrips", transcript);
		transcript.runTranscript(jtpMain.getWidth(), jtpMain.getHeight());
		
		jtpMain.addTab("timetable", timeTable);
		timeTable.runTimeTable(jtpMain.getWidth(), jtpMain.getHeight());
		
	}
	
	public StudentObject getStudent() {
		return student;
	}

	public void setStudent(StudentObject student) {
		this.student = student;
	}
	
	public AccountObject getAccount() {
		return account;
	}

	public void setAccount(AccountObject account) {
		student = studentControl.loadStudent(account.getIdAccount());
		this.account = account;
	}
	public JTabbedPane getJtpMain() {
		return jtpMain;
	}
}
