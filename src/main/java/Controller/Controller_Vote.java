package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;

/**
 * Servlet implementation class Controller_Vote
 */
@WebServlet("/")
public class Controller_Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller_Vote() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
		System.out.println("request: " + request  + ", response" + response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
		System.out.println("request: " + request  + ", response" + response);
	}

	/*모든 파일들이 controller를 경유하니까 doPro도 경유 할 것이다.
	 * 이때 doPro의 역할은 버튼을 누를 때 다음 페이지로 넘어가는 곳에 필요한 메소드를
	 * 적용 시켜주는 것이다 (메소드는 DAO에서 처리한다)*/
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DTO 순수 객체에서 이름만 따온다 (출력결과는 /Vote)
		String context = request.getContextPath();	//톰캣의 context path를 가져온다
		//페이지가 넘어 갈 때 (출력결과는 context/command 경로로 나올 것이다)
		String command = request.getServletPath();	//파일 경로의 맨 마지막 부분을 나타낸다 (페이지가 어디인지 알려주는 역할)
		String site = "main.jsp";
		
		System.out.println(context + ", " + command);
		
		//메소드를 DAO에서 처리하기 때문에 연결시켜준다
		DAO dao = new DAO();
		
		switch(command) {
		case "/info":
			site = dao.info(request, response); break;
		case "/vote":
			site = "Vote.jsp"; break;
		case "/insert":
			int result = dao.vote(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(result == 1) {
				out.println("<script>");
				out.println(" alert('투표 정보가 등록 되었습니다!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println(" alert('투표실패!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			}
			break;
		case "/check":
			site = dao.check(request, response); break;
//		case "/rank":
//			site = dao.rank(request, response); break;
		case "/home":
			site = "main.jsp"; break;	//"홈으로" 버튼 누르면 main.jsp로 페이지 이동
		}
		//주의 할점.
		//디스패처 뒤에 .forward(request, response)를 하지 않으면 다음페이지에 정보가 따라오지 않는다
		
		//getRequestDispatcher(경로.jsp)는 현재 switch문으로 통과된 site값을 경로로 지정해준다
		//forward는 페이지를 이동시키는 메소드이다
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);;
	}
}
