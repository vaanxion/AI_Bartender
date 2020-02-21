<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="model.PO"
	import="util.CommonUtil"
	import="dao.PODao"
	import="java.util.ArrayList" %>

<jsp:include page="header.jsp"></jsp:include>
<script src="../js/query.js"></script>

<%
	int pageNum = new CommonUtil().getParseInt(request.getParameter("pageNum"));
	if(pageNum < 0) pageNum = 0;
	//System.out.println(pageNum);
%>
<div class="body">
	<form id="form" action="#" method="post">
		<input type="text" id="id" name="id" value="" hidden> 
		<input id="action" name="action" type="text" value="" hidden>
		<input id="pageNum" name="pageNum" type="number" value=<%=pageNum%> hidden>
		<table class="qTable">
			<tr>
				<th>訂單編號</th>
				<th>總金額</th>
				<th>狀態</th>
				<th>訂購人</th>
				<!-- <th>建立人</th>
				<th>建立時間</th>
				<th>最後修改人</th>
				<th>最後修改時間</th> -->
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%
				System.out.println("query");
					ArrayList<PO> arr = new PODao().query(null);
					int j = 0;
					if (arr.size() > 0) {
						int pageRow = pageNum * 10;
						for (int i = pageRow ; i < arr.size() && i < (pageRow + 10) ; i++) {
			%>
			<tr style='background-color:<%=j%2==0 ? "#f6c184" : "#f9dbb8" %>' >
				<td><%=arr.get(i).getId()%></td>
				<td><%=arr.get(i).getTotal()%></td>
				<td><%=arr.get(i).getStatus()%></td>
				<td><%=arr.get(i).getOwner()%></td>
				<%-- <td><%=arr.get(i).getCreateUser()%></td>
				<td><%=arr.get(i).getCreateTime()%></td>
				<td><%=arr.get(i).getUpdateUser()%></td>
				<td><%=arr.get(i).getUpdateTime()%></td> --%>
				<td><input type="button" value="修改" name="update" uuid="<%=arr.get(i).getId()%>"></td>
				<td><input type="button" value="刪除" name="del"	uuid="<%=arr.get(i).getId()%>"></td>
			</tr>
			<%
						j++;
					}
			%>
				<tr>
					<td colspan="14">
					<br>
						<% if(pageRow > 0){ %>
						<input type="button" id="pre" name="pre" value="上一頁">
						<% }
						   if(arr.size() > pageRow + 10){ %>
						<input type="button" id="next" name="next" value="下一頁">
						<% } %>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;第<%=pageNum+1 %>頁</span>
					</td>
				</tr>
			<%
				} else {
			%>
			<tr>
				<td colspan="14">查無資料，請先新增</td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</div>

<script>
	<%-- $(document).ready(function() {
		$("input[name=del]").click(function() {
			$("#id").val($(this).attr("uuid"));
			$("#action").val("del");
			$("#form").attr("action", "action.jsp");
			if(<%=j == 1%>){
				$("#pageNum").val(0);
			}
			$("#form").submit();
		});
	}); --%>
</script>
<jsp:include page="footer.jsp"></jsp:include>
