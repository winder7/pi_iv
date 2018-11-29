package entities;

/**
 * @Autor Alexandre Almeida
 * @Data 28/11/2018
 */
public class Bens {

    private int id;
    private String nome;
    private String data_compra;
    private String data_baixa;
    private int vida_util;
    private boolean novo;
    private Double valor_residual;
    private int tempo_uso;
    private String situacao;
    private String categoria;
    private int turno_trabalhado;
    private int fk_Empresa_id;

    public Bens() {
    }

    public Bens(int id, String nome, String data_compra, String data_baixa, int vida_util, boolean novo, Double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.novo = novo;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
    }

    public Bens(String nome, String data_compra, String data_baixa, int vida_util, boolean novo, Double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id) {
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.novo = novo;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
    }

    public String getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(String data_baixa) {
        this.data_baixa = data_baixa;
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
    
    
}
