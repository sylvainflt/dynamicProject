/**
 * 
 */

suppButton.addEventListener("click", validationSuppression);

function validationSuppression(event){
	let checkboxes = document.querySelectorAll('input[name="articlesIds"]:checked')
	if( checkboxes.length < 1){
		event.preventDefault()
		alert("Veuillez cocher les articles à supprimer.")
	}else{
		if(!confirm("Etes vous sur de vouloir supprimer les articles cochés ?")){
			event.preventDefault()
		}
		
	}
}

suppCatButton.addEventListener("click", validationSupCat)

function validationSupCat(event){
	let checkboxes = document.querySelectorAll('input[name="categoriesIds"]:checked')
	if( checkboxes.length < 1){
		event.preventDefault()
		alert("Veuillez cocher les catégories à supprimer.")
	}else{
		if(!confirm("Etes vous sur de vouloir supprimer les catégories cochés ?")){
			event.preventDefault()
		}
		
	}
}