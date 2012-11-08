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
<div class="kullanici_giris" style="background-image:url(<c:url value="/resources/image/ana_sayfa/ust_resim.jpg"/>);">
									<c:choose>
											<c:when test="${isAuthenticated=='true' }">
											<table align="right"  style="padding-right:10px;padding-top: 120px;" class="merhabakutu" >
											<tr><td>
												<a id="username"><c:out value="${username}"></c:out></a>
										    </td><td>
												<a id="logout" href="<c:url value="/j_spring_security_logout"/>" value="Logout">Çıkış</a>
											</td></tr>
											</table>
											</c:when>
											<c:otherwise>
											<form  class="formkullanicigiris" method="post" action="j_spring_security_check">
											<table align="right" style="padding-right:10px;">
												<tr><td>
												<a>Kullanıcı adı</a>
												</td><td> 
												<input id="textfield" type="text" name="j_username" />
												</td></tr>
												<tr><td>
												<a>Şifre</a> 
												</td><td>
												<input id="textfield" name="j_password" type="password"/>
												</td></tr>
												<tr><td></td><td>
												<input id="button" type="submit"  value="Giriş" />
												</td></tr>
												<tr><td></td><td>
												<a href="<c:url value="/login/requestPassword.htm"/>">Şifremi Unuttum</a>
												</td></tr>
												<tr><td></td><td>
												<a href="<c:url value="/login/membershipForm.htm"/>">Üye Ol</a>
												</td></tr>
												
											</table>
											</form>
											</c:otherwise>
									</c:choose>		 
								</div>
</body>
</html>