package display;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connect.ConnectDatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import object.*;
import control.*;

public class AccountManage extends JPanel{
	private JLabel jlTitle, jlId, jlName, jlUser, jlPass, jlPer, jlNote;
	private JTextField jtfSearch, jtfId, jtfName, jtfUser, jtfPass, jtfNote;
	private JTable jtbAccountManage;
	private JButton jbAddAccount, jbChangePermission, jbDeleteAccount, jbSearch;
	private JScrollPane jcpPane;
	private JComboBox jcbPermission, jcbPer;
	public DefaultTableModel accountModel;
	private DefaultComboBoxModel permissionModel;
	ConnectDatabase connectDatabase;
	ResultSet resultSet;
	StudentControl studentControl;
	private String column[] = {"STT", "IdAccount", "Name","UserName", "PassWord", "Permission", "Note"};
	private String row[][] = {{}};
	private String permission[] = {"All", "Admin", "Student"};
	private AccountControl accountControl;
	int count = 1;
	
	public AccountManage() {
		jlTitle = new JLabel("Account Manage");
		jtfSearch = new JTextField();
		jtfId = new JTextField();
		jtfName = new JTextField();
		jtfUser = new JTextField();
		jtfPass = new JTextField();
		jtfNote = new JTextField();
		
		jlId = new JLabel("ID:");
		jlName = new JLabel("Name:");
		jlUser = new JLabel("UserName:");
		jlPass = new JLabel("PassWord:");
		jlPer = new JLabel("Permission:");
		jlNote = new JLabel("Note:");
		
		jcbPermission = new JComboBox();
		jcbPer = new JComboBox(permission);
		
		jbAddAccount = new JButton("Add Account");
		jbChangePermission = new JButton("Change Permission");
		jbDeleteAccount = new JButton("Delete Account");
		jbSearch = new JButton("Search Id");
		
		accountModel = new DefaultTableModel(row, column){
            boolean[] canEdit = new boolean [] { false, false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
		permissionModel = new DefaultComboBoxModel<>(permission);
		jtbAccountManage = new JTable(accountModel);
		jtbAccountManage.setFont(new Font("Arial", 1, 12));

		jcpPane = new JScrollPane(jtbAccountManage);
		connectDatabase = new ConnectDatabase();
		studentControl = new StudentControl();
		
		accountControl = new AccountControl();
	}
	
	public void runAccountManage(int x, int y) {
		setSize(x, y);
		setLayout(null);
		
		add(jlTitle);
		jlTitle.setFont(new Font("Arial", 1, x/35));
		jlTitle.setBounds(x/3, 0, x/3, 30);
		
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
		
		add(jlUser);
		jlUser.setFont(new Font("Arial", 1, 14));
		jlUser.setBounds(10, y-140, x/7, 30);
		
		add(jtfUser);
		jtfUser.setFont(new Font("Arial", 1, 14));
		jtfUser.setBounds(x/6, y-140, x/5, 30);
		
		add(jlPass);
		jlPass.setFont(new Font("Arial", 1, 14));
		jlPass.setBounds(x/2, y-240, x/7, 30);
		
		add(jtfPass);
		jtfPass.setFont(new Font("Arial", 1, 14));
		jtfPass.setBounds(x*2/3, y-240, x/5, 30);
		
		add(jlPer);
		jlPer.setFont(new Font("Arial", 1, 14));
		jlPer.setBounds(x/2, y-190, x/7, 30);
		
		add(jcbPer);
		jcbPer.setFont(new Font("Arial", 1, 14));
		jcbPer.setBounds(x*2/3, y-190, x/5, 30);
		
		add(jlNote);
		jlNote.setFont(new Font("Arial", 1, 14));
		jlNote.setBounds(x/2, y-140, x/7, 30);
		
		add(jtfNote);
		jtfNote.setFont(new Font("Arial", 1, 14));
		jtfNote.setBounds(x*2/3, y-140, x/5, 30);
		
		add(jbSearch);
		jbSearch.setFont(new Font("Arial", 1, 15));
		jbSearch.setBounds(x*5/6, 50, 150, 20);
		
		add(jtfSearch);
		jtfSearch.setBounds(x*2/3, 50, x/6, 20);
		
		add(jcbPermission);
		jcbPermission.setModel(permissionModel);
		jcbPermission.setBounds(10, 50, 100, 20);
		
		add(jcpPane);
		jcpPane.setBounds(0, 80, x, y-330);
		
		add(jbAddAccount);
		jbAddAccount.setBounds(x/5, y - 80, x/5-20, 30);
		jbAddAccount.setFont(new Font("Arial", 1, 12));
		
		add(jbChangePermission);
		jbChangePermission.setBounds(x*2/5 + 10, y - 80, x/5 -10, 30);
		jbChangePermission.setFont(new Font("Arial", 1, 12));
		
		add(jbDeleteAccount);
		jbDeleteAccount.setBounds(x*3/5 + 20, y - 80, x/5, 30);
		jbDeleteAccount.setFont(new Font("Arial", 1, 12));
		
		loadAccountTable();
		
		jcbPermission.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				loadAccountTable();
			}
		});
		
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadAccountTable();
			}
		});
		
		jbAddAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pattern pattern = Pattern.compile("\\d*");
			    Matcher matcher = pattern.matcher(jtfId.getText());
		        if(jtfId.getText().length() == 0 || jtfName.getText().length() == 0 || jtfUser.getText().length() == 0 || jtfPass.getText().length() == 0) {
		        	JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin!");
		        }
		        else if(matcher.matches()) {
		        	AccountObject account = new AccountObject();
		        	account.setIdAccount(Integer.parseInt((String)jtfId.getText()));
		        	account.setName(jtfName.getText());
		        	account.setUsername(jtfUser.getText());
		        	account.setPassword(jtfPass.getText());
		        	account.setPermission((String)jcbPer.getSelectedItem());
		        	if(jtfNote.getText().length() == 0) {
		        		account.setNote("null");
		        	}
		        	else {
		        		account.setNote(jtfNote.getText());
		        	}
		        	
		        	accountControl.addAccount(account);
		        	loadAccountTable();
		        }
		        else {
		        	JOptionPane.showMessageDialog(null, "ID phai la so!");
		        }
			}
		});
		
		jbChangePermission.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newPermission;
				int choose = jtbAccountManage.getSelectedRow();
				if(choose >=0) {
					int id = Integer.parseInt((String)jtbAccountManage.getValueAt(choose, 1));
					AccountObject account = accountControl.loadAccount(id);
					if(account.getPermission().toLowerCase().equals("admin")) {
						int ans = JOptionPane.showConfirmDialog(null, "Viec nay se xoa bo quyen admin cua tai khoan?ban co chac khong?", "Question", JOptionPane.YES_NO_OPTION);
						if(ans == JOptionPane.YES_OPTION) {
							newPermission = "student";
						}
						else {
							newPermission = "admin";
						}
					}
					else {
						int ans = JOptionPane.showConfirmDialog(null, "Hanh vi bi han che?ban co chac khong?", "Question", JOptionPane.YES_NO_OPTION);
						if(ans == JOptionPane.YES_OPTION) {
							newPermission = "admin";
						}
						else {
							newPermission = "student";
						}
					}
					accountControl.changeInfAccount(account, "permission", newPermission);
					loadAccountTable();
				}
			}
		});
		
		jbDeleteAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = jtbAccountManage.getSelectedRow();
				if(choose >= 0) {
					int ans = JOptionPane.showConfirmDialog(null, "Are you sure!", "question", JOptionPane.YES_NO_OPTION);
					if(ans == JOptionPane.YES_OPTION) {
						int id = Integer.parseInt((String)jtbAccountManage.getValueAt(choose, 1));
						accountControl.deleteAccount(id);
						studentControl.deleteStudent(id);
						loadAccountTable();
					}
				}
			}
		});
	}
	
	public void loadAccountTable() {
		accountModel.setRowCount(0);
		count = 1;
		String s, permission, idSearch;
		
		if(jcbPermission.getSelectedIndex() == 0 ) {
			permission = "Select permission from account";
		}
		else {
			permission ="Select permission from account where permission = '" + (String)jcbPermission.getSelectedItem() + "'";
		}
		
		if(jtfSearch.getText().length() == 0) {
			idSearch = "select idAccount from account"; 
		}
		else {
			Pattern pattern = Pattern.compile("\\d*");
		    Matcher matcher = pattern.matcher(jtfSearch.getText());
		    if(matcher.matches()) {
		    	int id = Integer.parseInt((String)jtfSearch.getText());
		    	idSearch = "select idAccount from account where idAccount = " + id;
		    }
		    else {
		    	idSearch = "select idAccount from account"; 
		    	JOptionPane.showMessageDialog(null, "Vui long nhap Id la so!");
		    }
		}
		
		s = "Select * from account where permission in (" + permission + ") and idAccount in (" + idSearch + ")";
		resultSet = connectDatabase.returnData(s);
		try {
			while(resultSet.next()) {
				AccountObject account = accountControl.loadAccount(resultSet.getInt(1));
				String A[] = {"" + count, "" + account.getIdAccount(), account.getName(), account.getUsername(), account.getPassword(), account.getPermission(), account.getNote()};
	        	accountModel.addRow(A);
	        	count ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
