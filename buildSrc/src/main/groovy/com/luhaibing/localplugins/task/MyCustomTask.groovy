package com.luhaibing.localplugins.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Created by luhaibing
 * <p>
 * Date: 2017-02-13.
 * Time: 00:28
 * <p>
 * className: MyCustomTask
 * classDescription: 自定义的任务
 */
public class MyCustomTask extends DefaultTask {

    @TaskAction
    void output() {
        println "这是一个自定义的任务的输出."
    }

}
