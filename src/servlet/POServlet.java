package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PODao;
import model.PO;
import util.CommonUtil;

/**
 * Servlet implementation class POaction
 */
@WebServlet("/POaction")
public class POServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public POServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		CommonUtil comm = new CommonUtil();
		PODao dao = new PODao();
		PO po = new PO();
		boolean b = false;
		
		String pageAction = comm.getParseString(request.getParameter("action"));
		int pageNum = comm.getParseInt(request.getParameter("pageNum"));
		if (pageNum < 0)
			pageNum = 0;
		po.setId(comm.getParseString(request.getParameter("id")));
		po.setAction(po.getId().isEmpty() ? "add" : "update");
		po.setTotal(comm.getParseInt(request.getParameter("total")));
		po.setOwner(comm.getParseString(request.getParameter("memberId")));
		po.setStatus(comm.getParseString(request.getParameter("status")));

		po.setCreateUser(comm.getParseString(request.getParameter("memberId")));
		po.setCreateTime(new Date().toString());
		po.setUpdateUser(comm.getParseString(request.getParameter("memberId")));
		po.setUpdateTime(new Date().toString());
//		if (id.equals("")) {
//			
//		}
		
		if (pageAction.equals("del") && po.getId().isEmpty()) {
			b = dao.del(po);
		} else if (pageAction.equals("update")) {
//			po.setAction(id.equals("") ? "add" : "update");
//			po.setTotal(total);
//			po.setStatus(status);
//			po.setUpdateUser(memberId);
//			po.setUpdateTime(new Date().toString());

			if (po.getAction().equals("add")) {
				po.setId("O001");
//				po.setOwner(memberId);
//				po.setCreateUser(memberId);
//				po.setCreateTime(new Date().toString());
			}
//			else {
//				po.setId(id);
//			}

			b = dao.update(po);
		}else {
			System.out.println("Error: 刪除時id不可為空");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
