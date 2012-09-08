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
					 <li><a href="/sera">Ana Sayfa</a></li>
					 <li><a href="#">Envanter Yapısı</a>
					 <ul>
					     <li><a href="/sera/cenvyapi/yapiGiris/ana.htm">Yapı Oluşturma</a></li>
					     <li><a href="/sera/cenvyapi/yapiSearch.htm">Yapı Arama ve Güncelleme</a></li>
					     <li><a href="/sera/cenvyapi/yapiSil.htm">Yapı Arama ve Silme</a></li>
					 </ul>
					 </li>
					 <li><a href="#">İşlemler</a>
						  <ul> 
							   <li><a href="/sera/cenvgiris/girisgetir.htm">Değer Girişi</a></li>
							   <li><a href="/sera/faktor/faktorgiris.htm">Çevresel Faktör Girişi</a></li>
							    <li><a href="/sera/hesaplama/hesaplamaGiris.htm">Dal Hesaplama</a></li>
							   <li><a href="/sera/donemsonuc/donemsonucgir.htm">Dönem Hesaplama</a></li>
							   <li><a href="#">Sonuç</a></li>
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