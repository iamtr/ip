package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import duke.storage.Storage;

/**
 * Represents a list of tasks, with methods for operations on task list.
 */
public class Tasklist {
    private List<Task> todolist = new ArrayList<>();

    public Tasklist() {
        restoreData();
    }

    public void addItem(Task item) {
        todolist.add(item);
    }

    public Task removeItem(int index) {
        return todolist.remove(index);
    }

    public boolean isEmpty() {
        return todolist.isEmpty();
    }

    /**
     * Prints the list of tasks in the task list.
     *
     * @return The list of tasks in the task list.
     */
    public String printTodolist() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        if (isEmpty()) {
            s = new StringBuilder("Todolist is empty!");
            return s.toString();
        }
        for (Task t : todolist) {
            s.append(i).append(". ").append(t.toString()).append("\n\t");
            i++;
        }
        return s.toString().trim();
    }

    /**
     * Marks a task as done or undone in the task list.
     *
     * @param taskNumber The index of the task to be marked as done.
     * @param isDone The status of the task.
     */
    public void markTaskAsDone(int taskNumber, boolean isDone) {
        todolist.get(taskNumber).markAsDone(isDone);
    }

    /**
     * Gets the list of tasks in the task list.
     *
     * @return The list of tasks in the task list.
     */
    public List<Task> getTodolist() {
        return todolist;
    }

    /**
     * Gets the string representation of a task in the task list.
     *
     * @param taskNumber The index of the task.
     * @return The string representation of the task.
     */
    public String getTaskString(int taskNumber) {
        return todolist.get(taskNumber).toString();
    }

    /**
     * Restores the data from existing file, if any.
     *
     * @throws IOException If an error occurs while writing to the file.
     */
    public void restoreData() {
        try {
            boolean isDone;
            List<String> tasks = Storage.loadData();
            for (String task : tasks) {
                String[] details = task.split("\\|");
                isDone = !Objects.equals(details[1], "0");
                for (int i = 0; i < details.length; i++) {
                    details[i] = details[i].trim();
                }
                if (details[0].equals("T")) {
                    addItem(new Todo(details[2], isDone));
                } else if (details[0].equals("D")) {
                    addItem(new Deadline(details[2], LocalDate.parse(details[3]), isDone));
                } else if (details[0].equals("E")) {
                    addItem(new Event(details[2], LocalDate.parse(details[3]), LocalDate.parse(details[4]), isDone));
                }
            }
        } catch (IOException e) {
            System.out.println("An error has occured while executing loadData()");
        }

    }
}
