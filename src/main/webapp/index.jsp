<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lista de Usuários</title>
  </head>
  <body>
    <h2>Usuários</h2>
    <a href="associate">Associar Empregado a Projeto</a>
    <table border="1">
      <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
      </tr>
      <c:forEach var="empregado" items="${empregados}">
        <tr>
          <td>${empregado.id}</td>
          <td>${empregado.nome}</td>
          <td>${empregado.email}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
