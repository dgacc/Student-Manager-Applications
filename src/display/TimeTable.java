package display;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

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

public class TimeTable extends JPanel{
	private JPanel jpHeader;
	private JLabel jlTitle, jlSearch,jlName, jlMssv;
	private JTextField jtfSearch;
	private JButton jbSearch;
	private JTable jtbTimeTable, jtbCharts;
	private JTableHeader timeTableHeader, chartsHeader;
	private DefaultTableModel timeTableModel, chartsModel;
	private JScrollPane jcpTimeTable, jcpCharts;
	private String timeTableColumn[] = {"Day", "Time", "Adress", "IdClass", "IdSubject", "NameClass", "Note"};
	private String timeTableRow[][] = {};
	private String chartsColumn[] = {"","Thu 2", "Thu3", "Thu 4", "Thu 5", "Thu 6", "Thu 7", "CN"};
	private String time[] = {"Tiet 1", "Tiet 2", "Tiet 3", "Tiet 4", "Tiet 5", "Tiet 6", "Tiet 7", "Tiet 8", "Tiet 9", "Tiet 10", "Tiet 11", "Tiet 12"};
	private String chartsRow[][] = {};
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	StudentControl studentControl;
	RegistrationControl registrationControl;
	public TimeTable() {
		jpHeader = new JPanel();
		jlTitle = new JLabel("Thời khóa biểu");
		jlName = new JLabel();
		jlMssv = new JLabel();
		jlSearch = new JLabel("Tìm thời khóa biểu cho:");
		jtfSearch = new JTextField();
		jbSearch =  new JButton("Tìm kiếm");
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		studentControl = new StudentControl();
		registrationControl = new RegistrationControl();
		
		timeTableModel = new DefaultTableModel(timeTableRow, timeTableColumn) {
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        chartsModel = new DefaultTableModel(chartsRow, chartsColumn){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
                @Override
            public Class<?> getColumnClass(int columnIndex) {
               return String.class;
            }
        };
        
		jtbTimeTable = new JTable(timeTableModel);
		jtbTimeTable.setFont(new Font("Arial", 1, 12));
		jcpTimeTable = new JScrollPane(jtbTimeTable);
		
		jtbCharts = new JTable(chartsModel);
		jtbCharts.setFont(new Font("Arial", 1, 12));
		jcpCharts = new JScrollPane(jtbCharts);
		
		
		
		timeTableHeader = jtbTimeTable.getTableHeader();
		timeTableHeader.setFont(new Font("Arial", 1, 15));
		timeTableHeader.setBackground(new Color(0, 170, 207));
		timeTableHeader.setForeground(Color.WHITE);
		
		
		
		chartsHeader = jtbCharts.getTableHeader();
		chartsHeader.setFont(new Font("Arial", 1, 15));
		chartsHeader.setBackground(new Color(0, 170, 207));
		chartsHeader.setForeground(Color.WHITE);
	}
	
	public void runTimeTable(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jcpTimeTable);
		jcpTimeTable.setBounds(30, y/8, x- 60, y/3);
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/7);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(0, 10, x, y/25);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		jpHeader.add(jlSearch);
		jlSearch.setFont(new Font("Arial", 1, 13));
		jlSearch.setBounds(x/2, 50, x/8, 20);
		jlSearch.setForeground(Color.WHITE);
		
		jpHeader.add(jtfSearch);
		jtfSearch.setFont(new Font("Arial", 1, 13));
		jtfSearch.setBounds( 5*x/ 8,50, x/8, 20);
		
		jpHeader.add(jbSearch);
		jbSearch.setFont(new Font("Arial", 1, 13));
		jbSearch.setBounds( 6*x/8 + 10, 50, x/12, 20);
		jbSearch.setForeground(Color.WHITE);
		jbSearch.setBackground(new Color(66, 103, 178));
		
		
		
		jpHeader.add(jlName);
		jlName.setFont(new Font("Arial", 1, 13));
		jlName.setBounds(30, 50, x/2, y/25);
		jlName.setForeground(Color.WHITE);
		
		jpHeader.add(jlMssv);
		jlMssv.setFont(new Font("Arial", 1, 13));
		jlMssv.setBounds(x/4, 50, x/2, y/25);
		jlMssv.setForeground(Color.WHITE);
		
		

		add(jcpCharts);
		jcpCharts.setBounds(30, y/2, x - 60, y/2-20);
		jtbCharts.setRowHeight(jcpCharts.getHeight()/14);
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("\\d*");
			    Matcher idCheck = pattern.matcher(jtfSearch.getText());
			    
			    if(jtfSearch.getText().length() != 0) {
			    	if(idCheck.matches()) {
				    	int idStudent = Integer.parseInt(jtfSearch.getText());
				    	StudentObject student = studentControl.loadStudent(idStudent);
				    	if(studentControl.checkIdStudent(idStudent)) {
				    		loadTimeTable(student);
				    	}
				    }
				    else {
				    	JOptionPane.showMessageDialog(null, "id Phai la so!");
				    }
			    }
			}
		});
		
	}
	
	public void loadTimeTable(StudentObject student) {
		boolean[][] TimeTable = {{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true}, 
								{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true},
								{true, true, true, true, true, true, true, true, true, true, true, true}, {true, true, true, true, true, true, true, true, true, true, true, true}};
		timeTableModel.setRowCount(0);
		chartsModel.setRowCount(0);
		registrationControl.time(TimeTable, student);
		jlTitle.setText("Thời khóa biểu" );
		jlMssv.setText("Mssv: " + student.getIdStudent());
		jlName.setText("Họ và tên: " +student.getName());
		String load = "select idClass from dangki where status = 'thanh cong' and idStudent = " + student.getIdStudent();
		ResultSet re = connectDatabase.returnData(load);
		
		try {
			while(re.next()) {
				int id = re.getInt(1);
				StudentClassObject stClass = studentClassControl.loadClass(id);
				String A[] = {stClass.getDay(), stClass.getTime(), stClass.getAddress(), "" + stClass.getIdClass(), "" + stClass.getIdSubject(), stClass.getNameClass(), stClass.getNote()};
				timeTableModel.addRow(A);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] B = new String[8];
//		B[7] = null;
		for(int i =0; i<12; i++) {
			B[0] = time[i];
			for(int j=1; j<7; j++) {
				if(i < 6) {
					if(TimeTable[j-1][i] == false) {
						B[j] = "co lich hoc";
					}
					else B[j] = null;
				}
				else if(i>6){
					if(TimeTable[j-1][i-1] == false) {
						B[j] = "co lich hoc";
					}
					else B[j] = null;
				}
				else {
					B[j] = null;
				}
			}
			chartsModel.addRow(B);
			jtbCharts.setDefaultRenderer(String.class, new CustomRenderer());
			
		}
	}
}

class CustomRenderer extends DefaultTableCellRenderer  {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Object valueAt = table.getModel().getValueAt(row, column);
		String s = "";
		if (valueAt != null) {
			s = valueAt.toString();
        }
        if(s.equals("co lich hoc")) {
        	c.setBackground(Color.CYAN);
        }
        else {
        	c.setBackground(Color.WHITE);
        }
        return c;
    }
}
