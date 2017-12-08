package display;

import javax.swing.*;
import javax.swing.table.*;

import Connect.ConnectDatabase;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import control.*;
import object.AccountObject;
import object.StudentObject;

public class StudentManage extends JPanel{
	
	private JPanel jpTableStudent, jpleft, jpright, jpCenter, jpHeader; 
	private JComboBox jcbNganh, jcbKhoa, jcbLop, jcbCourse, jcbSpecialized, jcbClass, jcbEducate;
	private JLabel jlTitle, jlId, jlName, jlCourse, jlSpecialized, jlClass, jlEducate ;
	private JTextField jtfId, jtfName;
	private JTable jtbStudentManage;
	private JButton jbAddStudent, jbChange, jbDeleteStudent;
	private JScrollPane jcpStudent;
	public DefaultTableModel studentModel;
	private DefaultComboBoxModel lopModel, classModel;
	ConnectDatabase connectDatabase;
	ResultSet resultSet;
	StudentControl studentControl;
	AccountControl accountControl;
	private String studentColumn[] = {"STT", "IdStudent", "Name", "Class", "Birthday", "Address", "Email", "State"};
	private String studentRow[][] = {};
	private String specializedValue[] = {"All", "Chuong Trinh Viet-Nhat", "Phong Dao Tao Dai Hoc", "TT Dao Tao Tai Nang", "Vien Cong Nghe Sinh Hoc & Thuc Pham",
								"Vien Cong Nghe Thong Tin & Truyen Thong", "Vien Co Khi", "Vien Co Khi Dong Luc", "Vien Det May-Da giay & Thoi Trang",
								"Vien Dao Tao Lien Tuc", "Vien Dien", "Vien Dien Tu-Vien Thong", "Vien Kinh Te-Quan Ly", "Vien Ki Thuat Hoa Hoc",
								"Vien Ki Thuat Hat Nhan & Vat li Moi Truong", "Vien Khoa Hoc & Cong Nghe Moi Truong", "Vien Khoa Hoc & Ki Thuat Vat Lieu",
								"Vien Ngoai Ngu", "Vien Su Pham Ki Thuat", "Vien Toan Ud & Tin Hoc", "Vien Vat Li Ki Thuat"};
	private String vietnhatclass[] = {"All", "VN-A", "Vn-B", "VN-C"};
	private String phongdtdh[] = {"All", "PDTDH"};
	private String tainangclass[] = {"All", "CTTT-CDT", "CTTT-DDT", "CTT-KHVL", "KSCLC-CKHK", "KSCLC-HTTT&TT", "KSCLC-THCN", "KSTN-CNTT", "KSTNCDT", "KSTN-DTTT", "KSTN-DKTDH", "KSTN-HD", "KSTN-TT"};
	private String sinhhocthucphamclass[] = {"All", "CN-TP", "KTSH 1", "KTSH 2", "TP 1", "TP 2", "TP 3"};
	private String cnttclass[] = {"All", "CN-CNTT 1", "CN-CNTT 2", "CN-CNTT 3", "CNTT 1.1", "CNTT 1.2", "CNTT 1.3", "CNTT 2.1", "CNTT 2.2", "CNTT 2.3", "CNTT 2.3", "ICT"};
	private String cokhiclass[] = {"All", "CN-CDT 1", "CN-CDT 2", "CN-CTM 1", "CN-CTM 2", "KTCDT 1", "KTCDT 2", "KTCDT 3" , "KTCK 1", "KTCK 2", "KTCK 3", "KTCK 4"};
	private String cokhidonglucclass[] = {"All", "OTO 01", "OTO 02", "CKDL 01", "CKDL 02", "CKDL 03", "KTHK", "TT"};
	private String detmayclass[] = {"All", "CN DG", "CN M1", "CN M2", "KTD"};
	private String daotaolientucclass[] = {"All", "DTLT 1", "DTLT 2", "DTLT 3", "DTLT 4"};
	private String dienclass[] = {"All", "CN-DK&TDH 01", "CN-DK&TDH 02", "CN-DK&TDH 03", "DIEN 01", "DIEN 02", "DK&TDK 01", "DK&TDK 02", "DK&TDK 03", "DK&TDK 04", "DK&TDK 05", "DK&TDK 06"};
	private String dientuvienthongclass[] = {"All", "CN-DT 01", "CN-DT 02", "CN-DT 03", "DT 01", "DT 02", "DT 03", "DT 04", "DT 05"};
	private String kinhtequanlyclass[] = {"All", "KT 01", "KT 02", "KTCN 01", "KTCN 02", "QLCN 01", "QLCN 02", "QLCN 03", "TC-NH"};
	private String kithuathoahocclass[] = {"All", "CN-KTHH 01", "CN-KTHH 02", "HH", "KTHH 01", "KTHH 02", "KTHH 03", "KTHH 04", "KTI&TT"};
	private String kithuathatnhanclass[] = {"All", "KTHN 01", "KTHN 02"};
	private String congnghemoitruongclass[] = {"All", "MT 01", "MT 02"};
	private String kithuatvatlieuclass[] = {"All", "VL 01", "VL 02", "VL 03"};
	private String ngoainguclass[] = {"All", "TA1.01", "TA1.02", "TA1.03", "TA1.04", "TA1.05", "TA2.01", "TA2.02", "TA2.03"};
	private String suphamkithuatclass[] = {"All", "SPKT CNTT", "SPKT KTD", "SPKT CN"};
	private String toantinclass[] = {"All", "TT 01", "TT 02"};
	private String vatlykithuatclass[] = {"All", "VLKT 01", "VLKT 02", "VLKT 03", "VLKT 04"};
	private String courseValue[] = {"All", "61", "60" , "59"};
	private String educateValue[] = {"He Dao Tao TC", "He Nien Che"};
	private String value[] = {"All"};
	private JLabel jlUser, jlPass, jlGtinh, jlNgaysinh, jlQueQuan, jlEmail, jlSdt;
	private JTextField jtfUser,jtfQueQuan, jtfEmail, jtfSdt;
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
	int count = 1;
	
