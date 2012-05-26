	showTimer = 1000
	closeTimer=1000
	posLayer='p1'
	w=100
	h=100
	html='Bolgeler'
	addx=1
	addy=1
	lastLayer=''

	if (sektorel)
		clkTkp='';
	else
		clkTkp='&clkTkp=1027';
		
	prestijhtml='<table width=96% cellpadding=4 cellspacing=0 align=center><tr><td class="Header2"><b>#pozisyonadi#</b><br><font class=Header3>#firmaadi#</font></td><td align=right valign=top><a href=\'javascript:hideDiv(1);\'><img src=\'/newimg/sk#chkskinno#/sil.jpg\' width=12 height=12 alt=\'\' border=0></a></td></tr><tr><td class=prestij colspan=2><div style="width:100%; overflow:auto; overflow-y: auto; height:135px;">#genelnitelikler#</div></td></tr><tr><td  colspan=2><br><a href="javascript:gokariyer(\'/jobsearch/jobdetail.kariyer\',\'&ilankodu=#ilankodu#'+clkTkp+'\',0)" class=link><b>&raquo; Ýlana Baþvur</b></a>#tumilanlar#</td></tr></table>'		

	var IE = document.all?true:false
	if (window.XMLHttpRequest)
		isIE6=false
	else 
		isIE6=true
		
	function setDiv(ax,bx,cx,dx,fx,gx,hx,ix)
	{
		//alert(document.getElementById(ax).offsetTop)
		//if (dx==6) return;
		if (lastLayer == ax) return
		lastLayer = ax;
		posLayer = ax;
		w = bx
		h = cx
		html = dx
		addx=fx
		addy=gx
		showTimer = hx*50
		closeTimer = ix*50
		//confirm(closeTimer)
		//hideDiv(0)
		setTimeout("showDiv()",showTimer)
	}
	
	function showDiv()
	{	
		vposDiv= document.getElementById(posLayer)
		vmainDiv= document.getElementById("mainDiv")
		if (showTimer>0 && !(tempX > vposDiv.offsetLeft && tempX < vposDiv.offsetLeft+vposDiv.offsetWidth	&& tempY > vposDiv.offsetTop-25 && tempY  < vposDiv.offsetTop+vposDiv.offsetHeight) ) return

		vmainDiv.style.top=vposDiv.offsetTop+addy
		vmainDiv.style.left=vposDiv.offsetLeft+addx
		vmainDiv.style.width=w
		vmainDiv.style.height=h

		if (posLayer.indexOf('prestij') >= 0 )
			{
				sonuc = prestijhtml
				sonuc = sonuc.replace(/#genelnitelikler#/g,ilanlar[html][5])
				sonuc = sonuc.replace(/#pozisyonadi#/g,ilanlar[html][4])
				sonuc = sonuc.replace(/#firmaadi#/g,ilanlar[html][3])
				sonuc = sonuc.replace(/#ilankodu#/g,ilanlar[html][0])
				if (ilanlar[html][1] =='')
					sonuc = sonuc.replace(/#tumilanlar#/g,'')
				else if (ilanlar[html][2]=='agaogluk_3.gif')
					sonuc = sonuc.replace(/#tumilanlar#/g,'<br><a href="javascript:gokariyer(\'/jobsearch/detayliarama.kariyer\',\'&ara=5&profilno=#firmakodu#'+clkTkp+'\',0)" class=link><b>&raquo; Firmanýn Tüm  Ýlanlarý</b></a>')
				else if (ilanlar[html][2]=='agaogluk_may.gif')
					sonuc = sonuc.replace(/#tumilanlar#/g,'<br><a href="javascript:gokariyer(\'/jobsearch/detayliarama.kariyer\',\'&ara=5&profilno=#firmakodu#'+clkTkp+'\',0)" class=link><b>&raquo; Firmanýn Tüm  Ýlanlarý</b></a>')
				else if (ilanlar[html][2]=='agaogluk_logo.gif')
					sonuc = sonuc.replace(/#tumilanlar#/g,'<br><a href="javascript:gokariyer(\'/jobsearch/detayliarama.kariyer\',\'&ara=5&profilno=#firmakodu#'+clkTkp+'\',0)" class=link><b>&raquo; Firmanýn Tüm  Ýlanlarý</b></a>')
				else if (ilanlar[html][0]=='630277')
					sonuc = sonuc.replace(/#tumilanlar#/g,'<br><a href="javascript:gokariyer(\'/jobsearch/detayliarama.kariyer\',\'&ara=5&profilno=#firmakodu#&sprofil=1'+clkTkp+'\',0)" class=link><b>&raquo; Firmanýn Tüm Ýlanlarý</b></a>')
				else
					sonuc = sonuc.replace(/#tumilanlar#/g,'<br><a href="javascript:gokariyer(\'/jobsearch/detayliarama.kariyer\',\'&ara=5&profilno=#firmakodu#'+clkTkp+'\',0)" class=link><b>&raquo; Firmanýn Tüm Ýlanlarý</b></a>')

				sonuc = sonuc.replace(/#firmakodu#/g,ilanlar[html][1])					
			}
		else
			{
				sonuc=html
			}
		
		sonuc = sonuc.replace(/#chkskinno#/g,chk_skinno)
		vhtml='<div id="shd001" style="position:absolute;margin:3px;z-index: 1000; height: '+h+'; width: '+w+'; filter: alpha(opacity=40); opacity: 0.4;">'
		vhtml= vhtml + '					<table width="'+w+'" cellpadding="0" cellspacing="0" border="0">'
		vhtml= vhtml + '						<tr>'
		vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '							<td bgcolor="black" width="100%"></td>'
		vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '						</tr>'
		vhtml= vhtml + '						<tr>'
		vhtml= vhtml + '							<td colspan="3" bgcolor="black" height="'+(h-22)+'"></td>'
		vhtml= vhtml + '						</tr>'
		vhtml= vhtml + '						<tr>'
		vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '							<td bgcolor="black"></td>'
		vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '						</tr>'
		vhtml= vhtml + '					</table>'
		vhtml= vhtml + '				</div>'
		
		vhtml= vhtml + '				<div id="shd002" style="position:relative; z-index: 1002;">'
		vhtml= vhtml + '				<table width="'+w+'" cellpadding="0" cellspacing="0" border="0">'
		vhtml= vhtml + '					<tr>'
		vhtml= vhtml + '						<td width="11" background="/img/sk1/ust/divsolust.gif"></td>'
		vhtml= vhtml + '						<td width="100%" background="/img/sk1/ust/divust.gif"></td>'
		vhtml= vhtml + '						<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '					</tr>'
		vhtml= vhtml + '					<tr>'
		vhtml= vhtml + '						<td background="/img/sk1/ust/divsol.gif"></td>'
		vhtml= vhtml + '						<td bgcolor="#ffffff" height="'+(h-22)+'" valign="top">'
		vhtml= vhtml + sonuc 
		vhtml= vhtml + '						</td>'
		vhtml= vhtml + '						<td background="/img/sk1/ust/divsag.gif"></td>'
		vhtml= vhtml + '					</tr>'
		vhtml= vhtml + '					<tr>'
		vhtml= vhtml + '						<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '						<td><img src="/img/sk1/ust/divalt.gif" width="100%" height="11" alt=""></td>'
		vhtml= vhtml + '						<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
		vhtml= vhtml + '					</tr>'
		vhtml= vhtml + '				</table>'
		vhtml= vhtml + '				</div>'
		if (isIE6)
			vhtml= vhtml + '<iframe src="about:blank" style="width: '+w+'px; height: '+h+'px; z-Index: 500; position:absolute; top: 5px; left: 0px;" frameborder="0"></iframe>'
		
		vmainDiv.innerHTML =vhtml.replace('a','A')
		vmainDiv.style.display=''

		if (closeTimer>0)
			setTimeout("hideDiv(0)",closeTimer) 
	}


	function hideDiv(clink)
	{	
		vmainDiv= document.getElementById('mainDiv')	
		vposDiv= document.getElementById(posLayer)
		if (clink==0 && ((tempX > vmainDiv.offsetLeft && tempX < vmainDiv.offsetLeft+vmainDiv.offsetWidth	&& tempY > vmainDiv.offsetTop && tempY  < vmainDiv.offsetTop+vmainDiv.offsetHeight) 
		   || (tempX > vposDiv.offsetLeft && tempX < vposDiv.offsetLeft+vposDiv.offsetWidth	&& tempY > vposDiv.offsetTop -25 && tempY  < vposDiv.offsetTop+vposDiv.offsetHeight) ))
			{
			setTimeout("hideDiv(0)",closeTimer) 
			}
		else
			{
			lastLayer=''
			vmainDiv.style.display='none'
				
			}
	}
	
	
	
	function writePrestij(basla,chklink,sayfadakiSayi)
	{
		son = basla+sayfadakiSayi-1
		for (xx=basla;xx<=son;xx=xx+2)
		{
			addtmp='';
			ind = xx
		
			if (ilanlar[ind][0]=="577907" || ilanlar[ind][0]=="579112") prfl="&sProfil=2";else prfl="";//Siemens 2. profili getir
						
			document.write ('<tr>');
			document.write ('<td valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)">');
			
			if (ilanlar[ind][2] != '') 
			{
				if (ilanlar[ind][1]=='1053')
				{
					addtmp='&sProfil=1';
				}
				else if (ilanlar[ind][0]=='630277')
				{
					addtmp='&sProfil=1';
				}
				else if (ilanlar[ind][0]=='681695')
				{
					addtmp='&ilangrubu=834';
				}
				else if (ilanlar[ind][0]=='646510')
				{
					ilanlar[ind][2]='14035_3.gif';
				}				
					
				else if (ilanlar[ind][2] == 'agaogluk_may.gif') {
				    addtmp = '';
				}
				else if (ilanlar[ind][2] == 'agaogluk_3.gif') {
				    addtmp = '';
				}
				else if (ilanlar[ind][2] == 'agaogluk_logo.gif') {
				
					addtmp='&plogo='+escape('agaogluk_logo');
				}
				else if (ilanlar[ind][0] == '707381') {
				
					ilanlar[ind][1]="";
				}
				else {
				    addtmp = '';
				}
				if(ilanlar[ind][1]=='')
					document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">')
				else
					document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+prfl+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">')
				
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>')
			}
			document.write ('</td>');

			document.write ('<td width="50%"  valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)"><span id="prestij'+ind+'" style="position:relative">')
			if (ilanlar[ind][1] != '') 
				document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+prfl+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">')
			else
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">')
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b></a><br>')

			document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig"><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</span></td>')

			ind = ind + 1
			addtmp='';
			document.write ('<td valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)">')
			
			if (ilanlar[ind][2] != '') 
			{
				if (ilanlar[ind][1]=='1053')
				{
					addtmp='&sProfil=1';
				}
				if (ilanlar[ind][0]=='672544')
				{
					addtmp='&sProfil=2';
				}
				else if (ilanlar[ind][0]=='681695')
				{
					addtmp='&ilangrubu=834';
				}
				else if (ilanlar[ind][0]=='630277')
				{
					addtmp='&sProfil=1';
				}
				else if (ilanlar[ind][0]=='646510')
				{
					ilanlar[ind][2]='14035_3.gif';
				}			
				
				else if (ilanlar[ind][2] == 'agaogluk_may.gif') {
				    addtmp = '';
				}
				else if (ilanlar[ind][2] == 'agaogluk_3.gif') {
				    addtmp = '';
				}
				else if (ilanlar[ind][2] == 'agaogluk_logo.gif') {
				
					addtmp='&plogo='+escape('agaogluk_logo');
				}
				else if (ilanlar[ind][0] == '707381') {
				
					ilanlar[ind][1]="";
				}
				else {
				    addtmp = '';
				}
				//Ziraat Bankasý
				if (ilanlar[ind][1]=='25229')
				{		
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">');
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>');
				}
				else if(ilanlar[ind][1]=='14035')
				{
				document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">');
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>');
				}
				else
				{
					if(ilanlar[ind][1]=='')
						document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">')
					else
						document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">');
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>');
				}
			}
			document.write ('</td>');

			document.write ('<td width="50%"  valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)"><span id="prestij'+ind+'" style="position:relative">')
			if (ilanlar[ind][1] != '') {
				//Ziraat bankasý
				if (ilanlar[ind][1]=='25229')
				{
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">');
				}
				else if(ilanlar[ind][1]=='14035')
				{
					document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">')				
				}				
				else
				{
				document.write ('<a href="/JobSearch/detayliarama.kariyer'+chklink+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&firmaBaslik='+escape(ilanlar[ind][3])+addtmp+'" class="prestijBig">')
				}
			}
			else
			{
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig">')
			}
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b></a><br>')

			document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'" class="prestijBig"><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</span></td>')


			document.write ('<tr><td colspan="2" height="3"></td></tr>');
		}
	}
	
	function writePrestijNew(basla,chklink,sayfadakiSayi)
	{
		chklink1 = chklink.replace('?','');
		son = basla+sayfadakiSayi-1
		for (xx=basla;xx<=son;xx=xx+2)
		{
			ind = xx
			
			document.write ('<tr>');
			document.write ('<td valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)">')
			if (ilanlar[ind][2] != '') 
			{
				document.write ('<a href="http://www.kariyer.net/ilanlar/'+trSil(ilanlar[ind][3]+' iþ ilanlarý')+'/'+chklink1+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&PTitle='+escape(ilanlar[ind][3]+' iþ ilanlarý')+'" class="prestijBig">')
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>')
			}
			document.write ('</td>');

			document.write ('<td width="50%"  valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)"><span id="prestij'+ind+'" style="position:relative">')
			if (ilanlar[ind][1] != '') 
				document.write ('<a href="http://www.kariyer.net/ilanlar/'+trSil(ilanlar[ind][3]+' iþ ilanlarý')+'/'+chklink1+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&PTitle='+escape(ilanlar[ind][3]+' iþ ilanlarý')+'" class="prestijBig">')
			else
				document.write ('<a href="http://www.kariyer.net/ilan/'+trSil(ilanlar[ind][4] +' iþ ilaný')+'/'+chklink1+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'&PTitle='+escape(ilanlar[ind][4]+' iþ ilaný')+'" class="prestijBig">')
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b></a><br>')

			document.write ('<a href="http://www.kariyer.net/ilan/'+trSil(ilanlar[ind][4]+'-iþ-ilaný')+'/'+chklink1+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'&PTitle='+escape(ilanlar[ind][4]+' iþ ilaný')+'" class="prestijBig"><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</span></td>')

			ind = ind + 1
			
			document.write ('<td valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)">')
			if (ilanlar[ind][2] != '') 
			{
				document.write ('<a href="http://www.kariyer.net/ilanlar/'+trSil(ilanlar[ind][3]+' iþ ilanlarý')+'/'+chklink1+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&PTitle='+escape(ilanlar[ind][3]+' iþ ilanlarý')+'" class="prestijBig">')
				document.write ('<img src="'+imageserver+'/profiles/logodb/Slogodb2/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>')
			}
			document.write ('</td>');

			document.write ('<td width="50%"  valign="top" onmouseover="setDiv(\'prestij'+ind+'\',430,280,'+ind+',-40,26,30,10)"><span id="prestij'+ind+'" style="position:relative">')
			if (ilanlar[ind][1] != '') 
				document.write ('<a href="http://www.kariyer.net/ilanlar/'+trSil(ilanlar[ind][3]+' iþ ilanlarý')+'/'+chklink1+'&Ara=5&ProfilNo='+ilanlar[ind][1]+clkTkp+'&PTitle='+escape(ilanlar[ind][3]+' iþ ilanlarý')+'" class="prestijBig">')
			else
				document.write ('<a href="http://www.kariyer.net/ilan/'+trSil(ilanlar[ind][4]+' iþ ilaný')+'/'+chklink1+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'&PTitle='+escape(ilanlar[ind][4]+' iþ ilaný')+'" class="prestijBig">')
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b></a><br>')

			document.write ('<a href="http://www.kariyer.net/ilan/'+trSil(ilanlar[ind][4]+' iþ ilaný')+'/'+chklink1+'+&ilankodu='+ilanlar[ind][0]+clkTkp+'&PTitle='+escape(ilanlar[ind][4]+' iþ ilaný')+'" class="prestijBig"><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</span></td>')


			document.write ('<tr><td colspan="2" height="3"></td></tr>');
		}
	}
	
	function writePrestijSektorel(basla,chklink,sayfadakiSayi)
	{
		writePrestij(basla,chklink,sayfadakiSayi)
		return
		
		
		son = basla+sayfadakiSayi-1
		for (xx=basla;xx<=son;xx=xx+2)
		{
			ind = xx
			
			if (ilanlar[ind][2] != '' || ilanlar[ind+1][2] != '') 
			{
				document.write ('<tr>');
					document.write ('<td valign="bottom" onmouseover="setDiv(\'prestij'+ind+'\',500,300,'+ind+',40,20,30,10)">')
					if (ilanlar[ind][2] != '') 
						{
						document.write ('<a href="/JobSearch/firmaprofil.kariyer'+chklink+'&firma='+ilanlar[ind][1]+'" class="prestijBig">')
						document.write ('<img src="/profiles/logodb/Slogodb/'+ilanlar[ind][2]+'" border=0 alt=""  hspace=0></a>')
						}
					document.write ('</td>');
					document.write ('<td  valign="bottom" onmouseover="setDiv(\'prestij'+(ind+1)+'\',500,300,'+(ind+1)+',40,20,30,10)">')
					if (ilanlar[ind+1][2] != '') 
						{
						document.write ('<a href="/JobSearch/firmaprofil.kariyer'+chklink+'&firma='+ilanlar[ind+1][1]+'" class="prestijBig">')
						document.write ('<img src="/profiles/logodb/Slogodb/'+ilanlar[ind+1][2]+'" border=0 alt=""  hspace=0></a>')
						}
					document.write ('</td>');
				document.write ('</tr>');
			}
			
			document.write ('<tr>');
			document.write ('<td width="50%"  valign="top" onmouseover="setDiv(\'prestij'+ind+'\',500,300,'+ind+',40,20,30,10)"><span id="prestij'+ind+'" style="position:relative"></span>')
			if (ilanlar[ind][1] != '') 
				document.write ('<a href="/JobSearch/firmaprofil.kariyer'+chklink+'&firma='+ilanlar[ind][1]+'" class="prestijBig">')
			else
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+'" class="prestijBig">')
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b><br><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</td>')

			ind = ind + 1

			document.write ('<td width="50%" valign="top" onmouseover="setDiv(\'prestij'+ind+'\',500,300,'+ind+',40,20,30,10)"><span id="prestij'+ind+'" style="position:relative"></span>')
			if (ilanlar[ind][1] != '') 
				document.write ('<a href="/JobSearch/firmaprofil.kariyer'+chklink+'&firma='+ilanlar[ind][1]+'" class="prestijBig">')
			else
				document.write ('<a href="/JobSearch/jobdetail.kariyer'+chklink+'+&ilankodu='+ilanlar[ind][0]+'" class="prestijBig">')
			document.write ('<b>')
			document.write (ilanlar[ind][3])
			document.write ('</b><br><font class="prestij">')
			document.write (ilanlar[ind][4])
			document.write ('</font></a>')
			document.write ('</td></tr>')
			
			document.write ('<tr><td colspan="2" height="3"></td></tr>');
		}
	}
	
	function trSil(mtxt)
	{
	mtxt=mtxt.replace(/ý/g,'i')
	mtxt=mtxt.replace(/Ý/g,'I')
	mtxt=mtxt.replace(/ð/g,'g')
	mtxt=mtxt.replace(/Ü/g,'U')
	mtxt=mtxt.replace(/ü/g,'u')
	mtxt=mtxt.replace(/þ/g,'s')
	mtxt=mtxt.replace(/Þ/g,'S')
	mtxt=mtxt.replace(/Ö/g,'O')
	mtxt=mtxt.replace(/ö/g,'o')
	mtxt=mtxt.replace(/ç/g,'c')
	mtxt=mtxt.replace(/Ç/g,'C')
	mtxt=mtxt.replace(/ /g,'-')

	return mtxt
	}