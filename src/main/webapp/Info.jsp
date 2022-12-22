<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Member"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Member> members= new ArrayList<Member>();
members = (ArrayList<Member>) request.getAttribute("members");
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
		<div class="title">후보조회</div>
		<div class="wrapper">
			<table>
				<tr>
					<td>후보번호</td>
					<td>성명</td>
					<td>소속정당</td>
					<td>학력</td>
					<td>주민번호</td>
					<td>지역구</td>
					<td>대표전화</td>
				</tr>
	          <%
				for (Member m : members) {
				%>
				<tr>
					<td><%=m.getM_no()%></td>
					<td><%=m.getM_name()%></td>
					<td><%=m.getParty()%></td>
					<td><%=m.getP_school()%></td>
					<td><%=m.getM_jumin()%></td>
					<td><%=m.getM_city()%></td>
					<td><%=m.getTel_all()%></td>
				</tr>
				<%
				}
				%> 
			</table>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>