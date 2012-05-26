if (newimgserver==undefined)
	var	newimgserver='';


if (menuClkTkpBaslangic==undefined)
	var menuClkTkpBaslangic=0;

var acikMenu=0
var timerID =0


	var IE = document.all?true:false
	if (!IE) document.captureEvents(Event.MOUSEMOVE)
	document.onmousemove = getMouseXY;
	var tempX = 0
	var tempY = 0
	
	function getMouseXY(e) {
		if (IE) {
	    	tempX = event.clientX + document.body.scrollLeft
    		tempY = event.clientY + document.body.scrollTop
  		} 
		else {
		    tempX = e.pageX
		    tempY = e.pageY
		}  
	  if (tempX < 0){tempX = 0}
	  if (tempY < 0){tempY = 0}  
	}

function menuGoster(menu,gecerlisayfa)
{
	//menülerden herhangi biri açýksa süre beklemeden yeni menü açýlmalý 
	if (document.getElementById('menudiv2').style.display=='' || document.getElementById('menudiv3').style.display==''  || document.getElementById('menudiv4').style.display==''  || document.getElementById('menudiv5').style.display==''  || document.getElementById('menudiv6').style.display==''  || document.getElementById('menudiv7').style.display==''  || document.getElementById('menudiv8').style.display==''  || document.getElementById('menudiv9').style.display=='')
		showTimer=0
	else
		showTimer=600

	clearTimeout(timerID)
	timerID = setTimeout('menuAc('+menu+','+gecerlisayfa+')',showTimer)
	
}

function menuAc(ac,gecerlisayfa)
{
	vposDiv = document.getElementById('spM'+ac)
	menuT = vposDiv.offsetTop
	
	if (acikMenu == ac) return
	tX=tempX
	tY=tempY
	if (tY > menuT+5 || tY < menuT -35) return
	acikMenu = ac	
	for(j=2;j<=9;j++)

			if (j==ac)
				{
					if (ac==9) {
						document.getElementById('menudiv'+j).style.left=vposDiv.offsetLeft - 163
						document.getElementById('MN'+j).src = newimgserver + '/img/sk1/ust/kmn'+j+'b.jpg';
						document.getElementById('menudiv'+j).style.display='';
					}
					
					else {
						document.getElementById('menudiv'+j).style.left=vposDiv.offsetLeft + 3;

					document.getElementById('menudiv'+j).style.display='';
					document.getElementById('MN'+j).setAttribute("background", newimgserver + '/img/sk1/ust/kmn'+j+'b.jpg');
					document.getElementById('amn'+j).className='topmenug';
					}
				//document.getElementById('amn'+j).setAttribute("class", "topmenug");
				}
			else
				{
				document.getElementById('menudiv'+j).style.display='none'
					if (gecerlisayfa==j) {
						
						
						document.getElementById('MN'+j).setAttribute("background", newimgserver+'/img/sk1/ust/kmn'+j+'m.jpg');
						document.getElementById('amn'+j).className='topmenum';
					}
					else {
						
						
						document.getElementById('MN'+j).setAttribute("background", newimgserver+'/img/sk1/ust/kmn'+j+'g.jpg');
						//document.getElementById('amn'+j).className='topmenug';
					}
				}
			setTimeout('menuKapa('+ac+','+gecerlisayfa+')',1) 

} 

function menuKapa(ac,gecerlisayfa)
{
	
	menuDivUst = document.getElementById('menudiv'+ac+'ust');
	vposDiv = document.getElementById('spM'+ac);
	
	menuH = menuDivUst.offsetHeight+3;
	menuW = menuDivUst.offsetWidth+10;
	menuL = vposDiv.offsetLeft;
	menuT = vposDiv.offsetTop;
	//alert(menuT)
	
	tX=tempX;
	tY=tempY;
	
	if (ac==9)
		//vkontrol = (tX < (menuL+80) && tX > menuL - menuW + 100)
		vkontrol = (tX > menuL-175 && tX < menuL-175 + menuW)
	else
		vkontrol = (tX > menuL && tX < menuL + menuW)
	
	if (vkontrol && tY > menuT -31 && tY  < menuT + menuH) 
		{
		setTimeout('menuKapa('+ac+','+gecerlisayfa+')',1000) 
		}
	else
		{
			document.getElementById('menudiv'+ac).style.display='none'
			if (gecerlisayfa==ac) {
				
				if (ac==9)
				{
					document.getElementById('MN'+ac).src=newimgserver+'/img/sk1/ust/kmn'+ac+'m.jpg';
				}
				else
				{
				document.getElementById('MN'+ac).setAttribute("background", newimgserver+'/img/sk1/ust/kmn'+ac+'m.jpg');
				document.getElementById('amn'+ac).className='topmenum';
				}
			}
			else {
				if (ac==9)
				{
					document.getElementById('MN'+ac).src=newimgserver+'/img/sk1/ust/kmn'+ac+'g.jpg';
				}
				else
				{
				document.getElementById('MN'+ac).setAttribute("background", newimgserver+'/img/sk1/ust/kmn'+ac+'g.jpg');
				document.getElementById('amn'+ac).className='topmenug';
				}
			}
			acikMenu=0
		}
	
}
function clickTakipLink(eklenecek)
{
	if (menuClkTkpBaslangic>0)
		return '&clktkp='+ (menuClkTkpBaslangic+eklenecek);
	else
		return '';
	
}

if (window.XMLHttpRequest) {
isIE6=false
} 
else 
{
isIE6=true
}

