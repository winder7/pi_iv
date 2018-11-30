package controller;

import DAO.BensDAO;
import DAO.EmpresaDAO;
import Util.Formatar;
import entities.Bens;
import entities.Empresa;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @Autor Alexandre Almeida
 * @Data 07/11/2018
 */
@ManagedBean
@ViewScoped
public class GerenciarBensBean {

    private boolean emUso = true;
    private boolean baixarEmpresa = false;
    private boolean editarValor = false;
    private String labelData = "Data Baixa";

    private int id;
    private String nome;
    private String data_compra;
    private int vida_util = 5;
    private boolean novo = true;
    private Double valor_residual = 10.0;
    private int tempo_uso;
    private String situacao;
    private String categoria;
    private int turno_trabalhado;
    private int fk_Empresa_id;
    private Double custo_bem = 0.0;

    private Double custo_venda;
    private String data_baixa;

    private ArrayList<Bens> bens;
    private ArrayList<Empresa> empresa;
    private Map<Integer, String> ItensBoxEmpresa;

    public GerenciarBensBean() {
        this.bens = new ArrayList<>();
        this.empresa = new ArrayList<>();
        setBoxEmpresa();
    }

    public void obter() {
        bens.clear();
        BensDAO bensDAO = new BensDAO();
        bens = bensDAO.calcularBens(fk_Empresa_id);
    }

    public void limpaTela() {
        this.id = 0;
        this.nome = null;
        this.data_compra = null;
        this.vida_util = 5;
        this.novo = true;
        this.valor_residual = 10.0;
        this.tempo_uso = 0;
        this.situacao = null;
        this.categoria = null;
        this.turno_trabalhado = 0;
        this.fk_Empresa_id = 0;
        this.custo_bem = 0.00;
        this.custo_venda = 0.0;
        this.data_baixa = null;

        bens.clear();
        baixarEmpresa = false;
    }

    public String cancelar() {
        limpaTela();
        return "gerenciarBens";
    }

    public void verificaSituacao() {
        if (this.situacao.equals("Vendido")) {
            this.editarValor = true;
        } else {
            this.editarValor = false;
            this.custo_venda = 0.0;
        }
        System.out.println(this.situacao + "  " + this.editarValor);
    }

    public void verificaData() {
        switch (this.situacao) {
            case "Em Uso":
                this.labelData = "Data da Baixa";
                this.emUso = true;
                break;
            case "Sinistro":
                this.labelData = "Data do Sinistro";
                this.emUso = false;
                break;
            case "Vendido":
                this.labelData = "Data da Venda";
                this.emUso = false;
                break;
            case "Doado":
                this.labelData = "Data da Doação";
                this.emUso = false;
                break;
        }
    }

    public void salvar() {
        BensDAO bensDAO = new BensDAO();
        Bens b = new Bens(this.id, this.data_baixa, this.situacao, this.custo_venda);
        bensDAO.baixarBem(b);
        limpaTela();
    }

    public void baixar(Bens b) {
        this.id = b.getId();
        this.nome = b.getNome();
        this.data_baixa = Formatar.Data(b.getData_baixa(), "dd/MM/yyyy", "yyyy-MM-dd");
        this.situacao = b.getSituacao();
        this.custo_venda = b.getCusto_venda();

        baixarEmpresa = true;
    }

    public void remover(Bens b) {
        BensDAO bensDAO = new BensDAO();
        bensDAO.removerBem(b);
        obter();
    }

    private void setBoxEmpresa() {
        ItensBoxEmpresa = new LinkedHashMap<>();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.obterEmpresa(LoginBean.id_logado);

        for (Empresa empresas : empresa) {
            ItensBoxEmpresa.put(0, "Selecione uma empresa");
            ItensBoxEmpresa.put(empresas.getId(), empresas.getNome());
        }
    }

    public String formatarNumero(double num) {
        return String.format("R$ " + "%,.2f", num);
    }

    //gets setters
    public Double getCusto_bem() {
        return custo_bem;
    }

    public boolean isEmUso() {
        return emUso;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    public void setCusto_bem(Double custo_bem) {
        this.custo_bem = custo_bem;
    }

    public Double getCusto_venda() {
        return custo_venda;
    }

    public void setCusto_venda(Double custo_venda) {
        this.custo_venda = custo_venda;
    }

    public ArrayList<Empresa> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ArrayList<Empresa> empresa) {
        this.empresa = empresa;
    }

    public boolean isEditarValor() {
        return editarValor;
    }

    public void setEditarValor(boolean editarValor) {
        this.editarValor = editarValor;
    }

    public String getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(String data_baixa) {
        this.data_baixa = data_baixa;
    }

    public Map<Integer, String> getItensBoxEmpresa() {
        return ItensBoxEmpresa;
    }

    public void setItensBoxEmpresa(Map<Integer, String> ItensBoxEmpresa) {
        this.ItensBoxEmpresa = ItensBoxEmpresa;
    }

    public boolean isBaixarEmpresa() {
        return baixarEmpresa;
    }

    public void setBaixarEmpresa(boolean baixarEmpresa) {
        this.baixarEmpresa = baixarEmpresa;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public int getVida_util() {
        return vida_util;
    }

    public void setVida_util(int vida_util) {
        this.vida_util = vida_util;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Double getValor_residual() {
        return valor_residual;
    }

    public void setValor_residual(Double valor_residual) {
        this.valor_residual = valor_residual;
    }

    public int getTempo_uso() {
        return tempo_uso;
    }

    public void setTempo_uso(int tempo_uso) {
        this.tempo_uso = tempo_uso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTurno_trabalhado() {
        return turno_trabalhado;
    }

    public void setTurno_trabalhado(int turno_trabalhado) {
        this.turno_trabalhado = turno_trabalhado;
    }

    public int getFk_Empresa_id() {
        return fk_Empresa_id;
    }

    public void setFk_Empresa_id(int fk_Empresa_id) {
        this.fk_Empresa_id = fk_Empresa_id;
    }

    public ArrayList<Bens> getBens() {
        return bens;
    }

    public void setBens(ArrayList<Bens> bens) {
        this.bens = bens;
    }

}
