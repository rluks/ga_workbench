package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class BoxColliderComponent extends ColliderComponent {

	public String name = "BoxCollider";
	
	public BoxColliderComponent(){
		super();
	}
	
	@Override
	public void Render(ShapeRenderer renderer){	
		float x = this.getPosition().x;
		float y = this.getPosition().y;
		float w2 = this.Entity().GetComponent(SpriteComponent.class).getHalfWidth();
		float h2 = this.Entity().GetComponent(SpriteComponent.class).getHalfHeight();
		float rotation = this.Entity().GetComponent(TransformComponent.class).Rotation;
		
		renderer.rect(x-w2, y-h2, w2, h2, 2*w2, 2*h2, 1.0f, 1.0f, rotation);
		
	}
	
	@Override
	public boolean isHit(ColliderComponent otherCollider) {
		//System.out.println(name + " BoxCollider:isHit(Collider)");
		
		//System.out.println("this: " + this.getClass().getSimpleName());
		//System.out.println("other: " + otherCollider.getClass().getSimpleName());
	
		//Double dispatch pattern
		//instead of (possibly multiple) if(c instance of)
		//we use this approach:
		//this knows its type, so we will just pass this to other collider
		//and do the collision detection there
		
		boolean result = otherCollider.isHitW(this);
		
		//System.out.println("Result:" + result);
		return result;
	}
	
	@Override
	public boolean isHitW(CircleColliderComponent otherCollider){
		//System.out.println(name + " BoxCollider:isHit(CircleCollider)");
		return otherCollider.isHitW(this);
	}
	
	@Override
	public boolean isHitW(BoxColliderComponent otherCollider){
		//System.out.println("Box vs Box collisions are not implemented!");
		return false;
	}

	@Override
	public boolean isHit(Vector2 p) {
		Vector2 pos = getPosition();
		SpriteComponent sc = this.Entity().GetComponent(SpriteComponent.class);
		float h2 = sc.getHalfHeight();
		float w2 = sc.getHalfWidth();
		if((p.x > pos.x-w2 && p.x < pos.x+w2)
		   &&(p.y > pos.y-h2 && p.y < pos.y+h2))
		{
				return true;
		}else
			return false;
	}

}
