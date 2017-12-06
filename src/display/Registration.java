package display;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Connect.ConnectDatabase;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.invoke.ConstantCallSite;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.*;
import control.*;

public class Registration extends JPanel{
	
	private JLabel jlTitle, jlName, jlId, jlClass, jlSpecialized, jlMax, jlSum, jlNote, 
	jlReIcon, jlRegister, jInfIcon,jlInfo, jlSearch ;
	private JTextField jtfDangki;
	private JPanel jpRight, jpLeft, jpHeader, jpRightTitle, jpLeftTitle;
	private JButton jbDangki, jbClass, jbDelete, jbDeleteAll, jbSend, jb;
	private JTable jtbRegistration, jtbTimeTable;
	private DefaultTableModel registrationModel, timeTableModel;
	private JScrollPane jcpDangki, jcpLichhoc;
	private String dangkiColumn[] = {"Ma lop", "Ten lop", "Ma hoc phan", "TT lop", "TT Dk", "TC"};
	private String dangkiRow[][] = {};
	private String lichhocColumn[] = {"Thu", "Thoi Gian", "Phong hoc", "Lop"};
	private String lichhocRow[][] = {};
	private JTableHeader timeTableHeader,registerHeader;
	
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	RegistrationControl registrationControl;
	StudentControl studentControl;
	SubjectControl subjectControl;
	ClassListForRegistration jpClass;
	int sum = 0;
	final int MAX = 24;
	
