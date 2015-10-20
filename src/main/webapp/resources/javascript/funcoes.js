function bloqueiaUI() {
	$("body").prepend("<div class='div-bloqueio-ui'/>");
}

function desbloqueiaUI() {
	$(".div-bloqueio-ui").remove();
	
	// Atribuindo placeholder aos filtros
	$('input[id*="filter"]').each(function() {
	    var $this = $(this);
	    $this.attr("placeholder", "Filtro");
	});
}

function upperCase(campo) {
	var valor = document.getElementById(campo.id).value;
	document.getElementById(campo.id).value = valor.toUpperCase();
}

function lowerCase(campo) {
	var valor = document.getElementById(campo.id).value;
	document.getElementById(campo.id).value = valor.toLowerCase();
}

function somenteNumero(campo, event) {
	if(event.keyCode >= 37 && event.keyCode <= 40) return; // Permite o uso das setas
	var value = campo.value;
	value = value.replace(/\D/g, ""); // Permite digitar apenas numeros
	campo.value = value;

