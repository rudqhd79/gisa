<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Vote"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Vote> votes= new ArrayList<Vote>();
votes = (ArrayList<Vote>) request.getAttribute("votes");
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
		<div class="title">투표검수조회</div>
		<div class="wrapper">
			<table>
			<tr>
				<td>성명</td>
				<td>생년월일</td>
				<td>나이</td>
				<td>성별</td>
				<td>후보번호</td>
				<td>투표시간</td>
				<td>유권자확인</td>
			</tr>
	          <%
				for (Vote v : votes) {
				%>
				<tr>
					<td><%=v.getV_name()%></td>
					<td><%=v.getV_birth()%></td>
					<td><%=v.getV_age()%></td>
					<td><%=v.getV_sex()%></td>
					<td><%=v.getM_no()%></td>
					<td><%=v.getV_time()%></td>
					<td><%=v.getV_confirm()%></td>
				</tr>
				<%
				}
				%> 
			</table>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>