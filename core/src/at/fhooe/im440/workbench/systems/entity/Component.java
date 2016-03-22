package at.fhooe.im440.workbench.systems.entity;

abstract public class Component {
	
	public final long Id;
	public boolean Active;//TODO automate not calling stuff when not active
	private Entity entity;
	public boolean showDebug;
	
	public Component() {
		maxId++;//theoretically overflow - is this safe? y (long is very large number)
		Id = maxId;
		Active = true;
		Register();
		showDebug = false;
	}
	
	public Component SetActive(boolean activeState){
		Active = activeState;
		return this;
	}
	
	public Entity Entity(){
		return this.entity;
	}
	
	//TODO make this visible only to Entity? (same package visible to entitysystem)
	 void AddEntity(Entity entity) {//nothing package modifier
		this.entity = entity;		
	}
	
	void RemoveEntity() {
		this.entity = null;	
		//TODO when to call Unregister();?
	}
	
	abstract void Register();
	abstract void Unregister();
	
	static private long maxId = 0;

	public void Update(float delta) {
		// TODO Auto-generated method stub
		
	}



}
