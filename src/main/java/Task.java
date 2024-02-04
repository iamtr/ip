public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDataString() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }
}
