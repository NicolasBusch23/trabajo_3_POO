package Ejercicio_8_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase principal de la interfaz gráfica que extiende de JFrame. 
 * Gestiona la captura de datos de las notas y despliega las estadísticas.
 */
public class Principal extends JFrame {

    // Componentes para la entrada de datos
    private JTextField[] txtNotas;
    private JButton btnCalcular;

    // Componentes para mostrar resultados
    private JLabel lblPromedioValor;
    private JLabel lblDesviacionValor;
    private JLabel lblMayorValor;
    private JLabel lblMenorValor;

    public Principal() {
        // Configuración básica de la ventana (JFrame)
        setTitle("Cálculo de Notas Estudiantiles - Ejercicio 8.2");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior: Ingreso de datos ---
        JPanel panelEntrada = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createTitledBorder("Ingrese las 5 notas:"));
        
        txtNotas = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            panelEntrada.add(new JLabel("  Nota " + (i + 1) + ":"));
            txtNotas[i] = new JTextField();
            panelEntrada.add(txtNotas[i]);
        }

        // --- Panel Central: El botón de acción ---
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCalcular = new JButton("Calcular Resultados");
        panelBoton.add(btnCalcular);

        // --- Panel Inferior: Resultados ---
        JPanel panelResultados = new JPanel(new GridLayout(4, 2, 5, 5));
        panelResultados.setBorder(BorderFactory.createTitledBorder("Estadísticas de rendimiento:"));

        panelResultados.add(new JLabel("  Promedio:"));
        lblPromedioValor = new JLabel("-");
        panelResultados.add(lblPromedioValor);

        panelResultados.add(new JLabel("  Desviación Estándar:"));
        lblDesviacionValor = new JLabel("-");
        panelResultados.add(lblDesviacionValor);

        panelResultados.add(new JLabel("  Nota Mayor:"));
        lblMayorValor = new JLabel("-");
        panelResultados.add(lblMayorValor);

        panelResultados.add(new JLabel("  Nota Menor:"));
        lblMenorValor = new JLabel("-");
        panelResultados.add(lblMenorValor);

        // Agregar los sub-contenedores al JFrame
        add(panelEntrada, BorderLayout.NORTH);
        add(panelBoton, BorderLayout.CENTER);
        add(panelResultados, BorderLayout.SOUTH);

        // --- GESTIÓN DE EVENTOS ---
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarCalculos();
            }
        });
    }

    private void ejecutarCalculos() {
        try {
            double[] valoresNotas = new double[5];
            
            // Validación y lectura de datos
            for (int i = 0; i < 5; i++) {
                String texto = txtNotas[i].getText().trim();
                if (texto.isEmpty()) {
                    throw new IllegalArgumentException("Por favor, llene todas las casillas.");
                }
                valoresNotas[i] = Double.parseDouble(texto.replace(',', '.'));
                
                if (valoresNotas[i] < 0 || valoresNotas[i] > 5) {
                    throw new IllegalArgumentException("Las notas deben estar entre 0 y 5.");
                }
            }

            // Invocamos la clase de lógica del mismo paquete
            ProcesadorNotas procesador = new ProcesadorNotas(valoresNotas);

            // Mostramos los resultados formateados
            lblPromedioValor.setText(String.format("%.2f", procesador.calcularPromedio()));
            lblDesviacionValor.setText(String.format("%.2f", procesador.calcularDesviacionEstandar()));
            lblMayorValor.setText(String.format("%.2f", procesador.obtenerMayorNota()));
            lblMenorValor.setText(String.format("%.2f", procesador.obtenerMenorNota()));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error: Uno o más campos no contienen un número válido.", 
                "Error de formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Datos inválidos", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    // Método Main para iniciar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
}