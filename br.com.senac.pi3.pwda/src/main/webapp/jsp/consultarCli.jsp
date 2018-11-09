<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Consulta de cliente</title>
    <link rel="stylesheet" href="./css/consultar-cliente.css">
    <link rel="stylesheet" href="../css/consultar-cliente.css">
        <!--google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Nunito:300,400,700" rel="stylesheet">
        
         <!--fontawesome-->
         <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
</head>
<body>
   <header>
           <nav>
                <div class="top-header">
                    <div class="container">
                         <a href="./jsp/home.jsp">
                         <img class="img-logo" src="./img/pwda-logo.png" alt="" width="200">
                         </a> 
                    </div><!--container-->
                </div><!--logo-->
                <div class="container">
                    <ul class="menu-principal">
                        <li><a href="./jsp/home.jsp">Home</a></li>
                        <li class="link-submenu-cadastro"><a href="#">Cadastro</a>
                             <ul class="sub-menu">
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/CliCadastrar" method="get">Cliente</a></li>
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/FuncCadastrar" method="get">Funcionário</a></li>
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/ProdCadastrar" method="get">Produto</a></li>
                            </ul>
                        </li>
                        <li class="link-submenu-consulta"><a href="#">Consultar</a>
                            <ul class="sub-menu">
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/CliConsultar" method="get">Cliente</a></li>
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/FuncConsultar" method="get">Funcionário</a></li>
                                <li class="sub-menu-item"><a href="${pageContext.request.contextPath}/ProdConsultar" method="get">Produto</a></li>
                            </ul>
                        </li>
                        <li><a style = "font-size: 12px" href="realizar-venda.html">Realizar Venda</a></li>
                    </ul>
                </div><!--container-->
            </nav>
     
  </header>
   <div class="wrap">
       <div class="container">
            <form action="${pageContext.request.contextPath}/CliConsultar" method="post">
                <h1>Consulta Clientes</h1>
                <input type="text" name="buscar" placeholder="Digite para buscar..." value="${buscar}">
        Situação<select name="situacao" id="situacao">
                    <option></option>
                    <option value="Ativos">Ativos</option>
                    <option value="Inativos">Inativos</option>
                    <option value="Todos">Todos</option>
                </select>
                <input type="submit" name="btn-buscar" value="Buscar">            
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Nrº Documento</th>
                        <th>E-mail</th>
                        <th>Telefone</th>                        
                        <th>Sexo</th>

                    </tr>
                    
                    <c:forEach items="${cliente}" var="cli" varStatus="stat">
                        <tr>
                            <td> <c:out value="${cli.getId()}"/> </td>
                            <td> <c:out value="${cli.getNome()}"/> </td>
                            <td> <c:out value="${cli.getNumDocumento()}"/> </td>
                            <td> <c:out value="${cli.getEmail()}"/> </td>
                            <td> <c:out value="${cli.getTelefone()}"/> </td>                            
                            <td> <c:out value="${cli.getSexo()}"/> </td>
                            <td> 
                                <a href="CliCadastrar?id=<c:out value='${cli.getId()}'/>" 
                                   class="btn-alterar"> <i class="fas fa-pencil-alt"> </i> </a>
                            </td>

                            <td>
                                <a href="CliDeletar?id=<c:out value='${cli.getId()}'/>" 
                                   class="btn-delete"> <i class="fas fa-times"> </i> </a>
                            </td>
                        </tr>
                    </c:forEach>
                        
                    <tr>
                        <td> <a href="CliCadastrar?action=doGet" class="btn-incluir">Novo cadastro</a ></td>
                    </tr>
                </table>
            </form>
        </div>
     </div>
      
   <footer>
       <p>Desenvolvido por PWDA - 2018</p>
       <p>Todos os direitos reservados</p>
   </footer>

</body>
</html>
