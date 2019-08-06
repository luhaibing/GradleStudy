package com.luhaibing.localplugins.task;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by luhaibing
 * <p>
 * Date: 2017-02-13.
 * Time: 00:31
 * <p>
 * className: MyCustomPlugin
 * classDescription: ...
 */
public class MyCustomPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.task('customTask', type: MyCustomTask)
    }

}
