package pro;


import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.net.*; 
import java.io.*; 
public class Client extends JFrame implements ActionListener 
{
	 public static Client clientForm = new Client(); 
	 private JTextField txtFile; 
	 AES aes = new AES(); 
	 public long startTime; 
	 public long endTime; 
	 public long totalTime; 
	 PasswordDialog p=new PasswordDialog(); 
	 public static JTextField txtip; 
	 String str1 = new String(); 
	 public static void main(String args[])
	 {
	  // Create and display the client form
	   clientForm.Display(); 
	 } 
	 public void Display()
	 {
	 	 JFrame frame = new JFrame(); 
	 	 frame.setTitle("Client"); 
	 	 frame.setVisible(true); 
	 	 frame.setSize(100,100); 
	 	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	 	 GridLayout layout = new GridLayout(3,2,3,10); 
	 	 JMenuBar mymenu = new JMenuBar(); 
	 	 mymenu.setOpaque(true); 
	 	 mymenu.setBackground(new Color(154, 165, 127)); 
	 	 mymenu.setPreferredSize(new Dimension(100,20)); 
	 	 frame.setJMenuBar(mymenu); 
	 	 JLabel lblip = new JLabel("IP Address"); 
	 	 txtip = new JTextField(); 
	 	 txtip.setPreferredSize(new Dimension(150,30)); 
	 	 JLabel lblFile = new JLabel("Filename:"); 
	 	 txtFile = new JTextField(); 
	 	 txtFile.setPreferredSize(new Dimension(150,30)); 
	 	 JButton btnTransfer = new JButton("Transfer"); 
	 	 btnTransfer.addActionListener(this); 
	 	 JButton btnExit = new JButton("Exit"); 
	 	 btnExit.addActionListener(new Quit()); 
	 	 JPanel mainPanel = new JPanel(); 
	 	 mainPanel.setLayout(layout); 
	 	 mainPanel.add(lblip); 
	 	 mainPanel.add(txtip); 
	 	 mainPanel.add(lblFile); 
	 	 mainPanel.add(txtFile); 
	 	 mainPanel.add(btnTransfer); 
	 	 mainPanel.add(btnExit); 
	 	 frame.getContentPane().add(mainPanel); 
	 	 frame.pack(); 
	 	 frame.setVisible(true); 
	 } 
	 public void actionPerformed(ActionEvent e) 
	 { 
	 	/* File Open Dialog box allows the user to select a file */ 
	 	JFileChooser fileDlg = new JFileChooser(); 
	 	fileDlg.showOpenDialog(this); 
	 	String filename = fileDlg.getSelectedFile().getAbsolutePath(); 
	 	System.out.println(filename);
	 	txtFile.setText(filename); 
	 	try
	 	{
	 	 	/* Try to connect to the server on localhost, port 5555 */ 
	 	 	Socket sk = new Socket(txtip.getText(), 5555); 
	 	 	JOptionPane.showMessageDialog(this, "Connected"); 
	 	 	//FileReader fr=new FileReader("D:\\mk.txt");   
	 	 	//System.out.println(fr);
	 	 	OutputStream output = sk.getOutputStream(); 
	 	 	/* Send filename to server */ 
	 	 	System.out.print(output);
	 	 	OutputStreamWriter outputStream = new OutputStreamWriter(sk.getOutputStream());
	 	 	outputStream.write(fileDlg.getSelectedFile().getName() + "n");
	 	 	System.out.println(outputStream);
	 	 	outputStream.flush(); 
	 	 	/* Get reponse from server */ 
	 	 	BufferedReader inReader = new BufferedReader(new InputStreamReader(sk.getInputStream()));
	 	 	System.out.println(inReader);
	 	 	String serverStatus = inReader.readLine(); 
	 	 	System.out.println("12345666");
	 	 	// Read the first line /* If server is ready, send the file */
	 	    System.out.println(serverStatus);
	 	 	if ( serverStatus.equals("READY") )
	 	 	{
	 	 		 byte[] buffer = new byte[sk.getSendBufferSize()];
	 	 		 System.out.println(buffer);
	 	 		 int bytesRead = 0; 
	 	 		 String temp=new String();
	 	 		 ReadFileString rfs1=new ReadFileString(); 
	 	 		 temp=rfs1.rfs(filename); 
	 	 		 System.out.println(temp);
	 	 		 str1 = PasswordDialog.password(0); 
	 	 		 byte[] key=str1.getBytes(); 
	 	 		 byte[] input=temp.getBytes(); 
	 	 		 startTime = System.currentTimeMillis(); 
	 	 		 byte[] enc=AES.encrypt(input, key); 
	 	 		 endTime = System.currentTimeMillis(); 
	 	 		 totalTime = endTime - startTime; 
	 	 		 JOptionPane.showMessageDialog(this,"Time taken to Encrypt: "+totalTime+" ms "); 
	 	 		 //temp = new BASE64Encoder().encode(temp.getBytes());
	 	 		 FileOutputStream wr = new FileOutputStream("C://Users/GIRADA MOUNIKA/Desktop/project/clenc.txt"); 
	 	 		 wr.write(enc); 
	 	 		 wr.close(); 
	 	 		 FileInputStream file = new FileInputStream("C://Users/GIRADA MOUNIKA/Desktop/project/clenc.txt"); 
	 	 		 while((bytesRead = file.read(buffer))>0) 
	 	 		 	{ 
	 	 		 		output.write(buffer,0,bytesRead); 
	 	 		 	} 
	 	 		 	output.close(); 
	 	 		 	file.close(); 
	 	 		 	sk.close(); 
	 	 		 	JOptionPane.showMessageDialog(this,  "Transfer complete:\nTotal transmitted : "+ enc.length ); 
	 	 	 } 
	 	 } 
	 	 catch (Exception ex)
	 	 { /* Catch any errors */ 
	 	 	JOptionPane.showMessageDialog(this, ex.getMessage()); 
	 	 } 
	 	} 
	 } 
	 class Quit implements ActionListener 
	 { 
	 	public void actionPerformed (ActionEvent e) 
	 	{ 
	 		System.exit(0); 
	 	} 
	 }