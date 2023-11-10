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

export function updateArticle(event){
	event.preventDefault()
	alert("update "+event.target.value)
}