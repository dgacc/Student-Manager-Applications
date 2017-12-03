package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.*;
import control.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDatabase;

public class SubjectManage extends JPanel{
	private JLabel jlTitle, jlId, jlName, jlSpecialized, jlCount, jlFactor, jlNote;
	private JTextField jtfSearch, jtfId, jtfName, jtfNote;
	private JTable jtbSubjectManagement;
	private JButton jbAdd, jbChange, jbDelete, jbSearch;
	private JScrollPane jcpPane;
	public DefaultTableModel subjectModel;
	private JComboBox jcbSpecialized, jcbNganh, jcbCount, jcbFactor;
	private DefaultComboBoxModel specializedModel;
	ConnectDatabase connectDatabase;
	ResultSet resultSet;
	SubjectControl subjectControl;
	private String column[] = {"STT", "IdSubject", "Name","Specialized", "Count", "Factor", "Note"};
	private String row[][] = {{}};
	private String specializedValue[] = {	"All", "Chuong Trinh Viet-Nhat", "Phong Dao Tao Dai Hoc", "TT Dao Tao Tai Nang", "Vien Cong Nghe Sinh Hoc & Thuc Pham",
			"Vien Cong Nghe Thong Tin & Truyen Thong", "Vien Co Khi", "Vien Co Khi Dong Luc", "Vien Det May-Da giay & Thoi Trang",
			"Vien Dao Tao Lien Tuc", "Vien Dien", "Vien Dien Tu-Vien Thong", "Vien Kinh Te-Quan Ly", "Vien Ki Thuat Hoa Hoc",
			"Vien Ki Thuat Hat Nhan & Vat li Moi Truong", "Vien Khoa Hoc & Cong Nghe Moi Truong", "Vien Khoa Hoc & Ki Thuat Vat Lieu",
			"Vien Ngoai Ngu", "Vien Su Pham Ki Thuat", "Vien Toan Ud & Tin Hoc", "Vien Vat Li Ki Thuat"};
	private String countValue[] = {"0", "1", "2", "3", "4", "5"};
	private String factorValue[] = {"0.8", "0.7", "0.5"};
	int count = 1;
	public SubjectManage() {
		jlTitle = new JLabel("Subject Managet");
		jlId = new JLabel("Id:");
		jlName = new JLabel("Name:");
		jlSpecialized = new JLabel("Specialized:");
		jlCount = new JLabel("Count;");
		jlFactor = new JLabel("Factor:");
		jlNote = new JLabel("Note");
		
		jtfSearch = new JTextField();
		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfNote = new JTextField();
		
		jbAdd = new JButton("Add");
		jbChange = new JButton("Change");
		jbDelete = new JButton("Delete");
		jbSearch = new JButton("Search");
		
		subjectModel = new DefaultTableModel(row, column){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };;;
		jtbSubjectManagement = new JTable(subjectModel);
		jtbSubjectManagement.setFont(new Font("Arial", 1, 12));

		jcpPane = new JScrollPane(jtbSubjectManagement);
		connectDatabase = new ConnectDatabase();
		subjectControl = new SubjectControl();
		
		specializedModel = new DefaultComboBoxModel<>(specializedValue);
		jcbSpecialized = new JComboBox<>();
		jcbSpecialized.setModel(specializedModel);
		jcbNganh = new JComboBox<>(specializedValue);
		jcbCount = new JComboBox<>(countValue);
		jcbFactor = new JComboBox<>(factorValue);
	}
	
