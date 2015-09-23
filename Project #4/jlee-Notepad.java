// 
// Name: Lee, Jong 
// Project: #4 
// Due: 3/14/14
// Course: cs-245-01-w14 
// 
// Description: 
// Project 4 requires us to create a Notepad program with the following functions.
// 

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.util.Scanner;
import java.io.*;



public class NotePad implements ActionListener,ListSelectionListener {
	String fileList = "New Open... Save Save_As... Page_Setup Print... Exit";
	String editList = "Undo Cut Copy Paste Delete Find Find_Next Replace... Go_To... Select_All Time/Date";
	JTextArea textarea = new JTextArea("", 0,0);
	JMenuBar menubar;
	JMenu file;
	JMenu edit;
	JMenu format;
	JMenu view;
	JMenu help;
	JMenuItem wordWrap;
	JMenuItem font;
	JMenuItem statusBar;
	JMenuItem viewHelp;
	JMenuItem about;
	JFrame jfrm;
	JPanel jp;
	JDialog findg;
	boolean matchCase;
	String dofind = null;

	String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	JPanel directionpanel;
	JToolBar jtb = new JToolBar();
	ImageIcon cut = new ImageIcon("cut.png");
	ImageIcon copy = new ImageIcon("copy.png");
	ImageIcon paste = new ImageIcon("paste.png");
	
	JTextArea findtext;
	JLabel findwhat;
	JLabel direction;
	JRadioButton up;
	JRadioButton down;
	JButton findnext;
	JButton findcancel;
	JCheckBox findmatch;
	ButtonGroup bg;
	JPanel find;
	JButton cutc;
	JButton copyc;
	JButton pastec;
	private boolean findCase = false;
	
	JPanel fontpanel1;
	JPanel fontpanel2;
	JPanel fontpanel3;
	JLabel fontLabel;
	JLabel sizeLabel;
	JLabel fontTypeLabel;
	JLabel previewLabel;
	JTextField label;
	JTextField fontText;
	JTextField sizeText;
	JTextField fontTypeText;
	JScrollPane fontScroll;
	JScrollPane typeScroll;
	JScrollPane sizeScroll;
	JList fontList;
	JList sizeList;
	JList fontTypeList;
	JButton fontOk;
	JButton fontCancel;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	JDialog fontDialog;
	
	NotePad(){
		jfrm = new JFrame("Notepad");
		jfrm.setSize(400,300);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLayout(new BorderLayout());
			
		menubar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		
		JButton cutc = new JButton("Cut");
		cutc.setActionCommand("Cut");
		cutc.addActionListener(this);
		
		JButton copyc = new JButton("Copy");
		copyc.setActionCommand("Copy");
		copyc.addActionListener(this);
		
		JButton pastec = new JButton("Paste");
		pastec.setActionCommand("Paste");
		pastec.addActionListener(this);
		
		jtb.add(cutc);
		jtb.add(copyc);
		jtb.add(pastec);
		
		jfrm.add(jtb, BorderLayout.NORTH);
		
		findtext = new JTextArea(0,20);
		findwhat = new JLabel("Find_what:");
		direction = new JLabel("Direction");
		up = new JRadioButton("Up");
		up.setMnemonic(KeyEvent.VK_U);
		down = new JRadioButton("Down");
		down.setMnemonic(KeyEvent.VK_D);
		findnext = new JButton("Find Next");
		findnext.addActionListener(this);
		findcancel = new JButton("Cancel");
		findcancel.addActionListener(this);
	    findmatch = new JCheckBox("Match case");
	    findmatch.setMnemonic(KeyEvent.VK_C);
		bg = new ButtonGroup();
		find = new JPanel();
		jp = new JPanel();
		
		
		//Creating a File menu along with file menu items
		String[] fileMenuItems = fileList.split(" ");
			for(int i = 0; i < fileMenuItems.length; i++){
				JMenuItem item = new JMenuItem(fileMenuItems[i]);
			
			if(fileMenuItems[i].equals("New")){
				file.add(item);
				item.addActionListener(this);
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			}
			
			if(fileMenuItems[i].equals("Open...")){
				file.add(item);
				item.addActionListener(this);
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
			}
			
			if(fileMenuItems[i].equals("Save")){
				file.add(item);
				item.addActionListener(this);
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
			}
				
			if(fileMenuItems[i].equals("Save_As...") || fileMenuItems[i].equals("Print...")){
				file.add(item);
				item.addActionListener(this);
				file.addSeparator();
			}
			
			if(fileMenuItems[i].equals("Page_Setup...")){
				file.add(item);
				item.addActionListener(this);
				item.setMnemonic(KeyEvent.VK_U);;
			}
			
			if(fileMenuItems[i].equals("Print...")){
				file.add(item);
				item.addActionListener(this);
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
			}
			
			if(fileMenuItems[i].equals("Exit")){
				file.add(item);
				item.addActionListener(this);
				item.setMnemonic(KeyEvent.VK_X);
			}
			
			else{
				file.add(item);
				item.addActionListener(this);
			}
			
			menubar.add(file);

			
			}
			
			//Creating a Edit menu along with the edit menu items
			String[] editMenuItems = editList.split(" ");
			for(int i = 0; i < editMenuItems.length; i++){
				JMenuItem editItem = new JMenuItem(editMenuItems[i]);
				
			if(editMenuItems[i].equals("Undo") || editMenuItems[i].equals("Delete") || editMenuItems[i].equals("Go_To...")){
				edit.add(editItem);
				editItem.addActionListener(this);
				edit.addSeparator();
			}
			
			if(editMenuItems[i].equals("Cut")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
			}

			if(editMenuItems[i].equals("Copy")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
			}

			if(editMenuItems[i].equals("Paste")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
			}
			

			if(editMenuItems[i].equals("Delete")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
			}
			
			if(editMenuItems[i].equals("Select_All")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
			}
			
			if(editMenuItems[i].equals("Replace...")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));
			}
			
