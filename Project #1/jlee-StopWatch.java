// 
// Name: Lee, Jong 
// Project: #1(Warm-up) 
// Due: 1/22/14
// Course: cs-245-01-w14 
// 
// Description: 
// Implement project 1-1 A SimpleStopWatch on page 24
// of the textbook and make the following modification:
// 1. Use the ae.getWhen() method to get the time instead of
// using the Calendar.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class StopWatch implements ActionListener {

	JLabel jlab;
	long start;
	
	StopWatch(){
		JFrame jfrm = new JFrame("A Simple Stopwatch");
		
		jfrm.getContentPane().setLayout(new FlowLayout());
		jfrm.setSize(230, 90);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton jbtnStart = new JButton("Start");
		JButton jbtnStop = new JButton("Stop");
		
		jbtnStart.addActionListener(this);
		jbtnStop.addActionListener(this);
		
		jfrm.getContentPane().add(jbtnStart);
		jfrm.getContentPane().add(jbtnStop);
		
		jlab = new JLabel("Press Start to begin timing.");
		
		jfrm.getContentPane().add(jlab);
		
		jfrm.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getActionCommand().equals("Start")){
			start = ae.getWhen();
			jlab.setText("Stopwatch is Running...");
		}
		else{
			jlab.setText("Elapsed time is " + (double) (ae.getWhen() - start) / 1000);
		}
	}
		
		public static void main(String args[]){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					new StopWatch();
				}
		});
}
}

		
		
		
	