	public StudentManage() {
		jlUser = new JLabel("UserName:"); 
		jlPass = new JLabel("PassWord:");
		jlGtinh = new JLabel("Sex:");
		jlNgaysinh = new JLabel("Birthday:"); 
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
		jtfQueQuan = new JTextField(); 
		jtfEmail = new JTextField(); 
		jtfSdt = new JTextField(); 
		jpfPass = new JPasswordField();
		
		jbCreate = new JButton("Create");
		jbClear = new JButton("Clear");
		
		studentControl = new StudentControl();
		accountControl = new AccountControl();
		
		jpTableStudent = new JPanel();
		jpleft = new JPanel();
		jpright = new JPanel();
		jpHeader = new JPanel();
		jpCenter = new JPanel();
		jcbNganh = new JComboBox(specializedValue);
		jcbKhoa = new JComboBox(courseValue);
		jcbLop = new JComboBox();
		jcbCourse = new JComboBox(courseValue);
		jcbSpecialized = new JComboBox(specializedValue);
		jcbClass = new JComboBox();
		jcbEducate = new JComboBox(educateValue);
		
		jlTitle = new JLabel("Student Manage");
		jlId = new JLabel("ID:");
		jlName = new JLabel("Name:");
		jlCourse = new JLabel("Course:");
		jlSpecialized = new JLabel("Specialized:");
		jlClass = new JLabel("Class:");
		jlEducate = new JLabel("Educate");
		
		jtfId = new JTextField();
		jtfName = new JTextField();
		
		jbAddStudent = new JButton("Add Student");
		jbChange = new JButton("Change");
		jbDeleteStudent = new JButton("Delete Student");
		
		studentModel = new DefaultTableModel(studentRow, studentColumn){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		classModel = new DefaultComboBoxModel(value);
		lopModel = new DefaultComboBoxModel(value);
		jtbStudentManage = new JTable(studentModel);
		jtbStudentManage.setFont(new Font("Arial", 1, 12));
		
		jcpStudent = new JScrollPane(jtbStudentManage);
		connectDatabase = new ConnectDatabase();
		studentControl = new StudentControl();
		accountControl = new AccountControl();
	}
	
	public void runStudentManage(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		
		
		add(jcpStudent);
		jcpStudent.setBounds(0, y /2 + 20, x, y/2 - 60);
		
		
		
		
		add(jpTableStudent);
		jpTableStudent.setLayout(null);
		jpTableStudent.setBackground( new Color(0, 170, 207));
		jpTableStudent.setBounds(0, y/2 - 20, x, 80);
		
		jpTableStudent.add(jcbNganh);
		jcbNganh.setBounds(10, 10, 250, 20);
		
		jpTableStudent.add(jcbKhoa);
		jcbKhoa.setBounds(300, 10, 100, 20);
		
		jpTableStudent.add(jcbLop);
		jcbLop.setModel(lopModel);
		jcbLop.setBounds(450, 10, 100, 20);
		
	
		
		
		
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/10);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(x/3, 0, x/3, 30);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
//		
		

	    add(jpleft);
	    jpleft.setLayout(null);
	    jpleft.setBackground(Color.WHITE);
	    jpleft.setBounds(0, y/8, x /3 - 10,y/3);
	
	    jpleft.add(jlId);
		jlId.setFont(new Font("Arial", 1, 14));
		jlId.setBounds(10, 60, x/10, 20);
		
		jpleft.add(jtfId);
		jtfId.setFont(new Font("Arial", 1, 14));
		jtfId.setBounds( x/10 + 10, 60, x/5, 20);
		
		
		
		
		
		jpleft.add(jlEducate);
		jlEducate.setFont(new Font("Arial", 1, 14));
		jlEducate.setBounds(10, 100, x/10, 20);
		
		jpleft.add(jcbEducate);
		jcbEducate.setBounds( x/10 + 10, 100, x/5, 20);
		
		jpleft.add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1, 14));
		jlSpecialized.setBounds(10, 140, x/10, 20);
		
		jpleft.add(jcbSpecialized);
		jcbSpecialized.setBounds( x/10 + 10, 140, x/5, 20);
		
		jpleft.add(jlCourse);
		jlCourse.setFont(new Font("Arial", 1, 14));
		jlCourse.setBounds(10, 180, x/10, 30);
		
		jpleft.add(jcbCourse);
		jcbCourse.setBounds( x/10 + 10, 180, x/5, 20);
		

		jpleft.add(jlClass);
		jlClass.setFont(new Font("Arial", 1, 14));
		jlClass.setBounds(10, 220, x/10, 20);
		
		jpleft.add(jcbClass);
		jcbClass.setModel(classModel);
		jcbClass.setBounds(x/10 + 10, 220, x/5, 20);
		
 
		
		
		
		
		
	    
	    add(jpright);
	    jpright.setLayout(null);
	    jpright.setBackground(Color.WHITE);
	    jpright.setBounds(2*x/3, y/8, x /3 - 10,y/3);
	    
	    jpright.add(jlUser);
		jlUser.setBounds(10, 60, x/10, 20);
		jlUser.setFont(new Font("Arial", 1, 14));

		jpright.add(jtfUser);
		jtfUser.setBounds( x/10 + 10, 60, x/5, 20);
		jtfUser.setFont(new Font("Arial", 1, 15));
		
		jpright.add(jlPass);
		jlPass.setBounds(10, 100, x/10, 20);
		jlPass.setFont(new Font("Arial", 1, 14));
		
		jpright.add(jpfPass);
		jpfPass.setBounds( x/10 + 10, 100, x/5, 20);
		jpfPass.setFont(new Font("Arial", 1, 15));
		
		jpright.add(jbAddStudent);
		jbAddStudent.setBounds(10, 140, x/9 - 20 , 20);
		jbAddStudent.setFont(new Font("Arial", 1, 15));
		
		jpright.add(jbChange);
		jbChange.setBounds(x/9 , 140, x/9 - 20, 20);
		jbChange.setFont(new Font("Arial", 1, 15));
		
		jpright.add(jbDeleteStudent);
		jbDeleteStudent.setBounds(2*x/9 - 10 , 140, x/9 - 20, 20);
		jbDeleteStudent.setFont(new Font("Arial", 1, 15));
		
		
		
		
	
		add(jpCenter);
		jpCenter.setLayout(null);
		jpCenter.setBackground(Color.WHITE);
		jpCenter.setBounds(x/ 3, y/8, x /3 - 10,y/3);
		
		jpCenter.add(jlName);
		jlName.setFont(new Font("Arial", 1, 14));
		jlName.setBounds(10, 60, x/10, 20);
		
		jpCenter.add(jtfName);
		jtfName.setFont(new Font("Arial", 1, 14));
		jtfName.setBounds( x/10 + 10, 60, x/5, 20);

		jpCenter.add(jlGtinh);
		jlGtinh.setBounds(10, 100, x/10, 30);
		jlGtinh.setFont(new Font("Arial", 1, 13));
