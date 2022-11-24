// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
	apiKey: "AIzaSyB01txdnuaFitXw0Kv68WSWItw4pvCUkw8",
	authDomain: "image-storage-fddf1.firebaseapp.com",
	projectId: "image-storage-fddf1",
	storageBucket: "image-storage-fddf1.appspot.com",
	messagingSenderId: "695462217036",
	appId: "1:695462217036:web:4358b799ebaffdd5ac3be9",
	measurementId: "G-JF9QP6SEVY"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

import { getStorage, ref as sRef, uploadBytesResumable, getDownloadURL } from "https://www.gstatic.com/firebasejs/9.12.1/firebase-storage.js";

// --config
var files = [];
var reader = new FileReader();

var namebox = document.getElementById('namebox');
var myimg = document.getElementById('myimg');
var SelBtn = document.getElementById('selbtn');
var UpBtn = document.getElementById('upbtn');

var input = document.createElement('input');
input.type = 'file';
input.accept = "image/png, image/jpeg";

SelBtn.onclick = function() {
	input.click();
}

input.onchange = e => {
	files = e.target.files;

	let extension = GetFileExt(files[0]);
	let name = GetFileName(files[0]);

	namebox.value = "";

	let progress = document.createElement('div');
	progress.classList.add('progress');

	let progressBar = document.createElement('div');
	progressBar.classList.add('progress-bar', 'progress-bar-striped', 'progress-bar-animated');
	progressBar.id = 'thumbImg';

	document.getElementById('imageBox').append(progress);
	progress.append(progressBar);

	reader.readAsDataURL(files[0]);
}

reader.onload = function() {
	myimg.src = reader.result;
}

function GetFileExt(file) {
	if (file) {
		let temp = file.name.split('.');
		let ext = temp.slice((temp.length - 1), (temp.length));
		return '.' + ext[0];
	}
};

function GetFileName(file) {
	if (file) {
		let temp = file.name.split('.');
		let fname = temp.slice(0, -1).join('.');
		return fname;
	}
};

function UploadProcess() {
	SelBtn.disabled = true;
	UpBtn.disabled = true;
	let ImgToUpload = files[0];

	let ImgName = namebox.value;

	const metaData = {
		contentType: ImgToUpload.type
	};

	const storage = getStorage();

	const storageRef = sRef(storage, "Thumbnail/" + ImgName + new Date().toLocaleString('vi-vn').replace(', ', '_'));

	const UploadTask = uploadBytesResumable(storageRef, ImgToUpload, metaData);

	UploadTask.on('state-changed', snapshot => {
		let progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
		document.getElementById('thumbImg').style.width = `${progress}%`;
	}, error => {
		alert("error: Image Not Uploaded !");
		error.printStackTrace();
	}, () => {
		getDownloadURL(UploadTask.snapshot.ref).then(downloadURL => {
			namebox.value = downloadURL;
			myimg.src = downloadURL;
		});
	})
};

UpBtn.onclick = UploadProcess;

// Multiple image upadate

var Files = [];
var FileReaders = [];
var ImageLinksArray = [];
var isUploaded = false;
var images = [];

const selectBtn = document.getElementById('selectBtn');
const uploadBtn = document.getElementById('uploadBtn');
const loadLab = document.getElementById('loadlab');
const imgDiv = document.getElementById('imagesDiv');
const clearLabel = document.getElementById('clearLabel');


// events

selectBtn.addEventListener('click', OpenFileDialog);

// firebase upload

function OpenFileDialog() {
	let inp = document.createElement('input');
	inp.type = 'file';
	inp.multiple = 'multiple';

	inp.onchange = e => {
		AssignImgsToFilesArray(e.target.files);
		CreateImgTags();
	}

	inp.click();
}

function AssignImgsToFilesArray(thefiles) {
	let num = Files.length + thefiles.length;
	let looplim = (num <= 10) ? thefiles.length : (10 - Files.length);
	for (let i = 0; i < looplim; i++) {
		Files.push(thefiles[i]);
	}

	if (num > 10) alert("Maximum 10 images are allowed !");
}


function CreateImgTags() {
	imgDiv.innerHTML = '';
	imgDiv.classList.add('imagesDivStyle');

	for (let i = 0; i < Files.length; i++) {
		FileReaders[i] = new FileReader();

		FileReaders[i].onload = function() {
			var div = document.createElement('div');
			var img = document.createElement('img');
			img.id = `imgNo${i}`;
			img.src = FileReaders[i].result;
			div.classList.add('imgs');
			var progress = document.createElement('div');
			progress.classList.add('progress', 'progress-loading');
			var progressBar = document.createElement('div');
			progressBar.id = `imgNo_${i}`;
			progressBar.classList.add('progress-bar', 'progress-bar-striped', 'progress-bar-animated');
			imgDiv.append(div);
			var input = document.createElement('input');
			input.setAttribute('type', 'hidden');
			input.id = `imgSrcNo${i}`;
			div.append(img);
			div.append(progress);
			div.append(input);
			progress.append(progressBar);
		}
		isUploaded = true;
		FileReaders[i].readAsDataURL(Files[i]);
	}

	clearLabel.innerHTML = 'Clear Images';
	clearLabel.style = 'cursor: pointer; display: block; color: navy; font-size: 12px;';
	clearLabel.addEventListener('click', ClearImages);
	isUploaded === true ? '' : imgDiv.append(clearLabel);
}

function ClearImages() {
	Files = [];
	ImageLinksArray = [];
	imgDiv.innerHTML = '';
	imgDiv.classList.remove('imagesDivStyle');
	isUploaded = false;
	document.getElementById('imagesLinkValue').innerHTML = '';
}


function GetImgUploadProgress() {
	return 'Uploading...';
}

function IsAllImagesUploaded() {
	return ImageLinksArray.length == Files.length;
}

function UploadAllImages() {
	selectBtn.disabled = true;
	uploadBtn.disabled = true;
	clearLabel.innerHTML = '';
	for (let i = 0; i < Files.length; i++) {
		UploadAnImage(Files[i], i);
	}
	imgDiv.classList.remove('imagesDivStyle');
}

function UploadAnImage(imgToUpload, imgNo) {

	const metadata = {
		contentType: imgToUpload.type
	};

	const storage = getStorage();

	const ImageAddress = "AssetImages/" + "Image#" + (imgNo + 1) + "_" + new Date().toLocaleString('vi-vn').replace(', ', '_');

	const storageRef = sRef(storage, ImageAddress);

	const UploadTask = uploadBytesResumable(storageRef, imgToUpload, metadata);

	UploadTask.on('state-changed', snapshot => {
		var progress = snapshot.bytesTransferred / snapshot.totalBytes * 100;
		document.getElementById(`imgNo_${imgNo}`).style.width = `${progress}%`;
		loadLab.innerHTML = GetImgUploadProgress();
	}, error => {
		alert("error: Image upload Failed");
		error.printStackTrace();
	}, () => {
		getDownloadURL(UploadTask.snapshot.ref).then(downloadURL => {
			ImageLinksArray.push(downloadURL);
			document.getElementById(`imgNo${imgNo}`).src = downloadURL;
			document.getElementById(`imgSrcNo${imgNo}`).value = downloadURL;
			if (IsAllImagesUploaded) {
				document.getElementById('imageList').value = ImageLinksArray;
				loadLab.innerHTML = `${ImageLinksArray.length} Image(s) Uploaded`;
			}
		});
	});
}

uploadBtn.onclick = UploadAllImages;



