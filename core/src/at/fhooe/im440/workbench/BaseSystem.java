package at.fhooe.im440.workbench;

import at.fhooe.im440.workbench.entitysystem.Component;

abstract public class BaseSystem {
	public abstract void Update(float delta);
}

interface IComponentSystem {
	
	public abstract void Register(Component component);
	public abstract void Unregister(Component component);

}
