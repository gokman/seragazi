<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Değer Giriş Raporu</title>
<link rel="stylesheet" href="<c:url value="/resources/css/form/general.css"/>"></link>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/yapi/agac.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>

</head>
<body class="genel">
   
	<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
	 <c:choose> 
	<c:when test="${isAuthenticated=='true'}">
	<div class="orta_div_sag">
	<%@include file="/WEB-INF/jsp/report/cenvgirisreport.htm" %>
    
	</div>
	</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu icerige erismek icin giris yapmalisiniz.
		</div>
	</c:otherwise>
	</c:choose>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>