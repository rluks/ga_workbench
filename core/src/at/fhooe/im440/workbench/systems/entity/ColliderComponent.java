package at.fhooe.im440.workbench.systems.entity;

import at.fhooe.im440.workbench.ServiceLocator;
import at.fhooe.im440.workbench.systems.ColliderRenderSystem;
import at.fhooe.im440.workbench.systems.CollisionSystem;
import at.fhooe.im440.workbench.systems.RenderSystem;

public abstract class ColliderComponent extends Component {

	//TODO bitsets flag, mask for col.filtering
	public boolean isColliding = false;
	
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
	
	abstract public boolean isHit(ColliderComponent c);

	abstract public boolean isHitW(CircleColliderComponent circleCollider);
	abstract public boolean isHitW(BoxColliderComponent circleCollider);

}
