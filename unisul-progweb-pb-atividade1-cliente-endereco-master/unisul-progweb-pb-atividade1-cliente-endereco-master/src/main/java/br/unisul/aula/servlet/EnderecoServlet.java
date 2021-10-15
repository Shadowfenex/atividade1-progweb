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

import br.unisul.aula.Banco.EnderecoImpl;
import br.unisul.aula.dtocliente.EnderecoDTO;
import br.unisul.aula.modelo.Endereco;

@WebServlet(name = "EnderecoServlet", value = "/EnderecoServlet")
public class EnderecoServlet extends HttpServlet{
	
	private final Gson gson = new Gson();
	private final EnderecoImpl enderecoImpl = new EnderecoImpl();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        List<Endereco> enderecoList = enderecoImpl.findAll();
        List<EnderecoDTO> dtos = new ArrayList<>();
        for (int i = 0; i < enderecoList.size(); i++) {
            EnderecoDTO dto = new EnderecoDTO(enderecoList.get(i));
            dtos.add(dto);
        }
        String enderecoJson = gson.toJson(dtos);
        response.getWriter().println(enderecoJson);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
        Endereco endereco = enderecoDTO.converterParaEndereco();
        enderecoImpl.insert(endereco);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
        System.out.println(enderecoDTO);
        Endereco endereco = enderecoDTO.converterParaEndereco();
        System.out.println(endereco);
        enderecoImpl.update(endereco);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        EnderecoDTO enderecoDTO = gson.fromJson(reader, EnderecoDTO.class);
        enderecoImpl.remove(enderecoDTO.getIdEndereco());
    }
}
