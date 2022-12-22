<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="st.css">
<script type="text/javascript" src="sc.js"></script>
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<div class="title">투표하기</div>
	<form class="info" name ="frm" action="insert">
		<table>
			<tr>
				<td>주민번호</td>
				<td><input type="text" name="v_jumin" /> <span>예 :
						8906153154726</span></td>
			</tr>
			<tr>
				<td>성명</td>
				<td><input type="text" name="v_name" /></td>
			</tr>
			<tr>
				<td>투표번호</td>
				<td><select name="m_no">
						<option value="">후보를 선택해주세요.</option>
						<option value="1">[1] 김후보</option>
						<option value="2">[2] 이후보</option>
						<option value="3">[3] 박후보</option>
						<option value="4">[4] 조후보</option>
						<option value="5">[5] 최후보</option>
				</select></td>
			</tr>
			<tr>
				<td>투표시간</td>
				<td><input type="text" name="v_time" /></td>
			</tr>
			<tr>
				<td>투표장소</td>
				<td><input type="text" name="v_area" /></td>
			</tr>
			<tr>
				<td>유권자확인</td>
				<td>
				<input type="radio" name="v_confirm" value="Y" />확인
				<input type="radio" name="v_confirm" value="N" />미확인
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn" type="submit"
						onclick="fn_submit(); return flase;">투표하기</button>
					<button class="btn" type="reset" onclick="restart()">다시하기</button>
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="footer.jsp"%>
</body>
</html>