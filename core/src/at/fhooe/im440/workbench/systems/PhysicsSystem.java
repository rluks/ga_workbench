package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.im440.workbench.systems.entity.Component;
import at.fhooe.im440.workbench.systems.entity.EntitySystem;
import at.fhooe.im440.workbench.systems.entity.PhysicsComponent;
import at.fhooe.im440.workbench.systems.entity.SpriteComponent;

public class PhysicsSystem extends BaseSystem implements IComponentSystem {
	
	List<PhysicsComponent> physics;
	
	public PhysicsSystem(){
		physics = new ArrayList<PhysicsComponent>();
	}
	
	@Override
	public void Update(float delta){
		for(PhysicsComponent pc: physics){
			pc.AngularVelocity=14.5f;
			pc.Update(delta);
		}
	}
	
	@Override
	public void Register(Component component) {
		physics.add((PhysicsComponent) component);
	}


	@Override
	public void Unregister(Component component) {
		physics.remove((PhysicsComponent) component);
	}

	@Override
	public void LateUpdate() {
		// TODO Auto-generated method stub
		
	}
}
