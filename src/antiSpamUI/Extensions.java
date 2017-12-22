package antiSpamUI;

import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import antiSpamFilter.AntiSpamFilterProblem;

public class Extensions {

	/**
	 * This method is used to put rules in tables.
	 * 
	 * @param map
	 * @return DefaultTableModel This method returns the model table.
	 */
	public static DefaultTableModel toTableModel(Map<?, ?> map, boolean firstLoad) {

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Rules", "Weight" }, 0);
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if(firstLoad)
			model.addRow(new Object[] { entry.getKey(), 0.0 });
			else
			model.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		return model;
	}

}
