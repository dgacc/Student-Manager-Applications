package display;

import javax.swing.*;

import object.AccountObject;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	private JMenuBar jmbMenu;
	private JMenu jmHome, jmTtinTK, jmTaoTK, jmDangNhap, 
				  jmDangXuat, jmTtinNhom, jmTtinPhienBan, jmHuongDan, jmTimKiem; 
	private JPanel jpMain;
	private JTextField jtfSeach;
	public CardLayout card;
	JLayeredPane pane = getLayeredPane();

	
	Login login;
	CreateAccount create;
	Admin admin;
	Student student;
	AccountObject account;
	Group_03 group;

	public MainFrame() {
		
				create = new CreateAccount();
		login = new Login();
		admin = new Admin();
		student = new Student();
		
		jmbMenu = new JMenuBar();
		jpMain = new JPanel();
		jtfSeach = new JTextField();
		
		card= new CardLayout();
		
		jmHome = new JMenu("Home");
		jmHome.setIcon(new ImageIcon("icon/frame/home.png"));
		
		jmTtinTK = new JMenu("Thông tin TK");
		jmTtinTK.setIcon(new ImageIcon("icon/frame/account.png"));
		
		jmDangNhap = new JMenu("Đăng nhập");
		jmDangNhap.setIcon(new ImageIcon("icon/frame/login.png"));
		
		jmTaoTK = new JMenu("Tạo TK");
		jmTaoTK.setIcon(new ImageIcon("icon/frame/addaccount.png"));
		
		jmDangXuat = new JMenu("Đăng xuất");
		jmDangXuat.setIcon(new ImageIcon("icon/frame/logout.png"));
		
		jmTtinNhom = new JMenu("Nhóm 03");
		jmTtinNhom.setIcon(new ImageIcon("icon/frame/group.png"));
		
		jmTtinPhienBan = new JMenu("Phiên bản");
		jmTtinPhienBan.setIcon(new ImageIcon("icon/frame/version.png"));
		
		jmHuongDan = new JMenu("Hướng dẫn");
		jmHuongDan.setIcon(new ImageIcon("icon/frame/guide.png"));

		jmTimKiem = new JMenu("Tìm kiếm");
		jmTimKiem.setIcon(new ImageIcon("icon/frame/seach.png"));
		
		jmbMenu.add(jmHome); 
		jmbMenu.add(jmTtinTK);
		jmbMenu.add(jmDangNhap);
		jmbMenu.add(jmTaoTK); 
		jmbMenu.add(jmDangXuat);
		jmbMenu.add(jmTtinNhom); 
		jmbMenu.add(jmTtinPhienBan);
		jmbMenu.add(jmHuongDan); 
		jmbMenu.add(jmTimKiem);
		jmbMenu.add(jtfSeach);
		account = new AccountObject();
		group = new Group_03();
	}

	public void runMainFrame(int X, int Y) {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("QuanLySV_v2.0");
		setSize(X, Y);
		//setLocation(100, 20);
		setResizable(false);
		setMinimumSize(new Dimension(1200, 700));
		setJMenuBar(jmbMenu);
		pane.add(jpMain);
		
		int x = X;
		int y = Y - jmbMenu.getHeight();
//		jpMain.setSize(x, y);
		jpMain.setLayout(card);
		jpMain.setBounds(0, jmbMenu.getHeight(),x, y);
		
		jpMain.add(login, "Login");
		login.runLogin(x, y, this);
		
		jpMain.add(create, "CreateAccount");
		create.runCreateAccount(x, y, this);
		
		jpMain.add(admin, "Admin");
		admin.runAdmin(x, y, this);
		
		jpMain.add(student, "Student");
		student.runStudent(x, y, this);
		
		jpMain.add(group, "Group");
		group.run(x, y);
		
		card.show(jpMain, "Login");
		
		
		jmHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(jpMain, "Home");
			}
		});
		
		jmTtinTK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
			}
		});

		jmDangNhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(jpMain, "Login");
			}
		});

		jmTaoTK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(jpMain, "CreateAccount");
			}
		});

		jmDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int ans = JOptionPane.showConfirmDialog(null, "Are you sure?", "Yes or No", JOptionPane.YES_NO_OPTION);
				if(ans == JOptionPane.YES_OPTION) {
					card.show(jpMain, "Login");
				}
			}
		});

		jmTtinNhom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(jpMain, "Group");
			}
		});

		jmTtinPhienBan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				JOptionPane.showMessageDialog(null, "Student Manage group_03 v2!");
			}
		});
		
		jmHuongDan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});

		jmTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});
		
		
		
	}
	
	public JPanel getJpMain() {
		return jpMain;
	}
	
	public AccountObject getAccount() {
		return account;
	}

	public void setAccount(AccountObject account) {
		this.account = account;
	}
}
