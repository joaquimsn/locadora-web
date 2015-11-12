package br.com.jsn.noleggio.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.jsn.noleggio.main.util.BeanInjector;
import br.com.jsn.noleggio.modules.agencia.model.Agencia;
import br.com.jsn.noleggio.modules.agencia.service.AgenciaService;
import br.com.jsn.noleggio.modules.veiculo.model.Veiculo;
import br.com.jsn.noleggio.modules.veiculo.service.VeiculoService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@Path("/")
public class WSController {
	
	@Inject
	private AgenciaService agenciaService;
	@Inject
	private VeiculoService veiculoService;
	
	private Gson gson;
	private GsonBuilder gsonBuilder;
	
	public  WSController() {
		 GsonBuilder gsonBuilder = new GsonBuilder();
		 gson = gsonBuilder.create();
	}
	
	@GET
	@Path("/agencias")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaAgencia() {
		agenciaService = BeanInjector.getBean(AgenciaService.class);
		List<Agencia> listaAgencia = agenciaService.buscarTodos();
		
		JsonObject jsonObject;
		JsonArray jsonArray = new JsonArray();
		
		for (Agencia agencia : listaAgencia) {
			jsonObject = new JsonObject();
			jsonObject.addProperty("idAgencia", agencia.getIdAgencia());
			jsonObject.addProperty("fantasia", agencia.getFantasia());
			jsonArray.add(jsonObject);
		}
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@GET
	@Path("/veiculos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaVeiculo(@QueryParam("idAgencia") String idAgencia) {
		veiculoService = BeanInjector.getBean(VeiculoService.class);
		
		List<Veiculo> listaVeiculo = veiculoService.buscarTodosPorAgencia(Integer.parseInt(idAgencia));
		
		JsonObject jsonObject;
		JsonArray jsonArray = new JsonArray();
		
		for (Veiculo veiculo : listaVeiculo) {
			jsonObject = new JsonObject();
			jsonObject.addProperty("idVeiculo", veiculo.getIdVeiculo());
			jsonObject.addProperty("modelo", veiculo.getModelo());
			jsonObject.addProperty("fabricante", veiculo.getFabricante());
			jsonObject.addProperty("grupo", veiculo.getGrupo().getValue());
			jsonObject.addProperty("precoKmLivre", veiculo.getPrecoKmLivre());
			jsonObject.addProperty("precoKmControlado", veiculo.getPrecoKmControlado());
			jsonObject.addProperty("acessorios", veiculo.getAcessorios());
			jsonArray.add(jsonObject);
		}
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}

}