	public Registration() {
		jlReIcon = new JLabel();
		jlReIcon.setIcon(new ImageIcon("Icon/frame/registerCources1.jpg"));
		jInfIcon = new JLabel();
		jInfIcon.setIcon(new ImageIcon("Icon/frame/info.png"));
		jlTitle = new JLabel("Registration for 20171");
		jlRegister = new JLabel("Đăng  ký môn học cho hệ đào tạo tín chỉ");
		jlSearch = new JLabel("Nhập mã lớp học: ");
		jlInfo  = new JLabel("Thông tin");
		jlId = new JLabel("Ma so sinh vien:");
		jlName = new JLabel("Ho va ten:");
		jlClass = new JLabel("Lop:");
		jlSpecialized = new JLabel("Chuong trinh:", SwingConstants.LEFT);
		jlMax = new JLabel("So tin chi dang ki toi da: " + MAX);
		jlSum = new JLabel("Tong so TC dang ki:");
		jlNote = new JLabel();
		jlNote.setForeground(Color.RED);
		jtfDangki = new JTextField();
		
		jpRight = new JPanel();
		jpLeft = new JPanel();
		jpRightTitle = new JPanel();
		jpLeftTitle =new JPanel();
		
	
		jpClass = new ClassListForRegistration();
//		jspMain = new JSplitPane();
		
		jbDangki = new JButton("Đăng ký");
		jbClass = new JButton("Thông tin danh sách lớp mở");
		jbDelete = new JButton("Xóa Lớp");
		jbDeleteAll = new JButton("Xóa");
		jbSend = new JButton("Gửi DK");
		jpHeader = new JPanel();
		
	
		
		registrationModel = new DefaultTableModel(dangkiRow, dangkiColumn){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		timeTableModel = new DefaultTableModel(lichhocRow, lichhocColumn){
            boolean[] canEdit = new boolean [] { false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		
		jtbRegistration = new JTable(registrationModel);
		jtbRegistration.setFont(new Font("Arial", 1, 12));
		registerHeader = jtbRegistration.getTableHeader();
		registerHeader.setFont(new Font("Arial", 1, 15));
		
		
		jtbTimeTable = new JTable(timeTableModel);
		jtbTimeTable.setFont(new Font("Arial", 1, 12));
		timeTableHeader = jtbTimeTable.getTableHeader();
		timeTableHeader.setFont(new Font("Arial", 1, 15));
		timeTableHeader.setBackground(new Color(0, 170, 207));
		timeTableHeader.setForeground(Color.WHITE);

		
		
		jcpDangki = new JScrollPane(jtbRegistration);
		jcpLichhoc = new JScrollPane(jtbTimeTable);
		
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		registrationControl = new RegistrationControl();
		studentControl = new StudentControl();
		subjectControl = new SubjectControl();
	}
	
	public void runRegistration(int x, int y, Student st) {
		setSize(x, y);
		setLayout(null);
		
		add(jlReIcon);
		jlReIcon.setBounds(100,  y/8, 100, 100);
		add(jInfIcon);
		jInfIcon.setBounds(3*x/4 + 30, y/8, 100, 100);  
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/10);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(0, 10, x, 30);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		
		add(jpRight);
		jpRight.setLayout(null);
		jpRight.setBounds(3*x/4,  y/8 + 50, x/4 - 40, 3*y/4);
		jpRight.setBackground(Color.WHITE);
		
		jpRight.add(jpRightTitle);
		jpRightTitle.setLayout(null);
		jpRightTitle.setSize(x, y/9);
		jpRightTitle.setBackground(new Color(0, 170, 207));
		
		jpRightTitle.add(jlInfo);
		jlInfo.setFont(new Font("Arial", 1, 20));
		jlInfo.setBounds(150, 10, x, 20);
		jlInfo.setForeground(Color.WHITE);
	
		jpRight.add(jlId);
		jlId.setFont(new Font("Arial", 1, 12));
		jlId.setBounds(10, 140, x, 30);
		
		jpRight.add(jlName);
		jlName.setFont(new Font("Arial", 1, 12));
		jlName.setBounds(10, 100, x, 30);
		
		
		jpRight.add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1,12));
		jlSpecialized.setBounds(10, 180, x, 30);
		
		jpRight.add(jlClass);
		jlClass.setFont(new Font("Arial", 1, 12));
		jlClass.setBounds(10, 220, x, 30);
		
		jpRight.add(jlMax);
		jlMax.setFont(new Font("Arial", 1, 12));
		jlMax.setBounds(10, 260, x, 30);
		
		jpRight.add(jlSum);
		jlSum.setFont(new Font("Arial", 1, 12));
		jlSum.setBounds(10, 300, 200, 30);
		
		jpRight.add(jbClass);
		jbClass.setBounds(10, 420, x/4 - 60, 30);
		jbClass.setForeground(Color.WHITE);
		jbClass.setBackground(new Color(66, 103, 178));

		jpRight.add(jbSend);
		jbSend.setBounds( 10, 380, x/12- 25, 30);
		jbSend.setForeground(Color.WHITE);
		jbSend.setBackground(new Color(66, 103, 178));
		
		jpRight.add(jbDelete);
		jbDelete.setBounds(x/12 - 8 , 380, x/12- 25, 30);
		jbDelete.setForeground(Color.WHITE);
		jbDelete.setBackground(new Color(66, 103, 178));
		
		jpRight.add(jbDeleteAll);
		jbDeleteAll.setBounds(2*x/12 - 25, 380, x/12- 25, 30);
		jbDeleteAll.setForeground(Color.WHITE);
		jbDeleteAll.setBackground(new Color(66, 103, 178));
		
		
		
		
		add(jpLeft);
		jpLeft.setLayout(null);
		jpLeft.setBounds(30 , y/8 + 50, 3*x/4 - 40, 3*y/5);
		jpLeft.setBackground(Color.WHITE);
		
		jpLeft.add(jpLeftTitle);
		jpLeftTitle.setLayout(null);
		jpLeftTitle.setSize(x, y/9);
		jpLeftTitle.setBackground(new Color(0, 170, 207));
		
		jpLeftTitle.add(jtfDangki);
		jtfDangki.setBounds(3*x/8, 50, 200, 20);
		jtfDangki.setFont(new Font("Arial", 1, 12));
		
		jpLeftTitle.add(jbDangki);
		jbDangki.setBounds(3*x/8+ 220, 50, 100, 20);
		jbDangki.setFont(new Font("Arial", 1, 15));
		jbDangki.setForeground(Color.WHITE);
		jbDangki.setBackground(new Color(66, 103, 178));
		
		jpLeftTitle.add(jlRegister);
		jlRegister.setFont(new Font("Arial", 1, 20));
		jlRegister.setBounds(220, 10, x, 20);
		jlRegister.setForeground(Color.WHITE);
		
		jpLeftTitle.add(jlSearch);
		jlSearch.setBounds(x/4, 50, 200, 20);
		jlSearch.setFont(new Font("Arial", 1, 12));
		jlSearch.setForeground(Color.white);
		
		jpLeft.add(jcpDangki);
		jcpDangki.setBounds(0, y/9, 3*x/4 - 40, y/3);
	
		jpLeft.add(jcpLichhoc);
		jcpLichhoc.setBounds(0, 4*y/9, 3*x/4 - 40, y/3);
		
		
	
	
		runJpLeft( 3*x/4 - 40, y);
		
		jbDangki.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = jtfDangki.getText();
				Pattern pattern = Pattern.compile("\\d*");
			    Matcher checkDK = pattern.matcher(jtfDangki.getText());
			    
			    if(checkDK.matches()) {
			    	int id = Integer.parseInt(jtfDangki.getText());
			    	if(studentClassControl.checkClass(id, "he dao tao theo TC")){
			    		StudentClassObject stClass = studentClassControl.loadClass(id);
			    		if((sum + subjectControl.loadSubject(stClass.getIdClass()).getCount()) <= MAX) {
			    			if(!registrationControl.check(st.getStudent(), stClass)) {
				    			if(registrationControl.checkInfClass(stClass, st.getStudent())) {
				    				registrationControl.add(st.getStudent(), stClass);
				    				loadRegistrationTable(st.getStudent());
				    			}
				    			else {
				    				jlNote.setText("*Trung lich:" + stClass.getTime());
				    			}
				    		}
			    		}
			    		else {
			    			jlNote.setText("*so tin chi dang ki vuot qua 24!:");
			    		}
			    		
			    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Lop Nhap khong chinh xac!");
			    	}
			    }
			    else {
			    	JOptionPane.showMessageDialog(null, "Ma lop khong dung.Vui long nhap lai!");
			    }
			}
		});
		
		jbSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registrationControl.updateStatus(st.getStudent());
				st.timeTable.loadTimeTable(st.getStudent());
				loadRegistrationTable(st.getStudent());
			}
		});
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbRegistration.getSelectedRow();
				if(choose >= 0) {
					int id = Integer.parseInt((String)jtbRegistration.getValueAt(choose, 0));
					if(registrationControl.checkDki(st.getStudent().getIdStudent(), id)) {
					studentClassControl.delteteStudentFromClass(id, st.getStudent().getIdStudent());					}
					registrationControl.delete(st.getStudent().getIdStudent(), id);
					loadRegistrationTable(st.getStudent());
				}
			}
		});
		
		jbDeleteAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registrationControl.deleteAll(st.getStudent());
				connectDatabase.updateData("delete from studentlist where idStudent = " + st.getStudent().getIdStudent());
				loadRegistrationTable(st.getStudent());
			}
		});
		
		jbClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jpClass.runClassListForRegistration(st.getJtpMain().getWidth(), st.getJtpMain().getHeight());
				st.getJtpMain().addTab("class", jpClass);
			}
		});
		