	public void runSubjectManage(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/35));
		jlTitle.setBounds(x/3, 0, x/3, 30);
		
		add(jcpPane);
		jcpPane.setBounds(0, 80, x, y-330);
		
		add(jcbSpecialized);
		jcbSpecialized.setBounds(10, 50, 200, 20);
		
		add(jtfSearch);
		jtfSearch.setBounds(x*2/3, 50, x/6, 20);
		jtfSearch.setFont(new Font("Arial", 1, 12));
		
		add(jbSearch);
		jbSearch.setBounds(x*5/6, 50, 150, 20);
		
		add(jlId);
		jlId.setFont(new Font("Arial", 1, 14));
		jlId.setBounds(10, y-240, x/7, 30);
		
		add(jtfId);
		jtfId.setFont(new Font("Arial", 1, 14));
		jtfId.setBounds(x/6 , y-240, x/5, 30);
		
		add(jlName);
		jlName.setFont(new Font("Arial", 1, 14));
		jlName.setBounds(10, y-190, x/7, 30);
		
		add(jtfName);
		jtfName.setFont(new Font("Arial", 1, 14));
		jtfName.setBounds(x/6, y-190, x/5, 30);
		
		add(jlSpecialized);
		jlSpecialized.setFont(new Font("Arial", 1, 14));
		jlSpecialized.setBounds(10, y-140, x/7, 30);
		
		add(jcbNganh);
		jcbNganh.setBounds(x/6, y-140, x/5, 30);
		
		add(jlCount);
		jlCount.setFont(new Font("Arial", 1, 14));
		jlCount.setBounds(x/2, y-240, x/7, 30);
		
		add(jcbCount);
		jcbCount.setFont(new Font("Arial", 1, 14));
		jcbCount.setBounds(x*2/3, y-240, x/5, 30);
		
		add(jlFactor);
		jlFactor.setFont(new Font("Arial", 1, 14));
		jlFactor.setBounds(x/2, y-190, x/7, 30);
		
		add(jcbFactor);
		jcbFactor.setFont(new Font("Arial", 1, 14));
		jcbFactor.setBounds(x*2/3, y-190, x/5, 30);
		
		add(jlNote);
		jlNote.setFont(new Font("Arial", 1, 14));
		jlNote.setBounds(x/2, y-140, x/7, 30);
		
		add(jtfNote);
		jtfNote.setFont(new Font("Arial", 1, 14));
		jtfNote.setBounds(x*2/3, y-140, x/5, 30);
		
		add(jbAdd);
		jbAdd.setBounds(x/5, y - 80, x/5-20, 30);
		jbAdd.setFont(new Font("Arial", 1, 12));
		
		add(jbChange);
		jbChange.setBounds(x*2/5 + 10, y - 80, x/5 -10, 30);
		jbChange.setFont(new Font("Arial", 1, 12));
		
		add(jbDelete);
		jbDelete.setBounds(x*3/5 + 20, y - 80, x/5, 30);
		jbDelete.setFont(new Font("Arial", 1, 12));
		
		loadSubjectTable();
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadSubjectTable();
			}
		});
		
		jcbSpecialized.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				loadSubjectTable();
			}
		});
		
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
				loadSubjectTable();
			}
		});
		
		jbChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbSubjectManagement.getSelectedRow();
				if(choose >= 0) {
					int id = Integer.parseInt((String)jtbSubjectManagement.getValueAt(choose, 1));
					String name = (String)jtbSubjectManagement.getValueAt(choose, 2);
					subjectControl.deleteSubject(id);
					
					jtfId.setText("" + id);
					jtfName.setText(name);
					
					jbChange.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int ans = JOptionPane.showConfirmDialog(null, "Are you sure", "Question", JOptionPane.YES_NO_OPTION);
							if(ans == JOptionPane.YES_OPTION) {
								subjectControl.deleteSubject(id);
								add();
							}
						}
					});
					loadSubjectTable();
				}
			}
		});
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbSubjectManagement.getSelectedRow();
				if(choose >= 0) {
					int id = Integer.parseInt((String)jtbSubjectManagement.getValueAt(choose, 1));
					subjectControl.deleteSubject(id);
					loadSubjectTable();
				}
			}
		});
	}
	
	public void loadSubjectTable() {
		subjectModel.setRowCount(0);
		count = 1;
		String specialized, s, idSearch;
		
		if(jcbSpecialized.getSelectedIndex() == 0 ) {
			specialized ="Select specialized from subject";
		}
		else  {
			specialized = "select specialized from subject where specialized = '" + (String)jcbSpecialized.getSelectedItem() + "'";
		}
		
		if(jtfSearch.getText().length() == 0) {
			idSearch = "select idSubject from subject"; 
		}
		else {
			Pattern pattern = Pattern.compile("\\d*");
		    Matcher matcher = pattern.matcher(jtfSearch.getText());
		    if(matcher.matches()) {
		    	int id = Integer.parseInt((String)jtfSearch.getText());
		    	idSearch = "select idSubject from subject where idSubject = " + id;
		    }
		    else {
		    	idSearch = "select idSubject from subject"; 
		    	JOptionPane.showMessageDialog(null, "Vui long nhap Id la so!");
		    }
		}
		
		
		s = "Select * from subject where specialized in (" + specialized + ") and idSubject in (" + idSearch + ")";
		resultSet = connectDatabase.returnData(s);
		try {
			while(resultSet.next()) {
				SubjectObject subject = subjectControl.loadSubject(resultSet.getInt(1));
				String A[] = {"" + count, "" + subject.getIdSubject(), subject.getName(), subject.getSpecialized(),"" + subject.getCount(), subject.getFactor(), subject.getNote()};
	        	subjectModel.addRow(A);
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add() {
		Pattern pattern = Pattern.compile("\\d*");
	    Matcher matcher = pattern.matcher(jtfId.getText());
        if(jtfId.getText().length() == 0 || jtfName.getText().length() == 0 ) {
        	JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin!");
        }
        else if(matcher.matches()) {
        	SubjectObject subject = new SubjectObject();
        	int id = Integer.parseInt(jtfId.getText());
        	if(subjectControl.checkIdSubject(id)){
        		JOptionPane.showMessageDialog(null, "Id da co!");
        	}
        	else if(jcbNganh.getSelectedIndex() == 0) {
        		JOptionPane.showMessageDialog(null, "Vui long chon Specialized!");
        	}
        	else {
        		subject.setIdSubject(id);
        		subject.setName(jtfName.getText());
        		int count = Integer.parseInt((String)jcbCount.getSelectedItem());
        		subject.setCount(count);
        		subject.setSpecialized((String)jcbNganh.getSelectedItem());
        		subject.setFactor((String)jcbFactor.getSelectedItem());
        		subject.setNote(jtfNote.getText());
	        	
        		subjectControl.addSubject(subject);
        		loadSubjectTable();
        	}
        }
        else {
        	JOptionPane.showMessageDialog(null, "ID phai la so!");
        }
	}
}
