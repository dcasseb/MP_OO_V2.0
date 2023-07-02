package view.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.util.Database;
import model.util.Filial;

public class TelaPrincipal extends JFrame {
    private Database database;
    private TelaFilial telaFilial;
    private TelaPatrimonio telaPatrimonio;
    private static TelaPrincipal instance;
    private ArrayList<Filial> filiais;

    public TelaPrincipal() {
        setTitle("Tela do Menu Principal");
        setSize(1920, 1080);
        
        telaFilial = new TelaFilial(database, null, TelaPrincipal.this);
        telaPatrimonio = new TelaPatrimonio(instance, database, filiais);

        // Cria o painel personalizado com imagem de fundo
        BackgroundPanel backgroundPanel = new BackgroundPanel("Imagens/TelaFundo.png");
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        ImageIcon buttonIcon = new ImageIcon("Imagens/iconeMenu.png");
        JButton imageButton = new JButton(buttonIcon);
        imageButton.setBorderPainted(false);
        imageButton.setContentAreaFilled(false);
        imageButton.setFocusPainted(false);
        imageButton.setOpaque(false);

        imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Filial> filiais = new ArrayList<>(); // Declare and instantiate the filiais variable

                // Ação do botão - Exibe uma caixa de seleção vertical
                String[] opcoes = {"Filiais", "Patrimônio", "Fechar"};
                int escolha = JOptionPane.showOptionDialog(TelaPrincipal.this, "Escolha uma opção",
                        "Caixa de Seleção", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
                if (escolha >= 0) {
                    System.out.println("Opção selecionada: " + opcoes[escolha]);

                    if (opcoes[escolha].equals("Filiais")) {
                        telaFilial.setVisible(true);
                        setVisible(false); // Make the TelaPrincipal frame invisible
                    } else if (opcoes[escolha].equals("Patrimônio")) {
                        TelaPatrimonio telaPatrimonio = new TelaPatrimonio(TelaPrincipal.this, database, filiais);
                        telaPatrimonio.setVisible(true);
                        setVisible(false); // Make the TelaPrincipal frame invisible
                    }
                }
            }
        });

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(imageButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Adiciona o painel de botões ao painel de fundo
        backgroundPanel.add(buttonPanel, BorderLayout.EAST);
    }
    
    public static TelaPrincipal getInstance() {
        if (instance == null) {
            instance = new TelaPrincipal();
        }
        return instance;
    }

    public static void main(String[] args) {
        TelaPrincipal example = new TelaPrincipal();
        example.setVisible(true);
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
