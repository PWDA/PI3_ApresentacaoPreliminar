package br.com.senac.pi3.pwda.controller;

import br.com.senac.pi3.pwda.model.Funcionario;
import br.com.senac.pi3.pwda.servico.ServicoFuncionario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncConsultar", "/FuncCadastrar", "/FuncDeletar"})
public class FuncionarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagina = request.getRequestURI();

        try {
            if (pagina.endsWith("FuncConsultar")) {
                RequestDispatcher rd
                    = request.getRequestDispatcher("/jsp/consultarFunc.jsp");
                rd.forward(request, response);
            } else if (pagina.endsWith("FuncCadastrar")) {
                funcionarioDigitar(request, response);
//                RequestDispatcher rd
//                    = request.getRequestDispatcher("/jsp/cadastrarFunc.jsp");
//                rd.forward(request, response);
            } else if (pagina.endsWith("FuncDeletar")) {
                funcionarioDeletar(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagina = request.getRequestURI();

        try {
            if (pagina.endsWith("FuncConsultar")) {
                funcionarioConsultar(request, response);
            } else if (pagina.endsWith("FuncCadastrar")) {
                funcionarioSalvar(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }
    }

    protected void funcionarioSalvar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {

        Funcionario funcionario = new Funcionario();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        // id = 0 CASO FOR TENTADO CADASTRAR MAIS DE 1 VEZ NA MESMA PAGINA
        if (request.getParameter("id").equalsIgnoreCase("") || request.getParameter("id") == null){
            funcionario.setId(0);
        }else{
            funcionario.setId(Integer.valueOf(request.getParameter("id")));
        }
        
        funcionario.setNome(request.getParameter("nome"));        
        funcionario.setNumDocumento(request.getParameter("NrDocumento"));
        funcionario.setTelefone(request.getParameter("telefone"));
        funcionario.setEmail(request.getParameter("email"));
        
        String dataNascimento = request.getParameter("data-nascimento");                       
        Date dtNasc = format.parse(dataNascimento);
        funcionario.setDataNasci(dtNasc);
        
        funcionario.setSexo(request.getParameter("sexo"));
        funcionario.setCargo(request.getParameter("cargo"));        
        funcionario.setDepartamento(request.getParameter("departamento"));
        funcionario.setNacionalidade(request.getParameter("nacionalidade"));
        funcionario.setEndereco(request.getParameter("endereco"));
        funcionario.setBairro(request.getParameter("bairro"));
        funcionario.setCep(request.getParameter("cep"));
        funcionario.setCidade(request.getParameter("cidade"));
        funcionario.setUf(request.getParameter("uf"));
        
        Integer re = Integer.parseInt(request.getParameter("numero-registro"));
        funcionario.setRegistro(re);
        
        

        try {
            
            boolean resposta = ServicoFuncionario.insertFunc(funcionario);
            if (resposta == true){                
                RequestDispatcher dispatcher
                    = request.getRequestDispatcher("/jsp/cadastrarFunc.jsp");
                dispatcher.forward(request, response);
            }
            
            //response.sendRedirect("FuncConsultar");
            
        } catch (Exception ex) {
//            RequestDispatcher rd = request.getRequestDispatcher("Funcionario/FuncionarioDigitar.jsp");
//
//            request.setAttribute("funcionario", funcionario);
//            request.setAttribute("erro", ex.getMessage());
//
//            rd.forward(request, response);
        }
    }

    protected void funcionarioConsultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String busca = request.getParameter("buscar");
        String situacao  = request.getParameter("situacao");
        
//        if (busca == null) {
//            busca = "";
//        }

        List<Funcionario> func = ServicoFuncionario.getList(busca,situacao);        

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/consultar.jsp"); 
//            request.setAttribute("funcionario", func);
//        dispatcher.forward(request, response);
        
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/consultarFunc.jsp");
        request.setAttribute("buscar", busca);
        request.setAttribute("funcionario", func);       
        rd.forward(request, response);
    }

    protected void funcionarioDigitar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String req = request.getParameter("id");
        int id;

        if (req == null) {
            id = 0;
        } else {
            id = Integer.parseInt(req);
        }

        Funcionario funcionario = ServicoFuncionario.getById(id);

        if (funcionario.getId() != id) {
            response.sendRedirect("/jsp/consultarFunc.jsp");
        } else {
            
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/cadastrarFunc.jsp");
            request.setAttribute("funcionario", funcionario);
            rd.forward(request, response);            
        }
    }

    protected void funcionarioDeletar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        try {

            boolean resposta = ServicoFuncionario.deleteRegistro(id);
            
            if(resposta == true){
                RequestDispatcher rd
                    = request.getRequestDispatcher("/jsp/consultarFunc.jsp");
                rd.forward(request, response);
            }

            
            
        } catch (Exception ex) {
            request.setAttribute("erro", ex.getMessage());

            funcionarioConsultar(request, response);
        }
    }

}
