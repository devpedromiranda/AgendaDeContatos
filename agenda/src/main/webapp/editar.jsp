<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
	<title>Agenda de contatos</title>
	<link rel="icon" href="imagens/agenda.png">
	<link rel="stylesheet" href="style.css">
</head>

<body>
	<h1>Editar Contato</h1>

	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly 
				value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
				value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="telefone" class="Caixa2"
				value="<%out.println(request.getAttribute("telefone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1"
				value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>

</html>