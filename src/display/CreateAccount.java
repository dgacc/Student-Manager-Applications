package display;

import javax.swing.*;

import control.AccountControl;
import control.StudentControl;
import object.AccountObject;
import object.StudentObject;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends JPanel{
	private JLabel jlUser, jlPass, jlID, jlHoten, jlGtinh, jlNgaysinh, jlQueQuan, jlEmail, jlSdt;
	private JTextField jtfUser, jtfID, jtfHoten, jtfQueQuan, jtfEmail, jtfSdt;
	private JPasswordField jpfPass;
	private JButton jbCreate, jbClear, jbCancel;
	private JComboBox jcbSex, jcbDay, jcbMonth, jcbYear;
	private DefaultComboBoxModel sexModel, dayModel, monthModel, yearModel;
	private String sex[] = {"Nam", "Nu", "Khac"};
	private String day[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", 
							"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private String month[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	private String year[] = {"1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001"};
	String url, user, pass;
	StudentControl studentControl;
	AccountControl accountControl;
	
	public CreateAccount() {
		jlUser = new JLabel("UserName:"); 
		jlPass = new JLabel("PassWord:");
		jlID = new JLabel("ID:"); 
		jlHoten = new JLabel("Name:");
		jlGtinh = new JLabel("Sex:");
		jlNgaysinh = new JLabel("DateOfBirthday:"); 
		jlQueQuan = new JLabel("Address:");
		jlEmail = new JLabel("Email:"); 
		jlSdt = new JLabel("Phone:");
		
		sexModel = new DefaultComboBoxModel<>(sex);
		dayModel = new DefaultComboBoxModel<>(day);
		monthModel = new DefaultComboBoxModel<>(month);
		yearModel = new DefaultComboBoxModel<>(year);
		
		jcbSex = new JComboBox<>();
		jcbSex.setModel(sexModel);
		jcbDay = new JComboBox<>();
		jcbDay.setModel(dayModel);
		jcbMonth = new JComboBox<>();
		jcbMonth.setModel(monthModel);
		jcbYear = new JComboBox<>();
		jcbYear.setModel(yearModel);
		
		jtfUser = new JTextField(); 
		jtfHoten = new JTextField();
		jtfID = new JTextField();
		jtfQueQuan = new JTextField(); 
		jtfEmail = new JTextField(); 
		jtfSdt = new JTextField(); 
		jpfPass = new JPasswordField();
		
		jbCreate = new JButton("Create");
		jbClear = new JButton("Clear");
		
		studentControl = new StudentControl();
		accountControl = new AccountControl();
	}
	public void runCreateAccount(int x, int y, MainFrame mainFrame) {
		setSize(x, y);
		setLayout(null);
		int m = x/6; //khoảng cách l�? trái của jlabel
		int n = y/10; // khoảng cách l�? trên của jlabal
		int p = x/6; // chi�?u dài jlabal
		int k = y/20; // chi�?u rộng jlabal
		
		add(jlUser);
		jlUser.setBounds(m, n, p, k);
		jlUser.setFont(new Font("Arial", 1, 20));
		jlUser.setIcon(new ImageIcon("icon/add/user.png"));
		
		add(jtfUser);
		jtfUser.setBounds(m + p, n, 2*m, k);
		jtfUser.setFont(new Font("Arial", 1, 15));
		
		add(jlPass);
		jlPass.setBounds(m, n+k+20, p, k);
		jlPass.setFont(new Font("Arial", 1, 20));
		jlPass.setIcon(new ImageIcon("icon/add/pass.png"));
		
		add(jpfPass);
		jpfPass.setBounds(m + p, n+k +20, 2*m, k);
		jpfPass.setFont(new Font("Arial", 1, 15));
		
		add(jlID);
		jlID.setBounds(m, n+ 2*k+2*20, p, k);
		jlID.setFont(new Font("Arial", 1, 20));
		jlID.setIcon(new ImageIcon("icon/add/id.png"));
		
		add(jtfID);
		jtfID.setBounds(m + p, n+ 2*k +2*20, 2*m, k);
		jtfID.setFont(new Font("Arial", 1, 15));
		
		add(jlHoten);
		jlHoten.setBounds(m, n+ 3*k+3*20, p, k);
		jlHoten.setFont(new Font("Arial", 1, 20));
		jlHoten.setIcon(new ImageIcon("icon/add/name.png"));
		
		add(jtfHoten);
		jtfHoten.setBounds(m + p, n+ 3*k +3*20, 2*m, k);
		jtfHoten.setFont(new Font("Arial", 1, 15));
		
		add(jlGtinh);
		jlGtinh.setBounds(m, n+ 4*k+4*20, p, k);
		jlGtinh.setFont(new Font("Arial", 1, 20));
		jlGtinh.setIcon(new ImageIcon("icon/add/gt.png"));
		
		add(jcbSex);
		jcbSex.setBounds(m + p, n+ 4*k +4*20, 2*m, k);
		jcbSex.setFont(new Font("Arial", 1, 13));
		
		add(jlNgaysinh);
		jlNgaysinh.setBounds(m, n+ 5*k+5*20, p, k);
		jlNgaysinh.setFont(new Font("Arial", 1, 20));
		jlNgaysinh.setIcon(new ImageIcon("icon/add/date.png"));
		
		add(jcbDay);
		jcbDay.setBounds(m + p, n+ 5*k +5*20, 2*m/3-10, k);
		jcbDay.setFont(new Font("Arial", 1, 13));
		
		add(jcbMonth);
		jcbMonth.setBounds(m*5/3 + p, n+ 5*k +5*20, 2*m/3-10, k);
		jcbMonth.setFont(new Font("Arial", 1, 13));
		
		add(jcbYear);
		jcbYear.setBounds(m*7/3 + p, n+ 5*k +5*20, 2*m/3, k);
		jcbYear.setFont(new Font("Arial", 1, 13));
		
		add(jlQueQuan);
		jlQueQuan.setBounds(m, n+ 6*k+6*20, p, k);
		jlQueQuan.setFont(new Font("Arial", 1, 20));
		jlQueQuan.setIcon(new ImageIcon("icon/add/address.png"));
		
		add(jtfQueQuan);
		jtfQueQuan.setBounds(m + p, n+ 6*k +6*20, 2*m, k);
		jtfQueQuan.setFont(new Font("Arial", 1, 15));
		
		add(jlEmail);
		jlEmail.setBounds(m, n+ 7*k+7*20, p, k);
		jlEmail.setFont(new Font("Arial", 1, 20));
		jlEmail.setIcon(new ImageIcon("icon/add/mail.png"));
		
		add(jtfEmail);
		jtfEmail.setBounds(m + p, n+ 7*k +7*20, 2*m, k);
		jtfEmail.setFont(new Font("Arial", 1, 15));
		
		add(jlSdt);
		jlSdt.setBounds(m, n+ 8*k+8*20, p, k);
		jlSdt.setFont(new Font("Arial", 1, 20));
		jlSdt.setIcon(new ImageIcon("icon/add/phone.png"));
		
		add(jtfSdt);
		jtfSdt.setBounds(m + p, n+ 8*k +8*20, 2*m, k);
		jtfSdt.setFont(new Font("Arial", 1, 15));
		
		add(jbCreate);
		jbCreate.setBounds(m*3/2, n+ 9*k+ 9*20, p*3/4, 30);
		jbCreate.setFont(new Font("Arial", 1, 15));
		
		add(jbClear);
		jbClear.setBounds(m*3/2+p, n+ 9*k+ 9*20, p * 3/4, 30);
		jbClear.setFont(new Font("Arial", 1, 15));
				
		jbCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Pattern idcheck = Pattern.compile("\\d*");
			    Matcher testId = idcheck.matcher(jtfID.getText());
			    
			    Pattern emailcheck = Pattern.compile("\\b[a-z0-9]*.\\b[a-z0-9]*@\\b[a-z0-9]*.\\b[a-z0-9]{2,4}");
			    Matcher testEmail = emailcheck.matcher(jtfEmail.getText());
			    
				if(jtfID.getText().length() == 0 || jtfHoten.getText().length() == 0 || jtfUser.getText().length() == 0 ||
						jpfPass.getText().length() == 0 || jtfQueQuan.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Vui long dien day du thong tin!");
				}
				else if(!testId.matches()){
					JOptionPane.showMessageDialog(null, "Nhap Id phai la so!");
				}
				else if(!testEmail.matches()) {
					JOptionPane.showMessageDialog(null, "Error!email:(String)[.](String)@(String).(String)");
				}
				else {
					StudentObject student = new StudentObject();
					AccountObject account = new AccountObject();
					int id = Integer.parseInt((String)jtfID.getText());
					String date = "" + jcbDay.getSelectedItem() + "/" + jcbMonth.getSelectedItem() + "/" + jcbYear.getSelectedItem();
					String user = jtfUser.getText();
					String pass = jpfPass.getText();
					
					if(accountControl.checkAccount(id) || studentControl.checkIdStudent(id)) {
						JOptionPane.showMessageDialog(null, "Id da co.Vui long nhap Id ms!");
					}
					else {
						student.setIdStudent(id);
						student.setName(jtfHoten.getText());
						student.setGioitinh((String)jcbSex.getSelectedItem());
						student.setBirthday(date);
						student.setAddress(jtfQueQuan.getText());
						student.setEmail(jtfEmail.getText());
						student.setPhone(jtfSdt.getText());
						
						studentControl.addStudent(student);
						
						account = accountControl.loadAccountFromStudent(student);
						accountControl.addAccount(account);
						accountControl.changeInfAccount(account, "username", user);
						accountControl.changeInfAccount(account, "password", pass);
						
						int ans = JOptionPane.showConfirmDialog(null, "Do you want login", "Question?", JOptionPane.YES_NO_OPTION);
						if(ans == JOptionPane.YES_OPTION) {
							mainFrame.setAccount(account);
							mainFrame.card.show(mainFrame.getJpMain(), "Student");
						}
						else {
							mainFrame.card.show(mainFrame.getJpMain(), "Login");
						}
					}
				}
			}
		});
		
		jbClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jtfUser.setText(null);
				jpfPass.setText(null);
				jtfID.setText(null);
				jtfHoten.setText(null);
				jcbSex.setSelectedIndex(0);
				jcbDay.setSelectedIndex(0);
				jcbMonth.setSelectedIndex(0);
				jcbYear.setSelectedIndex(0);
				jtfQueQuan.setText(null);
				jtfEmail.setText(null);
				jtfSdt.setText(null);
			}
		});
	}
}
