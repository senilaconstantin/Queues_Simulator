package pachet_assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    //public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    public File outF;
    public double sumServiceTime;

    private final Scheduler scheduler;

    private List<Task> generatedTasks;

    public SimulationManager(ArrayList<Integer> fR, File outF) throws IOException {

        this.numberOfClients = fR.get(0);
        this.numberOfServers = fR.get(1);
        this.timeLimit = fR.get(2);
        this.minArrivalTime = fR.get(3);
        this.maxArrivalTime = fR.get(4);
        this.minProcessingTime = fR.get(5);
        this.maxProcessingTime = fR.get(6);
        this.sumServiceTime = 0;
        this.outF = outF;
        try {
            this.outF.createNewFile();
        } catch (Exception e) {
            throw new FileNotFoundException("Nu s-a gasit fisierul si nici nu s-a putut creea!");
        }

        this.scheduler = new Scheduler(this.numberOfServers);
        generateNRandomTasks();

    }

    public SimulationManager(File inF, File outF) throws IOException {
        ArrayList<Integer> fR = fileRead(inF);
        this.numberOfClients = fR.get(0);
        this.numberOfServers = fR.get(1);
        this.timeLimit = fR.get(2);
        this.minArrivalTime = fR.get(3);
        this.maxArrivalTime = fR.get(4);
        this.minProcessingTime = fR.get(5);
        this.maxProcessingTime = fR.get(6);
        this.sumServiceTime = 0;
        this.outF = outF;
        this.scheduler = new Scheduler(this.numberOfServers);
        generateNRandomTasks();
    }

    private ArrayList<Integer> fileRead(File inF) throws FileNotFoundException {
        ArrayList<Integer> fR = new ArrayList<>();
        String s = "[1-9][0-9]*";

        Scanner fRead;
        try {
            fRead = new Scanner(inF);
        } catch (Exception e) {
            throw new FileNotFoundException("Nu s-a gasit fisierul!");
        }

        while (fRead.hasNextLine()) {
            String linie = fRead.nextLine();
            String[] splitLinie = linie.split(",");
            for (String str : splitLinie) {
                if (str.matches(s)) {
                    fR.add(Integer.valueOf(Integer.parseInt(str)));
                } else
                    System.out.println("Format invalid!!");
            }
        }

        fRead.close();

        return fR;
    }

    public int randomArrivingTime() {//cu Math.random() generez un nr double intre 0.0 si 0.99
        return (int) (Math.random() * (maxArrivalTime - minArrivalTime)) + minArrivalTime;
    }

    public int randomProcessingTime() {
        return (int) (Math.random() * (maxProcessingTime - minProcessingTime)) + minProcessingTime;
    }


    private void generateNRandomTasks() {//generez clientii apoi ii ordonez crescator dupa arrivingTime
        generatedTasks = new ArrayList<>(numberOfClients);

        for (int i = 0; i < numberOfClients; i++) {
            Task x = new Task(0, randomArrivingTime(), randomProcessingTime());
            generatedTasks.add(x);
        }

        Collections.sort(this.generatedTasks);//ordonez crescator

        for (int i = 1; i <= numberOfClients; i++) {
            Task x = this.generatedTasks.get(i - 1);
            x.setId(i);
            this.generatedTasks.set(i - 1, x);
            this.sumServiceTime += x.getProcessingTime();
        }
    }

    private String getString(int currentTime) {
        StringBuilder s = new StringBuilder("\nTime " + currentTime + "\n");
        s.append("Waiting clients: ");
        int i = 0;
        for (Task t : generatedTasks) {
            if (i == 0) {
                i = 1;
                s.append(t.toString());
            } else
                s.append("; ").append(t.toString());
        }
        s.append("\n").append(scheduler.toString());

        return s.toString();
    }

    private int getMq() {
        int maxTQ;
        if (generatedTasks.isEmpty()) {
            maxTQ = scheduler.getTotWait();
        } else {
            maxTQ = 0;
        }
        return maxTQ;
    }


    @Override
    public void run() {
        int currentTime = 0;
        int maxTQ = 0;

        FileWriter fw = null;
        try {
            fw = new FileWriter(this.outF.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (currentTime < timeLimit && (!generatedTasks.isEmpty() || maxTQ > 0)) {
            while (!generatedTasks.isEmpty() && generatedTasks.get(0).getArrivalTime() == currentTime) {
                scheduler.dispatchTask(generatedTasks.get(0));
                generatedTasks.remove(0);
            }
            String s = getString(currentTime);
            try {
                assert fw != null;
                fw.write(s);
                System.out.println(s);
            } catch (Exception ignored) {
            }
            maxTQ = getMq();
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        }
        this.scheduler.deadThread();
        try {
            double avg = (double) this.scheduler.getSum() / (double) this.numberOfClients;
            assert fw != null;
            fw.write("\nAverage waiting time: " + avg);
            fw.write("\nAverage service time: " + (this.sumServiceTime / (double) this.numberOfClients));
            System.out.println("Average waiting time: " + avg);
            System.out.println("Average service time: " + (this.sumServiceTime / (double) this.numberOfClients));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ///Ex1
//        SimulationManager thd = new SimulationManager(new File("in1.txt"), new File("out1.txt"));
//        Thread t = new Thread(thd);
//        t.start();

        ////Ex2
//        SimulationManager thd2 = new SimulationManager(new File("in2.txt"), new File("out2.txt"));
//        Thread t2 = new Thread(thd2);
//        t2.start();


        ////Ex3
//        SimulationManager thd3 = new SimulationManager(new File("in3.txt"), new File("out3.txt"));
//        Thread t3 = new Thread(thd3);
//        t3.start();


        ///interfata Gui

        Gui g = new Gui();


    }

}
