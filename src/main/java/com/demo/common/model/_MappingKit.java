package com.demo.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("admin_user", "id", AdminUser.class);
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("class", "id", Class.class);
		arp.addMapping("exam", "id", Exam.class);
		arp.addMapping("student", "id", Student.class);
		arp.addMapping("subject", "id", Subject.class);
		arp.addMapping("teacher", "id", Teacher.class);
		arp.addMapping("temp_paper", "id", TempPaper.class);
		arp.addMapping("test_paper", "id", TestPaper.class);
		arp.addMapping("topic", "id", Topic.class);
		arp.addMapping("user", "id", User.class);
	}
}

