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
    private double valor_residual;
    private int tempo_uso;
    private String situacao;
    private String categoria;
    private int turno_trabalhado;
    private int fk_Empresa_id;
    private double custo_bem;
    private double custo_venda;
    private double depreciacao;
    private double valorContabil;
    private double ganhoPerda;
    private String nome_empresa;
    private String responsavel;
    private String telefone;
    private String email;
    private double percentDepr;

    public Bens() {
    }

    public Bens(int id, String data_baixa, String situacao, double custo_venda) {
        this.id = id;
        this.data_baixa = data_baixa;
        this.situacao = situacao;
        this.custo_venda = custo_venda;
    }

    public Bens(int id, String nome, String data_compra, String data_baixa, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem, double custo_venda, double depreciacao, double valorContabil, double ganhoPerda,double percentDepr) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
        this.custo_venda = custo_venda;
        this.depreciacao = depreciacao;
        this.valorContabil = valorContabil;
        this.ganhoPerda = ganhoPerda;
        this.percentDepr = percentDepr;
    }

    public Bens(int id, String nome, String data_compra, String data_baixa, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem, double custo_venda) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
        this.custo_venda = custo_venda;
    }

    public Bens(String nome, String data_compra, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem) {
        this.nome = nome;
        this.data_compra = data_compra;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
    }

    public Bens(int id, String nome, String data_compra, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
    }

    public Bens(int id, String nome, String data_compra, String data_baixa, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem, double custo_venda, double depreciacao, double valorContabil, double ganhoPerda, String nome_empresa, String responsavel, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
        this.custo_venda = custo_venda;
        this.depreciacao = depreciacao;
        this.valorContabil = valorContabil;
        this.ganhoPerda = ganhoPerda;
        this.nome_empresa = nome_empresa;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.email = email;
    }

    public Bens(int id, String nome, String data_compra, String data_baixa, int vida_util, double valor_residual, int tempo_uso, String situacao, String categoria, int turno_trabalhado, int fk_Empresa_id, double custo_bem, double custo_venda, double depreciacao, double valorContabil, double ganhoPerda, String nome_empresa, String responsavel, String telefone, String email, double percentDepr) {
        this.id = id;
        this.nome = nome;
        this.data_compra = data_compra;
        this.data_baixa = data_baixa;
        this.vida_util = vida_util;
        this.valor_residual = valor_residual;
        this.tempo_uso = tempo_uso;
        this.situacao = situacao;
        this.categoria = categoria;
        this.turno_trabalhado = turno_trabalhado;
        this.fk_Empresa_id = fk_Empresa_id;
        this.custo_bem = custo_bem;
        this.custo_venda = custo_venda;
        this.depreciacao = depreciacao;
        this.valorContabil = valorContabil;
        this.ganhoPerda = ganhoPerda;
        this.nome_empresa = nome_empresa;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.email = email;
        this.percentDepr = percentDepr;
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

    public String getData_baixa() {
        return data_baixa;
    }

    public void setData_baixa(String data_baixa) {
        this.data_baixa = data_baixa;
    }

    public int getVida_util() {
        return vida_util;
    }

    public void setVida_util(int vida_util) {
        this.vida_util = vida_util;
    }

    public double getValor_residual() {
        return valor_residual;
    }

    public void setValor_residual(double valor_residual) {
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

    public double getCusto_bem() {
        return custo_bem;
    }

    public void setCusto_bem(double custo_bem) {
        this.custo_bem = custo_bem;
    }

    public double getCusto_venda() {
        return custo_venda;
    }

    public void setCusto_venda(double custo_venda) {
        this.custo_venda = custo_venda;
    }

    public double getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(double depreciacao) {
        this.depreciacao = depreciacao;
    }

    public double getValorContabil() {
        return valorContabil;
    }

    public void setValorContabil(double valorContabil) {
        this.valorContabil = valorContabil;
    }

    public double getGanhoPerda() {
        return ganhoPerda;
    }

    public void setGanhoPerda(double ganhoPerda) {
        this.ganhoPerda = ganhoPerda;
    }

    public String getNome_empresa() {
        return nome_empresa;
    }

    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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

    public double getPercentDepr() {
        return percentDepr;
    }

    public void setPercentDepr(double percentDepr) {
        this.percentDepr = percentDepr;
    }
}