			if(editMenuItems[i].equals("Go_To...")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
			}
			

			if(editMenuItems[i].equals("Find")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
			}
			

			if(editMenuItems[i].equals("Find_Next")){
				KeyStroke f3keyStroke = KeyStroke.getKeyStroke("F3");
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(f3keyStroke);
			}
			
			if(editMenuItems[i].equals("Time/Date")){
				edit.add(editItem);
				editItem.addActionListener(this);
				editItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F5));
			}
			
			else{
				edit.add(editItem);
				editItem.addActionListener(this);
			}
			}
			
			
			//Making the rest of the menus and adding it to the menubar
			menubar.add(edit);
	
			format = new JMenu("Format");
			format.setMnemonic(KeyEvent.VK_O);
			wordWrap = new JMenuItem("Word Wrap");
			wordWrap.setMnemonic(KeyEvent.VK_W);
			font = new JMenuItem("Font...");
			font.setMnemonic(KeyEvent.VK_F);
			wordWrap.addActionListener(this);
			font.addActionListener(this);
			
			format.add(wordWrap);
			format.add(font);
			
			
			menubar.add(format);
			
			view = new JMenu("View");
			view.setMnemonic(KeyEvent.VK_V);
			statusBar = new JMenuItem("Status Bar");
			statusBar.addActionListener(this);
			view.add(statusBar);
			
			menubar.add(view);
			
			help = new JMenu("Help");
			help.setMnemonic(KeyEvent.VK_H);
			viewHelp = new JMenuItem("View Help");
			viewHelp.addActionListener(this);
			about = new JMenuItem("About Notepad");
			about.addActionListener(this);
			
			help.add(viewHelp);
			help.addSeparator();
			help.add(about);
			
			
			menubar.add(help);
			
			
			findg = new JDialog(jfrm,"Find",false);
			
			findnext = new JButton("Find_Next");
			findnext.addActionListener(this);
			
			findcancel = new JButton("Cancel");
			findcancel.addActionListener(this);
			
			jfrm.setJMenuBar(menubar);
			jfrm.add(textarea);
			
			fontDialog = new JDialog();
			fontDialog.setTitle("Fonts");
			fontDialog.setSize(500,400);

			gbl = new GridBagLayout();
			fontDialog.setLayout(gbl);
			
			gbc = new GridBagConstraints();
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			
			gbc.anchor = GridBagConstraints.WEST;
			fontLabel = new JLabel("Fonts: ");
			fontDialog.add(fontLabel, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			sizeLabel = new JLabel("Sizes: ");
			fontDialog.add(sizeLabel, gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			fontTypeLabel = new JLabel("Font Types: ");
			fontDialog.add(fontTypeLabel, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			fontText = new JTextField("Times New Roman", 10);
			fontDialog.add(fontText, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			sizeText = new JTextField("8",4);
			fontDialog.add(fontText, gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			fontTypeText = new JTextField("Regular", 8);
			fontDialog.add(sizeLabel, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			fontList = new JList(fonts);
			fontList.setFixedCellWidth(110);
			fontList.addListSelectionListener(this);
			fontList.setSelectedIndex(0);
			fontScroll = new JScrollPane(fontList);
			fontDialog.add(fontScroll,gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			String[] fontSizes = {"8","10","12","14","16","18","20","24","28","32","48","72"};
			sizeList = new JList(fontSizes);
			sizeList.setFixedCellWidth(20);
			sizeList.addListSelectionListener(this);
			sizeList.setSelectedIndex(0);
			sizeScroll = new JScrollPane(sizeList);
			fontDialog.add(sizeScroll,gbc);
			
			gbc.gridx = 3;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.WEST;
			String[] fontTypes = {"Regular", "Bold", "Italic", "Bold Italic"};
			fontTypeList = new JList(fontTypes);
			fontTypeList.setFixedCellWidth(60);
			fontTypeList.addListSelectionListener(this);
			fontTypeList.setSelectedIndex(0);
			typeScroll = new JScrollPane(fontTypeList);
			fontDialog.add(typeScroll,gbc);
			
			
			gbc.gridx = 1;
			gbc.gridy = 4;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.CENTER;
			fontpanel1 = new JPanel();
			fontpanel1.setLayout(new FlowLayout());
			previewLabel = new JLabel("Preview: ");
			fontpanel1.add(previewLabel);
			fontDialog.add(fontpanel1,gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 5;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.CENTER;
			fontpanel2 = new JPanel();
			fontpanel2.setLayout(new FlowLayout());
			label = new JTextField("AaBbCcDdEeFfGg",15);
			label.setEditable(false);
			label.setBorder(BorderFactory.createEtchedBorder());
			label.setFont(new Font("Arial", Font.PLAIN, 16));
			fontpanel2.add(label);
			fontDialog.add(fontpanel2, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 6;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			gbc.anchor = GridBagConstraints.CENTER;
			fontpanel3 = new JPanel();
			fontpanel3.setLayout(new FlowLayout());
			fontOk = new JButton("OK");
			fontCancel = new JButton("Cancel");
			fontpanel3.add(fontOk);
			fontOk.addActionListener(this);
			fontpanel3.add(fontCancel);
			fontCancel.addActionListener(this);
			fontDialog.add(fontpanel3,gbc);
			
			jfrm.setVisible(true);
	}
	
	
	public void valueChanged(ListSelectionEvent le){
		try{
			if(le.getSource() == fontList){
				Font f2 = new Font(String.valueOf(fontList.getSelectedValue()), fontTypeList.getSelectedIndex(), Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
				Integer.parseInt(String.valueOf(sizeList.getSelectedValue()));
				fontText.setText(String.valueOf(fontList.getSelectedValue()));
				label.setFont(f2);
			}
			
			
			 else if(le.getSource() == sizeList)
	         {
	            Font f2 = new Font(String.valueOf(fontList.getSelectedValue()),fontTypeList.getSelectedIndex(),
	            Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
	            sizeText.setText(String.valueOf(sizeList.getSelectedValue()));
	            label.setFont(f2);
	         }

			
			 else if(le.getSource() == fontTypeList)
	         {
	            Font f2 = new Font(String.valueOf(fontList.getSelectedValue()),fontTypeList.getSelectedIndex(),
	            Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
	            fontTypeText.setText(String.valueOf(fontTypeList.getSelectedValue()));
	            label.setFont(f2);
	         }
	      }
	      catch(Exception e)
	      {
	    	  System.out.println("Error");
	      }
	   }
		
	
	public Font font()
	{
		Font font = new Font(String.valueOf(fontList.getSelectedValue()), fontTypeList.getSelectedIndex(),
		Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
		return font;
	}
	
	
	public void findText(){
        int index=0;
		String findstr = findtext.getText().toUpperCase();
		int findstrLength = findstr.length();                   
		String findtextarea = textarea.getText().toUpperCase();
		Highlighter h = textarea.getHighlighter();
		h.removeAllHighlights();
		try
		    {
		        while(index >= 0)                     
		        {
		            index = findtextarea.indexOf(findstr,index);
		            h.addHighlight(index,index+findstrLength, DefaultHighlighter.DefaultPainter);
		            index++;
		        }
		    }
		catch(Exception e){
			System.out.println("There is no word like that");
		}
	}
	
    //Implementing methods for the actions performed.
	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("New")){
			textarea.setText("");
		}
		
		//Action for opening a file
		if(ae.getActionCommand().equals("Open...")){
			JFileChooser open = new JFileChooser();
			int option = open.showDialog(jfrm, "Choose a file");
			
			if(option == JFileChooser.APPROVE_OPTION){
				textarea.setText("");
				try{
					Scanner scan = new Scanner(new FileReader(open.getSelectedFile().getPath()));
					while(scan.hasNext()){
						textarea.append(scan.nextLine() + "\n");
					}
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		} //End of the open file action
		
		//Action for save file
		if(ae.getActionCommand().equals("Save")){
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(jfrm);
			
			if(option == JFileChooser.APPROVE_OPTION){
				try{
					BufferedWriter bw = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					//write the contents to the file
					bw.write(textarea.getText());
					bw.close();
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
				
				
		}//End of save file action
		
		//Action for save as file
		if(ae.getActionCommand().equals("Save_As...")){
			JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(jfrm);
			
			if(option == JFileChooser.APPROVE_OPTION){
				try{
					BufferedWriter bw = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
					//write the contents to the file
					bw.write(textarea.getText());
					bw.close();
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
		}//End of save as file action
		
		//Action to cut
		if(ae.getActionCommand().equals("Cut")){
			textarea.cut();
		}
		
		//Action to copy
		if(ae.getActionCommand().equals("Copy")){
			textarea.copy();
		}
		
		//Action to paste
		if(ae.getActionCommand().equals("Paste")){
			textarea.paste();
		}
		
		//Action to delete
		if(ae.getActionCommand().equals("Delete")){
			textarea.replaceSelection(null);
		}
		
		if(ae.getActionCommand().equals("Find")){
			findg.setBounds(100,100,440,215);
			findg.setVisible(true);
			
			jp.setBorder(new EmptyBorder(5,5,5,5));
			jp.setLayout(null);
			
			findnext.setBounds(312,25,89,23);
			jp.add(findnext);
			
			findcancel.setBounds(312,55,89,23);
			jp.add(findcancel);
			
			findwhat.setBounds(10,30,75,23);
			jp.add(findwhat);
			
			findtext.setBounds(93,30,183,34);
			jp.add(findtext);
			findtext.setColumns(10);
			
			findmatch.setBounds(10,147,97,23);
			jp.add(findmatch);
			
			direction.setBounds(221,109,89,14);
			jp.add(direction);
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(up);
			up.setBounds(184,129,53,23);
			jp.add(up);
			bg.add(down);
			down.setBounds(239,129,109,23);
			jp.add(down);
			findg.add(jp);
					
         }
		
		if(ae.getActionCommand().equals("Find_Next")){
			findText();
		}
		
		if(ae.getActionCommand().equals("Cancel")){
			findg.dispose();
			findg.setVisible(false);
		}
		
		if(ae.getActionCommand().equals("Font..."))
		{
			fontDialog.setVisible(true);
		}
		
		if(ae.getActionCommand().equals("OK")){
			Font f2 = new Font(String.valueOf(fontList.getSelectedValue()), fontTypeList.getSelectedIndex(), Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
			Integer.parseInt(String.valueOf(sizeList.getSelectedValue()));
			fontText.setText(String.valueOf(fontList.getSelectedValue()));
			textarea.setFont(f2);
			
			
			
            fontDialog.dispose();
			fontDialog.setVisible(false);
		}
		
		if(ae.getActionCommand().equals("CANCEL"))
		{
			fontDialog.dispose();
			fontDialog.setVisible(false);
		}
		
		if(ae.getActionCommand().equals("Word Wrap")){
			textarea.setColumns(20);
			textarea.setRows(20);
			textarea.setLineWrap(true);
			textarea.setWrapStyleWord(true);
			
			JScrollPane sp = new JScrollPane(textarea);
			jp.add(sp);
			jfrm.add(jp);
			jfrm.setLocationRelativeTo(null);
			jfrm.pack();
		}
		
		//Action for about notepad
		if(ae.getActionCommand().equals("About Notepad")){
			JDialog dialogAbout = new JDialog(jfrm,"About",true);
			JLabel labelAbout = new JLabel("(c)Jong Woo Lee");
			dialogAbout.setLayout(new FlowLayout());
			dialogAbout.add(labelAbout);
			dialogAbout.setSize(200,150);
			dialogAbout.setVisible(true);
		}
		
		//Action for closing the notepad
		if(ae.getActionCommand().equals("Exit")){
			int response = JOptionPane.showOptionDialog(jfrm, "Do you want to save changes to Untitled?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,null,JOptionPane.CANCEL_OPTION);
			switch(response){
			case JOptionPane.YES_OPTION:
				JFileChooser save = new JFileChooser();
				int option = save.showSaveDialog(jfrm);
				
				if(option == JFileChooser.APPROVE_OPTION){
					try{
						BufferedWriter bw = new BufferedWriter(new FileWriter(save.getSelectedFile().getPath()));
						//write the contents to the file
						bw.write(textarea.getText());
						bw.close();
					}
					catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
				break;
				
			case JOptionPane.NO_OPTION:
				jfrm.dispose();
				break;
			
			case JOptionPane.CANCEL_OPTION:
				 break;
			}
		}
		}	
		

	public static void main(String[] args){
		new NotePad();
	}
	}