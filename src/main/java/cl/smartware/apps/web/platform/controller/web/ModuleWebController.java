package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.service.MultiDatabaseService;
import cl.smartware.apps.web.platform.service.WebPlatformModules;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/dashboard/modules")
public class ModuleWebController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
	
	@Autowired
	private MultiDatabaseService multiDatabaseService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleWebController.class);
	
	@Secured({ "ROLE_USER" })
	@GetMapping("")
	public String modules() throws IOException
	{
		LOGGER.info("Showing modules");
		return viewsComponentUtils.addThemeFolderToView("modules");
	}

	@Secured({ "ROLE_USER" })
	@GetMapping("/{module}")
	public String modules(@PathVariable("module") String module, Model model)
	{
		LOGGER.info(MessageFormat.format("Validating 'path-variable' module {0}", module));
		WebPlatformModules webPlatformModule = getValidWebPlatformModule(module);
		
		String moduleName = webPlatformModule.getModuleName();
		
		List<String> databases = multiDatabaseService.getDatabasesListFromModule(webPlatformModule);
		
		model.addAttribute("databases", databases);
		model.addAttribute("tableTitle", "Bases de datos en módulo: " + moduleName);
		model.addAttribute("module", module);
		model.addAttribute("moduleTitle", moduleName);
		
		LOGGER.info(MessageFormat.format("Showing module {0}", moduleName));
		return viewsComponentUtils.addThemeFolderToView("module");
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/{module}/{database}")
	public String databases(
			@PathVariable("module") String module
			, @PathVariable("database") String database
			, Model model)
	{
		return viewsComponentUtils.addThemeFolderToView("module-database");
	}
	
	private WebPlatformModules getValidWebPlatformModule(String module)
	{
		WebPlatformModules webPlatformModule = null;
		
		try
		{
			webPlatformModule = WebPlatformModules.valueOf(module.toUpperCase());
		}
		catch (IllegalArgumentException e)
		{
			LOGGER.warn(MessageFormat.format("Unknown module '{0}'", module));
			webPlatformModule = WebPlatformModules.UNKNOWN;
		}
		catch (NullPointerException e)
		{
			LOGGER.warn("The module is null, but is set it as 'unknown'");
			webPlatformModule = WebPlatformModules.UNKNOWN;
		}
		
		return webPlatformModule;
	}
}