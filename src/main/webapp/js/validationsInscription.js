/**
 * validation des champs du formulaire d'inscription
 */

fname.addEventListener("change", validationPrenom);

function validationPrenom(event){
	if(fname.value.length < 3){
		fname.classList.add("is-invalid")
  		invalidPrenom.classList.remove("hidden")
  	} else {
		fname.classList.remove("is-invalid")
		fname.classList.add("is-valid")
  		invalidPrenom.classList.add("hidden")
	  }
}

valider.addEventListener("click", validationPrenom);
annuler.addEventListener("click", validationPrenom);