suppImagesButton.addEventListener("click", validationSuppression);

function validationSuppression(event){
	let checkboxes = document.querySelectorAll('input[name="imagesChecks"]:checked')
	let checkboxesAll = document.querySelectorAll('input[name="imagesChecks"]')
	if( checkboxes.length < 1){
		event.preventDefault()
		alert("Veuillez cocher les images à supprimer.")
	}else{
		if(checkboxes.length === checkboxesAll.length){
			event.preventDefault()
			alert("Veuillez laisser au moins une image.")
		}else{
			if(!confirm("Etes vous sur de vouloir supprimer les images cochés ?")){
				event.preventDefault()
			}
		}
	}
}