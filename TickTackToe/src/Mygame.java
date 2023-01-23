import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.*;

public class Mygame extends JFrame implements ActionListener {
	JLabel heading, clock;
    Font font = new Font("", Font.BOLD, 40);
    JPanel panel;
    JButton [] btns = new JButton[9];
    int [][]wps = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    
    // game instance
    
    int [] gamechances = {2,2,2,2,2,2,2,2,2};
    int activePlayer =0;
    boolean gameover = false;

	public Mygame() {
		this.setTitle("Tick Tack Toe game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(1440,900);
	    //this.pack();
		
		ImageIcon img = new ImageIcon("src/images/symbol.jpg");
		this.setIconImage(img.getImage());
		//this.isResizable();
		createGUI();
		this.setVisible(true);
	}
	private void createGUI() {
		this.getContentPane().setBackground(Color.decode("#1296f3"));
		this.setLayout(new BorderLayout());
		
		// heading setting
		
		heading = new JLabel("Tick Tack Toe");
		heading.setFont(font);
	    heading.setHorizontalAlignment(SwingConstants.CENTER);
	    heading.setIcon(new ImageIcon("src/images/ss.jpg"));
	    heading.setForeground(Color.white);
	    
	    this.add(heading,BorderLayout.NORTH);
	    
	    // clock setting
	    
	    clock = new JLabel();
	    clock.setFont(font);
	    clock.setHorizontalAlignment(SwingConstants.CENTER);
	    clock.setForeground(Color.white);
	    
         this.add(clock,BorderLayout.SOUTH);
         
         Thread t = new Thread() {
        	 public void run() {
        		 try {
        			 while(true) {
        				String date = new Date().toLocaleString();
        				
        				clock.setText(date);
        				Thread.sleep(1000);
        			 }
        		 }
        		 catch(Exception e) { 
        			 e.printStackTrace();
        			 
        		 }
        		 
        	 }
         };
         t.start();
         
         // buttons setting
         
          panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));  
        
         for(int i =1; i<=9; i++) {
        	 JButton btn = new JButton("");
        	 
        	  btn.setBackground(Color.decode("#90caf9"));
        	 btn.setFont(font);
        	 btn.addActionListener(this);
        	 btns[i-1]=btn;
        	 panel.add(btn);
        	 btn.setName(String.valueOf(i-1));
         }
         this.add(panel,BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton currentButton = (JButton)e.getSource();
		
		String namestr = currentButton.getName();
		
		int name = Integer.parseInt(namestr.trim());
		if(gameover) {
			
			JOptionPane.showMessageDialog(this, "Game is Already over...");
			return;
		}
		
		if(gamechances[name]==2) {
			if(activePlayer == 0) {
				currentButton.setIcon(new ImageIcon("src/images/zero2.png"));
				gamechances[name]=activePlayer;
				activePlayer=1;
			}else {
				currentButton.setIcon(new ImageIcon("src/images/cross.png"));
				gamechances[name]=activePlayer;
				activePlayer = 0;
			}
			
			for(int temp[]:wps) {
				if(gamechances[temp[0]]==gamechances[temp[1]] && gamechances[temp[1]]==gamechances[temp[2]] && gamechances[temp[2]]!=2) {
					gameover = true;
					JOptionPane.showMessageDialog(null, "Player "+gamechances[temp[0]]+" has won");
					int i =JOptionPane.showConfirmDialog(this, "Do you want to play more");
					if(i==0) {
					   this.setVisible(false);
					   new Mygame();
					} else if(i==1) {
						System.exit(2355);
					}else {
						
					}
					break;
				}
			}
			
		}else {
			JOptionPane.showMessageDialog(this,"this position is already occupied");
		}
		int c=0;
		for(int k=0; k<9; k++) {
			if(gamechances[k]==2) break;
			else c++;
		}
		if(c>=8 && gameover==false) {
			JOptionPane.showMessageDialog(null, "Draw Match");
			int i =JOptionPane.showConfirmDialog(this, "Do you want to play more");
			if(i==0) {
			   this.setVisible(false);
			   new Mygame();
			} else if(i==1) {
				System.exit(2355);
			}
			gameover = true;
		}
	}

}
