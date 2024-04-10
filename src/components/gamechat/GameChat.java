package components.gamechat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Class holding game chat component to be called in main
 *
 * @author Kevin Mosekjaer
 */
public class GameChat {

	/**
	 * JPanels holding main panel and others for formatting
	 */
	private JPanel panel, top, bottom, center;

	/**
	 * JTextARea for chat, title
	 */
	private JTextArea chatArea, title;

	/**
	 * text box to write messages
	 */
	private JTextField textBox;

	/**
	 * button to send messages to chat
	 */
	private JButton send;

	/**
	 * Holds title, variable to add messages
	 */
	//private JLabel chat, newMessage;

	/**
	 * Holds scrollable chat
	 */
	//private JScrollPane scrollChat;

	/**
	 * Borders to separate sections
	 */
	private Border leftSide, underTitle;

	/**
	 * Constructor initializing game chat
	 */
	public GameChat() {
		initializeGameChat();
	}

	/**
	 * Getter for JPanel holding the game chat component
	 *
	 * @return panel
	 */
	public JPanel getGameChat() {
		return panel;
	}

	/**
	 * Method to create the game chat section
	 */
	private void initializeGameChat() {
		panel = new JPanel(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel(new BorderLayout());
		center = new JPanel(new BorderLayout());

		// Top area
		//chat = new JLabel("Chat");
		title = new JTextArea("Chat");
		title.setEditable(false);
		title.setFocusable(false);
		top.add(title);
		underTitle = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
		top.setBorder(underTitle);
		panel.add(top, BorderLayout.NORTH);


		// Middle section
		chatArea = new JTextArea();
		chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
		chatArea.setBackground(Color.WHITE);
		chatArea.setEditable(false);
		center.setBackground(Color.WHITE);

		center.add(chatArea, BorderLayout.WEST);
		panel.add(center, BorderLayout.CENTER);


		// Bottom section
		textBox = new JTextField();
		send = new JButton("Send");
		bottom.add(send, BorderLayout.EAST);
		bottom.add(textBox, BorderLayout.CENTER);
		panel.add(bottom, BorderLayout.SOUTH);

		leftSide = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK);
		panel.setBorder(leftSide);
	}

	/**
	 * Function to display chat messages
	 *
	 * @param message m
	 */
	public void displayChatMessage(String message) {
		chatArea.append(message + "\n");
	}

	/**
	 * Resets text area after sending message
	 */
	public void resetTextArea() {
		textBox.setText("");
	}

	/**
	 * Getter for message to be sent
	 *
	 * @return message to be sent
	 */
	public String getMessageSend() {
		return textBox.getText();
	}

	/**
	 * Setting up action listener
	 *
	 * @param e e
	 */
	public void addChatSendListener(ActionListener e) {
		send.addActionListener(e);
	}

	/**
	 * Updates chat title
	 * @param bundle b
	 */
	public void updateChatLabels(ResourceBundle bundle) {
		title.setText(bundle.getString("chat"));

	}

}
