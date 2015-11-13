package at.fhooe.im440.workbench.entitysystem;

import at.fhooe.im440.workbench.CollisionSystem;
import at.fhooe.im440.workbench.ServiceLocator;

public abstract class ColliderComponent extends Component {

	@Override
	void Register() {
		CollisionSystem s = ServiceLocator.Get(CollisionSystem.class);
		s.Register(this);	
	}

	@Override
	void Unregister() {
		// TODO Auto-generated method stub

	}
	
	abstract public boolean isHit(ColliderComponent c);

	abstract public boolean isHitW(CircleColliderComponent circleCollider);
	abstract public boolean isHitW(BoxColliderComponent circleCollider);

}
