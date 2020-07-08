/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerEngenhariaAlternativa;
import controller.ControllerReservaMaquina;
import controller.LogErro;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.ProgramacaoMaquina;
import model.ReservaMaquina;
import controller.ControllerItem;
import controller.ControllerProducao;
import java.awt.Color;
import javax.swing.JOptionPane;
import model.Pesagem;
import model.Produto;

/**
 *
 * @author renato.soares
 */
public class JFMontagemMaquina extends javax.swing.JFrame {
    private final CanvasCarretelEntrada carEntrada;
    private final CanvasCarretelEntrada carSaida;
    private final CanvasBigBag pvc;   
    private final CanvasBigBag CoPvc; 
    private String comando="";
    private ProgramacaoMaquina prog;
    private List<ReservaMaquina> resMaq;
    
    private LogErro erro = new LogErro();

    public void setProg(ProgramacaoMaquina prog) {
        this.prog = prog;
        jTAProdutoSaida.setText("Codigo: " + prog.getProduto().item.getCodigo() +
                "\n " + prog.getProduto().item.getDescricao() + " \n Lote: " +
                prog.getLoteproducao());
    }
    /**
     * Creates new form JFMontagemMaquina
     * @param codigoMaquina
     */
    public JFMontagemMaquina(String codigoMaquina) {
        initComponents();
        prog = new ProgramacaoMaquina();
        carEntrada = new CanvasCarretelEntrada();        
        carSaida = new CanvasCarretelEntrada(); 
        resMaq = new ArrayList<>();
        jPanelCarEntrada.add(carEntrada);
        jPanelCarSaida.add(carSaida);
        pvc = new CanvasBigBag();
        CoPvc = new CanvasBigBag();
        jPanelExtPrincipal.add(pvc);
        jPanelCoExt.add(CoPvc);                        
        CardLayout card = (CardLayout) root.getLayout();
        card.show(root,"jPanel1");
        buscaReservaMaquina(codigoMaquina);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        root = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanelCarEntrada = new javax.swing.JPanel();
        jPanelExtPrincipal = new javax.swing.JPanel();
        jPanelCoExt = new javax.swing.JPanel();
        jPanelCarSaida = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAPvcExtrusado = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAProdutoSaida = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTACoExtrusaoPigmento = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTACarretelEntrada = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Montagem de Máquina");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        root.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelCarEntrada.setToolTipText("Carretel de entrada");
        jPanelCarEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelCarEntrada.setEnabled(false);
        jPanelCarEntrada.setLayout(new java.awt.BorderLayout());

        jPanelExtPrincipal.setEnabled(false);
        jPanelExtPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCoExt.setEnabled(false);
        jPanelCoExt.setLayout(new java.awt.BorderLayout());

        jPanelCarSaida.setEnabled(false);
        jPanelCarSaida.setLayout(new java.awt.BorderLayout());

        jTAPvcExtrusado.setColumns(20);
        jTAPvcExtrusado.setRows(5);
        jTAPvcExtrusado.setBorder(javax.swing.BorderFactory.createTitledBorder("Extrusora Principal"));
        jTAPvcExtrusado.setFocusable(false);
        jTAPvcExtrusado.setPreferredSize(new java.awt.Dimension(190, 115));
        jScrollPane2.setViewportView(jTAPvcExtrusado);

        jTAProdutoSaida.setColumns(20);
        jTAProdutoSaida.setRows(5);
        jTAProdutoSaida.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));
        jTAProdutoSaida.setFocusable(false);
        jScrollPane3.setViewportView(jTAProdutoSaida);

        jTACoExtrusaoPigmento.setColumns(20);
        jTACoExtrusaoPigmento.setRows(5);
        jTACoExtrusaoPigmento.setBorder(javax.swing.BorderFactory.createTitledBorder("Co-Extrusora "));
        jTACoExtrusaoPigmento.setFocusable(false);
        jScrollPane4.setViewportView(jTACoExtrusaoPigmento);

        jTACarretelEntrada.setColumns(20);
        jTACarretelEntrada.setRows(5);
        jTACarretelEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("Carretel Entrada"));
        jTACarretelEntrada.setFocusable(false);
        jScrollPane5.setViewportView(jTACarretelEntrada);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelCoExt, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanelExtPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jPanelCarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelCarSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelCarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanelExtPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelCoExt, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4))
                .addContainerGap(233, Short.MAX_VALUE))
        );

        root.add(jPanel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        //System.out.println("key pressed");
        try {                    
            if(evt.getKeyCode()!=16) {
                if(evt.getKeyCode()!=10){
                    comando = comando + String.valueOf(evt.getKeyChar());               
                }else{                
    //                System.out.println("KeyChar: " + evt.getKeyChar());
    //                System.out.println("KeyCode: " + evt.getKeyCode());                
                    if(comando.length()==10){
                        System.out.println("Materia prima: " + comando);
                        comandoTrocaMateriaPrima(comando);                
                    }else{
                        if(comando.length()>0){
                            System.out.println("Pesagem : " + comando);
                            comandoTrocaCarretelEntrada(Integer.valueOf(comando));
                        }
                    }
                    comando="";
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            erro.gravaErro(e);
            comando="";
        }
    }//GEN-LAST:event_formKeyPressed

//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JFMontagemMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFMontagemMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFMontagemMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFMontagemMaquina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFMontagemMaquina().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCarEntrada;
    private javax.swing.JPanel jPanelCarSaida;
    private javax.swing.JPanel jPanelCoExt;
    private javax.swing.JPanel jPanelExtPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTACarretelEntrada;
    private javax.swing.JTextArea jTACoExtrusaoPigmento;
    private javax.swing.JTextArea jTAProdutoSaida;
    private javax.swing.JTextArea jTAPvcExtrusado;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel root;
    // End of variables declaration//GEN-END:variables

    private boolean buscaReservaMaquina(String codigoMaquina) {
        try {
            ControllerReservaMaquina res = new ControllerReservaMaquina();
            resMaq = res.buscaListaReservaMaquina(codigoMaquina);
            if(resMaq!=null){
                preenchedadosExtrusoraPrincipal();
                preencheDadosCoExtrusora();
                preencheDadosCarretelEntrada();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            erro.gravaErro(e);
        }
        JOptionPane.showMessageDialog(root, "Não foi possivel buscar dados da montagem da maquina."
                + "\nPor favor verifique e tente novamente."
                + "\nSe o problema persistir procure o setor de informática.","Falha ao buscar a montagem",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private void preenchedadosExtrusoraPrincipal() {       
        ControllerItem ctr = new ControllerItem();
        Item pvc = new Item();
        for(ReservaMaquina r : resMaq){
            if(r.getTipoExtrusao().trim().equals("1")){
                pvc = ctr.BuscaDadosItem(Long.valueOf(r.getCodItemRes()));                
                jTAPvcExtrusado.setText("Codigo Item: " + r.getCodItemRes()+
                        "\n" + pvc.getDescricao() + 
                        "\nLote Interno: " + r.getLoteItemRes() +
                        "\nSaldo: " + r.getQuantItemRes());                
            }
        }
    }

    private void preencheDadosCoExtrusora() {
        String dados ="";
        ControllerItem ctr = new ControllerItem();
        Item pvcPig = new Item();
        
        for(ReservaMaquina r : resMaq){
            if(r.getTipoExtrusao().trim().equals("2") || r.getCodItemRes().trim().substring(0,4).equals("2003")){
                pvcPig = ctr.BuscaDadosItem(Long.valueOf(r.getCodItemRes()));
                dados = dados + "Codigo Item: " + r.getCodItemRes() +
                        "\n" + pvcPig.getDescricao() +
                        "\nLote Interno: " + r.getLoteItemRes()+
                        "\nSaldo: " + r.getQuantItemRes()+
                        "\n";
            }
        }
        jTACoExtrusaoPigmento.setText(dados);
    }

    private void preencheDadosCarretelEntrada() {
        Pesagem pes = new Pesagem();        
        ControllerProducao ctr = new ControllerProducao();        
        for(ReservaMaquina r : resMaq){
            if(r.getPesagem()!=0){
                pes = ctr.BuscaDadosProducaoPesagem(r.getPesagem());
                jTACarretelEntrada.setText("Numero pesagem: " + r.getPesagem()+
                        "\nCodigo Item: " + pes.getCodItem()+
                        "\n" + pes.getDecItem()+
                        "\nLote: " + pes.getLote() +
                        "\nSaldo: " + pes.getSaldoConsumo());                                   
            }
        }
    }

    private void comandoTrocaMateriaPrima(String lote) {
        long codReservaMaquina=0;
        String tipoExtrusao="";
        long codigoItem=0;
        for(ReservaMaquina r : resMaq){
            if(r.getLoteItemRes().equals(lote)){
                codReservaMaquina = r.getCodigoReserva();
                tipoExtrusao = r.getTipoExtrusao();
                codigoItem = Long.valueOf(r.getCodItemRes());
                break;
            }                    
        }
        if(codReservaMaquina!=0){
            if(tipoExtrusao.equals("1")){
                jTAPvcExtrusado.setBackground(Color.red);
                String novoLote = JOptionPane.showInputDialog(root,"Informe o novo lote de matéria prima");
                if(novoLote==null || novoLote.isEmpty()){
                    jTAPvcExtrusado.setBackground(Color.WHITE);
                    return;
                }
                ControllerReservaMaquina ctrItem = new ControllerReservaMaquina();
                Item itemEntrando = new Item();
                itemEntrando = ctrItem.buscaItemMateriaPrima(novoLote);
                if(itemEntrando==null){
                    JOptionPane.showMessageDialog(root,"Não foi possivel buscar o item do lote da matéria prima digitada"
                            + "\nPor favor tente novamente."
                            + "\nSe o problema persistir procure o setor de informatica.","Falha ao busca item",JOptionPane.ERROR_MESSAGE);
                    jTAPvcExtrusado.setBackground(Color.yellow);
                    return;
                }
                if(codigoItem==itemEntrando.getCodigo() ||
                        validaEngenharia(itemEntrando,String.valueOf(codigoItem))){
                    if(atualizaReservaMaquina(codReservaMaquina,String.valueOf(itemEntrando.getCodigo()),novoLote)){
                        jTAPvcExtrusado.setBackground(Color.green);
                        preenchedadosExtrusoraPrincipal();
                    }else{
                        JOptionPane.showMessageDialog(root,"Falha ao registrar item para atualização da montagem da maquina"
                            + "\nPor favor tente novamente."
                            + "\nSe o problema persistir procure o setor de informatica.","Falha ao atualizar lista de montagem da maquina",
                            JOptionPane.ERROR_MESSAGE);
                    jTAPvcExtrusado.setBackground(Color.yellow);
                    }
                }else{
                    JOptionPane.showMessageDialog(root,"O item escolhido não corresponde a engenharia de produto"
                            + "\nPor favor informe ao setor de engenharia de produção"
                            + "\nSe o problema persistir procure o setor de informatica.","Enenharia de Produção incorreta",JOptionPane.ERROR_MESSAGE);
                    jTAPvcExtrusado.setBackground(Color.yellow);
                }
            }else{
                jTACoExtrusaoPigmento.setBackground(Color.red);
                String novoLote = JOptionPane.showInputDialog(root,"Informe o novo lote de matéria prima");
                if(novoLote==null || novoLote.isEmpty()){
                    jTACoExtrusaoPigmento.setBackground(Color.WHITE);
                    return;
                }
                ControllerReservaMaquina ctrItem = new ControllerReservaMaquina();
                Item itemEntrando = new Item();
                itemEntrando = ctrItem.buscaItemMateriaPrima(novoLote);
                if(itemEntrando==null){
                    JOptionPane.showMessageDialog(root,"Não foi possivel buscar o item do lote da matéria prima digitada"
                            + "\nPor favor tente novamente."
                            + "\nSe o problema persistir procure o setor de informatica.","Falha ao busca item",JOptionPane.ERROR_MESSAGE);
                    jTACoExtrusaoPigmento.setBackground(Color.yellow);
                    return;
                }
                if(codigoItem==itemEntrando.getCodigo() ||
                        validaEngenharia(itemEntrando,String.valueOf(codigoItem))){
                    if(atualizaReservaMaquina(codReservaMaquina,String.valueOf(itemEntrando.getCodigo()),novoLote)){
                        jTACoExtrusaoPigmento.setBackground(Color.green);
                        preencheDadosCoExtrusora();
                    }else{
                        JOptionPane.showMessageDialog(root,"Falha ao registrar item para atualização da montagem da maquina"
                            + "\nPor favor tente novamente."
                            + "\nSe o problema persistir procure o setor de informatica.","Falha ao atualizar lista de montagem da maquina",
                            JOptionPane.ERROR_MESSAGE);
                    jTACoExtrusaoPigmento.setBackground(Color.yellow);
                    }
                }else{
                    JOptionPane.showMessageDialog(root,"O item escolhido não corresponde a engenharia de produto"
                            + "\nPor favor informe ao setor de engenharia de produção"
                            + "\nSe o problema persistir procure o setor de informatica.","Enenharia de Produção incorreta",JOptionPane.ERROR_MESSAGE);
                    jTACoExtrusaoPigmento.setBackground(Color.yellow);
                }
            }
        }else{
            JOptionPane.showMessageDialog(root,"Lote interno não encontrado na montagem da maquina. "
                    + "\nPor fvor verifique e tente novamente","Lote Interno não encontrado",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comandoTrocaCarretelEntrada(int pesgem) {
        long codReservaMquina=0;
        for(ReservaMaquina r : resMaq){
            if(r.getPesagem()==pesgem){
                codReservaMquina = r.getCodigoReserva();                
                break;
            }                    
        }
        if(codReservaMquina!=0){
            jTACarretelEntrada.setBackground(Color.red);
            
        }else{
            JOptionPane.showMessageDialog(root,"Numero da pesagem não encontrada na montagem da maquina."
                    + "\nPor fvor verifique e tente novamente","Numero pesagem não encontrada",JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validaEngenharia(Item itemEntrando,String itemSaindo) {
        List<Produto> prods = new ArrayList<>();
        ControllerEngenhariaAlternativa eng = new ControllerEngenhariaAlternativa();
            prods = eng.buscaListaAlternativas(itemSaindo,String.valueOf(prog.getProduto().item.getCodigo()));
            if(prods!=null){
                for(Produto p : prods){
                    if(p.item.getCodigo()==itemEntrando.getCodigo()){
                        return true;
                    }
                }
            }
        return false;
    }

    private boolean atualizaReservaMaquina(long codReservaMaquina,String codItementrando, 
            String lote) {
        ControllerReservaMaquina ctr = new ControllerReservaMaquina();
        try {
            for(ReservaMaquina r : resMaq){
                if(r.getCodigoReserva()==codReservaMaquina){
                    r.setCodItemRes(codItementrando);
                    r.setLoteItemRes(lote);
                    r.setQuantItemRes(ctr.buscaSaldoCansumoMP(lote));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            erro.gravaErro(e);
        }
        return false;
    }
}
