

public class BookManagement {
	//*
	public static void main(String args[]) {
		
		Ui ui=new Ui();
	}
		/*
		while(true) {
			System.out.println("select mode (Enter 0~9)");  	//cmd-based UI design,not done yet
			System.out.println("\n1.Add Book\t2.Delete Book\t3.Find All Book");
			System.out.println("\n4.Borrow Book\t5.Return Book\t6.Find OverDue Book");
			System.out.println("\n7.Add User\t8.Find User");
			System.out.println("\n0.End Program");
			//Scanner scan=new Scanner(System.in);
			String input="3";  //default 3 show all book
			while(true) {
				try {
					Scanner scan=new Scanner(System.in);
					input=scan.nextLine();
					break;
				}catch(Exception e){}
			}
			switch(input) {
				//0.End Program
				case "0":
					//scan.close();
					System.exit(0);
					break;
				//1.Add Book
				case "1":
					addBook();
					
					
					break;
				//3.Find All Book
				case "3":
					findAllBook();
					while(true) {
						try {
							//System.out.printf("Enter next instruction");
							//System.out.printf("input=%s",input);
							break;
						}catch(Exception e){}
					}
					break;
				default:
					break;
			}
		}
		*/
	
}