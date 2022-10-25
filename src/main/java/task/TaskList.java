package task;

import appointment.AppointmentList;
import employee.EmployeeList;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> tasks = new ArrayList<>();

    //view every single task in the clinic
    public static void viewTasks() {
        System.out.println("Here are all the tasks:");
        for (Task task : tasks) {
            System.out.println("_______________________________________");
            task.printTask();
        }
    }

    public static void addTask(Task task) {
        tasks.add(task);
        // appointment aggregate task
        AppointmentList.findAppointment(task.getAppointmentId()).addTaskToAppointment(task);
        // employee aggregate task
        EmployeeList.findEmployee(task.getEmployeeId()).addTaskToEmployee(task);
        // added to overall task list
        System.out.print("Got it. I've added this task: ");
        System.out.println(task.getTaskDescription());
        System.out.println("Performed by: " + task.getEmployeeId());
        System.out.println("Appointment: " + task.getAppointmentId());
        System.out.println("Now you have " + tasks.size() + " task in the list.");
    }

    // assign task to be done by another person
    public static void reassignTask(int taskId, int employeeId){
        Task taskToReassign = TaskList.findTask(taskId);
        // Remove from original Employee's task list
        if(taskToReassign != null){
            EmployeeList.findEmployee(taskToReassign.getEmployeeId()).removeTaskFromEmployee(taskId);
            // Add to new Employee's task list
            EmployeeList.findEmployee(employeeId).addTaskToEmployee(taskToReassign);
            // Change employeeId in taskToReassign
            taskToReassign.setEmployeeId(employeeId);
        }
    }

    public static void removeTask(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                System.out.print("Got it. I've removed this task: ");
                System.out.println(task.getTaskDescription());
                System.out.println("Performed by: " + task.getEmployeeId());
                System.out.println("Appointment: " + task.getAppointmentId());
                System.out.println("Now you have " + tasks.size() + " task in the list.");
                break;
            }
        }
    }

    public static Task findTask(int taskId){
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }

}
