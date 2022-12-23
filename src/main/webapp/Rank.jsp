<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Vote"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Vote> vote = new ArrayList<Vote>();
vote = (ArrayList<Vote>) request.getAttribute("vote");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="st.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">후보자등수</div>
		<table>
			<tr>
				<td>후보번호</td>
				<td>성명</td>
				<td>총투표건수</td>
			</tr>
			<%
			for (Vote v : vote) {
			%>
			<tr>
				<td><%=v.getM_no()%></td>
				<td><%=v.getV_name()%></td>
				<td><%=v.getV_count()%></td>
			</tr>
			<%
			}
			%>
		</table>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>