//		jspMain.addPropertyChangeListener(new PropertyChangeListener() {
//			
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				int dividerLocation = jspMain.getDividerLocation();
//				if(dividerLocation > x/2) {
//					dividerLocation = x/2;
//					jspMain.setDividerLocation(dividerLocation);
//				}
//				if(dividerLocation < x/4) {
//					dividerLocation = x/4;
//					jspMain.setDividerLocation(dividerLocation);
//				}
//				runJpRight(x-dividerLocation, y);
//			}
//		});
	}
	
	public void loadRegistrationTable(StudentObject student) {
		jlId.setText("Mssv: " + student.getIdStudent());
		jlName.setText("Họ và tên: " + student.getName());
		jlClass.setText("Lớp: " + student.getClassSt());
		jlSpecialized.setText(student.getSpecialized());
		jlNote.setText(null);
		registrationModel.setRowCount(0);
		timeTableModel.setRowCount(0);
		sum = 0;
		String loadRegistration = "select * from dangki where idStudent = " + student.getIdStudent();
		ResultSet resultSet = connectDatabase.returnData(loadRegistration);
		
		try {
			while(resultSet.next()) {
				int idClass = resultSet.getInt(2);
				StudentClassObject stClass = studentClassControl.loadClass(idClass);
				SubjectObject subject = subjectControl.loadSubject(stClass.getIdSubject());
				sum += subject.getCount();
				String A[] = {"" + stClass.getIdClass(), stClass.getNameClass(), "" + stClass.getIdSubject(), "chinh sua DK", resultSet.getString(4), "" + subject.getCount()};
				String B[] = {"" + stClass.getDay(), stClass.getTime(), stClass.getAddress(), stClass.getNote()};
				registrationModel.addRow(A);
				timeTableModel.addRow(B);
				jlSum.setText("Tổng số TC đăng ký: " + sum);
				jtfDangki.setText(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runJpLeft(int x, int y) {
		jpLeft.setSize(x, y);
	
		jpLeft.add(jlNote);
		jlNote.setBounds(230, 50, 200, 30);
		jlNote.setFont(new Font("Arial", 1, 12));
		
	
	}
}
