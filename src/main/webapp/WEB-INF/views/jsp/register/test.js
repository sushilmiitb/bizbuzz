function MFSConstructor(){
	this.fs = undefined;
}

MFSConstructor.prototype.getFS = function (args){
	function successFS(fs){
		this.fs = fs;
		if(args.getFSSuccess !== undefined){
			args.getFSSuccess(args.getFSSuccessArgs);
		}
	}
	function failFS(){
		alert("Failed to load file system.");
	}
	window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, successFS, failFS);
}

MFSConstructor.prototype.initializeFS = function (args){
	alert("inside initializeFS");
}
var MobileFileSystem = new MFSConstructor();