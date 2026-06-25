package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * Classe controladora que gerencia as requisições da agenda de contatos.
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})
public class Controller extends HttpServlet {
	
	/** Identificador de versão para a serialização da classe. */
	private static final long serialVersionUID = 1L;
	
	/** Objeto de acesso a dados (Data Access Object) para interagir com o banco. */
	DAO dao = new DAO();
	
	/** Objeto que representa os dados de um contato. */
	JavaBeans contato = new JavaBeans();
       
	/**
     * Construtor padrão da classe Controller.
     */
    public Controller() {
        super();
    }

    /**
	 * Método que intercepta e direciona as requisições HTTP GET.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		if(action.equals("/main")) {
			contatos(request, response);
		}else if(action.equals("/insert")) {
			adicionarContato(request, response);
		}else if(action.equals("/select")) {
			listarContato(request, response);
		}else if(action.equals("/update")) {
			editarContato(request, response);
		}else if(action.equals("/delete")) {
			deleteContato(request, response);
		}else if(action.equals("/report")) {
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	
	/**
	 * Lista todos os contatos e os encaminha para a página da agenda.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		ArrayList<JavaBeans> lista = dao.listarContato();
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request .getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Recebe os dados do formulário e insere um novo contato no banco de dados.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		response.sendRedirect("main");
	}
	
	/**
	 * Busca os dados de um contato específico para exibição na página de edição.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contato.setId(request.getParameter("id"));
	
		dao.selecionarContato(contato);
		
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("email", contato.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Grava as alterações feitas nos dados de um contato existente.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.alterarContato(contato);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Remove um contato do banco de dados com base no seu ID.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void deleteContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		contato.setId(request.getParameter("id"));
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}
	
	/**
	 * Gera um relatório em formato PDF contendo a lista completa de contatos.
	 *
	 * @param request Objeto que contém a requisição do cliente
	 * @param response Objeto que contém a resposta enviada ao cliente
	 * @throws ServletException Caso ocorra uma exceção específica do servlet
	 * @throws IOException Caso ocorra um erro de entrada ou saída de dados
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Document documento = new Document();
		try {
			response.setContentType("aplication/pdf");
			response.addHeader("Content-Disposition", "inline; fileName="
					+ "contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			ArrayList<JavaBeans> lista = dao.listarContato();
			for(int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelefone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch(Exception e) {
			e.printStackTrace();
			documento.close();
		}
	}
}