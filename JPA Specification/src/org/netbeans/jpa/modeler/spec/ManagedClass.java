/**
 * Copyright [2014] Gaurav Gupta
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
package org.netbeans.jpa.modeler.spec;

import java.util.HashSet;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import javax.lang.model.element.TypeElement;
import javax.xml.bind.annotation.XmlAttribute;
import org.apache.commons.lang.StringUtils;
import org.netbeans.jpa.modeler.spec.extend.IAttributes;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;

public abstract class ManagedClass extends JavaClass {

    @XmlAttribute
    protected AccessType access;

    @XmlAttribute(name = "metadata-complete")
    protected Boolean metadataComplete;//REVENG PENDING


    public abstract IAttributes getAttributes();

    public abstract void setAttributes(IAttributes attributes);

    @Override
    public void load(EntityMappings entityMappings, TypeElement element, boolean fieldAccess) {
        super.load(entityMappings, element, fieldAccess);
        this.getAttributes().load(entityMappings, element, fieldAccess);
        this.access = AccessType.load(element);
//      this.metadataComplete = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "metadataComplete");
            
    }

    /**
     * Gets the value of the access property.
     *
     * @return possible object is {@link AccessType }
     *
     */
    public AccessType getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     *
     * @param value allowed object is {@link AccessType }
     *
     */
    public void setAccess(AccessType value) {
        this.access = value;
    }

    /**
     * Gets the value of the metadataComplete property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isMetadataComplete() {
        return metadataComplete;
    }

    /**
     * Sets the value of the metadataComplete property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setMetadataComplete(Boolean value) {
        this.metadataComplete = value;
    }

    public Set<String> getAllConvert(){
        return getAttributes().getAllConvert();
    }
}
