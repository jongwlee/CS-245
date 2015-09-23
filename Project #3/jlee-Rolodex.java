// 
// Name: Lee, Jong 
// Project: # 3
// Due: 2/27/14 
// Course: CS-245-01-w14 
// 
// Description: 
// Implement a Rolodex class to display a contact file using tabs.
// We are to read a text file and display the name,email,and the picture
// of each person


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;

//Class of the user to get the name,email,and the picture
class User{

	private String userName;
	private String userEmail;
	private String picture;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}

	

public class Rolodex implements ActionListener {
	String[] tokens;
	String name;
	String email;
	String pictures;
	JTabbedPane jtp;
	JPanel topPanel;
	JPanel panel;
	JLabel labname;
	JLabel labemail;
	JTextField txtname;
	JTextField txtemail;
	ImageIcon image;
	JLabel l;
	JOptionPane joptionpane;
	JFrame frame;
	int x;
	int y;
	
	ArrayList<User> userList = new ArrayList<User>();
	
	//Constructor receives the filename
	Rolodex(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		
		//Split the line with ~
		while(scanner.hasNext()){
			tokens = scanner.nextLine().split("~");
			name = tokens[0];
			email = tokens[1];
			pictures = tokens[tokens.length - 1];
			
			User user = new User();
			user.setUserName(name);
			user.setUserEmail(email);
			user.setPicture(pictures);
			userList.add(user);
		}
		
		
		
	    frame = new JFrame("Rolodex");
		frame.setSize(700,200);
		
		//Menu
		JMenuBar jmb = new JMenuBar();
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiOpen = new JMenuItem("Open");
		jmiOpen.setEnabled(false);
		JMenuItem jmiExit = new JMenuItem("Exit");
		jmiExit.setMnemonic(KeyEvent.VK_X);
		jmFile.add(jmiOpen);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);
		
		jmiOpen.addActionListener(this);
		jmiExit.addActionListener(this);
		
		
		JMenu jmTabs = new JMenu("Tabs");
		jmTabs.setMnemonic(KeyEvent.VK_T);
		
		JMenu jmiplacement = new JMenu("Placement");
		JMenuItem jmitop = new JMenuItem("Top");
		JMenuItem jmiright = new JMenuItem("Right");
		JMenuItem jmibottom = new JMenuItem("Bottom");
		JMenuItem jmileft = new JMenuItem("Left");
		jmiplacement.add(jmitop);
		jmiplacement.add(jmiright);
		jmiplacement.add(jmibottom);
		jmiplacement.add(jmileft);
		
		jmitop.addActionListener(this);
		jmiright.addActionListener(this);
		jmibottom.addActionListener(this);
		jmileft.addActionListener(this);

		
		JMenu jmilayoutpolicy = new JMenu("Layout policy");
		JMenuItem jmiscroll = new JMenuItem("Scroll");
		JMenuItem jmiwrap = new JMenuItem("Wrap");
		jmilayoutpolicy.add(jmiscroll);
		jmilayoutpolicy.add(jmiwrap);
		
		jmiscroll.addActionListener(this);
		jmiwrap.addActionListener(this);

		
		JMenuItem jmidefaults = new JMenuItem("Defaults");
		jmidefaults.setMnemonic(KeyEvent.VK_D);
		jmidefaults.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		jmidefaults.addActionListener(this);
		jmTabs.add(jmiplacement);
		jmTabs.add(jmilayoutpolicy);
		jmTabs.addSeparator();
		jmTabs.add(jmidefaults);
		jmb.add(jmTabs);


		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiabout = new JMenuItem("About");
		jmiabout.addActionListener(this);
		jmHelp.add(jmiabout);
		jmb.add(jmHelp);
		
		frame.setJMenuBar(jmb);


		
		
		
		jtp = new JTabbedPane(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		
		for(User user : userList){
			image = new ImageIcon(user.getPicture());
			panel = new JPanel();
			labname = new JLabel("Name: ");
			txtname = new JTextField(20);
			txtname.setText(user.getUserName());
			labemail = new JLabel("Email: ");
			txtemail = new JTextField(20);
			txtemail.setText(user.getUserEmail());
			l = new JLabel(image);
			l.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(l);
			panel.add(labname);
			panel.add(txtname);
			panel.add(labemail);
			panel.add(txtemail);
			jtp.addTab(user.getUserName(), panel);
			topPanel.add(jtp);
		}
		
		frame.add(jtp);
		center(frame);
		frame.setVisible(true);
	
	}

	
	//function to center the frame
	public static void center(JFrame jfrm){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = jfrm.getSize().width;
		int height = jfrm.getSize().height;
		
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;
		
		jfrm.setLocation(x,y);
	}
	
	//Handle the menu item action events
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		
		if(s.equals("Exit")){
			System.exit(0);
		}
		
		if(s.equals("About")){
			joptionpane.showMessageDialog(frame, "Rolodex version 0.1\n(c) 2014 Jong Woo Lee");
		}
		
		if(s.equals("Left")){
			jtp.setTabPlacement(JTabbedPane.LEFT);
		}
		
		if(s.equals("Top")){
			jtp.setTabPlacement(JTabbedPane.TOP);
		}
		
		if(s.equals("Bottom")){
			jtp.setTabPlacement(JTabbedPane.BOTTOM);
		}
		
		if(s.equals("Right")){
			jtp.setTabPlacement(JTabbedPane.RIGHT);
		}
		
		if(s.equals("Defaults")){
			jtp.setTabPlacement(JTabbedPane.TOP);
		}
		
		if(s.equals("Scroll")){
			jtp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}
		
		if(s.equals("Wrap")){
			jtp.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		}
		
	}
	

public static void main(String[] args) throws FileNotFoundException{
	String filename = "contacts.txt";
	
	Rolodex rolodex = new Rolodex(filename);
	
	
	
}



	
}
