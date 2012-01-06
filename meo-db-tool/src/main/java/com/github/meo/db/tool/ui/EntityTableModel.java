package com.github.meo.db.tool.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.springframework.util.Assert;

import com.github.meo.db.tool.domain.EntityType;
import com.github.meo.db.tool.domain.IEntity;
import com.github.meo.db.tool.domain.IEntityType;

public class EntityTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4896925340182805217L;
	private IEntityType entityType;
	private List<IEntity> entities;

	public EntityTableModel() {
		init();
	}

	public EntityTableModel(List<IEntity> entities) {
		init();
		setEntities(entities);
	}

	private void init() {
		entities = new ArrayList<IEntity>();
		entityType = new EntityType();
	}

	public int getColumnCount() {
		return entityType.getAttributeTypes().size();
	}

	public int getRowCount() {
		return entities.size();
	}

	public String getColumnName(int columnIndex) {
		return entityType.getAttributeTypes().get(columnIndex).getName();
	}

	public Object getValueAt(int row, int column) {
		return entities.get(row).getAttributes().get(column).getValue();
	}

	public List<IEntity> getEntities() {
		return entities;
	}
	
	public IEntity getEntity(final int rowIndex) {
		return entities.get(rowIndex);
	}

	public void setEntities(List<IEntity> entities) {

		Assert.notNull(entities);

		if (entities.size() > 0) {
			setEntityType(entities.get(0).getEntityType());
		}

		this.entities = entities;
	}

	public void addEntity(IEntity entity) {

		Assert.notNull(entity);

		entities.add(entity);
	}

	public IEntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(IEntityType entityType) {

		Assert.notNull(entityType);

		this.entityType = entityType;
	}

}
