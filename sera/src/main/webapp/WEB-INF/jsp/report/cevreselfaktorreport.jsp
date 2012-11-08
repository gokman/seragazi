<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Çevresel Faktör Raporu</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<!--<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/main.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" /> -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>


</head>
<body>
   
<c:choose> 
	<c:when test="${isAuthenticated=='true'}">
	<div>
	
<center>	
<%@include file="/WEB-INF/jsp/report/cevreselfaktorreport.htm" %>
</center>
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu içeriğe erişmek için giriş yapmalısınız.
		</div>
	</c:otherwise>
	</c:choose>	
	
</body>
</html>