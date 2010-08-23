package net.anotheria.maf.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Configuration of the Framework. This class contains all mappings the framework will react on.
 * @author another
 *
 */
public final class ActionMappings {
	
	/**
	 * Action aliases.
	 */
	private static final ConcurrentMap<String, String> aliases = new ConcurrentHashMap<String, String>();
	/**
	 * Action mappings.O
	 */
	private static final ConcurrentMap<String, ActionMapping> mappings = new ConcurrentHashMap<String, ActionMapping>(); 

	/**
	 * Adds a mapping.
	 * @param path
	 * @param type
	 * @param forwards
	 */
	public static void addMapping(String path, String type, ActionForward... forwards){
		mappings.put(path, new ActionMapping(path, type, forwards));
	}
	/**
	 * Adds a mapping.
	 * @param path
	 * @param type
	 * @param forwards
	 */
	public static void addMapping(String path, Class<? extends Action> type, ActionForward... forwards){
		mappings.put(path, new ActionMapping(path, type.getName(), forwards));
	}

	/**
	 * Adds an alias.
	 * @param sourcePath
	 * @param targetPath
	 */
	public static void addAlias(String sourcePath, String targetPath){
		aliases.put(sourcePath, targetPath);
	}
	
	public static ActionMapping findMapping(String actionPath){
		String alias = aliases.get(actionPath);
		if (alias!=null)
			return findMapping(alias);
		return mappings.get(actionPath);
	}
	
	private ActionMappings(){}
}
