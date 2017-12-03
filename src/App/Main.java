package App;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Connect.*;
import control.*;
import display.*;
import object.*;

public class Main {
	public static void main(String[] args) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		MainFrame frame = new MainFrame();
		frame.runMainFrame(d.width, d.height);
//		frame.runMainFrame(1200, 700);
		
		
//		RegistrationControl registrationControl = new RegistrationControl();
//		StudentControl studentControl = new StudentControl();
//		StudentObject st = studentControl.loadStudent(1);
//		StudentClassObject stClass = new StudentClassObject();
//		stClass.setTime("11h05-11h50");
//		stClass.setDay("Thu 3");
//		
//		System.out.println("check time:" + registrationControl.checkInfClass(stClass, st));
		
	}
	
	
}
