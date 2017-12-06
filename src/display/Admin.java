package display;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.AccountControl;
import object.AccountObject;

public class Admin extends JPanel{
	private JPanel jpLeft, jpRight, jpHeader;
	private JLabel jlIcon1, jlIcon2, jlIcon3;
	private JButton jbInf, jbStudent, jbAccount, jbSubJect, jbClass, jbLogOut, jbChangePass;
	
	CardLayout card;
	Information information;
	StudentManage studentManage;
	AccountManage accountManage;
	ClassManage classManage;
	SubjectManage subjectManage;
	StudentList studentList;
	
	AccountControl accountControl;
	AccountObject account;
	
	public Admin() {
		jpLeft = new JPanel();
		jpRight = new JPanel();
		jpHeader = new JPanel();
		
		jlIcon1 = new JLabel();
		jlIcon1.setIcon(new ImageIcon("icon/linhtinh/word.png"));
		
		jlIcon2 = new JLabel();
		jlIcon2.setIcon(new ImageIcon("icon/linhtinh/pen.png"));
		
		jlIcon3 = new JLabel();
		jlIcon3.setIcon(new ImageIcon("icon/linhtinh/book.png"));
		
		jbInf = new JButton("Information");
		jbStudent = new JButton("Student Manage");
		jbAccount = new JButton("Account Manage");
		jbSubJect = new JButton("Subject Manage");
		jbClass = new JButton("Class Manage");
		jbChangePass = new JButton("Change PassWord");
		jbLogOut = new JButton("Logout");
		
		card = new CardLayout();
		
		information = new Information();
		studentManage = new StudentManage();
		accountManage = new AccountManage();
		subjectManage = new SubjectManage();
		classManage = new ClassManage();
		studentList = new StudentList();
		
		accountControl = new AccountControl();
		account = new AccountObject();
	}
	
	public void runAdmin(int x, int y, MainFrame mainFrame) {
		setSize(x, y);
		setLayout(null);
		
		add(jpLeft);
		jpLeft.setLayout(null);
		jpLeft.setBounds(0, 0 ,x/6, y );
		jpLeft.setBackground(Color.WHITE);
		
		jpLeft.add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x/6, y/10);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		//them icon ban do the gioi
//		jpLeft.add(jlIcon1);
//		jlIcon1.setBounds(10, 10, 128, 128);
		
		//them icon chiec but
//		jpLeft.add(jlIcon2);
//		jlIcon2.setBounds(140, 10, 64, 64);
		
		//them icon quyen sach
//		jpLeft.add(jlIcon3);
//		jlIcon3.setBounds(30, y*3/4, 128, 128);
		
		jpLeft.add(jbInf);
		jbInf.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4, jpLeft.getWidth()*4/5, 30);
		jbInf.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbStudent);
		jbStudent.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 + 50, jpLeft.getWidth()*4/5, 30);
		jbStudent.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbAccount);
		jbAccount.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 +100, jpLeft.getWidth()*4/5, 30);
		jbAccount.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbSubJect);
		jbSubJect.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 + 150, jpLeft.getWidth()*4/5, 30);
		jbSubJect.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbClass);
		jbClass.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 + 200, jpLeft.getWidth()*4/5, 30);
		jbClass.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbChangePass);
		jbChangePass.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 + 250, jpLeft.getWidth()*4/5, 30);
		jbChangePass.setFont(new Font("Arial", 1, 15));
		
		jpLeft.add(jbLogOut);
		jbLogOut.setBounds(jpLeft.getWidth()/10, jpLeft.getHeight()/4 + 300, jpLeft.getWidth()*4/5, 30);
		jbLogOut.setFont(new Font("Arial", 1, 15));
		
		add(jpRight);
		jpRight.setLayout(card);
		jpRight.setBounds(x/6 + 10, 0, x*5/6 - 40, y);
		
		jpRight.add(information, "Information");
		information.runInformation(jpRight.getWidth(), jpRight.getHeight());
		
		jpRight.add(studentManage, "StudentManage");
		studentManage.runStudentManage(jpRight.getWidth(), jpRight.getHeight());
		
		jpRight.add(accountManage, "AccountManage");
		accountManage.runAccountManage(jpRight.getWidth(), jpRight.getHeight());
		
		jpRight.add(subjectManage, "SubjectManage");
		subjectManage.runSubjectManage(jpRight.getWidth(), jpRight.getHeight());
		
		jpRight.add(classManage, "ClassManage");
		classManage.runClassManage(jpRight.getWidth(), jpRight.getHeight(), this);
		
		jpRight.add(studentList, "StudentList");
		studentList.runStudentList(jpRight.getWidth(), jpRight.getHeight(), this);
		
		card.show(jpRight, "Information");
		jbInf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(jpRight, "Information");
			}
		});
		
		jbStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(jpRight, "StudentManage");
				studentManage.newTable();
			}
		});
		
		jbAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(jpRight, "AccountManage");
			}
		});
		
		jbSubJect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(jpRight, "SubjectManage");
			}
		});
		
		jbClass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(jpRight, "ClassManage");
			}
		});
		
		jbChangePass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newPass = JOptionPane.showInputDialog(null, "Input new Password:");
				if(newPass.length() != 0) {
					accountControl.changeInfAccount(mainFrame.getAccount(), "password", newPass);
					int ans = JOptionPane.showConfirmDialog(null, "Change Password successful!Do you want login?", "Question", JOptionPane.YES_NO_OPTION);
					if(ans == JOptionPane.YES_OPTION) {
						accountControl.inTTin(mainFrame.getAccount());
						mainFrame.card.show(mainFrame.getJpMain(), "Login");
					}
				}
			}
		});
		
		jbLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int ans = JOptionPane.showConfirmDialog(null, "Are you sure?", "Question", JOptionPane.YES_NO_OPTION);
				if(ans == JOptionPane.YES_OPTION) {
					mainFrame.card.show(mainFrame.getJpMain(), "Login");
				}
			}
		});
	}

	public JPanel getJpRight() {
		return jpRight;
	}
	
	public AccountObject getAccount() {
		return account;
	}

	public void setAccount(AccountObject account) {
		this.account = account;
	}
}
