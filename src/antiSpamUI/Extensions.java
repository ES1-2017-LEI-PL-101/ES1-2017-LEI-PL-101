package antiSpamUI;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class Extensions {

	/**
	 * This method is used to put rules in tables.
	 * 
	 * @param map
	 * @return DefaultTableModel This method returns the model table.
	 */
	public static DefaultTableModel toTableModel(Map<?, ?> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Rules", "Weight" }, 0);
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		return model;
	}

}
