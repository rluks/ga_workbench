package at.fhooe.im440.workbench.entitysystem;

import com.badlogic.gdx.math.Vector2;

public class CircleColliderComponent extends ColliderComponent {

	public String name = "CircleCollider";
	public float radius = 30.0f;
	public float offset;//TODO better way to calculate this (maybe move anchor to center of entity for tc.pos?)
	Vector2 position;
	
	public CircleColliderComponent(){
		super();

	}
	
	public Vector2 getPosition(){
		TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
		position = tc.Position;
		return position;
	}
	
	public CircleColliderComponent setRadius(float r){
		this.radius = r;
		return this;
	}
	
	public CircleColliderComponent setName(String n){
		this.name = n;
		return this;
	}
	
	void setOffset(){
		//TODO
		//TODO offset
	}
	
	public boolean isHit(Vector2 p){
		setOffset();
		TransformComponent tc = this.Entity().GetComponent(TransformComponent.class);
		Vector2 c = tc.Position;//center of the circle
		System.out.println("circle center: " + c);
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
