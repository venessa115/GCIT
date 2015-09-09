package com.gcit.lms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Publisher;

public class JDBC {

	public void addAuthor(Author author){
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_author (authorName) values (?)");
			pstmt.setString(1, author.getAuthorName());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addBook(Book book){
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book (title) values (?)");
			pstmt.setString(1, book.getTitle());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addPublisher(Publisher p){
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_publisher (publisherName,publisherAddress) values (?,?)");
			pstmt.setString(1,p.getPublisherName());
			pstmt.setString(2,p.getPublisherAddress());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAuthor(Author author)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_author where authorId=?");
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteBook(Book book)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book where bookId=?");
			pstmt.setInt(1, book.getBookId());
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void deletePublisher(Publisher p)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_publisher where publisherId=?");
			pstmt.setInt(1, p.getPublisherId());
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getAuthorName(Author author) throws SQLException
	{
		
		String name=new String();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select authorName from tbl_author where authorId=?");
			pstmt.setInt(1, author.getAuthorId());
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
	public String getBookName(Book book) throws SQLException
	{
		
		String name=new String();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select title from tbl_book where bookId=?");
			pstmt.setInt(1, book.getBookId());
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
	public String getPublisherName(Publisher p) throws SQLException
	{
		
		String name=new String();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select publisherName from tbl_publisher where publisherId=?");
			pstmt.setInt(1, p.getPublisherId());
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
	public String getPublisherAddress(Publisher p) throws SQLException
	{
		
		String name=new String();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select publisherAddress from tbl_publisher where publisherId=?");
			pstmt.setInt(1, p.getPublisherId());
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
	
	
	
	
	public void editAuthor(String authorname,Author author,int id)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_author set authorName = replace(authorName, ? , ?) where authorId=?");
			pstmt.setString(1, authorname);
			pstmt.setString(2, author.getAuthorName());
			pstmt.setInt(3,id);
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editBook(String bookname,Book book,int bookId)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book set title = replace(title, ? , ?) where bookId=?");
			pstmt.setString(1, bookname);
			pstmt.setString(2, book.getTitle());
			pstmt.setInt(3,bookId);
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editPublisher(String pubname,String pubAddress,Publisher p,int pubId)
	{
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("update tbl_publisher set publisherName = ?,publisherAddress=? where publisherId=?");
			pstmt.setString(1, p.getPublisherName());
			pstmt.setString(2, p.getPublisherAddress());
			pstmt.setInt(3,pubId);
			pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public List<Author> getAuthors(){
		List<Author> authors = new ArrayList<Author>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_author");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Author a = new Author();
				a.setAuthorId(rs.getInt("authorId"));
				a.setAuthorName(rs.getString("authorName"));
				
				authors.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}
	
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Book a = new Book();
				a.setBookId(rs.getInt("bookId"));
				a.setTitle(rs.getString("title"));
				//a.setPubId(rs.getInt("pubId"));
				books.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	public List<Publisher> getPublishers(){
		List<Publisher> publishers = new ArrayList<Publisher>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_publisher");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Publisher a = new Publisher();
				a.setPublisherId(rs.getInt("publisherId"));
				a.setPublisherName(rs.getString("publisherName"));
				a.setPublisherAddress(rs.getString("publisherAddress"));
				publishers.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return publishers;
	}
	
	public List<Branch> getBranches(){
		List<Branch> branches = new ArrayList<Branch>();
		try {
			PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_library_branch");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				Branch a = new Branch();
				a.setBranchId(rs.getInt("branchId"));
				a.setBranchName(rs.getString("branchName"));
				a.setBranchAddress(rs.getString("branchAddress"));

				
				branches.add(a);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branches;
	}
	
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "venessa");
		return conn;
	}
}
