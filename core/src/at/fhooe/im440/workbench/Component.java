package at.fhooe.im440.workbench;

public class Component {
	public final long Id;
	public boolean Active;
	public Entity Entity;
	
	public Component() {
		maxId++;//TODO might theoretically overflow - is this safe?
		Id = maxId;
		
		Active = true;
	}
	
	public Component SetActive(boolean activeState){
		Active = activeState;
		return this;
	}
	
	//TODO make this visible only to Entity?
	public void AddEntity(Entity entity) {
		Entity = entity;		
	}
	
	//TODO make this visible only to Entity?
	public void RemoveEntity() {
		Entity = null;	
	}
	
	static private long maxId = 0;



}
