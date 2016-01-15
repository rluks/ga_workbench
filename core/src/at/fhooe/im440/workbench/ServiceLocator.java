package at.fhooe.im440.workbench;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import at.fhooe.im440.workbench.systems.BaseSystem;

public class ServiceLocator {
	static LinkedHashMap<String, BaseSystem> systems;
	//TODO singleton? 
	static{}//TODO static constructor
	
	static int debugIndex;
	
	public static void Init(){
		systems = new LinkedHashMap<String, BaseSystem>();
		debugIndex = 0;
	}
	
	//update all systems here
	public static void Update(float delta){
		for(Entry<String, BaseSystem> entry: systems.entrySet()){
			BaseSystem system = entry.getValue();
			system.Update(delta);
		}
	}
	
	//where will i initialize systems? - in each individual screen differently
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

	public static void DebugNextSystem() {
		
		debugIndex++;
		debugIndex %= systems.size();
		
		//String selected = "PhysicsSystem";
		int index = 0;
		for(Entry<String, BaseSystem> entry: systems.entrySet()){
			BaseSystem system = entry.getValue();
			if(index == debugIndex){
				system.Debug(true);
				System.out.println("Debugging " + entry.getKey());
			}else
				system.Debug(false);
			index++;
		}
		
	}
}
