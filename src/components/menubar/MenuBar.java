package components.menubar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Class holding the MenuBar component to be called into main
 *
 * @author Kevin Mosekjaer
 */
public class MenuBar {

	/**
	 * Menu bar holding options
	 */
	private static JMenuBar menuBar;

	/**
	 * Menu options
	 */
	private static JMenu file, game, network, language, help;

	/**
	 * Sub menu options
	 */
	private static JMenuItem english, french, host, connect, disconnect, how, about, restart, save, load, option;
	private static JDialog dialogConnect, dialogHost;
	private static JTextField hostName, connectName, address, hostStatus, clientStatus;
	private static JButton connectS, hostS, cancelC, cancelH;
	private static JComboBox<String> hostPortSelection, connectPortSelection;
	private static String portChoices[] = {"0","80","50","100"};

	/**
	 * Constructor for MenuBar
	 */
	public MenuBar() {
		initializeMenuBar();
		displayConnectNetworkGUI();
		displayHostNetworkGUI();
	}

	/**
	 * Getter for the menu bar
	 *
	 * @return JMenuBar menu
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}


	public JDialog getConnectMenu() {
		return dialogConnect;
	}

	public JDialog getHostMenu() {
		return dialogHost;
	}

	/**
	 * Method creating the menu bar, menu options, and sub menu
	 */
	private static void initializeMenuBar() {
		menuBar=new JMenuBar();

		file = new JMenu("File");
		game = new JMenu("Game");
		network = new JMenu("Network");
		language = new JMenu("Language");
		help = new JMenu("Help");

		// Sub menu options for file
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		save.setBorder(new EmptyBorder(0,2,0,30));
		file.add(save);
		file.add(load);

		// Sub menu for Game
		restart = new JMenuItem("Restart");
		option = new JMenuItem("Options");
		restart.setBorder(new EmptyBorder(0,2,0,19));
		game.add(restart);
		game.add(option);


		// Sub menu for network
		host = new JMenuItem("Host");
		connect = new JMenuItem("Connect");
		disconnect = new JMenuItem("Disconnect");
		host.setBorder(new EmptyBorder(0,2,0,45));

		//host.add(dialogHost);
		//connect.add(dialogConnect);

		network.add(host);
		network.add(connect);
		network.add(disconnect);

		// Sub menu options for language
		english = new JMenuItem("English");
		french = new JMenuItem("French");
		language.add(english);
		english.setBorder(new EmptyBorder(0,2,0,23));
		language.add(french);

		// Sub menu for help
		how = new JMenuItem("How to Play");
		about = new JMenuItem("About");
		how.setBorder(new EmptyBorder(0,2,0,0));
		help.add(how);
		help.add(about);

		// Borders to make things more even
		file.setBorder(new EmptyBorder(5,25,5,25));
		game.setBorder(new EmptyBorder(5,20,5,20));
		network.setBorder(new EmptyBorder(5,18,5,18));
		language.setBorder(new EmptyBorder(5,10,5,10));
		help.setBorder(new EmptyBorder(5,25,5,30));

		// Adds menu bar options
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(language);
		menuBar.add(help);

	}

