<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Consultar Funcionários</title>
    <link rel="stylesheet" href="./css/consultar-funcionario.css">
    <link rel="stylesheet" href="../css/consultar-funcionario.css">
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
           
            <input type="hidden" value="${erro}" id="erroMsg" name="erroMsg" />
           
            <form action="${pageContext.request.contextPath}/FuncConsultar" method="post">
                <h1>Consulta de Funcionários</h1>                                                
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
                        <th>E-mail</th>
                        <th>Telefone</th>
                        <th>Cargo</th>
                        <th>Departamento</th>

                    </tr>

                    <c:forEach items="${funcionario}" var="func" varStatus="stat">
                        <tr>
                            <td> <c:out value="${func.getId()}"/> </td>
                            <td> <c:out value="${func.getNome()}"/> </td>
                            <td> <c:out value="${func.getEmail()}"/> </td>
                            <td> <c:out value="${func.getTelefone()}"/> </td>
                            <td> <c:out value="${func.getCargo()}"/> </td>
                            <td> <c:out value="${func.getDepartamento()}"/> </td>
                            <td> 
                                <a href="FuncCadastrar?id=<c:out value='${func.getId()}'/>" 
                                   class="btn-alterar"> <i class="fas fa-pencil-alt"> </i> </a>
                            </td>

                            <td>
                                <a href="FuncDeletar?id=<c:out value='${func.getId()}'/>" 
                                   class="btn-delete"> <i class="fas fa-times"> </i> </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>                        
                        <td> <a href="FuncCadastrar?action=doGet" class="btn-incluir">Novo cadastro</a ></td>
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
