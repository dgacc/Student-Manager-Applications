package display;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDatabase;
import object.*;
import control.*;

public class ClassManage extends JPanel{
	private JPanel jpHeader = new JPanel();
	private JLabel jlTitle, jlEducate, jlIdClass, jlIdSubject, jlNameTeacher, jlAdress, jlTime, jlNote, jlNameClass, jlNumber;
	private JTable jtbStudentManagement;
	private JTextField jtfSearch, jtfIdClass, jtfNameClass, jtfIdSubject, jtfNameTeacher, jtfNote, jtfNumber;
	private JButton jbAdd, jbChange, jbDelete, jbList, jbSearch;
	private JScrollPane jcpPane;
	public DefaultTableModel classModel;
	private JComboBox jcbEducate, jcbHdt, jcbDay, jcbBuilding, jcbFloors, jcbRoom, jcbBegin, jcbEnd, jcbNumber;
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	SubjectControl subjectControl;
	private String columnClass[] = {"STT", "IdClass", "NameClass", "nameTeacher", "Number", "Note"};
	private String columnClassTC[] = {"STT", "IdClass", "IdSubject", "NameClass", "nameTeacher", "Day", "Adress", "Time", "Number", "Note"};
	private String rowClass[][] = {{}};
	private String educateValue[] = {"All", "he bien che", "he dao tao theo TC"};
	private String dayValue[] = {"Thu 2", "Thu 3", "Thu 4", "Thu 5", "Thu 6", "Thu 7"};
	private String buildingValue[] = {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "D3", "D5", "D6", "D7", "D8", "D9", "T", "TC"};
	private String floorsValue[] = {"1", "2", "3", "4", "5"};
	private String roomValue[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private String timeBegin[] = {"06h45", "07h35", "08h30", "09h20", "10h15", "11h00", "12h30", "13h30", "14h15", "15h05", "16h00", "16h45"};
	private String timeEnd[] = {"07h30", "08h25", "09h15", "10h05", "11h00", "11h50", "13h20", "14h05", "15h00", "15h55", "16h45", "17h35"};
	int count = 1;
	public ClassManage() {
		jlTitle = new JLabel("Class Manage");
		jlIdClass = new JLabel("IdClass:");
		jlEducate = new JLabel("Educate:");
		jlIdSubject = new JLabel("IdSubject:");
		jlNameTeacher = new JLabel("Tearcher:");
		jlAdress = new JLabel("Address:");
		jlTime = new JLabel("Time:");
		jlNote = new JLabel("Note");
		jlNameClass = new JLabel("NameClass:");
		jlNumber = new JLabel("Number:");
		
		jtfSearch = new JTextField();
		jtfIdClass = new JTextField();
		jtfIdSubject = new JTextField();
		jtfNameTeacher = new JTextField();
		jtfNote = new JTextField();
		jtfNameClass = new JTextField();
		jtfNumber = new JTextField();
		
		jbAdd = new JButton("Add");
		jbList = new JButton("List Student");
		jbChange = new JButton("Change");
		jbDelete = new JButton("Delete");
		jbSearch = new JButton("Search");
		
		classModel = new DefaultTableModel(rowClass, columnClass){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		jtbStudentManagement = new JTable(classModel);
		jtbStudentManagement.setFont(new Font("Arial", 1, 12));

		jcpPane = new JScrollPane(jtbStudentManagement);
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		subjectControl = new SubjectControl();
		
		jcbEducate = new JComboBox(educateValue);
		jcbHdt = new JComboBox(educateValue);
		jcbDay = new JComboBox(dayValue);
		jcbBuilding = new JComboBox(buildingValue);
		jcbFloors = new JComboBox(floorsValue);
		jcbRoom = new JComboBox(roomValue);
		jcbBegin = new JComboBox(timeBegin);
		jcbEnd = new JComboBox(timeEnd);
	}
	
	public void runClassManage(int x, int y, Admin admin) {
		setSize(x, y);
		setLayout(null);
		
		add(jcpPane);
		jcpPane.setBounds(0, 80, x, y-330);
		
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/7);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(0, 10, x, y/25);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		

		jpHeader.add(jbSearch);
		jbSearch.setFont(new Font("Arial", 1, 13));
		jbSearch.setBounds( 6*x/8 + 10, 50, x/10, 20);
		jbSearch.setForeground(Color.WHITE);
		jbSearch.setBackground(new Color(66, 103, 178));
		
		
		jpHeader.add(jtfSearch);
		jtfSearch.setFont(new Font("Arial", 1, 13));
		jtfSearch.setBounds( 5*x/ 8,50, x/8, 20);	
		
		
		jpHeader.add(jcbEducate);
		jcbEducate.setBounds(10, 50, 200, 20);
		
//		add(jcbEducate);
//		jcbEducate.setBounds(10, 50, 150, 20);
//		
	
		
//		add(jbSearch);
//		jbSearch.setFont(new Font("Arial", 1, 15));
//		jbSearch.setBounds(x*5/6, 50, 150, 20);
//		
//		add(jtfSearch);
//		jtfSearch.setBounds(x*2/3, 50, x/6, 20);
		
		add(jlIdClass);
		jlIdClass.setFont(new Font("Arial", 1, 14));
		jlIdClass.setBounds(0, y-240, x/9, 30);
		
		add(jtfIdClass);
		jtfIdClass.setFont(new Font("Arial", 1, 14));
		jtfIdClass.setBounds(x/9, y-240, x*2/9 -10, 30);
		
		add(jlEducate);
		jlEducate.setFont(new Font("Arial", 1, 14));
		jlEducate.setBounds(0 , y-190, x/9, 30);
		
		add(jcbHdt);
		jcbHdt.setBounds(x/9 , y-190, x*2/9 - 10, 30);
		
		add(jlIdSubject);
		jlIdSubject.setFont(new Font("Arial", 1, 14));
		jlIdSubject.setBounds(0, y-140, x/9, 30);
		
		add(jtfIdSubject);
		jtfIdSubject.setFont(new Font("Arial", 1, 14));
		jtfIdSubject.setBounds(x/9, y-140, x*2/9 - 10, 30);
		
		add(jlNameClass);
		jlNameClass.setFont(new Font("Arial", 1, 14));
		jlNameClass.setBounds(x/3, y-240, x/9, 30);
		
		add(jtfNameClass);
		jtfNameClass.setFont(new Font("Arial", 1, 14));
		jtfNameClass.setBounds(x*4/9, y-240, x*2/9, 30);
		
		add(jlNameTeacher);
		jlNameTeacher.setFont(new Font("Arial", 1, 14));
		jlNameTeacher.setBounds(x/3, y-190, x/9, 30);
		
		add(jtfNameTeacher);
		jtfNameTeacher.setFont(new Font("Arial", 1, 14));
		jtfNameTeacher.setBounds(x*4/9, y-190, x*2/9, 30);
		
		add(jlAdress);
		jlAdress.setFont(new Font("Arial", 1, 14));
		jlAdress.setBounds(x/3, y-140, x/9, 30);
		
		add(jcbBuilding);
		jcbBuilding.setBounds(x*4/9, y-140, x*2/27 -10, 30);
		
		add(jcbFloors);
		jcbFloors.setBounds(x*4/9 + x*2/27, y-140, x*2/27 -10, 30);
		
		add(jcbRoom);
		jcbRoom.setBounds(x*4/9 + x*4/27, y-140, x*2/27, 30);
		
		add(jlTime);
		jlTime.setFont(new Font("Arial", 1, 14));
		jlTime.setBounds(x*2/3 + 10, y-240, x/9, 30);
		
		add(jcbDay);
		jcbDay.setBounds(x*7/9 + 10, y-240, x*2/27 - 5, 30);
		
		add(jcbBegin);
		jcbBegin.setBounds(x*7/9 + x*2/27 + 10, y-240, x*2/27 - 10, 30);
		
		add(jcbEnd);
		jcbEnd.setBounds(x*7/9 + x*4/27 + 5, y-240, x*2/27 - 15, 30);
		
		add(jlNumber);
		jlNumber.setFont(new Font("Arial", 1, 14));
		jlNumber.setBounds(x*2/3 + 10, y-190, x/9, 30);
		
		add(jtfNumber);
		jtfNumber.setFont(new Font("Arial", 1, 14));
		jtfNumber.setBounds(x*7/9 + 10, y-190, x*2/9 -20, 30);
		
		add(jlNote);
		jlNote.setFont(new Font("Arial", 1, 14));
		jlNote.setBounds(x*2/3 + 10, y-140, x/9, 30);
		
		add(jtfNote);
		jtfNote.setFont(new Font("Arial", 1, 14));
		jtfNote.setBounds(x*7/9 + 10, y-140, x*2/9 -20, 30);
		
		add(jbAdd);
		jbAdd.setBounds(x/6, y - 80, x/6-30, 30);
		
		add(jbChange);
		jbChange.setBounds(x*2/6 + 10, y - 80, x/6 -20, 30);
		
		add(jbDelete);
		jbDelete.setBounds(x*3/6 + 20, y - 80, x/6 -10, 30);
		
		add(jbList);
		jbList.setBounds(x*4/6 + 20, y - 80, x/6, 30);
		
		loadClassTable();
		
		jcbEducate.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				int educate = jcbEducate.getSelectedIndex();
				if(educate == 1 || educate == 0) {
					classModel.setColumnIdentifiers(columnClass);
				}
				else {
					classModel.setColumnIdentifiers(columnClassTC);
				}
				loadClassTable();
			}
		});
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadClassTable();
			}
		});
		
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
				loadClassTable();
			}
		});
		
		jbChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbStudentManagement.getSelectedRow();
				if(choose >= 0) {
					int idClass = Integer.parseInt((String)jtbStudentManagement.getValueAt(choose, 1));
					studentClassControl.deleteClass(idClass);;
					loadClassTable();
				}
			}
		});
		
		jbList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbStudentManagement.getSelectedRow();
				if(choose >= 0) {
					int idClass = Integer.parseInt((String)jtbStudentManagement.getValueAt(choose, 1));
					admin.studentList.setStClass(studentClassControl.loadClass(idClass));
					admin.studentList.getJtfIdStudent().setText(null);
					admin.studentList.getJtfSearch().setText(null);
					admin.studentList.loadStudentList();
					admin.card.show(admin.getJpRight(), "StudentList");
				}
			}
		});
	}
	
	public void loadClassTable() {
		classModel.setRowCount(0);
		count = 1;
		String s, educate, idSearch;
		
		if(jcbEducate.getSelectedIndex() == 0 ) {
			educate = "Select educate from studentclass";
		}
		else {
			educate ="Select educate from studentclass where educate = '" + (String)jcbEducate.getSelectedItem() + "'";
		}
		
		if(jtfSearch.getText().length() == 0) {
			idSearch = "select idClass from studentclass"; 
		}
		else {
			Pattern pattern = Pattern.compile("\\d*");
		    Matcher matcher = pattern.matcher(jtfSearch.getText());
		    if(matcher.matches()) {
		    	int id = Integer.parseInt((String)jtfSearch.getText());
		    	idSearch = "select idClass from studentclass where idClass = " + id;
		    }
		    else {
		    	idSearch = "select idClass from studentclass"; 
		    	JOptionPane.showMessageDialog(null, "Vui long nhap Id la so!");
		    }
		}
		
		s = "Select * from studentclass where educate in (" + educate + ") and idClass in (" + idSearch + ")";
		ResultSet resultSet = connectDatabase.returnData(s);
		try {
			
			while(resultSet.next()) {
				StudentClassObject stClass = studentClassControl.loadClass(resultSet.getInt(1));
				String A[] = {"" + count, "" + stClass.getIdClass(), stClass.getNameClass(), stClass.getNameTeacher(), ""+stClass.getNumber(), stClass.getNote()};
				String B[] = {"" + count, "" + stClass.getIdClass(), ""+stClass.getIdSubject(), stClass.getNameClass(), stClass.getNameTeacher(), stClass.getDay(),
							stClass.getAddress(), stClass.getTime(), ""+stClass.getNumber(), stClass.getNote()};
				if(jcbEducate.getSelectedIndex() == 0 || jcbEducate.getSelectedIndex() == 1) {
					classModel.addRow(A);
				}
				else {
					classModel.addRow(B);
				}
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add() {
		Pattern pattern = Pattern.compile("\\d*");
	    Matcher idClassTest = pattern.matcher(jtfIdClass.getText());
	    Matcher idSubjectTest = pattern.matcher(jtfIdSubject.getText());
	    Matcher numberTest = pattern.matcher(jtfNumber.getText());
	    StudentClassObject stClass = new StudentClassObject();
	    if(jtfIdClass.getText().length() == 0 || jtfNameTeacher.getText().length() == 0 || jtfNumber.getText().length() == 0) {
	    	JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin tai IdClass, Teachaer, Number!");
	    }
	    else if(idClassTest.matches()) {
	    	int classId = Integer.parseInt((String)jtfIdClass.getText());
	    	if(numberTest.matches()) {
	    		int number = Integer.parseInt((String)jtfNumber.getText());
	    		if(jcbHdt.getSelectedIndex() == 0) {
		    		JOptionPane.showMessageDialog(null, "Vui long chon Educate!");
		    	}
		    	else if(jcbHdt.getSelectedIndex() == 1) {
		    		if(jtfNameClass.getText().length() == 0) {
		    			JOptionPane.showMessageDialog(null, "Vui long nhap NameClass!");
		    		}
		    		else {
		    			stClass.setEducate((String)jcbHdt.getSelectedItem());
			    		stClass.setIdClass(classId);
			    		stClass.setIdSubject(0);
			    		stClass.setNameClass(jtfNameClass.getText());
			    		stClass.setNameTeacher(jtfNameTeacher.getText());
			    		stClass.setNumber(number);
			    		stClass.setAddress(null);
			    		stClass.setTime(null);
			    		if(jtfNote.getText().length() == 0) {
			    			stClass.setNote(null);
			    		}
			    		else {
			    			stClass.setNote(jtfNote.getText());
			    		}
			    		studentClassControl.addClass(stClass);
		    		}
		    	}
		    	else {
		    		String time = (String)jcbBegin.getSelectedItem() + "-" + (String)jcbEnd.getSelectedItem();
		    		int begin = Integer.parseInt(time.substring(0, 2));
		    		int end = Integer.parseInt(time.substring(6, 8));
		    		if(jtfIdSubject.getText().length() == 0) {
		    			JOptionPane.showMessageDialog(null, "Vui long nhap IdSubject!");
		    		}
		    		else if(begin > end) {
		    			JOptionPane.showMessageDialog(null, "Time Error!");
		    		}
		    		else if(idSubjectTest.matches()) {
		    			int subjectId = Integer.parseInt((String)jtfIdSubject.getText());
		    			String address = (String)jcbBuilding.getSelectedItem() + "-" + (String)jcbFloors.getSelectedItem() + "" + (String)jcbRoom.getSelectedItem();
		    			SubjectObject subject = subjectControl.loadSubject(subjectId);
		    			if(subjectControl.checkIdSubject(subjectId)) {
		    				stClass.setEducate((String)jcbHdt.getSelectedItem());
		    				stClass.setIdClass(classId);
		    				stClass.setIdSubject(subject.getIdSubject());
		    				if(jtfNameClass.getText().length() == 0) {
		    					stClass.setNameClass(subject.getName());
		    				}
		    				else {
		    					stClass.setNameClass(jtfNameClass.getText());
		    				}
				    		stClass.setNameTeacher(jtfNameTeacher.getText());
				    		stClass.setDay((String)jcbDay.getSelectedItem());
				    		stClass.setAddress(address);
				    		stClass.setTime(time);
				    		stClass.setNumber(number);
				    		if(jtfNote.getText().length() == 0) {
				    			stClass.setNote(null);
				    		}
				    		else {
				    			stClass.setNote(jtfNote.getText());
				    		}
				    		studentClassControl.addClass(stClass);
		    			}
		    			else {
		    				JOptionPane.showMessageDialog(null, "Mon hoc chua ton tai!Yeu cau them mon hoc voi Id = " + subjectId);
		    			}
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "IdSubject phai la so!Vui long nhap lai!");
		    		}
		    		
		    	}
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Number phai la so!Vui long nhap lai!");
	    	}
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Vui long nhap IdClass la so!");
	    }
	}
}
