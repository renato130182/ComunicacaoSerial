/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;
import model.ProgramacaoMaquina;

/**
 *
 * @author renato.soares
 */
public class ProgramacaoMaquinaDAO {
    private String sql;
    
    public List<ProgramacaoMaquina> buscaProgramacaoMaquina(String codMaquina){
        List<ProgramacaoMaquina> lista = new ArrayList<ProgramacaoMaquina>();
        ConexaoDatabase db = new ConexaoDatabase();
        if(db.equals(db)){
            try {
                sql = "select prog.codigoitem, item.descricao, prog.loteproducao, "
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
                   prog.setProduto( new Produto(res.getString("prog.codigoitem"),
                           res.getString("item.descricao").trim()));
                   prog.setLoteproducao(res.getString("prog.loteproducao"));
                   prog.setQuantidadeProgramada(res.getInt("prog.quantloteprogramado"));
                   prog.setMetragemProgramada(res.getLong("prog.metragemprogramada"));
                   prog.setDataProgramada(res.getString("prog.datacadastro"));
                   lista.add(prog);
                }
                return lista;
            } catch (SQLException ex) {
                Logger.getLogger(ProgramacaoMaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    public ProgramacaoMaquina buscaProgramacaoLoteItem (String lote, String item){
        ConexaoDatabase db = new ConexaoDatabase();
        try {            
            if(db.equals(db)){
                sql = "select prog.quantloteprogramado, prog.quantloteproduzido, prog.metragemprogramada, "
                        + "it.descricao,itc.minimo, itc.nominal,itc.maximo from condumigproducao.programacaomaquina "
                        + "prog inner join condumigproducao.item it on it.codigo = prog.codigoitem "
                        + "inner join condumigproducao .itemitemcontrole itc on itc.codigoitem = it.codigo "
                        + "where prog.loteproducao = ? and prog.codigoitem = ? and itc. "
                        + "codigoitemcontrole = 1 and itc.situacao = 1 order by itc.codigo desc;";
                    java.sql.Connection conec = db.getConnection();
                    PreparedStatement st = conec.prepareStatement(sql);
                    st.setString(1, lote);
                    st.setString(2, item);
                    ResultSet res = st.executeQuery();
                    if(res.next()){
                       ProgramacaoMaquina prog = new ProgramacaoMaquina();
                       prog.setLoteproducao(lote);
                       
                       prog.setMetragemProgramada(res.getInt("metragemprogramada"));
                       prog.setQuantidadeProgramada(res.getInt("quantloteprogramado"));
                       prog.setQuantidadeProduzida(res.getInt("quantloteproduzido"));
                       prog.setMetragemTotalProgramada(res.getInt("metragemprogramada")*res.getInt("quantloteprogramado"));                       
                       prog.setProduto(new Produto(item,res.getString("descricao"),res.getFloat("minimo"),
                               res.getFloat("nominal"),res.getFloat("maximo")));
                       db.desconectar();
                       return prog;
                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProgramacaoMaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.desconectar();
        return null;
    }
}
