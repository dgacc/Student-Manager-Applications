package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import Connect.ConnectDatabase;
import object.*;

public class AccountControl {
	ConnectDatabase connectDatabase;
	
	public AccountControl() {
		connectDatabase = new ConnectDatabase();
	}
	
	public void addAccount(AccountObject account) {
		String s = "insert into account (idAccount, name, username, password, permission, note) values ("
				+ account.getIdAccount() + ",'" + account.getName() + "','" + account.getUsername() + "','"
				+ account.getPassword() + "','" + account.getPermission() + "','" + account.getNote() + "')";
		if(!checkAccount(account.getIdAccount())) {
			connectDatabase.updateData(s);
		}
	}
	
	public int returnID(String user, String pass) {
		int id = 0;
		String s = "select * from account where username = '" + user + "'";
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public void deleteAccount(AccountObject account) {
		if(checkAccount(account.getIdAccount())) {
			String deleteAccount = "delete from account where idAccount =" + account.getIdAccount();
			connectDatabase.updateData(deleteAccount);
		}
	}
	
	public void deleteAccount(int id) {
		if(checkAccount(id)) {
			String deleteAccount = "delete from account where idAccount =" + id;
			connectDatabase.updateData(deleteAccount);
		}
	}
	
	public void changePassWord(AccountObject account,String passAfter) {
		String s = "update account set password = '" + passAfter + "' where idAccount = " + account.getIdAccount();
		connectDatabase.updateData(s);
	}
	
	public void changeInfAccount(AccountObject account, String typeChange, String newValue) {
		String changeInfAccount = "Update account set " + typeChange + " = '" + newValue + "' where idAccount = " + account.getIdAccount();
		connectDatabase.updateData(changeInfAccount);
	}
	
	public boolean checkAccount(String username, String password) {
		String s = "select username, password from account";
		try {
			ResultSet resultSet = connectDatabase.returnData(s);
			while(resultSet.next()) {
				String s1 = resultSet.getString(1);
				//System.out.println(s1);
				
				String s2 = resultSet.getString(2);
				//System.out.println(s2);
				if(username.equals(s1) && password.equals(s2)) {
					return true;
				}
			}
			return false;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean checkAccount(int id) {
		String s = "select idAccount from account";
		try {
			ResultSet resultSet = connectDatabase.returnData(s);
			while(resultSet.next()) {
				if(id == resultSet.getInt(1)) return true;
			}
			return false;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public AccountObject loadAccount(int idAccount) {
		AccountObject account = new AccountObject();
		String s = "select * from account where idAccount = " +idAccount;
		ResultSet rs = connectDatabase.returnData(s);
		try {
			while(rs.next()) {
				account.setIdAccount(rs.getInt(1));
				account.setName(rs.getString(2));
				account.setUsername(rs.getString(3));
				account.setPassword(rs.getString(4));
				account.setPermission(rs.getString(5));
				account.setNote(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public AccountObject loadAccountFromStudent(StudentObject student) {
		AccountObject account = new AccountObject();
		account.setIdAccount(student.getIdStudent());
		account.setName("" + student.getName());
		account.setUsername("" + student.getIdStudent());
		account.setPassword("" + student.getIdStudent());
		account.setPermission("student");
		return account;
	}
	
	public void inTTin(AccountObject account) {
		System.out.println("Thong tin account:");
		System.out.println("ID:" + account.getIdAccount());
		System.out.println("Name:" + account.getName());
		System.out.println("UserName:" + account.getUsername());
		System.out.println("PassWord:" + account.getPassword());
		System.out.println("Permission:" + account.getPermission());
	}
}
