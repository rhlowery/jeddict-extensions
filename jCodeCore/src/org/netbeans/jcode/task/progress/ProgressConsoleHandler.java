/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.jcode.task.progress;

import org.netbeans.jcode.task.ITaskSupervisor;

/**
 *
 * @author Gaurav Gupta
 */
public class ProgressConsoleHandler implements ProgressHandler {
    private final static int LIMIT = 100;
    private int taskLimit,state =0;//TODO taskLimit with %
    private final ITaskSupervisor taskSupervisor;

    public ProgressConsoleHandler(ITaskSupervisor taskSupervisor) {
        this.taskSupervisor = taskSupervisor;
        this.taskLimit=LIMIT;
    }
    
    public ProgressConsoleHandler(ITaskSupervisor taskSupervisor, int taskLimit) {
        this.taskSupervisor = taskSupervisor;
        this.taskLimit= taskLimit;
    }

    @Override
    public void progress(String message) {
        if (++state < taskLimit) {
            taskSupervisor.proceed(1);
        }
        taskSupervisor.log(message, true);
    }

    @Override
    public void append(String message) {
        taskSupervisor.log(message, true);
    }

    @Override
    public void start() {
        taskSupervisor.start(taskLimit);
    }

    @Override
    public void start(int step) {
        taskSupervisor.start(step);
    }

    @Override
    public void progress(String message, int step) {
        taskSupervisor.log(step, message, true);
    }

    @Override
    public void finish() {
    }

}