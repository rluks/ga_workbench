package at.fhooe.im440.workbench.systems.entity;

public class BoxColliderComponent extends ColliderComponent {

	public String name;
	
	@Override
	public boolean isHit(ColliderComponent otherCollider) {
		System.out.println(name + " BoxCollider:isHit(Collider)");
		
		System.out.println("this: " + this.getClass().getSimpleName());
		System.out.println("other: " + otherCollider.getClass().getSimpleName());
	
		//instead of (possibly multiple) if(c instance of)
		//we use this approach:
		//this knows its type, so we will just pass this to other collider
		//and do the collision detection there
		
		boolean result = otherCollider.isHitW(this);
		
		System.out.println("Result:" + result);
		return result;
	}
	
	@Override
	public boolean isHitW(CircleColliderComponent otherCollider){
		System.out.println(name + " BoxCollider:isHit(CircleCollider)");
		return true;
	}
	
	public boolean isHitW(BoxColliderComponent otherCollider){
		System.out.println(name + " BoxCollider:isHit(BoxCollider)");
		return true;
	}

}
