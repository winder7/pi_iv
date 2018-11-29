package controller;

import DAO.EmpresaDAO;
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

    private boolean readonly = true;

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
    }

    private void obter() {
//        disciplina.clear();
//        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//        disciplina = disciplinaDAO.obterDisciplina();
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
}

public void verificaCategoria() {
        this.readonly = (!categoria.equals("Maquina"));
    }

//    public void add() {
//        if (botao.equals("Incluir")) {
//            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//            Disciplina disc = new Disciplina(codigo, nome, situacao, fk_Curso_cod);
//            disciplinaDAO.inserirDisciplina(disc);
//            limpaTela();
//            obter();
//        } else {
//            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//            Disciplina disc = new Disciplina(codigo, nome, situacao, fk_Curso_cod, cod_antigo);
//            disciplinaDAO.editarDisciplina(disc);
//            limpaTela();
//            obter();
//            botao = "Incluir";
//            icone = "plus-circle";
//            readonly = false;
//        }
//    }
    public String cancelar() {
        limpaTela();
        return "cadastrarBens";
    }

//    public void editar(Disciplina d) {
//        cod_antigo = d.getCodigo();
//        codigo = d.getCodigo();
//        nome = d.getNome();
//        situacao = d.getSituacao();
//        fk_Curso_cod = d.getFk_Curso_cod();
//        
//        readonly = true;
//        botao = "Alterar";
//        icone = "fa-refresh";
//    }
    public void remover(Bens b) {
//        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//        disciplinaDAO.removerDisciplina(d);
//        obter();
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
