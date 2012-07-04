<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/kullanici_giris.css"/>" type="text/css" />
</head>
<body>
<div class="kullanici_giris">
									<c:choose>
											<c:when test="${isAuthenticated=='true' }">
											<table class="merhabakutu">
											<tr><td>
												<a id="merhaba">Merhaba</a>
											</td></tr>
											<tr><td>
												<a id="username"><c:out value="${username}"></c:out></a>
										    </td></tr>
										    <tr><td>
												<a id="logout" href="<c:url value="/j_spring_security_logout"/>" value="Logout">Çıkış</a>
											</td></tr>
											</table>
											</c:when>
											<c:otherwise>
											
												<form class="formkullanicigiris" method="post" action="j_spring_security_check"><br />
												<a>Kullanıcı adı</a> <br />
												<input id="textfield" type="text" name="j_username" /><br />
												<a>Şifre</a> <br />
												<input id="textfield" name="j_password" type="password"/><br />
												<input id="button" type="submit"  value="Giriş" />
												<br />
												<a href="<c:url value="/login/requestPassword.htm"/>">Şifremi Unuttum</a><br/> 
												<a href="<c:url value="/login/membershipForm.htm"/>">Üye Ol</a>
												</form>
											
											</c:otherwise>
									</c:choose>		 
								</div>
</body>
</html>