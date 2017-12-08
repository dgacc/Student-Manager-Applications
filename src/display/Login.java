package display;

import javax.swing.*;
import control.*;
import object.AccountObject;
import object.StudentObject;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JPanel{
	private JPanel jpHeader;
	private JLabel jlTitle, jlTitle1, jlUser, jlPass, jlGoiY, jlIcon;
	private JTextField jtfUser;
	private JPasswordField jpfPass;
	private JButton jbLogIn, jbCreateID, jbForgotPass;
	AccountControl accountControl;
	StudentControl studentControl;
	
	public Login() {
		jlIcon = new JLabel();
		jlIcon.setIcon(new ImageIcon("icon/frame/education.png"));
		jtfUser = new JTextField();
		jpfPass = new JPasswordField();
		
		
		jbLogIn = new JButton("LogIn");
		jbCreateID = new JButton("CreateID");
		jbForgotPass = new JButton("Forgot PassWord");
		
		jlTitle = new JLabel("Ứng dụng quản lý sinh viên");
//		jlTitle1 = new JLabel("Nhóm 03 - 20171");
		jlGoiY = new JLabel("Vui lòng đăng nhập để vào hệ thống ! Nếu chưa có TK bấm CreateID để tạo ms!");
		jlUser = new JLabel("UserName:"); 
		jlPass = new JLabel("PassWord:");
	
		jpHeader = new JPanel();
		
		accountControl = new AccountControl();
		studentControl = new StudentControl();
	}

	public void runLogin(int x, int y, MainFrame mainFrame) {
		setSize(x, y);
		setLayout(null);
		add(jlIcon);
		jlIcon.setBounds(x/2-100, y/10, 200, 200);
		jlIcon.setFocusable(true);
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, 2*y/9);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/32));
		jlTitle.setBounds(x/6, 0, x*2/3, 50);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		jlTitle.setVerticalAlignment((int) CENTER_ALIGNMENT);
		
		
		
//		jpHeader.add(jlTitle1);
//		jlTitle1.setFont(new Font("Arial", 1, 20));
//		jlTitle1.setBounds(x/6, 30, x*2/3, 50);
//		jlTitle1.setForeground(Color.WHITE);
//		jlTitle1.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//		
		
		
		add(jlGoiY);
		jlGoiY.setBounds(x/5, 3*y/4 + 50, x*2/3, 50);
		jlGoiY.setFont(new Font("Arial", 1, x/64));
		jlGoiY.setForeground(new Color(98, 86, 86));
		
		add(jlUser);
		jlUser.setBounds(x/2 - x/6, y/3, x/7, 50);
		jlUser.setFont(new Font("Arial", 1, 20));
		jlUser.setIcon(new ImageIcon("icon/username.png"));
		
		add(jtfUser);
		jtfUser.setBounds(x/2 - x/6, y/3 + 50, x/3, 50);
		jtfUser.setFont(new Font("Arial", 1, 15));
		jtfUser.setText("tran");
		jtfUser.setHorizontalAlignment((int)CENTER_ALIGNMENT);
		
		add(jlPass);
		jlPass.setBounds(x/2- x/6, y/3+90, x/7, 50);
		jlPass.setFont(new Font("Arial", 1, 20));
		jlPass.setIcon(new ImageIcon("icon/password.png"));
		
		add(jpfPass);
		jpfPass.setBounds(x/2 - x/6, y/3 + 130, x/3, 50);
		jpfPass.setFont(new Font("Arial", 1, 15));
		jpfPass.setText("a");
		jpfPass.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		add(jbLogIn);
		jbLogIn.setBounds(x/2 - x/6, y/3+200, x/6 - 3, 50);
		jbLogIn.setFont(new Font("Arial", 1, 20));
		jbLogIn.setBackground(new Color(0, 170, 207));
		jbLogIn.setForeground(Color.white);
		
		add(jbCreateID);
		jbCreateID.setBounds(x/2 + 3, y/3+200, x/6 - 3, 50);
		jbCreateID.setFont(new Font("Arial", 1, 20));
		jbCreateID.setBackground(new Color(0, 170, 207));
		jbCreateID.setForeground(Color.white);
		
		add(jbForgotPass);
		jbForgotPass.setBounds(x/2 - x/6, y/3+270, x/3, 50);
		jbForgotPass.setFont(new Font("Arial", 1, 20));
		jbForgotPass.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		jbForgotPass.setBackground(new Color(0, 170, 207));
		jbForgotPass.setForeground(Color.white);
		
		
		jbLogIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = jtfUser.getText();
				String pass = jpfPass.getText();
				
				if(accountControl.checkAccount(user, pass)) {
					AccountObject account = accountControl.loadAccount(accountControl.returnID(user, pass));
					mainFrame.setAccount(account);
					if(account.getPermission().toLowerCase().equals("admin")) {
						mainFrame.card.show(mainFrame.getJpMain(), "Admin");
						mainFrame.admin.setAccount(account);
						mainFrame.admin.information.insertInfAccount(mainFrame.admin.getAccount());
					}
					else if(account.getPermission().toLowerCase().equals("student")){
						mainFrame.card.show(mainFrame.getJpMain(), "Student");
						mainFrame.student.setAccount(account);
						StudentObject st = studentControl.loadStudent(account.getIdAccount());
						if(st.getEducate() == 1) {
							mainFrame.student.getJtpMain().addTab("registration", mainFrame.student.registration);
							mainFrame.student.registration.runRegistration(mainFrame.student.getJtpMain().getWidth(),
									mainFrame.student.getJtpMain().getHeight(), mainFrame.student);
							mainFrame.student.registration.loadRegistrationTable(mainFrame.student.getStudent());
							mainFrame.student.transcript.loadTranscriptTable(mainFrame.student.getStudent());
						}
						
						mainFrame.student.timeTable.loadTimeTable(mainFrame.student.getStudent());
						mainFrame.student.information.insertInfAccount(mainFrame.student.getAccount());
					}
					else {
						JOptionPane.showMessageDialog(null, "Tk cua ban la tai khoan khach!lh vs quan tri vien de cap quyen!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Tk MK k chinh xac");
				}
			}
		});
		
		jbCreateID.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.card.show(mainFrame.getJpMain(), "CreateAccount");
			}
		});
		
		jbForgotPass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String user = JOptionPane.showInputDialog(null, "Nhap ten tai khoan:");
					String inputId = JOptionPane.showInputDialog(null, "Nhap id:");
					Pattern idcheck = Pattern.compile("\\d*");
				    Matcher testId = idcheck.matcher(inputId);
				    
				    if(user.length() != 0 && "".equals(user) && inputId.length() != 0 && "".equals(inputId)) {
				    	if(testId.matches()) {
					    	int id = Integer.parseInt(inputId);
							
							if(accountControl.checkAccount(id)) {
								AccountObject account = accountControl.loadAccount(id);
								if(account.getUsername().toLowerCase().equals(user.toLowerCase())) {
									JOptionPane.showMessageDialog(null, "Mat khau dang ki la:" + account.getPassword());
								}
								else {
									JOptionPane.showMessageDialog(null, "Id cua ban khong trung khop voi username!Vui long kiem tra lai!");
								}
							}
					    }
					    else {
					    	JOptionPane.showMessageDialog(null, "Vui long kiem tra lai ID!");
					    }
				    }
				}
				catch (java.lang.NullPointerException exception) {
					System.out.println("Bam Cancel");
				}
				
			}
		});
	}
}
