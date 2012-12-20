package nl.kennisnet.arena.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;



@Entity
public class Combined extends Positionable implements DomainObject {

	@OneToMany(targetEntity = Type.class)
	private List<Type> items = new ArrayList<Type>();

	public List<Type> getItems() {
		return items;
	}

	public void setItems(List<Type> items) {
		this.items = items;
	}
	
	
}
