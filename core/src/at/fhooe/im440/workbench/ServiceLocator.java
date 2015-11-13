package at.fhooe.im440.workbench;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import at.fhooe.im440.workbench.entitysystem.EntitySystem;
import at.fhooe.im440.workbench.systems.BaseSystem;

public class ServiceLocator {
	static LinkedHashMap<String, BaseSystem> systems;
	//TODO singleton? 
	static{}//TODO static constructor
	
	public static void Init(){
		systems = new LinkedHashMap<String, BaseSystem>();

	}
	
	//TODO should we update all of systems here? yy
	public static void Update(float delta){
		for(Entry<String, BaseSystem> entry: systems.entrySet()){
			BaseSystem system = entry.getValue();
			system.Update(delta);
		}
	}
	
	//TODO where will i initialize systems? 
	//TODO from where i call register?
	public static void Register(BaseSystem system){
		System.out.println("ServiceLocator Register:" + system.getClass().getSimpleName());
		systems.put(system.getClass().getSimpleName(), system);
	}
	
	public static void Unregister(BaseSystem system){
		systems.remove(system.getClass().getSimpleName());
	}
	
	@Deprecated
	public static BaseSystem Get(String systemName){
		return systems.get(systemName);
	}
	
	public static <T extends BaseSystem> T Get(Class<T> systemType) 
	{	
		for(Entry<String, BaseSystem> entry: systems.entrySet()){
			BaseSystem system = entry.getValue();
			if (system.getClass().isAssignableFrom(systemType))
				return systemType.cast(system);
		}
		
		throw new IllegalArgumentException("ServiceLocator>>System not found");
		//return null;
		
	}
}
