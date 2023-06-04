package View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;

import java.awt.*;

import Model.Cards.DealCard;
/**
 * A window that contains all the cards of a player
 * @author csd4623
 *
 */
@SuppressWarnings("serial")
public class CardInventoryUI extends JFrame {
	private ArrayList<DealCard> inventory;
	@SuppressWarnings("rawtypes")
	private JList list;
	private DealCard selected;
	/**
	 * <b>constructor</b>: Creates a new Window and 
	 * initializes some buttons and panels <br>
	 * <b>postconditions</b>: Creates a new Window 
	 * and shows to the player the deal cards in his inventory<br>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardInventoryUI(ArrayList<DealCard> inventory) {
		this.inventory = inventory;
		this.setTitle("Deal Card Inventory");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Map<Object, Icon> icons = new HashMap<Object, Icon>();
		Object[] cards = new Object[inventory.size()];
		int i = 0;
		for(DealCard card: inventory) {
			ImageIcon img = new ImageIcon("src/resources/images/"+card.getInfo()[5]);
			img.setImage(GraphicUI.getScaledImage(img.getImage(), 60,60));
			cards[i] = "Cost: "+card.getCost()+", Value: "+card.getValue();
			icons.put(cards[i], img);
			i++;
			
		}
		list = new JList(cards);
		list.setCellRenderer(new IconList(icons));	
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel.add(scrollPane);
		add(panel);
		pack();
		setVisible(false);
	}
	
	public void viewer() {
		setVisible(true);
	}
	public void buyerMode(Controller controller) {
		this.setTitle("Select a card to sell");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if(!event.getValueIsAdjusting()) {
					int idx = list.getSelectedIndex();
					if (idx != -1) {
						getJFrame().dispose();
						selected = inventory.get(idx);
						controller.buyerCardSelected(selected);
					}
					else
						System.out.println("Please select a card");
				}
			}
		});
		this.setVisible(true);
	}
	public JFrame getJFrame() {return this;}
}
