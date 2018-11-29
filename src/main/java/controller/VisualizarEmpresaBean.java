package controller;

import DAO.EmpresaDAO;
import entities.Empresa;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@ViewScoped
public class VisualizarEmpresaBean {
    private int id_selec;
    
    private int id;
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String responsavel;
    private String data_cadastro;
    private int fk_Usuario_id_user;
    
    private Empresa empresa;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public VisualizarEmpresaBean() {
        obter();
    }

    private void obter() {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.visualizarEmpresa(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empresaId")));
        this.id = empresa.getId();
        this.cnpj = empresa.getCnpj();
        this.nome = empresa.getNome();
        this.telefone = empresa.getTelefone();
        this.email = empresa.getEmail();
        this.responsavel = empresa.getResponsavel();
    }
    
    
    public void limpaTela() {
        this.id = 0;
        this.cnpj = null;
        this.nome = null;
        this.telefone = null;
        this.email = null;
        this.responsavel = null;
        this.data_cadastro = null;
        this.fk_Usuario_id_user = 0;
    }

    public String voltar() {
        limpaTela();
        return "cadastrarBens";
    }


    //Getters e Seters
    public int getId_selec() {
        return id_selec;
    }

    public void setId_selec(int id_selec) {
        this.id_selec = id_selec;
    }
       
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    
    public String getBotao() {
        return botao;
    }

    public void setBotao(String botao) {
        this.botao = botao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
