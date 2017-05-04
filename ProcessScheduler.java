import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program implements 4 schedulers. First-come First-Served,Shortest First Scheduler,
 * Round Robin Scheduler,and Random Scheduler
 */
public class ProcessScheduler {
    ArrayList<Process> queue;
    public static void main(String[] args){
        ProcessScheduler ps = new ProcessScheduler();
        ps.ReadFile("in.txt");
        System.out.println(ps.queue.toString());
        ps.FCFS();
    }
    
    public void printTAT(double turnArTi, int size){
        System.out.printf("Average turnaround time: %.2f",turnArTi/(double)size);
    }

    public void FCFS(){
        int counter= 0;
        int size = this.queue.size();
        int turnaroundTime = 0;
        while(!this.queue.isEmpty()){
            counter += this.queue.get(0).getCycles();
            System.out.println("Process " + this.queue.get(0).getPID() + "finishes on cycle " + counter);
            turnaroundTime += counter;
            this.queue.remove(0);
        }
        this.printTAT(turnaroundTime,size);
    }

    public ProcessScheduler(){
        this.queue = new ArrayList<>();
    }

    public void ReadFile(String fileName){
        try{
            Scanner fileIn = new Scanner(new File(fileName));
            while(fileIn.hasNext()){
                String[] temp = fileIn.next().split(",");
                this.queue.add(new Process(Integer.parseInt(temp[0]),Integer.parseInt(temp[1])));
            }
        }catch(FileNotFoundException ex ){ex.printStackTrace();}
    }
}
