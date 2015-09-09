package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;

public class LibrarianJDBC {
	
	public boolean verifycard(int cardno)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select cardNo from tbl_borrower");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			if(cardno==rs.getInt(1))
			{
			return true;	
			}
				
			}
			
		}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return false;
		
	}
	public boolean returnBook(int bookId,int branchId,int cardno)
	{
		 int rowCount = 0,t=1;
		  List rownames = new ArrayList();
		  List rownames1 = new ArrayList();
		
		try
		{
			PreparedStatement pstmt = getConnection().prepareStatement("select cardNo from tbl_book_loans where branchId=? and cardNo=?");
			
			pstmt.setInt   (1,branchId);
			pstmt.setInt   (2,cardno);
			ResultSet rs=pstmt.executeQuery();
			if(rs.last()){
			    rowCount = rs.getRow(); 
				  System.out.println("rowcount"+rowCount);

			    rs.beforeFirst();
			    
			}
			while (rs.next()) {
			    
			    rownames.add(rs.getInt(1));
			    
			    	
			    }
			
			int [] option = new int  [rownames.size()];
			  for(int i = 0;i < option.length;i++)
				  option[i] = (int) rownames.get(i);
			  
			  
			  PreparedStatement pstmt1= getConnection().prepareStatement("select branchId from tbl_book_loans where branchId=? and cardNo=?");
			  pstmt1.setInt   (1,branchId);
				pstmt1.setInt   (2,cardno);
				ResultSet rs1=pstmt1.executeQuery();
				
				
				while (rs1.next()) {
				    
				    rownames1.add(rs1.getInt(1));
				    
				    	
				    }
				
				int [] option1 = new int  [rownames1.size()];
				  for(int i = 0;i < option1.length;i++)
					  option1[i] = (int) rownames1.get(i);
				  
				  for(int i=0;i< rowCount;i++)
					{
						System.out.println("v"+option[i]);
						System.out.println("v1"+option1[i]);
					if(option[i]==cardno && option1[i]==branchId){  
			  
			Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		
		String  query1="update tbl_book_loans set dateIn = ? where tbl_book_loans.cardNo=? and tbl_book_loans.branchId=? and tbl_book_loans.bookId=?";
	    PreparedStatement preparedStmt2 = getConnection().prepareStatement(query1);
	    
	    preparedStmt2.setTimestamp(1, timestamp);
	     preparedStmt2.setInt   (2,cardno);
	     preparedStmt2.setInt   (3,branchId);
	     preparedStmt2.setInt   (4,bookId);
	    preparedStmt2.executeUpdate();
	    return true;
		
					}
		}
				 
		 }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("no data");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
		}
		return false;

		
	}
	public void enterCheckedBook(int bookId,int branchId,int cardno)
	{
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		Calendar cal=Calendar.getInstance();
		cal.setTime(timestamp);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		Timestamp timenew=new Timestamp(cal.getTime().getTime());
		try
		{

	    String  query1="insert into tbl_book_loans values(?,?,?,?,?,?)";
	    PreparedStatement preparedStmt2 = getConnection().prepareStatement(query1);
	    preparedStmt2.setInt   (1,bookId);
	     preparedStmt2.setInt   (2,branchId);
	     preparedStmt2.setInt   (3,cardno);
	     preparedStmt2.setTimestamp(4, timestamp);
	     preparedStmt2.setTimestamp(5, timenew);
	     preparedStmt2.setTimestamp(6,null);
	     preparedStmt2.executeUpdate();
	    preparedStmt2.executeUpdate();
		  
	  }
			catch (SQLException e) {
				// TODO Auto-generated catch block
				//System.out.println("no data");
				e.printStackTrace();
			}
			catch(ClassNotFoundException e)
			{
			}

	}

	public String getBranchName(Branch branch)
	{
		String name=new String();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select branchName from tbl_library_branch where branchId=?");
			pstmt.setInt(1, branch.getBranchId());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			name=rs.getString(1);
			}
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	public void editBranch(Branch branch,int branchId)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_library_branch set branchName=?, branchAddress=? where branchId=?");
			
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());

			pstmt.setInt(3,branchId);
			

			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getBookCopies(Book b)
	{
		int noOfCopies=0;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select tbl_book_copies.noOfCopies from tbl_book_copies join tbl_library_branch on tbl_library_branch.branchId=tbl_book_copies.branchId join tbl_book on tbl_book.bookId=tbl_book_copies.bookId where tbl_book_copies.bookId=?");
			pstmt.setInt(1, b.getBookId());
			ResultSet rs = pstmt.executeQuery();
			
		
				while(rs.next()){
					noOfCopies=rs.getInt(1);
					}
					
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfCopies;
	}
	
	public List<Book> getBooks(int branchId){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book join tbl_book_copies on tbl_book.bookId=tbl_book_copies.bookId join tbl_library_branch on tbl_library_branch.branchId=tbl_book_copies.branchId  where tbl_book_copies.branchId=?");
			pstmt.setInt(1,branchId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Book b = new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setTitle(rs.getString("title"));
				

				
				books.add(b);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public void editBookCopies(int copies,int bookId)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_copies join tbl_book on tbl_book.bookId=tbl_book_copies.bookId set tbl_book_copies.noOfCopies = ? where tbl_book.bookId=?");
			pstmt.setInt(1,copies);
			pstmt.setInt(2,bookId);
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "venessa");
		return conn;
	}
}
