package View;
import java.awt.Component;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;


@SuppressWarnings("serial")
public class IconList 
	extends DefaultListCellRenderer {

	private Map<Object, Icon> icons = null;
	
	public IconList(Map<Object, Icon> icons) {
		this.icons = icons;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(
		JList list, Object value, int index, 
		boolean isSelected, boolean cellHasFocus) {
		
		// Get the renderer component from parent class
		
		JLabel label = 
			(JLabel) super.getListCellRendererComponent(list, 
				value, index, isSelected, cellHasFocus);
		
		// Get icon to use for the list item value
		
		Icon icon = icons.get(value);
		
		// Set icon to display for value
		
		label.setIcon(icon);
		return label;
	}
}

