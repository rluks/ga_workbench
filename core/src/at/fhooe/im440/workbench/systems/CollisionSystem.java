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
	List<ColliderComponent> colliding;
	
	public CollisionSystem(){
		colliders = new ArrayList<ColliderComponent>();
		colliding = new ArrayList<ColliderComponent>();
	}
	
	void HandleCollision(ColliderComponent a, ColliderComponent b){
		colliding.add(a);
		colliding.add(b);
	}
	
	void CalculateCollisions(){
		for(int i = 0; i < colliders.size(); ++i){
			ColliderComponent c1 = colliders.get(i);
			for(int j = 1; j < colliders.size(); ++j){
				ColliderComponent c2 = colliders.get(j);
				if(c1 == c2)
					continue;
				if(c1.isHit(c2)){
					HandleCollision(c1, c2);
				}
			}
		}
	}
	

	@Override
	public void Update(float delta) {
		ClearCollisons();
		CalculateCollisions();
		MarkCollisons();
	}
	
	private void MarkCollisons() {
		for(ColliderComponent c : colliding){
			c.isColliding = true;
		}
	}

	public void LateUpdate(){
		
	}
	
	private void ClearCollisons() {
		for(ColliderComponent c : colliding){
			c.isColliding = false;
		}
		colliding.clear();
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
		//System.out.println("testing: " + origin2d);
		for(ColliderComponent cc: colliders){
			if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				CircleColliderComponent.class.cast(cc).isHit(origin2d);
			}
		}
		
	}
	
	public Entity GetEntityByPos(Vector2 origin2d) {
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
