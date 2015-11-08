package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.entitysystem.PhysicsComponent;
import at.fhooe.im440.workbench.entitysystem.SpriteComponent;

public class PhysicsSystem extends BaseSystem implements IComponentSystem {
	List<PhysicsComponent> physics;
	
	public PhysicsSystem(){
		physics = new ArrayList<PhysicsComponent>();
		//EntitySystem es = (EntitySystem)ServiceLocator.Get("EntitySystem");
		//PhysicsComponent pc = (PhysicsComponent) es.GetTest().GetComponent("PhysicsComponent");
		//physics.add(pc);
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
}
