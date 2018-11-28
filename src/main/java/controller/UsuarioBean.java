package controller;

import DAO.UsuarioDAO;
import Util.Relatorio;
import entities.Usuario;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Winder Rezende
 * @Data  04/11/2018, 23:41:56
 */
@ManagedBean
@ViewScoped
public class UsuarioBean {

    private int id_user;
    private String login;
    private String senha;
    private String situacao = "true";
    private ArrayList<Usuario> usuarios;
    private String email;
    Usuario usr;
    UsuarioDAO usrDao;
    private boolean editar;
    
    public UsuarioBean() {
        usuarios = new ArrayList<>();
        usr = new Usuario();
        usrDao = new UsuarioDAO();
        obter();
    }
    
    public String cancelar(){
        login = null;
        senha = "senha";
        situacao = "true";
        email = null;
        editar = false;
        return ("cadastrarUsuario");
    }
    
    private void obter(){
        usuarios = usrDao.obterUsuarios();
    }
    
    public void add(){
        usr = new Usuario(login, senha, situacao, email);
        usrDao.inserirUsuario(usr);
        obter();
        cancelar();
    }
    
    public void iniciaEditar(Usuario lista) {
        editar = true;
        id_user = lista.getId_user();
        login = lista.getLogin();
        senha = lista.getSenha().equals("Privada") ? "" : "senha" ;
        situacao = lista.getSituacao().equals("Ativo") ? "true" : "false";
        email = lista.getEmail();
    }
    
    public void alterar(){
        usr = new Usuario(id_user, login, senha, situacao, email);
        usrDao.alterarUsuario(usr);
        editar = false;
        obter();
        cancelar();
    }

    public void remover(Usuario lista) {
        usrDao.apagarUsuario(lista.getId_user());
        obter();
    }
    
    public void gerarRelatorio(){
        Relatorio gerar = new Relatorio();
        gerar.getRelatorio(usuarios);
    }

    //Getters e Seters
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

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
