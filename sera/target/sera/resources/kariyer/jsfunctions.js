/*Genel kullanýlan scripler*/
/*Fonksiyon isimleri js ile baþlamalý ki herhangi deðiþken yada fonksiyonla karýþmasýn*/

/*
getAjaxPage(divAdi,pageURL,reLoad)
getAjaxPage('divIlanListesi','getIlanSecimi.kariyer'+all_link_add,false)
*/

function createAjaxPageRequestObject() 
	{
		var ro;
		var browser = navigator.appName;
		if(browser == "Microsoft Internet Explorer"){
			ro = new ActiveXObject("Microsoft.XMLHTTP");
		}else{
			ro = new XMLHttpRequest();
		}
		return ro;
	}

var httpAjaxPage = createAjaxPageRequestObject();
var httpAjaxPageDiv = createAjaxPageRequestObject();



function jsNumericBox()/*add:onkeypress info:Textbox a sayý dýþýnda giriþi engeller*/	
	{		
		if(!(event.keyCode==45||event.keyCode==46||event.keyCode==48||event.keyCode==49||event.keyCode==50||event.keyCode==51||event.keyCode==52||event.keyCode==53||event.keyCode==54||event.keyCode==55||event.keyCode==56||event.keyCode==57))		
		{event.returnValue=false;}	
	}

function jsCheckEmail(obje,MaybeEmth)/*Email geçerlimi kontrol et. MaybeEmth=1 ise boþ mailler geçerlidir.*/
	{
		var str=obje.value;
		if (MaybeEmth==1 && str=="") return true;
		var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		if (str.length<1) return false;
		if (filter.test(str)) return true
		else return false;
	}

function getAjaxPageDiv(divAdi,myUrl,reLoad,height,width)
	{	
		if (httpAjaxPageDiv.readyState!=0 && httpAjaxPageDiv.readyState!=4) 
			{
			setTimeout("getAjaxPageDiv('"+divAdi+"','"+myUrl+"',"+reLoad+",'"+height+"','"+width+"')",1000)	
			return
			}
		var thistime= new Date()
		var hours=thistime.getHours()
		var minutes=thistime.getMinutes()
		var seconds=thistime.getSeconds()
		if (eval(hours) <10) {hours="0"+hours}
		if (eval(minutes) < 10) {minutes="0"+minutes}
		if (seconds < 10) {seconds="0"+seconds}
		var thistime = hours+minutes+seconds
		
		w = width;
		h = height;
		
		pageNesneDiv = document.getElementById(divAdi);
		pageNesneDiv.style.display='';
		if (pageNesneDiv.innerHTML!='' && !reLoad) 
			{
			return ;//daha önce yüklenmiþ...
			}
		pageNesneDiv.innerHTML = '<span style="background-color:whitesmoke;border:1px solid silver;width:50px;height:50px;padding:20px"><img src="/img/sk1/ikon/loader.gif" border="0"></span>';
		if (myUrl.indexOf('?'))
			myUrl=myUrl+'&qrnd='+thistime
		else
			myUrl=myUrl+'?qrnd='+thistime
		httpAjaxPageDiv.open('get', myUrl);
		AjaxDivAdiDiv = divAdi;
		httpAjaxPageDiv.onreadystatechange = handleAjaxPageDiv;
		httpAjaxPageDiv.send(null);
		
	}

function handleAjaxPageDiv() 
	{
		if(httpAjaxPageDiv.readyState == 4 && httpAjaxPageDiv.status==200)
		{
			nesne = document.getElementById(AjaxDivAdiDiv)
			if(httpAjaxPageDiv.responseText!='')
				{
										
					vhtml='<div id="shd001Div" style="position:absolute;margin:3px;z-index: 1000; height: '+h+'; width: '+w+'; filter: alpha(opacity=40); opacity: 0.4;">'
					vhtml= vhtml + '					<table width="'+w+'" cellpadding="0" cellspacing="0" border="0">'
					vhtml= vhtml + '						<tr>'
					vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow1.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '							<td bgcolor="black" width="100%"></td>'
					vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow2.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '						</tr>'
					vhtml= vhtml + '						<tr>'
					vhtml= vhtml + '							<td colspan="3" bgcolor="black" height="'+(h)+'"></td>'
					vhtml= vhtml + '						</tr>'
					vhtml= vhtml + '						<tr>'
					vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow3.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '							<td bgcolor="black"></td>'
					vhtml= vhtml + '							<td><img src="/img/sk1/ust/shadow4.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '						</tr>'
					vhtml= vhtml + '					</table>'
					vhtml= vhtml + '				</div>'
					
					vhtml= vhtml + '				<div id="shd002Div" style="position:relative; z-index: 1002;">'
					vhtml= vhtml + '				<table width="'+w+'" cellpadding="0" cellspacing="0" border="0">'
					vhtml= vhtml + '					<tr>'
					vhtml= vhtml + '						<td width="11" background="/img/sk1/ust/divsolust.gif"></td>'
					vhtml= vhtml + '						<td width="100%" background="/img/sk1/ust/divust.gif"></td>'
					vhtml= vhtml + '						<td width="11"><img src="/img/sk1/ust/divsagust.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '					</tr>'
					vhtml= vhtml + '					<tr>'
					vhtml= vhtml + '						<td background="/img/sk1/ust/divsol.gif"></td>'
					vhtml= vhtml + '						<td bgcolor="#ffffff" height="'+(h-12)+'" valign="top">'
					vhtml= vhtml + httpAjaxPageDiv.responseText; 
					vhtml= vhtml + '						</td>'
					vhtml= vhtml + '						<td background="/img/sk1/ust/divsag.gif"></td>'
					vhtml= vhtml + '					</tr>'
					vhtml= vhtml + '					<tr>'
					vhtml= vhtml + '						<td background="/img/sk1/ust/divsol.gif"></td>'
					vhtml= vhtml + '						<td align="right" bgcolor="#ffffff"><a href="javascript:void(0);" onclick="javascript:document.getElementById(\''+AjaxDivAdiDiv+'\').style.display=\'none\';"><img src="/newimg/sk1/sil.jpg" width="12" height="12" alt="" border="0"></a></td>'
					vhtml= vhtml + '						<td background="/img/sk1/ust/divsag.gif"></td>'
					vhtml= vhtml + '					</tr>'
					vhtml= vhtml + '					<tr>'
					vhtml= vhtml + '						<td><img src="/img/sk1/ust/divsolalt.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '						<td><img src="/img/sk1/ust/divalt.gif" width="100%" height="11" alt=""></td>'
					vhtml= vhtml + '						<td><img src="/img/sk1/ust/divsagalt.gif" width="11" height="11" alt=""></td>'
					vhtml= vhtml + '					</tr>'
					vhtml= vhtml + '				</table>'
					vhtml= vhtml + '				</div>'
					
					nesne.innerHTML=vhtml
					execJS(nesne)
				}
				
		}
		if(httpAjaxPageDiv.readyState == 4 && httpAjaxPageDiv.status==500)
		{
			nesne = document.getElementById(AjaxDivAdiDiv)
			nesne.innerHTML='Ýsteðiniz yerine getirilemedi!';
		}
	}
	
