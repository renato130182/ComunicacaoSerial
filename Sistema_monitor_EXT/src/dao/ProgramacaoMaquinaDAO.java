/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.LogErro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import model.ProgramacaoMaquina;
import model.Item;

/**
 *
 * @author renato.soares
 */
public class ProgramacaoMaquinaDAO {
    private String sql;
    LogErro erro = new LogErro();
    private final Connection conection;
    
    public ProgramacaoMaquinaDAO() {
        conection=null;
    }
    
    public ProgramacaoMaquinaDAO(Connection conec) {
        this.conection = conec;
    }
    public List<ProgramacaoMaquina> buscaProgramacaoMaquina(String codMaquina){
        List<ProgramacaoMaquina> lista = new ArrayList<>();
        ConexaoDatabase db = new ConexaoDatabase();
        if(db.equals(db)){
            try {
                sql = "select prog.laminadora,prog.codigo,prog.codigoitem, item.descricao, prog.loteproducao, "
                        + "prog.datacadastro,prog.quantloteprogramado,prog.metragemprogramada "
                        + ",prog.datacadastro from condumigproducao.programacaomaquina prog inner join "
                        + "condumigproducao.item on item.codigo = prog.codigoitem  "
                        + "where prog.montada = '0' and prog.situacao < 2 and "
                        + "prog.codigomaquina = ?;";
                java.sql.Connection conec = db.getConnection();
                PreparedStatement st = conec.prepareStatement(sql);
                st.setString(1, codMaquina);
                ResultSet res = st.executeQuery();
                while(res.next()){
                   ProgramacaoMaquina prog = new ProgramacaoMaquina();
                   prog.setProduto( new Produto(new Item(res.getLong("prog.codigoitem"),
                           res.getString("item.descricao").trim())));
                   prog.setLoteproducao(res.getString("prog.loteproducao"));
                   prog.setQuantidadeProgramada(res.getInt("prog.quantloteprogramado"));
                   prog.setMetragemProgramada(res.getLong("prog.metragemprogramada"));
                   prog.setDataProgramada(res.getString("prog.datacadastro"));
                   prog.setCodigoProgramacao(res.getInt("prog.codigo"));
                   prog.setLaminadora(res.getString("laminadora"));
                   lista.add(prog);
                }
                conec.close();
                return lista;
            } catch (SQLException ex) {
                erro.gravaErro(ex);
            }
        }
        return null;
    }
    public ProgramacaoMaquina buscaProgramacaoLoteItem (String lote, Long item){
        ConexaoDatabase db = new ConexaoDatabase();
        try {            
            if(db.equals(db)){
                sql = "select prog.laminadora,prog.codigo,prog.quantloteprogramado, prog.quantloteproduzido, prog.metragemprogramada, "
                        + "it.descricao,itc.minimo, itc.nominal,itc.maximo, prog.qtfiosentrada,prog.qtfiossaida from condumigproducao.programacaomaquina "
                        + "prog inner join condumigproducao.item it on it.codigo = prog.codigoitem "
                        + "inner join condumigproducao .itemitemcontrole itc on itc.codigoitem = it.codigo "
                        + "where prog.loteproducao = ? and ((prog.codigoitem = ? and (itc."
                        + "codigoitemcontrole = 1 and itc.situacao = 1)) or "
                        + "(prog.codigoitem = ? and (itc.codigoitemcontrole = 91 and itc.situacao = 1)) "
                        + "or (prog.codigoitem = ? and (itc.codigoitemcontrole = 20 and itc.situacao = 1)))"
                        + "order by itc.codigo desc limit 1;";
                    //System.out.println(sql);    
                    java.sql.Connection conec = db.getConnection();
                    PreparedStatement st = conec.prepareStatement(sql);
                    st.setString(1, lote);
                    st.setLong(2, item);
                    st.setLong(3, item);
                    st.setLong(4, item);
                    ResultSet res = st.executeQuery();
                    if(res.next()){
                       ProgramacaoMaquina prog = new ProgramacaoMaquina();
                       prog.setLoteproducao(lote);
                       
                       prog.setMetragemProgramada(res.getInt("metragemprogramada"));
                       prog.setQuantidadeProgramada(res.getInt("quantloteprogramado"));
                       prog.setQuantidadeProduzida(res.getInt("quantloteproduzido"));
                       prog.setMetragemTotalProgramada(res.getInt("metragemprogramada")*res.getInt("quantloteprogramado"));                       
                       prog.setProduto(new Produto(new Item(item,res.getString("descricao")),res.getFloat("minimo"),
                               res.getFloat("nominal"),res.getFloat("maximo")));
                       prog.setQtdFiosEntrada(res.getInt("qtfiosentrada"));
                       prog.setQtdfiosSaida(res.getInt("qtfiossaida"));
                       prog.setCodigoProgramacao(res.getInt("prog.codigo"));
                       prog.setLaminadora(res.getString("laminadora"));
                       db.desconectar();
                       return prog;
                    }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            erro.gravaErro(ex);
        }
        db.desconectar();
        return null;
    }

    public boolean setarMontagemLoteProducao(String lote, String item) {
        try {
            sql = "update condumigproducao.programacaomaquina set montada = 1 "
                    + " where loteproducao = ? and codigoitem = ?;";
            PreparedStatement st = conection.prepareStatement(sql);            
            st.setString(1,lote);
            st.setString(2,item);
            st.executeUpdate();
            return st.getUpdateCount()==1;  
        } catch (SQLException e) {
            e.printStackTrace();
            erro.gravaErro(e);
        }
        return false;
    }
}
