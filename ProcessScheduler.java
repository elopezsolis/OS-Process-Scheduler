import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
    }


    public void FCFS(){

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
