import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Table {
	JFrame f;
	public Table(Object data[][]) {
	//Setup JFrame
	JFrame.setDefaultLookAndFeelDecorated(true);
    JDialog.setDefaultLookAndFeelDecorated(true);
    f=new JFrame("¬d¸ßµ²ªG");
    f.setSize(800,800);
    f.setLocationRelativeTo(null);
    Container cp=f.getContentPane();	
	//Build Elements
    
	String[] columns={"BookId","Title","Author","Available","BorrowerId","DueDay"};
	JTable jt=new JTable(data,columns);
    jt.setPreferredScrollableViewportSize(new Dimension(800,800));
    cp.add(new JScrollPane(jt),BorderLayout.CENTER);
    f.setVisible(true);
    //Close JFrame       
    }
}