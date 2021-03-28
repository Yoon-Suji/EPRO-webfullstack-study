package kr.or.connect.todo.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

/**
 * Servlet implementation class TodoTypeServlet
 */
@WebServlet("/todotype")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id =Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		String newType = "";
		if (type.equals("TODO"))
			newType = "DOING";
		else if(type.equals("DOING"))
			newType = "DONE";
		
		TodoDto dto = new TodoDto(newType, id);
		TodoDao dao = new TodoDao();
		dao.updateTodo(dto);
		
		response.sendRedirect("main");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
