var pageloaded=false

window.onload = function () 
{
	pageloaded=true
}

function jsNVL(vlx, def)
{
	if ((typeof(vlx)=='undefined') || (vlx=='') || (vlx=='undefined') || (vlx == null))
	{
		var vlx = def;
	}	
	return vlx;
}

function tryFunction(msg,abaslik,bd,dur,trycount)
{ 
	//args = args.replace(/\"/g,"'"); 
	trycount = (trycount)? trycount : 0; 
	dur = (dur)? dur : 15; 
	if(pageloaded)
	{    
		yalert(msg,abaslik,bd)
		trycount=0; }
	else if(trycount < dur)
	{
		trycount++;    
		setTimeout("tryFunction(\""+msg+"\",\""+abaslik+"\","+dur+","+trycount+")", 200); 
	}
}


	window.alert = function (msg,abaslik,bd) {
		tryFunction(msg,abaslik,bd,50,0)
	}
	
	function yalert(msg,abaslik,bd){
		var sonuc;
		var msgHtml = new String();
	   if (document.all) 
    	    {
			docHeight= document.body.offsetHeight;
			docWidth= document.body.offsetWidth-20;
			}
	    else 
			{
    	    docHeight = document.height ;
			docWidth = document.width-20 ;
			}

		abaslik = jsNVL(abaslik,'Dikkat!');
		sonuc = jsNVL(msg,'').toString();
		//sonuc = sonuc.replace(/#chkskinno#/g,chk_skinno)
		sonuc = sonuc.replace(/\n/g,'<br>');


		if (window.XMLHttpRequest) {
		isIE6=false
		} 
		else 
		{
		isIE6=true
		}


		msgHtml= 		   '				<table cellpadding="0" cellspacing="0" border="0">';
		msgHtml= msgHtml + '					<tr>';
		msgHtml= msgHtml + '						<td width="11" background="/img/sk1/ust/divsolust.gif"></td>';
		msgHtml= msgHtml + '						<td background="/img/sk1/ust/divust.gif"><img src="/img/tpx.gif" width="320" border=0 height="1"></td>';
		msgHtml= msgHtml + '						<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>';
		msgHtml= msgHtml + '					</tr>';
		msgHtml= msgHtml + '					<tr>';
		msgHtml= msgHtml + '						<td background="/img/sk1/ust/divsol.gif"></td>';
		msgHtml= msgHtml + '						<td bgcolor="#ffffff" valign="top"><p class="Header4" style="height:20">'+abaslik+'</p>';
		msgHtml= msgHtml + '							<p>';
		if (bd!='1')
		{
		msgHtml= msgHtml + '							<img src="/img/sk1/ikon/unlem.gif" style="position:relative; top: -25px;" border="0" align="right">';
		}
		
		msgHtml= msgHtml + 								sonuc;
		msgHtml= msgHtml + '							</p>';
		

		msgHtml= msgHtml + '							<p align="center"><br>';
		msgHtml= msgHtml + '								<input type="Image" onload="this.focus()" src="/img/sk1/btn/tamam.gif" border="0" onclick="closeAlert()">';
		msgHtml= msgHtml + '							</p>';
		

		msgHtml= msgHtml + '						</td>';
		msgHtml= msgHtml + '						<td background="/img/sk1/ust/divsag.gif"></td>';
		msgHtml= msgHtml + '					</tr>';
		msgHtml= msgHtml + '					<tr>';
		msgHtml= msgHtml + '						<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>';
		msgHtml= msgHtml + '						<td><img src="/img/sk1/ust/divalt.gif" width="100%" height="11" alt=""></td>';
		msgHtml= msgHtml + '						<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>';
		msgHtml= msgHtml + '					</tr>';
		msgHtml= msgHtml + '				</table>';


		altDiv01 = document.getElementById('alertDiv01');
		altDiv02 = document.getElementById('alertDiv02');
		altDiv03 = document.getElementById('alertDiv03');
		altDiv04 = document.getElementById('alertDiv04');
		altFrame = document.getElementById('alertFrame');


		if (docHeight > document.body.scrollHeight)
			altDiv04.style.height=docHeight;
		else
			altDiv04.style.height=document.body.scrollHeight;
		
		if (docWidth > document.body.scrollWidth)
			altDiv04.style.width=docWidth;
		else
			altDiv04.style.width=document.body.scrollWidth;
		
		altDiv01.style.width = ''
		
		altDiv03.innerHTML=msgHtml;
		altDiv04.style.display='';
		altDiv01.style.display='';
		
		w = altDiv01.offsetWidth;
		h = altDiv01.offsetHeight;
		//confirm(w)
		if(w > h*4)
		{
			altDiv01.style.width = w / 2
			w = altDiv01.offsetWidth;
			h = altDiv01.offsetHeight;
		}
		if (w>600)
		{
			altDiv01.style.width = 600
			w = altDiv01.offsetWidth;
			h = altDiv01.offsetHeight;
		}
		//confirm(w)

		l = altDiv01.offsetLeft;
		t = altDiv01.offsetTop;
		altx = (document.body.scrollWidth - altDiv01.offsetWidth)/2;
		alty = document.body.scrollTop + (document.body.clientHeight - altDiv01.offsetHeight)/2;
		//aaa=confirm(altx+'-'+alty)
		altDiv01.style.top=alty-55;
		altDiv01.style.left=altx;
		

		shdHtml= 		   '<table  cellpadding="0" cellspacing="0" border="0" width="'+w+'">';
		shdHtml= shdHtml + '	<tr>';
		shdHtml= shdHtml + '		<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>';
		shdHtml= shdHtml + '		<td bgcolor="black" width="100%"></td>';
		shdHtml= shdHtml + '		<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>';
		shdHtml= shdHtml + '	</tr>';
		shdHtml= shdHtml + '	<tr>';
		shdHtml= shdHtml + '		<td colspan="3" bgcolor="black"  height="'+(h-22)+'">';
		shdHtml= shdHtml + '		</td>';
		shdHtml= shdHtml + '	</tr>';
		shdHtml= shdHtml + '	<tr>';
		shdHtml= shdHtml + '		<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>';
		shdHtml= shdHtml + '		<td bgcolor="black"></td>';
		shdHtml= shdHtml + '		<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>';
		shdHtml= shdHtml + '	</tr>';
		shdHtml= shdHtml + '</table>';

		altDiv02.innerHTML=shdHtml;
		altDiv02.style.left=altx+3;
		altDiv02.style.top=alty-55+3;
		altDiv02.style.width=w;
		altDiv02.style.height=h;
		altDiv02.style.display='';	
		if (isIE6)
		{
			altFrame.style.left=altx+3;
			altFrame.style.top=alty-55+3;
			altFrame.style.width=w;
			altFrame.style.height=h;
			altFrame.style.display='';
		}
	}
	

function closeAlert()
	{
	document.getElementById('alertFrame').style.display='none';
	document.getElementById('alertDiv04').style.display='none';
	document.getElementById('alertDiv02').style.display='none';
	document.getElementById('alertDiv01').style.display='none';
	}

