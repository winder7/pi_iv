package entities;

/**
 * @Autor Alexandre Almeida / Winder Rezende
 * @Data 28/11/2018
 */
public class Empresa {

    private int id;
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String responsavel;
    private String data_cadastro;
    private int fk_Usuario_id_user;

    public Empresa(int id, String cnpj, String nome, String telefone, String email, String responsavel, String data_cadastro, int fk_Usuario_id_user) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.responsavel = responsavel;
        this.data_cadastro = data_cadastro;
        this.fk_Usuario_id_user = fk_Usuario_id_user;
    }

    public Empresa(String cnpj, String nome, String telefone, String email, String responsavel, int fk_Usuario_id_user) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.responsavel = responsavel;
        this.fk_Usuario_id_user = fk_Usuario_id_user;
    }



    public Empresa() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public int getFk_Usuario_id_user() {
        return fk_Usuario_id_user;
    }

    public void setFk_Usuario_id_user(int fk_Usuario_id_user) {
        this.fk_Usuario_id_user = fk_Usuario_id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
