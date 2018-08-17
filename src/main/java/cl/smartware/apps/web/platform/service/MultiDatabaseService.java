package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Map;

public interface MultiDatabaseService
{
	List<String> getDatabasesListFromModule(String module);
	
	public List<String> getDatabasesListFromModule(WebPlatformModules module);
	
	public List<String> getTableList(String database);

	public List<Map<String, Object>> getRows(String database, String table);

	public List<String> getColumns(String database, String table);

	boolean isValidDatabase(WebPlatformModules webPlatformModule, String database);
}
