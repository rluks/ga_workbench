package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.List;

import at.fhooe.im440.workbench.entitysystem.ColliderComponent;
import at.fhooe.im440.workbench.entitysystem.Component;
import at.fhooe.im440.workbench.entitysystem.PhysicsComponent;

public class CollisionSystem extends BaseSystem implements IComponentSystem {

	List<ColliderComponent> colliders;
	
	public CollisionSystem(){
		colliders = new ArrayList<ColliderComponent>();
	}
	
	@Override
	public void Update(float delta) {
		/*for(PhysicsComponent pc: physics){
			pc.AngularVelocity=14.5f;
			pc.Update(delta);
		}*/

	}
	
	@Override
	public void Register(Component component) {
		colliders.add((ColliderComponent) component);
	}


	@Override
	public void Unregister(Component component) {
		colliders.remove((ColliderComponent) component);
	}

}
