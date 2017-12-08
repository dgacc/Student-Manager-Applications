package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Connect.ConnectDatabase;
import control.StudentClassControl;
import object.StudentClassObject;

public class ClassListForRegistration extends JPanel{
	private JPanel jpHeader;
	private JLabel jlTitle;
	private JTable jtbClassList;
	private DefaultTableModel classModel;
	private JTextField jtfSearch;
	private JTableHeader classListHeader;
	private JButton jbSearch;
	private String listColumn[] = {"STT", "IdCLass", "idSubject", "NameClass", "Day", "Address", "Time", "Number"};
	private String listRow[][] = {};
	private JScrollPane jcpList;
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	int count = 1;
	public ClassListForRegistration() {
		
		
		jpHeader = new JPanel();
		jlTitle = new JLabel("Class List For Registration");
		
		classModel = new DefaultTableModel(listRow, listColumn);
		jtbClassList = new JTable(classModel);
		jcpList = new JScrollPane(jtbClassList);
		
		jtfSearch = new JTextField();
		jbSearch = new JButton("Search");
		
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		
		classListHeader = jtbClassList.getTableHeader();
		classListHeader.setFont(new Font("Arial", 1, 15));
		classListHeader.setBackground(new Color(0, 170, 207));
		classListHeader.setForeground(Color.WHITE);
		
		
		
	}
	
	public void runClassListForRegistration(int x,int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jcpList);
		jcpList.setBounds(30, y/8, x- 60, 6*y/7 - 10);
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/7);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(0, 10, x, y/25);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		jpHeader.add(jtfSearch);
		jtfSearch.setFont(new Font("Arial", 1, 13));
		jtfSearch.setBounds( 5*x/ 8,50, x/8, 20);
		
		jpHeader.add(jbSearch);
		jbSearch.setFont(new Font("Arial", 1, 13));
		jbSearch.setBounds( 6*x/8 + 10, 50, x/12, 20);
		jbSearch.setForeground(Color.WHITE);
		jbSearch.setBackground(new Color(66, 103, 178));
		
		
//		add(jcpList);
//		jcpList.setBounds(0, 80, x, y-100);
		
		loadClassTable();
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadClassTable();
			}
		});
	}
	
	public void loadClassTable() {
		classModel.setRowCount(0);
		count = 1;
		String s, idSearch;
		
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
		
		s = "Select * from studentclass where educate = 'he dao tao theo TC' and idClass in (" + idSearch + ")";
		ResultSet resultSet = connectDatabase.returnData(s);
		try {
			
			while(resultSet.next()) {
				StudentClassObject stClass = studentClassControl.loadClass(resultSet.getInt(1));
				String B[] = {"" + count, "" + stClass.getIdClass(), ""+stClass.getIdSubject(), stClass.getNameClass(), stClass.getDay(),
							stClass.getAddress(), stClass.getTime(), ""+stClass.getNumber()};
				classModel.addRow(B);
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
