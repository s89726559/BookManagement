import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
			
		}
	}
}
