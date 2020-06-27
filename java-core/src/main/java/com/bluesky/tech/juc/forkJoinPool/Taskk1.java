package com.bluesky.tech.juc.forkJoinPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

class Taskk1 extends RecursiveAction {
    private long Load = 0;
    public Taskk1(long Load) {
        this.Load = Load;
    }
    @Override
    protected void compute() {
        //if work is above threshold, break tasks up into smaller tasks
        List<Taskk1> subtasks = new ArrayList<Taskk1>();
        subtasks.addAll(createSubtasks());
        for(RecursiveAction subtask : subtasks){
            subtask.fork();
        }
    }
    private List<Taskk1> createSubtasks() {
        List<Taskk1> subtasks =new ArrayList<Taskk1>();
        Taskk1 subtask1 = new Taskk1(this.Load / 2);
        Taskk1 subtask2 = new Taskk1(this.Load / 2);
        Taskk1 subtask3 = new Taskk1(this.Load / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        subtasks.add(subtask3);
        return subtasks;
    }
}