	public static void displayHostNetworkGUI() {
		// main setup
		dialogHost = new JDialog();
		dialogHost.setTitle("Host Server");
		dialogHost.setModal(true);
		ImageIcon icon = new ImageIcon(MenuBar.class.getResource("/assets/Connect4_Logo.png"));
		dialogHost.setIconImage(icon.getImage());
		JPanel main = new JPanel();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		Border border = new EmptyBorder(new Insets(5,5,5,10));
		Border text = new EmptyBorder(new Insets(0,5,0,5));
		Border label = new EmptyBorder(0,5,0,15);
		Border empty = new EmptyBorder(0,0,0,0);

		// name section
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(border);
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBorder(label);
		hostName = new JTextField();
		hostName.setBorder(text);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		hostName.setFont(new Font("Dialog", Font.PLAIN, 14));
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(hostName);

		JPanel portPanel = new JPanel(new BorderLayout());
		portPanel.setBorder(border);
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBorder(label);
		hostPortSelection = new JComboBox<>(portChoices);
		hostPortSelection.setBorder(empty);
		hostPortSelection.setEditable(true);
		hostPortSelection.setSelectedIndex(0);
		hostPortSelection.setBackground(Color.WHITE);
		hostPortSelection.setPreferredSize(new Dimension(150,20));
		portLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		hostPortSelection.setFont(new Font("Dialog", Font.PLAIN, 14));
		portPanel.add(portLabel, BorderLayout.WEST);
		portPanel.add(hostPortSelection, BorderLayout.EAST);



		// status section
		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setBorder(border);
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBorder(label);
		hostStatus = new JTextField();
		hostStatus.setBorder(text);
		hostStatus.setPreferredSize(new Dimension(150,20));
		hostStatus.setEditable(false);
		statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		hostStatus.setFont(new Font("Dialog", Font.PLAIN, 14));
		statusPanel.add(statusLabel, BorderLayout.WEST);
		statusPanel.add(hostStatus);

		// Buttons section
		JPanel buttonPanel = new JPanel(new GridLayout());
		JPanel hostPanel = new JPanel(new BorderLayout());
		JPanel cancelPanel = new JPanel(new BorderLayout());

		hostPanel.setBorder(BorderFactory.createEmptyBorder(5,10,7,5));
		cancelPanel.setBorder(BorderFactory.createEmptyBorder(5,5,7,10));

		hostS = new JButton("Host");
		hostS.setMargin(new Insets(0,0,0,0));

		cancelH = new JButton("Cancel");
		cancelH.setMargin(new Insets(0,0,0,0));

		cancelPanel.add(cancelH);
		hostPanel.add(hostS);

		buttonPanel.add(hostPanel);
		buttonPanel.add(cancelPanel);


		//Put all together
		main.add(namePanel);
		main.add(portPanel);
		main.add(statusPanel);
		main.add(buttonPanel);

		dialogHost.setPreferredSize(new Dimension(300, 175));
		dialogHost.setContentPane(main);
		dialogHost.pack();
		dialogHost.setLocationRelativeTo(null);
		//dialogHost.setVisible(true);
	}


	public static void displayConnectNetworkGUI() {

		// main setup
		dialogConnect = new JDialog();
		dialogConnect.setTitle("Connect to Server");
		dialogConnect.setModal(true);
		ImageIcon icon = new ImageIcon(MenuBar.class.getResource("/assets/Connect4_Logo.png"));
		dialogConnect.setIconImage(icon.getImage());
		JPanel main = new JPanel();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		Border border = new EmptyBorder(new Insets(5,5,5,10));
		Border text = new EmptyBorder(new Insets(0,5,0,5));
		Border label = new EmptyBorder(0,5,0,15);
		Border empty = new EmptyBorder(0,0,0,0);

		// name section
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.setBorder(border);
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBorder(label);
		connectName = new JTextField();
		connectName.setBorder(text);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		connectName.setFont(new Font("Dialog", Font.PLAIN, 14));
		namePanel.add(nameLabel, BorderLayout.WEST);
		namePanel.add(connectName);

		// address section
		JPanel addressPanel = new JPanel(new BorderLayout());
		addressPanel.setBorder(border);
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBorder(label);
		address = new JTextField();
		address.setBorder(text);
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		address.setFont(new Font("Dialog", Font.PLAIN, 14));
		addressPanel.add(addressLabel, BorderLayout.WEST);
		addressPanel.add(address);

		// port section
		JPanel portPanel = new JPanel(new BorderLayout());
		portPanel.setBorder(border);
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBorder(label);
		connectPortSelection = new JComboBox<>(portChoices);
		connectPortSelection.setBorder(empty);
		connectPortSelection.setEditable(true);
		connectPortSelection.setSelectedIndex(0);
		connectPortSelection.setBackground(Color.WHITE);
		connectPortSelection.setPreferredSize(new Dimension(150,20));
		portLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		connectPortSelection.setFont(new Font("Dialog", Font.PLAIN, 14));
		portPanel.add(portLabel, BorderLayout.WEST);
		portPanel.add(connectPortSelection, BorderLayout.EAST);


		// status section
		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setBorder(border);
		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBorder(label);
		clientStatus = new JTextField();
		clientStatus.setBorder(text);
		clientStatus.setPreferredSize(new Dimension(150,20));
		clientStatus.setEditable(false);
		statusLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		clientStatus.setFont(new Font("Dialog", Font.PLAIN, 14));
		statusPanel.add(statusLabel, BorderLayout.WEST);
		statusPanel.add(clientStatus);

		// Buttons section
		JPanel buttonPanel = new JPanel(new GridLayout());
		JPanel connectPanel = new JPanel(new BorderLayout());
		JPanel cancelPanel = new JPanel(new BorderLayout());

		connectPanel.setBorder(BorderFactory.createEmptyBorder(5,10,7,5));
		cancelPanel.setBorder(BorderFactory.createEmptyBorder(5,5,7,10));

		connectS = new JButton("Connect");
		connectS.setMargin(new Insets(0,0,0,0));

		cancelC = new JButton("Cancel");
		cancelC.setMargin(new Insets(0,0,0,0));

		cancelPanel.add(cancelC);
		connectPanel.add(connectS);

		buttonPanel.add(connectPanel);
		buttonPanel.add(cancelPanel);


		//Put all together
		main.add(namePanel);
		main.add(addressPanel);
		main.add(portPanel);
		main.add(statusPanel);
		main.add(buttonPanel);

		dialogConnect.setPreferredSize(new Dimension(300, 200));
		dialogConnect.setContentPane(main);
		dialogConnect.pack();
		dialogConnect.setLocationRelativeTo(null);
	}



