package display;

import javax.swing.*;
import java.awt.*;
import control.*;
import object.*;

public class Information extends JPanel{
	private JPanel jpInfHeader,jpMainInf, jpLeft, jpRight,  jpRightHeader, jpLeftHeader ; 
	private JLabel jlTitle, jlId, jlName, jlName1,jlSex, jlBirthday, jlAddress,
	jlPhone, jlEmail, jlClass,jlClass1, jlSpecialized,jlSpecialized1, jlCourse, jlEducate, jlUser,
	jlPass, jlLeftHeader, jlRightHeader, jlAvatar;
	JLayeredPane layeredPane = new JLayeredPane();
	
	AccountControl accountControl;
	StudentControl studentControl;
	
	public Information() {
		
		jlTitle = new JLabel("Information");
		jlId = new JLabel("ID:");
		jlName = new JLabel("Name:");
		jlName1 = new JLabel("Name:");
		jlSex = new JLabel("Sex:");
		jlBirthday = new JLabel("Birthday:");
		jlAddress = new JLabel("Address:");
		jlPhone = new JLabel("Phone:");
		jlEmail = new JLabel("Email:");
		jlClass = new JLabel("Class:");
		jlClass1 = new JLabel("Class:");
		jlSpecialized = new JLabel();
		jlSpecialized1 = new JLabel("Specialized:");
		
		jlCourse = new JLabel("Course:");
		jlEducate = new JLabel("Educate:");
		jlUser = new JLabel("UserName:");
		jlPass = new JLabel("PassWord:");
		jlLeftHeader = new JLabel("Thông tin cá nhân");
		jlRightHeader = new JLabel("Thông tin học tập");
		jlAvatar = new JLabel();
		jlAvatar.setIcon(new ImageIcon("icon/add/men.png"));
		
		jpInfHeader = new JPanel();
		jpMainInf  = new JPanel(); 
		jpLeft = new JPanel();
		jpRight  = new JPanel(); 
		jpLeftHeader = new JPanel();
		jpRightHeader  = new JPanel(); 
		
		
		accountControl = new AccountControl();
		studentControl = new StudentControl();
		
	}