//ÝÞ ARAMA MENÜSÜ
menu2html=' <div id="menudiv2shadow" style="position:absolute;margin:3px;z-index: 1000; height: 370; width: 380; filter: alpha(opacity=40); opacity: 0.4;">'
menu2html+= ' 	<table width="380" cellpadding="0" cellspacing="0" border="0">'
menu2html+=' 		<tr>'
menu2html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu2html+=' 			<td bgcolor="black" width="358"></td>'
menu2html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu2html+=' 		</tr>'
menu2html+=' 		<tr>'
menu2html+=' 			<td colspan="3" bgcolor="black" height="248"></td>'
menu2html+=' 		</tr>'
menu2html+=' 		<tr>'
menu2html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu2html+=' 			<td bgcolor="black"></td>'
menu2html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu2html+=' 		</tr>'
menu2html+=' 	</table>'
menu2html+=' </div>'
menu2html+=' <div id="menudiv2ust" style="position:relative; z-index: 1002;">'
menu2html+=' <table width="380" cellpadding="0" cellspacing="0" border="0">'
menu2html+=' 	<tr>'
menu2html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu2html+=' 		<td width="66" bgcolor="#ffffff"></td>'
menu2html+=' 		<td width="292" background="/img/sk1/ust/divust.gif"></td>'
menu2html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu2html+=' 	</tr>'
menu2html+=' 	<tr>'
menu2html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu2html+=' 		<td colspan="2" bgcolor="#ffffff" height="248" valign="top">'
menu2html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0">'
menu2html+=' 				<tr>'
menu2html+=' 					<td><input type="Text" name="mnKeyword" id="mnKeyword" value=" Anahtar Kelime" style="width:250px" class=nesne onclick="if(this.value==\' Anahtar Kelime\') this.value=\'\'"></td>'
menu2html+=' 					<td><a href="javascript:mnKeywordAra();"><img src="/img/sk1/btn/ara.gif"  alt="" border=0></a></td>'
menu2html+=' 				</tr>'
menu2html+=' 				<tr>'
menu2html+=' 					<td colspan="2"><p>'
menu2html+=' 						<a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&sonarama=1&rndm='+Math.random()+clickTakipLink(3)+'" class=menulink>&raquo; Detaylý Ýþ Arama</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
menu2html+=' 						<a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&Tarih=1g'+clickTakipLink(4)+'" class=menulink>&raquo; Son 1 Gün</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
menu2html+=' 						<a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&Tarih=3g'+clickTakipLink(5)+'" class=menulink>&raquo; Son 3 Gün</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
menu2html+=' 						<a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&Tarih=7g'+clickTakipLink(6)+'" class=menulink>&raquo; Son 7 Gün</a>'
menu2html+=' 						</p>'
menu2html+=' 					</td>'
menu2html+=' 				</tr>'
menu2html+=' 				<tr>'
menu2html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu2html+=' 				</tr>'
menu2html+=' 			</table>'
menu2html+=' 			<table width="100%" cellpadding="0" cellspacing="0" border="0">'
menu2html+=' 				<tr>'
menu2html+=' 					<td width="55"% valign="top">'
menu2html+=' 						<table width="100%" cellpadding="0" cellspacing="4" border="0" height="160">'
menu2html+=' 							<tr>'
menu2html+=' 								<td width="22"><img src="/img/sk1/job/gozk.jpg" width="22" height="22" alt=""></td>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&sonarama=1&rndm='+Math.random()+clickTakipLink(7)+'" class="menulink">Detaylý Ýþ Arama</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 						<tr>'
menu2html+=' 								<td><img src="/img/sk1/job/yildizk.jpg" width="22" height="22" alt=""></td>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=2'+clickTakipLink(8)+'" class="menulink">Takibimdeki Ýlanlar</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 							<tr>'
menu2html+=' 								<td><img src="/img/sk1/job/moryildizk.jpg" width="22" height="22" alt=""></td>'
menu2html+=' 								<td nowrap><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&IlgilendigimFirma=1&LoginGerekli=1'+clickTakipLink(9)+'" class="menulink">Takibimdeki Firmalarýn Ýlanlarý</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 							<tr>'
menu2html+=' 								<td><img src="/img/sk1/job/buyuteck.jpg" width="22" height="22" alt=""></td>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=3'+clickTakipLink(10)+'" class="menulink">Ýncelediðim Ýlanlar</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 							<tr>'
menu2html+=' 								<td><img src="/img/sk1/job/checkk.jpg" width="22" height="22" alt=""></td>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=4'+clickTakipLink(11)+'" class="menulink">Özgeçmiþime Uygun Ýlanlar</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 						</table>'
menu2html+=' 					</td>'
menu2html+=' 					<td width="45%" valign="top">'
menu2html+=' 						<table width="100%" cellpadding="0" cellspacing="4" border="0">'
menu2html+=' 							<tr>'
menu2html+=' 								<td>'
menu2html+=' 									<a href="http://www.kariyerexecutive.net" class="menulink" target="_blank">&raquo; Executive Ýlanlar</a>'
menu2html+=' 									<font class="menugray"><br>KariyerExecutive üyelerinin üst düzey yönetici ilanlarý</font></td>'
menu2html+=' 							</tr>'
/*menu2html+=' 							<tr>'
menu2html+=' 								<td height="20"></td>'
menu2html+=' 							</tr>'*/

menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="http://staj.kariyer.net" class="menulink" target="_blank">&raquo; Staj Ýlanlarý</a><br>'
menu2html+=' 								<font class="menugray">Tüm staj ilanlarý ve öðrencilere özel yarýþmalar, tavsiyeler...</font></td>'
menu2html+=' 							</tr>'

menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="http://mavi.kariyer.net" class="menulink" target="_blank">&raquo; Mavi Yaka Ýlanlarý</a>'
menu2html+=' 								</td>'
menu2html+=' 							</tr>'

menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&iso500=1'+clickTakipLink(12)+'" class="menulink">&raquo; ISO 500 Firmalarýnýn Ýlanlarý</a></td>'
menu2html+=' 							</tr>'

menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&Engelli=1'+clickTakipLink(13)+'" class="menulink">&raquo; Engelli Ýlanlarý</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&GirisimciIlanlariGoster=1'+clickTakipLink(14)+'" class="menulink">&raquo; Giriþimci Ýlanlarý</a></td>'
menu2html+=' 							</tr>'

