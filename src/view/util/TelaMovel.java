package view.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMovel extends JFrame {

    public TelaMovel() {
        setTitle("Tela de Itens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);

        // Cria o painel personalizado com imagem de fundo e layout BorderLayout
        BackgroundPanel backgroundPanel = new BackgroundPanel("/C:/Users/darau/OneDrive/Documentos/OO/telaSecundaria.png/");
        backgroundPanel.setLayout(new BorderLayout());
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // Cria um botão para voltar à TelaPrincipal
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("/C:/Users/darau/OneDrive/Documentos/OO/iconeRetorno.png/"));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
                dispose(); // Fecha a tela atual (TelaPatrimonio)
            }
        });

        // Cria um painel para o botão no canto superior direito
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);

        // Adiciona o painel de botão ao painel de fundo
        backgroundPanel.add(buttonPanel, BorderLayout.NORTH);

        // Cria a caixa de opção
        JRadioButton adicionarButton = new JRadioButton("Adicionar");
        JRadioButton editarButton = new JRadioButton("Editar");
        JRadioButton removerButton = new JRadioButton("Remover");

        // Agrupa os botões em um ButtonGroup
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(adicionarButton);
        buttonGroup.add(editarButton);
        buttonGroup.add(removerButton);

        // Define a ação ao selecionar uma opção
        ActionListener radioButtonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (adicionarButton.isSelected()) {
                    System.out.println("Opção selecionada: Adicionar");
                } else if (editarButton.isSelected()) {
                    System.out.println("Opção selecionada: Editar");
                } else if (removerButton.isSelected()) {
                    System.out.println("Opção selecionada: Remover");
                }
            }
        };

        adicionarButton.addActionListener(radioButtonListener);
        editarButton.addActionListener(radioButtonListener);
        removerButton.addActionListener(radioButtonListener);

        // Centraliza as caixas de opção no painel de fundo
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(adicionarButton);
        centerPanel.add(editarButton);
        centerPanel.add(removerButton);

        // Adiciona o painel central ao painel de fundo
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
    }

    // Classe interna para o painel personalizado com a imagem de fundo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            // Carrega a imagem de fundo
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Desenha a imagem de fundo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1920, 1080); // Define o tamanho preferencial do painel
        }
    }
}