	/**
	 * Adds restart listener
	 * @param actionListener a
	 */
	public void addRestartActionListener(ActionListener actionListener) {
		restart.addActionListener(actionListener);
	}

	/**
	 * Adds listener to language settings
	 * @param actionListener a
	 * @param languageCode l
	 */
	public void addLanguageChangeListener(ActionListener actionListener, String languageCode) {
		if ("en".equals(languageCode)) {
			english.addActionListener(e -> {
				actionListener.actionPerformed(e);
			});
		} else if ("fr".equals(languageCode)) {
			french.addActionListener(e -> {
				actionListener.actionPerformed(e);
			});
		}
	}

	public void addHostActionListener(ActionListener actionListener) {
		host.addActionListener(actionListener);
	}


	public void addConnectActionListener(ActionListener actionListener) {
		connect.addActionListener(actionListener);
	}


	public void addDisconnectActionListener(ActionListener actionListener) {
		disconnect.addActionListener(actionListener);
	}

	public void addConnectButtonListener(ActionListener e) {
		connectS.addActionListener(e);
	}

	public void addHostButtonListener(ActionListener e) {
		hostS.addActionListener(e);
	}

	public void addCancelHostButtonListener(ActionListener e) {
		cancelH.addActionListener(e);
	}

	public void addCancelClientButtonListener(ActionListener e) {
		cancelH.addActionListener(e);
	}

	public String getHostName() {
		return hostName.getText();
	}

	public void resetHostName() {
		hostName.setText("");
	}

	public void resetConnectName() {
		connectName.setText("");
	}

	public void resetAddress() {
		address.setText("");
	}

	public void setHostStatusWaiting() {
		hostStatus.setText("Waiting for players...");
	}

	public void setHostStatusConnected() {
		hostStatus.setText("Player has connected!");
	}

	public void resetHostStatus() {
		hostStatus.setText("");
	}

	public void setClientStatusWaiting() {
		clientStatus.setText("Looking for server...");
	}

	public void setClientStatusConnected() {
		clientStatus.setText("Connected to server!");
	}

	public void resetClientStatus() {
		clientStatus.setText("");
	}


	public void hostResetPort() {
		hostPortSelection.setSelectedIndex(0);
	}

	public void connectResetPort() {
		connectPortSelection.setSelectedIndex(0);
	}

	public String getConnectName() {
		return connectName.getText();
	}

	public String getAddress() {
		return address.getText();
	}


	public int hostGetPort() {
		Object selectedItem = hostPortSelection.getSelectedItem();
		if (selectedItem != null) {
			try {
				return Integer.parseInt(selectedItem.toString());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		return -1;
	}

	public int connectGetPort() {
		Object selectedItem = connectPortSelection.getSelectedItem();
		if (selectedItem != null) {
			try {
				return Integer.parseInt(selectedItem.toString());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid port number.", "Error", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		return -1;
	}


	public void serverAlreadyStarted() {
		JOptionPane.showMessageDialog(dialogHost, "A Server is already running, please disconnect and try again.", "Server Already Running", JOptionPane.ERROR_MESSAGE);
	}

	public void clientAlreadyStarted() {
		JOptionPane.showMessageDialog(dialogConnect, "A client connection is active, please disconnect and try again.", "Client Already Connected", JOptionPane.ERROR_MESSAGE);
	}


	/**
	 * Updates menu labels
	 * @param bundle b
	 */
	public void updateMenuLabels(ResourceBundle bundle) {
		file.setText(bundle.getString("file"));
		game.setText(bundle.getString("game"));
		network.setText(bundle.getString("network"));
		language.setText(bundle.getString("language"));
		help.setText(bundle.getString("help"));
		english.setText(bundle.getString("english"));
		french.setText(bundle.getString("french"));
		host.setText(bundle.getString("host"));
		connect.setText(bundle.getString("connect"));
		disconnect.setText(bundle.getString("disconnect"));
		how.setText(bundle.getString("how"));
		about.setText(bundle.getString("about"));
		restart.setText(bundle.getString("restart"));
		save.setText(bundle.getString("save"));
		load.setText(bundle.getString("load"));
		option.setText(bundle.getString("option"));
	}
}
