package com.luhaibing.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 插件实现类
 */
public class FirstPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        //这里实现plugin的逻辑
        println ""
        System.out.println("========================");
        System.out.println("这是第一个插件!");
        System.out.println("========================");
        //println ""
        //println("========================")
        //println("这是第一个插件!")
        //println("========================")
        println ""
        //比如这里加一个简单的task
        project.task('cooker-test-task') {
            println "这是第一个插件的任务!"
            println ""
        }
    }

}