package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class PayDayCards extends JFrame {

	public ClassLoader cldr;
	int mailCardCount = 0, dealCardCount = 0;
	String[][] mailCards = new String[48][6];
	String[][] dealCards = new String[20][8];

	public PayDayCards() {
		
		javax.swing.UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		javax.swing.UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		cldr = this.getClass().getClassLoader();
		this.setResizable(false);
		this.setTitle("Cards");
		this.setPreferredSize(new Dimension(405, 200));
		//set Mail Button
		JButton mailButton = new JButton("Get Mail Card");
		mailButton.setName("Mail");
		mailButton.setSize(200, 200);
		JLayeredPane basic_panel = new JLayeredPane();
		basic_panel.setSize(405, 200);
		this.add(basic_panel);
		basic_panel.add(mailButton);
		mailButton.addActionListener(new CardListener());
		
		//set Deal Button
		JButton dealButton = new JButton("Get Deal Card");
		dealButton.setName("Deal");
		dealButton.setSize(200, 200);
		dealButton.setBounds(200, 0, 200, 200);
		dealButton.addActionListener(new CardListener());
		basic_panel.add(dealButton);
		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(basic_panel, GroupLayout.PREFERRED_SIZE, 910, GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(basic_panel, GroupLayout.PREFERRED_SIZE, 685, GroupLayout.PREFERRED_SIZE));
		pack();
		basic_panel.repaint();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		readFile("resources/dealCards.csv", "Deal");
		readFile("resources/mailCards.csv", "Mail");
	}

	@SuppressWarnings("unused")
	public void readFile(String path, String type) {
		BufferedReader br = null;
		String sCurrentLine;
		try {
			String fullPath = cldr.getResource(path).getPath();
			br = new BufferedReader(new FileReader(fullPath));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(PayDayCards.class.getName()).log(Level.SEVERE, null, ex);
		}
		int count = 0;
		int splitCount = 0;
		HashMap<Integer, String> domainsMap = new HashMap<>();
		try {
			br.readLine();
			while ((sCurrentLine = br.readLine()) != null) {
				if (type.equals("Mail")) {
					mailCards[count++] = sCurrentLine.split(",");
				} else {
					dealCards[count++] = sCurrentLine.split(",");
				}
			}
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(PayDayCards.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@SuppressWarnings({ "static-access", "unused" })
	public void showMailCard(int i) {
		Object[] options = {mailCards[i][3]};
		URL imageURL = cldr.getResource("resources/images/" + mailCards[i][5]); //image
		Image image = new ImageIcon(imageURL).getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		System.out.println("Type: " + mailCards[i][0] + "\nTypeEn: " + mailCards[i][1]
				+ "\nMessage:" + mailCards[i][2] + "\nChoice: " + mailCards[i][3] + "\nEuro:" + Integer.parseInt(mailCards[i][4]));
		JOptionPane p = new JOptionPane();
		int n = p.showOptionDialog(this,
				mailCards[i][2],
				mailCards[i][0],
				JOptionPane.OK_OPTION,
				0,
				new ImageIcon(image),
				options,
				options[0]);
	}
	
	@SuppressWarnings("static-access")
	public int showDealCard(int i) {
		Object[] options = {dealCards[i][6], dealCards[i][7]};
		URL imageURL = cldr.getResource("resources/images/" + dealCards[i][5]); //image
		System.out.println("Type: " + dealCards[i][0] + "\nTypeEn: " + dealCards[i][1]
				+ "\nMessage: " + dealCards[i][2] + "\nCost:" + Integer.parseInt(dealCards[i][3])
				+ "\nValue:" + Integer.parseInt(dealCards[i][4]) + "\nChoice1: " + dealCards[i][6] + "\nChoice2: " + dealCards[i][7]);
		Image image = new ImageIcon(imageURL).getImage();
		image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		JOptionPane p = new JOptionPane();
		
		int selection = p.showOptionDialog(this,
				dealCards[i][2] + "\nBuy Price: " + dealCards[i][3] + " $\nSell Price: " + dealCards[i][4] + " $ \n",
				dealCards[i][0],
				JOptionPane.OK_OPTION,
				0,
				new ImageIcon(image),
				options,
				options[0]);
		
		return selection;
	}

	private class CardListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button.getName().equals("Mail")) {
				int x = mailCardCount++;
				if (mailCardCount == 48) {
					mailCardCount = 0;
				}
				showMailCard(x);
			} else if (button.getName().equals("Deal")) {
				int x = dealCardCount++;
				if (dealCardCount == 20) {
					dealCardCount = 0;
				}
				showDealCard(x);
			}
		}
	}

	public void printArray(String[][] arr) {
		for(int i = 0; i < arr.length; i++)
		{
		    for(int j = 0; j < arr[i].length; j++)
		    {
		        System.out.print(arr[i][j]);
		        if(j < arr[i].length - 1) System.out.print(" ");
		    }
		    System.out.println();
		}
	}
	
	public String[][] getMailCards() {return mailCards;}
	public String[][] getDealCards() {return dealCards;}
}
