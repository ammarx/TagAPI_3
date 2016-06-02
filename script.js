var getJsonLibrariesDownloadsClassifiersNativesX = function(name, natives_OS) {
	var data = name;
	var json = JSON.parse(data);	

	var p = "";
	var n = 0;
	for (i = 0; i < 500; i++){	
		try{
			p = p + json.libraries[n].downloads.classifiers[natives_OS].url + "\n";
			n = n + 1;
		} catch (err) {
			n = n + 1;
		}    
	}
	return(p);   
};


var getJsonLibrariesDownloadsClassifiersNativesY = function(name, natives_OS) {
	var data = name;
	var json = JSON.parse(data);	

	var p = "";
	var n = 0;
	for (i = 0; i < 500; i++){	
		try{
			p = p + json.libraries[n].downloads.classifiers[natives_OS].path + "\n";
			n = n + 1;
		} catch (err) {
			n = n + 1;
		}    
	}
	return(p);   
};
