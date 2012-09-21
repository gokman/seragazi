<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9" />
	
			<!-- Acordion form js and css -->
			<script type="text/javascript">
			<!--
			function isNumericKey(e) {
			   var k = document.all ? e.keyCode : e.which;
			   return ((k > 47 && k < 58) || k == 8);
			}
			function extractNumeric(str) {
			   return str.replace(/\D/g,"");
			}
			// -->
			</script>
		
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.1.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery-1.2.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.form.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.metadata.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.validate.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.maskedinput-1.0.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.dimensions.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/form/jquery.accordion.js"/>"></script>
		
			
	</head>

<body class="genel">
	
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/header.jsp" />
<div class="orta_div_sag">	

        <fieldset style="width:500px;height:auto;margin-left: auto;margin-right: auto;margin-bottom:20px;border-color: #2581C5;">
				<legend>Kullanıcı Kayıt İşlemi Sonucu</legend> 
				<h3>Üyelik bilgileriniz <b><c:out value="${email}"/></b> adresine gönderilmiştir.</h3>
				<br />
		</fieldset> 

</div>
					<jsp:include page="/WEB-INF/jsp/ana_sayfa/footer.jsp" />
		
</body>
</html>