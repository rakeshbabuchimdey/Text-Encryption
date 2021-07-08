package pro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.net.*; 
import java.io.*; 
import javax.swing.JOptionPane; 
public class Server extends JFrame implements ActionListener 
{ 
	public static String filename; 
	public static String temp; 
	AES aes = new AES(); 
	public long startTime; 
	public long endTime; 
	public long totalTime; 
	PasswordDialog p=new PasswordDialog(); 
	String str11 = new String(); 
	public String decr; 
	public static RandomAccessFile f; 
	private static JTextArea txtEnc; 
	private static JTextArea txtDec; 
	public static void main(String args[])
	{
	 	try
	 	{
	 		 Server s = new Server(); 
	 		 System.out.println("Server running..."); 
	 		 JOptionPane.showMessageDialog(s,"Server Running"); // Listen on port 5555 
			  ServerSocket server = new ServerSocket(5555); 
			  // Accept the sk 
			  Socket sk = server.accept(); 
			  System.out.println("Server accepted client"); 
			  JOptionPane.showMessageDialog(s,"Server accepted client"); 
			  InputStream input = sk.getInputStream(); 
			  System.out.println(input);
			  BufferedReader inReader = new BufferedReader(new InputStreamReader(sk.getInputStream())); 
			  BufferedWriter outReader = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));
			  System.out.println(inReader);
			  System.out.println(outReader);
			  // Read the filename 
			  filename = inReader.readLine(); 
			  System.out.print(filename);
			  if ( !filename.equals("") )
			  { 
			  // Reply back to client with READY status 
			  outReader.write("READYn"); 
			  outReader.flush(); 
			  } 
			  // Create a new file in the tmp directory using the filename 
			  FileOutputStream wr = new FileOutputStream(new File("C://Users/GIRADA MOUNIKA/Desktop/project/enc" + filename)); 
			  byte[] buffer = new byte[sk.getReceiveBufferSize()]; 
			  System.out.println(buffer);
			  int bytesReceived = 0; 
			  while((bytesReceived = input.read(buffer))>0)
			  	{ /* Write to the file */ 
			  		wr.write(buffer,0,bytesReceived); 
			  	} 
			  	JOptionPane.showMessageDialog(s,"Encrypted File Saved sucessfullynPath:C://Users/GIRADA MOUNIKA/Desktop/project/enc"+ filename); 
			  	s.Servergui(); 
			  	try 
			  	{ 
			  		f =new RandomAccessFile("C://Users/GIRADA MOUNIKA/Desktop/project/enc" + filename,"r"); 
			  	} 
			  	catch (FileNotFoundException e1) 
			  	{ // TODO Auto-generated catch block 
			  		e1.printStackTrace(); 
			  	} 
			  	long longlength = 0; 
			  	try
			  	{ 
			  		longlength = f.length(); 
			  	} 
			  	catch (IOException e1) 
			  	{ 
			  		// TODO Auto-generated catch block 
			  		e1.printStackTrace(); 
			  	} 
			  	int length = (int) longlength; 
			  	// Read file and return data 
			  	byte[] data = new byte[length]; 
			  	try 
			  	{ 
			  		f.readFully(data); 
			  		f.close(); 
			  	} 
			  	catch (IOException e1) 
			  	{ // TODO Auto-generated catch block 
			  		e1.printStackTrace(); 
			  	} 
			  	temp=new String(data); 
			  	txtEnc.setText(temp); 
			  } 
			  catch(Exception e)
			  { 
			  		System.err.println(e); 
			  } 
			} 
			void Servergui() 
			{ 
				JFrame frame = new JFrame(); 
				frame.setTitle("Server"); 
				frame.setVisible(true); 
				frame.setSize(150,100); 
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				GridLayout layout = new GridLayout(3,1,3,3); 
				JMenuBar mymenu = new JMenuBar(); 
				mymenu.setOpaque(true); 
				mymenu.setBackground(new Color(154, 165, 127)); 
				mymenu.setPreferredSize(new Dimension(30,20)); 
				frame.setJMenuBar(mymenu); 
				JLabel lblEnc = new JLabel("Encrypted Text"); 
				lblEnc.setPreferredSize(new Dimension(5,15)); 
				txtEnc = new JTextArea(5,10); 
				txtEnc.setLineWrap(true); 
				txtEnc.setEditable(false); 
				txtEnc.setPreferredSize(new Dimension(5,15)); 
				JScrollPane scrollPane1= new JScrollPane ( txtEnc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
				JLabel lblDec = new JLabel("Decrypted Text"); 
				lblDec.setPreferredSize(new Dimension(5,15)); 
				txtDec= new JTextArea(5,10); 
				txtDec.setLineWrap(true); 
				txtDec.setEditable(false); 
				txtDec.setPreferredSize(new Dimension(5,15)); 
				JScrollPane scrollPane11= new JScrollPane( txtDec,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
				JButton btnDec = new JButton("Decrypt"); 
				btnDec.setPreferredSize(new Dimension(5,15)); 
				btnDec.addActionListener(this); 
				JButton btnExit = new JButton("Exit"); 
				btnDec.setPreferredSize(new Dimension(5,15)); 
				//btnExit.addActionListener(new Quit()); 
				JPanel mainPanel = new JPanel(); 
				mainPanel.setLayout(layout); 
				mainPanel.add(lblEnc); 
				//mainPanel.add(txtEnc); 
				mainPanel.add(scrollPane1); 
				mainPanel.add(lblDec); 
				// mainPanel.add(txtDec); 
				mainPanel.add(scrollPane11); 
				mainPanel.add(btnDec); 
				mainPanel.add(btnExit); 
				frame.getContentPane().add(mainPanel); 
				frame.pack(); 
				frame.setVisible(true); 
				}
				 @Override 
				 public void actionPerformed(ActionEvent e) 
				 {
				  // TODO Auto-generated method stu 
				 	try 
				 	{ 
				 		f = new RandomAccessFile("C://Users/GIRADA MOUNIKA/Desktop/project/enc" + filename,"r"); 
				 	} 
					catch (FileNotFoundException e2) 
					{ 
					// TODO Auto-generated catch block 
					e2.printStackTrace(); 
					} 
					long longlength = 0; 
					try 
					{ 
					longlength = f.length(); 
					} 
					catch (IOException e1) 
					{ 
					// TODO Auto-generated catch block 
					e1.printStackTrace(); 
					} 
					int length = (int) longlength; 
					// Read file and return data 
					byte[] data = new byte[length]; 
					try 
					{ 
					f.readFully(data); 
					} 
					catch (IOException e1) 
					{ 
					// TODO Auto-generated catch block 
						e1.printStackTrace(); 
					} 
					temp=new String(data); 
					txtEnc.setText(temp); 
					str11 = PasswordDialog.password(1); 
					byte[] key=str11.getBytes();
					try
					{ 
						startTime = System.currentTimeMillis(); 
						byte[] dec=AES.decrypt(data, key); 
						endTime = System.currentTimeMillis(); 
						totalTime = endTime - startTime; 
						temp=new String(dec); 
						txtDec.setText(temp); 
						JOptionPane.showMessageDialog(this,"Time taken to Decrypt: "+totalTime+" ms "); 
					} 
					catch(Exception ex) 
					{ 
						JOptionPane.showMessageDialog(this,ex.getMessage()); 
					} 
				} 
			} 
			