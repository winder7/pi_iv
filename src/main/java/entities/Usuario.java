package entities;

/**
 * @Autor Alexandre Almeida / Winder Rezende
 * @Data 28/11/2018
 */
public class Usuario {

    private int id_user;
    private String login;
    private String senha;
    private String situacao;
    private String data_cad;
    private String email;

    public Usuario() {
    }

    public Usuario(String login, String senha, String situacao, String email) {
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
        this.email = email;
    }
    
    public Usuario(int id_user, String login, String senha, String situacao, String email) {
        this.id_user = id_user;
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
        this.email = email;
    }

    public Usuario(int id_user, String login, String senha, String situacao, String data_cad, String email) {
        this.id_user = id_user;
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
        this.data_cad = data_cad;
        this.email = email;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getData_cad() {
        return data_cad;
    }

    public void setData_cad(String data_cad) {
        this.data_cad = data_cad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
