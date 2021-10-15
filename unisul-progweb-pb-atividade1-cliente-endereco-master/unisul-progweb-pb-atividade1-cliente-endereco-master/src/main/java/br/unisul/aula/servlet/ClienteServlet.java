package br.unisul.aula.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.unisul.aula.Banco.ClienteImpl;
import br.unisul.aula.dtocliente.ClienteDTO;
import br.unisul.aula.dtocliente.EnderecoDTO;
import br.unisul.aula.modelo.Cliente;
import br.unisul.aula.modelo.Endereco;

@WebServlet(name = "ClienteServlet", value = "/ClienteServlet")
public class ClienteServlet extends HttpServlet {

	private final Gson gson = new Gson();
	private final ClienteImpl clienteImpl = new ClienteImpl();
	    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        List<Cliente> clienteList = clienteImpl.findAll();
        List<ClienteDTO> dtos = new ArrayList<>();
        for (int i = 0; i < clienteList.size(); i++) {
            ClienteDTO dto = new ClienteDTO(clienteList.get(i));
            dtos.add(dto);
        }
        String clienteJson = gson.toJson(dtos);
        response.getWriter().println(clienteJson);
    }
	    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        ClienteDTO clienteDTO = gson.fromJson(reader, ClienteDTO.class);
        Cliente cliente = clienteDTO.converterParaCliente();
        clienteImpl.insert(cliente);
    }
	    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        ClienteDTO clienteDTO = gson.fromJson(reader, ClienteDTO.class);
        System.out.println(clienteDTO);
        Cliente cliente = clienteDTO.converterParaCliente();
        System.out.println(cliente);
        clienteImpl.update(cliente);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        ClienteDTO clienteDTO = gson.fromJson(reader, ClienteDTO.class);
        clienteImpl.remove(clienteDTO.getIdCliente());
    }
}
