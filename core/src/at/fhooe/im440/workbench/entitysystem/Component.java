package at.fhooe.im440.workbench.entitysystem;

abstract public class Component {
	public final long Id;
	public boolean Active;
	private Entity entity;
	
	public Component() {
		maxId++;//TODO might theoretically overflow - is this safe?
		Id = maxId;
		
		Active = true;
	}
	
	public Component SetActive(boolean activeState){
		Active = activeState;
		return this;
	}
	
	public Entity Entity(){
		return this.entity;
	}
	
	//TODO make this visible only to Entity? (same package visible to entitysystem)
	protected void AddEntity(Entity entity) {
		this.entity = entity;		
	}
	
	//TODO make this visible only to Entity?
	protected void RemoveEntity() {
		this.entity = null;	
	}
	
	static private long maxId = 0;



}
