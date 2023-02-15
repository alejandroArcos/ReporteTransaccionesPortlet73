package ReporteTransaccionesPortlet.portlet;

import com.google.gson.Gson;
/*import com.google.gson.Gson;*/
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.cotizador.CotizadorService;
import com.tokio.cotizador.Bean.ListaTransaccionesRequest;
import com.tokio.cotizador.Bean.ListaTransaccionesResponse;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import ReporteTransaccionesPortlet.constants.ReporteTransaccionesPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + ReporteTransaccionesPortletKeys.ReporteTransacciones,
"mvc.command.name=/getListaTransaccionesUrl" }, service = MVCResourceCommand.class)

public class ListaTransaccionesResourceCommand extends BaseMVCResourceCommand {
	@Reference
    CotizadorService _CotizadorService;
	
	@Override
    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		System.out.println("Ya entré papá");
		String codigoCliente = "";
		String poliza = ParamUtil.getString(resourceRequest, "id_poliza");
		String certif = "";
		String recibo ="";
		String endoso = ParamUtil.getString(resourceRequest, "id_endoso");
		String estatus = ParamUtil.getString(resourceRequest, "id_estatus");
		int tipMovimiento =ParamUtil.getInteger(resourceRequest, "id_movimiento");
		String fDesde = ParamUtil.getString(resourceRequest, "dc_dateDesde");
		String fHasta = ParamUtil.getString(resourceRequest, "dc_dateHasta");
		int moneda = ParamUtil.getInteger(resourceRequest, "id_moneda");
		String folio = ParamUtil.getString(resourceRequest, "id_folio");
		String idTransaccion = ParamUtil.getString(resourceRequest, "no_Transa");
		
		ListaTransaccionesRequest peticion =new ListaTransaccionesRequest();
		
		peticion.setCodigoCliente(codigoCliente);
		peticion.setPoliza(poliza);
		peticion.setCertif(certif);
		peticion.setRecibo(recibo);
		peticion.setIdTipoMovimiento(tipMovimiento);
		peticion.setFechaInicio(fDesde);
		peticion.setFechaFin(fHasta);
		peticion.setIdMoneda(moneda);
		peticion.setFolio(folio);
		peticion.setIdTransaccion(idTransaccion);
		
		
		ListaTransaccionesResponse respuesta =  _CotizadorService.wsListaTransacciones(peticion);
		
		System.out.println("respuesta: ");
		

		Gson gson = new Gson();
		String gsonString = gson.toJson(respuesta);
		
		
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(gsonString);
		
		System.out.println(gsonString);
	}
	
}
