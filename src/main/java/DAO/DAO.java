package DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Member;
import DTO.Vote;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sys1234");
		return con;
	}

	public String info(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Member> members = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT A.M_NO AS 후보번호, A.M_NAME AS 성명, B.P_NAME AS 소속정당, ";
			sql += " DECODE(A.P_SCHOOL, '1', '고졸', '2', '학사', '3', '석사', '4', '박사') AS 학력, ";
		    sql += " SUBSTR(A.M_JUMIN, 1, 6) || '-' || SUBSTR(A.M_JUMIN,7, 13) AS 주민번호, A.M_CITY AS 지역구, ";
			sql += " B.P_TEL1 || '-' || B.P_TEL2 || '-' || ";
			sql += " (SUBSTR(B.P_TEL3, 4)) || (SUBSTR(B.P_TEL3, 4)) || (SUBSTR(B.P_TEL3,4)) || (SUBSTR(B.P_TEL3, 4)) AS 대표전화 ";
			sql += " FROM TBL_MEMBER_202005 A JOIN TBL_PARTY_202005 B ";
			sql += " ON(A.P_CODE = B.P_CODE)";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				member.setM_no(rs.getString(1));
				member.setM_name(rs.getString(2));
				member.setParty(rs.getString(3));
				member.setP_school(rs.getString(4));
				member.setM_jumin(rs.getString(5));
				member.setM_city(rs.getString(6));
				member.setTel_all(rs.getString(7));
				members.add(member);
			}
			request.setAttribute("members", members);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Info.jsp";
	}
	
	public int vote(HttpServletRequest request, HttpServletResponse response) {
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		String v_age = request.getParameter("v_age");
		int result = 0;
		
		System.out.println("v_confirm:" +v_confirm);
		
		try {
			conn = getConnection();
			
			String sql = "insert into tbl_vote_202005 values (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);
			
			result = ps.executeUpdate();
			System.out.println(result);
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String check(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vote> votes = new ArrayList<Vote>();
		try {
			conn = getConnection();
			String sql = "SELECT V_NAME,";
			sql +=  " SUBSTR(V_JUMIN,1,2)+1900||'년'||SUBSTR(V_JUMIN,3,2)||'월'||SUBSTR(V_JUMIN,5,2)||'일생' 생년월일,";
			sql +=  " '만 '||TRUNC(MONTHS_BETWEEN(TRUNC(SYSDATE), TO_DATE(19||SUBSTR(V_JUMIN,1,6),'YYYYMMDD')) / 12)||'세' 나이,";
			sql +=  " DECODE(SUBSTR(V_JUMIN, 7, 1), '1', '남자', '2', '여자', '3', '남자', '4', '여자', '5', '남자', '6', '여자') 성별,";
			sql +=  " M_NO,SUBSTR(V_TIME, 1, 2)||':'||SUBSTR(V_TIME, 3, 2) 투표시간,";
			sql +=  " DECODE(V_CONFIRM, 'N', '미확인', 'Y', '확인') 유권자확인";
			sql +=  " FROM TBL_VOTE_202005 WHERE V_AREA = '제1투표장'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vote vote = new Vote();
				vote.setV_name(rs.getString(1));
				vote.setV_birth(rs.getString(2));
				vote.setV_age(rs.getString(3));
				vote.setV_sex(rs.getString(4));
				vote.setM_no(rs.getString(5));
				vote.setV_time(rs.getString(6));
				vote.setV_confirm(rs.getString(7));
				votes.add(vote);
			}
			request.setAttribute("votes", votes);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Check.jsp";
	}
	
	public String rank(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Vote> vote = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT A.M_NO, A.M_NAME, COUNT(B.M_NO)";
			sql += " FROM TBL_MEMBER_202005 A JOIN TBL_VOTE_202005 B ON (A.M_NO = B.M_NO) AND B.V_CONFIRM = 'Y'";
			sql += " group by A.M_NO, A.M_NAME ORDER BY COUNT(B.M_NO) DESC";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vote vot = new Vote();
				vot.setM_no(rs.getString(1));
				vot.setV_name(rs.getString(2));
				vot.setV_count(rs.getString(3));
				
				vote.add(vot);
				
			}
			request.setAttribute("vote", vote);
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Rank.jsp";
	}
	
	
}
