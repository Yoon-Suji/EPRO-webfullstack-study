document.querySelectorAll('.right-arrow').forEach(item => {
    item.addEventListener('click', event => {
        var parent = item.parentElement.parentElement;
        var type = parent.id;
        var id = item.parentElement.id;
        
        var oReq = new XMLHttpRequest();
        oReq.onreadystatechange = function(){
    		window.location.reload();
    		console.log("success");
		};
	
        oReq.open("GET", "todotype?id="+id+"&type="+type);
        oReq.send();
        
    });
});