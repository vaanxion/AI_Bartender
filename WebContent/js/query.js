$(document).ready(function() {

	$("input[name=update]").click(function() {
		$("#id").val($(this).attr("uuid"));
		$("#form").attr("action", "edit.jsp");
		$("#form").submit();
	});
	$("#pre").click(function() {
		var pageNum = $("#pageNum").val();
		$("#form").attr("action", "query.jsp");
		if (pageNum > 0) {
			$("#pageNum").val(--pageNum);
		}
		$("#form").submit();
	});

	$("#next").click(function() {
		var pageNum = $("#pageNum").val();
		$("#form").attr("action", "query.jsp");
		$("#pageNum").val(++pageNum);
		$("#form").submit();
	});
});