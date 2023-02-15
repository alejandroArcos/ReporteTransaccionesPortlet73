<%@ include file="./init.jsp" %>
<portlet:resourceURL id="/getListaTransaccionesUrl" var="listaTransaccionesUrl"/>


<%-- <section class="site-wrapper">  --%>
    <%-- <header></header>  --%>
    <div class="container-fluid">
        <h3 class="tit-reporte">Reporte de Transacciones</h3>
        
        <div class="formulario-Transacciones">
	        <div class="row">
	            <div class="col-md-3">
	                <div class="md-form">
	                    <input type="text" id="id_poliza" name="id_poliza" class="form-control">
	                    <label for="id_poliza">Póliza:</label>
	                </div>
	            </div>
	            <div class="col-md-3">
	                <div class="md-form">
	                    <input type="text" id="id_endoso" name="id_endoso" class="form-control">
	                    <label for="id_endoso">Endoso:</label>
	                </div>
	            </div>
	            <div class="col-md-3">
	                <div class="md-form form-group">
	                    <select name="id_estatus" id="id_estatus" class="mdb-select form-control-sel">
	                        <option value="-1" selected>Todos</option>
	                        <c:forEach items="${listaEstatus}" var="opc">
							    <option value="${opc.idCatalogoDetalle}">${opc.descripcion}</option>
							</c:forEach>
	                    </select>
	                    <label for="id_estatus">Estatus</label>
	                </div>
	            </div>
	            <div class="col-md-3">
	                <div class="md-form form-group">
	                    <select name="id_movimiento" id="id_movimiento" class="mdb-select form-control-sel">
	                        <option value="-1">Todos</option>
	                        <c:forEach items="${listaTipPago}" var="opc">
							    <option value="${opc.idCatalogoDetalle}">${opc.descripcion}</option>
							</c:forEach>
	                    </select>
	                    <label for="id_movimiento">Tipo de Movimiento</label>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-sm-6">
	                <div class="md-form form-group">
	                    <div class="row">
	                        <div class="col">
	                            <input placeholder="Fecha Desde" type="date" id="dc_dateDesde" name="dc_dateDesde" class="form-control datepicker">
	                        </div>
	                        <div class="col">
	                            <input placeholder="Fecha Hasta" type="date" id="dc_dateHasta" name="dc_dateHasta" class="form-control datepicker">
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-3">
	                <div class="md-form form-group">
	                    <select name="id_moneda" id="id_moneda" class="mdb-select form-control-sel">
		                    <option value="-1" selected>Todos</option>
							<c:forEach items="${listaMoneda}" var="opc">
							    <option value="${opc.idCatalogoDetalle}">${opc.valor}</option>
							</c:forEach>
		                </select>
	                    <label for="id_moneda">Moneda</label>
	                </div>
	            </div>
	        </div>
	        <div class="row">
	        	<div class="col-md-3">
	                <div class="md-form">
	                    <input type="text" id="id_folio" name="id_folio" class="form-control">
	                    <label for="id_folio">Folio:</label>
	                </div>
	            </div>
	            <div class="col-md-3">
	                <div class="md-form">
	                    <input type="text" id="no_Transa" name="no_Transa" class="form-control">
	                    <label for="no_Transa">No. Transacción:</label>
	                </div>
	            </div>
	        </div>
        </div>
        
        
        <div class="row">
            <div class="col-sm-12 text-right">
                <a class="btn btn-pink" id="btn-buscar"><i class="fas fa-search"></i>Buscar</a>
            </div>
        </div>

        <div class="container">
            <div class="table-wrapper" hidden="hidden">
                <table class="table data-table table-striped table-bordered" style="width:100%;" id="table1_1">
                    <thead>
                        <tr>
                            <th>Póliza</th>
                            <th>Endoso</th>
                            <th>Contratante</th>
                            <th>Moneda</th>
                            <th>Prima Total</th>
                            <th>Tipo de Movimiento</th>
							
                            <th>Folio</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<%-- </section>  --%>

<input type="hidden"  id="idLt" value="${listaTransaccionesUrl}">

<style type="text/css">
    .card-deck .card .view.overlay {
        font-size: 50px;
    }
    
    .card-deck .card .card-body .fa {
        position: absolute;
        bottom: 0;
        font-size: 25px;
        margin-left: -12px;
        margin-bottom: -10px;
    }
    
    .site-wrapper .accordion .card-deck .card {
        border-bottom: none;
    }
    
    .site-wrapper .container-fluid .nav-tabs {
        justify-content: space-between;
    }
    
    .site-wrapper .container-fluid .nav-tabs li a {
        color: white;
    }
    
    .site-wrapper .nav-tabs .nav-item {
        width: 23%;
        text-align: center;
    }
    
    .site-wrapper .container-fluid .tab-content {
        box-shadow: none;
    }
    
    h3.tit-reporte {
        padding-left: 20%;
        color: indigo;
        font-weight: normal;
    }
    
    .tab-content .col-sm-12.col-md-2.text-right {
        padding: 11px;
    }
    
    /*.site-wrapper .select-wrapper ul.select-dropdown {
        z-index: 1;
    }*/
    
    .site-wrapper section#content {
        margin-top: 105px;
    }
</style>


<script src="<%=request.getContextPath()%>/js/custom.js"></script>