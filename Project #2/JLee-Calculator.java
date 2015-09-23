// 
// Name: Lee, Jong 
// Project: #2 
// Due: 2/7/14 
// Course: CS-245-01-w14 
// 
// Description: 
// Project 2 requires us to create a Calculator with functions
// like multiplying,adding,subtracting,dividing.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Calculator implements ActionListener {
	JPanel[] row = new JPanel[5];
	JButton[] button = new JButton[16];
	boolean[] functions = new boolean[4];
	int[] temp = {0,0};
	
	//Make an array of the buttons of the Calculator
	String[] buttons = {"7", "8", "9", "/",
						"4", "5", "6", "*",
						"1", "2", "3", "-",
						"0", "C", "=", "+"};

	//label for displaying the calculations
	JLabel display;
	ImageIcon icon = new ImageIcon("Calculator.png");
	
	Calculator(){
		JFrame jfrm = new JFrame("Calculator");
		jfrm.setIconImage(icon.getImage());
		jfrm.setSize(200,300);
		jfrm.setResizable(false);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.getContentPane().setLayout(new GridLayout(5,4));
		display = new JLabel();
		
		display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//making different panels for the rows
		for(int i = 0; i < 5; i++){
			row[i] = new JPanel();
		}
		
		//creating boolean functions
		for(int i = 0; i < 4; i++){
			functions[i] = false;
		}
		
		jfrm.getContentPane().add(display);
		
		//array to creating and setting each action command for the buttons
		for(int i = 0; i < 16; i++){
			button[i] = new JButton();
			button[i].setText(buttons[i]);
			button[i].setActionCommand(buttons[i]);
			button[i].addActionListener(this);
		}
		
		button[13].setMnemonic(KeyEvent.VK_SHIFT);
		
		jfrm.getRootPane().setDefaultButton(button[11]);
		display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		display.setText("0");
				
		//adding four buttons for each row
		for(int i = 0; i < 4; i++){
			row[1].add(button[i]);
			jfrm.add(row[1]);
		}
		
		for(int i = 4; i < 8; i++){
			row[2].add(button[i]);
			jfrm.add(row[2]);
		}

		for(int i = 8; i < 12; i++){
			row[3].add(button[i]);
			jfrm.add(row[3]);
		}
		
		for(int i = 12; i < 16; i++){
			row[4].add(button[i]);
			jfrm.add(row[4]);
		}		
		
		center(jfrm);
		jfrm.setVisible(true);
		
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
	

//function to clear the label when user presses "C"
public void clear(){
		display.setText("0");
		for(int i = 0; i < 4; i++){
			functions[i] = false;
		}
		for(int i = 0; i < 2; i++){
			temp[i] = 0;
		}
	}

//function to get a negative answer
public void getPositiveNegative(){
	int currentValue = Integer.parseInt(display.getText());
	
	if(currentValue < 0){
		currentValue = (-1) * currentValue;
		display.setText(Integer.toString(currentValue));
	}
	else{
	}
}

//function to get the answers
public void getResults(){
	int result = 0;
	temp[1] = Integer.parseInt(display.getText());
	
	//if function is set to true then we do the addition function
	if(functions[0] == true){
		result = temp[0] + temp[1];
	}
	
	//if function is set to true then we do the subtraction function
	else if(functions[1] == true){
		result = temp[0] - temp[1];
	}
	
	//if function is set to true then we do the multiplication function
	else if(functions[2] == true){
		result = temp[0] * temp[1];
	}
	
	//if function is set to true then we do the division function
	else if(functions[3] == true){
		result = temp[0] / temp[1];
	}
	
	display.setText(Integer.toString(result));
	for(int i = 0; i < 4; i++){
		functions[i] = false;
	}
}


public void actionPerformed(ActionEvent ae){
	//if the user presses 0 then it will set the text to 0 only if the first text is not 0
	//only prevent user from getting 00
	if(ae.getActionCommand().equals("0")){
		if(display.getText().equals("0")){
			return;
		}
		else{
		display.setText(display.getText() + "0");
	}
}
	
	if(ae.getActionCommand().equals("1")){
		if(display.getText().equals("0")){
			display.setText("1");
		}
		else{
			display.setText(display.getText() + "1");
		}
}
	
	if(ae.getActionCommand().equals("2")){
		if(display.getText().equals("0")){
			display.setText("2");
		}
		else{
			display.setText(display.getText() + "2");
	}
}
	
	if(ae.getActionCommand().equals("3")){
		if(display.getText().equals("0")){
			display.setText("3");
		}
		else{
			display.setText(display.getText() + "3");
	}
}
	
	if(ae.getActionCommand().equals("4")){
		if(display.getText().equals("0")){
			display.setText("4");
		}
		else{
			display.setText(display.getText() + "4");
	}
}
	
	if(ae.getActionCommand().equals("5")){
		if(display.getText().equals("0")){
			display.setText("5");
		}
		else{
			display.setText(display.getText() + "5");
	}
}
	
	if(ae.getActionCommand().equals("6")){
		if(display.getText().equals("0")){
			display.setText("6");
		}
		else{
			display.setText(display.getText() + "6");
	}
}
	
	if(ae.getActionCommand().equals("7")){
		if(display.getText().equals("0")){
			display.setText("7");
		}
		else{
			display.setText(display.getText() + "7");
	}
}
	
	if(ae.getActionCommand().equals("8")){
		if(display.getText().equals("0")){
			display.setText("8");
		}
		else{
			display.setText(display.getText() + "8");
	}
}
	
	if(ae.getActionCommand().equals("9")){
		if(display.getText().equals("0")){
			display.setText("9");
		}
		else{
			display.setText(display.getText() + "9");
	}
}
		
	if(ae.getActionCommand().equals("C")){
		clear();
	}
	
	if(ae.getActionCommand().equals("=")){
		getResults();
	}
	
	if(ae.getActionCommand().equals("+")){
		temp[0] = Integer.parseInt(display.getText());
		functions[0] = true;
		display.setText("");
	}
	
	if(ae.getActionCommand().equals("-")){
		temp[0] = Integer.parseInt(display.getText());
		functions[1] = true;
		display.setText("");
	}
	
	if(ae.getActionCommand().equals("*")){
		temp[0] = Integer.parseInt(display.getText());
		functions[2] = true;
		display.setText("");
	}
	
	if(ae.getActionCommand().equals("/")){
		temp[0] = Integer.parseInt(display.getText());
		functions[3] = true;
		display.setText("");
	}
}

public static void main(String args[]){
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			new Calculator();
		}
	});
	}
}