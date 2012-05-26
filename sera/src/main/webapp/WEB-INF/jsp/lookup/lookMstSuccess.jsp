<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9" />
<title>Ana Sayfa</title>
<link href="/resources/css/ana_sayfa/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="/resources/css/ana_sayfa/menu_style.css" type="text/css" />
</head>

<body bgcolor="#AF4555">
<div align="center"><img src="/resources/image/ana_sayfa/ust_resim.jpg" /></div>
<table align="center" cellspacing="0" cellpadding="0" width="1000px" bgcolor="#FFFFFF">
<tbody>
<!-- üst kisim -->
<tr><td align="center">
<div class="ust_div">
<div class="ust_div_ust"></div>
<div class="ust_div_menu">
<div class="menu">
		<ul>
			<li><a href="#" >Ana Sayfa</a></li>
			<li><a href="#" id="current">Ürünler</a>
				<ul>
					<li><a href="#">Ürün Gir</a></li>
					<li><a href="#">Listele</a></li>
			   </ul>
		  </li>
			<li><a href="#">Müşteriler</a>
                <ul>
                <li><a href="#">Listele</a></li>
                <li><a href="#">Müşteri Gir</a></li>
                </ul>
          </li>
			<li><a href="#">İletişim</a></li>
			<li><a href="#">Hakkımızda</a></li>
		</ul>
	</div>
</div>
</div>
</td></tr>

<!-- orta kisim -->
<tr><td>

	<table class="orta_div">
	<tr><td>
	<div class="orta_div_sol">
	<!-- kullanıcı giriş bölümü olacak-->
	<div class="kullanici_giris">
	<form method="post">
	<br />
	Kullanıcı Adı <br />
	<input type="text" width="10px" name="username"/><br />
	Şifre <br />
	<input type="text" width="10px" name="password"/><br />
	<input class="dugme_giris" type="submit" width="60px" value=" "/>
	
	</form>
	</div>
	<div class="sol_menu">
	<img src="reklam.jpg" />
	</div>
	</div>
	</td><td valign="top">
	<div class="orta_div_sag">
	ana bolum
	</div> 
	</td>
	</tr>
	</table>

</td></tr>

<!-- alt kisim -->
<tr><td align="center">
<div class="alt_div">alt bölüm</div>
</td></tr>

</tbody>
</table>
<div align="center"><img src="/resources/image/ana_sayfa/alt_resim.jpg" /></div>
</body>
</html>
