<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 LIST::YONNY's JOURNEY</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<link href="/mm/css/default.css" rel="stylesheet" type="text/css">

<style>
	.thumnail {
		height: 30px;
		margin: 5px;
	}
	
	table {
		width: 80%;
		margin: 0 auto;
		border: 0;
		border-collapse: collapse;
	}
	
	table td{
		padding: 3px;
		border: 1px solid #ccc;
		text-align: center;
	}
	
	table tr:first-child > td{
		background-color: #ccc;
		color: white;
		font-weight: bold;
		font-size: 1.15em;
	}
	
	td>span {
		margin: 0 auto;
		display: inline-block;
	}
</style>
</head>
<body>
<!-- header 시작 -->
<%@ include file="../frame/header.jsp" %>
<!-- header 끝-->
	
<!-- nav 시작 -->
<%@ include file="../frame/nav.jsp" %>
<!-- nav 끝-->

<!-- contents 시작 -->
	<div id="contents">
	<h3>회원 LIST 페이지입니다.^*^</h3>
	<hr>
	<form>
		<table>
			<tr>
				<td>순번</td>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>이미지</td>
				<td>등록일</td>
				<td>관리</td>
			</tr>
			<!-- 리스트 반복 시작 -->
			
			<c:forEach items="${memList}" var="member">
				<div>
					<tr>
						<td>${member.idx}</td>
						<td>${member.id}</td>
						<td>${member.pw}</td>
						<td>${member.name}</td>
						<td>${member.photo}</td>
						<td>${member.regDate}</td>	
						<td><span><a href="#">수정</a> | <a href="#">삭제</a></span></td>
					</tr>
				</div>
			</c:forEach>
			<!-- 리스트 반복 끝 -->
			
		</table>
		
	</form>
	</div>
<!-- contents 끝-->

<!-- footer 시작 -->
<%@ include file="../frame/footer.jsp" %>
<!-- footer 끝-->

</body>
</html>