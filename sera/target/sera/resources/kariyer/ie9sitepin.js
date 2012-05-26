$(document).ready(function () {
    if ( $.browser.msie && parseInt($.browser.version, 10)>=9) 
	{
	
	initializePinnedSite();
	activeList("/rss/iepindata.kariyer");
    //setInterval(setOverlayIcon('iconos/warning.ico', 'yeni haber'), 100);
	}
});
function activeList(xmlUrl) {

    var currentTime = new Date()
    var month = currentTime.getMonth() + 1;
    var day = currentTime.getDate();
    var year = currentTime.getFullYear();
	
    window.external.msSiteModeCreateJumplist('Son Ýlanlar');
    
	var icon = "/pinie/iconos/haber.ico";
    var nodeCount = 0;
    try {
        $.ajax({
            type: "GET",
            url: xmlUrl,
            dataType: "xml",
            error: function (jqXHR, textStatus, errorThrown) {
                confirm(errorThrown);
            },
            success: function (xml) {
			//alert(xml);
                var liste = $(xml).find('item').get().reverse();
                $.each(liste, function () {
                    nodeCount++;
                    var title = $(this).find('title').text();
                    var url = $(this).find('link').text();
                    try {
                        window.external.msSiteModeAddJumpListItem(title, url, icon);
                    } catch (ex) {
                        alert(ex);
                    }
                });
            }
        });
    } catch (e) {
        alert(e);
    }
}
function initializePinnedSite() {
    try {
        if (window.external.msIsSiteMode()) {
            document.addEventListener('msthumbnailclick', processSelection, false);
        }
        else {
            
			obj = document.getElementById("pinContainer");
            obj.style.display = "inline-block";
        }
    }
    catch (e) {
    }
}


function showCallout(e) {
    $("#callout").fadeIn();
}
function hideCallout(e) {
    $("#callout").fadeOut();
}
setTimeout(hideCallout, 3000);

function setOverlayIcon(iconUri, descText) {
    try {
        if (window.external.msIsSiteMode()) {
            window.external.msSiteModeSetIconOverlay(iconUri, descText);
        }
    }
    catch (e) {
    }
}
function clearOverlayIcon() {
    try {
        if (window.external.msIsSiteMode()) {
            window.external.msSiteModeClearIconOverlay();
        }
    }
    catch (e) {
    }
}
