function getAjaxPage(divAdi,myUrl,reLoad)
	{	
		if (httpAjaxPage.readyState!=0 && httpAjaxPage.readyState!=4) 
			{
			setTimeout("getAjaxPage('"+divAdi+"','"+myUrl+"',"+reLoad+")",1000)	
			return
			}
		pageNesne = document.getElementById(divAdi);
		if (pageNesne.innerHTML!='' && !reLoad) 
			return ;//daha önce yüklenmiþ...

		var thistime= new Date()
		var hours=thistime.getHours()
		var minutes=thistime.getMinutes()
		var seconds=thistime.getSeconds()
		if (eval(hours) <10) {hours="0"+hours}
		if (eval(minutes) < 10) {minutes="0"+minutes}
		if (seconds < 10) {seconds="0"+seconds}
		var thistime = hours+minutes+seconds		
		
		pageNesne.innerHTML = '<span style="background-color:whitesmoke;border:1px solid silver;width:50px;height:50px;padding:20px"><img src="/img/sk1/ikon/loader.gif" border="0"></span>';
		if (myUrl.indexOf('?')) 
			myUrl=myUrl+'&qrnd='+thistime 
		else
			myUrl=myUrl+'?qrnd='+thistime
		
		httpAjaxPage.open('get', myUrl);
		AjaxDivAdi = divAdi;
		httpAjaxPage.onreadystatechange = handleAjaxPage;
		
		httpAjaxPage.send(null);
	}

function handleAjaxPage() 
	{
		if(httpAjaxPage.readyState == 4 && httpAjaxPage.status==200)
		{
			nesne = document.getElementById(AjaxDivAdi)
			if(httpAjaxPage.responseText!='')
				{
					nesne.innerHTML=httpAjaxPage.responseText;
					execJS(nesne)
				}
		}
		if(httpAjaxPage.readyState == 4 && httpAjaxPage.status==500)
		{
			nesne = document.getElementById(AjaxDivAdi)
			nesne.innerHTML='Ýsteðiniz yerine getirilemedi!';
		}
	}
	
	
function execJS(node)//ajaxla yüklenen sayfanýn scriptlerini çalýþtýrýyor
{
  //confirm(node.id)
  var bSaf = (navigator.userAgent.indexOf('Safari') != -1);
  var bOpera = (navigator.userAgent.indexOf('Opera') != -1);
  var bMoz = (navigator.appName == 'Netscape');

  if (!node) return;

  /* IE wants it uppercase */
  var st = node.getElementsByTagName('SCRIPT');
  var strExec;

  for(var i=0;i<st.length; i++) {eval(st[i]);}

  for(var i=0;i<st.length; i++)
  {
    if (bSaf) {
      strExec = st[i].innerHTML;
      st[i].innerHTML = "";
    } else if (bOpera) {
      strExec = st[i].text;
      st[i].text = "";
    } else if (bMoz) {
      strExec = st[i].textContent;
      st[i].textContent = "";
    } else {
      strExec = st[i].text;
      st[i].text = "";
    }

    try 
	{
		var x = document.createElement("script");
      	x.type = "text/javascript";
	    /* In IE we must use .text! */
    	if ((bSaf) || (bOpera) || (bMoz))
        	x.innerHTML = strExec;
	      else 
		  	x.text = strExec;
		document.getElementsByTagName("head")[0].appendChild(x);
	} 
	catch(e) 
	{
		//confirm('sdf');
      	confirm(e);
	}
  }
}

function urlencode(str) {
	return escape(str).replace(/\+/g,'%2B').replace(/%20/g, '+').replace(/\*/g, '%2A').replace(/\//g, '%2F').replace(/@/g, '%40');
}

function urldecode(str) {
	return unescape(str.replace('+', ' '));
}