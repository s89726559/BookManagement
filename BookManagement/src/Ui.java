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
	    cp.setLayout(null);  //取消預設BorderLayout
	    ButtonHandler handler =new ButtonHandler();
	    
	    JButton button1=new JButton("Add Book");
	    button1.setBounds(20,20,150,40);  //決定元件位置與大小
    	button1.addActionListener(handler);
    	cp.add(button1);
    	
    	JButton button2=new JButton("Delete Book");
	    button2.setBounds(190,20,150,40);  //決定元件位置與大小
    	button2.addActionListener(handler);
    	cp.add(button2);
    	
    	JButton button3=new JButton("Find All Book");
	    button3.setBounds(360,20,150,40);  //決定元件位置與大小
    	button3.addActionListener(handler);
    	cp.add(button3);
    	
    	JButton button4=new JButton("Borrow Book");
	    button4.setBounds(20,80,150,40);  //決定元件位置與大小
    	button4.addActionListener(handler);
    	cp.add(button4);
    	
    	JButton button5=new JButton("Return Book");
	    button5.setBounds(190,80,150,40);  //決定元件位置與大小
    	button5.addActionListener(handler);
    	cp.add(button5);
    	
    	JButton button6=new JButton("Find OverDue Book");
	    button6.setBounds(360,80,150,40);  //決定元件位置與大小
    	button6.addActionListener(handler);
    	cp.add(button6);
    	
    	JButton button7=new JButton("Add User");
	    button7.setBounds(20,140,150,40);  //決定元件位置與大小
    	button7.addActionListener(handler);
    	cp.add(button7);
    	
    	JButton button8=new JButton("Find User");
	    button8.setBounds(190,140,150,40);  //決定元件位置與大小
    	button8.addActionListener(handler);
    	cp.add(button8);
    	
    	JButton button9=new JButton("End Program");
	    button9.setBounds(360,140,150,40);  //決定元件位置與大小
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
				title = JOptionPane.showInputDialog("請輸入書名");
				if(title!=null) {
					author= JOptionPane.showInputDialog("請輸入作者");
					if(author!=null) {
						Sql sql=new Sql();
			        	int bookId=sql.findUsableBookId();
			        	int result=sql.addBook(bookId,title,author);
			        	if(result==1) {
			        		JOptionPane.showMessageDialog(null, "成功新增書籍!");
			        	}else {
			        		JOptionPane.showMessageDialog(null, "出現意外錯誤，新增書籍失敗!");
			        	}
					}else {
						JOptionPane.showMessageDialog(null, "作者不可為空!");
					}
				}else{
					JOptionPane.showMessageDialog(null, "書名不可為空!");
				}
			}
			
			//event Delete Book
			if(event.getActionCommand()=="Delete Book") {
				String bookId;
				bookId = JOptionPane.showInputDialog("請輸入書的ID");
				if(bookId!=null) {
					int id=Integer.parseInt(bookId);
					Sql sql=new Sql();
			        int result=sql.deleteBook(id);
			        if(result==1) {
			        	//JOptionPane.showMessageDialog(null, "成功刪除書籍!");
			        }else {
			        	//JOptionPane.showMessageDialog(null, "無法刪除書籍，請確認是否輸入錯誤!");
			        }
				}else{
					JOptionPane.showMessageDialog(null, "ID不可為空!");
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
				JFrame borrowFrame=new JFrame("借書");
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
			    
			    JButton btnBorrow = new JButton("借書");
			    
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
			if(event.getActionCommand()=="借書") {
			    Sql sql=new Sql();
			    sql.borrowBook(Integer.parseInt( bookId.getText()), Integer.parseInt(userId.getText()) );
			}
			
			//event Return Book
			if(event.getActionCommand()=="Return Book") {
				//JFrame
				JFrame Frame=new JFrame("還書");
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
			    
			    JButton btnReturn = new JButton("還書");
			    
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
			if(event.getActionCommand()=="還書") {
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
				JFrame Frame=new JFrame("新增使用者");
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
			    
			    JButton btnBorrow = new JButton("新增使用者");
			    
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
			if(event.getActionCommand()=="新增使用者") {
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
