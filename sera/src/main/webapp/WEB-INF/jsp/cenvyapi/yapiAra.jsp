<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Yapı Arama Ekranı</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.6.1.min.js"/>"></script>
<link href="<c:url value="/resources/css/form/cenv_deger_giris.css"/>" rel="stylesheet" type="text/css"/>
<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/form.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$("#tableliste").click(
		function git(){
			window.load="/sera/cenvyapi/degerGiris/${cenvdeger.id}.htm";
			
		}
	)
	
});
</script>   
</head>
<body class="genel">
<%@include file="/WEB-INF/jsp/ana_sayfa/header.jsp" %>
<c:choose>
<c:when test="${isAuthenticated=='true'}">
<div class="orta_div_sag">
	<form:form cssClass="formstil" cssStyle="padding-left:30px" action="/sera/cenvyapi/yapiSearch.htm" method="POST" commandName="cenvDegerSearch" modelAttribute="cenvDegerSearch" enctype="multipart/form-data">
<table width="400px" height="auto">
<tr>
<td></td>
<td>
<form:input id="searchfield"  path="baslik"  maxlength="100" /> 
<input id="searchbutton"  type="submit"  value="Ara"  />
</td></tr>
</table>
</form:form>

<c:forEach var="cenvdeger" items="${cenvdegerler}">

<table class="liste" id="tableliste">
                    <tr><td>
                    </td><td></td>
                    </tr>
                    <tr><td class="sol">Başlık:</td>
                    <td class="sag">
                    <a href="<c:out value="/sera/cenvyapi/yapiGiris/${cenvdeger.id}.htm"/>">${cenvdeger.baslik}</a>               
                    </td></tr>
                    <tr><td class="sol">Tip:</td>
                    <td class="sag">
                    <a href="<c:out value="/sera/cenvyapi/yapiGiris/${cenvdeger.id}.htm"/>">${cenvdeger.tip1}</a>                  
                    </td></tr>
                    <tr><td class="sol">Değer Tipi:</td>
                    <td class="sag">
                    <a href="<c:out value="/sera/cenvyapi/yapiGiris/${cenvdeger.id}.htm"/>">${cenvdeger.tip2}</a>                
                    </td></tr>
                    <tr><td class="sol">Birim:</td>
                    <td class="sag">
                    <a href="<c:out value="/sera/cenvyapi/yapiGiris/${cenvdeger.id}.htm"/>">${cenvdeger.birim}</a>               
                    </td></tr>
                    </table>
                </c:forEach>

</div>
</c:when>
	<c:otherwise>
		<div class="orta_div_sag">
			Bu içeriğe erişmek için giriş yapmalısınız.
		</div>
	</c:otherwise>
	</c:choose>
</div>
<%@include file="/WEB-INF/jsp/ana_sayfa/footer.jsp" %>
</body>
</html>