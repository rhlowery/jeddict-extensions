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
package org.netbeans.jcode.ng.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import org.netbeans.jcode.console.Console;
import static org.netbeans.jcode.console.Console.BOLD;
import static org.netbeans.jcode.console.Console.FG_RED;
import static org.netbeans.jcode.console.Console.UNDERLINE;
import org.netbeans.jcode.core.util.FileUtil;
import static org.netbeans.jcode.core.util.ProjectHelper.getProjectWebRoot;
import org.netbeans.jcode.layer.Technology;
import static org.netbeans.jcode.layer.Technology.Type.VIEWER;
import org.netbeans.jcode.rest.controller.RESTGenerator;
import org.openide.util.lookup.ServiceProvider;
import org.netbeans.jcode.layer.Generator;
import static org.netbeans.jcode.ng.main.AngularUtil.copyDynamicFile;
import static org.netbeans.jcode.ng.main.AngularUtil.getResource;
import org.netbeans.jcode.ng.main.domain.ApplicationSourceFilter;
import org.netbeans.jcode.ng.main.domain.NG1SourceFilter;
import org.netbeans.jcode.ng.main.domain.NGApplicationConfig;
import org.netbeans.jcode.ng.main.domain.NGEntity;
import org.netbeans.jcode.parser.ejs.EJSParser;
import org.netbeans.jpa.modeler.spec.Entity;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Gaurav Gupta
 */
@ServiceProvider(service = Generator.class)
@Technology(type = VIEWER, label = "Angular JS 1", panel = AngularPanel.class, parents = {RESTGenerator.class})
public class Angular1Generator extends AngularGenerator {

    private static final String TEMPLATE = "org/netbeans/jcode/template/angular1/";
    private ApplicationSourceFilter sourceFilter;

    @Override
    public String getTemplatePath() {
        return TEMPLATE;
    }

    @Override
    protected ApplicationSourceFilter getApplicationSourceFilter(NGApplicationConfig applicationConfig) {
        if (sourceFilter == null) {
            sourceFilter = new NG1SourceFilter(applicationConfig);
        }
        return sourceFilter;
    }
    private final static String MODULE_JS = "app/app.module.js";
    
    @Override
        protected void generateClientSideComponent() {
        try {
            
            NGApplicationConfig applicationConfig = getAppConfig();
            ApplicationSourceFilter fileFilter = getApplicationSourceFilter(applicationConfig);
            
            handler.append(Console.wrap(AngularGenerator.class, "MSG_Copying_Entity_Files", FG_RED, BOLD, UNDERLINE));
            Map<String, String> templateLib = getResource(getTemplatePath() + "entity-include-resources.zip");
            List<NGEntity> entities = new ArrayList<>();
            for (Entity entity : entityMapping.getConcreteEntity().collect(toList())) {
                NGEntity ngEntity = getEntity(entity);
                if (ngEntity != null) {
                    entities.add(ngEntity);
                    generateNgEntity(applicationConfig, fileFilter, getEntityConfig(), ngEntity, templateLib);
                    generateNgEntityi18nResource(applicationConfig, fileFilter, ngEntity);
                }
            }
            applicationConfig.setEntities(entities);
            
            generateNgApplication(applicationConfig, fileFilter);
            generateNgApplicationi18nResource(applicationConfig, fileFilter);
            generateNgLocaleResource(applicationConfig, fileFilter);
            generateNgHome(applicationConfig, fileFilter);
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
        
    private void generateNgHome(NGApplicationConfig applicationConfig, ApplicationSourceFilter fileFilter) throws IOException {
        FileObject webRoot = getProjectWebRoot(project);

        Map<String, Object> data = new HashMap();
        data.put("entityScriptFiles", entityScriptFiles);
        scriptFiles.remove(MODULE_JS);
        scriptFiles.add(0, MODULE_JS);
        data.put("scriptFiles", scriptFiles);

        EJSParser parser = new EJSParser();
        parser.addContext(applicationConfig);
        parser.addContext(data);

        Function<String, String> pathResolver = templatePath -> templatePath.substring(templatePath.lastIndexOf("/_")+2);// "_index.html" ->  "index.html";

        copyDynamicFile(getParserManager(parser, null),getTemplatePath() +  "_index.html", webRoot, pathResolver, handler);
        copyDynamicFile(getParserManager(parser, null), getTemplatePath() + "_bower.json", project.getProjectDirectory(), pathResolver, handler);
        handler.append(Console.wrap(AngularGenerator.class, "MSG_Copying_Bower_Lib_Files", FG_RED, BOLD));
        FileUtil.copyStaticResource(getTemplatePath() + "bower_components.zip", webRoot, null, handler);
    }

}
