package com.luhaibing.localplugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by luhaibing
 * <p>
 * Date: 2017-02-12.
 * Time: 23:01
 * <p>
 * className: SecondPlugin
 * classDescription: 本地插件的实现类
 */
public class SecondPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        //System.out.println("========================");
        //System.out.println("这是第二个插件!");
        //System.out.println("========================");
        println ""
        println "========================"
        println "这是第二个插件!"
        println "========================"
        println ""
        project.task("secondTask") {
            println "这是第二个插件的任务!"
            println ""
        }
    }

}
