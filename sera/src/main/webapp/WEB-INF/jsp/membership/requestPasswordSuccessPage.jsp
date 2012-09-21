<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
	
<link rel="stylesheet" href="<c:url value="/resources/css/form/form2.css"/>" type="text/css" />
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
	
			
	</head>

<body class="genel">

<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
<div class="orta_div_sag">	
	


				<fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
				<legend> Şifre Hatırlatma İşlemi Sonuc</legend> 
				<div class="formspacer" > </div>

				<h3>Şifreniz <b><c:out value="${email}"/></b> adresine gönderilmiştir.</h3>
				<br/>
		        </fieldset> 
</div>
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/footer.jsp" />

	

</body>
</html>