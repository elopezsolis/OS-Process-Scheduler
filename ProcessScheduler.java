import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * This program implements 4 schedulers. First-come First-Served,Shortest First Scheduler,
 * Round Robin Scheduler,and Random Scheduler
 */
public class ProcessScheduler {
    public static void main(String[] args){
        ArrayList<Process> queue;
        ProcessScheduler ps = new ProcessScheduler();
        queue = ps.ReadFile("in.txt");
        ps.FCFS(queue);
        ps.SFS(queue);
        ps.RRS(queue,50);
        ps.RRS(queue,100);
        ps.computeProbability(queue);
        ps.RandomScheduler(queue,50);
    }
    
    public void printTAT(double turnArTi, int size){
        System.out.printf("Average turnaround time: %.2f\n\n",turnArTi/(double)size);
    }

    /**
     * First Come First Serve. This method executes each process as they are queued in the List.
     * @param list - Contains the processes to schedule.
     */
    public void FCFS(ArrayList<Process> list){
        ArrayList<Process> temp = new ArrayList<>(list);
        int counter= 0;
        int size = temp.size();
        int turnaroundTime = 0;

        System.out.println("Running First-Come, First-Served scheduler.");
        while(!temp.isEmpty()){
            counter += temp.get(0).getCycles();
            System.out.println("Process " + temp.get(0).getPID() + "finishes on cycle " + counter);
            turnaroundTime += counter;
            temp.remove(0);
        }
        this.printTAT(turnaroundTime,size);
    }

    public ProcessScheduler(){}

    public void RandomScheduler(ArrayList<Process> queue, int quantum){
        ArrayList<Process> list = new ArrayList<>(queue);
        int counter =0;
        int total =0;
        System.out.println("Running Random Scheduler with quantum 50");
        while(!list.isEmpty()){
            int next = this.nextItem(list);
            Process current = list.get(next);

            if(current.getCycles() <= quantum) {
                counter += current.getCycles();
                System.out.println("Process " + current.getPID() + " finishes on cycle " + counter);
                total += counter;
                list.remove(next);
            }
            else {
                current.setCycles(current.getCycles() - quantum);
                counter += quantum;
            }
        }
        this.printTAT(total,queue.size());

    }
    public int nextItem(ArrayList<Process> list){
        double rand = Math.random();
        double[] probArr = this.computeProbability(list);
        double commProb =0.0;

        for(int i =0;i<list.size();i++){
            commProb += probArr[i];
            if(rand <= commProb)
                return i;
        }
        return -1;

    }
    public double[] computeProbability(ArrayList<Process> list){
        double[] prob = new double[list.size()];
        double total = 0;
        for ( int i =0; i <list.size();i++){
            total += list.get(i).getCycles();
        }

        for(int i=0;i<list.size();i++){
            prob[i] = list.get(i).getCycles()/total;
        }

        return prob;
    }

    /**
     * Shortest First Scheduler
     * Creates a copy of the passed List and sorts it, then it calls First Come First Serve
     * @param queue - the List of processes to schedule.
     */
    public void SFS(ArrayList<Process> queue){
        ArrayList<Process> temp = new ArrayList<>(queue);
        System.out.println("Running Shortest First Scheduler.");
        Collections.sort(temp);
        this.FCFS(temp);

    }
    /**
     * Round Robin Scheduler - Schedules each process only by the set quantum, it removes them from the front of the list
     * and if they have any cycles left then it puts them at the back of the list.
     * @param queue - list with all the processes
     * @param quantum - set amount of cycles for the process to be schedule for.
     */
    public void RRS(ArrayList<Process> queue, int quantum){
        ArrayList<Process> temp = new ArrayList<>(queue);
        int size = temp.size();
        int counter =0;
        double total =0;

        System.out.println("Running Round Robin with quantum " + quantum);
        while(!temp.isEmpty()){
            Process current = new Process(temp.get(0));
            temp.remove(0);
            if(current.getCycles() <= quantum) {
                counter += current.getCycles();
                current.setCycles(0);
                System.out.println("Process " + current.getPID() + " finishes on cycle " + counter);
                total += counter;
            }
            else {
                current.setCycles(current.getCycles() - quantum);
                counter += quantum;
                temp.add(current);
            }
        }
        this.printTAT(total,size);
    }
    public ArrayList<Process> ReadFile(String fileName){
        ArrayList<Process> queue = new ArrayList<>();
        try{
            Scanner fileIn = new Scanner(new File(fileName));
            while(fileIn.hasNext()){
                String[] temp = fileIn.next().split(",");
                queue.add(new Process(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])));
            }
        }catch(FileNotFoundException ex ){ex.printStackTrace();}
        return queue;
    }

}