/*menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&EskiHukumlu=1" class="menulink">&raquo; Eski Hükümlü Ýlanlarý</a></td>'
menu2html+=' 							</tr>'
menu2html+=' 							<tr>'
menu2html+=' 								<td><a href="/jobsearch/detayliarama.kariyer'+chk_link_add+'&Ara=1&SehitYakini=1" class="menulink">&raquo; Terör Maðduru Ýlanlarý</a></td>'
menu2html+=' 							</tr>'*/
menu2html+=' 						</table>'
menu2html+=' 					</td>'
menu2html+=' 				</tr>'
menu2html+=' 			</table>'
menu2html+=' 		</td>'
menu2html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu2html+=' 	</tr>'
menu2html+=' 	<tr>'
menu2html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu2html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="358" height="11" alt=""></td>'
menu2html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu2html+=' 	</tr>'
menu2html+=' </table>'
menu2html+=' </div>'

if (isIE6)
{
menu2html+=' <iframe src="about:blank" style="width: 380px; height: 260px; z-Index: 500; position:absolute; top: 5px; left: 0px;" frameborder="0"></iframe>'
}

document.getElementById('menudiv2').innerHTML=menu2html

// KARÝYERÝM MENÜSÜ
menu3html=' <div id="menudiv3shadow" style="position:absolute;margin:3px;z-index: 1000; height: 272; width: 396; filter: alpha(opacity=40); opacity: 0.4;">'
menu3html+=' 	<table width="396" cellpadding="0" cellspacing="0" border="0">'
menu3html+=' 		<tr>'
menu3html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu3html+=' 			<td bgcolor="black" width="374"></td>'
menu3html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu3html+=' 		</tr>'
menu3html+=' 		<tr>'
menu3html+=' 			<td colspan="3" bgcolor="black" height="250"></td>'
menu3html+=' 		</tr>'
menu3html+=' 		<tr>'
menu3html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu3html+=' 			<td bgcolor="black"></td>'
menu3html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu3html+=' 		</tr>'
menu3html+=' 	</table>'
menu3html+=' </div>'
menu3html+=' <div id="menudiv3ust" style="position:relative; z-index: 1002;">'
menu3html+=' <table width="396" cellpadding="0" cellspacing="0" border="0">'
menu3html+=' 	<tr>'
menu3html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu3html+=' 		<td width="78" bgcolor="#ffffff"></td>'
menu3html+=' 		<td width="296" background="/img/sk1/ust/divust.gif"></td>'
menu3html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu3html+=' 	</tr>'
menu3html+=' 	<tr>'
menu3html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu3html+=' 		<td colspan="2" bgcolor="#ffffff" height="250" valign="top">'
//menu3html+=' 			<table  cellpadding="4" cellspacing="0" border="0" width="370" height="120" style="background-repeat: no-repeat;background-image: url(/img/sk1/ust/kariyerimbg.jpg);">'
menu3html+=' 			<table  cellpadding="0" cellspacing="0" border="0" width="370">'
menu3html+=' 				<tr>'
menu3html+=' 					<td>'
//if (chk_userrefno!='')
//	menu3html+=' 						<img src="/incfiles/cvresimgostermenu.kariyer'+chk_link_add+'" width="75" border=0  align="right" vspace="4" hspace="4">'
if (chk_userrefno=='') {
	menu3html+=' 					<p>&nbsp;<a href="/kariyerim/newuser.kariyer'+chk_link_add+clickTakipLink(16)+'" class="menulink"><b>&raquo; Hemen Üye Ol</b></a><br>'
	menu3html+=' 					&nbsp;<font class="menugray">Özgeçmiþinizi oluþturun, ilanlara baþvurun.</font></p>'
}							
menu3html+=' 						<p>&nbsp;<a href="/kariyerim/index.kariyer'+chk_link_add+clickTakipLink(17)+'" class="menulink"><b>&raquo; Kariyerim</b></a><br>'
menu3html+=' 						&nbsp;<font class="menugray">Özgeçmiþlerinizi listeleyin, görüntüleyin.</font></p>'
menu3html+=' 						<p>&nbsp;<a href="/kariyerim/loginupdate.kariyer'+chk_link_add+clickTakipLink(18)+'" class="menulink"><b>&raquo; Üyelik Bilgilerim</b></a><br>'
menu3html+=' 						&nbsp;<font class="menugray">Þifre, e-posta, abonelik ile ilgili iþlemlerinizi yapýn.</font></p>'
if (chk_userrefno!='') {
	menu3html+=' 					<p>&nbsp;<a href="/kariyerim/logout.kariyer'+chk_link_add+'" class="menulink"><b>&raquo; Güvenli Çýkýþ</b></a><br>'
	menu3html+=' 					&nbsp;<font class="menugray">Kariyer.net\'ten çýkmadan önce Güvenli Çýkýþ\'a týklayýn</font></p>'
}
menu3html+=' 					</td>'
menu3html+=' 				</tr>'
menu3html+=' 				<tr>'
menu3html+=' 					<td><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu3html+=' 				</tr>'

menu3html+=' 			</table>'
menu3html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0" height="120">'
menu3html+=' 				<tr>'
menu3html+=' 					<td width="50%">&nbsp;<a href="/kariyerim/myapplications.kariyer'+chk_link_add+clickTakipLink(19)+'" class="menulink">&raquo; Baþvurularým</a></td>'
menu3html+=' 					<td width="50%">&nbsp;<a href="/isHabercim/index.kariyer'+chk_link_add+clickTakipLink(24)+'" class="menulink">&raquo; Ýþ Habercim</a></td>'
menu3html+=' 				</tr>'
menu3html+=' 				<tr>'
menu3html+=' 					<td>&nbsp;<a href="/kariyerim/mymessages.kariyer'+chk_link_add+clickTakipLink(20)+'" class="menulink">&raquo; Mesajlarým</a></td>'
menu3html+=' 					<td>&nbsp;<a href="/ktest/index.kariyer'+chk_link_add+clickTakipLink(26)+'" class="menulink">&raquo; K-Testlerim</a></td>'
menu3html+=' 				</tr>'
menu3html+=' 				<tr>'
menu3html+=' 					<td>&nbsp;<a href="/kariyerim/onaylist.kariyer'+chk_link_add+clickTakipLink(21)+'" class="menulink">&raquo; SMS Mesajlarým</a></td>'
menu3html+=' 					<td>&nbsp;<a href="/pano/index.kariyer'+chk_link_add+clickTakipLink(27)+'" class="menulink">&raquo; Mesaj Panosu</a></td>'
menu3html+=' 				</tr>'
menu3html+=' 				<tr>'
menu3html+=' 					<td>&nbsp;<a href="/kariyerim/mulakatlarim.kariyer'+chk_link_add+clickTakipLink(22)+'" class="menulink">&raquo; Mülakatlarým</a></td>'
menu3html+=' 					<td>&nbsp;</td>'
menu3html+=' 				</tr>'
menu3html+=' 				<tr>'
menu3html+=' 					<td colspan="2">&nbsp;<a href="/kariyerim/araclarim.kariyer'+chk_link_add+clickTakipLink(23)+'" class="menulink">&raquo; Araçlarým</a>'
menu3html+=' 					&nbsp;<font class="menugray">(Fotoðraf iþlemleri, dosya ekleme, ön yazýlarým, görüntülü cv)</font></td>'
menu3html+=' 				</tr>'
menu3html+=' 			</table>'
menu3html+=' 		</td>'
menu3html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu3html+=' 	</tr>'
menu3html+=' 	<tr>'
menu3html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu3html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="374" height="11" alt=""></td>'
menu3html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu3html+=' 	</tr>'
menu3html+=' </table>'
menu3html+=' </div>'

