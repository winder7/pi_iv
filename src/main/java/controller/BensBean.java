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
public class BensBean {

    private boolean readonly;

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

    private ArrayList<Bens> bens;
    private ArrayList<Empresa> empresa;
    private Map<Integer, String> ItensBoxEmpresa;

    private String botao = "Incluir";
    private String icone = "plus-circle";

    public BensBean() {
        this.bens = new ArrayList<>();
        this.empresa = new ArrayList<>();
        obter();
        setBoxEmpresa();
        this.readonly = true;
    }

    private void obter() {
        bens.clear();
        BensDAO bensDAO = new BensDAO();
        bens = bensDAO.obterBens();
    }

    public void limpaTela() {
        this.readonly = true;
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

        botao = "Incluir";
        icone = "plus-circle";
        readonly = false;
    }

    public void verificaCategoria() {
        this.readonly = (!categoria.equals("Maquina"));
    }

    public void add() {
        if (botao.equals("Incluir")) {
            BensDAO bensDAO = new BensDAO();
            valor_residual = (custo_bem * (valor_residual / 100));
            Bens b = new Bens(nome, data_compra, vida_util, valor_residual, tempo_uso, "Em uso", categoria, turno_trabalhado, fk_Empresa_id, custo_bem);
            bensDAO.inserirBem(b);
            limpaTela();
            obter();
        } else {
            BensDAO bensDAO = new BensDAO();
            valor_residual = (custo_bem * (valor_residual / 100));
            Bens b = new Bens(this.id, nome, data_compra, vida_util, valor_residual, tempo_uso, this.situacao, categoria, turno_trabalhado, fk_Empresa_id, custo_bem);
            System.out.println("this.situacao " + this.situacao);
            System.out.println("this.id " + this.id);
            bensDAO.editarBem(b);
            limpaTela();
            obter();
            botao = "Incluir";
            icone = "plus-circle";
            readonly = false;
        }
    }

    public String cancelar() {
        limpaTela();
        return "cadastrarBens";
    }

    public void editar(Bens b) {
        this.id = b.getId();
        this.nome = b.getNome();
        this.data_compra = Formatar.Data(b.getData_compra(), "dd/MM/yyyy", "yyyy-MM-dd");
        this.vida_util = b.getVida_util();
        this.novo = b.getTempo_uso() == 0;
        this.valor_residual = (b.getValor_residual() / b.getCusto_bem()) * 100;
        this.tempo_uso = b.getTempo_uso();
        this.situacao = b.getSituacao();
        this.categoria = b.getCategoria();
        System.out.println(this.categoria);
        this.readonly = !this.categoria.equals("Maquina");
        System.out.println(this.readonly);
        this.turno_trabalhado = b.getTurno_trabalhado();
        this.fk_Empresa_id = b.getFk_Empresa_id();
        this.custo_bem = b.getCusto_bem();

        readonly = true;
        botao = "Alterar";
        icone = "fa-refresh";
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

    //gets setters
    public Double getCusto_bem() {
        return custo_bem;
    }

    public void setCusto_bem(Double custo_bem) {
        this.custo_bem = custo_bem;
    }

    public ArrayList<Empresa> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ArrayList<Empresa> empresa) {
        this.empresa = empresa;
    }

    public Map<Integer, String> getItensBoxEmpresa() {
        return ItensBoxEmpresa;
    }

    public void setItensBoxEmpresa(Map<Integer, String> ItensBoxEmpresa) {
        this.ItensBoxEmpresa = ItensBoxEmpresa;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
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
