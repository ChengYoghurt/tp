package command;

import appointment.AppointmentList;

public class RemoveAppointmentCommand extends Command{

    public final String COMMAND_WORD = "remove";
    private final int appointmentId;

    public RemoveAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute(AppointmentList appointmentList) {
        AppointmentList.removeAppointment(appointmentId);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}