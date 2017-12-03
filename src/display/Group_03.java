package display;

import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Group_03 extends JPanel{
	private JLabel jlTTin;
	private DefaultTableModel groupMode;
	private JTable jtbGroup;
	private JTableHeader groupHeader;
	private String column[] = {"STT", "ID", "Name", "Email", "Note"};
	private JScrollPane jcpGroup;
	private String row[][] = {{"1", "20152824", "Hoàng Minh Phong", "phongpt97@gmail.com", "Nhóm trưởng"},
								{"2", "20154314", "Hồ Ngọc Văn", "hongocvan251297@gmail.com", null},
								{"3", "tự điền", "Bá Trung", "tự điền", null},
								{"4", "20151585", "Bùi Đức Hòa", "20151585@student.hust.edu.vn", null},
								{"5", "tự điền", "Nguyễn Tiến Thành", "tự điền", null}};
	public Group_03() {
		jlTTin = new JLabel("Thông Tin Nhóm 03");
		
		groupMode = new DefaultTableModel(row, column){
            boolean[] canEdit = new boolean [] { false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		
        jtbGroup = new JTable(groupMode);
        jtbGroup.setFont(new Font("Arial", 1, 12));
        jtbGroup.setRowHeight(35);
        jcpGroup = new JScrollPane(jtbGroup);
        
        groupHeader = jtbGroup.getTableHeader();
        groupHeader.setFont(new Font("Arial", 1, 15));
	}
	
	public void run(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jlTTin);
		jlTTin.setFont(new Font("Arial", 1, 25));
		jlTTin.setBounds(x/3, 0, x/3, 50);
		
		add(jcpGroup);
		jcpGroup.setBounds(100, 100, x-200, 200);
	}
}
