package display;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Connect.ConnectDatabase;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.*;
import control.*;

public class Transcript extends JPanel{
	private JLabel jlTitle, jlId, jlName, JlSpecilized, jlTC, jlSum;
	private JTable jtbTranscript;
	private JTableHeader transcriptHeader;
	private DefaultTableModel transcriptModel;
	private JScrollPane jcpTranscript;
	private String column[] = {"STT", "ma HP", "ten HP", "TC", "Lop", "diem QT", "diem KT", "diem tong"};
	private String row[][] = {};
	ConnectDatabase connectDatabase;
	StudentClassControl studentClassControl;
	SubjectControl subjectControl;
	int count =1, TC = 0, sum = 0;
	public Transcript() {
		jlTitle = new JLabel("Bang diem cua sinh vien");
		jlId = new JLabel("MSSV:");
		jlName = new JLabel("Ho va Ten:");
		JlSpecilized = new JLabel("Nganh Dao Tao:");
		jlTC = new JLabel("So Tc da hoc:");
		jlSum = new JLabel("Diem Tong:");
		
		transcriptModel = new DefaultTableModel(row, column){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		jtbTranscript = new JTable(transcriptModel);
		jtbTranscript.setFont(new Font("Arial", 1, 12));
		jcpTranscript = new JScrollPane(jtbTranscript);
		
		transcriptHeader = jtbTranscript.getTableHeader();
		transcriptHeader.setFont(new Font("Arial", 1, 15));
		transcriptHeader.setBackground(Color.GRAY);
		transcriptHeader.setForeground(Color.white);
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		subjectControl = new SubjectControl();
	}
	
	public void runTranscript(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/50));
		jlTitle.setBounds(x/3, 0, x/2, y/25);
		
		add(jcpTranscript);
		jcpTranscript.setBounds(0, 80, x, y/2);
		
		add(jlId);
		jlId.setFont(new Font("Arial", 1, 15));
		jlId.setBounds(10, y/2 + 100, 500, 30);
		
		add(jlName);
		jlName.setFont(new Font("Arial", 1, 15));
		jlName.setBounds(10, y/2 + 140, 500, 30);
		
		add(JlSpecilized);
		JlSpecilized.setFont(new Font("Arial", 1, 15));
		JlSpecilized.setBounds(10, y/2 + 180,	500, 30);
		
		add(jlTC);
		jlTC.setFont(new Font("Arial", 1, 15));
		jlTC.setBounds(10, y/2 + 220, 500, 30);
		
		add(jlSum);
		jlSum.setFont(new Font("Arial", 1, 15));
		jlSum.setBounds(10, y/2 + 260, 500, 30);
	}
	
	public void loadTranscriptTable(StudentObject student) {
		transcriptModel.setRowCount(0);
		count = 1; TC = 0;
		String s;
		
		jlId.setText("Mssv: " + student.getIdStudent());
		jlName.setText("Ho va ten: " + student.getName());
		JlSpecilized.setText("Nganh dao tao: " + student.getSpecialized());
		s = "Select * from studentlist where idStudent = " + student.getIdStudent();
		ResultSet resultSet = connectDatabase.returnData(s);
		try {
			
			while(resultSet.next()) {
				StudentClassObject stClass = studentClassControl.loadClass(resultSet.getInt(1));
				SubjectObject subject = subjectControl.loadSubject(stClass.getIdSubject());
				String A[] = {"" + count, "" + subject.getIdSubject(), subject.getName(), "" +subject.getCount(), stClass.getNameClass(),
							"" + resultSet.getFloat(4), "" + resultSet.getFloat(5), "" + resultSet.getFloat(6)};
				transcriptModel.addRow(A);
				TC += subject.getCount();
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jlTC.setText("Tong Tc da hoc: " + TC);
		jlSum.setText("Diem tong: " + sum);
	}
	
}