	public void runInformation(int x, int y) {
		setSize(x, y);
		setLayout(null);
		int dx = x/6, dy = y/15, k = y/15, weight = x/3, height = y/16, fontsize = 17;
		
		
		
		
		
		
		add(jpInfHeader);
		jpInfHeader.setLayout(null);
		jpInfHeader.setSize(x, y/4);
		jpInfHeader.setBackground(new Color(0, 170, 207));
		
		
		jpInfHeader.add(jpMainInf);
		jpMainInf.setLayout(null);
		jpMainInf.setSize(2*x/3, y/4 - 20);
		jpMainInf.setBackground(Color.WHITE);
		jpMainInf.setBounds(x/2 - x/3, 10, 2*x/3, y/4 - 20);
		
		jpMainInf.add(jlAvatar);
		jlAvatar.setBounds(50, 15, 120, 120);
	
		jpMainInf.add(jlName1);
		jlName1.setFont(new Font("Arial", 1, 30));
//		jlName1.setIcon(new ImageIcon("icon/add/name.png"));
		jlName1.setBounds(dx, 20, weight, height);

		jpMainInf.add(jlSpecialized1);
		jlSpecialized1.setFont(new Font("Arial", 1, 11));
//		jlSpecialized.setIcon(new ImageIcon("icon/add/nganhhoc.png"));
		jlSpecialized1.setBounds(dx, 50, x, height);
		jlSpecialized1.setForeground(new Color(132, 125, 125));

		
		jpMainInf.add(jlClass1);
		jlClass1.setFont(new Font("Arial", 1, 11));
//		jlClass.setIcon(new ImageIcon("icon/add/course.png"));
		jlClass1.setBounds(dx, 70, x, height);
		jlClass1.setForeground(new Color(132, 125, 125));
		
		add(jpLeftHeader);
		jpLeftHeader.setLayout(null);
		jpLeftHeader.setBackground( new Color(0, 170, 207));
		jpLeftHeader.setBounds(x/2 - x/3 + 20, y/4 + 25, 200, 50);
		
		jpLeftHeader.add(jlLeftHeader);
		jlLeftHeader.setForeground(Color.WHITE);
		jlLeftHeader.setFont(new Font("Arial", 1, 20));
		jlLeftHeader.setBounds(20, 10, 200, 25);
	
	
		add(jpLeft);
		jpLeft.setLayout(null);
	    jpLeft.setBackground(Color.WHITE);
		jpLeft.setBounds(x/2 - x/3, y/4 + 50, x/3 - 10, 3*y/4 - 50);
		
		
//		jpLeft.add(jlEducate);
//		jlEducate.setFont(new Font("Arial", 1, fontsize));
//		jlEducate.setIcon(new ImageIcon("icon/add/hedaotao.png"));
//		jlEducate.setBounds(dx, dy + 7*k, weight, height);
		jpLeft.add(jlName);
		jlName.setFont(new Font("Arial", 1, fontsize));
		jlName.setIcon(new ImageIcon("icon/add/name.png"));
		jlName.setBounds(30, 50, weight, height);
		
		
		jpLeft.add(jlSex);
		jlSex.setFont(new Font("Arial", 1, fontsize));
		jlSex.setIcon(new ImageIcon("icon/add/gt.png"));
		jlSex.setBounds(30, 100, weight, height);
		
		jpLeft.add(jlBirthday);
		jlBirthday.setFont(new Font("Arial", 1, fontsize));
		jlBirthday.setIcon(new ImageIcon("icon/add/date.png"));
		jlBirthday.setBounds(30,150, weight, height);
		
		jpLeft.add(jlAddress);
		jlAddress.setFont(new Font("Arial", 1, fontsize));
		jlAddress.setIcon(new ImageIcon("icon/add/address.png"));
		jlAddress.setBounds(30, 200, weight, height);
		
		jpLeft.add(jlEmail);
		jlEmail.setFont(new Font("Arial", 1, fontsize));
		jlEmail.setIcon(new ImageIcon("icon/add/mail.png"));
		jlEmail.setBounds(30, 250, weight, height);
		
		jpLeft.add(jlPhone);
		jlPhone.setFont(new Font("Arial", 1, fontsize));
		jlPhone.setIcon(new ImageIcon("icon/add/phone.png"));
		jlPhone.setBounds(30, 300, weight, height);
		
		
		
		add(jpRightHeader);
		jpRightHeader.setLayout(null);
		jpRightHeader.setBackground( new Color(0, 170, 207));
		jpRightHeader.setBounds(x/2 + 30, y/4 + 25, 200, 50);
		
		jpRightHeader.add(jlRightHeader);
		jlRightHeader.setForeground(Color.WHITE);
		jlRightHeader.setFont(new Font("Arial", 1, 20));
		jlRightHeader.setBounds(20, 10, 200, 25);
	
		add(jpRight);
		jpRight.setLayout(null);
		jpRight.setBackground(Color.WHITE);
		jpRight.setBounds(x/2 + 10, y/4 + 50, x/3 - 10, 3*y/4 - 50);
		
		jpRight.add(jlClass);
		jlClass.setFont(new Font("Arial", 1, fontsize));
		jlClass.setIcon(new ImageIcon("icon/add/name.png"));
		jlClass.setBounds(30, 50, weight, height);
		
		jpRight.add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1, fontsize));
		jlSpecialized.setIcon(new ImageIcon("icon/add/nganhhoc.png"));
		jlSpecialized.setBounds(30, 100, weight, height);
		
		jpRight.add(jlEducate);
		jlEducate.setFont(new Font("Arial", 1, fontsize));
		jlEducate.setIcon(new ImageIcon("icon/add/hedaotao.png"));
		jlEducate.setBounds(30, 150, weight, height);
		
		jpRight.add(jlUser);
		jlUser.setFont(new Font("Arial", 1, fontsize));
		jlUser.setIcon(new ImageIcon("icon/add/user.png"));
		jlUser.setBounds(30, 200, weight, height);
		
		jpRight.add(jlPass);
		jlPass.setFont(new Font("Arial", 1, fontsize));
		jlPass.setIcon(new ImageIcon("icon/add/pass.png"));
		jlPass.setBounds(30, 250, weight, height);
		

	}
	
	public void insertInfAccount(AccountObject account) {
		System.out.println("*************************************");
		accountControl.inTTin(account);
		StudentObject student = studentControl.loadStudent(account.getIdAccount());
		jlTitle.setText("Information of " + account.getIdAccount());
		jlId.setText("ID: " + account.getIdAccount());
		jlName.setText("Name: " + account.getName());
		jlName1.setText(account.getName());
		jlUser.setText("UserName: " + account.getUsername());
		jlPass.setText("PassWord: " + account.getPassword());
		jlCourse.setText("Course: " + student.getCourse());
		jlSpecialized.setText(student.getSpecialized());
		jlSpecialized1.setText(student.getSpecialized());
		jlClass.setText("Class: " + student.getClassSt());
		jlClass1.setText(student.getClassSt());
		jlEducate.setText("Educate: " + student.getEducate());
		jlSex.setText("Sex: " + student.getGioitinh());
		jlBirthday.setText("Birthday: " + student.getBirthday());
		jlAddress.setText("Address: " + student.getAddress());
		jlEmail.setText("Email: " + student.getEmail());
		jlPhone.setText("Phone: " + student.getPhone());
	}
}
