import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        System.out.println(queue.toString());
        ps.FCFS(queue);
        ps.SFS(queue);
    }
    
    public void printTAT(double turnArTi, int size){
        System.out.printf("Average turnaround time: %.2f",turnArTi/(double)size);
    }

    public void FCFS(ArrayList<Process> list){
        ArrayList<Process> temp = new ArrayList<>(list);
        int counter= 0;
        int size = temp.size();
        int turnaroundTime = 0;
        while(!temp.isEmpty()){
            counter += temp.get(0).getCycles();
            System.out.println("Process " + temp.get(0).getPID() + "finishes on cycle " + counter);
            turnaroundTime += counter;
            temp.remove(0);
        }
        this.printTAT(turnaroundTime,size);
    }

    public ProcessScheduler(){
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

    /**
     * Creates a copy of the passed queue and sorts it, then it calls First Come First Serve
     * @param queue - queue of processes to schedule.
     */
    public void SFS(ArrayList<Process> queue){
        ArrayList<Process> temp = new ArrayList<>(queue);
        Collections.sort(temp);
        System.out.println(temp.toString());
        this.FCFS(temp);

    }
}
