/**
 * This class will implement a process.
 */
public class Process implements Comparable{
    private int PID;
    private int cycles;
    public Process(){
        this.PID =0;
        this.cycles=0;
    }
    public Process(Process temp){
        this.PID = temp.PID;
        this.cycles = temp.cycles;
    }
    public Process(int newPid, int newCycles){
        this.PID = newPid;
        this.cycles = newCycles;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }
    public String toString(){
        return this.PID + "," + this.cycles;
    }


    @Override
    public int compareTo(Object o) {
        if(this.cycles < ((Process)o).cycles)
            return -1;
        else if(this.cycles > ((Process)o).cycles )
            return 1;
        else
        return 0;
    }
}
