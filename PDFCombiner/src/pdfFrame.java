import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import java.awt.SystemColor;

public class pdfFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public pdfFrame() {
	}
	
		

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;	
	private JButton btnNewButton;
	private JProgressBar progressBar;
	private JTextPane txtpnAA;
	
	private StartAction startAction;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pdfFrame frame = new pdfFrame();	
					frame.pdfFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 * @return 
	 */
	public void pdfFrame() {		
		setResizable(false);
		setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage("assets\\pdf.png"));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		textField.setBounds(100, 20, 300, 20);
		contentPane.add(textField);
		textField.setColumns(90);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textField_1.setBounds(100, 60, 300, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFilePath = new JLabel("Folder Path \u2192");
		lblFilePath.setFont(new Font("Impact", Font.PLAIN, 14));
		lblFilePath.setForeground(Color.WHITE);
		lblFilePath.setBackground(Color.BLACK);
		lblFilePath.setBounds(10, 20, 80, 14);
		contentPane.add(lblFilePath);
		
		JLabel lblExtractPath = new JLabel("Extract Path \u2192");
		lblExtractPath.setFont(new Font("Impact", Font.PLAIN, 14));
		lblExtractPath.setForeground(Color.WHITE);
		lblExtractPath.setBounds(10, 64, 81, 14);
		contentPane.add(lblExtractPath);
		
				
		btnNewButton = new JButton("Start");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		BufferedImage img; 
		Image icon1 = null;
		try {
		img = ImageIO.read(new File("assets\\jigsaw.png"));
		icon1 = img.getScaledInstance(25, 25, DO_NOTHING_ON_CLOSE);
		}catch (IOException e) {
			
		}	
		
		
		txtpnAA = new JTextPane();
		txtpnAA.setEditable(false);
		txtpnAA.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtpnAA.setText("How do you use?\r\nYou enter file location on the file path text field.\r\nThen you enter extract location on the extract path field.\r\n\r\nHow do it run?\r\nThis program getting the file path of your pdf in folder.\r\nAnd it going to combine these more then extract these in your extract path.\r\nIt play \"beep\" sound when operation was finished");
		txtpnAA.setForeground(SystemColor.text);
		txtpnAA.setToolTipText("");
		txtpnAA.setBackground(new Color(51, 51, 51));
				
		JScrollPane scrollPane = new JScrollPane(txtpnAA);
		scrollPane.setBounds(10, 187, 390, 170);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Created by Akif Esad Tosun");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setBounds(10, 395, 156, 25);
		contentPane.add(lblNewLabel);		
		
		progressBar = new JProgressBar(0,100);
		progressBar.setBounds(300, 400, 100, 20);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		startAction = new StartAction(textField,textField_1,progressBar,txtpnAA,btnNewButton);
		
		ImageIcon icon = new ImageIcon(icon1);
		btnNewButton.setIcon(icon);
		btnNewButton.setBounds(300, 100, 100, 25);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(startAction);
	}
}
