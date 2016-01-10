package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.im440.workbench.systems.entity.Component;
import at.fhooe.im440.workbench.systems.entity.EntitySystem;
import at.fhooe.im440.workbench.systems.entity.PhysicsComponent;
import at.fhooe.im440.workbench.systems.entity.SpriteComponent;

public class PhysicsSystem extends BaseSystem implements IComponentSystem {
	
	List<Component> physics;
	List<IForce> forces;
	
	public PhysicsSystem(){
		physics = new ArrayList<Component>();
	}
	
	@Override
	public void Update(float delta){
		for(Component pc: physics){
			pc.Update(delta);
		}
	}
	
	@Override
	public void Register(Component component) {
		physics.add((Component) component);
	}


	@Override
	public void Unregister(Component component) {
		physics.remove((Component) component);
	}
	
	public void Debug(boolean b){
		for(Component pc: physics){
			pc.showDebug = b;
			System.out.println("physics debug " + b);
		}
	}
}
