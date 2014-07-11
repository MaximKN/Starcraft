package Starcraft;

import javax.swing.SwingUtilities;

public class TestDrive {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	new TaskSetter();
	        }
		});
	}
}
