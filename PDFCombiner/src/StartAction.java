import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

class StartAction implements ActionListener{
		JTextField inputField;
		JTextField executeField;
		JProgressBar operateBar;
		JTextPane cmdPane;
		JButton startButton;
		StartAction(JTextField field1,JTextField field2,JProgressBar bar,JTextPane pane,JButton btn){
			this.inputField = field1;
			this.executeField = field2;
			this.operateBar = bar;
			this.cmdPane = pane;
			this.startButton = btn;
			
		}
		LinkedList<String> mergeList = new LinkedList<>();
		LinkedList<File> pdfFileList = new LinkedList<>();
		LinkedList<PDDocument> pdfList = new LinkedList<>();
		
		public void finishSound() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int a=0;a<3;a++) {
						Toolkit toolKit = Toolkit.getDefaultToolkit();
						toolKit.beep();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
				
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String pathname = inputField.getText();
			String extractPath = new String("");
			System.out.println(pathname);
			File fileExplorer = new File(pathname);
			String[] files = fileExplorer.list();	
			startButton.setEnabled(false);
			boolean isComplete = false;
			if(files != null) {
				for(String str:files) {// 20 percent
					if(isPDF(str)) {
						mergeList.add(pathname + ("\\") + str);
						System.out.println(pathname + "\\" + str);
						addComment("Found: " + pathname + "\\" + str);
					}
					
				}
				
				operateBar.setValue(operateBar.getValue() + 20);
				
				addComment("All Files Founded");
				
				for(String str: mergeList) {
					pdfFileList.add(new File(str));
					
				}
				operateBar.setValue(operateBar.getValue() + 20);
				
				
				PDFMergerUtility PDFmerger = new PDFMergerUtility();
				if(executeField.getText() != null&&isPDF(executeField.getText())) {
						
					
						extractPath = executeField.getText();					
					
				}
				else
					extractPath = "pdf\\merge"+new File("pdf").list().length+".pdf";
				
				PDFmerger.setDestinationFileName(extractPath);
				System.out.println("çýkýþ yolu: " + extractPath);
				for(File file: pdfFileList) {
					PDDocument pdf = null;
					try {
						pdf = PDDocument.load(file);
						PDFmerger.addSource(file);
						addComment("File Loaded: " + file.getPath());
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						addComment("ERROR: " +e1.getMessage());
						
						
					}	
					pdfList.add(pdf);
										
				}	
				operateBar.setValue(operateBar.getValue() + 30);
				addComment("All File Loaded");
				
				try {
					PDFmerger.mergeDocuments(null);
					isComplete = true;
					operateBar.setValue(operateBar.getValue() + 30);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					addComment("ERROR: " +e1.getMessage());
					
				}
				
				for(PDDocument pdf : pdfList) {
					try {
						pdf.close();						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						addComment("ERROR: " + e1.getMessage());
						
					}
					addComment("closed all files");
				}
				
			}else {
				addComment("Enter valid folder path...");
			}			
			
			if(isComplete) {
				finishSound();				
				addComment("ExtractPath->" + extractPath);
			}else {
				addComment("Operation cannot finish...");
			}
			startButton.setEnabled(true);
		
		}
		
		public boolean isPDF(String str) {
			if(str.length()>5)
				return str.substring(str.length()-4).equals(".pdf");
			else
				return false;
		}
		
		public void addComment(String cmd) {
			cmdPane.setText(cmdPane.getText() +"\r\n" +cmd);
		}
	}
