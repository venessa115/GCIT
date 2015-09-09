package com.gcit.lms.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.database.JDBC;
import com.gcit.lms.database.LibrarianJDBC;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Publisher;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({"/addAuthor", "/editAuthor","/deleteAuthor","/branchName","/editBranch","/bookName","/addCopies","/forwardBranch","/verifyBorrower","/checkBranch","/checkBook","/returnBranch","/returnBook","/addBook","/addPublisher","/deleteBook","/deletePublisher","/editPublisher","/editBook"})
public class AdminServlet extends HttpServlet {
	
	int ai,booki,pubi;
	String an,bookname,publishername,publisheraddress;
	String bi;
	String bn,bName,bAddress,bookId,copies,cardno,checkbranch,checkbook,returnbranch,returnbook;
	int no_of_copies=0;
	boolean verifyresult;
	private static final long serialVersionUID = 1L;
	boolean returnverify;

    /**
     * Default constructor. 
     */
    public AdminServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		
		String forwardPath = "/admin.jsp";
		switch (reqUrl) {
		case "/editAuthor":
			System.out.println("venessa");
			try {
			editAuthorget(request);
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editauthor.jsp");
			rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//TODO Call Edit on JDBC
			break;
		case "/editBook":
			
			try {
			editBookget(request);
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editbook.jsp");
			rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//TODO Call Edit on JDBC
			break;
			case "/editPublisher":
			
			try {
			editPublisherget(request);
			
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/editpublisher.jsp");
			rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//TODO Call Edit on JDBC
			break;
			
		case "/deleteAuthor":
			//int id=request.getParameter("authorId)
			deleteAuthor(request);
			String message="The author was deleted successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message, "UTF-8"));

			//TODO Call Delete on jDBC
			break;
		case "/forwardBranch":
			request.setAttribute("branchid",bi);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/addcopies.jsp");
			rd.forward(request, response);
			break;
		case "/deleteBook":
			deleteBook(request);
			String message1="The book was deleted successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message1, "UTF-8"));
			break;
		case "/deletePublisher":
			deletePublisher(request);
			String message3="The publisher was deleted successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message3, "UTF-8"));
			break;
			
			
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());
		
		String forwardPath = "/admin.jsp";
		switch (reqUrl) {
		case "/addAuthor":
			if(request.getParameter("authorName")=="")
			{
				String message="Please enter an author name";
				response.sendRedirect("addauthor.jsp?message=" + URLEncoder.encode(message, "UTF-8"));	
			}

			else
			{
			addAuthor(request);
			String message="The author has been added successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			break;
		case "/editAuthor":
			if(request.getParameter("authorName")=="")
			{
				String message="Please enter a author name";
				response.sendRedirect("editauthor.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			else{
			try {
				editAuthorpost(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
			String message="The author has been updated successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			break;
		case "/editBook":
			if(request.getParameter("bookName")=="")
			{
				String message="Please enter a book name";
				response.sendRedirect("editbook.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			else
			{
			try {
				editBookpost(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
			String message1="The book has been updated successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message1, "UTF-8"));
			}
			break;
		case "/editPublisher":
			if(request.getParameter("publisherName")=="")
			{
				String message="Please enter a publisher name";
				response.sendRedirect("editpublisher.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			else if(request.getParameter("publisherAddress")=="")
			{
				String message="Please enter a publisher address";
				response.sendRedirect("editpublisher.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			}
			else{
			try {
				editPublisherpost(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
			String message2="The publisher has been updated successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(message2, "UTF-8"));
			}
			break;
			
			
		case "/branchName":
			bi=request.getParameter("branch");
			System.out.println(bi);
			if(bi.equals("select a branch"))
			{
				String msg="Please enter a branch";
				response.sendRedirect("branch.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
				
			}
			else{
		try {
			getBranchName(request);
			String msg="You have selected: "+ bn;
			response.sendRedirect("liboptions.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			}
			break;
		
		case "/editBranch":
			try {
				bName=request.getParameter("branchName");
				System.out.println("v1 "+bName);
				bAddress=request.getParameter("address");
				editBranch(request);
				String msg="The details have been updated successfully";
				response.sendRedirect("liboptions.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/bookName":
			try {
				bookId=request.getParameter("book");
				System.out.println("bookId "+bookId);
				if(bookId.equals("select a book"))
				{
					String msg="Please enter a book name";
					response.sendRedirect("addcopies.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
					
				}
				
				else{
				int nocopies=getBookCopies(request);
				System.out.println(nocopies);
				
				String msg="Existing number of copies:"+Integer.toString(nocopies);
				System.out.println(msg);
				response.sendRedirect("addcopiescont.jsp?message=" +msg);
				}
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/addCopies":
			copies=request.getParameter("copies");
			
			System.out.println(copies);
			try {
				if(copies=="")
				{
					String msg="Please enter a number";
					System.out.println(msg);
					response.sendRedirect("addcopiescont.jsp?message=" +msg);	
				}
				else{
				editBookCopies(request);
				String msg="The update is successful";
				System.out.println(msg);
				response.sendRedirect("liboptions.jsp?message=" +msg);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/verifyBorrower":
			cardno=request.getParameter("cardno");

			LibrarianJDBC jdbc=new LibrarianJDBC();
			verifyresult=jdbc.verifycard(Integer.parseInt(cardno));
			System.out.println(verifyresult);
			if(verifyresult==true){
				response.sendRedirect("borrower.jsp?message=" + URLEncoder.encode("", "UTF-8"));
				
			}
			else
			{
				String msg="Please enter a valid card no";
				response.sendRedirect("verifyborrower.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));	
			}
			break;
		case "/checkBranch":
			checkbranch=request.getParameter("branch");
			if(checkbranch.equals("select a branch"))
			{
				String msg="Please enter a branch";
				response.sendRedirect("checkoutbranch.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
				
			}
			else{
		
			try {
				getBranchCheck(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("branchid",checkbranch);
			System.out.println(checkbranch);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/checkoutbook.jsp");
			rd.forward(request, response);
			//String msg="You have selected: "+ checkbranch;
			//response.sendRedirect("checkoutbook.jsp?message=" + URLEncoder.encode(bi, "UTF-8"));
			
			
		
			}
			break;
		case "/checkBook":
			checkbook=request.getParameter("book");
			try {
				if(checkbook.equals("select a book"))
				{
					String msg="Please enter a book";
					response.sendRedirect("checkoutbook.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
					
				}
				else{
				check(request);
				String msg="The check out was successful";
				response.sendRedirect("borrower.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "/returnBranch":
			returnbranch=request.getParameter("branch");
			if(returnbranch.equals("select a branch"))
			{
				String msg="Please enter a branch";
				response.sendRedirect("returnbranch.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
				
			}
			else{
		
			try {
				getBranchReturn(request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("branchid",returnbranch);
			System.out.println(returnbranch);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/returnbook.jsp");
			rd.forward(request, response);
			
			}
			break;
		case "/returnBook":
			returnbook=request.getParameter("book");
			
				try {
					if(returnbook.equals("select a book"))
					{
						String msg="Invalid entry";
						response.sendRedirect("returnbranch.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
						
					}
					else
					{
						if(returnverify==true)
						{
				returnbook(request);
				String msg="Your book has been returned successfully";
				response.sendRedirect("borrower.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
						}
						else
						{
							String msg="You do not have any borrowed books";
							response.sendRedirect("borrower.jsp?message=" + URLEncoder.encode(msg, "UTF-8"));
							
						}
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "/addBook":
			if(request.getParameter("bookName")=="")
			{
				String messagebook="Please enter an book name";
				response.sendRedirect("addbooks.jsp?message=" + URLEncoder.encode(messagebook, "UTF-8"));	
			}

			else
			{
			addBook(request);
			String messagebook="The book has been added successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(messagebook, "UTF-8"));
			}
			break;
		case "/addPublisher":
			if(request.getParameter("publisherName")=="")
			{
				String messagebook="Please enter an publisher name";
				response.sendRedirect("addpublishers.jsp?message=" + URLEncoder.encode(messagebook, "UTF-8"));	
			}
			else if(request.getParameter("publisherAddress")=="")
			{
				String messagebook="Please enter an publisher address";
				response.sendRedirect("addpublishers.jsp?message=" + URLEncoder.encode(messagebook, "UTF-8"));	
			}

			else
			{
			addPublisher(request);
			String messagebook="The publisher has been added successfully";
			response.sendRedirect("admin.jsp?message=" + URLEncoder.encode(messagebook, "UTF-8"));
			}
			break;
			
			
			
		default:
			break;
		}

		/*RequestDispatcher rd = request.getServletContext().getRequestDispatcher(forwardPath);
		rd.forward(request, response);*/
	}

	private void addAuthor(HttpServletRequest request) {
		String authorName = request.getParameter("authorName");
		
		
		Author author = new Author();
		author.setAuthorName(authorName);
		
		JDBC jdbc = new JDBC();
		jdbc.addAuthor(author);
		
	}
	private void addBook(HttpServletRequest request) {
		String name = request.getParameter("bookName");
		
		
		Book book= new Book();
		book.setTitle(name);
		
		JDBC jdbc = new JDBC();
		jdbc.addBook(book);
		
	}
	private void addPublisher(HttpServletRequest request) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		
		Publisher p=new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherAddress(publisherAddress);
		
		JDBC jdbc = new JDBC();
		jdbc.addPublisher(p);
		
	}
	private void deleteAuthor(HttpServletRequest request) {
		String authorId = request.getParameter("authorId");
		System.out.print("venessa "+authorId);
		
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));
		
		JDBC jdbc = new JDBC();
		jdbc.deleteAuthor(author);
		
	}
	
	private void deleteBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		System.out.print("venessa "+bookId);
		
		Book book=new Book();
		book.setBookId(Integer.parseInt(bookId));
		
		JDBC jdbc = new JDBC();
		jdbc.deleteBook(book);
		
	}
	private void deletePublisher(HttpServletRequest request) {
		String publisherId = request.getParameter("publisherId");
		System.out.print("venessa "+publisherId);
		
		Publisher p=new Publisher();
		p.setPublisherId(Integer.parseInt(publisherId));
		
		JDBC jdbc = new JDBC();
		jdbc.deletePublisher(p);
		
	}
	
	private void getBranchName(HttpServletRequest request) throws ServletException, IOException, SQLException {
		
		Branch branch=new Branch();
		branch.setBranchId(Integer.parseInt(bi));
		LibrarianJDBC jdbc=new LibrarianJDBC();
		bn=jdbc.getBranchName(branch);
		
		}
private void getBranchCheck(HttpServletRequest request) throws ServletException, IOException, SQLException {
		
		Branch branch=new Branch();
		branch.setBranchId(Integer.parseInt(checkbranch));
		LibrarianJDBC jdbc=new LibrarianJDBC();
		bn=jdbc.getBranchName(branch);
		
		}
private void getBranchReturn(HttpServletRequest request) throws ServletException, IOException, SQLException {
	
	Branch branch=new Branch();
	branch.setBranchId(Integer.parseInt(returnbranch));
	LibrarianJDBC jdbc=new LibrarianJDBC();
	bn=jdbc.getBranchName(branch);
	
	}

	private void editAuthorget(HttpServletRequest request) throws ServletException, IOException, SQLException {
		String authorId = request.getParameter("authorId");
		System.out.println(authorId);
		
		ai=Integer.parseInt(authorId);
		Author author = new Author();
		author.setAuthorId(ai);
		JDBC jdbc = new JDBC();
		an=jdbc.getAuthorName(author);
		
		
		
	}
	
	private void editBookget(HttpServletRequest request) throws ServletException, IOException, SQLException {
		String bookId = request.getParameter("bookId");
		//System.out.println(authorId);
		
		booki=Integer.parseInt(bookId);
		Book b=new Book();
		b.setBookId(booki);
		JDBC jdbc = new JDBC();
		bookname=jdbc.getBookName(b);
		
		
		
	}
	private void editPublisherget(HttpServletRequest request) throws ServletException, IOException, SQLException {
		String pubId = request.getParameter("publisherId");
		//System.out.println(authorId);
		
		pubi=Integer.parseInt(pubId);
		Publisher p=new Publisher();
		p.setPublisherId(pubi);
		JDBC jdbc = new JDBC();
		publishername=jdbc.getPublisherName(p);
		publisheraddress=jdbc.getPublisherAddress(p);
	
	}
	

	
	private void editAuthorpost(HttpServletRequest request) throws ServletException, IOException, SQLException {
		

		JDBC jdbc = new JDBC();
		System.out.println("ven "+ai);
		
		String newAuthorName=request.getParameter("authorName");
		System.out.println("ven "+newAuthorName);
		Author author = new Author();
		author.setAuthorName(newAuthorName);
		jdbc.editAuthor(an,author,ai);
		
	
	}
private void editBookpost(HttpServletRequest request) throws ServletException, IOException, SQLException {
		

		JDBC jdbc = new JDBC();
		System.out.println("ven "+ai);
		
		String newBookName=request.getParameter("bookName");
		
		Book book=new Book();
		book.setTitle(newBookName);
		jdbc.editBook(bookname,book,booki);
		
	
	}
private void editPublisherpost(HttpServletRequest request) throws ServletException, IOException, SQLException {
	

	JDBC jdbc = new JDBC();
	System.out.println("ven "+ai);
	
	String newPublisherName=request.getParameter("publisherName");
	String newPublisherAddress=request.getParameter("publisherAddress");
	
	Publisher p=new Publisher();
	p.setPublisherName(newPublisherName);
	p.setPublisherAddress(newPublisherAddress);
	jdbc.editPublisher(publishername,publisheraddress,p,pubi);
	

}
	
	private void editBranch(HttpServletRequest request) throws ServletException, IOException, SQLException 
	{
		
		Branch branch=new Branch();
		branch.setBranchName(bName);
		branch.setBranchAddress(bAddress);
		LibrarianJDBC jdbc=new LibrarianJDBC();
		
		jdbc.editBranch(branch,Integer.parseInt(bi));

	
	}
	
	private int getBookCopies(HttpServletRequest request) throws ServletException, IOException, SQLException 
	{
		
		
		Book book=new Book();
		book.setBookId(Integer.parseInt(bookId));
		LibrarianJDBC jdbc=new LibrarianJDBC();
		
		no_of_copies=jdbc.getBookCopies(book);
		return no_of_copies;
	}
	
	private void editBookCopies(HttpServletRequest request) throws ServletException, IOException, SQLException 
	{
		System.out.println("venessa");
		LibrarianJDBC jdbc=new LibrarianJDBC();
		int newcopies=Integer.parseInt(copies)+no_of_copies;
		
		jdbc.editBookCopies(newcopies,Integer.parseInt(bookId));
	
	}
	
	private void check(HttpServletRequest request) throws ServletException, IOException, SQLException 
	{
		Book book=new Book();
		LibrarianJDBC jdbc=new LibrarianJDBC();

		book.setBookId(Integer.parseInt(checkbook));
		int no_of_copies=jdbc.getBookCopies(book);

		int newcopies=no_of_copies-1;

		
		jdbc.editBookCopies(newcopies,Integer.parseInt(checkbook));
		jdbc.enterCheckedBook(Integer.parseInt(checkbook),Integer.parseInt(checkbranch),Integer.parseInt(cardno));
	}
	
	private void returnbook(HttpServletRequest request) throws ServletException, IOException, SQLException 
	{
		Book book=new Book();
		LibrarianJDBC jdbc=new LibrarianJDBC();

		book.setBookId(Integer.parseInt(returnbook));
		int no_of_copies=jdbc.getBookCopies(book);

		int newcopies=no_of_copies+1;
		jdbc.editBookCopies(newcopies,Integer.parseInt(returnbook));
		returnverify=jdbc.returnBook(Integer.parseInt(returnbook),Integer.parseInt(returnbranch),Integer.parseInt(cardno));

	}
	
	
}