//		jlGtinh.setIcon(new ImageIcon("icon/add/gt.png"));
		
		jpCenter.add(jcbSex);
		jcbSex.setBounds( x/10 + 10, 100, x/5, 20);
		jcbSex.setFont(new Font("Arial", 1, 13));
		
		jpCenter.add(jlNgaysinh);
		jlNgaysinh.setBounds(10, 140, x/10, 20);
		jlNgaysinh.setFont(new Font("Arial", 1, 14));
//		jlNgaysinh.setIcon(new ImageIcon("icon/add/date.png"));
		
		jpCenter.add(jcbDay);
		jcbDay.setBounds( x/10 + 10, 140, x/15 - 5, 20);
		jcbDay.setFont(new Font("Arial", 1, 13));
		
		jpCenter.add(jcbMonth);
		jcbMonth.setBounds( 1*x/6 + 10 , 140, x/15 - 5, 20);
		jcbMonth.setFont(new Font("Arial", 1, 13));
		
		jpCenter.add(jcbYear);
		jcbYear.setBounds( 7*x/30 + 10, 140, x/15 -5, 20);
		jcbYear.setFont(new Font("Arial", 1, 13));
		
		jpCenter.add(jlQueQuan);
		jlQueQuan.setBounds(10, 180, x/10, 20);
		jlQueQuan.setFont(new Font("Arial", 1, 14));
