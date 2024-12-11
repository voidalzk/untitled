<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Associar Empregado a Projeto</title>
</head>
<body>
    <h2>Associar Empregado a Projeto</h2>
    <form action="associate" method="post">
        <label for="empregado">Empregado:</label>
        <select name="empregadoId" id="empregado">
            <c:forEach var="empregado" items="${empregados}">
                <option value="${empregado.id}">${empregado.nome}</option>
            </c:forEach>
        </select>
        <br/><br/>
        <label for="projetos">Projetos:</label>
        <select name="projetoIds" id="projetos" multiple>
            <c:forEach var="projeto" items="${projetos}">
                <option value="${projeto.id}">${projeto.nome}</option>
            </c:forEach>
        </select>
        <br/><br/>
        <input type="submit" value="Associar"/>
    </form>
</body>
</html>