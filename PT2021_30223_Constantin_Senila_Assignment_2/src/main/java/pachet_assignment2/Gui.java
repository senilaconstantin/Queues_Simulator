package pachet_assignment2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Gui {

    private final JFrame frame;
    private final JTextField nrClients= new JTextField();
    private final JTextField nrQueues= new JTextField();
    private final JTextField nrSimulationTime= new JTextField();
    private final JTextField nrMinArrivalTime= new JTextField();
    private final JTextField nrMaxArrivalTime= new JTextField();
    private final JTextField nrMinServicelTime= new JTextField();
    private final JTextField nrMaxServicelTime= new JTextField();
    private final JLabel tittluLab = new JLabel("Queue simulator");
    private final JLabel clLab = new JLabel("Clients");
    private final JLabel qLab = new JLabel("Queues");
    private final JLabel simTimeLab = new JLabel("Simulation Time");
    private final JLabel minArrivalLabel = new JLabel("Minimum Arrival Time");
    private final JLabel maxArrivalLabel = new JLabel("Maximum Arrival Time");
    private final JLabel minServiceLabel = new JLabel("Minimum Service Time");
    private final JLabel maxServiceLabel = new JLabel("Maximum Service Time");
    private final JButton btnStart = new JButton("Start");
    private final JLabel lblFileOutName = new JLabel("File Out Name");
    private final JTextField nameFileText = new JTextField();

    public Gui() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(100, 100, 662, 451);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        initLabel();
        initialize();
        numeFis();
        buton();
    }


    private void initLabel() {
        tittluLab.setHorizontalAlignment(SwingConstants.CENTER);
        tittluLab.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        tittluLab.setForeground(Color.BLACK);
        tittluLab.setBounds(10, 10, 628, 45);
        frame.getContentPane().add(tittluLab);
        clLab.setForeground(Color.BLACK);
        clLab.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        clLab.setBounds(10, 89, 69, 27);
        frame.getContentPane().add(clLab);
        qLab.setForeground(Color.BLACK);
        qLab.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        qLab.setBounds(247, 89, 69, 27);
        frame.getContentPane().add(qLab);
        simTimeLab.setForeground(Color.BLACK);
        simTimeLab.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        simTimeLab.setBounds(448, 89, 128, 27);
        frame.getContentPane().add(simTimeLab);
        minArrivalLabel.setForeground(Color.BLACK);
        minArrivalLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        minArrivalLabel.setBounds(10, 158, 179, 27);
        frame.getContentPane().add(minArrivalLabel);
        maxServiceLabel.setForeground(Color.BLACK);
        maxServiceLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        maxServiceLabel.setBounds(380, 225, 179, 27);
        frame.getContentPane().add(maxServiceLabel);
        maxArrivalLabel.setForeground(Color.BLACK);
        maxArrivalLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        maxArrivalLabel.setBounds(380, 158, 179, 27);
        frame.getContentPane().add(maxArrivalLabel);
        minServiceLabel.setForeground(Color.BLACK);
        minServiceLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        minServiceLabel.setBounds(10, 225, 179, 27);
        frame.getContentPane().add(minServiceLabel);
        lblFileOutName.setForeground(Color.BLACK);
        lblFileOutName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
        lblFileOutName.setBounds(109, 287, 122, 27);
        frame.getContentPane().add(lblFileOutName);
    }

    public String getNrClients() {
        return nrClients.getText();
    }

    public String getNrQueues() {
        return nrQueues.getText();
    }

    public String getNrSimulationTime() {
        return nrSimulationTime.getText();
    }

    public String getNrMinArrivalTime() {
        return nrMinArrivalTime.getText();
    }

    public String getNrMaxArrivalTime() {
        return nrMaxArrivalTime.getText();
    }

    public String getNrMinServicelTime() {
        return nrMinServicelTime.getText();
    }

    public String getNrMaxServicelTime() {
        return nrMaxServicelTime.getText();
    }

    public String getNameFileText() {
        return nameFileText.getText();
    }

    private void initialize() {
        nrClients.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrClients.setForeground(Color.BLACK);
        nrClients.setBounds(69, 91, 69, 24);
        frame.getContentPane().add(nrClients);
        nrClients.setColumns(10);
        nrQueues.setForeground(Color.BLACK);
        nrQueues.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrQueues.setColumns(10);
        nrQueues.setBounds(312, 91, 69, 24);
        frame.getContentPane().add(nrQueues);
        nrSimulationTime.setForeground(Color.BLACK);
        nrSimulationTime.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrSimulationTime.setColumns(10);
        nrSimulationTime.setBounds(572, 91, 66, 24);
        frame.getContentPane().add(nrSimulationTime);
        nrMinArrivalTime.setForeground(Color.BLACK);
        nrMinArrivalTime.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrMinArrivalTime.setColumns(10);
        nrMinArrivalTime.setBounds(199, 160, 69, 24);
        frame.getContentPane().add(nrMinArrivalTime);
        nrMaxArrivalTime.setForeground(Color.BLACK);
        nrMaxArrivalTime.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrMaxArrivalTime.setColumns(10);
        nrMaxArrivalTime.setBounds(569, 160, 69, 24);
        frame.getContentPane().add(nrMaxArrivalTime);
        nrMinServicelTime.setForeground(Color.BLACK);
        nrMinServicelTime.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrMinServicelTime.setColumns(10);
        nrMinServicelTime.setBounds(199, 227, 69, 24);
        frame.getContentPane().add(nrMinServicelTime);
        nrMaxServicelTime.setForeground(Color.BLACK);
        nrMaxServicelTime.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nrMaxServicelTime.setColumns(10);
        nrMaxServicelTime.setBounds(569, 227, 69, 24);
        frame.getContentPane().add(nrMaxServicelTime);
    }
    public void numeFis(){
        nameFileText.setForeground(Color.BLACK);
        nameFileText.setFont(new Font("Times New Roman", Font.BOLD, 15));
        nameFileText.setColumns(10);
        nameFileText.setBounds(236, 289, 254, 24);
        frame.getContentPane().add(nameFileText);
    }

    public void buton() {
        btnStart.setForeground(Color.BLACK);
        btnStart.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnStart.setBounds(247, 358, 141, 37);
        frame.getContentPane().add(btnStart);

        btnStart.addActionListener(e -> {
            ArrayList<Integer> date=new ArrayList();
            date.add(Integer.parseInt(getNrClients()));
            date.add(Integer.parseInt(getNrQueues()));
            date.add(Integer.parseInt(getNrSimulationTime()));
            date.add(Integer.parseInt(getNrMinArrivalTime()));
            date.add(Integer.parseInt((getNrMaxArrivalTime())));
            date.add(Integer.parseInt(getNrMinServicelTime()));
            date.add(Integer.parseInt(getNrMaxServicelTime()));
            try {
                SimulationManager thd=new SimulationManager(date,new File(getNameFileText()));
                Thread t = new Thread(thd);
                t.start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}

