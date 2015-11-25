package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.VectorRenderSystem;

public class CircleColliderComponent extends ColliderComponent {

	public String name = "CircleCollider";
	public float radius = 30.0f;
	
	public CircleColliderComponent(){
		super();
	}
	
	@Override
	public void Render(ShapeRenderer renderer){			
		renderer.circle(this.getPosition().x, this.getPosition().y, this.radius);
	}
	
	public CircleColliderComponent setName(String n){
		this.name = n;
		return this;
	}
	
	@Override
	public boolean isHit(Vector2 p){
		
		//center of the circle
		Vector2 c = getPosition();
		
		//calculate distance
		double distance = getDistance(c, p);
		
		return (distance <= this.radius);
	}
	
	@Override
	public boolean isHit(ColliderComponent otherCollider) {	
		//System.out.println(name + " CircleCollider:isHit(Collider)");
		
		//System.out.println("this: " + this.getClass().getSimpleName());
		//System.out.println("other: " + otherCollider.getClass().getSimpleName());
			
		boolean result = otherCollider.isHitW(this);
		
		//System.out.println("Result:" + result);
		return result;
	}

	@Override
	public boolean isHitW(CircleColliderComponent otherCollider) {
		//System.out.println(this.Entity().Name + " vs " + otherCollider.Entity().Name + " CircleCollider:isHit(CircleCollider)");
		
		Vector2 a = getPosition();
		Vector2 b = otherCollider.getPosition();
		double distance = getDistance(a, b);
		boolean result = (distance >= this.radius+otherCollider.radius) ? false : true;
		return result;
	}
	
	@Override
	public boolean isHitW(BoxColliderComponent otherCollider){
		//System.out.println(this.Entity().Name + " vs " + otherCollider.Entity().Name + " CircleCollider:isHit(CircleCollider)");
		VectorRenderSystem vrs = ServiceLocator.Get(VectorRenderSystem.class);
		
		//translate the axis of the circle (aka. move circle to the space of box, so we can use simple axis aligned method)
		//first create translation matrix using difference between bottom left corner of box and origin 0,0
		Vector2 btmLeftCorn = otherCollider.getPosition();
		SpriteComponent scG = otherCollider.Entity().GetComponent(SpriteComponent.class);
		float boxHalfHeightG = scG.getHalfHeight();
		float boxHalfWidthG = scG.getHalfWidth();
		btmLeftCorn.x -= boxHalfWidthG;
		btmLeftCorn.y -= boxHalfHeightG;
		
		Vector2 translation = Vector2.Zero.sub(btmLeftCorn);
		//and rotate it by the angle of box rotation
		float rotation = otherCollider.Entity().GetComponent(TransformComponent.class).Rotation;
		Matrix3 mat = new Matrix3().setToTranslation(translation).setToRotation(rotation);
		
		//now use the matrix to translate, move the center of the circle
		Vector2 translatedCenter = getPosition().mul(mat);
		vrs.RenderVector(getPosition(), translatedCenter, Color.WHITE);
		
		//Vector from center of the circle to center of the bounding box
		Vector2 V = new Vector2();
		
		Vector2 cCenter = getPosition();
		Vector2 bCenter = otherCollider.getPosition();
		
		V.set(cCenter.cpy().sub(bCenter));
		

		//vrs.RenderVector(bCenter, V, Color.BLACK);
		
		//Clamp it to get projection of the center of the circle
		//on boundary of the box
		//clamping is done by half width/height of the box
		SpriteComponent sc = otherCollider.Entity().GetComponent(SpriteComponent.class);
		float boxHalfHeight = sc.getHalfHeight();
		float boxHalfWidth = sc.getHalfWidth();
		float clmX = MathUtils.clamp(V.x, -boxHalfWidth, boxHalfWidth);
		float clmY = MathUtils.clamp(V.y, -boxHalfHeight, boxHalfHeight);
		
		Vector2 clampedV = new Vector2(bCenter.x+clmX, bCenter.y+clmY);
		vrs.RenderVector(bCenter, clampedV, Color.GREEN);
		
		//distance between clampedV and the center of the circle
		float distance = getDistance(cCenter, clampedV);
		
		//and subtract the radius of the circle
		distance -= this.radius;
		
		//System.out.println(distance);
		
		return (distance <= 0); 
		
		//return false;
	}


}
