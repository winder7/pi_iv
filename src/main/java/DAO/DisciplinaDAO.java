package DAO;

import Util.Exibir;
import entities.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Alexandre Almeida / Winder Rezende
 * @Data 28/11/2018
 */
public class DisciplinaDAO {

    public void inserirDisciplina(Empresa disciplina) {
        String SQL = "INSERT INTO disciplina(codigo, nome, situacao, fk_curso_cod) VALUES (?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, disciplina.getCodigo());
            pstm.setString(2, disciplina.getNome());
            pstm.setString(3, disciplina.getSituacao());
            pstm.setInt(4, disciplina.getFk_Curso_cod());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Disciplina: " + ex);
        }
    }

    public ArrayList<Empresa> obterDisciplina() {

        ArrayList<Empresa> disciplina = new ArrayList<>();

        String SQL = "SELECT * FROM disciplina ORDER BY codigo";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Empresa disc = new Empresa(
                            rs.getString("codigo"),
                            rs.getString("nome"),
                            rs.getString("situacao"),
                            rs.getInt("fk_curso_cod")
                    );
                    disciplina.add(disc);
                }
                pstm.close();
            }
            System.out.println("Disciplinas obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Disciplinas!: \n" + ex);
        }

        return disciplina;
    }
    
    public ArrayList<Empresa> obterDisciplinaDisponivel(String fk_Aluno_cpf, int fk_Curso_cod) {

        ArrayList<Empresa> disciplina = new ArrayList<>();

        String SQL = "SELECT nome, codigo FROM disciplina WHERE codigo \n"
                + "NOT IN(SELECT d.codigo FROM matriculadisciplina m\n"
                + "LEFT JOIN disciplina d ON(m.fk_disciplina_codigo = d.codigo)\n"
                + "WHERE d.fk_curso_cod = ? AND fk_aluno_cpf = ? \n"
                + "AND (conceito != 'Insuficiente' OR conceito IS NULL)) AND fk_curso_cod = ?;";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setInt(1, fk_Curso_cod);
            pstm.setString(2, fk_Aluno_cpf);
            pstm.setInt(3, fk_Curso_cod);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Empresa disc = new Empresa(
                            rs.getString("codigo"),
                            rs.getString("nome")
                    );
                    disciplina.add(disc);
                }
                pstm.close();
            }
            System.out.println("Disciplinas disponíveis obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter disciplinas disponíveis!: \n" + ex);
        }
        return disciplina;
    }

    public ArrayList<Empresa> obterDisciplinaPorCurso(int cod_curso) {

        ArrayList<Empresa> disciplina = new ArrayList<>();

        String SQL = "SELECT * FROM disciplina WHERE fk_curso_cod = " + cod_curso + " ORDER BY codigo";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Empresa disc = new Empresa(
                            rs.getString("codigo"),
                            rs.getString("nome"),
                            rs.getString("situacao"),
                            rs.getInt("fk_curso_cod")
                    );
                    disciplina.add(disc);
                }
                pstm.close();
            }
            System.out.println("Disciplinas obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Disciplinas!: \n" + ex);
        }

        return disciplina;
    }

    public void editarDisciplina(Empresa d) {
        String SQL = "UPDATE disciplina SET codigo = ?, nome = ?, situacao = ?, fk_curso_cod = ? WHERE codigo = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, d.getCodigo());
            pstm.setString(2, d.getNome());
            pstm.setString(3, d.getSituacao());
            pstm.setInt(4, d.getFk_Curso_cod());
            pstm.setString(5, d.getCod_antigo());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar disciplina!:\n" + ex);
        }
    }

    public void removerDisciplina(Empresa disciplina) {
        String SQL = "DELETE FROM disciplina WHERE codigo = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, disciplina.getCodigo());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover disciplina: " + ex);
        }
    }
}
