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
package org.netbeans.jcode.generator.internal;

import java.io.IOException;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.jcode.generator.AbstractGenerator;
import org.netbeans.jcode.jpa.util.PersistenceHelper.PersistenceUnit;

/**
 *
 * @author Gaurav Gupta
 */
public abstract class BaseApplicationGenerator extends AbstractGenerator {

    private PersistenceUnit persistenceUnit;
    private Project project;

    /**
     * Creates a new instance of EntityRESTServicesCodeGenerator
     * @param project
     * @param persistenceUnit
     */
    public void initialize(Project project,PersistenceUnit persistenceUnit) {
        this.project = project;
        this.persistenceUnit = persistenceUnit;
    }

    protected void preGenerate(List<String> fqnEntities) throws IOException {
    }

    protected void configurePersistence() {
    }


    protected Project getProject() {
        return project;
    }

    protected PersistenceUnit getPersistenceUnit() {
        return persistenceUnit;
    }

}
