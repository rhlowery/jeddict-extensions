//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.internal.jpa.metadata.accessors.classes.XMLAttributes;
import static org.netbeans.jcode.jpa.JPAConstants.BASIC_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.ELEMENT_COLLECTION_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.EMBEDDED_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.EMBEDDED_ID_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.ID_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.MANY_TO_MANY_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.MANY_TO_ONE_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.ONE_TO_MANY_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.ONE_TO_ONE_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.TRANSIENT_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.VERSION_FQN;
import org.netbeans.jpa.modeler.db.accessor.EmbeddedIdSpecAccessor;
import org.netbeans.jpa.modeler.db.accessor.IdSpecAccessor;
import org.netbeans.jpa.modeler.db.accessor.VersionSpecAccessor;
import org.netbeans.jpa.modeler.spec.extend.Attribute;
import org.netbeans.jpa.modeler.spec.extend.PersistenceAttributes;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;
import org.netbeans.jpa.source.JavaSourceParserUtil;
import org.netbeans.jpa.modeler.spec.extend.IPrimaryKeyAttributes;
import org.netbeans.jpa.modeler.spec.workspace.WorkSpace;
import static org.netbeans.jpa.source.JavaSourceParserUtil.getElements;

/**
 *
 *
 * This element contains the entity field or property mappings. It may be
 * sparsely populated to include only a subset of the fields or properties. If
 * metadata-complete for the entity is true then the remainder of the attributes
 * will be defaulted according to the default rules.
 *
 *
 *
 * <p>
 * Java class for attributes complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="attributes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="id" type="{http://java.sun.com/xml/ns/persistence/orm}id" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="embedded-id" type="{http://java.sun.com/xml/ns/persistence/orm}embedded-id" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="basic" type="{http://java.sun.com/xml/ns/persistence/orm}basic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="version" type="{http://java.sun.com/xml/ns/persistence/orm}version" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="many-to-one" type="{http://java.sun.com/xml/ns/persistence/orm}many-to-one" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="one-to-many" type="{http://java.sun.com/xml/ns/persistence/orm}one-to-many" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="one-to-one" type="{http://java.sun.com/xml/ns/persistence/orm}one-to-one" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="many-to-many" type="{http://java.sun.com/xml/ns/persistence/orm}many-to-many" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="element-collection" type="{http://java.sun.com/xml/ns/persistence/orm}element-collection" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="embedded" type="{http://java.sun.com/xml/ns/persistence/orm}embedded" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="transient" type="{http://java.sun.com/xml/ns/persistence/orm}transient" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "primaryKeyAttributes", propOrder = {
    "description",
    "id",
    "embeddedId",
    "version"
})
public class PrimaryKeyAttributes extends PersistenceAttributes<IdentifiableClass> implements IPrimaryKeyAttributes {

    protected String description;
    protected List<Id> id;
    @XmlElement(name = "embedded-id")
    protected EmbeddedId embeddedId;
    protected List<Version> version;

    @Override
    public void load(EntityMappings entityMappings, TypeElement typeElement, boolean fieldAccess) {
        Set<String> mapsId = new HashSet<>();
        VariableElement embeddedIdVariableElement = null;
        List<Element> elements = getElements(typeElement, fieldAccess);

        //this is not manadatory but provided support for blog snippet which have no method
        if (!fieldAccess && elements.isEmpty()) {//if no elements then add all fields
            elements.addAll(JavaSourceParserUtil.getFields(typeElement));
        }
        for (Element element : elements) {
            VariableElement variableElement;
            ExecutableElement getterElement;
            if (element instanceof VariableElement) {
                variableElement = (VariableElement) element;
                getterElement = JavaSourceParserUtil.guessGetter(variableElement);
            } else {
                variableElement = JavaSourceParserUtil.guessField((ExecutableElement) element);
                getterElement = (ExecutableElement) element;
            }

            if (JavaSourceParserUtil.isAnnotatedWith(element, ID_FQN)
                    && !(JavaSourceParserUtil.isAnnotatedWith(element, ONE_TO_ONE_FQN)
                    || JavaSourceParserUtil.isAnnotatedWith(element, MANY_TO_ONE_FQN))) {
                this.addId(Id.load(element, variableElement, getterElement));
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, BASIC_FQN)) {
                this.addBasic(Basic.load(element, variableElement, getterElement));
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, TRANSIENT_FQN)) {
                this.addTransient(Transient.load(element, variableElement, getterElement));
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, VERSION_FQN)) {
                this.addVersion(Version.load(element, variableElement, getterElement));
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, ELEMENT_COLLECTION_FQN)) {
                this.addElementCollection(ElementCollection.load(entityMappings, element, variableElement, getterElement));
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, ONE_TO_ONE_FQN)) {
                OneToOne oneToOneObj = new OneToOne().load(entityMappings, element, variableElement, getterElement, null);
                this.addOneToOne(oneToOneObj);
                if (StringUtils.isNotBlank(oneToOneObj.getMapsId())) {
                    mapsId.add(oneToOneObj.getMapsId());
                } else {
                    mapsId.add(oneToOneObj.getName());
                }
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, MANY_TO_ONE_FQN)) {
                ManyToOne manyToOneObj = new ManyToOne().load(entityMappings, element, variableElement, getterElement, null);
                this.addManyToOne(manyToOneObj);
                if (StringUtils.isNotBlank(manyToOneObj.getMapsId())) {
                    mapsId.add(manyToOneObj.getMapsId());
                } else {
                    mapsId.add(manyToOneObj.getName());
                }
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, ONE_TO_MANY_FQN)) {
                OneToMany oneToManyObj = new OneToMany().load(entityMappings, element, variableElement, getterElement, null);
                this.addOneToMany(oneToManyObj);
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, MANY_TO_MANY_FQN)) {
                ManyToMany manyToManyObj = new ManyToMany().load(entityMappings, element, variableElement, getterElement, null);
                this.addManyToMany(manyToManyObj);
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, EMBEDDED_ID_FQN)) {
                this.setEmbeddedId(EmbeddedId.load(entityMappings, element, variableElement, getterElement));
                embeddedIdVariableElement = variableElement;
            } else if (JavaSourceParserUtil.isAnnotatedWith(element, EMBEDDED_FQN)) {
                this.addEmbedded(Embedded.load(entityMappings, element, variableElement, getterElement));
            } else {
                this.addBasic(Basic.load(element, variableElement, getterElement)); //Default Annotation
            }
        }

        if (this.getEmbeddedId() != null) {
            for (VariableElement variableElement : JavaSourceParserUtil.getFields(JavaSourceParserUtil.getAttributeTypeElement(embeddedIdVariableElement))) {
                if (!mapsId.contains(variableElement.getSimpleName().toString())) {
                    ExecutableElement getterElement = JavaSourceParserUtil.guessGetter(variableElement);
                    this.addId(Id.load(variableElement, variableElement, getterElement));
                }
            }
        }

    }

    @Override
    public List<Attribute> findAllAttribute(String name, boolean includeParentClassAttibute) {
        List<Attribute> attributes = super.findAllAttribute(name, includeParentClassAttibute);
        if (id != null) {
            for (Id id_TMP : id) {
                if (id_TMP.getName() != null && id_TMP.getName().equals(name)) {
                    attributes.add(id_TMP);
                }
            }
        }
        if (version != null) {
            for (Version version_TMP : version) {
                if (version_TMP.getName() != null && version_TMP.getName().equals(name)) {
                    attributes.add(version_TMP);
                }
            }
        }
        if (embeddedId != null) {
            if (embeddedId.getName() != null && embeddedId.getName().equals(name)) {
                attributes.add(embeddedId);
            }
        }

        return attributes;
    }

    @Override
    public boolean isAttributeExist(String name) {
        if (super.isAttributeExist(name)) {
            return true;
        }
        if (id != null) {
            for (Id id_TMP : id) {
                if (id_TMP.getName() != null && id_TMP.getName().equals(name)) {
                    return true;
                }
            }
        }
        if (version != null) {
            for (Version version_TMP : version) {
                if (version_TMP.getName() != null && version_TMP.getName().equals(name)) {
                    return true;
                }
            }
        }
        if (embeddedId != null) {
            if (embeddedId.getName() != null && embeddedId.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     *
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     *
     */
    @Override
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the id property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Id }
     *
     *
     */
    @Override
    public List<Id> getId() {
        if (id == null) {
            id = new ArrayList<>();
        }
        return this.id;
    }

    @Override
    public void addId(Id id) {
        this.getId().add(id);
        notifyListeners(id, "addAttribute", null, null);
        id.setAttributes(this);
    }

    @Override
    public void removeId(Id id) {
        this.getId().remove(id);
        notifyListeners(id, "removeAttribute", null, null);
    }

    public Optional<Id> getId(String id_) {
        if (id != null) {
            return id.stream().filter(a -> a.getId().equals(id_)).findFirst();
        }
        return null;
    }

    /**
     * Gets the value of the embeddedId property.
     *
     * @return possible object is {@link EmbeddedId }
     *
     */
    @Override
    public EmbeddedId getEmbeddedId() {
        return embeddedId;
    }

    /**
     * Sets the value of the embeddedId property.
     *
     * @param value allowed object is {@link EmbeddedId }
     *
     */
    @Override
    public void setEmbeddedId(EmbeddedId value) {
        this.embeddedId = value;
        if (value == null) {
            notifyListeners(null, "removeAttribute", null, null);
        } else {
            notifyListeners(embeddedId, "addAttribute", null, null);
            value.setAttributes(this);
        }

    }

    /**
     * Gets the value of the version property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the version property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersion().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Version }
     *
     *
     * @return 
     */
    @Override
    public List<Version> getVersion() {
        if (version == null) {
            version = new ArrayList<>();
        }
        return this.version;
    }

    @Override
    public List<Version> getSuperVersion() {
        List<Version> superVersion = new ArrayList();
        JavaClass currentManagedClass = getJavaClass();
        do {
            if (currentManagedClass instanceof IdentifiableClass) {
                IdentifiableClass identifiableClass = (IdentifiableClass) currentManagedClass;
                superVersion.addAll(identifiableClass.getAttributes().getVersion());
            }
            currentManagedClass = currentManagedClass.getSuperclass();
        } while (currentManagedClass != null);
        return superVersion;
    }

    @Override
    public void addVersion(Version version) {
        this.getVersion().add(version);
        notifyListeners(version, "addAttribute", null, null);
        version.setAttributes(this);
    }

    @Override
    public void removeVersion(Version version) {
        this.getVersion().remove(version);
        notifyListeners(version, "removeAttribute", null, null);
    }

    public Optional<Version> getVersion(String _id) {
        if (version != null) {
            return version.stream().filter(a -> a.getId().equals(_id)).findFirst();
        }
        return null;
    }

    @Override
    public List<Attribute> getAllAttribute(boolean includeParentClassAttibute) {
        List<Attribute> attributes = new ArrayList<>();
        if (this.getEmbeddedId() != null) {
            attributes.add(this.getEmbeddedId());
        }
        attributes.addAll(this.getId());
        attributes.addAll(super.getAllAttribute(includeParentClassAttibute));
        attributes.addAll(this.getVersion());
        return attributes;
    }

    @Override
    public XMLAttributes getAccessor(WorkSpace workSpace) {
        return getAccessor(workSpace, false);
    }

    //Remove inherit functionality , once eclipse support dynamic mapped superclass
    @Override
    public XMLAttributes getAccessor(WorkSpace workSpace, boolean inherit) {
        XMLAttributes attr = super.getAccessor(workSpace);
        attr.setIds(new ArrayList<>());
        attr.setVersions(new ArrayList<>());
        return updateAccessor(workSpace, attr, inherit);
    }

    public XMLAttributes updateAccessor(WorkSpace workSpace, XMLAttributes attr) {
        return updateAccessor(workSpace, attr, false);
    }

    @Override
    public XMLAttributes updateAccessor(WorkSpace workSpace, XMLAttributes attr, boolean inherit) {
        super.updateAccessor(workSpace, attr, inherit);
        return processAccessor(attr, inherit);
    }

    private XMLAttributes processAccessor(XMLAttributes attr, boolean inherit) {
        if (getEmbeddedId() != null) {
            attr.setEmbeddedId(EmbeddedIdSpecAccessor.getInstance(getEmbeddedId(), inherit));
        } else {
            attr.getIds().addAll(getId()
                    .stream()
                    .map(id -> IdSpecAccessor.getInstance(id, inherit))
                    .collect(toList()));
        }
        attr.getVersions().addAll(getVersion()
                .stream()
                .map(version -> VersionSpecAccessor.getInstance(version, inherit))
                .collect(toList()));

        return attr;
    }

    @Override
    public List<Attribute> getNonRelationAttributes() {
        List<Attribute> attributes = new ArrayList<>(this.getId());
        attributes.addAll(this.getBasic());
        attributes.addAll(this.getElementCollection().stream().filter(ec -> ec.getConnectedClass() == null).collect(toList()));
        attributes.addAll(this.getVersion());
        return attributes;
    }

    public boolean hasCompositePrimaryKey() {
        return this.getId().size() + (this.getEmbeddedId() != null ? 1 : 0)
                + this.getOneToOne().stream().filter(attr -> attr.isPrimaryKey()).count()
                + this.getManyToOne().stream().filter(attr -> attr.isPrimaryKey()).count() > 1;
    }

    @Override
    public Attribute getIdField() {
        List<Id> superIds = this.getSuperId();
        IdClass idClass;
        EmbeddedId superEmbeddedId;
        if (superIds.size() == 1) {
            return superIds.get(0);
        } else if ((superEmbeddedId = this.getSuperEmbeddedId()) != null) {
            return superEmbeddedId;
        } else if ((idClass = this.getSuperIdClass()) != null) {
            DefaultAttribute pkFindEntity = new DefaultAttribute();
            pkFindEntity.setName(idClass.getClazz());
            pkFindEntity.setAttributeType(idClass.getClazz());
            return pkFindEntity;
        } else { //no pk
            return null;
        }
    }

    @Override
    public List<Id> getSuperId() {
        List<Id> superIds = new ArrayList();
        JavaClass currentManagedClass = getJavaClass();
        do {
            if (currentManagedClass instanceof IdentifiableClass) {
                IdentifiableClass identifiableClass = (IdentifiableClass) currentManagedClass;
                superIds.addAll(identifiableClass.getAttributes().getId());
            }
            currentManagedClass = currentManagedClass.getSuperclass();
        } while (currentManagedClass != null);
        return superIds;
    }

    @Override
    public List<Attribute> getPrimaryKeyAttributes() {
        List<Attribute> superPrimaryKeys = new ArrayList();
        JavaClass currentManagedClass = getJavaClass();
        do {
            if (currentManagedClass instanceof IdentifiableClass) {
                IdentifiableClass identifiableClass = (IdentifiableClass) currentManagedClass;
                superPrimaryKeys.addAll(identifiableClass.getAttributes().getId());
                superPrimaryKeys.addAll(identifiableClass.getAttributes().getDerivedRelationAttributes());
            }
            currentManagedClass = currentManagedClass.getSuperclass();
        } while (currentManagedClass != null);
        return superPrimaryKeys;
    }

    public EmbeddedId getSuperEmbeddedId() {
        JavaClass currentManagedClass = getJavaClass();
        do {
            if (currentManagedClass instanceof IdentifiableClass) {
                IdentifiableClass identifiableClass = (IdentifiableClass) currentManagedClass;
                if (identifiableClass.getAttributes().getEmbeddedId() != null) {
                    return identifiableClass.getAttributes().getEmbeddedId();
                }
            }
            currentManagedClass = currentManagedClass.getSuperclass();
        } while (currentManagedClass != null);
        return null;
    }

    public IdClass getSuperIdClass() {
        JavaClass currentManagedClass = getJavaClass();
        do {
            if (currentManagedClass instanceof IdentifiableClass) {
                IdentifiableClass identifiableClass = (IdentifiableClass) currentManagedClass;
                if (identifiableClass.getIdClass() != null) {
                    return identifiableClass.getIdClass();
                }
            }
            currentManagedClass = currentManagedClass.getSuperclass();
        } while (currentManagedClass != null);
        return null;
    }

    @Override
    public Set<String> getConnectedClass() {
        Set<String> javaClasses = new HashSet<>(super.getConnectedClass());
        getCompositeKeyConnectedClass().ifPresent(jc -> javaClasses.add(jc));
        return javaClasses;
    }

    public Optional<String> getCompositeKeyConnectedClass() {
        List<Id> superIds = this.getSuperId();
        if (superIds.size() > 1) {
            EmbeddedId superEmbeddedId = this.getSuperEmbeddedId();
            if (superEmbeddedId != null) {
                return Optional.of(superEmbeddedId.getConnectedClass().getFQN());
            } else {
                IdClass idClass = this.getSuperIdClass();
                return Optional.of(getJavaClass().getRootPackage() + '.' + idClass.getClazz());
            }
        }
        return Optional.empty();
    }

}
