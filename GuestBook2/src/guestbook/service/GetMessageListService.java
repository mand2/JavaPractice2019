package guestbook.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.dao.MessageDao;
import guestbook.model.Message;
import guestbook.model.MessageListView;
import jdbc.ConnectionProvider;

public class GetMessageListService implements GuestBookService {
	
	/*싱글톤*/
	/*
	private GetMessageListService() {}
	private static GetMessageListService service = new GetMessageListService();
	
	public static GetMessageListService getInstance() {
		return service;
	}
	*/
	
	
	//1. 한 페이지에 보여줄 게시글의 개수
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	
	//page마다 보여주는 게시글이 달라짐. 
	/* MessageListView에 필요한 것들: 
	 * messageCountPerPage messageTotalCount 
	 * pageTotalCount curruntPageNumber 
	 * messageList 
	 * firstRow endRow
	 * @pageNumber : 현재 페이지
	 * @
	 * */
	public MessageListView getMessageListView(int pageNumber) {
		Connection conn = null;
		int currentPageNumber = pageNumber;
		MessageListView view = null;
		try {
			//Connection
			conn = ConnectionProvider.getConnection();
			
			//Dao
			MessageDao dao = MessageDao.getInstance();
			
			//전체게시물의개수
			int messageTotalCount = dao.selectCount(conn);
			
			//게시물 내용리스트 & DB의 검색으로 사용할 firstRow endRow 
			List<Message> messageList = null;
			int firstRow = 0;
			
			if(messageTotalCount > 0 ) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE ;
				messageList = dao.selectList(conn, firstRow, MESSAGE_COUNT_PER_PAGE );
				
			} else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			view = new MessageListView(MESSAGE_COUNT_PER_PAGE, messageTotalCount, currentPageNumber, messageList, firstRow);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return view ;
		
	}


	@Override
	public String getViewName(HttpServletRequest request, HttpServletResponse response) {
		
		String viewName = "WEB-INF/view/list_view.jsp";
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//사용자의 요청 받기
		int pageNumber = 1;
		String pageNo = request.getParameter("page");  //request에서 받아오는 페이지넘버
		if(pageNo != null) {
			pageNumber = Integer.parseInt(pageNo);
		}
		
		//해당페이지의 게시글 list를 객체로 만들어줌
		MessageListView viewData = getMessageListView(pageNumber);
		
		request.setAttribute("viewData", viewData);
		
		
		return viewName;
	}
	
	
}