//		jlQueQuan.setIcon(new ImageIcon("icon/add/address.png"));
		
		jpCenter.add(jtfQueQuan);
		jtfQueQuan.setBounds( x/10 + 10, 180, x/5, 20);
		jtfQueQuan.setFont(new Font("Arial", 1, 14));
		
		jpCenter.add(jlEmail);
		jlEmail.setBounds(10, 220, x/10, 20);
		jlEmail.setFont(new Font("Arial", 1, 14));
//		jlEmail.setIcon(new ImageIcon("icon/add/mail.png"));
		
		jpCenter.add(jtfEmail);
		jtfEmail.setBounds( x/10 + 10, 220, x/5, 20);
		jtfEmail.setFont(new Font("Arial", 1, 14));
		
		loadStudentTable();
		newTable();
		
		jbAddStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("\\d*");
			    Matcher matcher = pattern.matcher(jtfId.getText());
		        if(jtfId.getText().length() == 0 || jtfName.getText().length() == 0) {
		        	JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin!");
		        }
		        else if(matcher.matches()) {
		        	StudentObject student = new StudentObject();
		        	AccountObject account = new AccountObject();
		        	
		        	if(studentControl.checkIdStudent(student.getIdStudent()) || accountControl.checkAccount(student.getIdStudent())){
		        		JOptionPane.showMessageDialog(null, "Id da co!");
		        	}
		        	else if(jcbCourse.getSelectedIndex() == 0) {
		        		JOptionPane.showMessageDialog(null, "Vui long chon course!");
		        	}
		        	else if(jcbSpecialized.getSelectedIndex() == 0) {
		        		JOptionPane.showMessageDialog(null, "Vui long chon Specialized!");
		        	}
		        	else if(jcbClass.getSelectedIndex() == 0) {
		        		JOptionPane.showMessageDialog(null, "Vui long chon Class!");
		        	}
		        	else {
		        		student.setIdStudent(Integer.parseInt(jtfId.getText()));
			        	student.setName(jtfName.getText());
			        	student.setCourse((String)jcbCourse.getSelectedItem());
			        	student.setSpecialized((String)jcbSpecialized.getSelectedItem());
			        	student.setClassSt((String)jcbClass.getSelectedItem());
			        	student.setEducate(jcbEducate.getSelectedIndex()+1);
			        	
			        	account = accountControl.loadAccountFromStudent(student);
			        	
		        		studentControl.addStudent(student);
		        		accountControl.addAccount(account);
		        		loadStudentTable();
		        	}
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "ID phai la so!");
		        }
			}
		});
		
		jbChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("\\d*");
			    Matcher matcher = pattern.matcher(jtfId.getText());
			    
				int choose = jtbStudentManage.getSelectedRow();
				jtfId.setText((String)jtbStudentManage.getValueAt(choose, 1));
				jtfName.setText((String)jtbStudentManage.getValueAt(choose, 2));
				int id =Integer.parseInt((String)jtbStudentManage.getValueAt(choose, 1));
				StudentObject student  = studentControl.loadStudent(id);
				
				jbChange.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(jtfId.getText().length() == 0 || jtfName.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "vui long nhap day du thong tin!");
						}
						else if(!matcher.matches()) {
							JOptionPane.showMessageDialog(null, "ID phai la so!");
						}
						else if(jcbCourse.getSelectedIndex() == 0) {
			        		JOptionPane.showMessageDialog(null, "Vui long chon course!");
			        	}
			        	else if(jcbSpecialized.getSelectedIndex() == 0) {
			        		JOptionPane.showMessageDialog(null, "Vui long chon Specialized!");
			        	}
			        	else if(jcbClass.getSelectedIndex() == 0) {
			        		JOptionPane.showMessageDialog(null, "Vui long chon Class!");
			        	}
			        	else {
			        		student.setIdStudent(Integer.parseInt(jtfId.getText()));
							student.setName(jtfName.getText());
							student.setCourse((String)jcbCourse.getSelectedItem());
							student.setSpecialized((String)jcbSpecialized.getSelectedItem());
							student.setClassSt((String)jcbClass.getSelectedItem());
							student.setEducate(jcbEducate.getSelectedIndex() + 1);
			        		studentControl.changeInfStudent(student);
			        		loadStudentTable();
			        	}
					}
				});
			}
		});
		
		jbDeleteStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int chooseRow = jtbStudentManage.getSelectedRow();
				if(chooseRow >= 0) {
					int ans = JOptionPane.showConfirmDialog(null, "Are you sure!", "question", JOptionPane.YES_NO_OPTION);
					if(ans == JOptionPane.YES_OPTION) {
						int id = Integer.parseInt((String)studentModel.getValueAt(chooseRow, 1));
						studentControl.deleteStudent(id);
						accountControl.deleteAccount(id);
						loadStudentTable();
					}
				}
			}
		});
		
		jcbNganh.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				changeClassWithSpecialized(jcbNganh, lopModel);
				loadStudentTable();
			}
		});
		
		jcbKhoa.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				loadStudentTable();
			}
		});
		
		jcbLop.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				loadStudentTable();
			}
		});
		
		jcbSpecialized.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				changeClassWithSpecialized(jcbSpecialized, classModel);
			}
		});
	}

	public void loadStudentTable() {
		studentModel.setRowCount(0);
		count = 1;
		String nganh, khoa, lop, s;
		
		if(jcbNganh.getSelectedIndex() == 0 ) {
			nganh ="Select specialized from student";
		}
		else {
			nganh ="Select specialized from student where specialized = '" + (String)jcbNganh.getSelectedItem() + "'";
		}
		
		if(jcbKhoa.getSelectedIndex() == 0 ) {
			khoa ="Select course from student";
		}
		else {
			khoa ="Select course from student where course = '" + (String)jcbKhoa.getSelectedItem() + "'";
		}
		
		if(jcbLop.getSelectedIndex() == 0 ) {
			lop ="Select class from student";
		}
		else {
			lop ="Select class from student where class = '" + (String)jcbLop.getSelectedItem() + "'";
		}
		
		s = "Select * from student where specialized in (" + nganh + ") and course in (" + khoa + ") and class in (" + lop + ")";
		resultSet = connectDatabase.returnData(s);
		try {
			while(resultSet.next()) {
				StudentObject student = studentControl.loadStudent(resultSet.getInt(1));
				String A[] = {"" + count, "" + student.getIdStudent(), student.getName(), 
						student.getClassSt(), student.getBirthday(), student.getAddress(), student.getEmail(), "Dang hoc"};
	        	studentModel.addRow(A);
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  newTable() {
		jcbNganh.setSelectedIndex(0);
		jcbKhoa.setSelectedIndex(0);
		jcbCourse.setSelectedIndex(0);
		jcbSpecialized.setSelectedIndex(0);
		jcbClass.setSelectedIndex(0);
		jcbEducate.setSelectedIndex(0);
		loadStudentTable();
	}
	
	public void addDataTest() {
		for(int i = 20140000; i<20141000; i++) {
			
		}
		
		for(int i = 20140000; i<20141000; i++) {
			
		}
		
		for(int i = 20150000; i<20151000; i++) {
			
		}
		
		for(int i = 20160000; i<20141000; i++) {
			
		}
		
		for(int i = 20170000; i<20141000; i++) {
			
		}
	}
	
	public void changeClassWithSpecialized(JComboBox jcbBox,DefaultComboBoxModel model) {
		model.removeAllElements();
		switch (jcbBox.getSelectedIndex()) {
			case 1:
				for(int i = 0; i<vietnhatclass.length; i++) {
					model.addElement(vietnhatclass[i]);
				}
				break;
			case 2:
				for(int i = 0; i<phongdtdh.length; i++) {
					model.addElement(phongdtdh[i]);
				}
				break;
			case 3:
				for(int i = 0; i<tainangclass.length; i++) {
					model.addElement(tainangclass[i]);
				}
				break;
			case 4:
				for(int i = 0; i<sinhhocthucphamclass.length; i++) {
					model.addElement(sinhhocthucphamclass[i]);
				}
				break;
			case 5:
				for(int i = 0; i<cnttclass.length; i++) {
					model.addElement(cnttclass[i]);
				}
				break;
			case 6:
				for(int i = 0; i<cokhiclass.length; i++) {
					model.addElement(cokhiclass[i]);
				}
				break;
			case 7:
				for(int i = 0; i<cokhidonglucclass.length; i++) {
					model.addElement(cokhidonglucclass[i]);
				}
				break;
			case 8:
				for(int i = 0; i<detmayclass.length; i++) {
					model.addElement(detmayclass[i]);
				}
				break;
			case 9:
				for(int i = 0; i<daotaolientucclass.length; i++) {
					model.addElement(daotaolientucclass[i]);
				}
				break;
			case 10:
				for(int i = 0; i<dienclass.length; i++) {
					model.addElement(dienclass[i]);
				}
				break;
			case 11:
				for(int i = 0; i<dientuvienthongclass.length; i++) {
					model.addElement(dientuvienthongclass[i]);
				}
				break;
			case 12:
				for(int i = 0; i<kinhtequanlyclass.length; i++) {
					model.addElement(kinhtequanlyclass[i]);
				}
				break;
			case 13:
				for(int i = 0; i<kithuathoahocclass.length; i++) {
					model.addElement(kithuathoahocclass[i]);
				}
				break;
			case 14:
				for(int i = 0; i<kithuathatnhanclass.length; i++) {
					model.addElement(kithuathatnhanclass[i]);
				}
				break;
			case 15:
				for(int i = 0; i<congnghemoitruongclass.length; i++) {
					model.addElement(congnghemoitruongclass[i]);
				}
				break;
			case 16:
				for(int i = 0; i<kithuatvatlieuclass.length; i++) {
					model.addElement(kithuatvatlieuclass[i]);
				}
				break;
			case 17:
				for(int i = 0; i<ngoainguclass.length; i++) {
					model.addElement(ngoainguclass[i]);
				}
				break;
			case 18:
				for(int i = 0; i<suphamkithuatclass.length; i++) {
					model.addElement(suphamkithuatclass[i]);
				}
				break;
			case 19:
				for(int i = 0; i<toantinclass.length; i++) {
					model.addElement(toantinclass[i]);
				}
				break;
			case 20:
				for(int i = 0; i<vatlykithuatclass.length; i++) {
					model.addElement(vatlykithuatclass[i]);
				}
				break;
			default:
				model.addElement(value[0]);
				break;
		}
	}
}
