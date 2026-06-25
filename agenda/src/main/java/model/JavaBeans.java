package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The id. */
	private String id;
	
	/** The Nome. */
	private String Nome;
	
	/** The Telefone. */
	private String Telefone;
	
	/** The Email. */
	private String Email;

	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
	}

	/**
	 * Instantiates a new java beans.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param telefone the telefone
	 * @param email the email
	 */
	public JavaBeans(String id, String nome, String telefone, String email) {
		super();
		this.id = id;
		Nome = nome;
		Telefone = telefone;
		Email = email;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return Nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		Nome = nome;
	}
	
	/**
	 * Gets the telefone.
	 *
	 * @return the telefone
	 */
	public String getTelefone() {
		return Telefone;
	}
	
	/**
	 * Sets the telefone.
	 *
	 * @param telefone the new telefone
	 */
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		Email = email;
	}
}

