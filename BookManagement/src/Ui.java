import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Ui {
	
	public Ui() {
		
		JFrame window=new JFrame("Book Management");
	    window.setBounds(0,0,550,300);
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
	    window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    
	    Container cp=window.getContentPane();
	    cp.setLayout(null);  //�����w�]BorderLayout
	    ButtonHandler handler =new ButtonHandler();
	    
	    JButton button1=new JButton("Add Book");
	    button1.setBounds(20,20,150,40);  //�M�w�����m�P�j�p
    	button1.addActionListener(handler);
    	cp.add(button1);
    	
    	JButton button2=new JButton("Delete Book");
	    button2.setBounds(190,20,150,40);  //�M�w�����m�P�j�p
    	button2.addActionListener(handler);
    	cp.add(button2);
    	
    	JButton button3=new JButton("Find All Book");
	    button3.setBounds(360,20,150,40);  //�M�w�����m�P�j�p
    	button3.addActionListener(handler);
    	cp.add(button3);
    	
    	JButton button4=new JButton("Borrow Book");
	    button4.setBounds(20,80,150,40);  //�M�w�����m�P�j�p
    	button4.addActionListener(handler);
    	cp.add(button4);
    	
    	JButton button5=new JButton("Return Book");
	    button5.setBounds(190,80,150,40);  //�M�w�����m�P�j�p
    	button5.addActionListener(handler);
    	cp.add(button5);
    	
    	JButton button6=new JButton("Find OverDue Book");
	    button6.setBounds(360,80,150,40);  //�M�w�����m�P�j�p
    	button6.addActionListener(handler);
    	cp.add(button6);
    	
    	JButton button7=new JButton("Add User");
	    button7.setBounds(20,140,150,40);  //�M�w�����m�P�j�p
    	button7.addActionListener(handler);
    	cp.add(button7);
    	
    	JButton button8=new JButton("Find User");
	    button8.setBounds(190,140,150,40);  //�M�w�����m�P�j�p
    	button8.addActionListener(handler);
    	cp.add(button8);
    	
    	JButton button9=new JButton("End Program");
	    button9.setBounds(360,140,150,40);  //�M�w�����m�P�j�p
    	button9.addActionListener(handler);
    	cp.add(button9);
    	
	}
	
	
	public class ButtonHandler implements ActionListener {
		
	    JTextField bookId = new JTextField(10);
	    JTextField userId = new JTextField(10);
	    JTextField name = new JTextField(10);
		
		@Override
		public void actionPerformed(ActionEvent event) {
			//System.out.printf("%s",event.getActionCommand()); //debug
			//event Add Book
			if(event.getActionCommand()=="Add Book") {
				String title,author;
				title = JOptionPane.showInputDialog("�п�J�ѦW");
				if(title!=null) {
					author= JOptionPane.showInputDialog("�п�J�@��");
					if(author!=null) {
						Sql sql=new Sql();
			        	int bookId=sql.findUsableBookId();
			        	int result=sql.addBook(bookId,title,author);
			        	if(result==1) {
			        		JOptionPane.showMessageDialog(null, "���\�s�W���y!");
			        	}else {
			        		JOptionPane.showMessageDialog(null, "�X�{�N�~���~�A�s�W���y����!");
			        	}
					}else {
						JOptionPane.showMessageDialog(null, "�@�̤��i����!");
					}
				}else{
					JOptionPane.showMessageDialog(null, "�ѦW���i����!");
				}
			}
			
			//event Delete Book
			if(event.getActionCommand()=="Delete Book") {
				String bookId;
				bookId = JOptionPane.showInputDialog("�п�J�Ѫ�ID");
				if(bookId!=null) {
					int id=Integer.parseInt(bookId);
					Sql sql=new Sql();
			        int result=sql.deleteBook(id);
			        if(result==1) {
			        	//JOptionPane.showMessageDialog(null, "���\�R�����y!");
			        }else {
			        	//JOptionPane.showMessageDialog(null, "�L�k�R�����y�A�нT�{�O�_��J���~!");
			        }
				}else{
					JOptionPane.showMessageDialog(null, "ID���i����!");
				}
			}			
			
			//event Find All Book
			if(event.getActionCommand()=="Find All Book") {
				Sql sql=new Sql();
			    sql.findAllBook();
			}			
			
			//event Borrow Book
			if(event.getActionCommand()=="Borrow Book") {
				//JFrame
				JFrame borrowFrame=new JFrame("�ɮ�");
				borrowFrame.setBounds(0,0,400,120);
				borrowFrame.setLocationRelativeTo(null);
				borrowFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
			    //Container
			    Container contentPaneBorrow = borrowFrame.getContentPane();
			    contentPaneBorrow.setLayout(new GridLayout(2,1,1,1));
			    JPanel[] panel=new JPanel[11];
			    for(int i=0;i<2;i++) {
			    	panel[i]=new JPanel();
			    	panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			    }
			    
			    JLabel label1=new JLabel("bookId:");
			    JLabel label2=new JLabel(" userId:");
			    
			    JButton btnBorrow = new JButton("�ɮ�");
			    
			    panel[0].add(label1);
			    panel[0].add(bookId);
			    panel[0].add(label2);
			    panel[0].add(userId);
			    panel[1].add(btnBorrow);
			    panel[1].setLayout(new FlowLayout(FlowLayout.CENTER));
			    
			    contentPaneBorrow.add(panel[0]);
			    contentPaneBorrow.add(panel[1]);
			    
			    borrowFrame.setVisible(true);
			}
			if(event.getActionCommand()=="�ɮ�") {
			    Sql sql=new Sql();
			    sql.borrowBook(Integer.parseInt( bookId.getText()), Integer.parseInt(userId.getText()) );
			}
			
			//event Return Book
			if(event.getActionCommand()=="Return Book") {
				//JFrame
				JFrame Frame=new JFrame("�ٮ�");
				Frame.setBounds(0,0,400,120);
				Frame.setLocationRelativeTo(null);
				Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
			    //Container
			    Container contentPane = Frame.getContentPane();
			    contentPane.setLayout(new GridLayout(2,1,1,1));
			    JPanel[] panel=new JPanel[11];
			    for(int i=0;i<2;i++) {
			    	panel[i]=new JPanel();
			    	panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			    }
			    
			    JLabel label1=new JLabel("bookId:");
			    JLabel label2=new JLabel(" userId:");
			    
			    JButton btnReturn = new JButton("�ٮ�");
			    
			    panel[0].add(label1);
			    panel[0].add(bookId);
			    panel[0].add(label2);
			    panel[0].add(userId);
			    panel[1].add(btnReturn);
			    panel[1].setLayout(new FlowLayout(FlowLayout.CENTER));
			    
			    contentPane.add(panel[0]);
			    contentPane.add(panel[1]);
			    
			    Frame.setVisible(true);
			}
			if(event.getActionCommand()=="�ٮ�") {
			    Sql sql=new Sql();
			    sql.returnBook(Integer.parseInt( bookId.getText()), Integer.parseInt(userId.getText()) );
			}
			
			//event Find OverDue Book
			if(event.getActionCommand()=="Find OverDue Book") {
				Sql sql=new Sql();
			    sql.findOverdueBook();
			}			
			
			//event Add User
			if(event.getActionCommand()=="Add User") {
				//JFrame
				JFrame Frame=new JFrame("�s�W�ϥΪ�");
				Frame.setBounds(0,0,400,120);
				Frame.setLocationRelativeTo(null);
				Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				
			    //Container
			    Container contentPane = Frame.getContentPane();
			    contentPane.setLayout(new GridLayout(2,1,1,1));
			    JPanel[] panel=new JPanel[11];
			    for(int i=0;i<2;i++) {
			    	panel[i]=new JPanel();
			    	panel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			    }
			    
			    JLabel label1=new JLabel("userId:");
			    JLabel label2=new JLabel(" name:");
			    
			    JButton btnBorrow = new JButton("�s�W�ϥΪ�");
			    
			    panel[0].add(label1);
			    panel[0].add(userId);
			    panel[0].add(label2);
			    panel[0].add(name);
			    panel[1].add(btnBorrow);
			    panel[1].setLayout(new FlowLayout(FlowLayout.CENTER));
			    
			    contentPane.add(panel[0]);
			    contentPane.add(panel[1]);
			    
			    contentPane.setVisible(true);
			}			
			if(event.getActionCommand()=="�s�W�ϥΪ�") {
			    Sql sql=new Sql();
			    sql.addUser(Integer.parseInt( userId.getText()), name.getText() );
			}
			
			//event Find User
			if(event.getActionCommand()=="Find User") {
				Sql sql=new Sql();
				sql.findAllUser();
			}			
			
			//event Find User
			if(event.getActionCommand()=="End Program") {
				System.exit(0);
			}			
			
		}
		
	}
	
}
