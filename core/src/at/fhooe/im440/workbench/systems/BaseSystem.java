package at.fhooe.im440.workbench.systems;

import at.fhooe.im440.workbench.systems.entity.Component;

abstract public class BaseSystem {
	public abstract void Update(float delta);
	public abstract void LateUpdate();
}

interface IComponentSystem {
	
	public abstract void Register(Component component);
	public abstract void Unregister(Component component);

}
