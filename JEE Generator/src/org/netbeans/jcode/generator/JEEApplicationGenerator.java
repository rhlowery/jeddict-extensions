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
package org.netbeans.jcode.generator;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.jcode.generator.internal.ApplicationGeneratorFactory;
import org.netbeans.jcode.generator.internal.BaseApplicationGenerator;
import org.netbeans.jcode.jpa.util.PersistenceHelper;
import org.netbeans.jcode.stack.config.data.*;
import org.netbeans.jcode.task.progress.ProgressHandler;
import org.openide.util.Exceptions;

/**
 *
 * @author Gaurav Gupta
 */
public class JEEApplicationGenerator {

    private static JEEApplicationGenerator INSTANCE;

    private JEEApplicationGenerator() {

    }

    public static JEEApplicationGenerator getInstance() {
        if (INSTANCE == null) {
            synchronized (JEEApplicationGenerator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JEEApplicationGenerator();
                }
            }
        }
        return INSTANCE;
    }

    public static void generate(ProgressHandler progressHandler, ApplicationConfigData applicationConfigData) {
        try {
            final Project project = applicationConfigData.getProject();
            final PersistenceHelper.PersistenceUnit pu = (PersistenceHelper.PersistenceUnit) new PersistenceHelper(project).getPersistenceUnit();
            final BaseApplicationGenerator generator = ApplicationGeneratorFactory.newInstance(project);
            generator.initialize(project, pu);
            generator.generate(applicationConfigData, progressHandler);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

}
