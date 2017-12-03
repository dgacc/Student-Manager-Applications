package display;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
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
	private JLabel jlTitle, jlName, jlId, jlClass, jlSpecialized, jlMax, jlSum, jlNote;
	private JTextField jtfDangki;
	private JSplitPane jspMain;
	private JPanel jpLeft, jpRight;
	private JButton jbDangki, jbClass, jbDelete, jbDeleteAll, jbSend, jb;
	private JTable jtbRegistration, jtbTimeTable;
	private DefaultTableModel registrationModel, timeTableModel;
	private JScrollPane jcpDangki, jcpLichhoc;
	private String dangkiColumn[] = {"Ma lop", "Ten lop", "Ma hoc phan", "TT lop", "TT Dk", "TC"};
	private String dangkiRow[][] = {};
	private String lichhocColumn[] = {"Thu", "Thoi Gian", "Phong hoc", "Lop"};
	private String lichhocRow[][] = {};
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	RegistrationControl registrationControl;
	StudentControl studentControl;
	SubjectControl subjectControl;
	ClassListForRegistration jpClass;
	int sum = 0;
	final int MAX = 24;
	
	public Registration() {
		jlTitle = new JLabel("Registration for 20171");
		jlId = new JLabel("Ma so sinh vien:");
		jlName = new JLabel("Ho va ten:");
		jlClass = new JLabel("Lop:");
		jlSpecialized = new JLabel("Chuong trinh:", SwingConstants.LEFT);
		jlMax = new JLabel("So tin chi dang ki toi da: " + MAX);
		jlSum = new JLabel("Tong so TC dang ki:");
		jlNote = new JLabel();
		jlNote.setForeground(Color.RED);
		jtfDangki = new JTextField();
		
		jpLeft = new JPanel();
		jpRight = new JPanel();
		jpClass = new ClassListForRegistration();
		jspMain = new JSplitPane();
		
		jbDangki = new JButton("Dang ki");
		jbClass = new JButton("thong tin danh sach lop mo");
		jbDelete = new JButton("Xoa lop");
		jbDeleteAll = new JButton("Xoa tat ca");
		jbSend = new JButton("Gui dang ki");
		
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
		jtbTimeTable = new JTable(timeTableModel);
		jtbTimeTable.setFont(new Font("Arial", 1, 12));
		
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

		add(jspMain);
		jspMain.setBounds(0, 0, x, y);
		jspMain.setDividerLocation(x/4);
		
		jspMain.setLeftComponent(jpLeft);
		jpLeft.setSize(x/4, y);
		jpLeft.setLayout(null);
		
		jpLeft.add(jlId);
		jlId.setFont(new Font("Arial", 1, jpLeft.getWidth()/25));
		jlId.setBounds(10, 50, jpLeft.getWidth()*3/4, 30);
		
		jpLeft.add(jlName);
		jlName.setFont(new Font("Arial", 1, jpLeft.getWidth()/25));
		jlName.setBounds(10, 100, jpLeft.getWidth()*3/4, 30);
		
		jpLeft.add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1, jpLeft.getWidth()/25));
		jlSpecialized.setBounds(10, 150, jpLeft.getWidth() - 20, 30);
		
		jpLeft.add(jlClass);
		jlClass.setFont(new Font("Arial", 1, jpLeft.getWidth()/25));
		jlClass.setBounds(10, 200, jpLeft.getWidth()*3/4, 30);
		
		jpLeft.add(jlMax);
		jlMax.setFont(new Font("Arial", 1, jpLeft.getWidth()/25));
		jlMax.setBounds(10, 250, jpLeft.getWidth()*3/4, 30);
		
		jpLeft.add(jbClass);
		jbClass.setBounds(50, 300, jpLeft.getWidth()-100, 20);
		
		jspMain.setRightComponent(jpRight);
		jpRight.setLayout(null);
		runJpRight(x*3/4, y);
		
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
		
		jspMain.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				int dividerLocation = jspMain.getDividerLocation();
				if(dividerLocation > x/2) {
					dividerLocation = x/2;
					jspMain.setDividerLocation(dividerLocation);
				}
				if(dividerLocation < x/4) {
					dividerLocation = x/4;
					jspMain.setDividerLocation(dividerLocation);
				}
				runJpRight(x-dividerLocation, y);
			}
		});
	}
	
	public void loadRegistrationTable(StudentObject student) {
		jlId.setText("Ma so sinh vien: " + student.getIdStudent());
		jlName.setText("Ho va ten: " + student.getName());
		jlClass.setText("Lop: " + student.getClassSt());
		jlSpecialized.setText("Vien: " + student.getSpecialized());
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
				jlSum.setText("Tong so TC dang ki: " + sum);
				jtfDangki.setText(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runJpRight(int x, int y) {
		jpRight.setSize(x, y);
		
		jpRight.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, jpRight.getWidth()/35));
		jlTitle.setBounds(jpRight.getWidth()/3, 0, jpRight.getWidth()/3, 30);
		
		jpRight.add(jtfDangki);
		jtfDangki.setBounds(10, 50, 100, 30);
		jtfDangki.setFont(new Font("Arial", 1, 12));
		
		jpRight.add(jbDangki);
		jbDangki.setBounds(120, 50, 100, 30);
		jbDangki.setFont(new Font("Arial", 1, 15));
		
		jpRight.add(jlNote);
		jlNote.setBounds(230, 50, 200, 30);
		jlNote.setFont(new Font("Arial", 1, 12));
		
		jpRight.add(jcpDangki);
		jcpDangki.setBounds(0, 100, jpRight.getWidth(), jpRight.getHeight()/3);
		
		jpRight.add(jlSum);
		jlSum.setFont(new Font("Arial", 1, 15));
		jlSum.setBounds(jpRight.getWidth()- 200, jpRight.getHeight()/3 + 100, 200, 30);
		
		jpRight.add(jbSend);
		jbSend.setBounds(jpRight.getWidth()*2/5, jpRight.getHeight()/3 + 150, jpRight.getWidth()/5, 30);
		
		jpRight.add(jbDelete);
		jbDelete.setBounds(jpRight.getWidth()- 220, jpRight.getHeight()/3 + 150, 100, 30);
		
		jpRight.add(jbDeleteAll);
		jbDeleteAll.setBounds(jpRight.getWidth()- 110, jpRight.getHeight()/3 + 150, 100, 30);
		
		jpRight.add(jcpLichhoc);
		jcpLichhoc.setBounds(0, jpRight.getHeight()/3 + 200, jpRight.getWidth(), y - jpRight.getHeight()/3 - 200);
	}
}
