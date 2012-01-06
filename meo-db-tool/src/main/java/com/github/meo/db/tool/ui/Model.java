package com.github.meo.db.tool.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.springframework.util.Assert;

import com.github.meo.db.tool.dao.Database;
import com.github.meo.db.tool.dao.EntityDao;
import com.github.meo.db.tool.dao.IEntityDao;
import com.github.meo.db.tool.domain.AttributeType;
import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IAttributeType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityRelationshipModel;
import com.github.meo.db.tool.domain.IEntityType;

public class Model {

	private EntityTableModel tableModel;
	private ComboBoxModel databaseComboBoxModel;
	private IEntityDao entityDao;
	private IEntityType entityType;
	private IAttributeType attributeType;
	private List<Database> databases;

	public Model() {
		init();
		databases.add(ModelTestObjects.getDatabaseAccountManagementSource());
		databases.add(ModelTestObjects.getDatabaseAccountManagementTarget());
	}

	private void init() {
		databases = new ArrayList<Database>();
	}

	public TableModel getTableModel() {

		IEntityType entityType = new EntityType("Entity Type");

		IAttributeType attributeTypeA = new AttributeType("Attribute Type A");
		IAttributeType attributeTypeB = new AttributeType("Attribute Type B");
		IAttributeType attributeTypeC = new AttributeType("Attribute Type C");

		entityType.addAttributeType(attributeTypeA);
		entityType.addAttributeType(attributeTypeB);
		entityType.addAttributeType(attributeTypeC);

		IEntity entityA = entityType.getEntity();
		IEntity entityB = entityType.getEntity();
		IEntity entityC = entityType.getEntity();

		List<IEntity> entities = new ArrayList<IEntity>();
		entities.add(entityA);
		entities.add(entityB);
		entities.add(entityC);

		tableModel = new EntityTableModel();
		tableModel.setEntities(entities);

		return tableModel;
	}

	public ComboBoxModel getDatabaseComboBoxModel() {
		databaseComboBoxModel = new DefaultComboBoxModel(getDatabases()
				.toArray());
		return databaseComboBoxModel;
	}

	public TreeModel getErmTreeModel(Database database) {

		DefaultMutableTreeNode root;

		root = new DefaultMutableTreeNode(database.getName());

		for (IEntityRelationshipModel erm : database
				.getEntityRelationshipModels()) {

			DefaultMutableTreeNode ermNode = new DefaultMutableTreeNode(erm);

			root.add(ermNode);

			for (IEntityType entityType : erm.getEntityTypes()) {

				DefaultMutableTreeNode entityNode = new DefaultMutableTreeNode(
						entityType);

				ermNode.add(entityNode);

				for (IAttributeType attributeType : entityType
						.getAttributeTypes()) {
					entityNode.add(new DefaultMutableTreeNode(attributeType));
				}
			}
		}

		return new DefaultTreeModel(root);
	}

	public IEntityDao getEntityDao() {
		return entityDao;
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public void setEntityDao(IEntityDao entityDao) {

		Assert.notNull(entityDao);

		this.entityDao = entityDao;
	}

	public void setEntityDao(Database database) {

		Assert.notNull(database);

		setEntityDao(new EntityDao(database));
	}

	public void setEntityType(IEntityType entityType) {
		this.entityType = entityType;
	}

	public void setAttributeType(IAttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public IAttributeType getAttributeType() {
		return attributeType;
	}

	public void addDatabase(Database database) {
		Assert.notNull(database);
		getDatabases().add(database);
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		Assert.notNull(databases);
		this.databases = databases;
	}

}