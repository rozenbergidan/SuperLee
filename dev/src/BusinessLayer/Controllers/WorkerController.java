package BusinessLayer.Controllers;

import BusinessLayer.Workers.Worker;
import BusinessLayer.Workers.WorkersList;

import java.util.Date;

public class WorkerController {
    private Worker loggedIn;
    private WorkersList workersList;

    public WorkerController(){
        this.workersList = new WorkersList();
    }

     public void addWorker(boolean isAdmin, String name, String id, String bankAccount, double salary, String educationFund,
                           int vacationDaysPerMonth, int sickDaysPerMonth, String startWorkingDate) throws Exception {
        if(loggedIn == null) throw new Exception("cant add new worker to the system because no worker is logged in");
        if(!loggedIn.getIsAdmin()) throw new Exception("cant add new worker to the system because loggedIn is not admin");
        if(workersList.contains(id)) throw new Exception("the system already has worker with the ID: " + id);
        workersList.addWorker(isAdmin, name, id, bankAccount, salary, educationFund, vacationDaysPerMonth,
                sickDaysPerMonth, startWorkingDate);
     }

     public void addConstraint(String date, )
}
