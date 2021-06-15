import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat; 
import java.text.DateFormat; 
import java.text.ParseException; 
import java.util.ArrayList;


public class Sql {
	
	//method for connecting to a database
	private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:./db/BookManagement.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
	}
	
	//method for creating a database 
	public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:./db/" + fileName +".db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	//method for creating Book table
	public static void createBookTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:./db/BookManagement.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Book (\n"
                + "	BookId integer PRIMARY KEY,\n"
                + "	Title text NOT NULL,\n"
                + "	Author text NOT NULL,\n"
                + " Available boolean default 1 NOT NULL,\n"
                + "	BorrowerId integer ,\n"
                + "	DueDay text \n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement stmt = conn.createStatement()) {
        	stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	//method for creating User table
	public static void createUserTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:./db/BookManagement.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS User (\n"
                + "	UserId integer PRIMARY KEY,\n"
                + "	Name text NOT NULL,\n"
                + "	NumberOfBooksBorrowed integer default 0 NOT NULL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement stmt = conn.createStatement()) {
        	stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}			
	
	
	//find a non-duplicate bookId
	public int findUsableBookId() {
		int id=1;
		String sql = "SELECT BookId FROM Book";
	        
	    try (Connection conn = this.connect();
	    		Statement stmt  = conn.createStatement();
	    		ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	    	while (rs.next()) {
	    		//find the max BookId ,then assign id 1 bigger than the max bookId
	    		if(id<rs.getInt("BookId")) {
	    			id=rs.getInt("BookId");
	    		}
	    	}
	        	
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    id=id+1;
		return id;
	}
	
	//method for adding a book to the table "Book"
	public int addBook(int bookId,String title,String author) {
		
        String sql = "INSERT INTO Book(BookId,Title,Author) VALUES("
                + bookId+","
                + '"'+title+'"'+','
                + '"'+author+'"'
                + ");";
        
        try (Connection conn = this.connect();
        		Statement stmt = conn.createStatement()) {
        	stmt.execute(sql);
        	System.out.println("新增成功!");
        	return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	
	//find all book
	public void findAllBook(){
		
        String sql = "SELECT BookId, Title, Author,Available,BorrowerId,DueDay FROM Book";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
        	ArrayList<Object> array =new ArrayList<>();
        	int i=0;
        	
        	String msg;
        	//msg=String.format("%-8s%-15s%-8s%-11s%-12s%-8s\n","BookId","Title","Author","Available","BorrowerId","DueDay");
        	System.out.printf("|%-8s|%-15s|%-8s|%-11s|%-12s|%-8s|\n","BookId","Title","Author","Available","BorrowerId","DueDay");
        	// loop through the result set
        	while (rs.next()) {
            	System.out.printf("|%-8d|%-15s|%-8s|%-11s|%-12s|%-8s|\n",rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getInt("Available"),rs.getInt("BorrowerId"),rs.getString("DueDay"));
            	array.add(rs.getInt("BookId"));
            	array.add(rs.getString("Title"));
            	array.add(rs.getString("Author"));
            	array.add(rs.getInt("Available"));
            	array.add(rs.getInt("BorrowerId"));
            	array.add(rs.getString("DueDay"));
            	i++;
            }
        	
        	Object[][] data=new Object[i][6];
        	
        	for(int j=0;j<i;j++) {
        		//test
        		//int temp=j*6;
        		//System.out.printf("temp=%d\n",temp);
        		//System.out.printf("%s",array.get(temp));
        		data[j][0]=array.get(j*6);
        		data[j][1]=array.get(j*6+1);
        		data[j][2]=array.get(j*6+2);
        		data[j][3]=array.get(j*6+3);
        		data[j][4]=array.get(j*6+4);
        		data[j][5]=array.get(j*6+5);
        	}
        	Table tb=new Table(data);
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
	
	//delete a book
	public int deleteBook(int bookId){
        String sql = "DELETE FROM Book WHERE BookId="+bookId;
        
        try (Connection conn = this.connect();
            Statement stmt  = conn.createStatement();){
        	stmt.executeUpdate(sql);
        	System.out.println("已執行指令!");
        	return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
	
	//find a non-duplicate userId
	public int findUsableUserId() {
		int id=1;
		String sql = "SELECT UserId FROM User";
	        
	    try (Connection conn = this.connect();
	    		Statement stmt  = conn.createStatement();
	    		ResultSet rs    = stmt.executeQuery(sql)){
	            
	            // loop through the result set
	    	while (rs.next()) {
	    		//find the max BookId ,then assign id 1 bigger than the max bookId
	    		if(id<rs.getInt("UserId")) {
	    			id=rs.getInt("UserId");
	    		}
	    	}
	    	
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
	    id=id+1;
		return id;
	}	
	
	//method for adding a User to the table "User"
	public void addUser(int userId,String name) {
		
        String sql = "INSERT INTO User(UserId,Name) VALUES("
                + userId+","
                + '"'+name+'"'
                + ");";
        
        try (Connection conn = this.connect();
        		Statement stmt = conn.createStatement()) {
        	stmt.execute(sql);
        	System.out.println("新增成功!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}	
	
	//show all User
	public void findAllUser(){
        String sql = "SELECT UserId, Name,NumberOfBooksBorrowed FROM User";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
        	ArrayList<Object> array =new ArrayList<>();
        	int i=0;
        	
            // loop through the result set
        	System.out.printf("\n|%-8s|%-8s|%-23s|","UserId","Name","NumberOfBooksBorrowed");
            while (rs.next()) {
            	System.out.printf("\n|%-8d|%-8s|%-23d|",rs.getInt("UserId"),rs.getString("Name"),rs.getInt("NumberOfBooksBorrowed"));
            	array.add(rs.getInt("UserId"));
            	array.add(rs.getString("Name"));
            	array.add(rs.getInt("NumberOfBooksBorrowed"));
            	i++;
            }
            
        	Object[][] data=new Object[i][3];
        	
        	for(int j=0;j<i;j++) {
        		data[j][0]=array.get(j*3);
        		data[j][1]=array.get(j*3+1);
        		data[j][2]=array.get(j*3+2);
        	}
        	UserTable tb=new UserTable(data);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	//delete a user
	public void deleteUser(int userId){
        String sql = "DELETE FROM User WHERE UserId="+userId;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	System.out.println("刪除成功!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	//borrow book
	public void borrowBook(int bookId,int userId){
		
		Calendar dueDay=Calendar.getInstance();
		dueDay.add(Calendar.MONTH, 1);
		dueDay.add(Calendar.DATE, 7);		//default user borrow 7 days
		
		String date=String.valueOf(dueDay.get(Calendar.YEAR))+"-"+String.valueOf(dueDay.get(Calendar.MONTH)+"-"+String.valueOf(dueDay.get(Calendar.DATE)));
		String sql = "SELECT * FROM User WHERE UserId="+userId;
		String sql2 = "SELECT Available FROM Book WHERE BookId="+bookId;
        String sql3 = "UPDATE Book SET Available=0,BorrowerId="+userId+",DueDay="+"'"+date+"'"+" WHERE BookId="+bookId;
        //debug
        /*
        System.out.printf("%s\n",sql);
        System.out.printf("%s\n",sql2);
        System.out.printf("%s\n",sql3);
        */
        int number=0;	//NumberOfBooksBorrowed
        int avai=0;		//Available
        
        //不同的ResultSet要用不同的Statement才能正確執行
        try (Connection conn = this.connect();
        		Statement stmt  = conn.createStatement();
        		ResultSet rs    = stmt.executeQuery(sql);){
        	number=rs.getInt("NumberOfBooksBorrowed");
        	try(Statement stmt2  = conn.createStatement();
        			ResultSet rs2   = stmt2.executeQuery(sql2);){
        		rs2.next();
        		avai=rs2.getInt("Available");
        		if(number<10&&avai==1) {
        			int num=number+1;
        			String sql4 = "UPDATE User SET NumberOfBooksBorrowed="+num+" WHERE UserId="+userId;
        			//System.out.printf("%s\n",sql3); //debug
        			Statement stmt3  = conn.createStatement();
        			stmt3.executeUpdate(sql3);
        			Statement stmt4  = conn.createStatement();
        			stmt4.executeUpdate(sql4);
        			System.out.println("借書成功!");
        		}else {
        			System.out.printf("\nthe book is't available or the user has borrowed 10 books,can't borrow anymore\n");
        		}
        	} catch (SQLException e) {
            	System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        
    }
    
	
	//return book
	public void returnBook(int bookId,int userId) {
		
		String sql = "SELECT NumberOfBooksBorrowed FROM User WHERE UserId="+userId;
		String sql2 = "SELECT Available,BorrowerId FROM Book WHERE BookId="+bookId;
        String sql3 = "UPDATE Book SET Available=1,BorrowerId=null ,DueDay=null WHERE BookId="+bookId;
 
        //debug
        /*
        System.out.printf("%s\n",sql);
        System.out.printf("%s\n",sql2);
        System.out.printf("%s\n",sql3);
        */       
        
        //不同的ResultSet要用不同的Statement才能正確執行
        try (Connection conn = this.connect();
        		Statement stmt  = conn.createStatement();
        		ResultSet rs    = stmt.executeQuery(sql);
        		Statement stmt2  = conn.createStatement();
        		ResultSet rs2   = stmt2.executeQuery(sql2);){
        		//restrict user borrow at most 10 books
        		//check the book is available
        		if(rs.getInt("NumberOfBooksBorrowed")>0&&rs2.getInt("Available")==0&&rs2.getInt("BorrowerId")==userId) {
        			int num=rs.getInt("NumberOfBooksBorrowed")-1;
        			String sql4 = "UPDATE User SET NumberOfBooksBorrowed="+num+" WHERE UserId="+userId;
        			//System.out.printf("%s\n",sql3); //debug
        			Statement stmt3  = conn.createStatement();
        			stmt3.executeUpdate(sql3);
        			Statement stmt4  = conn.createStatement();
        			stmt4.executeUpdate(sql4);
        			System.out.println("還書成功!");
        		}else {
        			System.out.printf("\nthe book is't borrowed or the borrower is wrong \n");
        		}
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        
    }
	
	//find overdue books
	public void findOverdueBook(){
        String sql = "SELECT BookId, Title, Author,Available,BorrowerId,DueDay FROM Book";
        
        try (Connection conn = this.connect();
        		Statement stmt  = conn.createStatement();
        		ResultSet rs    = stmt.executeQuery(sql)){
        	
        	ArrayList<Object> array =new ArrayList<>();
        	int i=0;
        	
            // loop through the result set
        	System.out.printf("\n\nOverDue Books\n|%-8s|%-15s|%-8s|%-11s|%-12s|%-8s|\n","BookId","Title","Author","Available","BorrowerId","DueDay");
            Date now=new Date();
        	while (rs.next()) {
            	String sDate=rs.getString("DueDay");
            	//System.out.printf("%s\n",sDate);
            	Date date;
        		try {
        			if(sDate!=null) {
        				date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        			
        				if(date.before(now)) {
        					System.out.printf("|%-8d|%-15s|%-8s|%-11s|%-12s|%-8s|\n",rs.getInt("BookId"),rs.getString("Title"),rs.getString("Author"),rs.getInt("Available"),rs.getInt("BorrowerId"),rs.getString("DueDay"));
        				
        	            	array.add(rs.getInt("BookId"));
        	            	array.add(rs.getString("Title"));
        	            	array.add(rs.getString("Author"));
        	            	array.add(rs.getInt("Available"));
        	            	array.add(rs.getInt("BorrowerId"));
        	            	array.add(rs.getString("DueDay"));
        	            	i++;
        				
        				}
        			}
        			
        			//System.out.println(sDate+"\t"+date); 
        		} catch (Exception e) {
        			System.out.println(e); 
        		}  
            }
        	
        	Object[][] data=new Object[i][6];
        	
        	for(int j=0;j<i;j++) {
        		//test
        		//int temp=j*6;
        		//System.out.printf("temp=%d\n",temp);
        		//System.out.printf("%s",array.get(temp));
        		data[j][0]=array.get(j*6);
        		data[j][1]=array.get(j*6+1);
        		data[j][2]=array.get(j*6+2);
        		data[j][3]=array.get(j*6+3);
        		data[j][4]=array.get(j*6+4);
        		data[j][5]=array.get(j*6+5);
        	}
        	Table tb=new Table(data);
        	
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	//test
	/*
	public static void main(String[] args) {
		
        createBookTable();
        createUserTable();
        Sql sql=new Sql();
        //test book methods
        //int bookId=sql.findUsableBookId();
        //sql.addBook(bookId,"台灣深度之旅","林拓");
        //sql.addBook(bookId,"工具人的存在意義","無名氏");
        //sql.deleteBook(0);
        //sql.borrowBook(3,2);
        //sql.returnBook(3,2);
        
        //test user methods
        //int userId=sql.findUsableUserId();
        //sql.addUser(userId,"王大明");
        //sql.deleteUser(3);
        
        
        sql.findAllBook();
        sql.findAllUser();
        sql.findOverdueBook();
        
		
    }
	*/
}
