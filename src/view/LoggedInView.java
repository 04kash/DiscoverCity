package view;

import interface_adapter.displayingLabels.DisplayingLabelsController;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

	public static final String viewName = "logged in";
	private static final long serialVersionUID = 1L;
	public final DisplayingLabelsController displayingLabelsController;

	/**
	 * Create the panel.
	 */
	public LoggedInView(DisplayingLabelsController displayingLabelsController) {
		this.displayingLabelsController=displayingLabelsController;
		this.setLayout(null);

		JButton locationSearch = new JButton("Search New Locations");
		locationSearch.setBounds(93, 78, 272, 23);
		add(locationSearch);

		locationSearch.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ViewManager.showSearchView();
					}
				}
		);

		JButton planner = new JButton("View Your Planner");
		planner.setBounds(93, 139, 272, 23);
		add(planner);
		planner.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ViewManager.showPlannerView();
						displayingLabelsController.execute();
					}
				}
		);

		JButton logOut = new JButton("Log out");
		logOut.setBounds(93, 192, 272, 23);
		add(logOut);
		logOut.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ViewManager.showLoginView();
					}
				}
		);
	}

	public void actionPerformed(ActionEvent evt) {
		System.out.println("Click " + evt.getActionCommand());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