if (isIE6)
{
menu3html+=' <iframe src="about:blank" style="width: 396px; height: 262px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}
document.getElementById('menudiv3').innerHTML=menu3html

// Eðitimlerim menüsü
menu4html='<div id="menudiv4shadow" style="position:absolute;margin:3px;z-index: 1000; height: 220px; width: 350; filter: alpha(opacity=40); opacity: 0.4;">'
menu4html+=' 	<table width="350" cellpadding="0" cellspacing="0" border="0">'
menu4html+=' 		<tr>'
menu4html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu4html+=' 			<td bgcolor="black" width="328"></td>'
menu4html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu4html+=' 		</tr>'
menu4html+=' 		<tr>'
menu4html+=' 			<td colspan="3" bgcolor="black" height="200"></td>'
menu4html+=' 		</tr>'
menu4html+=' 		<tr>'
menu4html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu4html+=' 			<td bgcolor="black"></td>'
menu4html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu4html+=' 		</tr>'
menu4html+=' 	</table>'
menu4html+=' </div>'
menu4html+=' <div id="menudiv4ust" style="position:relative; z-index: 1002;">'
menu4html+=' <table width="350" cellpadding="0" cellspacing="0" border="0">'
menu4html+=' 	<tr>'
menu4html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu4html+=' 		<td width="100" bgcolor="#ffffff"></td>'
menu4html+=' 		<td width="228" background="/img/sk1/ust/divust.gif"></td>'
menu4html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu4html+=' 	</tr>'
menu4html+=' 	<tr>'
menu4html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu4html+=' 		<td colspan="2" bgcolor="#ffffff" height="200" valign="top">'
menu4html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0" height="40">'
menu4html+=' 				<tr>'
menu4html+=' 					<td valign="top" colspan="2">'
menu4html+=' 						<p><a href="/egitimlerim/giris.kariyer'+chk_link_add+'&tab=1'+clickTakipLink(29)+'" class=menulink>&raquo; Microsoft Eðitimleri</a></p>'
menu4html+=' 						<p><a href="/egitimlerim/giris.kariyer'+chk_link_add+'&tab=2'+clickTakipLink(30)+'" class=menulink>&raquo; Profesyonel Geliþim Eðitimleri</a></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimlerim.kariyer'+chk_link_add+'&tab=3'+clickTakipLink(31)+'" class=menulink>&raquo; Aldýðým Eðitimler</a></p>'
menu4html+=' 					</td>'
menu4html+=' 				</tr>'
menu4html+=' 				<tr>'
menu4html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu4html+=' 				</tr>'
menu4html+=' 				<tr>'
menu4html+=' 					<td valign="top" width="55%" >'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11168'+clickTakipLink(32)+'" class=menulink>&raquo; Kariyer Ýngilizce Okulu (Yýllýk)</a>&nbsp; <img src="/newimg/ikonlar/yeni1.gif"></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11166'+clickTakipLink(32)+'" class=menulink>&raquo; Kariyer Ýngilizce Okulu (Aylýk)</a>&nbsp; <img src="/newimg/ikonlar/yeni1.gif"></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11172'+clickTakipLink(32)+'" class=menulink>&raquo; Ýnsan Kaynaklarý Uzmanlýðý E-Eðitimi</a></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11124'+clickTakipLink(32)+'" class=menulink>&raquo; MS Excel 2007 Paketi</a></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11135'+clickTakipLink(32)+'" class=menulink>&raquo; MS Access 2007 Paketi</a></p>'
menu4html+=' 						<p><a href="/egitimlerim/egitimdetay.kariyer'+chk_link_add+'&EgitimPaketNo=11164'+clickTakipLink(32)+'" class=menulink>&raquo; Satýnalma Uzmanlýk E-eðitimi</a></p>'
menu4html+=' 					</td>'
menu4html+=' 					<td valign="top" width="35%" valign="top" >'
menu4html+=' 						<p><a href="/egitimlerim/index.kariyer'+chk_link_add+clickTakipLink(32)+'" class=menulink><img src="/newimg/Egitimlerim/avantaj.jpg" width="99" height=76 border=0 ></a></p>'
menu4html+=' 					</td>'
menu4html+=' 				</tr>'
menu4html+=' 			</table>'
menu4html+=' 		</td>'
menu4html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu4html+=' 	</tr>'
menu4html+=' 	<tr>'
menu4html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu4html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="328" height="11" alt=""></td>'
menu4html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu4html+=' 	</tr>'
menu4html+=' </table>'
menu4html+=' </div>'


if (isIE6)
{
menu4html+=' <iframe src="about:blank" style="width: 350px; height: 200px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}

document.getElementById('menudiv4').innerHTML=menu4html

// Bölgesel Sayfalar MENÜSÜ
menu5html='<div id="menudiv5shadow" style="position:absolute;margin:3px;z-index: 1000; height: 210px; width: 340; filter: alpha(opacity=40); opacity: 0.4;">'
menu5html+=' 	<table width="340" cellpadding="0" cellspacing="0" border="0">'
menu5html+=' 		<tr>'
menu5html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu5html+=' 			<td bgcolor="black" width="318"></td>'
menu5html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu5html+=' 		</tr>'
menu5html+=' 		<tr>'
menu5html+=' 			<td colspan="3" bgcolor="black" height="188"></td>'
menu5html+=' 		</tr>'
menu5html+=' 		<tr>'
menu5html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu5html+=' 			<td bgcolor="black"></td>'
menu5html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu5html+=' 		</tr>'
menu5html+=' 	</table>'
menu5html+=' </div>'
menu5html+=' <div id="menudiv5ust" style="position:relative; z-index: 1002;">'
menu5html+=' <table width="340" cellpadding="0" cellspacing="0" border="0">'
menu5html+=' 	<tr>'
menu5html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu5html+=' 		<td width="72" bgcolor="#ffffff"></td>'
menu5html+=' 		<td width="246" background="/img/sk1/ust/divust.gif"></td>'
menu5html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu5html+=' 	</tr>'
menu5html+=' 	<tr>'
menu5html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu5html+=' 		<td colspan="2" bgcolor="#ffffff" height="188" valign="top">'
menu5html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0" height="40">'
menu5html+=' 				<tr>'
menu5html+=' 					<td width="50%" valign="top">'
menu5html+=' 						<p class="menugray"><a href="http://akdeniz.kariyer.net" target="_blank" class=menulink>&raquo; Akdeniz</a><br>akdeniz.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://ankara.kariyer.net" target="_blank" class=menulink>&raquo; Ankara</a><br>ankara.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://bursa.kariyer.net" target="_blank" class=menulink>&raquo; Bursa</a><br>bursa.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://cukurova.kariyer.net" target="_blank" class=menulink>&raquo; Çukurova</a><br>cukurova.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://ege.kariyer.net" target="_blank" class=menulink>&raquo; Ege</a><br>ege.kariyer.net</p>'
menu5html+=' 					</td>'
menu5html+=' 					<td width="50%" valign="top">'
menu5html+=' 						<p class="menugray"><a href="http://guneydogu.kariyer.net" target="_blank" class=menulink>&raquo; Güneydoðu</a><br>guneydogu.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://karadeniz.kariyer.net" target="_blank" class=menulink>&raquo; Karadeniz</a><br>karadeniz.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://kayseri.kariyer.net" target="_blank" class=menulink>&raquo; Kayseri</a><br>kayseri.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://kocaeli.kariyer.net" target="_blank" class=menulink>&raquo; Kocaeli</a><br>kocaeli.kariyer.net</p>'
menu5html+=' 						<p class="menugray"><a href="http://konya.kariyer.net" target="_blank" class=menulink>&raquo; Konya</a><br>konya.kariyer.net</p>'
menu5html+=' 					</td>'
menu5html+=' 				</tr>'
menu5html+=' 			</table>'
menu5html+=' 		</td>'
menu5html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu5html+=' 	</tr>'
menu5html+=' 	<tr>'
menu5html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu5html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="318" height="11" alt=""></td>'
menu5html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu5html+=' 	</tr>'
menu5html+=' </table>'
menu5html+=' </div>'


if (isIE6)
{
menu5html+=' <iframe src="about:blank" style="width: 340px; height: 210px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}

document.getElementById('menudiv5').innerHTML=menu5html


// Sektörel Sayfalar MENÜSÜ
menu6html='<div id="menudiv6shadow" style="position:absolute;margin:3px;z-index: 1000; height: 322; width: 360; filter: alpha(opacity=40); opacity: 0.4;">'
menu6html+=' 	<table width="360" cellpadding="0" cellspacing="0" border="0">'
menu6html+=' 		<tr>'
menu6html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu6html+=' 			<td bgcolor="black" width="338"></td>'
menu6html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu6html+=' 		</tr>'
menu6html+=' 		<tr>'
menu6html+=' 			<td colspan="3" bgcolor="black" height="300"></td>'
menu6html+=' 		</tr>'
menu6html+=' 		<tr>'
menu6html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu6html+=' 			<td bgcolor="black"></td>'
menu6html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu6html+=' 		</tr>'
menu6html+=' 	</table>'
menu6html+=' </div>'
menu6html+=' <div id="menudiv6ust" style="position:relative; z-index: 1002;">'
menu6html+=' <table width="360" cellpadding="0" cellspacing="0" border="0">'
menu6html+=' 	<tr>'
menu6html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu6html+=' 		<td width="72" bgcolor="#ffffff"></td>'
menu6html+=' 		<td width="266" background="/img/sk1/ust/divust.gif"></td>'
menu6html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu6html+=' 	</tr>'
menu6html+=' 	<tr>'
menu6html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu6html+=' 		<td colspan="2" bgcolor="#ffffff" height="300" valign="top">'
menu6html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0">'
menu6html+=' 				<tr>'
menu6html+=' 					<td width="100%"><p>'
menu6html+=' 						<a href="http://tekstil.kariyer.net" class=menulink target="_blank"><b>&raquo; Tekstil</b></a>'
menu6html+=' 						<font class="menugray"><br>http://tekstil.kariyer.net<br>'
menu6html+=' 						Tekstil dünyasýnýn kalbi burada atýyor!</font>'
menu6html+=' 						</p>'
menu6html+=' 					</td>'
menu6html+=' 					<td width="78"><img src="/img/sk1/ust/tekstilImg.jpg" width="80" height="60" alt=""></td>'
menu6html+=' 				</tr>'
menu6html+=' 				<tr>'
menu6html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu6html+=' 				</tr>'
menu6html+=' 			</table>'
menu6html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0" height="40">'
menu6html+=' 				<tr>'
menu6html+=' 					<td width="50%" valign="top">'
menu6html+=' 						<p class="menugray"><a href="http://bilisim.kariyer.net" target="_blank" class=menulink>&raquo; Biliþim</a><br>bilisim.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://egitim.kariyer.net" target="_blank" class=menulink>&raquo; Eðitim</a><br>egitim.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://finans.kariyer.net" target="_blank" class=menulink>&raquo; Finans</a><br>finans.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://hizlituketim.kariyer.net" target="_blank" class=menulink>&raquo; Hýzlý Tüketim</a><br>hizlituketim.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://insaat.kariyer.net" target="_blank" class=menulink>&raquo; Ýnþaat</a><br>insaat.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://kimya.kariyer.net" target="_blank" class=menulink>&raquo; Kimya</a><br>kimya.kariyer.net</p>'
menu6html+=' 					</td>'
menu6html+=' 					<td width="50%" valign="top">'
menu6html+=' 						<p class="menugray"><a href="http://lojistik.kariyer.net" target="_blank" class=menulink>&raquo; Lojistik</a><br>lojistik.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://otomotiv.kariyer.net" target="_blank" class=menulink>&raquo; Otomotiv</a><br>otomotiv.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://perakende.kariyer.net" target="_blank" class=menulink>&raquo; Perakende</a><br>perakende.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://saglik.kariyer.net" target="_blank" class=menulink>&raquo; Saðlýk</a><br>saglik.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://sigorta.kariyer.net" target="_blank" class=menulink>&raquo; Sigorta</a><br>sigorta.kariyer.net</p>'
menu6html+=' 						<p class="menugray"><a href="http://turizm.kariyer.net" target="_blank" class=menulink>&raquo; Turizm</a><br>turizm.kariyer.net</p>'
menu6html+=' 					</td>'
menu6html+=' 				</tr>'
menu6html+=' 			</table>'
menu6html+=' 		</td>'
menu6html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu6html+=' 	</tr>'
menu6html+=' 	<tr>'
menu6html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu6html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="338" height="11" alt=""></td>'
menu6html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu6html+=' 	</tr>'
menu6html+=' </table>'
menu6html+=' </div>'


if (isIE6)
{
menu6html+=' <iframe src="about:blank" style="width: 360px; height: 322px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}

document.getElementById('menudiv6').innerHTML=menu6html



// Kariyer Rehberi
menu7html='<div id="menudiv7shadow" style="position:absolute;margin:3px;z-index: 1000; height: 250; width: 360; filter: alpha(opacity=40); opacity: 0.4;">'
menu7html+=' 	<table width="300" cellpadding="0" cellspacing="0" border="0">'
menu7html+=' 		<tr>'
menu7html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
menu7html+=' 			<td bgcolor="black" width="278"></td>'
menu7html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
menu7html+=' 		</tr>'
menu7html+=' 		<tr>'
menu7html+=' 			<td colspan="3" bgcolor="black" height="228"></td>'
menu7html+=' 		</tr>'
menu7html+=' 		<tr>'
menu7html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
menu7html+=' 			<td bgcolor="black"></td>'
menu7html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
menu7html+=' 		</tr>'
menu7html+=' 	</table>'
menu7html+=' </div>'
menu7html+=' <div id="menudiv7ust" style="position:relative; z-index: 1002;">'
menu7html+=' <table width="300" cellpadding="0" cellspacing="0" border="0">'
menu7html+=' 	<tr>'
menu7html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu7html+=' 		<td width="124" bgcolor="#ffffff"></td>'
menu7html+=' 		<td width="154" background="/img/sk1/ust/divust.gif"></td>'
menu7html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
menu7html+=' 	</tr>'
menu7html+=' 	<tr>'
menu7html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu7html+=' 		<td colspan="2" bgcolor="#ffffff" height="228" valign="top">'
menu7html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0">'
menu7html+=' 				<tr>'
menu7html+=' 					<td width="100%"><p>'
menu7html+=' 						<a href="http://www.kariyer.net/kariyerrehberi/kariyerRehberiDetay.kariyer'+chk_link_add+'&prt=81&kn=711'+clickTakipLink(34)+'" class=menulink><b>&raquo; Röportaj</b></a>'
menu7html+=' 						<font class="menugray"><br>'
menu7html+=' 						Yünsa ÝK Müdürü'
menu7html+=' 						<br>Tamer Tok'
menu7html+=' 						</font>'
menu7html+=' 						</p>'

menu7html+=' 					</td>'
menu7html+=' 					<td width="78"><a href="http://www.kariyer.net/kariyerrehberi/kariyerRehberiDetay.kariyer'+chk_link_add+'&prt=81&kn=711'+clickTakipLink(34)+'"><img src="http://web4.kariyer.net/uploadFiles/tempPhoto/kariyerRehberi/S00000000711.jpg" width="75" alt="Yünsa ÝK Müdürü Tamer Tok" border="0"></a></td>'
menu7html+=' 				</tr>'
menu7html+=' 				<tr>'
menu7html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu7html+=' 				</tr>'

menu7html+=' 				<tr>'
menu7html+=' 					<td colspan="2"><a href="/Projelerimiz/Kaldigimiz-Yerden/kaldigimizYerden_01_01.kariyer'+chk_link_add+'" class="menulink"><b>&raquo; Kaldýðýmýz Yerden Projesi</b></a></td>'
menu7html+=' 				</tr>'
menu7html+=' 				<tr>'
menu7html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu7html+=' 				</tr>'

menu7html+=' 			</table>'
menu7html+=' 			<table width="100%" cellpadding="0" cellspacing="3" border="0" height="40">'
menu7html+=' 				<tr>'
menu7html+=' 					<td width="50%" valign="top">'
menu7html+=' 						<p><a href="/kariyerRehberi/kariyerRehberiArama.kariyer'+chk_link_add+'&prt=94&result=1&subjectType=1'+clickTakipLink(35)+'" class=menulink>&raquo; Makale</a></p>'
menu7html+=' 						<p><a href="/kariyerRehberi/kariyerRehberiArama.kariyer'+chk_link_add+'&prt=94&result=1&subjectType=2'+clickTakipLink(36)+'" class=menulink>&raquo; Sýra Dýþý Kariyer</a></p>'
menu7html+=' 						<p><a href="/kariyerRehberi/kariyerRehberiArama.kariyer'+chk_link_add+'arn=&sid=&prt=94&result=1&subjectType=3'+clickTakipLink(37)+'" class=menulink>&raquo; Röportaj</a></p>'
menu7html+=' 					</td>'
menu7html+=' 					<td width="50%" valign="top" nowrap>'
menu7html+=' 						<p><a href="/employers/login/firmaBHikaye.kariyer'+chk_link_add+clickTakipLink(38)+'" class=menulink>&raquo; Ýþveren Baþarý Hikayeleri</a></p>'
menu7html+=' 						<p><a href="/kariyerrehberi/AdayBasariHikayeleri/index.kariyer'+chk_link_add+clickTakipLink(39)+'" class=menulink>&raquo; Aday Baþarý Hikayeleri</a></p>'
menu7html+=' 						<p><a href="/kariyerDergi/index.kariyer'+chk_link_add+clickTakipLink(40)+'"  target="_blank" class=menulink>&raquo; Kariyer Dergi</a></p>'
menu7html+=' 						<p><a href="http://www.kariyer.net/universite_etkinlikleri"  target="_blank" class=menulink>&raquo; Üniversite Etkinliklerimiz</a></p>'
menu7html+=' 					</td>'
menu7html+=' 				</tr>'

menu7html+=' 			</table>'
menu7html+=' 		</td>'
menu7html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu7html+=' 	</tr>'
menu7html+=' 	<tr>'
menu7html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
menu7html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" width="278" height="11" alt=""></td>'
menu7html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
menu7html+=' 	</tr>'
menu7html+=' </table>'
menu7html+=' </div>'


if (isIE6)
{
menu7html+=' <iframe src="about:blank" style="width: 360px; height: 200px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}

document.getElementById('menudiv7').innerHTML=menu7html


// Yardým MENÜSÜ
menu8html='<div id="menudiv8shadow" style="margin:3px; position:absolute; z-index: 1000; height: 290px;  width: 360; filter: alpha(opacity=40); opacity: 0.4;">'
menu8html+=' 	<table width="250" border="0" cellpadding="0" cellspacing="0">'
menu8html+=' 		<tr>'
menu8html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" alt="" width="11" height="11"></td>'
menu8html+=' 			<td width="228" bgcolor="black"></td>'
menu8html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" alt="" width="11" height="11"></td>'
menu8html+=' 		</tr>'
menu8html+=' 		<tr>'
menu8html+=' 			<td colspan="3" bgcolor="black" height="268"></td>'
menu8html+=' 		</tr>'
menu8html+=' 		<tr>'
menu8html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" alt="" width="11" height="11"></td>'
menu8html+=' 			<td bgcolor="black"></td>'
menu8html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" alt="" width="11" height="11"></td>'
menu8html+=' 		</tr>'
menu8html+=' 	</table>'
menu8html+=' </div>'
menu8html+=' <div id="menudiv8ust" style="position: relative; z-index: 1002;">'
menu8html+=' <table width="250" border="0" cellpadding="0" cellspacing="0">'
menu8html+=' 	<tr>'
menu8html+=' 		<td width="11" background="/img/sk1/ust/divsol.gif"></td>'
menu8html+=' 		<td width="66" bgcolor="#ffffff"></td>'
menu8html+=' 		<td width="162" background="/img/sk1/ust/divust.gif"></td>'
menu8html+=' 		<td width="11"><img src="/img/sk1/ust/divsagust.gif" alt="" width="11" height="11"></td>'
menu8html+=' 	</tr>'
menu8html+=' 	<tr>'
menu8html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu8html+=' 		<td colspan="2" valign="top" bgcolor="#ffffff" height="268">'
menu8html+=' 			<table width="100%" border="0" cellpadding="0" cellspacing="3">'
menu8html+=' 				<tr>'
menu8html+=' 					<td><p>'
menu8html+=' 						<a set="yes" linkindex="0" href="http://web4.kariyer.net/TeknikDestek/sifremi_unuttum.kariyer'+chk_link_add+'&KategoriID=-1&YardimID=0'+clickTakipLink(43)+'" class="menulink"><b>&raquo; Þifremi Unuttum</b></a>'
menu8html+=' 						<font class="menugray"><br>Kullanýcý adý ve þifrenizi unuttuysanýz bu bölümden yeni þifrenizi öðrenebilirsiniz.</font>'
menu8html+=' 						</p>'
menu8html+=' 					</td>'
menu8html+=' 					<td><img src="/img/sk1/ust/yardimImg.jpg" alt="" width="78" height="60"></td>'
menu8html+=' 				</tr>'
menu8html+=' 				<tr>'
menu8html+=' 					<td colspan="2"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu8html+=' 				</tr>'
menu8html+=' 			</table>'
menu8html+=' 			<table width="100%" border="0" cellpadding="0" cellspacing="3" height="40">'
menu8html+=' 				<tr>'
menu8html+=' 					<td><a href="http://www.kariyer.net/yenilik" class="menulink" target="_blank">&raquo;  Kariyer.net Yenilikleri</a><br><font class="menugray">Yeni eklenen özellikler, bilgiler, geliþmeler...</font><br><br></td>'
menu8html+=' 				</tr>'
menu8html+=' 				<tr>'
menu8html+=' 					<td><a href="/support/NasilCv/baslarken.kariyer'+chk_link_add+clickTakipLink(44)+'" class="menulink">&raquo; Etkin Özgeçmiþ Sunumu</a><br><font class="menugray">Özgeçmiþinizi en doðru þekilde oluþturun.</font><br><br></td>'
menu8html+=' 				</tr>'
menu8html+=' 				<tr>'
menu8html+=' 					<td><a href="/support/isarama/index.htm" target="_blank" class="menulink">&raquo; Detaylý Ýþ Arama Klavuzu</a><br><font class="menugray">Ýlan arama yönteminize göz atýn, uygun ilaný gözden kaçýrmayýn.</font><br><br></td>'
menu8html+=' 				</tr>'
menu8html+=' 				<tr>'
menu8html+=' 					<td><a href="/teknikdestek/index.kariyer'+chk_link_add+clickTakipLink(45)+'" class="menulink">&raquo;  Diðer Yardým Konularý</a><br><font class="menugray">Soru sorun, önerilerinizi paylaþýn!</font></td>'
menu8html+=' 				</tr>'
menu8html+=' 			</table>'
menu8html+=' 		</td>'
menu8html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu8html+=' 	</tr>'
menu8html+=' 	<tr>'
menu8html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" alt="" width="11" height="11"></td>'
menu8html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" alt="" width="228" height="11"></td>'
menu8html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" alt="" width="11" height="11"></td>'
menu8html+=' 	</tr>'
menu8html+=' </table>'
menu8html+=' </div>'

if (isIE6)
{
menu8html+=' <iframe src="about:blank" style="width: 253px; height: 284px; z-Index: 500; position:absolute; top: 5px; left: 0px" frameborder="0"></iframe>'
}

document.getElementById('menudiv8').innerHTML=menu8html


// Ýþveren Giriþi MENÜSÜ
menu9html='<div id="menudiv9shadow" style="margin:3px; position:absolute; z-index: 1000; height: 190px;  width: 370; filter: alpha(opacity=40); opacity: 0.4;">'
menu9html+=' 	<table width="350" border="0" cellpadding="0" cellspacing="0">'
menu9html+=' 		<tr>'
menu9html+=' 			<td><img src="/img/sk1/ust/shadow1.gif" alt="" width="11" height="11"></td>'
menu9html+=' 			<td width="328" bgcolor="black"></td>'
menu9html+=' 			<td><img src="/img/sk1/ust/shadow2.gif" alt="" width="11" height="11"></td>'
menu9html+=' 		</tr>'
menu9html+=' 		<tr>'
menu9html+=' 			<td colspan="3" bgcolor="black" height="170"></td>'
menu9html+=' 		</tr>'
menu9html+=' 		<tr>'
menu9html+=' 			<td><img src="/img/sk1/ust/shadow3.gif" alt="" width="11" height="11"></td>'
menu9html+=' 			<td bgcolor="black"></td>'
menu9html+=' 			<td><img src="/img/sk1/ust/shadow4.gif" alt="" width="11" height="11"></td>'
menu9html+=' 		</tr>'
menu9html+=' 	</table>'
menu9html+=' </div>'
menu9html+=' <div id="menudiv9ust" style="position: relative; z-index: 1002;">'
menu9html+=' <table width="350" border="0" cellpadding="0" cellspacing="0">'
menu9html+=' 	<tr>'
menu9html+='		<td width="11" background="/img/sk1/ust/divsolust.gif"></td>'
menu9html+='		<td width="204" bgcolor="#ff0000" background="/img/sk1/ust/divust.gif"></td>'
menu9html+='		<td width="116" bgcolor="#ffffff"></td>'
menu9html+='		<td width="11"><img src="/img/sk1/ust/divsag.gif" width="11" height="11" alt=""></td>'
menu9html+=' 	</tr>'
menu9html+=' 	<tr>'
menu9html+=' 		<td background="/img/sk1/ust/divsol.gif"></td>'
menu9html+=' 		<td colspan="2" valign="top" bgcolor="#ffffff" height="170">'
menu9html+=' 			<table width="100%" border="0" cellpadding="0" cellspacing="3">'
menu9html+=' 				<tr>'
menu9html+=' 					<td valign="top">'
menu9html+=' 						<p><a href="'+employerURL+'/employers/login/index.kariyer'+chk_link_add+clickTakipLink(47)+'" linkindex="0" class="menulink"><b>&raquo; Üye Firma Giriþi</b></a></p>'
menu9html+=' 						<p><a href="http://web1.kariyer.net/employers/forms/newCompany.kariyer'+chk_link_add+clickTakipLink(48)+'" linkindex="0" class="menulink"><b>&raquo; Üye Firma Ol!</b></a></p>'
menu9html+=' 						<p><a href="/support/firmaneden.kariyer'+chk_link_add+clickTakipLink(49)+'" linkindex="0" class="menulink"><b>&raquo; Neden Kariyer.net</b></a></p>'
menu9html+=' 					</td>'
menu9html+=' 					<td width="1%"><img src="/img/sk1/Employers/UyeFirmaOl/telikon.jpg" alt="" width="42" height="44"></td>'
menu9html+=' 					<td><b>Bize Hemen Ulaþýn!</b><br>0 (216) 468 76 00</td>'
menu9html+=' 				</tr>'
menu9html+=' 				<tr>'
menu9html+=' 					<td colspan="3"><font color="#c0c0c0">- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -</font></td>'
menu9html+=' 				</tr>'
menu9html+=' 			</table>'
menu9html+=' 			<table width="100%" border="0" cellpadding="0" cellspacing="3" height="40">'
menu9html+=' 				<tr>'
http://www.kariyer.net
menu9html+=' 					<td align="left"><a href="/eleman-ariyorum-hemen-bul/index.kariyer'+chk_link_add+clickTakipLink(50)+'" linkindex="0" class="menulink"><img src="/img/sk1/hemenbul/grfx_r1_c1.jpg" align="absmiddle" alt="" width="83" height="39" border="0"></a> &nbsp; <a href="/eleman-ariyorum-hemen-bul/index.kariyer'+chk_link_add+'" linkindex="0" class="menulink"><img src="/img/sk1/hemenbul/grfx_r1_c3.jpg" align="absmiddle" alt="" width="107" height="39" border="0"></a></td>'
menu9html+=' 				</tr>'
menu9html+=' 			</table>'
menu9html+=' 		</td>'
menu9html+=' 		<td background="/img/sk1/ust/divsag.gif"></td>'
menu9html+=' 	</tr>'
menu9html+=' 	<tr>'
menu9html+=' 		<td><img src="/img/sk1/ust/divsolalt.gif" alt="" width="11" height="11"></td>'
menu9html+=' 		<td colspan="2"><img src="/img/sk1/ust/divalt.gif" alt="" width="328" height="11"></td>'
menu9html+=' 		<td><img src="/img/sk1/ust/divsagalt.gif" alt="" width="11" height="11"></td>'
menu9html+=' 	</tr>'
menu9html+=' </table>'
menu9html+=' </div>'

if (isIE6)
{
	menu9html+=' <iframe src="about:blank" style="width:350; height: 170px; z-Index: 500; position:absolute; top: 5px; left: 0px;" frameborder="0"></iframe>'
}

document.getElementById('menudiv9').innerHTML=menu9html