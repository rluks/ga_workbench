package at.fhooe.im440.workbench.entitysystem;

import com.badlogic.gdx.math.Vector2;

public class CircleColliderComponent extends ColliderComponent {

	public String name = "CircleCollider";
	public float radius = 1.0f;
	//TODO offset from origin of entity
	
	public CircleColliderComponent setRadius(float r){
		this.radius = r;
		return this;
	}
	
	public CircleColliderComponent setName(String n){
		this.name = n;
		return this;
	}
	
	public boolean isHit(Vector2 p){
		TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
		Vector2 c = tc.Position;//center of the circle
		double distance = Math.sqrt((c.x - p.x)*(c.x - p.x) + (c.y - p.y)*(c.y - p.y));
		//distance from the center
		boolean result = (distance <= this.radius);
		System.out.println(result);
		return result;
	}
	
	@Override
	public boolean isHit(ColliderComponent otherCollider) {	
		System.out.println(name + " CircleCollider:isHit(Collider)");
		
		System.out.println("this: " + this.getClass().getSimpleName());
		System.out.println("other: " + otherCollider.getClass().getSimpleName());
		
		boolean result = otherCollider.isHitW(this);
		
		System.out.println("Result:" + result);
		return result;
	}

	@Override
	public boolean isHitW(CircleColliderComponent otherCollider) {
		System.out.println(name + " CircleCollider:isHit(CircleCollider)");
		return true;
	}
	
	public boolean isHitW(BoxColliderComponent otherCollider){
		System.out.println(name + " CircleCollider:isHit(BoxCollider)");
		return true;
	}

}
