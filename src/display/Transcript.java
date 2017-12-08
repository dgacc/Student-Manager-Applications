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
	private JPanel jpHeader, jpInf, jplearned, jpTarget, jpFails;
	private JLabel jlTitle, jlId, jlName, JlSpecilized, jlTC, jlSum,
	jlListIcon, jlPontIcon, jlInfIcon, jlFails, jlInfTitle, jlPassedTitle, jlFailTile, jlPointTitles;
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
		jlListIcon = new JLabel();
		jlListIcon.setIcon(new ImageIcon("Icon/frame/list.png"));
		
		jlFails = new JLabel();
		jlFails.setIcon(new ImageIcon("Icon/frame/fails1.jpg"));
	
		jlPontIcon = new JLabel();
		jlPontIcon.setIcon( new ImageIcon("Icon/frame/DewPoint.png"));
		jlInfIcon = new JLabel();
		jlInfIcon.setIcon( new ImageIcon("Icon/frame/presonalDetails.png"));
		jlTitle = new JLabel("Bảng điểm");
		jlId = new JLabel("MSSV:");
		jlName = new JLabel("Ho va Ten:");
		JlSpecilized = new JLabel("Nganh Dao Tao:");
		jlTC = new JLabel("So Tc da hoc:");
		jlSum = new JLabel("Diem Tong:");
		jpHeader = new JPanel();
		jpInf = new JPanel();
		jplearned = new JPanel();
		jpTarget = new JPanel();
		jpFails = new JPanel();
		jlInfTitle = new JLabel();
		jlPassedTitle = new JLabel();
		jlFailTile = new JLabel();
		jlPointTitles = new JLabel();
	
		
		 
		
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
		transcriptHeader.setBackground(new Color(0, 170, 207));
		transcriptHeader.setForeground(Color.white);
		connectDatabase = new ConnectDatabase();
		studentClassControl = new StudentClassControl();
		subjectControl = new SubjectControl();
	}
	
	public void runTranscript(int x, int y) {
		setSize(x, y);
		setLayout(null);
	
		add(jlInfIcon);
		jlInfIcon.setBounds(50, y/5 - 50, 100 ,100);
		
		add(jlListIcon);
		jlListIcon.setBounds( 2*x/5 + 30, y/5 - 50, 100 ,100);
		
		add(jlFails);
		jlFails.setBounds(3*x/5 + 20, y/5 - 50, 100 ,100);
		
		add(jlPontIcon);
		jlPontIcon.setBounds(4*x/5 + 10, y/5 - 50, 100 ,100);
		
		
		
		
		add(jpHeader);
		jpHeader.setLayout(null);
		jpHeader.setSize(x, y/10);
		jpHeader.setBackground(new Color(0, 170, 207));
		
		jpHeader.add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, 30));
		jlTitle.setBounds(x/6, 0, x*2/3, 50);
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		jlTitle.setVerticalAlignment((int) CENTER_ALIGNMENT);
		
		
		add(jpInf);
		jpInf.setLayout(null);
		jpInf.setBackground(Color.WHITE);
		jpInf.setBounds(30 , y/5, 2*x/5 -30,100);
		
		jpInf.add(jlInfTitle);
		jlInfTitle.setFont(new Font("Arial", 1, 20));
		jlInfTitle.setBounds(150, 5, 200, 30);
		jlInfTitle.setText("Thông tin");
		
	
		jpInf.add(jlId);
		jlId.setFont(new Font("Arial", 1, 15));
		jlId.setBounds(350, 40, 200, 30);
		
		jpInf.add(jlName);
		jlName.setFont(new Font("Arial", 1, 15));
		jlName.setBounds(150, 40, 100, 30);
	
		jpInf.add(JlSpecilized);
		JlSpecilized.setFont(new Font("Arial", 1, 15));
		JlSpecilized.setBounds(10, 60, 500, 30);
		
		
		
		
		add(jplearned);
		jplearned.setLayout(null);
		jplearned.setBackground(Color.WHITE);
		jplearned.setBounds(2*x/5+10, y/5 , x/5 - 20 ,100);
		
		jplearned.add(jlPassedTitle);
		jlPassedTitle.setFont(new Font("Arial", 1, 20));
		jlPassedTitle.setBounds(150, 5, 200, 30);
		jlPassedTitle.setText("TC đã qua");
		
		jplearned.add(jlTC);
		jlTC.setFont(new Font("Arial", 1, 30));
		jlTC.setBounds(150, 60, 500, 30);
	
		
		
		add(jpFails);
		jpFails.setLayout(null);
		jpFails.setBackground(Color.WHITE);
		jpFails.setBounds(3*x/5, y/5, x/5 -20 ,100);
		
		jpFails.add(jlFailTile);
		jlFailTile.setFont(new Font("Arial", 1, 20));
		jlFailTile.setBounds(150, 5, 200, 30);
		jlFailTile.setText("TC trượt");
		
		
		
		
		add(jpTarget);
		jpTarget.setLayout(null);
		jpTarget.setBackground(Color.WHITE);	
		jpTarget.setBounds(4*x/5 - 10, y/5, x/5 - 20,100);
		
		jpTarget.add(jlPointTitles);
		jlPointTitles.setFont(new Font("Arial", 1, 20));
		jlPointTitles.setBounds(150, 5, 200, 30);
		jlPointTitles.setText("Điểm tổng");
		
		jpTarget.add(jlSum);
		jlSum.setFont(new Font("Arial", 1, 30));
		jlSum.setBounds(150, 60, 500, 30);
		
	
		
		
		
//		
		add(jcpTranscript);
		jcpTranscript.setBounds(30, y/2 - 60, x - 60, y/2 + 30);
		jcpTranscript.setBackground(Color.white);
		
//		
//	
//		
//		
	}
	
	public void loadTranscriptTable(StudentObject student) {
		transcriptModel.setRowCount(0);
		count = 1; TC = 0;
		String s;
		
		jlId.setText("Mssv: " + student.getIdStudent());
		jlName.setText(student.getName());
		JlSpecilized.setText("Ngành Đào Tạo: " + student.getSpecialized());
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
		jlTC.setText( ""+ TC);
		jlSum.setText("" + sum);
	}
	
}
