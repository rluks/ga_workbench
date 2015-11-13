package at.fhooe.im440.workbench.systems.entity;

import java.util.LinkedHashMap;

public class Entity {
	public final long Id;
	public String Name;
	public boolean Active;
	
	public Entity() {
		maxId++;//TODO might theoretically overflow - is this safe?
		Id = maxId;
		
		Name = "Entity n." + Id;
		Active = true;
		
		components = new LinkedHashMap<String, Component>();
	}
	
	public Entity Name(String name){
		Name = name;
		return this;
	}
	
	public Entity SetActive(boolean activeState){
		Active = activeState;
		return this;
	}
	
	public Entity AddComponent(Component component){
		component.AddEntity(this);//TODO or some other way to inject reference?
		components.put(component.getClass().getSimpleName(), component);//what if there is already same type?
		return this;
	}
	
	@Deprecated
	public Component GetComponent(String type){
		return components.get(type);
	}
		
	public <T extends Component> T GetComponent(Class<T> componentType){	
		String type = componentType.getSimpleName();
		return componentType.cast(components.get(type));
	}
	
	public Component RemoveComponent(String type){
		Component c = components.remove(type);
		c.RemoveEntity();
		return c;
	}
	
	//Decisions decisions
	//private List<Component> componentsList;
	//private Map<String, Component> componentsMap;
	private LinkedHashMap<String, Component> components;
	
	static private long maxId = 0;
	
	
}
