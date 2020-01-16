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
import model.ComposicaoCobre;
import model.Producao;

/**
 *
 * @author renato.soares
 */
public class ProducaoDAO {
    private String sql;
    LogErro erro = new LogErro();
    
    public Producao buscaItemProducao(String codMaquina){
        ConexaoDatabase db = new ConexaoDatabase();
        Producao prod = new Producao();
        try {            
            if(db.isInfoDB()){
                sql ="SELECT res.codigoitemprod,res.loteproducao,prd.met_produzida,prd.carretel_saida "
                        + "FROM condumigproducao.reservamaquina res inner join bd_sistema_monitor.tb_maquina_producao "
                        + "prd on prd.cod_maq = res.codigomaquina where res.codigomaquina = ? group by res.codigomaquina;";
                Connection conec = db.getConnection();
                PreparedStatement st = conec.prepareStatement(sql);
                st.setString(1, codMaquina);
                ResultSet res = st.executeQuery();
                if(res.next()){
                    prod.setItemProducao(res.getString("codigoitemprod"));
                    prod.setLoteProducao(res.getString("loteproducao"));
                    prod.setCarretelSaida(res.getString("carretel_saida"));
                    prod.setMetragemProduzida(res.getLong("met_produzida"));
                    db.desconectar();
                    return prod;                    
                }else{
                    System.out.println("Não ha item em produção.");
                }
            }
        } catch (SQLException e) {
            erro.gravaErro(e);
        }
        db.desconectar();
        return null;
    }
    
    public Long BuscaMetragemProduzida (String lote, String item){
        Long metragem;
        ConexaoDatabase db = new ConexaoDatabase();
        try {
            sql = "SELECT sum(metragemoperador) as met FROM condumigproducao.pesagem "
                    + "where loteproduzido = ? and codigoitem = ?;";
            Connection conec = db.getConnection();
            PreparedStatement st = conec.prepareStatement(sql);
            st.setString(1, lote);
            st.setString(2, item);
            ResultSet res = st.executeQuery();
            if(res.next()){
                metragem = res.getLong("met");
                db.desconectar();
                return metragem;
            }
        } catch (SQLException e) {
            erro.gravaErro(e);
        }
        db.desconectar();
        return null;
    }
    
    public boolean atualizaMetragemProduzida (String maquina, String metragem){
        sql = "update bd_sistema_monitor.tb_maquina_producao set met_produzida "
                + "= met_produzida + ? where cod_maq = ?;";        
        ConexaoDatabase db = new ConexaoDatabase();
        if(db.isInfoDB()){
            Connection conec = db.getConnection();
            try {
                PreparedStatement st = conec.prepareStatement(sql);
                st.setString(1, metragem);
                st.setString(2, maquina);
                st.executeUpdate();
                if(st.getUpdateCount()!=0){
                    db.desconectar();
                    return true;
                }else{
                    db.desconectar();
                    return false;
                }                                
            } catch (SQLException ex) {
                erro.gravaErro(ex);
            }            
        }
        db.desconectar();        
        return false;
    }
    
    public boolean atualizaSaldoConsumoEntrada (String cod_Pesagem, String metragem){
        sql = "update condumigproducao.pesagem set saldoconsumo = (saldoconsumo - ?) where codigo = ?";        
        ConexaoDatabase db = new ConexaoDatabase();
        if(db.isInfoDB()){
            Connection conec = db.getConnection();
            try {
                PreparedStatement st = conec.prepareStatement(sql);
                st.setString(1, metragem);
                st.setString(2, cod_Pesagem);
                st.executeUpdate();
                if(st.getUpdateCount()!=0){
                    db.desconectar();
                    return true;
                }else{
                    db.desconectar();
                    return false;
                }                                
            } catch (SQLException ex) {
                erro.gravaErro(ex);
            }            
        }
        db.desconectar();        
        return false;
    }

    public boolean atualizaCarretelSaida(String carretelSaida, String codMaquina) {
        sql = "update bd_sistema_monitor.tb_maquina_producao set carretel_saida = ? where cod_maq = ?";        
        ConexaoDatabase db = new ConexaoDatabase();
        if(db.isInfoDB()){
            Connection conec = db.getConnection();
            try {
                PreparedStatement st = conec.prepareStatement(sql);
                st.setString(1, carretelSaida);
                st.setString(2, codMaquina);
                st.executeUpdate();
                if(st.getUpdateCount()!=0){
                    db.desconectar();
                    return true;
                }else{
                    db.desconectar();
                    return false;
                }                                
            } catch (SQLException ex) {
                erro.gravaErro(ex);
            }            
        }
        db.desconectar();        
        return false;
    }
    
    public List<ComposicaoCobre> buscaComposicaoCobrePesagem(int codPesagem){
        ConexaoDatabase db = new ConexaoDatabase();
        List<ComposicaoCobre> compCobre = new ArrayList<>();
        
        try {
            if(db.isInfoDB()){
                sql = "SELECT * FROM condumigproducao.compcobrepesagem where idPesagem = ?;";
                Connection conec = db.getConnection();
                PreparedStatement st = conec.prepareStatement(sql);
                st.setInt(1, codPesagem);            
                ResultSet res = st.executeQuery();
                while(res.next()){
                    ComposicaoCobre cobre = new ComposicaoCobre();
                    cobre.setIdPesagem(codPesagem);
                    cobre.setLaminadora(res.getString("laminadora"));
                    cobre.setPorcentagem(res.getDouble("porcentagem"));
                    compCobre.add(cobre);
                }
                return compCobre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            erro.gravaErro(e);
        }
        db.desconectar();
        return null;
    }
}
