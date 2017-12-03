package display;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.*;
import control.*;

public class StudentList extends JPanel{
	ConnectDatabase connectDatabase;
	ResultSet resultSet;
	StudentClassObject stClass;
	StudentClassControl studentClassControl;
	StudentControl studentControl;
	private JLabel jlTitle;
	private JTextField jtfIdStudent, jtfSearch;
	private JButton jbBack, jbAddStudent, jbDeleteStudent, jbSearch;
	private JTable jtbStudentListTable;
	private DefaultTableModel studentListModel;
	private JScrollPane jcpStudentList;
	private String column[] = {"STT", "IdClass", "IdStudent", "Name", "QT", "HP", "KQ"};
	private String row[][] = {{}};
	int count = 1;
	public StudentList() {
		connectDatabase = new ConnectDatabase();
		stClass = new StudentClassObject();
		studentClassControl = new StudentClassControl();
		studentControl = new StudentControl();
		
		jbBack = new JButton("Back");
		jbAddStudent = new JButton("Add Student");
		jbDeleteStudent = new JButton("Delete Student");
		jbSearch = new JButton("Search");
		
		jlTitle = new JLabel("Danh sach sinh vien lop");
		jtfIdStudent = new JTextField();
		jtfSearch = new JTextField();
		
		studentListModel = new DefaultTableModel(row, column){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		jtbStudentListTable = new JTable(studentListModel);
		jtbStudentListTable.setFont(new Font("Arial", 1, 12));
		
		jcpStudentList = new JScrollPane(jtbStudentListTable);
	}
	
	public void runStudentList(int x, int y, Admin admin) {
		setSize(x, y);
		setLayout(null);
		
		add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/40));
		jlTitle.setBounds(x/3, 0, x/3, 30);
		
		add(jbBack);
		jbBack.setBounds(10, 50, x/9 - 10, 20);
		
		add(jtfSearch);
		jtfSearch.setFont(new Font("Arial", 1, 12));
		jtfSearch.setBounds(x*5/8, 50, x/4 - 10, 20);
		
		add(jbSearch);
		jbSearch.setBounds(x*7/8, 50, x/8 - 10, 20);
		
		add(jtfIdStudent);
		jtfIdStudent.setBounds(x/8, 80, x/4 - 10 , 20);
		
		add(jbAddStudent);
		jbAddStudent.setBounds(x*3/8, 80, x/8, 20);
		
		add(jbDeleteStudent);
		jbDeleteStudent.setBounds(x/2+10, 80, x/8, 20);
		
		add(jcpStudentList);
		jcpStudentList.setBounds(0, 110, x, y-90);
		
		loadStudentList();
		
		jbAddStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stClass.getIdClass() != 0) {
					add();
					loadStudentList();
				}
			}
		});
		
		jbDeleteStudent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbStudentListTable.getSelectedRow();
				if(choose >= 0) {
					
					int idStudent = Integer.parseInt((String)jtbStudentListTable.getValueAt(choose, 2));
					studentClassControl.delteteStudentFromClass(stClass.getIdClass(), idStudent);
					loadStudentList();
				}
			}
		});
		
		jbBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				admin.card.show(admin.getJpRight(), "ClassManage");
			}
		});
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadStudentList();
			}
		});
	}
	
	public void loadStudentList() {
		jlTitle.setText("DS sinh vien lop " + stClass.getIdClass());
		studentListModel.setRowCount(0);
		count = 1;
		String load, idSearch;
		
		if(jtfSearch.getText().length() == 0) {
			idSearch = "select idStudent from studentlist where idClass = " + stClass.getIdClass();
		}
		else {
			Pattern pattern = Pattern.compile("\\d*");
		    Matcher idSearchTest = pattern.matcher(jtfSearch.getText());
		    
		    if(idSearchTest.matches()) {
		    	int idStudent = Integer.parseInt((String)jtfSearch.getText());
		    	idSearch = "select idStudent from studentlist where idClass = " + stClass.getIdClass() + " and idStudent = " + jtfSearch.getText();
		    }
		    else {
		    	idSearch = "select idStudent from studentlist where idClass = " + stClass.getIdClass();
		    	JOptionPane.showMessageDialog(null, "Id phai la so!");
		    }
		}
		
		load = "select * from studentlist where idClass = " + stClass.getIdClass() + " and idStudent in (" + idSearch + ")";
		ResultSet resultSet = connectDatabase.returnData(load);
		
		try {
			while(resultSet.next()) {
				String A[] = {"" + count, "" + stClass.getIdClass(), "" + resultSet.getInt(2), resultSet.getString(3), null, null, null};
				studentListModel.addRow(A);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add() {
		Pattern pattern = Pattern.compile("\\d*");
	    Matcher idStudentTest = pattern.matcher(jtfIdStudent.getText());
	    
	    if(jtfIdStudent.getText().length() != 0) {
	    	if(idStudentTest.matches()) {
	    		int id = Integer.parseInt((String)jtfIdStudent.getText()); 
	    		if(studentControl.checkIdStudent(id)) {
	    			StudentObject student = studentControl.loadStudent(id);
	    			studentClassControl.insertStudent(stClass, student);
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null, "Sinh vien co id:" + id + " khong ton tai!Vui long nhap lai!");
	    		}
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, "Vui long nhap Id la so!");
	    	}
	    }
	}

	public StudentClassObject getStClass() {
		return stClass;
	}

	public void setStClass(StudentClassObject stClass) {
		this.stClass = stClass;
	}
	
	public JTextField getJtfIdStudent() {
		return jtfIdStudent;
	}
	
	public JTextField getJtfSearch() {
		return jtfSearch;
	}
}
