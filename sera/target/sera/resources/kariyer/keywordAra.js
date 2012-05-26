	var KeywordRequest = newKeywordRequestObject();
	var KeywordIntervalID = 0;
	
	
	
	function KeywordKeyPr(Yeni)
	{
		
		var KeywordSure = document.getElementById('KeywordSure')
		if (Yeni!=0) 
			KeywordSure.value=Yeni

		if (KeywordIntervalID == 0 )
			KeywordIntervalID = setInterval('KeywordKeyPr(0)',100)
			
		if (KeywordSure.value <=0)
			{
			clearInterval(KeywordIntervalID)
			KeywordIntervalID = 0
			KeywordSure.value=15
			fnKeywordisyonAra()
			}
		else
			KeywordSure.value = KeywordSure.value - 1
			
	}
	
	function fnKeywordisyonAra()
		{
				var Keyword = document.getElementById('Keyword')
				document.getElementById('PozisyonUnvanId').value='';
				document.getElementById('FSektor').value='';
				document.getElementById('IsAlani').value='';

				if (Keyword.value.length<3 && Keyword.value!='DJ' && Keyword.value!='dj'  && Keyword.value!='Dj') 
				{
					document.getElementById('divAnahtarKelime').innerHTML='';
					document.getElementById('divAnahtarKelimeTmp').style.display='none';
					return
				}
				document.getElementById('divAnahtarKelimeTmp').style.display='block';
				parametreler = 'Kriter='+escape(Keyword.value)
				myURL = 'http://www.kariyer.net/jobsearch/getAnahtarKelime.kariyer';
				if(navigator.appName == "Microsoft Internet Explorer")
				{
					KeywordRequest.open('post', myURL,true);
					KeywordRequest.onreadystatechange = fnKeywordListele;
				}
				else
				{
					KeywordRequest.onreadystatechange = fnKeywordListele;
					KeywordRequest.open('post', myURL,true);
				}
				KeywordRequest.setRequestHeader('If-Modified-Since', 'Sat, 1 Jan 2000 00:00:00 GMT');
				KeywordRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded;language=tr");
				KeywordRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded; charset=iso-8859-9');
				KeywordRequest.setRequestHeader('Content-length', parametreler.length);
				KeywordRequest.setRequestHeader('Connection', 'close');
				KeywordRequest.send(parametreler)
				
		}
	
	
	
	function fnKeywordListele() 
	{
		if(KeywordRequest.readyState == 4 && KeywordRequest.status==200)
		{
			if (KeywordRequest.responseText=='')
				document.getElementById('divAnahtarKelimeTmp').style.display='none';
			else
				document.getElementById('divAnahtarKelimeTmp').style.display='block';
			document.getElementById('divAnahtarKelime').innerHTML=KeywordRequest.responseText;
			
	
		}
	}
	
	function newKeywordRequestObject() 
	{
		var ro;
		var browser = navigator.appName;
		if(browser == "Microsoft Internet Explorer")
		{
			ro = new ActiveXObject("Microsoft.XMLHTTP");
		}
		else
		{
			ro = new XMLHttpRequest();
		}
		return ro;
	}	
	function xmlEncodeText(x)
	{
		
		x=x.replace(/!k!/g,"<")
		x=x.replace(/!b!/g,">")
		x=x.replace(/!amp!/g,"&")
		return x
	
	}
	function keywSec(tip,kod,deger)
	{
		document.getElementById('PozisyonUnvanId').value='';
		document.getElementById('FSektor').value='';
		document.getElementById('IsAlani').value='';
		switch (tip)
		{
		case 'P':
			document.getElementById('PozisyonUnvanId').value=kod;
			break;
		case 'S':
			document.getElementById('FSektor').value=kod;
			break;
		case 'I':
			document.getElementById('IsAlani').value=kod;
			break;
		default:
			break;
		}
		document.getElementById('Keyword').value=deger;
		document.getElementById('divAnahtarKelimeTmp').style.display='none';
	
	}