package at.fhooe.im440.workbench.systems;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.systems.entity.CircleColliderComponent;
import at.fhooe.im440.workbench.systems.entity.ColliderComponent;
import at.fhooe.im440.workbench.systems.entity.Component;
import at.fhooe.im440.workbench.systems.entity.Entity;
import at.fhooe.im440.workbench.systems.entity.PhysicsComponent;

public class CollisionSystem extends BaseSystem implements IComponentSystem {

	List<ColliderComponent> colliders;
	
	public CollisionSystem(){
		colliders = new ArrayList<ColliderComponent>();
	}
	
	@Override
	public void Update(float delta) {

	}
	
	@Override
	public void Register(Component component) {
		colliders.add((ColliderComponent) component);
	}


	@Override
	public void Unregister(Component component) {
		colliders.remove((ColliderComponent) component);
	}

	public void test(Vector2 origin2d) {
		System.out.println("testing: " + origin2d);
		for(ColliderComponent cc: colliders){
			if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				CircleColliderComponent.class.cast(cc).isHit(origin2d);
			}
		}
		
	}
	
	public Entity GetEntityByPos(Vector2 origin2d) {
		System.out.println("testing: " + origin2d);
		for(ColliderComponent cc: colliders){
			if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				if(CircleColliderComponent.class.cast(cc).isHit(origin2d)){
					return cc.Entity();
				}
			}
		}
		
		return null;
		
	}

}
