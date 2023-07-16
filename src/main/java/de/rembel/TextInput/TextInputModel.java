package de.rembel.TextInput;

import de.rembel.General.Command;

import java.util.UUID;

public class TextInputModel {

    private String message;
    private String reason;
    private UUID uuid;
    private Command command;
    private int time;

    public TextInputModel(String message, String reason, UUID uuid, Command command, int time){
        this.message=message;
        this.reason=reason;
        this.uuid=uuid;
        this.command=command;
        this.time=time;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Command getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getReason() {
        return reason;
    }

    public int getTime() {
        return time;
    }
}
