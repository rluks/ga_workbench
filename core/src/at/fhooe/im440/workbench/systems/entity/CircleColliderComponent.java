package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;

public class CircleColliderComponent extends ColliderComponent {

	public String name = "CircleCollider";
	public float radius = 30.0f;
	
	public CircleColliderComponent(){
		super();
	}
	
	public CircleColliderComponent setName(String n){
		this.name = n;
		return this;
	}
	
	public boolean isHit(Vector2 p){
		getPosition();//update on demand
		
		Vector2 c = position;//center of the circle
		//System.out.println("circle center: " + c);
		double distance = Math.sqrt((c.x - p.x)*(c.x - p.x) + (c.y - p.y)*(c.y - p.y));
		
		//distance from the center
		boolean result = (distance <= this.radius);
		//System.out.println(result);
		
		return result;
	}
	
	@Override
	public boolean isHit(ColliderComponent otherCollider) {	
		//System.out.println(name + " CircleCollider:isHit(Collider)");
		
		//System.out.println("this: " + this.getClass().getSimpleName());
		//System.out.println("other: " + otherCollider.getClass().getSimpleName());
		
		//update positions
		this.getPosition();
		otherCollider.getPosition();
		
		boolean result = otherCollider.isHitW(this);
		
		//System.out.println("Result:" + result);
		return result;
	}

	@Override
	public boolean isHitW(CircleColliderComponent otherCollider) {
		//System.out.println(this.Entity().Name + " vs " + otherCollider.Entity().Name + " CircleCollider:isHit(CircleCollider)");
		Vector2 a = this.position;
		Vector2 b = otherCollider.position;
		double distance = Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
		boolean result = (distance >= this.radius+otherCollider.radius) ? false : true;
		return result;
	}
	
	public boolean isHitW(BoxColliderComponent otherCollider){
		System.out.println(this.Entity().Name + " vs " + otherCollider.Entity().Name + " CircleCollider:isHit(CircleCollider)");
		
		//Vector from center of the circle to center of the bounding box
		Vector2 V = new Vector2();
		
		Vector2 cCenter = position.cpy();
		Vector2 bCenter = otherCollider.position.cpy();
		
		V.set(cCenter.sub(bCenter));
		
		VectorRenderSystem vrs = ServiceLocator.Get(VectorRenderSystem.class);
		vrs.RenderVector(bCenter, V, Color.RED);
		
		return false;
	}

}
