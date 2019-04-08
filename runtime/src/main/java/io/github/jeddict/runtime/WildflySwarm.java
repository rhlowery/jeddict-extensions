/**
 * Copyright 2013-2019 the original author or authors from the Jeddict project (https://jeddict.github.io/).
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
package io.github.jeddict.runtime;

import static io.github.jeddict.jcode.DatabaseType.H2;
import io.github.jeddict.jcode.RuntimeProvider;
import static io.github.jeddict.jcode.jpa.PersistenceProviderType.HIBERNATE;
import io.github.jeddict.jcode.util.BuildManager;
import org.openide.util.lookup.ServiceProvider;
import io.github.jeddict.jcode.annotation.Runtime;

//@ServiceProvider(service = RuntimeProvider.class)
//@Runtime(name = "WILDFLY_SWARM",
//        displayName = "Wildfly Swarm",
//        persistenceProvider = HIBERNATE,
//        embeddedDB = H2)
public final class WildflySwarm extends Wildfly {

    @Override
    public String getDockerTemplate() {
        return TEMPLATE + "DockerFile_JAVA.ftl";
    }

    @Override
    public String getBuildName() {
        return "${build.name}-swarm.jar";
    }

    @Override
    public void addDependency(boolean docker) {
        BuildManager.getInstance(project)
                .copy(TEMPLATE + "wildfly/swarm/pom/_pom.xml")
                .commit();
    }

    @Override
    public void addTestDependency(boolean docker) {
    }
}
