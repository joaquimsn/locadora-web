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
