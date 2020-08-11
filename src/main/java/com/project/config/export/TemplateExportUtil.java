package com.project.config.export;

import com.project.config.export.excel.ExcelExportUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

public class TemplateExportUtil {

	public static void create(String templateName, String exportName, Map<String, Object> paramMap) throws IOException, TemplateException {
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		URL resource = ExcelExportUtil.class.getResource(templateName);
		File resourceFile = new File(resource.getPath());
		configuration.setDirectoryForTemplateLoading(resourceFile.getParentFile());
		configuration.setClassicCompatible(false);
		Template template = configuration.getTemplate(resourceFile.getName());

		File file = new File(exportName);
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				throw new RuntimeException("创建文件夹失败：" + file.getParentFile().getAbsolutePath());
			}
		}
		Writer writer = new FileWriter(file);
		template.process(paramMap, writer);
		writer.flush();
		writer.close();
	}
}