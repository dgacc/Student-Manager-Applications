package display;

import javax.swing.*;
import java.awt.*;
import control.*;
import object.*;

public class Information extends JPanel{
	private JLabel jlTitle, jlId, jlName, jlSex, jlBirthday, jlAddress, jlPhone, jlEmail, jlClass, jlSpecialized, jlCourse, jlEducate, jlUser, jlPass;
	AccountControl accountControl;
	StudentControl studentControl;
	
	public Information() {
		jlTitle = new JLabel("Information");
		jlId = new JLabel("ID:");
		jlName = new JLabel("Name:");
		jlSex = new JLabel("Sex:");
		jlBirthday = new JLabel("Birthday:");
		jlAddress = new JLabel("Address:");
		jlPhone = new JLabel("Phone:");
		jlEmail = new JLabel("Email:");
		jlClass = new JLabel("Class:");
		jlSpecialized = new JLabel("Specialized:");
		jlCourse = new JLabel("Course:");
		jlEducate = new JLabel("Educate:");
		jlUser = new JLabel("UserName:");
		jlPass = new JLabel("PassWord:");
		
		accountControl = new AccountControl();
		studentControl = new StudentControl();
		
	}

	public void runInformation(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		int dx = x/6, dy = y/15, k = y/15, weight = x/3, height = y/16, fontsize = 17;
		
		add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/35));
		jlTitle.setBounds(x/3, 0, x/3, height);
		
		add(jlId);
		jlId.setFont(new Font("Arial", 1, fontsize));
		jlId.setIcon(new ImageIcon("icon/add/id.png"));
		jlId.setBounds(dx, dy, weight, height);
		
		add(jlName);
		jlName.setFont(new Font("Arial", 1, fontsize));
		jlName.setIcon(new ImageIcon("icon/add/name.png"));
		jlName.setBounds(dx, dy + k, weight, height);
		
		add(jlUser);
		jlUser.setFont(new Font("Arial", 1, fontsize));
		jlUser.setIcon(new ImageIcon("icon/add/user.png"));
		jlUser.setBounds(dx, dy + 2*k, weight, height);
		
		add(jlPass);
		jlPass.setFont(new Font("Arial", 1, fontsize));
		jlPass.setIcon(new ImageIcon("icon/add/pass.png"));
		jlPass.setBounds(dx, dy + 3*k, weight, height);
		
		add(jlCourse);
		jlCourse.setFont(new Font("Arial", 1, fontsize));
		jlCourse.setIcon(new ImageIcon("icon/add/course.png"));
		jlCourse.setBounds(dx, dy + 4*k, weight, height);
		
		add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1, fontsize));
		jlSpecialized.setIcon(new ImageIcon("icon/add/nganhhoc.png"));
		jlSpecialized.setBounds(dx, dy + 5*k, weight, height);
		
		add(jlClass);
		jlClass.setFont(new Font("Arial", 1, fontsize));
		jlClass.setIcon(new ImageIcon("icon/add/class.png"));
		jlClass.setBounds(dx, dy + 6*k, weight, height);
		
		add(jlEducate);
		jlEducate.setFont(new Font("Arial", 1, fontsize));
		jlEducate.setIcon(new ImageIcon("icon/add/hedaotao.png"));
		jlEducate.setBounds(dx, dy + 7*k, weight, height);
		
		add(jlSex);
		jlSex.setFont(new Font("Arial", 1, fontsize));
		jlSex.setIcon(new ImageIcon("icon/add/gt.png"));
		jlSex.setBounds(dx, dy + 8*k, weight, height);
		
		add(jlBirthday);
		jlBirthday.setFont(new Font("Arial", 1, fontsize));
		jlBirthday.setIcon(new ImageIcon("icon/add/date.png"));
		jlBirthday.setBounds(dx, dy + 9*k, weight, height);
		
		add(jlAddress);
		jlAddress.setFont(new Font("Arial", 1, fontsize));
		jlAddress.setIcon(new ImageIcon("icon/add/address.png"));
		jlAddress.setBounds(dx, dy + 10*k, weight, height);
		
		add(jlEmail);
		jlEmail.setFont(new Font("Arial", 1, fontsize));
		jlEmail.setIcon(new ImageIcon("icon/add/mail.png"));
		jlEmail.setBounds(dx, dy + 11*k, weight, height);
		
		add(jlPhone);
		jlPhone.setFont(new Font("Arial", 1, fontsize));
		jlPhone.setIcon(new ImageIcon("icon/add/phone.png"));
		jlPhone.setBounds(dx, dy + 12*k, weight, height);
	}
	
	public void insertInfAccount(AccountObject account) {
		System.out.println("*************************************");
		accountControl.inTTin(account);
		StudentObject student = studentControl.loadStudent(account.getIdAccount());
		jlTitle.setText("Information of " + account.getIdAccount());
		jlId.setText("ID: " + account.getIdAccount());
		jlName.setText("Name: " + account.getName());
		jlUser.setText("UserName: " + account.getUsername());
		jlPass.setText("PassWord: " + account.getPassword());
		jlCourse.setText("Course: " + student.getCourse());
		jlSpecialized.setText("Specialized: " + student.getSpecialized());
		jlClass.setText("Class: " + student.getClassSt());
		jlEducate.setText("Educate: " + student.getEducate());
		jlSex.setText("Sex: " + student.getGioitinh());
		jlBirthday.setText("Birthday: " + student.getBirthday());
		jlAddress.setText("Address: " + student.getAddress());
		jlEmail.setText("Email: " + student.getEmail());
		jlPhone.setText("Phone: " + student.getPhone());
	}
}
