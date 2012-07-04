<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
 
	<link href="<c:url value="/resources/css/ana_sayfa/main.css"/>" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/resources/css/ana_sayfa/menu.css"/>" type="text/css" />
 
</head>
<body>
<div id="anadiv"> 
<table cellpadding="0" cellspacing="0"><tr><td>
		<div id="ust_menu">
		
				<div>
					<img src="<c:url value="/resources/image/ana_sayfa/ust_resim.jpg"/>"/>
				</div>
				
				<div class="menudiv">
					<ul id="menu-bar">
					 <li class="current"><a href="/">Ana Sayfa</a></li>
					 <li><a href="#">İşletme Yapisi</a></li>
					 <li><a href="#">İşlemler</a>
						  <ul>
							   <li><a href="/sera/cenvyapi/yapiGiris/ana.htm">Değer Girisi</a></li>
							   <li><a href="/sera/cenvyapi/yapiSearch.htm">Değer Arama ve Güncelleme</a></li>
							   <li><a href="#">Hesaplama</a></li>
							   <li><a href="#">Sonuç</a></li>
							   <li><a href="/sera/cenvsabit/sabitGir.htm">Sabitler</a></li>
						  </ul>
					 </li>
					 <li><a href="#">Yetkiler</a>
						  <ul>
							   <li><a href="#">Yetki Tanımlama</a></li>
							   <li><a href="#">Yetkileri Görüntüle</a></li>
						  </ul>
					 </li>
					</ul>
				</div>
				
		</div>
		</td></tr>
		<tr><td>
		<div class="orta_div"> 
			 <div class="orta_div_sol">
			 <!--kullanýcý giriþ -->
			 <%@include file="/WEB-INF/jsp/ana_sayfa/loginuser.jsp" %>
			 </div>
	    
</body>
</html>		 