/*
 Christian Znidarsic
 ValidateHikers function
 The validateHikers function is used to validate the input
 number of hikers on the client side. If the input is valid
 it allows the "submit" button to be pressed. Otherwise,
 the submit button is disabled.
 */

function validateHikers() {
    var errorMessage = document.getElementById("errorMessage");
    var submitButton = document.getElementById("submitButton");
    var inputText = document.getElementById("hikersInput").value;
	var validInput = true;
	
	console.log(inputText);
	
	if (inputText == "") {
		errorMessage.innerHTML = "";
	}
	else if (inputText <= 0) {
		validInput = false;
		errorMessage.innerHTML = "Number of hikers must be positive";
	}
	else if (!(/^\d+$/.test(inputText))) {
		validInput = false;
		errorMessage.innerHTML = "The input \"Hikers\" can only contain numbers";
	}
	else if (inputText > 99) {
		validInput = false;
		errorMessage.innerHTML = "Number of hikers is too large";
	}
	
	
	
	if (validInput) {
		errorMessage.innerHTML = "";
		submitButton.removeAttribute("disabled");
	}
	else {
		submitButton.setAttribute("disabled", "");
	}
	
}