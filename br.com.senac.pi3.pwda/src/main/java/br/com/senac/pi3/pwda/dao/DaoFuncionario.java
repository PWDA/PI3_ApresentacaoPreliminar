package br.com.senac.pi3.pwda.dao;


import br.com.senac.pi3.pwda.db.utils.ConnectionUtils;
import br.com.senac.pi3.pwda.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaoFuncionario {

    public static boolean update(Funcionario func)
            throws SQLException, Exception {

        Connection connection = ConnectionUtils.getConnection();

        String sqlComando = "UPDATE TB_FUNCIONARIO SET NOME=?, NRDOC=?, TELEFONE=?,"
                + "DT_NASCIMENTO=?, NACIONALIDADE=?, ENDERECO=?, BAIRRO=?,"
                + "CIDADE=?, UF=?, CEP=?, EMAIL=?, SEXO=?, RE=?,"
                + "DEPARTAMENTO=?, CARGO=?, DH_ALTERACAO= NOW() WHERE (PK_ID=?)";

        PreparedStatement pst = connection.prepareStatement(sqlComando);

        try {

            pst.setString(1, func.getNome());
            pst.setString(2, func.getNumDocumento());
            pst.setString(3, func.getTelefone());

            Timestamp t = new Timestamp(func.getDataNasci().getTime());
            pst.setTimestamp(4, t);

            pst.setString(5, func.getNacionalidade());
            pst.setString(6, func.getEndereco());
            pst.setString(7, func.getBairro());
            pst.setString(8, func.getCidade());
            pst.setString(9, func.getUf());
            pst.setString(10, func.getCep());
            pst.setString(11, func.getEmail());
            pst.setString(12, func.getSexo());

            pst.setInt(13, func.getRegistro());
            pst.setString(14, func.getDepartamento());
            pst.setString(15, func.getCargo());            

            pst.setInt(16, func.getId());
            pst.execute();
        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return true;
    }

    public static boolean insert(Funcionario func)
            throws SQLException, Exception {

        Connection connection = ConnectionUtils.getConnection();

        String sqlComando = "INSERT INTO TB_FUNCIONARIO "
        + "(NOME, NRDOC, TELEFONE, DT_NASCIMENTO, NACIONALIDADE, ENDERECO, BAIRRO,"
        + "CIDADE, UF, CEP, EMAIL, SEXO, RE, DEPARTAMENTO, CARGO, DH_INCLUSAO, TG_INATIVO)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), 0);";

        PreparedStatement pst = connection.prepareStatement(sqlComando);

        try {
            if (connection != null) {

                pst.setString(1, func.getNome());
                pst.setString(2, func.getNumDocumento());
                pst.setString(3, func.getTelefone());

                Timestamp t = new Timestamp(func.getDataNasci().getTime());
                pst.setTimestamp(4, t);

                pst.setString(5, func.getNacionalidade());
                pst.setString(6, func.getEndereco());
                pst.setString(7, func.getBairro());
                pst.setString(8, func.getCidade());
                pst.setString(9, func.getUf());
                pst.setString(10, func.getCep());
                pst.setString(11, func.getEmail());
                pst.setString(12, func.getSexo());
                pst.setInt(13, func.getRegistro());
                pst.setString(14, func.getDepartamento());
                pst.setString(15, func.getCargo());                

                pst.execute();
            }
        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return true;
    }

    public static List<Funcionario> selectAll(String condicao, String situacao)
            throws SQLException, ClassNotFoundException {
        String filtro = "";
        String filtro2 = "";
        
        List<Funcionario> listarFunc = new ArrayList<Funcionario>();

        Connection connection = ConnectionUtils.getConnection();
        Statement pst = connection.createStatement();  
        
        if(condicao != null || !condicao.trim().isEmpty()){
            filtro = condicao;
        }
        
        if(situacao.trim().equals("Ativos")){
            filtro2 = " AND TG_INATIVO = 0";
        }else if(situacao.trim().equals("Inativos")){
            filtro2 = " AND TG_INATIVO = 1";
        }        
        
        ResultSet resultado = pst.executeQuery(
                "SELECT PK_ID, NOME, TELEFONE, CARGO, DEPARTAMENTO, "
                + "EMAIL FROM TB_FUNCIONARIO WHERE NOME LIKE'"+filtro+"%'"+filtro2+";");

        while (resultado.next()) {

            Funcionario func = new Funcionario();
            func.setId(resultado.getInt("PK_ID"));
            func.setNome(resultado.getString("NOME"));
            func.setTelefone(resultado.getString("TELEFONE"));
            func.setCargo(resultado.getString("CARGO"));
            func.setDepartamento(resultado.getString("DEPARTAMENTO"));
            func.setEmail(resultado.getString("EMAIL"));

            listarFunc.add(func);
        }
        if (pst != null && !pst.isClosed()) {
            pst.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        return listarFunc;
    }

    public static boolean inativar(Funcionario func)
            throws SQLException, Exception {

        String sqlComando = "UPDATE TB_FUNCIONARIO SET TG_INATIVO = 1 WHERE PK_ID = ?";

        Connection connection = ConnectionUtils.getConnection();
        PreparedStatement pst = connection.prepareStatement(sqlComando);
        pst.setInt(1, func.getId());

        try {
            pst.execute();
            pst.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean setRegistro(Funcionario func) throws Exception, ClassNotFoundException, SQLException {
        if (func.getId() == 0) {
            return insert(func);
        } else {
            return update(func);
        }
    }

    public static Funcionario getById(int id) throws Exception, ClassNotFoundException, SQLException {        
        Funcionario func = new Funcionario();

        Connection connection = ConnectionUtils.getConnection();

        Statement st = connection.createStatement();


        ResultSet rs = st.executeQuery("SELECT * FROM TB_FUNCIONARIO WHERE PK_ID = " + id);

        while (rs.next()) {                        
            func.setId(rs.getInt("PK_ID"));
            func.setNome(rs.getString("NOME").trim());
            func.setNumDocumento(rs.getString("NRDOC").trim());            
            func.setTelefone(rs.getString("TELEFONE").trim());
            func.setSexo(rs.getString("SEXO").trim());
            Date dataNascimento = rs.getDate("DT_NASCIMENTO");
            func.setDataNasci(dataNascimento);
            func.setNacionalidade(rs.getString("NACIONALIDADE").trim());
            func.setEndereco(rs.getString("ENDERECO"));
            func.setBairro(rs.getString("BAIRRO").trim());
            func.setCidade(rs.getString("CIDADE").trim());
            func.setUf(rs.getString("UF").trim());
            func.setCep(rs.getString("CEP").trim());
            func.setEmail(rs.getString("EMAIL").trim());
            func.setDepartamento(rs.getString("DEPARTAMENTO"));
            func.setRegistro(rs.getInt("RE"));
            
        }

        return func;
    }
    
}