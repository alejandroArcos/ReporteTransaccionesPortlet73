var optionsXlsButton = {dom: 'fBrltip', buttons: [{
    extend:    'excelHtml5',
    text:      '<a class="btn-floating btn-sm teal waves-effect waves-light py-2 my-0">XLS</a>',
    titleAttr: 'Exportar XLS',
    className:"btn-unstyled",
    exportOptions: {
        /*columns: ':not(:last)',*/	
        format: {
          body: function ( data, row, column, node ) {
              return data.replace( /[$,%]/g, '' );
          }
        }

    }
}]};

var idAlert = 0;

$(document).ready(function() {
    $('.datepicker').pickadate({
        format: 'yyyy-mm-dd',
        formatSubmit: 'yyyy-mm-dd'
    });
    
    $('.table-wrapper').attr('hidden',true);
    
    /*console.log("ready!");*/

});


$('#btn-buscar').click(function(){
	/*if (!vaciosTxt($('#id_folio'))){*/
	eliminaAlerttext();
	$.post($("#idLt").val(), $('.formulario-Transacciones :input').serialize() )
	 .done(function(data) {
		 if ((data!="")&&(data!=null)){
			 $('#table1_1').dataTable().fnDestroy();
			 $('#table1_1 tbody tr').remove();
			 
			 var respuestaJson = JSON.parse(data);
			 /*console.log("respuestaJson: ");*/
			 if(respuestaJson.metadatos.code==0){
				 $.each(respuestaJson.listaTransacciones, function(key,valor){
					 /*console.log('key: '+ key + ' valor: ' + valor);*/
					 $('#table1_1 tbody').append("<tr><td>"+valor.poliza+"</td><td>"+valor.certif+"</td><td>"+valor.nombre+"</td><td>"+valor.listaMoneda.descripcion+"</td><td>$"+valor.primaTotal+"</td><td>"+valor.listaTipoMovimiento.descripcion+"</td><td>"+valor.folio+"</td></tr>");
					 /*$('#table1_1 tbody').append("<tr><td>"+valor.poliza+"</td><td>"+valor.certif+"</td><td>"+valor.nombre+"</td><td>"+valor.listaMoneda.descripcion+"</td><td>$"+valor.primaTotal+"</td><td>"+valor.listaTipoMovimiento.descripcion+"</td><td>PAQUETE EMPRESARIEL 2011</td><td>"+valor.folio+"</td></tr>");*/
					 /*$.each(valor, function(k,val){
					 console.log('k: '+ k + ' val: ' + val);
				 });*/
				 });
				 $('#table1_1').DataTable(optionsXlsButton);
				 $('.table-wrapper').attr('hidden',false);				 
			 }
			 else{
				 agregaAlertError(respuestaJson.metadatos.msg);
			 }	 
		 }
	 });
	
	/*}*/
});

function vaciosTxt(campos){
	var errores = false;
	$.each(campos,function(index, value) {
		if (valIsNullOrEmpty($(value).val())) {
			errores = true;
			$(value).addClass("invalid");
			$(value).parent().append("<div class=\"alert alert-danger\" role=\"alert\"> <span class=\"glyphicon glyphicon-ban-circle\"></span>El campo es requerido</div>");
		}
	});
	return errores;
}
	
function noSelect(campos){
	var errores = false;
	$.each(campos,function(index, value) {
		if (valIsInvalidSelect($(value).val())) {
			errores = true;
			$(value).siblings("input").addClass('invalid');
			$(value).parent().append("<div class=\"alert alert-danger\" role=\"alert\"> <span class=\"glyphicon glyphicon-ban-circle\"></span>El campo es requerido</div>");
		}
	});
	return errores;
}

function valIsNullOrEmpty(value) {
	if (value === undefined) {
	      return true;
	}
	value = value.trim();
	return (value == null || value == "null" || value === "");
}
	
function valIsInvalidSelect(value) {
	return (value === '-1');
};

function agregaAlertSuccess(mensaje){
	showMessageSuccess(".navbar", mensaje,idAlert);
	idAlert++;
}
function agregaAlertError(mensaje){
	showMessageError(".navbar", mensaje,idAlert);
	idAlert++;
}
function eliminaAlerttext(){
	idAlert--;
	if (idAlert<0){
		idAlert=0;
	}
	$(".alert-danger").remove();
	$(".invalid").removeClass('invalid');
}