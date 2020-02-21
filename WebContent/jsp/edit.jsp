<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.*" import="java.util.ArrayList"%>

<jsp:include page="header.jsp"></jsp:include>
<script src="../js/edit.js"></script>

<%
	String id = new util.CommonUtil().getParseString(request.getParameter("id"));
	String owner = "";
	String status = "";
	int total = 0;

	ArrayList<PO> arr = null;
	if (id != null) {
		PO po = new PO();
		po.setId(id);
		arr = new dao.PODao().query(po);
		for (PO apo : arr) {
	owner = apo.getOwner();
	status = apo.getStatus();
	total = apo.getTotal();
		}
	}
%>
<div class="body">
	<form id="form" action="action.jsp" method="post">
		<input id="id" name="id" type="text" value='<%=id != null ? id : ""%>' hidden>
		<input id="action" name="action" type="text" value="edit" hidden>
		<table class="eTable">
			<tr>
				<td><span><font>*</font> 訂單編號</span></td>
				<td><%=id%></td>
			</tr>
			<tr>
				<td><span><font>*</font> 訂購人 </span></td>
				<td><input id="owner" name="owner" type="text" value=<%=owner%>></td>
			</tr>
			<tr>
				<td><span><font>*</font> 狀態 </span></td>
				<td><input id="status" name="status" type="text" value=<%=status%>></td>
			</tr>
			<tr>
				<td><span><font>*</font> 總金額 </span></td>
				<td><input id="total" name="total" type="number" value=<%=total%> min="0"></td>
			</tr>
		</table>
		<input type="button" value="新增/修改" name="edit" id="edit"> <br>
		<br>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>