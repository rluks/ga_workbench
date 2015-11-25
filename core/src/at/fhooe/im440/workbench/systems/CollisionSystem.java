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
	
	
	//TODO store latest non colliding position and move it back there
	void HandleCollision(ColliderComponent a, ColliderComponent b){
		colliding.add(a);
		colliding.add(b);
		//TODO just report that there was a collision
		//report situation, some collliders will be triggers
	}
	
	void CalculateCollisions(){
		for(int i = 0; i < colliders.size(); ++i){
			ColliderComponent c1 = colliders.get(i);
			for(int j = i+1; j < colliders.size(); ++j){
				ColliderComponent c2 = colliders.get(j);
				if(c1 == c2)
					continue;
				if(c1.skipTesting || c2.skipTesting)
					continue;
				if(c1.isHit(c2)){
					HandleCollision(c1, c2);
				}
			}
		}
	}
	
	float timeaccumulator = 0;
	float fixedtimestep = 0.01f;//60 fps

	@Override
	public void Update(float delta) {
		
		//fixed timestep implemention / "fix your timestep"
		timeaccumulator+=delta;
		while(timeaccumulator >= fixedtimestep){
			//actualUpdate(fixedtimestep);
			ClearCollisons();
			CalculateCollisions();
			MarkCollisons();
			
			timeaccumulator-=fixedtimestep;
		}
		
		
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
			//if (cc.getClass().isAssignableFrom(CircleColliderComponent.class)){
				if(cc.isHit(origin2d)){
					return cc.Entity();
				}
			//}
		}
		
		return null;
		
	}

}
