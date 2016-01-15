package at.fhooe.im440.workbench.systems.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.ColliderRenderSystem;
import at.fhooe.im440.workbench.systems.CollisionSystem;

public abstract class ColliderComponent extends Component {

	// TODO bitsets flag, mask for col.filtering
	public boolean isColliding = false;
	public boolean skipTesting = false;

	@Override
	void Register() {
		CollisionSystem s = ServiceLocator.Get(CollisionSystem.class);
		s.Register(this);
		ColliderRenderSystem crs = ServiceLocator.Get(ColliderRenderSystem.class);
		crs.Register(this);
	}

	@Override
	void Unregister() {
		// TODO Auto-generated method stub

	}

	/*
	 * Returns copy of Position Vector2 from TransformComponent
	 */
	public Vector2 getPosition() {
		return this.Entity().GetComponent(TransformComponent.class).Position.cpy();
	}
	
	public float getDistance(Vector2 a, Vector2 b){
		return (float)Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
	}
	
	public abstract void Render(ShapeRenderer renderer);

	abstract public boolean isHit(ColliderComponent c);

	abstract public boolean isHitW(CircleColliderComponent circleCollider);

	abstract public boolean isHitW(BoxColliderComponent circleCollider);

	public abstract boolean isHit(Vector2 p);

}
