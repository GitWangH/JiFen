package oracle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.huatek.frame.core.model.BaseEntity;
/***
 *实体管理
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   
@Table(name="FW_SOURCE_ENTITY")
public class FwSourceEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5980873353006815407L;

	@Id
	@Column(name="ENTITY_ID", nullable=false)	
	@SequenceGenerator(name="my_seq", sequenceName="S_FRAMEWORK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(max=50)
	@Column(name = "ENTITY_NAME", nullable = false)
	private String entityName;
	
	@Size(max=50)
	@Column(name = "ENTITY_CLASS", nullable = false)
	private String entityClass;
	
	@Size(max=50)
	@Column(name = "ENTITY_FIELD", nullable = false)
	private String entityField;
	
	@Size(max=50)
	@Column(name = "ENTITY_COLUMN", nullable = false)
	private String entityColumn;
	
	@Size(max=200)
	@Column(name = "QUERY_URL", nullable = true)
	private String queryUrl;
	
	@Size(max=200)
	@Column(name = "QUERY_PARAM", nullable = true)
	private String queryParam;
	
	@Size(max=200)
	@Column(name = "ENTITY_ALIAS", nullable = true)
	private String entityAlias;
	
	@Size(max=50)
	@Column(name = "OUTPUT_KEY", nullable = false)
	private String outputKey;
	
	@Size(max=50)
	@Column(name = "OUTPUT_TITLE", nullable = false)
	private String outputTitle;
	
	@Size(max=50)
	@Column(name = "OUTPUT_CLASS", nullable = false)
	private String outputClass;
	
	@Size(max=50)
	@Column(name = "OUTPUT_VALUE", nullable = false)
	private String outputValue;
	
	@Column(name = "NOT_NULL")
	private int notNull;
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public String getEntityField() {
		return entityField;
	}
	public void setEntityField(String entityField) {
		this.entityField = entityField;
	}
	public String getEntityColumn() {
		return entityColumn;
	}
	public void setEntityColumn(String entityColumn) {
		this.entityColumn = entityColumn;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	public String getEntityAlias() {
		return entityAlias;
	}
	public void setEntityAlias(String entityAlias) {
		this.entityAlias = entityAlias;
	}
	public String getOutputKey() {
		return outputKey;
	}
	public void setOutputKey(String outputKey) {
		this.outputKey = outputKey;
	}
	public String getOutputTitle() {
		return outputTitle;
	}
	public void setOutputTitle(String outputTitle) {
		this.outputTitle = outputTitle;
	}
	public String getOutputClass() {
		return outputClass;
	}
	public void setOutputClass(String outputClass) {
		this.outputClass = outputClass;
	}
	public String getOutputValue() {
		return outputValue;
	}
	public void setOutputValue(String outputValue) {
		this.outputValue = outputValue;
	}
	public int getNotNull() {
		return notNull;
	}
	public void setNotNull(int notNull) {
		this.notNull = notNull;
	}
	
	public boolean isApplyQueryParam(){
		if(this.queryParam!=null&&this.entityClass.equals(this.outputClass)){
			return true;
		}
		return false;
	}
	
}